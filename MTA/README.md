# MTA POC - Mail Transfer Client interacting with a Springboot Microservice


# Getting the service up and running
* setup the db (see below)

* run the service (at the project root...)
```
java -jar target/sample-poc-service.jar
OR
mvn spring-boot:run
```

* Interact with Services via Swagger Documentation
```
localhost:8081/api/swagger-ui.html
```

# General Note about this POC
This example is essentially a simple Springboot microservice connected to a mysql database.  
The database is also connected and configured to a Postfix mail forwarding service.  The database is is structured based on the Postfix example.
If you want to change the db table names (or structure) Please see the PostFix documentation, and the configuration files and steps.

Please note that the db model structure is very simple, as are the example REST services offered.

To work with this example, either copy the sql script to your local db, and run the service off localhost, or 
deploy this service inside a Docker container, and change the url in the application.yml file from:
```
url: jdbc:mysql://localhost:3306/servermail
```

to: 
(where "mta_db_container" is the name -- and docker internal network host name -- of the db Docker container )
```
url: jdbc:mysql://mta_db_container:3306/servermail
```

#Architecture notes:
This is only a POC! 
It is sensible and suggested you structure the containers, db and overall setup differently!  This POC written with simplicity in mind.
For instance, the db  should not be in a container, as the data is only persistent as long as the container stays alive!
Also, please take great care when test emailing with any of your virtual domains.  You don" want to end up getting your domain and emails black-listed "in the real world".


## Quick  Note re: example Db Model Objects...

* The class VirtualDomain (db table virtual_domains) houses any existing virtual domains: "example.com"
* THe class VirtualAlias (db table virtual_aliases) houses the mappings between the "real world" users (source) and the virtual email (destination)
* The class VirtualUser (db table virtual_users) houses virtual users email and password



# Deployment


## Temp step (do this once! You only need to create the internal network on time.)
```
docker network create postfixNetworkInner --internal
```

## STEP 1
```
cd /Users/justin/projects/aps-project/MTA/docker/db
-- # --net=host

export MYSQL_ROOT_PASS=root;
docker build --build-arg root_pass=${MYSQL_ROOT_PASS} -t poc/mta_db_image .;
docker run -p 3307:3306 --net=postfixNetworkInner --name mta_db_container -d poc/mta_db_image;
docker exec -i -t mta_db_container /tmp/db_setup.sh;

# docker exec -i -t mta_db_container /bin/bash
```

## STEP 2
```
cd /Users/justin/projects/aps-project/MTA/docker/postfix

docker build -t poc/mta_postfix_image .
docker run --net=postfixNetworkInner --name mta_postfix_contaisner -d poc/mta_postfix_image
# docker exec -i -t mta_postfix_container /bin/bash
```


## checking postfix image connectivity to db
```
#testing connection to db!
postmap -q example.com mysql:/etc/postfix/mysql-virtual-mailbox-domains.cf
postmap -q alias@example.com mysql:/etc/postfix/mysql-virtual-alias-maps.cf

```


##STEP 3 the micro service
```
docker exec -d -i -t mta_service /data/docker-service.sh
```


# Docker tips
## cleanup
```
#postfix image
docker kill mta_postfix_container
docker rm mta_postfix_container
docker rmi poc/mta_postfix_image

#db image
docker kill mta_db_container;
docker rm mta_db_container;
docker rmi poc/mta_db_image;
```

## Stop and restart Docker as a service on a MAC
```
# docker kill docker service on MAC
killall com.docker.osx.hyperkit.linux

# start docker service on a MAC
open -a Docker
```

# Documentation: 
## postfix docker image
https://hub.docker.com/r/tozd/postfix/

### Configuring postfix with a db
https://workaround.org/ispmail/wheezy/connecting-postfix-to-the-database

### For additional tips on how to configure postfix:
https://www.digitalocean.com/community/tutorials/how-to-install-and-configure-postfix-as-a-send-only-smtp-server-on-ubuntu-16-04

### And these detailed StackExchange articles should help with trouble shooting...
* https://serverfault.com/questions/661324/postfix-in-docker-container-not-forwarding-virtual-aliases
* https://serverfault.com/questions/631941/send-mail-from-docker-container-with-hosts-postfix

### Suggeested Steps for additional development
You could set up a DNS server via Docker
http://www.damagehead.com/blog/2015/04/28/deploying-a-dns-server-using-docker/