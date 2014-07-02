package com.sap1ens

import akka.actor.{ActorLogging, Actor}
import akka.camel.{Consumer, Oneway, Producer}

/**
 * Should be created as a separate actor
 */
class EventingProducer extends Actor with ActorLogging with Producer with Oneway {
  def endpointUri = "activemq:queue:events"
}

/**
 * Should be mixed with any actor
 */
trait EventingConsumer extends Actor with ActorLogging with Consumer {
  def endpointUri = "activemq:topic:events"
}