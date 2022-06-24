This project is about collecting data from 25 countries in Europe, from World Bank organization http://data.worldbank.org/. Data includes many indicators for each one of the countries based on a spesific period of time. After normalizing csv files , they are used to create the database.Then using Spring Boot, the web app is created. User can choose beside these 25 countries  and indicators and create plots.   




### project: 
* spring boot (backend) 
* Html,css,javascript,bootstrap (frontend)
* thymeleaf (connection frontend - backend)
* HighCharts (graphs)


### Explanation of folders and files :

Inside Load data script:
* **CountrieData** : folder that contains data for each one of the countries.
* **ReadyForLoad** : After running readyForLoadCsv.py, normalized csv files are saved in this folder to be loaded to database.
* **globals.py** : global variables.
* **readyForLoadCsv.py** : script to normalize csv files.
* **loadData.py** : create the database.
* **main.py** : main

* **project** : Spring boot App.

