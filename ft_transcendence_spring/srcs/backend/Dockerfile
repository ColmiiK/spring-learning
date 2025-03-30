FROM amazoncorretto:17-alpine

WORKDIR /app

RUN apk add --no-cache maven

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests -T 1.5C

CMD ["java", "-jar", "target/transcendence-0.0.1-SNAPSHOT.jar"]
