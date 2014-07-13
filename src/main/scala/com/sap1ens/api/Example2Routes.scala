package com.sap1ens.api

import scala.concurrent.ExecutionContext
import spray.util.LoggingContext
import spray.routing._

class Example2Routes(implicit ec: ExecutionContext, log: LoggingContext) extends ApiRoute {

  val route: Route =
    path("example2") {
      get {
        complete("Done!")
      }
    }
}