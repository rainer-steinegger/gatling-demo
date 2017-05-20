package com.unic.gatling.scenarios

import com.unic.gatling.configurations.Environment
import com.unic.gatling.feeders.InMemoryFeeder
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object RegistrationScenario {

  val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

  val headers_1 = Map("Accept" -> "*/*")

  val registrationScenario = scenario("RegistrationSimulation")
    .feed(InMemoryFeeder.newUnicornHardcodedFeed)
    .during(Environment.testLengthInSeconds.toInt) {
      exec(http("get registration page")
        .get("/registration")
        .headers(headers_0)
        .resources(http("get header image")
          .get("/images/header.png")
          .headers(headers_1),
          http("register")
            .post("/?registration")
            .headers(headers_0)
            .formParam("firstName", "${firstName}")
            .formParam("lastName", "${lastName}")
            .formParam("age", "${age}")
            .formParam("gender", "${gender}")))
        .pause(6)
    }

}
