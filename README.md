# Vessel register

## What

This is a project based on a Bookshelf2020 template. It includes :
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
* search for specific vessels by it's mane and type
* connects to database with available vessels
* user in role 'user' can:
  * access to vessel info
  * rent vessel 
  * search for specific vessel
  * see which vessel is already taken
  * access to table with his bookings - active and taken
* user in role 'manager' can:
  * access to vessel, flag, shipyard, and ship types lists
  * lease vessel to first user that filed a rental application
  * retrieve vessel booking from user
  * edit vessel data and import vessel image
  * edit flags, shipyards and vessel types
*user in role 'admin' can:
  *create new vessel entity  
