# Create REST API with JAVA Spring Boot Practice
## Information
### Pre-requisites
- IntelliJ or any code editor https://www.jetbrains.com/idea/download/ 
- Java 8 https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
- Gradle https://gradle.org/install/ 
- Lombok https://projectlombok.org/download
- (MAC) Docker https://hub.docker.com/editions/community/docker-ce-desktop-mac
- (Windows) Docker https://docs.docker.com/desktop/windows/install/
- Postman https://www.postman.com/downloads/ 

### Starting
Create Database using docker-compose that we provided

### Database
- Database: MSSQL
- Account: SA
- Password: yourStrong(!)Password

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
              - varchar(30)
                    - example1 : 2021-01-02T00:00:00.123
                    
### Mocked Data in table
![image](https://user-images.githubusercontent.com/94523251/142173710-ee7f8618-936f-4e89-add8-d5b90cf6adf4.png)

