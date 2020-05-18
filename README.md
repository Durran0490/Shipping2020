# bookshelf-template

## What

This is a template to start your own Java EE project. It includes minimal configuration and essential functionality:
* 3 user roles: USER, MANAGER and ADMIN
* login page
* registration page (new users go to USER role)
* logout action
* basic Facelet templating
* 3 users inserted via data.sql (src\main\resources\META-INF\data.sql)
  * user
  * manager
  * admin
  * all with the same password 123456

## How

1. Download this code as .zip file
2. Unzip
3. Rename the folder
4. Change groupId, artifactId and name in the pom.xml if needed
5. Change context-root in /WEB-INF/jboss-web.xml if needed
6. Open the project in IDE.
7. Do everything you want.
