from globals import *

try:
    connection = mysql.connector.connect(host="localhost" , user="root",passwd="password!",allow_local_infile=True)
    print("Connected to localhost db")
    cursor = connection.cursor()
except Exception as e:
    print(e)
    sys.exit(1)

def createDatabase():
    cursor.execute("CREATE DATABASE IF NOT EXISTS "+dbName)
    cursor.execute("USE "+dbName+";")
    print("Database",dbName,"was succesfully created")

def createCountyTable():
    countries = pd.read_csv(folderName+"\Countries.csv",skiprows=0)
    columnName = countries.columns
    EndQuery = "UNIQUE KEY (Country_Code)"
    query = ""
    for i,q in enumerate(columnName):
        r = re.findall('([A-Z])', q[1:])
        q = q.replace(" ","_")
        if r :
            ind = q[1:].index(r[0])
            if q[ind] !="_":
                q = q.replace(r[0],"_"+r[0])
        if i == 0:
            query = query +q + " INT PRIMARY KEY,"
        else:
            query = query +q + " VARCHAR(40),"
    query = query + EndQuery
    cursor.execute("CREATE TABLE IF NOT EXISTS Countries("+query+")")
    print("Table Countries was succesfully created")


def loadInTableCountries():
    cursor.execute("SET GLOBAL local_infile=1;")
    cursor.execute("LOAD DATA LOCAL INFILE '"+folderName+"""/Countries.csv' INTO TABLE Countries FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\r\n' IGNORE 1 LINES;""")
    connection.commit()
    print("Data Countries succesfully loaded")

def createIndicatorTable():
    Indicators = pd.read_csv(folderName+"\Indicators.csv",skiprows=0)
    columnName = Indicators.columns
    EndQuery = "UNIQUE KEY (Indicator_Code)"
    query = ""
    for i,q in enumerate(columnName):
        q = q.replace(" ","_")
        if i == 0:
            query = query +q + " INT PRIMARY KEY,"
        elif i == 1:
            query = query +q + " VARCHAR(100),"
        else:
            query = query +q + " TEXT,"
    query = query + EndQuery
    cursor.execute("CREATE TABLE IF NOT EXISTS Indicators("+query+")")
    print("Table Indicators was succesfully created")

def loadInTableIndicators():
    cursor.execute("SET GLOBAL local_infile=1;")
    cursor.execute("LOAD DATA LOCAL INFILE '"+folderName+"""/Indicators.csv' INTO TABLE Indicators FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\r\n' IGNORE 1 LINES;""")
    connection.commit()
    print("Data Indicators succesfully loaded")

def createDataTable():
    AllData = pd.read_csv(folderName+"\AllData.csv",skiprows=0)
    columnName = AllData.columns
    #EndQuery = "FOREIGN KEY (Country_Id) REFERENCES Countries(Country_Id), FOREIGN KEY (Indicator_Id) REFERENCES Indicators(Indicator_Id)"
    query = ""
    for i,q in enumerate(columnName):
        q = q.replace(" ","_")
        if i < 3:
            query = query +q + " INT,"
        else:
            query = query +q + " DOUBLE,"
    query = query[:-1]
    cursor.execute("CREATE TABLE IF NOT EXISTS AllData("+query+")")
    print("Table AllData was succesfully created")

def loadInTableData():
    startLoad = time.time()
    cursor.execute("SET GLOBAL local_infile=1;")
    cursor.execute("SET @@FOREIGN_KEY_CHECKS = 0;")
    cursor.execute("LOAD DATA LOCAL INFILE '"+folderName+"""/AllData.csv' INTO TABLE AllData FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\r\n' IGNORE 1 LINES;""")
    endLoad = time.time()
    totalLoad = endLoad - startLoad
    print("load time is: "+str(totalLoad))

    startForeign1 = time.time()
    cursor.execute("ALTER TABLE AllData ADD FOREIGN KEY (Country_Id) REFERENCES Countries(Country_Id)")
    endForeign1 = time.time()
    totalForeign1 = endForeign1 - startForeign1
    print("foreign 1 time is: "+str(totalForeign1))

    startForeign2 = time.time()
    cursor.execute("ALTER TABLE AllData ADD FOREIGN KEY (Indicator_Id) REFERENCES Indicators(Indicator_Id)")
    endForeign2 = time.time()
    totalForeign2 = endForeign2 - startForeign2
    print("foreign 2 time is: "+str(totalForeign2))

    startPrimary = time.time()
    cursor.execute("ALTER TABLE AllData ADD PRIMARY KEY(Country_Id,Indicator_Id,Year)")
    endPrimary = time.time()
    totalPrimary = endPrimary - startPrimary
    print("Primary time is: "+str(totalPrimary))
    connection.commit()
    print("Data AllData succesfully loaded")
    


def loadData():
    createDatabase()
    createCountyTable()
    loadInTableCountries()
    createIndicatorTable()
    loadInTableIndicators()
    createDataTable()
    loadInTableData()
