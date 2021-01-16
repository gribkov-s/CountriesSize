package ru.otus.hadoop.sgribkov

import org.rogach.scallop._

class Parameters(arguments: Seq[String]) extends ScallopConf(arguments) {

  val inputFilePath = opt [String] (required = true, name = "input-file-path")
  val outputFilePath = opt [String] (required = true, name = "output-file-path")
  verify()
}
