package ca.mcit.bigdata.project.bixi
package dumpster

import io.circe.Json
import io.circe.generic.auto._
import io.circe.parser._

import scala.util.Random

final case class System(system_id: String, timezone: String)

final case class SystemInfoJson(data: System)

import scala.io.Source

//with HiveClient

//final case class Feed(name: String, url: String)
//final case class Language(feeds: List[Feed])
//final case class Gbfs(data: Language)

object Playground extends App {

  val gbfsUrl = "https://gbfs.velobixi.com/gbfs/gbfs.json"
  val gbfsJsonSource: String = scala.io.Source.fromURL(gbfsUrl).mkString

//  println(gbfsJsonSource)

  val gbfsJson: GbfsJson = decode[GbfsJson](gbfsJsonSource) match {
    case Right(gbfsJson) => gbfsJson
  }

  val systemInformationUrl: String = gbfsJson.data.en.feeds.find(feed => {feed.name == "system_information"}).get.url
  val stationInformationUrl: String = gbfsJson.data.en.feeds.find(feed => {feed.name == "station_information"}).get.url

  println(systemInformationUrl)
  println(stationInformationUrl)

}


//import io.circe.syntax._
//import ca.mcit.bigdata.project.bixi.model.Station
//import io.circe.Decoder.Result
//import java.io.{BufferedReader, BufferedWriter, InputStream, InputStreamReader, OutputStreamWriter}
//import java.sql.DriverManager
//import io.circe.{Error, _}
//import scala.util.Either
//import io.circe.generic.extras._


//  //  read json files from `resources` directory of project and
//  //  convert the whole file into single string object
//  val systemInformationJsonSource: String = Source.fromResource("system_information.json").getLines.mkString
//  val stationInformationJsonSource: String = Source.fromResource("station_information.json").getLines.mkString

//@ConfiguredJsonCodec @JsonKey("station_id")

//  https://alvinalexander.com/source-code/scala/scala-get-content-rest-url-get-rest-content/
//  val url = "https://gbfs.velobixi.com/gbfs/en/station_information.json"
//  val result = scala.io.Source.fromURL(url).mkString
//  println(result)


//val url = "http://api.hostip.info/get_json.php?ip=12.215.42.19"
//val result = scala.io.Source.fromURL(url).mkString
//println(result)
//
//stmt.execute(
//"""create external table bdss2001_devang_jayswal.ext_system_information(
//  |system_id STRING, timezone STRING
//  |) ROW FORMAT DELIMITED
//  |FIELDS TERMINATED BY ','
//  |STORED AS TEXTFILE
//  |LOCATION '/user/bdss2001/devang.jayswal/final_project/dataset/bixi/CSV/system'
//  """.stripMargin)
//
////  connection.commit()
//stmt.close()
//connection.close()

//val systemInformationJsonSource: String = Source.fromResource("system_information.json").getLines.mkString
//
//  val systemInformationJson = decode[SystemInfoJson](systemInformationJsonSource) match {
//  case Right(systemInformationJson) => systemInformationJson
//  }

//  def matchTest(x: Int): String = x match {
//
//  }


//  case class AS()

//  val objType = obj.getClass
//  def objectToCsv(obj: Any) = obj match {
//    case Station => {
//      val station = obj.asInstanceOf[Station]
//      s"${station.station_id},${station.name},${station.short_name},${station.lat},${station.lon},${station.capacity}\n"
//    }
//    case System => {
//      val system = obj.asInstanceOf[System]
//      s"${system.system_id},${system.timezone}\n"
//    }
//    case _ => "Not supported at this moment"
//  }

//  println(objectToCsv(System("as", "fg")))


/* Scala Pattern Matching */

// FUNCTION
//  def matchTest(x: Int): String = x match {
//    case 1 => "one"
//    case 2 => "two"
//    case _ => "other"
//  }
//  println(matchTest(3))  // prints other
//  println(matchTest(1))  // prints one


// BLOCK
// val i: Int = Random.nextInt(10)
//  // i is an integer
//  i match {
//  case 1  => println("January")
//  case 2  => println("February")
//  case 3  => println("March")
//  case 4  => println("April")
//  case 5  => println("May")
//  case 6  => println("June")
//  case 7  => println("July")
//  case 8  => println("August")
//  case 9  => println("September")
//  case 10 => println("October")
//  case 11 => println("November")
//  case 12 => println("December")
//  // catch the default with a variable so you can print it
//  case whoa  => println("Unexpected case: " + whoa.toString)
//  }
//
//  val x: Int = Random.nextInt(10)
//  val whatever = x match {
//  case 0 => "zero"
//  case 1 => "one"
//  case 2 => "two"
//  case _ => "other"
//  }
//  println(whatever)

/* Scala Pattern Matching */

//  println(systemInformationJson.data)

//  val def stationTOCsv(station: Station) =

//  def stationToCsv(station: Station) =
//    s"${station.station_id},${station.name},${station.short_name},${station.lat},${station.lon},${station.capacity}\n"
//
//  val x = stationInformationJson.data.stations.foreach(station => stationToCsv(station))
//
//  val y =stationInformationJson.data.stations.map(station => stationToCsv(station))

//  println(y)


//import org.apache.hadoop.fs.{FileSystem, Path}
//import org.apache.hadoop.conf.Configuration


// 1. Set the configuration
//val conf = new Configuration()
////  val hadoopConfDir = "E:\\workspace\\IntelliJ\\mcit-bigdata-bixi-project\\src\\main\\resources\\hadoop_configuration\\"
////  conf.addResource(new Path(s"$hadoopConfDir/core-site.xml"))
////  conf.addResource(new Path(s"$hadoopConfDir/hdfs-site.xml"))
//
//Source.fromResource("hadoop_configuration/core-site.xml")
//
//
//


//
//val t: String = getClass.getResource("/hadoop_configuration/core-site.xml").getPath
//  val c  = Source.fromURL(getClass.getResource("/hadoop_configuration/hdfs-site.xml")).mkString
//
//  println(t)
//  println(c)
//
//  //  conf.addResource(new Path(t))
//  //  conf.addResource(new Path(c))
//
//  System.setProperty("HADOOP_USER_NAME", "devang.jayswal")
//
//
//  // 2. Create the client
//  val fs: FileSystem = FileSystem.get(conf)
//
//  val userPath = "/user/bdss2001/devang.jayswal"
//
//  // helper function for creating user path
//  def path(path: String): Path = {
//  new Path(s"$userPath/$path")
//  }
//
//  fs.create(path("course3/enriched_trip11.csv"))
//  fs.close()

//import io.circe.generic.auto._
//import io.circe.parser._
//
//import scala.io.Source

//case class RentalMethod(method: String)
//
//case class Stat(station_id: String, external_id: String, name: String, short_name: String, lat: Double, lon: Double, rental_methods: List[RentalMethod])
//
//case class TempStat(station_id: String, short_name: String)
//
//case class TempDataStat(stations: List[TempStat])
//
//case class Temp(ttl: Int, data: TempDataStat)
//
//case class StationInfoJson(data: TempDataStat)


//implicit class CSVWrapper(val prod: Product) extends AnyVal {
//  def toCSV() = prod.productIterator.map {
//    case Some(value) => value
//    case None => ""
//    case rest => rest
//  }.mkString(",")
//}
//
//val station = Station("1", "Métro Champ-de-Mars (Viger / Sanguinet)", "6001", 45.51016276368593, -73.55663707494385, 31);
//val stationTOCsv =
//s"${station.station_id},${station.name},${station.short_name},${station.lat},${station.lon},${station.capacity}\n"
//
//println(stationTOCsv)


//  def convertToCsv(json: Station) = {
//  }


//  station_information
//  temp2
//  val stationInformationJson: String = Source.fromResource("station_information.json").getLines.mkString
//
//  val whole = decode[StationInfoJson](stationInformationJson) match {
//    case Right(books) => println(s"Here are the books ${books}")
//    case Left(ex) => println(s"Ooops something error ${ex}")
//  }
//  println(decode[StationInfoJson](stationInformationJson))
