package com.demo.gatling.simulations

import com.demo.gatling.configurations.{Environment, Headers}
import com.demo.gatling.scenarios.{CreateAndAccessUnicornsScenario, FilterUnicornsScenario}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class StandardSimulation extends Simulation {

  val httpConf = http
    .baseURL(Environment.baseUrl)
    .contentTypeHeader(Headers.contentType)
    .acceptHeader(Headers.acceptType)

  val scenarios = List(
    CreateAndAccessUnicornsScenario.createAndAccessUnicornsScenario.inject(
      atOnceUsers(1)),
    FilterUnicornsScenario.filterUnicornsScenario.inject(
      atOnceUsers(5), constantUsersPerSec(1) during 5)
  )

  setUp(scenarios)
    .protocols(httpConf)
    .assertions(
      global.responseTime.max.lte(Environment.maxResponseTimeInMs.toInt)
    )
}

