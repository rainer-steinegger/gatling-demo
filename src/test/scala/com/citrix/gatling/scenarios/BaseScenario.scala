package com.citrix.gatling.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

object BaseScenario {

  val resourcesPath = "/resources"

  val createResource = http("create resource")
    .post(resourcesPath)
    .check(status is 201, jsonPath("$.id").saveAs("resourceId"))

  val accessResource = http("access resource")
    .get(resourcesPath + "/${resourceId}")
    .check(status is 200)

  val createAndAccessResources = scenario("resource creation and access scenario")
    .during(60) {
      exec(createResource)
        .repeat(Random.nextInt(5) + 1) {
          exec(accessResource)
        }
    }

}
