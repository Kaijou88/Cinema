# Cinema

![Header Image](src/images/cinema_img.jpg)

# Context
[Project's purpose](#purpose)

[Project's structure](#structure)

[For developer](#developer)

[Author](#author)

# <a name="purpose"></a>Project's purpose
The cinema project is a simple version of a real one. 
This cinema project has the following functionality:
- Registration of new user with USER role only and login;
- User with ADMIN role can add new cinema halls, movies, movie sessions;
- User with USER role can add movie sessions into the bucket and place an order;
- User can see own order history;
- User with ADMIN role can see users' information and see their order history.

# <a name="structure"></a>Project's structure
- Java 11
- Maven
- Travis
- hibernate 5.4.15
- springframework 5.2.6
- springframework.security 5.3.3
- javax.servlet 3.1.0
- hibernate.validator 6.1.5
- mysql-connector-java 8.0.20
- log4j 2.13.3

# <a name="developer"></a>For developer
Open the project in your IDE as a maven project.

Add Java SDK 11 in project structure.

Configure Tomcat:
- Add artifact;
- Add Java SDK 11.

To run the project correctly, you need to do the following steps first:
- change the path to the *.log file in src\main\resources\log4j.properties;
- configure src\main\resources\db.properties with the correct username and password;
- create a scheme with the name "cinema" in MySQL.

Run the project.
The following users will be added to the DB automatically:
- Admin with login - "admin@gmail.com" and password - "1234". Admin has a role - ADMIN;
- Customer with login "bob@gmail.com" and password - "1234". User has a role - USER;

For testing the functionality of the project can be used Postman or other similar programs. In the Header must be added Authorization field with a value that contains "Basic login:password", where login:password encoded to Base64 format.

# <a name="author"></a>Author
[Maryna Franchuk](https://github.com/Kaijou88)

