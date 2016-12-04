The following application allows driver to update their location, and customers to search for nearest driver.

# Becuase of time constraint, the following still needs improvement - 
* Design - The design is not scalable as of now. It is taking 2.3s to search for nearest 10 drivers with 50,000 records on large Linu VM with database in another VM in same data center. 
* Tests - Tests are not added

# Technology Choices - 
* The application was built on Spring boot for elastic scalability and faster development.
* The applicaiton allows API usage through swagger UI.

# How to run the applicaiton - 
* THe application can be run from maven clean install. The jar contains tomcat container. java -jar -Djava.security.egd=file:/dev/./urandom -Djasypt.encryptor.password=null
* The configurations are setup as profiles in application-dev.properties. 
* The application connects to Oracle schema with scripts in /db folder

# How to view the APIs
* After running the application https://localhost:8092/swagger-ui.html gives the list of APIs.
