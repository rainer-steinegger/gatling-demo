package com.demo.gatling.feeders

import io.gatling.core.Predef._

object JsonFeeder {

  val newUnicornJsonFeed = jsonFile("new_unicorns.json").random
}
