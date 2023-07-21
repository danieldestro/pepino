# JUnit, Cucumber and Spring Boot Example Project

This is a simple Java project implemented to show case how to create a REST API application and implement the software test pyramid concept (unit, integration and end-to-end tests).

Technologies adopted:
* Java JDK 17
* Spring Boot 6
* JUnit 5
* Cucumber 7
* Maven

## Test pyramid

According to the test pyramid concept, we can have:
* Unit tests - implemented with JUnit with no integration (*Test.java files)
* Integration tests - implemented with JUnit integrated with Spring and database (*IT.java files)
* E2E tests - implemented with Cucumber integrated with JUnit, Spring and database (*.feature files)

## How to run unit tests

This command compiles and run unit tests implemented with JUnit (all *Test.java files).

```mvn clean test```

## How to run integration and E2E tests

This command compiles and run integration and E2E tests implemented with JUnit and Cucumber (all *IT.java and *.feature files).

```mvn clean test integration-test```