ARG VERSION

FROM eclipse-temurin:8-jdk-jammy AS builder
COPY . /observer
WORKDIR /observer
RUN ./gradlew -g .gradle --no-daemon clean build

FROM eclipse-temurin:8-jdk-jammy
ARG VERSION
COPY --from=builder /observer/app/build/libs/app-$VERSION.jar app.jar
