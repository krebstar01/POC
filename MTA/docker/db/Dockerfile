FROM mariadb:latest

ARG root_pass
ENV MYSQL_ROOT_PASSWORD=$root_pass

RUN apt-get update && \
    apt-get install telnet -y && \
    apt-get install vim -y && \
    apt-get install -y net-tools

# apt-get install postfix postfix-mysql dovecot-core dovecot-imapd dovecot-lmtpd dovecot-mysql


ADD SETUP/db_setup.sh /tmp/db_setup.sh
ADD SETUP/db_setup.sql /tmp/db_setup.sql

RUN chmod -R 777 /tmp/db_setup.sh
RUN chmod -R 777 /tmp/db_setup.sql

WORKDIR /tmp


