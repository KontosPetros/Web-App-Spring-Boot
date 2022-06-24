# Project-db
### Ονόματα Ομάδας :
* ΚΟΝΤΟΣ ΠΕΤΡΟΣ        Α.Μ:2898
* ΓΕΩΡΓΙΑΔΗΣ ΓΕΩΡΓΙΟΣ  Α.Μ:3199

### Υλοποίηση project: 
* spring boot (backend) 
* Html,css,javascript,bootstrap (frontend)
* thymeleaf (για την σύνδεση frontend με backend)
* HighCharts (graphs)


### Επεξήγηση φακέλων και αρχείων :
Στον φάκελο **Load data script** περιέχονται :
* **CountrieData** : φάκελος που περιέχει τα δεδομένα για κάθε χώρα ξεχωριστα.
* **ReadyForLoad** : φάκελος που δημιουργήται μετά το τρέξιμο του script (readyForLoadCsv.py) και περιέχει τα δεδομένα που είναι έτοιμα να φορτωθούν στην βάση.
* **globals.py** : περιέχει κάποιες global μεταβλητές και καλείται σε όλα τα υπόλοιπα scripts.
* **readyForLoadCsv.py** : script που μετασχηματίζει τα δεδομένα του φακέλου CountriesData και τα ετοιμάζει ώστε να φορτωθούν στην βάση κατευθείαν.
* **loadData.py** : script που δημιουργεί την βάση και τα tables και φορτώνει τα αρχεία csv ,που βρίσκονται στον φάκελο ReadyForLoad, κατευθείαν στην βάση.
* **main.py** : script που τρέχει τα 2 παραπάνω script.

Στον φάκελο **project** περιέχονται όλα τα αρχεία που χρειάζεται η spring boot για να τρέξει το project. (ο υλοποιημένος κώδικας βρίσκεται μέσα στον φάκελο src)

Στον φάκελο **deliverables** περιέχονται :
* Ένα pdf που περιέχει την αναφορά του project.
* Ένα αρχείο txt που περιέχει το link του βίντεο με την επεξήγηση όλα του project.

### Για να τρέξει το project πρέπει να αλλαχτούν το username και το password της βάσης στο αρχείο "Load data script/loadData.py" και στο αρχείο "project/src/main/resources/application.properties".
