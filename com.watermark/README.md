# Run in Eclipse or Intellij
##  in "Program Arguments:"
######################################################################################################

import Maven project to the IDE of your choice

run the command "mvn clean install" (either in your IDE or on command line)

Run as Java Application
(choose WatermarkStatusApplication as your main method)
enter below in Project Properties > Run Configurations > Arguments > enter below:
server /YOUR_CHOSEN_IDE_WORKSPACE_DIRECTORY/com.watermark/src/main/resources/watermark-admin.yml

/home/justin/DEV/GIT_REPO/EXAMPLES/com.watermark/target/watermark.jar

# Run the shaded jar via command line
######################################################################################################

You should have Java 1.7 or higher correctly installed and configured on your PC!
unzip the rar "watermark-service-test.rar"

in the command line tool of choice, "cd" to the directory watermark-service-test
run the command:

java -jar watermark-service.jar server watermark-admin.yml


## Data
you will notice in the same folder where the service is running that a folder containing a file "watermark.mv.db" 
has been created.  This is the "in file" data created by H2db.

open up the page: http://localhost:8004/swagger

here you have access to all needed webservices, via the swagger documentation UI.

after opening the page,
 - click on the "default" link (Show/Hide List Operations Expand Operations)
 inspect the existing services (see below)
 
###############calling the service endpoints#############################################
Base Context
http://localhost:8004/service/status
For detailed information in all service endpoints, there respective paramters and general use, please see the swagger documentation page. 

POST /status/requestTicket
to request a ticket for a book or journal, the user can post with the following parameters
content (book OR journal)
title (required)	
author (required)	
topic (optional -- book only -- from one of the three topics: business, science, media)	

The user will recieve the following response after a successful post:

{
  "Ticket": "b6411eec-340d-4199-bbd3-46ea497c908e",
  "Message": "You have requested a Book to be processed \n please check back later with the following ticket number \n /service/status/retrieveDocumentByTicket/b6411eec-340d-4199-bbd3-46ea497c908e"
}
 
GET /status/retrieveAllDocuments
if the user polls the retrieveAllDocuments endpoint, the newly created document will be listed with the Status of "SUBMITTED" 

Users can then poll the retrieveDocumentByTicketAsync to get the status of there document
There  are three statuses a document can have:

SUBMITTED
UNDERWAY
FINISHED

GET /status/retrieveDocumentByTicketAsync/{ticketId}/{timeout}
the endpoint takes two url parameters:
ticketId - an existing ticket identifier
timeout - a value set for testing to "force / simulate" a time out.

upon first poll, the status is listed as: UNDERWAY

Upon second and final polling the state will be: FINISHED

Additional endpoint for testing and reviewing data:
GET /status/retrieveAllWatermarks

OR

in the rest tool/app of your choice, i.e. Google's Postman,  enter the following url: 
http://localhost:8004/service/status/retrieveAllDocuments
(you should recieve and empy object, [])

**Please note  that the port 8004 should be open on your PC.
If it is already used, simply open the file "watermark-admin.yml" 
and change the value "port:" to a valid and open port


###########################################notes about the service############################

The endpoint retrieveDocumentByTicketAsync invokes a method that takes a document through a couple of state changes.
"Sleep" is invoked a couple of times to simulate a lengthy processing time.
the total time for each Sleep call is 10 seconds (10000 milliseconds).
If you set the parameter "timeout" to less then 10 seconds, you will simulate a timout, and will recieve a erroc code: 408.

###########################################about the tech stack############################
Dropwizard (microservice)
Hibernate 5
H2DB (in memory database, file mode)
Swagger (online documentation)

Testing:
standard Junit for dao methods
Mockito and Junit used for service tests
Grizzly Container needed to handle service async calls.

###########################################additional notes#################################


