# REST-API-Java-Spring-Hibernate
A questions server base on Java, Spring boot and Hibernate

A note here to not forget it:
- Create .mvn directory: $ mvn -N io.takari:maven:wrapper
- Run mysql in docker container:  $ sudo docker run -p 3306:3306 --name db_questions -e MYSQL_ROOT_PASSWORD=myPW -d mysql:5.7

- Query to create db and new user:
	
	create database db_questions;
	
	create user 'springuser'@'%' identified by 'ThePassword';
	
	grant all on db_questions.* to 'springuser'@'%';

- Run application: $ sudo ./mvnw spring-boot:run
- Run tests: $ sudo ./mvnw test
- Build the project: $ sudo ./mvnw clean package
- Run jar: $ java -jar target/rest_api-0.0.1.jar

newQuestion format:
{
  "questionType": "Checkbox",
  "question": "What are the futbol teams do you like?",
  "answerOffered": {
      "Real Madrid": "",
      "Atletico de Madrid": "",
      "Tenerife": "",
      "Las Palmas": "",
      "Getafe": "",
      "Vigo": ""
 }
}