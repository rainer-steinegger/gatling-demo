package com.citrix.gatling.simulations

import com.citrix.gatling.configurations.{Environment, Headers}
import com.citrix.gatling.scenarios.BaseScenario
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SampleGatlingSimulation extends Simulation {

  val httpConf = http
    .baseURL(Environment.baseUrl)
    .contentTypeHeader(Headers.contentType)
    .acceptHeader(Headers.acceptType)

  val scenarios = List(
    BaseScenario.createAndAccessResources.inject(
      atOnceUsers(40),
      rampUsersPerSec(1) to 5 during 60
    )
  )

  setUp(scenarios)
    .protocols(httpConf)
    .assertions(
      global.responseTime.max.lte(Environment.maxResponseTimeInMs.toInt)
    )
}

