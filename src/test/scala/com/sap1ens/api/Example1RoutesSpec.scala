package com.sap1ens.api

import spray.testkit.ScalatestRouteTest
import com.sap1ens.{BaseSpec, CoreActors, Core}
import spray.http.{MediaTypes, StatusCodes, HttpEntity}
import MediaTypes._

class Example1RoutesSpec extends BaseSpec with ScalatestRouteTest with Api with CoreActors with Core {
  def actorRefFactory = system

  behavior of "Example1 API"

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
}
