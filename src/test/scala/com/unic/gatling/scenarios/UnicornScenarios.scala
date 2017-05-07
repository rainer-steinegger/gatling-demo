package com.unic.gatling.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

object UnicornScenarios {

  val resourcesPath = "/unicorns"

  val createUnicornScenario = http("create unicorn")
    .post(resourcesPath)
    .body(StringBody("""{"firstName": "hardcoded", "lastName": "hardcoded", "age": "100", "gender": "male"}"""))
    .check(status is 201, jsonPath("$.id").saveAs("unicornId"))

  val accessUnicornScenario = http("access unicorn")
    .get(resourcesPath + "/${unicornId}")
    .check(status is 200)

  val createAndAccessUnicornsScenario = scenario("unicorn creation and access scenario")
    .during(5) {
      exec(createUnicornScenario)
        .repeat(Random.nextInt(5) + 1) {
          exec(accessUnicornScenario)
        }
    }

}
