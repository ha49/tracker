# FITNESS TRACKER

In order to run the program smoothly please run Artemis Server in a Docker Container using the command below.

● docker container run --name artemis -p 8161:8161 -p 61616:61616 vromero/activemq-artemis

● Then run TrackerApplication.java under src/main/java/com/example/tracker/web folder.

● Application is  deployed to heroku https://thx.herokuapp.com/ 
## SIGN UP

This application allows registration for two roles: CLIENT and COACH. Registration for these roles are open to anybody
without authorization. ADMIN role is predetermined and currently not extendable. Certain pages and endpoints are
restricted for these roles. Home page and information page can be accessed without login.

### POST: REGISTER CLIENT

__http://localhost:8080/user/createClient__

````
{
"firstName": "Mona",
"lastName": "Lisa",
"weight": 80,
"height": 160,
"phoneNumber": "07000000",
"diet": "1800 cal",
"gender": "Female",
"lifeStyle": "inactive",
"userFlx": {
"username": "monalisa",
"password": 1234,
"email": "monalisa@email.com"
}

}
````

### POST: REGISTER COACH

__http://localhost:8080/user/createCoach__

````
{
"firstName": "Leonardo",
"lastName": "Da Vinci",
"specialization": "FITNESS",
"phoneNumber": "0800000",
"userFlx": {

"username": "leonarda",
"password": "1234",
"email": "leonardo@email.com"
}
}
````

# JMS

When a new client registers, Tracker application sends a message to Customer Service application ( customer module
within the project ). The message includes name and contact information of the new client.

## GET REGISTERED COACH AND CLIENT DATA

Access to coach and client data requires authorization. Coach can access all coach and client data. Client can not
access to coach data. Client also has restricted access to client data. Other than new users added during at the
previous stages usernames coach1, coach2, client1, client2 can be used as test case. The password for all users is 1234.

#### SOME ACCESSIBLE ENDPOINTS FOR COACH WITH VALID CREDENTIALS (username: coach1 , password: 1234)

__CoachPage : http://localhost:8080/coachpage__
__Get All Coaches:  http://localhost:8080/coach/getall__
__Get a Certain Coach:  http://localhost:8080/coach/get/36__
__Get Clients of a Certain Coach: http://localhost:8080/coach/getclients/9__
__Get All Client Data: http://localhost:8080/client/getall__
__Get Certain Client Data: http://localhost:8080/client/get/18__

#### INACCESSIBLE ENDPOINTS FOR COACH DESPITE VALID CREDENTIALS

__Admin Page:  http://localhost:8080/adminpage__
__Admin Page:  http://localhost:8080/clientpage__

#### SOME ACCESSIBLE ENDPOINTS FOR CLIENT WITH VALID CREDENTIALS (username: client1 , password: 1234)

__ClientPage : http://localhost:8080/clientpage__
__Get Certain Client Data: http://localhost:8080/client/get/18__

#### INACCESSIBLE ENDPOINTS FOR CLIENT DESPITE VALID CREDENTIALS

__Admin Page:  http://localhost:8080/adminpage__
__Admin Page:  http://localhost:8080/coachpage__
__Get All Coaches:  http://localhost:8080/coach/getall__
__Get a Certain Coach:  http://localhost:8080/coach/get/36__
__Get All Client Data: http://localhost:8080/client/getall__

### CLIENT DECIDES TO REGISTER TO A PROGRAM (SUBSCRIPTION) PROVIDED BY A CERTAIN COACH

#### POST: ADD MEMBERSHIP

Please update id values after using get methods for coach and client

````
{  
"clientFlx": {
"id": 224
},
"coachFlx": {
"id": 227
},
"membershipDurationDays":120
}

````

### CLIENT UPLOADS DOCUMENT (TEST)  WHICH WILL BE ACCESSIBLE BY COACH

#### POST: ADD DOCUMENT

````
{
"clientFlx": 
    { "id": 162 
     },
    "name": "Blood Test",
    "path": "/test/bloodtest"
}

````

### COACH UPLOADS SOME FITNESS VIDEO LINKS  WHICH WILL BE ACCESSIBLE BY CLIENT

#### POST: LINK

````
{
"description": "20 MIN FULL BODY WORKOUT BEGINNER",
"link": "https://www.youtube.com/watch?v=UItWltVZZmE",
"coachFlx": {
"id": 205

}
}
````

## CLIENT DECIDES TO END HIS/HER SUBSCRIPTION

### DELETE:

When Client is removed, document and program membership data will also be deleted together with client information

__Delete Client By Id http://localhost:8080/client/delete/224__

## COACH DECIDES TO END HIS/HER SUBSCRIPTION

### DELETE:

When Coach is removed, link and program membership data will also be deleted together with coach information

__Delete Coach By Id http://localhost:8080/coach/delete/227__ 


