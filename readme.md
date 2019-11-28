# microservice-baseline for micronaut and kotlin

[![Build Status]()
[![Quality Gate Status]()
[![Coverage]()
[![Technical Debt]()

This project serves as a baseline when you wish to begin a new micro service exposing HTTP endpoints.

It is set up with the following dependencies/frameworks:  
* Kotlin and JDK8
* [Netty Web Server](https://netty.io/)
* [Micronaut](https://micronaut.io/)
* [JUnit5](https://junit.org/junit5/)
* [Mockk](https://mockk.io/)


### Start the application 

#### maven
Run `mvn exec:exec` and it will start the application.

#### docker 
Build a new image standing in the root-project-folder and execute `docker build -t microservice -F Docker/Dockerfile .` 
to create an image with name microservice.

To start the container `docker run -p8080:8080 microservice` to start a container that listens on port 8080 on
the local machine. Go to `http://localhost:8080/health` to check after the container has started. 

#### idea
Start the main class by right click on it, should start without any special configuration.


### Health endpoint

The application includes a simple health-endpoint, reachable at `/health`.

Example response:

```json
{
  "service": "microservice-baseline",
  "timestamp": "2018-10-15T17:28:00.164Z",
  "runningSince": "2018-10-15T17:27:56.516Z",
  "version": "0.1-SNAPSHOT"
}
```

### Endpoint tests

`HealthEndpointTest.java` showcases how a reusable `TestServer` is started,
and the endpoint tested using REST-assured.

It is achieved by extending `AbstractEndpointTest`.

### Properties

This baseline is set up without Spring, Constretto or similar tools.
Instead the properties are read through the class `PropertiesHelper` in
the Main class.

Properties are read from the following locations, in the following order:

1. `application.properties` from `classpath` (resources/application.properties).
2. `config_override/application.properties` file.

### Dependency Injection to Endpoint Constructors

See `JerseyConfig` for example on how the property `secured.endpoint.message`
is passed to the `SecuredEndpoint` constructor, and registered with Jersey.

It is only required to register the endpoint if you have special constructors.
E.g. `HealthEndpoint` is registered through the line: `packages("no.capraconsulting");`

### Logback

The default `logback.xml` in this repository only appends statements to `STDOUT`.
This is intended, and covers the two main use cases:

* Running locally, and in IDE
* Running in [ECS](https://aws.amazon.com/ecs/) with the [awslogs Log Driver](
  https://docs.aws.amazon.com/AmazonECS/latest/developerguide/using_awslogs.html)

If you wish to run the application with logging to file, you should use a
properly set up file appender:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds">
  <property name="LOG_DIR" value="logs/"/>
  <property name="appName" value="app"/>
  <appender name="logfile" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>${LOG_DIR}/${appName}.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>${LOG_DIR}/${appName}-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
      <maxHistory>7</maxHistory>
      <totalSizeCap>250MB</totalSizeCap>
      <timeBasedFileNamingAndTriggeringPolicy
        class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
        <maxFileSize>50MB</maxFileSize>
      </timeBasedFileNamingAndTriggeringPolicy>
    </rollingPolicy>
    <encoder>
      <pattern>%d{yyyy-MM-dd'T'HH:mm:ss.SSSZ} [%thread] %-5level %logger{35} - %msg%n</pattern>
    </encoder>
  </appender>
  <logger name="org.eclipse.jetty" level="WARN"/>
  <logger name="no.capraconsulting" level="INFO"/>
  <root level="info">
    <appender-ref ref="logfile"/>
  </root>
</configuration>
```
"# microservice-baseline-micronaut" 
# microservice-baseline-micronaut
