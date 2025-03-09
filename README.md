# Capital gains

## Contents table

- [Prerequisites](#Prerequisites)
- [Architecture](#Architecture)
- [Running](#Running)
- [Tests](#Tests)

## Prerequisites
- Java 21
- Maven (project managment and build)

## Architecture

For the implementation of this project, the principles of Clean Architecture were applied. This helps ensure that the project is well-organized, with clearly defined separation of responsibilities, operates independently of the framework, has a high level of maintainability, low coupling, and contains elements that can be easily replaced or extended. In this project, to facilitate the reading and printing of JSON objects, the jackson-databind dependency was added.

## Running

First, inside the project's root folder, run this command::

```sh
mvn clean package
```

After that, just run this command:

```sh
java -jar .\target\capitalgains-1.0-SNAPSHOT.jar
```

## Tests

To run the application tests, execute the following command:

```sh
mvn clean test
```

