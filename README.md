# MS3
MS3 Interview

HOW TO RUN THE CODE:

1. Download the zipped folder from Github and open the folder in Eclipse or any IDE.

2. We need the JAR SQLITE to run the code. So we need to download the JAR file that will act as a connection branch between 
SQLite and Java Code. We can google it or we can use this link to download the file. 
https://bitbucket.org/xerial/sqlite-jdbc/downloads/

3. Now we need to attach the JAR file with the project. If we are using Eclipse, we can go simply to the project and right click on the project and choose “Build Path” and then select the option that says “Configure Build Path”.

4. A new window will pop up and choose the tab “Libraries”. Select the “Modulepath” and click on “Add External JARs”. Select the JAR file.

5. We need to make sure the file location in the code that we want to parse.

6. Then we need to create the database, where we will parse the data. We can go to the terminal. and go to sqlite folder and type the following:

cd sqlite            // to go to the sqlite folder
sqlite3 arif.db.     // to create the database
.database            // to make sure the database is saved

APPROACH:

I made 2 classes. In one class called “Data”, i have created the getter and setter so that we can create the objects in the Main class. 

In another class, I have started the connection with the database. Then i have parse the data from the csv file using comma split by and then i save the parsed data (whose column is equal to 10) in the data objects. And then i have saved the data objects inside list called "DataList". Then i have created SQL statement to create the table "X" and inserting the data from the list into the database. I have also printed the the data that are stored in the database in the console using select statement. 

I have created a csv file ("badDataCSV") to save the data file. I have used for loop and filewriter class to save each bad data into the CSV file.

I have commented very often in the code for your better understanding.


