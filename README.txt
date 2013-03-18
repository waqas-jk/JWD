Java Web Application - Assignment
==================================
This is an open source program to demonstrate the integration of Spring Framework, Struts, SPring Securrity and Hibernate
Other technologies are also used like JQQuery, Struts Tiles, JUnit etc.

This application provides a simple user login functionality. Administrators can also user management operations (CRUD). 


Setup Environment:
==================

 1. Download and install JDK 6+ (make sure your JAVA_HOME environment variable points to the JDK's root directory, not a JRE).
 2. Download and install MySQL 5.5+ (or use a different database see here).
 3. Download and install Maven 3.0+. Also added the Maven's bin directory path to windows environment variable 'Path'.
 4. Create a empty database with name "JWD" in mysql (Or you can change it in pom.xml file by modifying prroperty db.name).
 5. Download and install Eclipse 3.0+ or SpringSource Tool Suite (STS).
 6. Download and install TOmcat 6+ (Optional). It is required to deploy application using eclipse. 
     a. Open Eclipse IDE
     b. Open Server view using Window>Show View>Server.
     c. Right clickand select New > Server.
     d. Follow instructions for add tomcat server into eclipse


How to run application:
=======================

 1. Open command prompt in wondows.
 2. Go to the Directory where project is located (for example C:\JWD>, if project is located on root of c drive).
 3. Execute command mvn jetty:run on commpand prompt. (Note: Make sure you have internet connection.)
 4. Now all dependies will start downloading. Relax and take a cup of tea.It will take few minutes.
 5.  a. After all the dependencies are downloaded jetty server will deploy application on server. 
     b. If application is deployed successfully, you will see a message "[INFO] Started Jetty Server" at last line.
 6. Now open internet browser of your choice and type "http://localhost:8080/" .
 7. Login screen will be shown. 

Note: Here are login names and passwords for adminstrator and normal users :
 
Administorator :
 User name : admin
 Password : admin

Normal User:
 user name : user
 Password : user



How to build project:
======================
These settings are only for Eclipse, SpringSource Tool Suite (STS) or any other eclipse distribution.

1. Open Eclipse IDE.
2. Import project using File > Import command.
3. Build project
4. Right Click the project and Choose Run > RUn as Server.
5. Now select tomcat server (created as above) and deploy application on server. 
6. A Browser window will be opened with application's login page.



I hope these instructions will work for you. If you face problems you can contact me at waqas.jk@gmail.com


Thanks.
