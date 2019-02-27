# REST-API-Java-Spring-Hibernate

A questions server base on Java, Spring Boot, Hibernate and MySQL.

This piece of software is a REST API that will serve up unique questions each time the embed widget on the publisher website calls it. The answer given by the embed will be stored in a database, MySQL in this case. Some question (trivia) has right and wrong answers and these answers are provided with the question when this is required.

### General considerations:

Each user has an unique UUID. This is given by the embed to the server in the URL address. 

Each site/user has its own list of questions evenly distributed among: trivia, poll, checkbox, and matrix and at least has to have one of each. What the software does is serve one different question at a time, and different kind each time in a sequential order ( trivia -> poll -> checkbox -> matrix -> trivia -> etc). When the list of question hit the end it starts from the beginning. The reason to do it in this way is to make the experience more attractive and avoid the user to have the same kind of question over and over, for example to avoid many trivia question in a row.

Questions will be stored in a MySQL database. The project has a pretty simple REST interface to deal with the questions. We can only set or delete questions, but enough to test the code.

In the code we find some test, far for being enough. Just a couple of them to show how it works.

From the title of the repo you can guess what we use here but let me be more specific: Java, Spring (Spring boot), Hibernate (Spring JPA Data), MySQL and docker.

Error handling is pretty basic. To be enough resilient it needs more work in this part.

Security: There are many issues here but thinking about the very basic, first we create a user in the database with all the permission that in real production we never do. The connections to the server is through a http connection where we should use https and implement some authentication process.

Scalability: To think about scalability we probably need to think about the whole architecture but, there is one thing to consider the first and it is make the code run asynchronous. All I/O operation should be async and not blocking to let it deal with a bigger amount of request, libraries like Apache Mina could help. Perhaps some load balancing software like NGinx, but depend of the architecture you choose to use. The last thing and less "famous" could be the dockerization of the database and make it distributed, ...but depend.  

### Technical considerations.

There are four kind of question: trivia, poll, checkbox and matrix. I end up with a solution to deal with this four and even more if it were required. The different between then are basically how to deal with the answers because they have four different formats. My solution is make all kind of answer to fit in a Hashmap<String, String> where depending of the kind of question, it should to be interpreted by the embed in different ways. This approach let us have flexible number of answers from 1 to (infinite?) In the body of request returned by the server there is a field call "questionType" that helps the embed to parse the question. The embed receive a simple JSON object matching the key/value schema of the hashmap that is quite easy to parse. The embed use the same mechanism to return the answer, it only modify the Hashmap (JSON object) with the answer included.

#### Trivia: 

In trivia questions the Hashmap key/value pair corresponds with a row of the questions, for example, in the question: 

Which team won the 2017 super bowl?
Falcons
Patriots

	Map<String, String> trivia = new Hashmap<>();

	trivia.put("Falcons", "false");

	trivia.put("Patriots", "true");

In this way we give to the embed the question and the answer at once. The embed knows what type of question is and it just need to parse accordingly.

An answer given by the embed to the above question is:

	{
		"questionType": "Trivia",
		"question": Which team won the 2017 super bowl?",
		"answerReturned": {
			"Falcons": "",
			"Patriots": "true"
		}
	}
	
Meaning that the user gave the write answer. In case of wrong answer, it could be:

	{
		"questionType": "Trivia",
		"question": Which team won the 2017 super bowl?",
		"answerReturned": {
			"Falcons": "false",
			"Patriots": ""
		}
	}

#### Poll:

Similar to trivia but this time we don't provide any right and wrong answer, so we leaf the fields blank.

	Map<String, String> poll = new Hashmap<>();

	poll.put("Nissan", "");

	poll.put("Honda", "");
	
	poll.put("Audi", "");
	
	poll.put("BMW", "");

The embed will return the question, with the answer included, as simple as:

	{
		"questionType": "Poll",
		"question": What's your favorite car brand?",
		"answerReturned": {
			"Nissan": "",
			"Honda": "",
			"Audi": "true",
			"BMW": ""
		}
	}

#### Checkbox:

The same that before but checkbox let the user selects more that one answer so the embeb include "true" in every answer selected: 

	{	
		"questionType": "Poll",
		"question": What are the colors do you like?",
		"answerReturned": {
			"Red": "",
			"Blue": "",
			"Yellow": "true",
			"Green": "true",
			"Black": "true",
			"Purple": "",
		}
	}

#### Matrix:

Matrix is a bit more complex but it still easy to follow. The key/value pair, instead of represents rows as before, the hashmap represent columns, and the way to separate the different answers is by dividing it with pipes "|". Parse separations by pipe is quite easy too.
In the example given, the first column is part of the question: 

	matrix.put("Age/Gender","<18|18 to 35|35 to 55|> 55")
	
The rest is us before:

	matrix.put("Male", "|||");
	matrix.put("Female", "|||");
	
The embed just need to set the answer in the right place, in my particular case that I am male between 35 and 55 the answer should be like:

	{"Male": "||true|"}

#### Set a question in database:

It has a REST API to include questions and answer in the database. We just need to make a POST request with the following URL: 
	
	http://localhost:8080/newQuestion/UUID/aa1
	
Where aa1 is the UUID at witch you want to include the question, and the body has to have the following structure:

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
or

	{
		"questionType": "Trivia",
		"question": "Which team won the 2017 super bowl?",
		"answerOffered": {
			"Falcons": "false",
			"Patriots": "true"
		}
	}

A couple of considerations, the question type has to begin by capital letter as the examples. The fields have to be exactly those. It's only the answerOffered what is flexible as explained before.
Remember to include at least one question of each type and try to include the same number of each.

#### Delete question from database:

We just need to make a DELETE request with the following URL:

	http://localhost:8080/deleteQuestion/14/UUID/aa1
	
Where 14 is the specific questionId and aa1 the UUID of the user. An important note here, the way the data persist in the database make that every question has its own Id, so there aren't two question with the same Id even from different users, so we can identify the question quite easy. We included the UUID, that you can guess it is not needed because of the questions Id, as a reference and help to identify questions-user.

#### Make a question request:

The whole idea is to serve up question to the embed and for that this is the most important part with the answer returned by the embed. The way the embed has to make it is with a GET  request with the following URL;

	http://localhost:8080/UUID/aa1
	
Where the aa1 is the UUID from which we want the question. It returns a question with the format explained as follow:

	{
		"id": 16,
		"questionType": "Trivia",
		"question": "What's the worst futbol team in Canary Island ?",
		"answerOffered": {
			"id": 15,
			"answer": {
			"Atletico de Madrid": "",
			"Las Palmas": "true",
			"Real Madrid": "",
			"Tenerife": ""
			}
		},
		"answerReturned": null,
		"recordAnswerReturned": null
	}

- "id" is the question id -this is unique-. 

- "questionType" is the type of question. 

- "question" is the question.

- "answerOffered" is the options we give to he user.

- "answerReturned" is the field that the embed has to fill with the answer given by the user as explained before.

- "recordAnswerReturned" is the field we use to store the answer in the database. The embed has nothing to do with it.

#### Return an answer for the embed:

Once the user has answered the question, the embed has to return this answer to store it in the database. The way to do that is to make a POST request to the following URL:

	http://localhost:8080/UUID/aa1
	
Where the aa1 is the UUID and the body of the request has to include the same question receive in the question request but with the answer included in the field "answerReturned".

#### Set everything up:

- For simplicity the software runs in a local machine, so the URL is going to be: 

	http://localhost:8080

- For simplicity the database runs in a docker container, MySQL database 5.7. To download the image and run the container:
(Note: if you don't use docker and prefer install MySQL, skip the two following steps)

	$ docker pull mysql

	$ sudo docker run -p 3306:3306 --name db_questions -e MYSQL_ROOT_PASSWORD=myPW -d mysql:5.7
	
- Once the docker is running, to create the db we need to run the following query:
	
	create database db_questions;
	
- To avoid use the db as root, we need to create a user:

	create user 'springuser'@'%' identified by 'ThePassword';
	
- We give it all permissions (Note: In production we must limit the permissions):

	grant all on db_questions.* to 'springuser'@'%';

- The internal structure and the tables are created by the software so the first time we run it we need to set:
	
	src/main/resources -> application.properties -> spring.jpa.hibernate.ddl-auto=create
	
After the first run, when the tables are created, we set back "spring.jpa.hibernate.ddl-auto" to "update".

- The project is a maven project, in order to use a maven wapper (to make our life easier) we need to run:

	$ mvn -N io.takari:maven:wrapper
	
- To build the project:

	$ sudo ./mvnw clean package
	
- To run the application from the JAR file after built:

		$ java -jar target/rest_api-0.0.1-SNATSHOT.jar
		
- To run the code from the code source:

	$ sudo ./mvnw spring-boot:run
	
- To run the tests: 

	$ sudo ./mvnw test
	

Adan J. Suarez
