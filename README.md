# Create Java WebApp with Mature Microservice
_Last updated: 2022-11-26_


This repository contains a _JKFramework-WebStack_ project that communicates with a Mature microservice backend.

## Pre-Requisites:
- [Create Mature Microservice](https://github.com/kiswanij/jk-framework-microservice-mature-example)
- Run the Mature Microservice

## Project Main Contents 
The repiository contains the following main contents: 
1. Maven project with [pom.xml](pom.xml)
  > *Note*: If you are using Eclipse, be sure to refresh your project (select your project→ right click→ Maven→ Update Project)
2. [Config file](src/main/resources/config.properties)  
3. [Java Model class](src/main/java/com/app/person/Model.java)    
4. [Java ServiceClient class](src/main/java/com/app/person/ServiceClient.java) 
5. [Faces Controller](src/main/java/com/app/person/Controller.java) 
6. [Facelets template](src/main/webapp/WEB-INF/templates) 
7. [Faces View](src/main/webapp/index.xhtml)   
8. [Main java class](src/main/java/com/app/App.java)  

Thats it, now run your App class, in few seconds your browser will open and you should see something like this:
![Screenshot](screenshots/output.png).
