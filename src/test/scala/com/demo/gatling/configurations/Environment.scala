package com.demo.gatling.configurations

import scala.util.Properties

object Environment {

  val baseUrl = Properties.envOrElse("baseUrl", "http://localhost:8080/")
  val testLengthInSeconds = Properties.envOrElse("testLengthInSeconds", "60")
  val maxResponseTimeInMs = Properties.envOrElse("maxResponseTimeInMs", "2000")
}
