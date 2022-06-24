from globals import *

def createFolder():
    isExist = os.path.exists(folderName)
    if not isExist: #check if folder exists
        os.makedirs(folderName) #if not then create a new folder


def createCountryCsv():
    global allCountries
    Countries = glob("CountriesData\*")
    for i in range(0,len(Countries)):#take all the countries that exist in the folder
        CountryProfil = glob(Countries[i]+"\*.csv")[1] 
        data = pd.read_csv(CountryProfil, skiprows=0)#read metadata_country csv
        data = data.iloc[:,:-1] #drop this last column
        data = data.drop(columns=['SpecialNotes']) #drop SpecialNotes column
        if i == 0 :
            allCountries = data
        else:
            allCountries = allCountries.append(data, ignore_index=True)
    allCountries.index = allCountries.index + 1
    allCountries.index.name = 'Country_Id'
    allCountries.to_csv(folderName+"\Countries.csv",index=True)
    

def createIndicatorCsv():
    Countries = glob("CountriesData\*")
    CountryIndicators = glob(Countries[0]+"\*.csv")[2]
    allIndicators = pd.read_csv(CountryIndicators, skiprows=0)#read metadata_Indicator csv
    allIndicators = allIndicators.iloc[:,:-1] #drop this last column
    allIndicators = allIndicators.sort_values(allIndicators.columns[0],ignore_index=True) # sort by first column
    allIndicators.index = allIndicators.index + 1
    allIndicators.index.name = 'Indicator_Id'
    allIndicators.to_csv(folderName+"\Indicators.csv",index=True)


def createDataCsv():
    Countries = glob("CountriesData\*")
    for i in range(0,len(Countries)):#take all the countries that exist in the folder
        CountryData = glob(Countries[i]+"\*.csv")[0] 
        data = pd.read_csv(CountryData, skiprows=4)#read metadata_country csv
        data = data.iloc[:,:-1] #drop this last column
        data = data.drop(columns=['Country Name','Indicator Name']) #drop SpecialNotes column
        data = data.rename(columns={'Country Code': 'Country_Id','Indicator Code': 'Indicator_Id'})
        data = data.sort_values(data.columns[1],ignore_index=True)
        data.index = data.index + 1
        data['Indicator_Id'] = data.index
        country = data['Country_Id'].iloc[0]
        countryIndex = allCountries.loc[allCountries['Country Code']== country].index.values[0]
        data['Country_Id'] = countryIndex
        data = pd.melt(data,id_vars=[data.columns[0],data.columns[1]],var_name = 'Year')
        if i == 0 :
            AllData = data
        else:
            AllData = AllData.append(data, ignore_index=True)
    AllData.to_csv(folderName+"\AllData.csv",index=False)



def readyForLoadCsv():
    createFolder()
    createCountryCsv()
    createIndicatorCsv()
    createDataCsv()