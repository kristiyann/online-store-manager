<h1><p align="center">Online Trade Manager</p></h1>

<h3>API Installation:</h3>
1. This code uses MySQL as a database: https://www.mysql.com/downloads/ <br>
https://dev.mysql.com/downloads/installer/ <br>
   After installing MySQL, create a connection and a new database using your Shell or MySQL Workbench <br>
2. Connection data is stored in the application.properties file. <br>

``` spring.datasource.url=jdbc:mysql://localhost:3306/{database name} ``` takes in the database name you are using. <br>
``` spring.datasource.username={username} ``` takes the username of the connection of the database <br>
``` spring.datasource.password={password} ``` takes the password of the connection <br>

Rest can stay the same without problems. <br>

If you're having issues turning on your connection, the easiest way to do that is by going to "Task Manager > Services" and turning on MySQL80 <br>
3. Clone the project and open in your desired IDE. By default it is started on port 8080.

<h3>API Use:</h3>

<h5> Base Users (Users + Clients): </h5> <br>
GET User Login - http://localhost:8080/api/User/Auth <br>
Required Body: { 
                 "email": "string@string.string", 
                 "password": "string" 
               } <br>

<h5> Users: </h5>
GET Single User - http://localhost:8080/api/User?id={userId} <br> <br>

GET User List - http://localhost:8080/api/User/All <br> <br>

POST Insert User - http://localhost:8080/api/User <br>
Required Body:  {
                "username": "string",
                "email": "string@string.string",
                "password": "string",
                "active": true/false,
                "role": "ADMINISTRATOR"/"MODERATOR"
                } <br>
                
PUT Edit User - http://localhost:8080/api/User <br>
Required Body: {
                  "id": UUID,
                  "username": "string",
                  "email": "string@string.string",
                  "password": "string", (can be null)
                  "active": true/false,
                  "role": "ADMINISTRATOR"/"MODERATOR"
               } <br>
                
<h5> Clients: </h5> <br>
GET Client List - http://localhost:8080/api/Client/All <br> <br>

POST Insert Client - http://localhost:8080/api/Client <br>
Required Body:  {
                  "username": "string",
                  "email": "string@string.string",
                  "password": "string",
                  "active": true/false,
                } <br>
PUT Edit Client - http://localhost:8080/api/Client <br>
Required Body:  {
                  "id": UUID,
                  "username": "string",
                  "email": "string@string.string",
                  "password": "string", (can be null)
                  "active": true/false,
                } <br>
                
<h5> Bank Accounts: </h5>

POST Insert - http://localhost:8080/api/BankAccount <br>
Required Body: {
                  "bankName": "string",
                  "cardNumber": "0000000000000000" (16 digits),
                  "cvv": "000" (3 digits),
                  "expiryDate": "YYYY-MM-DD",
                  "clientId": UUID
               } <br>
               
GET BankAccountList by Client - http://localhost:8080/api/BankAccount?clientId={clientId} <br> <br>

PUT BankAccount - http://localhost:8080/api/BankAccount <br>
Required Body: {
                  "id": UUID,
                  "bankName": "string",
                  "cardNumber": "0000000000000000" (16 digits),
                  "cvv": "000" (3 digits),
                  "expiryDate": "YYYY-MM-DD",
               } <br>

DELETE BankAccount - http://localhost:8080/api/BankAccount?id={id} <br> <br>
               
<h5> DeliveryCompanies: </h5> <br>
GET DeliveryCompanyList - http://localhost:8080/api/DeliveryCompany/All <br> <br>

POST Insert DeliveryCompany - http://localhost:8080/api/DeliveryCompany <br>
Required Body:  {
                "name": "string",
                "deliveryFee": 0.00: 
                } <br>
PUT Edit DeliveryCompany - http://localhost:8080/api/DeliveryCompany <br>
Required Body:  {
                "id": UUID,
                "name": "string",
                "deliveryFee": 0.00
                } <br>
DELETE DeliveryCompany - http://localhost:8080/api/DeliveryCompany?id={deliveryCompanyId} <br> <br>
