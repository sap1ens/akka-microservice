package com.sap1ens.api

import spray.testkit.ScalatestRouteTest
import com.sap1ens._
import spray.http.{MediaTypes, StatusCodes, HttpEntity}
import spray.httpx.SprayJsonSupport._
import MediaTypes._
import com.sap1ens.api.ApiRoute.Message

class Example1RoutesSpec extends BaseSpec with ScalatestRouteTest with Api with CoreActors with Core {
  def actorRefFactory = system

  import com.sap1ens.api.ApiRoute.ApiRouteProtocol._

  behavior of "Example1 API"

  // we have to redefine routes to properly inject services
  val routesWithServices = {
    pathPrefix("api") {
      new Example1Routes(services).route
    }
  }

  it should "complete 'example1/test' endpoint" in {
    Post("/api/example1/test", HttpEntity(`application/json`, """{ "thing": "ping" }""")) ~> routes ~> check {
      status should be (StatusCodes.OK)
      body.asString should be ("you send me ping")
    }
  }

  it should "complete 'example1/done' endpoint" in {
    Get("/api/example1/done") ~> routes ~> check {
      status should be (StatusCodes.OK)
      body.asString should be ("Done!")
    }
  }

  it should "fail 'example1/service' endpoint with incorrect service ID" in {
    Get("/api/example1/service/incorrect/hi") ~> routesWithServices ~> check {
      status should be (StatusCodes.BadRequest)
      responseAs[Message].message should be ("Sorry, provided service is not supported.")
    }
  }

  it should "complete 'example1/service' endpoint with correct result" in {
    Get("/api/example1/service/example/hello") ~> routesWithServices ~> check {
      status should be (StatusCodes.OK)
      responseAs[Message].message should be ("hello")
    }
  }
}
