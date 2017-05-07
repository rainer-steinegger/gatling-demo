package com.unic.gatling.feeders

import io.gatling.core.Predef._

object CsvFeeder {
  
  val newUnicornCsvFeed = csv("new_unicorns.csv").random
}
