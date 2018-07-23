package com.demo.gatling.feeders

import io.gatling.core.Predef._

object CsvFeeder {
  
  val newUnicornCsvFeed = csv("new_unicorns.csv").random

  val searchFilterRandomFeed = csv("filter_unicorns.csv").circular
}
