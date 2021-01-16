package ru.otus.hadoop.sgribkov

import net.liftweb.json._
import net.liftweb.json.Extraction._
import scala.io.Source
import java.io.{File, PrintWriter}
import scala.collection.mutable.ArrayBuffer


object CountriesSize extends App {

  implicit val formats = DefaultFormats

  val params = new Parameters(args)
  val inputFilePath = params.inputFilePath()
  val outputFilePath = params.outputFilePath()

  case class CountryName(official: String)
  case class CountryInfo(name: CountryName, region: String, capital: List[String], area: Int)
  case class CountrySize(name: String, capital: String, area: Int)

  val inputFileContent = Source.fromFile(inputFilePath).mkString
  val inputJson = parse(inputFileContent)
  val inputJsonElements = inputJson.children

  var countriesFiltered: ArrayBuffer[CountrySize] = ArrayBuffer()

  for (element <- inputJsonElements) {
    val country = element.extract[CountryInfo]
    if (country.region == "Africa") {
      val countrySelected = CountrySize(country.name.official, country.capital.head, country.area)
      countriesFiltered = countriesFiltered :+ countrySelected
    }
  }

  val countriesTop10 = countriesFiltered.sortBy(country => -country.area).take(10)
  val outputJson = prettyRender(decompose(countriesTop10))

  val outputFile = new File(outputFilePath)
  val printWriter = new PrintWriter(outputFile)
  printWriter.println(outputJson)
  printWriter.close()

}
