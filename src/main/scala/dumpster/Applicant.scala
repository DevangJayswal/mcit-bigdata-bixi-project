package ca.mcit.bigdata.project.bixi
package dumpster

import io.circe.{Decoder, HCursor, parser}

import scala.io.Source

/**
  * In this example, we try to decode manually on the Applicant objects. Using the get[String]("name") is the same as
  * downField("age").as[Int] by getting the attributes in the parenthesis as a bracket. With doing manually decoding,
  * we can omit some of the attributes that we don't need and just get the attributes that we need.
  *
  */
case class Applicant(stationId: String, name: String, shortName: String, lat: Double, lon: Double, capacity: Int)

object Applicant {
  // here we are actually casting the return value to Decode

  implicit val decoder: Decoder[Applicant] = (hCursor: HCursor) =>
    for {
      stationId <- hCursor.downField("data").downField("stations").downArray.downField("station_id").as[String]
      name <- hCursor.downField("data").downField("stations").downArray.downField("name").as[String]
      shortName <- (hCursor.downField("data").downField("stations").downArray.downField("short_name").as[String])
      lat <- hCursor.downField("data").downField("stations").downArray.downField("lat").as[Double]
      lon <- hCursor.downField("data").downField("stations").downArray.downField("lon").as[Double]
      capacity <- hCursor.downField("data").downField("stations").downArray.downField("capacity").as[Int]
    } yield new Applicant(stationId, name, shortName, lat, lon, capacity)

  def main(args: Array[String]): Unit = {
    val inputString =
      """
        |[
        | {"name": "Jane Doe", "age":26, "phoneNumber":"512222222"},
        | {"name": "Petter Pan", "age":55, "phoneNumber":"214553356"},
        | {"name": "Jason Mamoa", "age":33, "phoneNumber":"2111112234", "email":"jasonMamoa@gmail.com"}
        |]
        |""".stripMargin

    val stationInformationJson: String = Source.fromResource("station_information.json").getLines.mkString

    parser.decode[List[Applicant]](stationInformationJson) match {
      case Right(applicants) => println(applicants)
      case Left(ex) => println(s"Oops something is wrong with decoding value ${ex}")
    }
  }
}