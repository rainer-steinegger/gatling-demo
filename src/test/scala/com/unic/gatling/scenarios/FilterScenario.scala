package com.unic.gatling.scenarios

import com.unic.gatling.configurations.Environment
import com.unic.gatling.feeders.{CsvFeeder, InMemoryFeeder}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object FilterScenario {

  val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

  val filterUnicornsScenario = scenario("Filter Unicorns")
    .feed(InMemoryFeeder.searchFilterRandomFeed)
    .during(Environment.testLengthInSeconds.toInt) {
      exec(http("all")
        .get("/")
        .headers(headers_0))
        .pause(3)
        .exec(http("filter")
          .get("/?maxAge=${maxAge}&gender=${gender}")
          .headers(headers_0)
          .check(status is 200))
        .pause(3)
        .exec(http("filter")
          .get("/?maxAge=${maxAge}&gender=${gender}")
          .headers(headers_0)
          .resources(http("details")
            .get("/details/2")
            .headers(headers_0)))
        .pause(4)
        .exec(http("details")
          .get("/details/3")
          .headers(headers_0))
    }
}
