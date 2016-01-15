# GGS-CoffeeSystem
This project was developed for GGS. It represents a system that allows programmers to get coffee and pay for it.

### Pre requisities
- [Apache Maven](https://maven.apache.org/index.html)
- [RabbitMQ](https://www.rabbitmq.com/)

## Built with
- Java 1.8
- Maven 3.2.1
- RabbitMQ
- Spring Boot (AMQP, JPA, TEST)
- HSQLDB
- JUnit

## Installation
- Clone the project
- Download all the maven dependencies in the project 
- Starts the [RabbitMq Server](https://www.rabbitmq.com/install-generic-unix.html)

### How to use 
To execute the system you need run [Application.java](https://github.com/saudborg/GGS-CoffeeSystem/blob/master/src/main/java/com/sauloborges/ggs/Application.java).
The system will read the variables on application.context like: 
- How many programmers would you like to test? EX: 100, 200, 500, 1000
- Would you like to print details about each step? EX:
  <br/>User <b>Programmer 8</b> got your coffee. 
	<br/>Spent total: 00 min, 04 sec, 115 mili
	<br/>Spent in the pay queue: 00 min, 00 sec, 079 mili
	<br/>Spent to paid and got a coffee: 00 min, 03 sec, 032 mili
	<br/>Spent in the machine coffee queue: 00 min, 01 sec, 270 mili
	<br/>Spent to got the coffee in machine: 00 min, 01 sec, 762 mili

All of this informations and more you can see in the file application.log. This file is configured to DEBUG mode, so you can see everything that is happening in the system.

### How it works
The system was developed using the concept of queues, where each machine represents one receiver for the specific queue following the rules.
- Create a number X of programmer and put in a Queue 1
- The machine 1 (Choose a coffee) receives programmers from Queue 1, but it has 10 consumers. Until one machine be available the others keep waiting at queue 1
- After processing the machine 1, will send to Queue 2
- The machine 2 (Pay for a coffee) receives programmers from Queue 2, but it has 5 consumers. Until one machine be available the others keep waiting at queue 2
- After processing the machine 2, will send to Queue 3
- The machine 3 (Get a coffe) receives programmers from Queue 3, but it has 2 consumers. Until one machine be available the others keep waiting at queue 3
- Meanwhile another receiver is receiving statistics about which machine each programmer passed and saves in a memory database (hsqldb). After this receiver has sure that all the programmers had got your coffee, it will set a flag saying that now can print the results.
- To show the results, the system will collect in the database all informations that it needs. Like: All the programmers, all the statistics, all the statistics in a specific machine.


## Results
Here you can see the results and how was the executation of each test 
- [100 programmers](https://github.com/saudborg/GGS-CoffeeSystem/tree/master/results/100%20programmers)
- [200 programmers](https://github.com/saudborg/GGS-CoffeeSystem/tree/master/results/200%20programmers)
- [500 programmers](https://github.com/saudborg/GGS-CoffeeSystem/tree/master/results/500%20programmers)
- [1000 programmers](https://github.com/saudborg/GGS-CoffeeSystem/tree/master/results/1000%20programmers)
