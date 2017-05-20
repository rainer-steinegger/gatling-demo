package com.unic.gatling.simulations

import com.unic.gatling.configurations.{Environment, Headers}
import com.unic.gatling.scenarios.{RegistrationScenario, FilterScenario}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class StandardSimulation extends Simulation {

  val httpConf = http
    .baseURL(Environment.baseUrl)
    .contentTypeHeader(Headers.contentType)
    .acceptHeader(Headers.acceptType)

  val scenarios = List(
    RegistrationScenario.registrationScenario.inject(
      atOnceUsers(1)),
    FilterScenario.filterUnicornsScenario.inject(
      atOnceUsers(15), constantUsersPerSec(2) during 5)
  )

  setUp(scenarios)
    .protocols(httpConf)
    .assertions(
      global.responseTime.max.lte(Environment.maxResponseTimeInMs.toInt)
    )
}

