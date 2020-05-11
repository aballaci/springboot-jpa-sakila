FROM mysql:5.7

MAINTAINER Armand Ballaci

COPY data/sakila-schema.sql /docker-entrypoint-initdb.d/00_sakila-schema.sql
COPY data/sakila-data.sql /docker-entrypoint-initdb.d/01_sakila-data.sql