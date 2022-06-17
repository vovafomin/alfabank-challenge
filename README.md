# Alfa Bank Challenge

## Requirements

- JDK 17
- Gradle

## Run with Docker

Build image:

```shell
docker build -t alfabank-challenge .
```

Run container:

```shell
docker run --rm -it -p 8080:8080 alfabank-challenge
```

## Run without Docker

```shell
./gradlew bootRun
```

Open the following link in Postman or a browser, and replace the currency code as follows:

For EUR:

http://localhost:8080/evaluate/EUR

For JPY:

http://localhost:8080/evaluate/JPY

By default we are comparing with the previous day's rates, optionaly you can use the query paramter `minusDays` to compare with rates of N days ago, example:

http://localhost:8080/evaluate/SEK?minusDays=2

# Documentation

http://localhost:8080/swagger-ui/index.html