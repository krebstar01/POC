##Overview
This is an example RESTful microservice built with Springboot.  It is meant to show off what Springboot can do.

###Tech Stack and contained Frameworks
The microservice persists data with a the help of H2.  It uses an infile database in order to offer the convenience of
 persistent test data after the service has been shut down. 
 
 * (out of the box) yml configuration
 * CRUD data repositories to persists data
 * A Simple "Customer RESTful Service" with
    * POST (create)
    * GET (get all)
    * GET (get by id)
    * PUT (update) 
    * DELETE (remove)                
 * The object model is rudimentary with a Customer and a child object (has many) Order
 * Swagger Service Documentation, viewed at:
    * http://localhost:8081/api/swagger-ui.html
 * Global Exception Handler
 * Custom Configuration Object (used to turn debugging messages on and off)
 * Spring Security
    * http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
        *     Note: I used the Spring MVC Security example in the above link, but adapated it to spring boot
              It' worth noting how much boiler plate code was removed!
        *     Note: An alternative, additional password encryption means (slow and strong):        
              https://www.mkyong.com/spring-security/spring-security-password-hashing-example/
        * For REST controller security, we can do the following:
        <a href="https://www.future-processing.pl/blog/exploring-spring-boot-and-spring-security-custom-token-based-authentication-of-rest-services-with-spring-security-and-pinch-of-spring-java-configuration-and-spring-integration-testing/">
        REST Controller Security</A>
  * @Transactional support for transactions that contain multiple invocations -- therein the method call should eiether fail or succeed
    see UserCredentialsManager for a simple example!      

##How to run the service
java -jar target/sample-poc-service.jar

OR

mvn spring-boot:run

##Simple Configuration
Many externalized config values can be controlled during development or when running the application.
The yaml file at:<br /> <br /> 
/springbootservice/src/main/resources/application.yml
<br /><br />  can be changed to set default properties

However when running the app, placing a copy of the yaml file in the same directory and changing needed values is 
enough.  <br /> (This can easily be shown by changing the server port value.)

Referencing a yaml on another directory via the command line is as easy as: <br /> 
 --spring.config.name="file:/path/to/application.yaml"

##Logging Configuration
logging is configured in the applicastion.yml file.<br /> 
default level is INFO<br /> 
Please note the element ROOT is "root" logging entity where a global logging level can be set<br /> 
Logging Levels can (of course) be set for individual packages as needed

Logging example can be seen in:<br />
/Users/justin/projects/springbootservice/src/main/java/com/sample/service/controller/handler/GlobalExceptionHandler.java

## Custom Configuration example
Custom Configuration can be created, per the example: <br />
/Users/justin/projects/springbootservice/src/main/java/com/sample/configuration/GlobalProperties.java

##Profile Configuration
Configurations can also be managed via profiles (more on this later)


## Project Setup
* add to your GIT ignore 
1) your git directory:
  vim .git/info/exclude
2) add the following lines: <br /> 
`.idea` <br /> 
`*.iml` <br /> 
`*.ipr` <br /> 
`*.iws` <br /> 
`/logs/*` <br /> 
`/data/*` <br /> 
`/target/*` <br /> 
  

##TODO Backlog

* Async service (see async annotation)
* unified logging solutions across multiple restful services
* webservice unit test examples - Mockito
* Unit test annotation marker interfaces
* yml profiles and priorities        
    * profiles: http://www.baeldung.com/spring-profiles
    * Logging strategy:
        https://springframework.guru/using-yaml-in-spring-boot-to-configure-logback/
* Preload / post load events (Spring)
* Quartz Timer example
* non standard DAO method (impl other than interface magic)
* example springbok configure list in yaml!
    * https://www.mkyong.com/spring-boot/spring-boot-configurationproperties-example/