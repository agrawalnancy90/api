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

## Query Examples

There are 7 GET end-points in this service (Assuming service is running on localhost:8080):

`localhost:8080/users`
* Returns a list of all users on the system, as defined in the user file (configurable in Application.properties file).

Example Response:
`[
{“name”: “root”, “uid”: 0, “gid”: 0, “comment”: “root”, “home”: “/root”, “shell”: “/bin/bash”},
{“name”: “dwoodlins”, “uid”: 1001, “gid”: 1001, “comment”: “”, “home”: “/home/dwoodlins”, “shell”: “/bin/false”}
]`

---

`localhost:8080/users/query[?name=<nq>][&uid=<uq>][&gid=<gq>][&comment=<cq>][&home=<hq>][&shell=<sq>]`
* Returns a list of users matching all of the specified query fields. The bracket notation indicates that any of the following query parameters may be supplied:
   - name
   - uid
   - gid
   - comment
   - home
   - shell
* Only exact matches are supported.

Example Query: `GET /users/query?shell=%2Fbin%2Ffalse`

Example Response:
`[
{“name”: “dwoodlins”, “uid”: 1001, “gid”: 1001, “comment”: “”, “home”: “/home/dwoodlins”, “shell”: “/bin/false”}
]`

---
  
`localhost:8080/users/{uid}`
* Returns a single user with <uid>. Return 404 if <uid> is not found.

Example Response:
`[
{“name”: “dwoodlins”, “uid”: 1001, “gid”: 1001, “comment”: “”, “home”: “/home/dwoodlins”, “shell”: “/bin/false”}
]`

---

`localhost:8080/users/{uid}/groups`
* Returns all the groups for a given user.

Example Response:
`[
{“name”: “docker”, “gid”: 1002, “members”: [“dwoodlins”]}
]`

---

`localhost:8080/groups`
* Returns a list of all groups on the system in the group file.

Example Response:
`[
{“name”: “_analyticsusers”, “gid”: 250, “members”:
[“_analyticsd’,”_networkd”,”_timed”]},
{“name”: “docker”, “gid”: 1002, “members”: []}
]`

---

`localhost:8080/groups/query[?name=<nq>][&gid=<gq>][&member=<mq1>[&member=<mq2>][&. ..]]`
* Returns a list of groups matching all of the specified query fields. The bracket notation indicates that any of the following query parameters may be supplied:
   - name
   - gid
   - member (repeated)
* Any group containing all the specified members are returned, i.e. when query members are a subset of group members.

Example Query:
`localhost:8080/groups/query?member=_analyticsd&member=_networkd`

Example Response:
`[
{“name”: “_analyticsusers”, “gid”: 250, “members”: [“_analyticsd’,”_networkd”,”_timed”]}
]`

---

`localhost:8080/groups/<gid>`
* Returns a single group with <gid>. Return 404 if <gid> is not found.

Example Response:
`{“name”: “docker”, “gid”: 1002, “members”: [“dwoodlins”]}`

---


## Built With
* Java 8
* Spring Boot (v2.1.2)
* Maven
* JUnit Framework
* Eclipse IDE

## Contact
Created by Nancy Agrawal (agrawalnancy90@gmail.com) - feel free to contact me!
