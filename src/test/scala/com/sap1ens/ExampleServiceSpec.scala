package com.sap1ens


class ExampleServiceSpec extends BaseActorSpec {

  import com.sap1ens.ExampleService._

  behavior of "Example Service"

  it should "return the same message" in {
    val exampleService = system.actorOf(ExampleService.props("test property"), "ExampleService")

    exampleService ! ExampleMessage("ping")

    expectMsg("ping")
  }

}
