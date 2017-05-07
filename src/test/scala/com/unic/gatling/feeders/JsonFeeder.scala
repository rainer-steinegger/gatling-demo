package com.unic.gatling.feeders

import io.gatling.core.Predef._

object JsonFeeder {

  val newUnicornJsonFeed = jsonFile("new_unicorns.json").random
}
