# CodingChallenge
## I used  Lombok, Spring Web, Spring Data, JPA and Spring Security in this project.

## Requirements:
## Initialise an embed database for saving users, such a: H2, Derby or HSQLDB - the dependencies for these can be found in Spring Initializr
### I used h2 database, to access this database run the project, open this link: http://localhost:8080/h2-ui/ and enter these information:
### JDBC URL: jdbc:h2:mem:testdb 
### User Name: cenker
### Surname: akan


## Create an endpoint for registering a new user with a email address and password - pay attention to how you save user passwords
### I stored the psaswords by hashing using the hashcode function of Java, here is the http request info:
### SignUp
### POST http://localhost:8080/signUp 
### {
###    "email":"cenkerakan@hotmail.com",
###    "password":"pass"
### }


## Create an endpoint which requests an email and password and confirms they match before returning a success response
### I created a login endpoint for this. Here is the request information:
### Login
### POST http://localhost:8080/login
### {
###    "email":"cenkerakan@hotmail.com",
###    "password":"pass"
### }


## Create an endpoint which returns the number of each occurrence of domain names in the database - i.e. (“gmail.com”, “outlook.com”, “hotmail.com”) pay attention to the time complexity of your implementation
### I used a hashmap to count the different domains. Here is the request information of the endpoint:
### Count Domains
### GET http://localhost:8080/domainCounts

## Endpoints should return ResponseEntity with appropriate HTTP semantics and body content
### It returns

## Optional: Use at least one of each different endpoint mappings in the correct context: GET, POST, PATCH, PUT and DELETE. 
### I used GET, POST, PATCH, PUT and DELETE. Here are the request information of other requests:
### Get All Users
### GET http://localhost:8080/getAllUsers


### Get User By Id
### GET http://localhost:8080/getUserById/{id}
### insert your target id on {id}


### Update User By Id
### PUT http://localhost:8080/updateUserById/{id}
### insert your target id on {id}


### Delete User By Id
### Delete http://localhost:8080/deleteUserById/{id}
### insert your target id on {id}


## Note: Since it is not specified I did not implement jwt authentication, aouthorization and dockerization.
  
