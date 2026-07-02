# Hello World API

A simple Spring Boot HTTP API with a single endpoint, `GET /hello-world`, built as part
of a technical assessment.

## Requirements

- Java 17+
- Maven 3.8+

## How to Run the Application

```bash
mvn spring-boot:run
```

The application starts on `http://localhost:8080` by default.

Example requests:

```bash
curl "http://localhost:8080/hello-world?name=alice"
# 200 OK -> {"message":"Hello Alice"}

curl "http://localhost:8080/hello-world?name=nancy"
# 400 Bad Request -> {"error":"Invalid Input"}

curl "http://localhost:8080/hello-world"
# 400 Bad Request -> {"error":"Invalid Input"}
```

## How to Run the Tests

```bash
mvn test
```

This runs `HelloWorldControllerTest`, which uses MockMvc to test the endpoint and covers:

- Valid names starting with A–M / a–m (including boundary letters `a` and `m`)
- Invalid names starting with N–Z / n–z (including boundary letter `n`)
- Missing, empty, and blank `name` parameters
- Names with leading/trailing whitespace
- Names starting with a non-letter character
- Mixed/upper case input and correct capitalization of the response

## Project Structure