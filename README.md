# COVID19 DATA PROVIDER

## About
 
Simple batch to daily download updated covid 19 data from https://covid.ourworldindata.org and persist it into MySQL Database;

## Technologies used

* **Java 8**
* **Spring Boot 2.3.0**
* **Spring Batch**
* **Spring Data**
* **Maven**
* **MapStruct**

## Compile and Package

The application also was developed to run with an jar. In order to generate this jar, you should run:

```bash
mvn package
```

It will clean, compile and generate a `jar` at target directory, e.g. covid19-data-provider-1.0.0-SNAPSHOT.jar

## Execution

### Database

You need to have **MySQL 8.0 or above** installed on your machine to run the application. After installed, create a database named `covid19`. 

If you do not want to install MySQL on your localhost you can build a docker image with the follow Dockerfile. The `schema.sql` file can be found at `src/main/resource` path.

```bash
 # Base image
 FROM mysql

 # Add environment variables
 ENV MYSQL_RANDOM_ROOT_PASSWORD true
 ENV MYSQL_DATABASE covid19
 ENV MYSQL_USER covid19-monitor
 ENV MYSQL_PASSWORD covid

 # Add the content of the uspto-db-schema.sql file to our image
 # All scripts in docker-entrypoint-initdb.d/ are automatically
 # executed during container startup
 ADD ./schema.sql /docker-entrypoint-initdb.d/
```

### Application

Run the job with the command:
```bash
java -jar covid19-data-provider-1.0.0-SNAPSHOT.jar
```

