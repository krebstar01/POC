# Run in Eclipse or Intellij
#  in "Program Arguments:"
######################################################################################################

import Maven project to the IDE of your choice (note: I'Ve only tried with the above two IDEs: 
preferably "Mars" build for Eclipse or Intellij 2016.1)

run the command "mvn clean install" (either in your IDE or on command line)

Run as Java Application
(choose WatermarkStatusApplication as your main method)
enter below in Project Properties > Run Configurations > Arguments > enter below:
server /YOUR_CHOSEN_IDE_WORKSPACE_DIRECTORY/com.watermark/src/main/resources/watermark-admin.yml

/home/justin/DEV/GIT_REPO/EXAMPLES/com.watermark/target/watermark.jar
######################################################################################################
# Run via command line
######################################################################################################
You should have Java 1.7 or higher correctly installed and configured on your PC!
unzip the rar "watermark-service-test.rar"

in the command line tool of choice, "cd" to the directory watermark-service-test
run the command:
java -jar watermark-service.jar server watermark-admin.yml

in the rest tool/app of your choice, i.e. Google's Postman,  enter the following url: 
http://localhost:8004/service/status/retrieveAllDocuments
(you should recieve and empy object, [])

**Please note  that the port 8004 should be open on your PC.
If it is already used, simply open the file "watermark-admin.yml" 
and change the value "port:" to a valid and open port

###############calling the service endpoints#############################################
Base Context
http://localhost:8004/service/status


POST: http://localhost:8004/service/status/requestTicket
GET http://localhost:8004/service/status/retrieveDocumentByTicket/{your-ticket-here}

//additional services purely for testing
GET http://localhost:8004/service/status/retrieveAllDocuments
GET http://localhost:8004/service/status/retrieveAllWatermarks


###########################################notes about the service############################

It should be noted that the "processing" of a document has been mocked.
Upon receiving a ticket, a user of the service can retrieve the status of their 
document's processing.

Upon call #1: the user is notified of a status "SUBMITTED"
Upon call #2: the user is notified of a status "UNDERWAY"
Upon call #3: the user is notified of a status "FINISHED", and 
the watermark has been generated

The database is in memory only (no file based) so data 
will not be persisted once the app has been shut down!

###########################################about the tech stack############################
Dropwizard (microservice)
Hibernate 5
HSQLDB (in memory database

Testing:
standard Junit for dao methods
Mockito and Junit used for service tests

###########################################additional notes###################################

Please note that the following exception takes place at start up, however is harmless.
It is caused by a race condition between the microservice start up and hsql schema creation.
Basically, the service starts and Hibernate tries to create an initial connection before the schema is created.
The service is and the db are unaffected, as the service starts normally.
##########################################################################################
WARN  [2016-08-25 12:48:28,324] org.hibernate.tool.schema.internal.ExceptionHandlerLoggedImpl: GenerationTarget encountered exception accepting command : Error executing DDL via JDBC Statement
! org.hsqldb.HsqlException: user lacks privilege or object not found: PUBLIC.WATERMARK
##########################################################################################