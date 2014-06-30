package com.sap1ens.api

import spray.routing._
import akka.actor.{ActorLogging, Props}
import akka.io.IO
import spray.can.Http
import spray.json.DefaultJsonProtocol
import spray.util.LoggingContext
import spray.httpx.SprayJsonSupport
import com.sap1ens.utils.ConfigHolder
import com.sap1ens.{Core, CoreActors}

trait Api extends Directives with RouteConcatenation with ConfigHolder {
  this: CoreActors with Core =>

  private implicit val _ = system.dispatcher

  val routes = pathPrefix("api") {
    new Example1Routes().route ~
    new Example2Routes().route
  }

  val rootService = system.actorOf(ApiService.props(config.getString("hostname"), config.getInt("port"), routes))
}

object ApiService {
  def props(hostname: String, port: Int, routes: Route) = Props(classOf[ApiService], hostname, port, routes)
}

class ApiService(hostname: String, port: Int, route: Route) extends HttpServiceActor with ActorLogging {
  IO(Http)(context.system) ! Http.Bind(self, hostname, port)

  def receive: Receive = runRoute(route)
}

object ApiRoute {
  case class Message(message: String)

  object ApiRouteProtocol extends DefaultJsonProtocol {
    implicit val messageFormat = jsonFormat1(Message)
  }

  object ApiMessages {
    val UnknownException = "Unknown exception"
  }
}

abstract class ApiRoute(implicit log: LoggingContext) extends Directives with SprayJsonSupport {

}
