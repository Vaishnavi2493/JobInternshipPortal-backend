\# Job Internship Portal — Backend



A full-stack Job Internship Portal backend built using \*\*Java, Spring Boot, Spring Security, JWT, and MySQL\*\*.



This application provides REST APIs for user authentication, job management, and job application workflows.



\## Features



\- User registration and login

\- JWT-based authentication

\- Role-based access for Admin and Job Seeker

\- Job listing and job search

\- Job application management

\- Application status tracking

\- REST API integration with an Angular frontend



\## Tech Stack



\- \*\*Language:\*\* Java

\- \*\*Backend Framework:\*\* Spring Boot

\- \*\*Security:\*\* Spring Security, JWT

\- \*\*Database:\*\* MySQL

\- \*\*Build Tool:\*\* Maven

\- \*\*API Style:\*\* REST



\## Project Structure



```text

src/main/java/com/example/demo/

├── config/

├── controller/

├── repository/

├── security/

├── service/

└── user/

```



\## Main API Endpoints



| Method | Endpoint | Purpose |

|---|---|---|

| POST | `/api/users/register` | Register a user |

| POST | `/api/users/login` | Authenticate a user |

| GET | `/jobs` | Retrieve available jobs |



Other endpoints support job management and job applications.



\## Run Locally



\### Prerequisites



\- Java Development Kit (JDK)

\- MySQL Server

\- Git



\### Setup



1\. Clone this repository.

2\. Create a MySQL database for the application.

3\. Create a local `src/main/resources/application.properties` file.

4\. Configure your database URL, username, and password in that local file.

5\. Open the project in Eclipse or another Java IDE.

6\. Install or resolve Maven dependencies.

7\. Run `JobInternshipPortalApplication.java`.

8\. The backend should start on port `8080`, provided the local configuration uses that port.



Keep database credentials and other secrets out of GitHub.



\## Frontend



The Angular frontend is available here:



\[Job Internship Portal — Frontend](https://github.com/Vaishnavi2493/JobInternshipPortal-frontend)



\## Repository



\[Job Internship Portal — Backend](https://github.com/Vaishnavi2493/JobInternshipPortal-backend)



\## Future Enhancements



\- Resume upload

\- Email notifications

\- Advanced job filtering

\- AI-powered career assistance



\---



\*\*Project:\*\* Job Internship Portal  

\*\*Purpose:\*\* Practice and demonstrate full-stack web development.



