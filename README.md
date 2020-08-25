# luis-serrato-sample-java-test
Transaction sample test

# Requirements
- JAVA 8
- Gradle 5.2.1 or +
- Git console 

# Installation
- Clone the reposotiry https://github.com/luiz2092/luis-serrato-sample-java-test.git
- Go to the folder 'luis-serrato-sample-java-test' and compile the project with 'gradle build'
- Go to \build\libs and run the generated jar with 'java -jar simple-test-luis-serrato-0.0.1-SNAPSHOT.jar'
- If the init was successful, there will be a new folder called 'transactions', this is where all the transactions will be stored.

# Usage
```` Operations ´´´´

- ADD TRANSACTION

Receives userId and requestBody params.
Request: POST http://[$host]:8081/v1/transactions/{userId}

params
    userId: Long
	requestBody: JSON

headers
'Content-Type: application/json' 

example: http://localhost:8081/v1/transactions/3399
requestBody:
	{
		"date":"2020-04-12",
		"description":"TForTest",
		"amount": 17.20
	}

Response:
	example
	{
		"transactionId": "fabb9b38-5274-4541-b732-9a4cf25688cf",
		"date": "2020-04-12T00:00:00.000-0500",
		"description": "TForTest",
		"amount": 17.2
	}

- SHOW TRANSACTION

Receives userId and transactionId params.
Request: GET http://[$host]:8081/v1/transactions/{userId}/{transactionId}

params 
	userId: Long
	transactionId: String
example
	http://localhost:8081/v1/transactions/3399/ad8fe4f1-dd94-436d-83ff-8ab3168648ae

Response:
	example
	{
		"transactionId": "ad8fe4f1-dd94-436d-83ff-8ab3168648ae",
		"userId": 3399,
		"date": "2020-04-12T00:00:00.000-0500",
		"description": "TForTest",
		"amount": 17.2
	}
	
- LIST TRANSACTIONS
Receives userId param.

Request:

GET http://[$host]:8081/v1/transactions/{userId}
params 
	userId: Long

example	
	http://localhost:8081/v1/transactions/3399
	
Response:
	example
	{
		"transactions": [
			{
				"transactionId": "ad8fe4f1-dd94-436d-83ff-8ab3168648ae",
				"userId": 3399,
				"date": "2020-04-12T00:00:00.000-0500",
				"description": "TForTest",
				"amount": 17.2
			}
		]
	}

- SUM TRANSACTIONS
Receives userId param.

Request:

GET http://[$host]:8081/v1/transactions/sum/{userId}
params 
	userId: Long

example
	http://localhost:8081/v1/transactions/sum/3399

Response:
	example	
	{
		"userId": 3399,
		"sum": 17.2
	}

- TRANSACTIONS REPORT SERVICE
Receives userId param.

Request:

GET http://[$host]:8081/v1/transactions/report/{userId}
params 
	userId: Long

example
	http://localhost:8081/v1/transactions/report/3399
	
Response:
	example	
	{
		"transactionsReport": [
			{
				"userId": 3399,
				"weekStart": "2020-04-10T05:00:00.000+0000",
				"weekFinish": "2020-04-16T05:00:00.000+0000",
				"quantity": 1,
				"amount": 17.2,
				"totalAmount": 0
			}
		]
	}
	
- RANDMON SINGLE TRANSACTION

Request:
GET http://[$host]:8081/v1/transactions/random

example
	http://localhost:8081/v1/transactions/random
	
Response:
	example		
	{
		"transactionId": "ad8fe4f1-dd94-436d-83ff-8ab3168648ae",
		"userId": 3399,
		"date": "2020-04-12T00:00:00.000-0500",
		"description": "TForTest",
		"amount": 17.2
	}
