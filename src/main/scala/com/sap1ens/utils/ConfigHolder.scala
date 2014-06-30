package com.sap1ens.utils

import com.typesafe.config.ConfigFactory

trait ConfigHolder {
  val config = ConfigFactory.load()
}
