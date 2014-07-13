package com.sap1ens

import akka.actor.ActorSystem
import com.sap1ens.utils.ConfigHolder
import akka.camel.CamelExtension
import org.apache.activemq.camel.component.ActiveMQComponent

trait Core {
  implicit def system: ActorSystem
}

trait BootedCore extends Core {
  implicit lazy val system = ActorSystem("microservice-system")

  sys.addShutdownHook(system.shutdown())
}

trait CoreActors extends ConfigHolder {
  this: Core =>

  val exampleService = system.actorOf(ExampleService.props("test property"), "ExampleService")

  val services: Services = Map(
    "example" -> exampleService
  )
}

trait ActiveMQSupport extends ConfigHolder {
  this: Core =>

  val brokerURL = s"tcp://${config.getString("activemq.host")}:${config.getString("activemq.port")}"
  val camel = CamelExtension(system)
  camel.context.addComponent("activemq", ActiveMQComponent.activeMQComponent(brokerURL))
}