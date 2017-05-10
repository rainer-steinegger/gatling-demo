package com.unic.gatling.scenarios

import com.unic.gatling.configurations.Environment
import com.unic.gatling.feeders.InMemoryFeeder
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

object CreateAndAccessUnicornsScenario {


  val createUnicornRequest = http("create unicorn")
    .post("/unicorns")
    .body(StringBody("""{"firstName": "${firstName}", "lastName": "${lastName}", "age": ${age}, "gender": "${gender}"}"""))
    .check(status is 201, jsonPath("$.id").saveAs("unicornId"))

  val accessUnicornRequest = http("access unicorn")
    .get("/unicorns/${unicornId}")
    .check(status is 200)

  val createAndAccessUnicornsScenario = scenario("unicorn creation and access scenario")
    .feed(InMemoryFeeder.newUnicornHardcodedFeed)
    .during(Environment.testLengthInSeconds.toInt) {
      exec(createUnicornRequest)
        .repeat(Random.nextInt(5) + 1) {
          exec(accessUnicornRequest)
        }
    }
}
