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

Base Users (Users + Clients):
GET User Login - http://localhost:8080/api/User/Auth <br>
Required Body: { 
                 "email": "", 
                 "password": "" 
               }

Users:
GET Single User - http://localhost:8080/api/User?id={userId} <br>

GET User List - http://localhost:8080/api/User/All <br>

POST Insert User - http://localhost:8080/api/User <br>
Required Body:  {
                "username": ,
                "email": ,
                "password": ,
                "active": ,
                "role": ("ADMINISTRATOR" or "MODERATOR"),
                }
               
DeliveryCompanies:
GET DeliveryCompanyList - http://localhost:8080/api/DeliveryCompany/All <br>

POST Insert DeliveryCompany - http://localhost:8080/api/DeliveryCompany <br>
Required Body:  {
                "name": ,
                "deliveryFee": ,
                }
