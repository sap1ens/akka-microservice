package com.sap1ens.api

import scala.concurrent.ExecutionContext
import spray.util.LoggingContext
import spray.json.DefaultJsonProtocol
import spray.routing._
import spray.http.StatusCodes

object Example1Routes {
  case class TestAPIObject(thing: String)

  object Example1RoutesProtocol extends DefaultJsonProtocol {
    implicit val testAPIObjectFormat = jsonFormat1(TestAPIObject)
  }
}

class Example1Routes(implicit ec: ExecutionContext, log: LoggingContext) extends ApiRoute {
  import Example1Routes._
  import Example1RoutesProtocol._

  val route: Route =
    path("example1" / "test") {
      post {
        entity(as[TestAPIObject]) { request =>
          complete(StatusCodes.OK, s"you send me ${request.thing}")
        }
      }
    } ~
    path("example1" / "done") {
      get {
        complete("Done!")
      }
    }
}
