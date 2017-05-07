package com.unic.gatling.simulations

import com.unic.gatling.configurations.{Environment, Headers}
import com.unic.gatling.scenarios.UnicornScenarios
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class UnicornSimulations extends Simulation {

  val httpConf = http
    .baseURL(Environment.baseUrl)
    .contentTypeHeader(Headers.contentType)
    .acceptHeader(Headers.acceptType)

  setUp(UnicornScenarios.createAndAccessUnicornsScenario.inject(atOnceUsers(40)))
    .protocols(httpConf)
    .assertions(
      global.responseTime.max.lte(Environment.maxResponseTimeInMs.toInt)
    )
}

