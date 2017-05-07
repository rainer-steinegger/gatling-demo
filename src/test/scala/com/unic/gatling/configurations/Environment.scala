package com.unic.gatling.configurations

import scala.util.Properties

object Environment {

  val baseUrl = Properties.envOrElse("baseUrl", "http://localhost:8080/")
  val maxResponseTimeInMs = Properties.envOrElse("maxResponseTimeInMs", "500")
}
