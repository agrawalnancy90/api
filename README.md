# Passwd as a Service
A toy HTTP service that exposes the user and group information on a UNIX-like system that is usually locked away in the UNIX /etc/passwd and /etc/groups files.

## Table of contents
* [Software Requirements](#softwarerequirements)
* [Setup](#setup)
* [Built With](#builtwith)
* [Contact](#contact)

## Software Requirements
* Java 8 or higher
* Maven
* Eclipse IDE or similar

## Setup

* Clone or download the code as zip. 
* Unzip code on your local machine

Running application using Eclipse IDE:
* Import as existing maven project
* **Configure path to user and group files in application.properties file (under src/main/resources). Default path in UNIX-like systems is /etc/passwd (for user file) and /etc/group (for group file). Override these two paths for any other system.**
* Run Application.java (Application should now be running locally on port 8080) 

## Code Examples

There are 7 GET end-points in this service (Assuming service is running on localhost:8080):
* localhost:8080/users
* - Returns a list of all users on the system, as defined in the user file (configurable in Application.properties file).

* localhost:8080/users/{uid}
* - Returns a list of users matching all of the specified query fields. The bracket notation indicates that any of the following query parameters may be supplied:
*   - name
*   - uid
*   - gid
*   - comment
*   - home
*   - shell
* Only exact matches are supported.

* localhost:8080/users/query[?name=<nq>][&uid=<uq>][&gid=<gq>][&comment=<cq>][&home=<hq>][&shell=<sq>]
* localhost:8080/users/{uid}/groups

## Built With
* Java 8
* Spring Boot (v2.1.2)
* Maven
* JUnit Framework
* Eclipse IDE

## Contact
Created by Nancy Agrawal (agrawalnancy90@gmail.com) - feel free to contact me!
