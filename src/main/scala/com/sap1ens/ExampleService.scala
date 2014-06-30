package com.sap1ens

import akka.actor.{Props, ActorLogging, Actor}

object ExampleService {
  case class ExampleMessage(message: String)

  def props(property: String) = Props(classOf[ExampleService], property)
}

class ExampleService(property: String) extends Actor with ActorLogging {
  import ExampleService._

  def receive = {
    case ExampleMessage(message) => {
      log.info(s"Example $message with property $property!")

      sender() ! message
    }
  }
}
