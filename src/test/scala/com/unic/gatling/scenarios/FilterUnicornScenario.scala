package com.unic.gatling.scenarios

import com.unic.gatling.configurations.Environment
import com.unic.gatling.feeders.{CsvFeeder, InMemoryFeeder}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object FilterUnicornsScenario {

  val filterUnicornsRequest = http("filter unicorns")
    .get("/unicorns?maxAge=${maxAge}&gender=${gender}")
    .check(status is 200)

  val filterUnicornsScenario = scenario("unicorn filtering scenario")
    .feed(CsvFeeder.searchFilterRandomFeed)
    .during(Environment.testLengthInSeconds.toInt) {
      exec(filterUnicornsRequest)
    }
}
