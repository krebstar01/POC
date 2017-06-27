#!/bin/bash
set -x

while ! mysqladmin ping -h localhost --silent; do
    sleep 1;
    echo "waiting for mysql to awake...";
done

echo "waiting just a few more seconds before we set up the schema and insert tables....";

sleep 10;

(mysql -s -uroot -p${MYSQL_ROOT_PASSWORD} < "db_setup.sql" > /dev/null 2>/dev/null || (echo failure >&2; false)) &&
echo 'DONE!'