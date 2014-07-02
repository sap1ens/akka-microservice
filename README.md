Microservice example
=================

Example of a microservice with Scala, Akka, Spray and Camel/ActiveMQ. Based on one of the Typesafe Activator templates.

To build & run:
```
sbt assembly
```

And then run JAR as usual, something like this:
```
nohup java -jar target/scala-2.10/akka-microservice-assembly-1.0.jar 1>/dev/null 2>&1 &
```


TODO:
- API docs
- Tests