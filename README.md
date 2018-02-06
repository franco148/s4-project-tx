# README - S4-PROJECT-TX #
Super System Scheduling System test project with Java

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* A test using Java, Spring Framework.
* Version 1.0

### Project Structure ###

* com.fral.extreme.s4: root package.
* common.dto         : contains the common objects used in the project.
* controllers        : contains the REST API controllers
* domain             : contains two sub packages, they are: model and repository
* model              : contains the model objects.
* repositoy          : contains the interface definitions for DAL layer.
* exception          : contains exception classes.
* services           : contains the services of the application.

### How do I get set up? ###


For build the artifact you need execute the console commands:

+ __Execute the following command to remove the target directory with all the build data before starting so that it is fresh__: 
```
mvn clean
```

+ __Execute the following command to compile your application sources__: 
```
mvn compile
```


+ __Execute the following command to make a JAR file of project__: 
```
mvn package
```



+ __To initialize the service you will need to run the following command.__: 
```
java -jar target\s4-project-tx-1.0-SNAPSHOT.jar
```

### Improvements ###
- Improve Error Handling.
- Add more unit tests
- Enable API documentation
- Parameterize the application.

### Who do I talk to? ###

* Repo owner or admin
* franco.robert.fral@gmail.com
