package ca.mcit.bigdata.project.bixi
package dumpster

import io.circe.generic.semiauto._
import io.circe.parser._
import io.circe.{Decoder, Encoder}

import scala.io.Source

case class Product(stationId: String, name: String, shortName: String, lat: Double, lon: Double, capacity: Int)

object Product {
  implicit val decoder: Decoder[Product] = deriveDecoder[Product]
  implicit val encoder: Encoder[Product] = deriveEncoder[Product]

  def main(args: Array[String]): Unit = {
    val inputString: String =
      """
        |{
        |   "productId": 111112222222,
        |   "price": 23.45,
        |   "countryCurrency": "USD",
        |   "inStock": true
        |}
        |""".stripMargin

    val stationInformationJson: String = Source.fromResource("station_information.json").getLines.mkString

    decode[Product](stationInformationJson) match {
      case Right(productObject) => println(productObject)
      case Left(ex) => println(s"Ooops some errror here ${ex}")
    }
  }
}