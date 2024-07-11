# Prerequisites

JDK

MySQL Database (if you want to use another kind of database, you'll need to change the application.properties at src/main/resources)

You don't need to do anything, the database is automatically created when you run the application

# Execution
Run the following command to build the project to a .jar

*.\mvnw clean package*

Then, run you database server

Then, run this command to execute your .jar

*java -jar target\back-0.0.1-SNAPSHOT.jar*

your API is now available at "http://localhost:8080/api" !

You can try to see if it is working using Postman. Look at the multiple controllers in com.example.back.controllers package to see the available api endpoints.

(You'll need the desktop postman agent since it's in localhost)

