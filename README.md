# Spring boot app with hibernate and testcontainer

1. Get mysql image
    ```shell
    docker run --name spring-hibernate-testcontainer-checking -p 3316:3306 -e MYSQL_ROOT_PASSWORD=pw -d mysql:8.1.0
    ```

2. Config connection

    - H2 config [application-h2.properties](src/main/resources/application-h2.properties)
    ```properties
    spring.h2.console.enabled=true
    spring.h2.console.path=/db/h2-console

    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=password

    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
    spring.jpa.hibernate.ddl-auto= create-drop
    spring.jpa.show-sql=true
    ```
   
    - MySQL config [application-mysql.properties](src/main/resources/application-mysql.properties)
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/forum?allowPublicKeyRetrieval=true&useSSL=false
    spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=pw

    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
    spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
    spring.jpa.hibernate.ddl-auto=none
    ```
3. Create a procedure
   ```sql
   DROP PROCEDURE IF EXISTS findAllProducerBefore;
   DELIMITER $	
   CREATE PROCEDURE findAllProducerBefore(IN selectDate DATE)
   BEGIN
       SELECT name, birthDate 
       FROM producer
       WHERE DATE(birthDate) < selectDate;
   END $
   DELIMITER ; 

   call findAllProducerBefore('1998-1-1');

   ```