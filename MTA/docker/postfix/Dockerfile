FROM tozd/postfix:latest
# FROM java:latest
RUN apt-get update && \
    apt-get update --fix-missing && \
    apt-get install telnet -y && \
    apt-get install vim -y && \
    apt-get install -y net-tools && \
    apt-get -y --force-yes install postfix-mysql

ADD SETUP/mysql-virtual-mailbox-domains.cf /etc/postfix/mysql-virtual-mailbox-domains.cf
ADD SETUP/mysql-virtual-alias-maps.cf /etc/postfix/mysql-virtual-alias-maps.cf

RUN chmod -R 777 /etc/postfix/mysql-virtual-mailbox-domains.cf
RUN chmod -R 777 /etc/postfix/mysql-virtual-alias-maps.cf

RUN postconf -e virtual_mailbox_maps=mysql:/etc/postfix/mysql-virtual-mailbox-domains.cf
RUN postconf -e virtual_alias_maps=mysql:/etc/postfix/mysql-virtual-alias-maps.cf

WORKDIR /tmp
