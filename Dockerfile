FROM gradle:jdk17 as build-env
WORKDIR /build
COPY . .
RUN gradle assemble

FROM gradle:jdk17
WORKDIR /app
COPY --from=build-env /build/build/libs .

ENTRYPOINT ["java", "-jar", "./alfabank-challenge-1.0.0.jar"]
EXPOSE 8080/tcp