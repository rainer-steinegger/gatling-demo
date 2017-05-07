package com.unic.gatling.scenarios

import com.unic.gatling.feeders.HardcodedFeeder
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

object CreateAndAccessUnicornsScenarios {

  val resourcesPath = "/unicorns"

  val createUnicornScenario = http("create unicorn")
    .post(resourcesPath)
    .body(StringBody("""{"firstName": "${firstName}", "lastName": "${lastName}", "age": ${age}, "gender": "${gender}"}"""))
    .check(status is 201, jsonPath("$.id").saveAs("unicornId"))

  val accessUnicornScenario = http("access unicorn")
    .get(resourcesPath + "/${unicornId}")
    .check(status is 200)

  val createAndAccessUnicornsScenario = scenario("unicorn creation and access scenario")
    .feed(HardcodedFeeder.hardcodedFeed)
    .during(5) {
      exec(createUnicornScenario)
        .repeat(Random.nextInt(5) + 1) {
          exec(accessUnicornScenario)
        }
    }
}
