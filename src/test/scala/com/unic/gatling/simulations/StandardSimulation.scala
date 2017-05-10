package com.unic.gatling.simulations

import com.unic.gatling.configurations.{Environment, Headers}
import com.unic.gatling.scenarios.{CreateAndAccessUnicornsScenario, FilterUnicornsScenario}
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
      atOnceUsers(15), rampUsersPerSec(1) to 3 during 15)
  )

  setUp(scenarios)
    .protocols(httpConf)
    .assertions(
      global.responseTime.max.lte(Environment.maxResponseTimeInMs.toInt)
    )
}

