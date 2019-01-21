#!/bin/sh
java -jar /root/RDAddrBook-0.0.1-SNAPSHOT.jar spring.datasource.url=jdbc:postgresql://{{db_instance_private_ip}}:5432/rdaddrbookdb -e spring.datasource.username={{db_user}} -e spring.datasource.password={{db_password}}
