## About The Project

This project is a library application that uses `Spring Boot` for server-side operations and `Angular.js` for the client side.

## Getting Started

To run locally, follow the steps below.

### Prerequisites

To run locally, you must first have `docker` and `docker-compose`.

- [Here's](https://docs.docker.com/desktop/) how to download Docker to your local environment.
- [Here's](https://docs.docker.com/compose/install/) how to download Docker Compose to your local environment.

### Installation

1. First of all, it should be checked that `docker` and `docker-compose` are running.

2. Clone the repo:
   ```sh
   git clone https://github.com/celikfatih/jupyter-docker-compose.git
    ```
3. Specify the values of the parameters in the `.env` file. If you do not specify, the values in the default.env file will apply.

   - `MONGO_DB_USERNAME`: MongoDB username.
   - `MONGO_DB_PASSWORD`: MongoDB password.
   - `MONGO_DB_URI`: The MongoDB uri to be used by the server-side application.
   - `MONGO_DB_NAME`: Database name to create in MongoDB.

4. Inside the project, to start library project (The `-d` parameter optional and allows it to run in the background):
   ```sh
   docker-compose up -d
   ```
5. If you don't get any error access to,
    * Library UI: `http://localhost:8000` or `http://127.0.0.1:8000`.
    * Library Application REST-API Documentation: `http://localhost:8082/swagger-ui/index.html` or `http://127.0.0.1:8082/swagger-ui/index.html`. 
    * MongoDB Database Manager: `http://localhost:8081` or `http://127.0.0.1:8081`.

## Contact

Fatih Celik - [@celikfatii](https://twitter.com/celikfatii) - celikfatih@protonmail.com

Project Link: [https://github.com/celikfatih/jupyter-docker-compose/](https://github.com/celikfatih/jupyter-docker-compose/)