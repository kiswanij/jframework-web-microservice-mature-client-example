# Create Java WebApp with Mature Microservice
_Last updated: 2022-11-26_


This repository contains a _JKFramework-WebStack_ example that communicates with a Mature microservice backend.

## Prerequisites:
- JDK 17+ Installed
- [Create Mature Microservice](https://github.com/kiswanij/jk-framework-microservice-mature-example)
- Run the Mature Microservice 

## Usage:
1. Clone the repository
2. Import as Java project into your favorite IDE (`We use SpringToolSuite and Eclipse for Java EE developers`)
3. Run `App` class.


## Project Main Contents 
The repository  contains the following main contents: 
1. Maven project with [pom.xml](pom.xml)
  > :page_facing_up:*Note*: If you are using Eclipse, be sure to refresh your project (select your project→ right click→ Maven→ Update Project)
2. [Config file](src/main/resources/config.properties)  
3. [Java Model class](src/main/java/com/app/person/Model.java)    
4. [Java ServiceClient class](src/main/java/com/app/person/ServiceClient.java) 
5. [Faces Controller](src/main/java/com/app/person/Controller.java) 
6. [Facelets template](src/main/webapp/WEB-INF/templates/default.xhtml) 
7. [Faces View](src/main/webapp/index.xhtml)   
8. [Main java class](src/main/java/com/app/App.java)  

## Screenshots
Thats it, now run your App class, in few seconds your browser will open and you should see something like this:

![Screenshot](screenshots/home.png)
