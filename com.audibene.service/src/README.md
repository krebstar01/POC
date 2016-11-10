How to run:
java -jar audibene-service.jar server audibene.yml


in a browser, open up the following url:
http://localhost:8004/service/swagger

Once opened, click on the link: "default" to view all services.  Here you can interigate and interact with the services.
Default values, examples are suggested, as well as assembled curl commands.

The Services are described below:

CREATE CUSTOMER
post /customer 

params:
customerName (formData, string)
audiologistName (formData, string)

CREATE APPOINTMENTS WITH A CUSTOMER
post /customer/appointment
params:
customerName (formData, string)
audiologistName (formData, string)
localDateTime (formData, string)   >> format example: "2016-11-08T00:18:15.294Z"

OVERVIEW ALL APPOINTMENTS
get /audiologist/appointments

OVERVIEW NEXTWEEK APPOINTMENTS
get /audiologist/appointmentsNextWeek

SEE CUSTOMER'S NEXT APPOINTMENT
get /customer/nextAppointment

CUSTOMER RATES LAST APPOINTMENT
put /customer/rateAppointment 
params:
customerName (query,string) >> Please choose an existing customer, >> Please a customer with appointments
rating (query,string) Please choose from the following 5 strings for rating purposes: "SUPER", "GREAT", "GOOD", "OK", "MEH"
"SUPER" being the best, "MEH" being the worst


Additional helper services....
get /customer/customers
get /audiologist/audiologists


Notes about the requirements:
JSON, RESTful based microservice (dropwizard)
Data is persisted and will servive a server restart (H2, File)*
*Please note you will see a folder called "data"  which will hold the files H2 needs to read/write to.  After running, the service, the file
"data/audibeneProdYml.mv.db" should be created.

No Authentication (check...)
No Userface, however I have provided a swagger documentation page.  This will allow for easy interigation of the service api

Deliverables:
Source Code: see enclosed zip :"Source.rar"
Should indeed work out of the box, assuming Java is installed on the machine where the service should run.


Solutions and Tradeoffs
For server shutdown persistence I chose H2.
I ran into a snag, initially as I first chose HSQLDB which did not work with modern version of Hibernate.

I was unable to deal with some minor issues, including correcting serializing the LocalDateTime value for the Appointments (GET)
to a human readable string

I created a customer serializer to handle this task, but for some reason it did not register an effect (debugging and stack overflow articles TBD)

In addition, I needed to make a wrapper class for the Appointment persistence entity.  There were some issues with serialization of members marked transient.
This was the easiest workaround.


Given time, the services have some rudeimentary validation checks.  I am returning a custom built up error response (which can be seen in the code)
however swagger only seems to return the following information when an error response (i.e. empty values) should be given.

Response Code 0
Response Headers
{
  "error": "no response from server"
}

I had also hoped to offer more test coverage, notably coverage of the services themselves, mocking the daos, etc in test the validations / custom error Responses.
Again, time was a factor.

An additional note: the instructions noted that the product manager was out of town.
That being said, I took note of the pronoun "MY" when used.  
To clarify, the user story "As an Audiologist I want to create my customers"
Here, the customers created "belong" to the Audiologist

However, for all other audiologist user stories, "My" was not used.  I took this to mean that the audiologist could, for example see all
appointments, regardless who created them



 












