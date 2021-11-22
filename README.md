# Create REST API with JAVA Spring Boot Practice
## Information
### Pre-requisites
- IntelliJ or any code editor https://www.jetbrains.com/idea/download/ 
- Java 8 https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
- Derby databse https://archive.apache.org/dist/db/derby/db-derby-10.15.1.3/db-derby-10.15.1.3-bin.zip
- Gradle https://gradle.org/install/ 
- Lombok https://projectlombok.org/download
- (MAC) Docker https://hub.docker.com/editions/community/docker-ce-desktop-mac
- (Windows) Docker https://docs.docker.com/desktop/windows/install/
- Postman https://www.postman.com/downloads/ 

### Code Commit  #test
Create your own new branch from the **main** branch and push your commits to the same local branch.

### Database
- Database: 
	Can use Derby database and follow the below steps,
		- update the table creation script in the Employee_Create.sql
		- update the seed script in the Employee_Seed.sql

### Table
- table name: employee
        - id
              - generated value
              - uuid
                      - example1: b5ce4b08-34ef-47aa-ad78-d3b5d59e62e2
                      - example2: a5d732a7-030a-45e4-b136-f9e11f0a3070
        - name
              - varchar(20)
                      - example1: Steve Aden
        - email
              - varchar(40)
                      - example1: steaveaden@accn.com
        - addressLine1
              - varchar(30)
                      - example1: Downtown San Jose
        - city
              - varchar(20)
                      - example1: San Jose
        - state 
              - varchar(20)
                      - example1: California
        - createDate
              - generated value
	      - timestamp
                    - example1 : 2021-02-11T15:45:01.123
                    
### Mocked Data in table
![image](https://user-images.githubusercontent.com/94523251/142173710-ee7f8618-936f-4e89-add8-d5b90cf6adf4.png)

## Tasks

### 1. Create REST API for showing employees based on global search
### input 1
```
Input 1: name i.e. "Sam" 
response  when employee(s) found (***the type must be the same as example***)

[
    {
        "id": "cc5a8d9f-21dd-4ce0-9e7f-18090b3d3268",
        "name": "Sams Dan",
        "email": "samsdan@accn.com",
        "address line 1": "St John's Anglican Church",
        "city" : "Canberra",
        "state" : "New South Wales",
        "create date": "2021-01-03T00:00:00.131"
    },
   {
        "id": "56619ca9-227b-430a-9759-d33aee0f7f2a",
         "name": "Samuel Mach",
        "email": "samuelmach@accn.com",
        "address line 1": "180 E Weber Ave Stockton",
        "city" : "Stockton",
        "state" : "California",
        "create date": "2021-01-02T00:00:00.127"
    },
    {
        "id": "b5ce4b08-34ef-47aa-ad78-d3b5d59e62e2",
        "name": "Willian Sam",
        "email": "williansam@accn.com",
        "address line 1": "24 Moorabool St",
        "city" : "Geelong",
        "state" : "Victoria",
        "create date": "2021-09-02T00:00:00.132"
    }
 ]

note: HTTP status 200
note2: create date always show 3 digits of milliseconds
```
response when employee not found
```
[]

note: HTTP status 200
```
### input 2
```
Input 2: email i.e. "samsdan@accn.com" 
response  when employee(s) found (***the type must be the same as example***)

[
    {
        "id": "cc5a8d9f-21dd-4ce0-9e7f-18090b3d3268",
        "name": "Sams Dan",
        "email": "samsdan@accn.com",
        "address line 1": "St John's Anglican Church",
        "city" : "Canberra",
        "state" : "New South Wales",
        "create date": "2021-01-03T00:00:00.131"
    }
 ]
 
note: HTTP status 200
note2: create date always show 3 digits of milliseconds
```
response when employee not found
```
[]

note: HTTP status 200
```
 
### 2. Create REST API to insert new record. Address line 1,city,state is optional, but if entered, mandatory to fill all 3 of them
input (***the type must be the same as example***)
```
{
    "name": "Aden George,
    "email": "adengeorge@accn.com",
    "address line 1": "Downtown San Jose",
    "city": "San Jose",
    "state": "California"
}
```
response when create successful (***the type must be the same as example***)

```
{
    "id": "ed2ae6f0-deba-4007-8e02-f46f4a7e388f",
    "name": "Aden George,
    "email": "adengeorge@accn.com",
    "address line 1": "Downtown San Jose",
    "city": "San Jose",
    "state": "California",
    "createdAt": "2021-02-10T18:33:37.394"
}

note: HTTP status 200
note2: createdAt always show 3 digits of milliseconds
```

