package com.citrix.gatling.configurations

import scala.util.Properties

object Environment {

  val baseUrl = Properties.envOrElse("baseUrl", "http://localhost:8080/")
  val domainName = Properties.envOrElse("prefix", setupApplication())
  val maxResponseTimeInMs = Properties.envOrElse("maxResponseTimeInMs", "500")


  def setupApplication(): String = {

    //TODO: setup code
    return "prefix";
  }
}
