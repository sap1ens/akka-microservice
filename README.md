# Microservice example

Example of a microservice with Scala, Akka, Spray and Camel/ActiveMQ. Based on one of the Typesafe Activator templates.

Project contains:
- Easy to test Akka system with a sample actor
- Spray-based RESTful API with full CORS support
- Actor and API sample tests
- Camel/ActiveMQ extension for a handy integration with Akka system
- Typesafe config with different profiles for production and testing environments
- Logback-SLF4J logging
- Sbt assembly plugin for JAR-file creation with custom merge strategy

# API structure

```
POST /api/example1/test                           (Example1Routes)
GET  /api/example1/done                           (Example1Routes)
GET  /api/example1/service/{serviceID}/{command}  (Example1Routes)
GET  /api/example2                                (Example2Routes)
```

# Build & Run

To build & run:
```
sbt assembly
```

And then run JAR as usual, something like this:
```
nohup java -jar target/scala-2.10/akka-microservice-assembly-1.0.jar 1>/dev/null 2>&1 &
```
