package com.unic.gatling.feeders

import scala.util.Random

object InMemoryFeeder {

  val newUnicornHardcodedFeed = Iterator.continually(Map("firstName" -> "hardcoded first",
                                                          "lastName" -> "hardCoded last",
                                                          "age" -> 12,
                                                          "gender" -> "male"))

  val searchFilterRandomFeed = Iterator.continually(Map("maxAge" -> Random.nextInt(500), "gender" -> randomGender))

  private def randomGender: String = {
    if (Random.nextBoolean())
      return "male"
    else
      return "female"
  }
}
