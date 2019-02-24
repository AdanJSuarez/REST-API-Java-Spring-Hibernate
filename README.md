# REST-API-Java-Spring-Hibernate
A questions server base on Java and Spring boot and Hibernate

A note here to not forget it:
- Create .mvn directory: mvn -N io.takari:maven:wrapper
- Run mysql in docker container: sudo docker run -p 3306:3306 --name db_questions -e MYSQL_ROOT_PASSWORD=myPW -d mysql:5.7
- Run application: sudo ./mvnw spring-boot:run
- Run tests: sudo ./mvnw test