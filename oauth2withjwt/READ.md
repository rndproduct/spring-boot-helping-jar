# Spring Boot Security using OAuth2 with JWT

In this project, we will implement about securing applications with Spring Boot Security using OAuth2 with JWT.

### What is OAuth2?

It is an authorization framework that provides some standardized rules for authorization. It was first created in 2006 and the first version was OAuth.

The main goal of the OAuth2 framework is to provide a simple flow of authorization that can be implemented on the web application, mobile phones, desktop application, and even on the devices used in our living rooms.

**OAuth2 specify the four roles on server side**:

- Resource Owner
- Resource Server
- Authentication Server
- Client

You need to follow all mentioned steps, in order to build an application having **Spring Boot Security using OAuth2 with JWT**.

**Step 1:** Create a simple maven project from the [Spring Initializr](https://start.spring.io/).


**Step 2:** Add the following dependencies on your pom.xml file.

Here, we used
- Spring Boot **2.7.0** version
- JDK 11
- Maven
- MySQL
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-jwt</artifactId>
        <version>1.1.1.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.security.oauth</groupId>
        <artifactId>spring-security-oauth2</artifactId>
        <version>2.3.8.RELEASE</version>
    </dependency>
</dependencies>
```
**Step 3:** Run the SQL script `DB Script.sql` or insert DB records manually.

### Generate the JSON Web Token through POSTMAN
**Step 1:** Encode Base64 token and use it in login API by using access key and secret key from `application.properties` file.

The properties are below:
- `security.jwt.client-id=rBnJwtClient100Id`
- `security.jwt.client-secret=XbVkNzRNszA1OO0BDAM`

We can generate base64 code from `JavaScript` or online platform as well.
Reference website [base64encode](https://www.base64encode.org/).

Example: 
- input: `rBnJwtClient100Id:XbVkNzRNszA1OO0BDAM`
- Output: `ckJuSnd0Q2xpZW50MTAwSWQ6WGJWa056Uk5zekExT08wQkRBTQ==`

**Step 2:** Use **Postman** Client
- **Login**
```
curl --location --request POST 'localhost:8080/oauth/token' \
--header 'Authorization: Basic ckJuSnd0Q2xpZW50MTAwSWQ6WGJWa056Uk5zekExT08wQkRBTQ==' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'username=badrul' \
--data-urlencode 'password=1234' \
--data-urlencode 'grant_type=password'
```
![Login (Header)](https://user-images.githubusercontent.com/15130238/182061345-3fc6ea36-8393-4806-82d9-f186026f6db4.png)
![Login](https://user-images.githubusercontent.com/15130238/182061127-da3642f9-b9ea-42eb-a77c-9da171aa6410.png)

- **Refresh Token**
```
curl --location --request POST 'localhost:8080/oauth/token' \
--header 'Authorization: Basic ckJuSnd0Q2xpZW50MTAwSWQ6WGJWa056Uk5zekExT08wQkRBTQ==' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=refresh_token' \
--data-urlencode 'refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiSldUUmVzb3VyY2VJZHNCcVZNMTAwMSJdLCJ1c2VyX25hbWUiOiJiYWRydWwiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiYXRpIjoiYTRjZmVkYmMtNGZkYi00YzA2LTg0ZDktZWY1MGYzODJmZjJlIiwiZXhwIjoxNjU5NTgwMzA5LCJhdXRob3JpdGllcyI6WyJBRE1JTiJdLCJqdGkiOiI0MTY0MzE5OS0zZTgzLTQ0ZWUtYWE4Yy1lYmQxYmMxY2ExNWEiLCJjbGllbnRfaWQiOiJyQm5Kd3RDbGllbnQxMDBJZCJ9.Wp7k06uoc5dCtG4QeIRuMvgSGUVEcet31R95Eok8XNk'
```
![Refresh Token (header)](https://user-images.githubusercontent.com/15130238/182061593-4d991e50-c041-4835-8c38-2c34f24e6d1d.png)
![Refresh Token](https://user-images.githubusercontent.com/15130238/182061661-15af2614-ddb9-49b5-a18a-25b41549b1d7.png)

- **Access API endpoint through JWT key**
![Api access](https://user-images.githubusercontent.com/15130238/182061793-415b9a35-1df1-43b3-a07a-4afc4dc0353d.png)

## References
- https://www.pixeltrice.com/spring-boot-security-using-oauth2-with-jwt/
- https://www.base64encode.org/

***
For any query: [linkedin.com/badrul.me](https://www.linkedin.com/in/badrulme/), hridoyjbd@gamil.com, Skype: hridoyjbd
