import sbtassembly.Plugin._
import AssemblyKeys._

name := """akka-microservice"""

version := "1.0"

scalaVersion := "2.10.4"

assemblySettings

val akkaVersion = "2.3.3"

val sprayVersion = "1.3.1"

resolvers += "spray" at "http://repo.spray.io/"

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-camel" % akkaVersion,
    "io.spray" % "spray-can" % sprayVersion,
    "io.spray" % "spray-routing" % sprayVersion,
    "io.spray" %% "spray-json" % "1.2.6",
    "com.typesafe" % "config" % "1.2.1",
    "org.apache.activemq" % "activemq-camel" % "5.8.0",
    "ch.qos.logback" % "logback-classic" % "1.1.2",
    "org.scalatest" % "scalatest_2.10" % "2.2.0" % "test",
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
)

lazy val logback = "ch.qos.logback" % "logback-classic" % "1.1.2"

test in assembly := {}