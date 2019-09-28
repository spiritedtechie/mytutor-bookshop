# mytutor-bookshop

Requirements:

 - Java 11 SDK
 - Maven 3

To run directly:

```
    cd <project_dir>
    mvn clean spring-boot:run
```

To build a standalone executable and run:

```
    cd <project_dir>
    mvn clean package spring-boot:repackage
    java -jar target/bookshop-1.0-SNAPSHOT.jar
```

To use the API:

```
    curl -X POST http://localhost:8080/order -d "book_name=A&quantity=3" 
```