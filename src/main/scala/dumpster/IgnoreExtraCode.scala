package ca.mcit.bigdata.project.bixi
package dumpster

sealed trait Foo

case class Qux(i: Int, d: Option[Double]) extends Foo

case class Value(bar: Boolean, baz: Option[Double])


object IgnoreExtraCode {



  //  val temp: Unit = parse(stationInformationJson) match {
  //    case Left(failure) => println("Invalid JSON :(")
  //    case Right(json) => println("Yay, got some JSON!")
  //  }



  //  stationId: Int, name: String, shortName: Option[String], lat: Double, lon: Double, capacity: Int

  //  val stations: Result[String] = stationInformationCursor.downField("data").downField("stations").downArray.as[String]
//  val stations = stationInformationCursor
//    .downField("data")
//    .downField("stations").downArray


  //  val allStation = stations.values.

  //  val station =
//  implicit val decodeValue: Decoder[Station] = new Decoder[Station] {
//    final def apply(stations: HCursor): Decoder.Result[Station] =
//      for {
//        //    stationId <- temp("station_id", classOf[String])
//        stationId <- stations.downField("station_id").as[String]
//        name <- stations.downField("name").as[String]
//        shortName <- (stations.downField("short_name").as[String])
//        lat <- stations.downField("lat").as[Double]
//        lon <- stations.downField("lon").as[Double]
//        capacity <- stations.downField("capacity").as[Int]
//      } yield {
//        new Station(stationId, name, shortName, lat, lon, capacity)
//      }
//  }
//
//  parser.decode[List[Station]](stationInformationJson) match {
//    case Right(applicants) => println(applicants)
//    case Left(ex) => println(s"Oops something is wrong with decoding value ${ex}")
//  }

  //  println(stations.asJson)
  //    .downField("ttl").as[Int]
  //    .downField("values")
  //    .downField("baz").as[Double]


  //  println(station match {
  //    case Left(value) =>
  //    case Right(value) => value.
  //  })

  //  println(temp("lat", classOf[Double]))
  //  def temp(f: String, t: Class[_]) =  {
  //    stations.downField(f).as[t.type]
  //  }

  //  def sample(c: Class[_]) = {
  //    println("Get a class " + c.getTypeName)
  //    stations.downField(f).as[c.]
  //  }
  //  sample(classOf[Int])

  // getting the value of `baz`
  //  val baz: Decoder.Result[Double] =
  //    cursor.downField("values").
  //      downField("baz").as[Double]
  // baz: Decoder.Result[Double] = Right(100.001)


  //  implicit val decodeValue: Decoder[Value] = new Decoder[Value] {
  //    final def apply(c: HCursor): Decoder.Result[Value] =
  //      for {
  //        bar <- c.downField("bar").as[Boolean]
  //        baz <- c.downField("baz").as[Option[Double]]
  //      } yield {
  //        new Value(bar, baz)
  //      }
  //  }

  //  println(decodeValue)

  //  println(value.getOrElse())


  //  println(baz.getOrElse())

  //  parse(lines) match {
  //    case Left(failure) => println("Invalid JSON :(")
  //    case Right(json) => println("Yay, got some JSON!")
  //  }


  //   parseResult: Either[ParsingFailure, Json] = Right(
  //     JObject(
  //       object[foo -> "bar",baz -> 123,list of stuff -> [
  //     4,
  //     5,
  //     6
  //   ]]
  //     )
  //   )


  // printing iterator
  //  linesIterator foreach println


  //  validating `stationInformationJson`
//  val parsedStationInformation: Either[ParsingFailure, Json] = parse(stationInformationJson)

  //  converting parsed Json to Json document
//  val stationInformationDoc: Json = parsedStationInformation.getOrElse(Json.Null)
//  //  creating cursor for traversing Json document
//  val stationInformationCursor: HCursor = stationInformationDoc.hcursor

  //  getting `values` Object
  //  val value: Result[Value] =
  //    cursor.downField("values").as[Value]

//  val linesIterator: Iterator[String] = Source.fromResource("temp.json").getLines


  //"values": {
  //  "bar": true,
  //  "baz": 100.002,
  //  "qux": [
  //  "a",
  //  "b"
  //  ]
  //  }



  //  val parseResultJSON: Json = parseResult.asJson

  //  println(lines)
//  val rawJson: String =
//    """
//    {
//      "foo": "bar",
//      "baz": 123,
//      "list of stuff": [ 4, 5, 6 ]
//    }
//    """


//  val foo: Foo = Qux(13, Some(14.0))
//
//
//  val json: Json = foo.asJson // .noSpaces
//  println(json)


}
