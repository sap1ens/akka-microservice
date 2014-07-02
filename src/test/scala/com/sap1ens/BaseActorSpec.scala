package com.sap1ens

import akka.testkit.{ImplicitSender, TestKit}
import akka.actor.ActorSystem

abstract class BaseActorSpec extends TestKit(ActorSystem()) with BaseSpec with Core with ImplicitSender