package com.unic.gatling.feeders

object HardcodedFeeder {

  val hardcodedFeed = Iterator.continually(Map("firstName" -> "hardcoded first", "lastName" -> "hardCoded last", "age" -> 12, "gender" -> "male"))
}
