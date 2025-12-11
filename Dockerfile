FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY . .
RUN chmod +x ./gradlew \
    && ./gradlew clean bootJar -x test

FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/build/libs/phone.jar /app/phone.jar

EXPOSE 9081
ENTRYPOINT ["java","-jar","/app/phone.jar"]
