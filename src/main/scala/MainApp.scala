package ca.mcit.bigdata.project.bixi

// for Json decoding and parsing circe
import io.circe.generic.auto._
import io.circe.parser._

//  for writing file into HDFS
import java.io.{BufferedWriter}

object MainApp extends HadoopClient with HiveClient with App {

  // consume Bixi endpoint for `gbfs.json` and convert Json to String
  val gbfsUrl = "https://gbfs.velobixi.com/gbfs/gbfs.json"
  val gbfsJsonSource: String = scala.io.Source.fromURL(gbfsUrl).mkString

  // decode Gbfs Json string to `GbfsJson` object
  val gbfsJson: GbfsJson = decode[GbfsJson](gbfsJsonSource) match {
    case Right(gbfsJson) => gbfsJson
  }

  //  find url for `system_information` and `station_information` from `GbfsJson` object
  val systemInformationUrl: String = gbfsJson.data.en.feeds.find(feed => {
    feed.name == "system_information"
  }).get.url
  val stationInformationUrl: String = gbfsJson.data.en.feeds.find(feed => {
    feed.name == "station_information"
  }).get.url

  //  call Bixi `system_information` and `station_information` endpoint and get the data
  val systemInformationJsonSource: String = scala.io.Source.fromURL(systemInformationUrl).mkString
  val stationInformationJsonSource: String = scala.io.Source.fromURL(stationInformationUrl).mkString

  //  decode Json string and convert to object that holds whole Json data
  val systemInformationJson: SystemInfoJson = decode[SystemInfoJson](systemInformationJsonSource) match {
    case Right(systemInformationJson) => systemInformationJson
  }
  val stationInformationJson: StationInformationJson = decode[StationInformationJson](stationInformationJsonSource) match {
    case Right(stationInformationJson) => stationInformationJson
  }

  // path of csv files to be created on HDFS
  val stationsCsvPath = "/user/bdss2001/devang.jayswal/final_project/bixi/external_table/stations/stations.csv"
  val systemCsvPath = "/user/bdss2001/devang.jayswal/final_project/bixi/external_table/system/system.csv"

  // create file writer to write into Csv files in HDFS
  val systemCsvFileWriterHdfs: BufferedWriter = createFileOnHdfsAndWrite(systemCsvPath)
  val stationsCsvFileWriterHdfs: BufferedWriter = createFileOnHdfsAndWrite(stationsCsvPath)

  // helper function to convert `System` List object to Csv and write into HDFS with file writer
  def convertSystemToCsvAndWrite(system: System): Unit = {
    val csv = s"${system.system_id},${system.timezone}\n"
    systemCsvFileWriterHdfs.write(csv)
  }

  // helper function to convert `Station` object to Csv and write into HDFS with file writer
  def convertStationToCsvAndWrite(station: Station): Unit = {
    val csv = s"${station.station_id},${station.name},${station.short_name},${station.lat},${station.lon},${station.capacity}\n"
    stationsCsvFileWriterHdfs.write(csv)
  }

  // convert System to Csv and Write into HDFS
  convertSystemToCsvAndWrite(systemInformationJson.data)
  systemCsvFileWriterHdfs.flush()
  systemCsvFileWriterHdfs.close()

  // convert Stations to Csv and Write to HDFS
  stationInformationJson.data.stations.foreach(station => convertStationToCsvAndWrite(station))
  stationsCsvFileWriterHdfs.flush()
  stationsCsvFileWriterHdfs.close()

  //  dropping database and all the tables if they exist
  stmt.execute("create database IF NOT EXISTS bdss2001_devang_jayswal")
  stmt.execute("DROP TABLE IF EXISTS bdss2001_devang_jayswal.ext_system_information")
  stmt.execute("DROP TABLE IF EXISTS bdss2001_devang_jayswal.ext_station_information")
  stmt.execute("DROP TABLE IF EXISTS bdss2001_devang_jayswal.station_information")

  //  create `ext_system_information` table
  stmt.execute(
    """create external table bdss2001_devang_jayswal.ext_system_information(
      |system_id STRING, timezone STRING
      |) ROW FORMAT DELIMITED
      |FIELDS TERMINATED BY ','
      |STORED AS TEXTFILE
      |LOCATION '/user/bdss2001/devang.jayswal/final_project/dataset/bixi/CSV/system'
      """.stripMargin)

  //  create `ext_station_information` table
  stmt.execute(
    """create external table bdss2001_devang_jayswal.ext_station_information(
      |station_id STRING, name STRING, short_name STRING, lat DOUBLE, lon DOUBLE, capacity INT
      |) ROW FORMAT DELIMITED
      |FIELDS TERMINATED BY ','
      |STORED AS TEXTFILE
      |LOCATION '/user/bdss2001/devang.jayswal/final_project/dataset/bixi/CSV/stations'
      """.stripMargin)

  //  create `station_information` table
  stmt.execute(
    """create table bdss2001_devang_jayswal.station_information
      |ROW FORMAT DELIMITED
      |FIELDS TERMINATED BY ','
      |STORED AS PARQUET
      |AS SELECT * from
      |bdss2001_devang_jayswal.ext_system_information
      |CROSS JOIN bdss2001_devang_jayswal.ext_station_information""".stripMargin)

  stmt.close()
  connection.close()

  hdfs.close()
}

//  conf.addResource(new Path(getClass.getResource("").getPath)
//  conf.addResource(new Path(Source.fromResource("hadoop_configuration/hdfs-site.xml").mkString))

//  println(new Path(Source.fromResource("hadoop_configuration/core-site.xml").mkString))


//    val stationTOCsv =
//      s"${station.station_id},${station.name},${station.short_name},${station.lat},${station.lon},${station.capacity}\n"

//    val fields: Array[String] = line.split(",", -1)
//    val wheelChairRaw: Int = fields(6).toInt
//
//    def writeTrip(wheelChair: Boolean): Unit = {
//      stationsCsvFile.write(s"${fields(2)},${fields(1)},${fields(0).toInt},${fields(3)},$wheelChair\n")
//    }
//
//    if (wheelChairRaw.equals(1)) writeTrip(false)
//    else if (wheelChairRaw.equals(2)) writeTrip(true)
//    else null
//  }

//  val stationInformationJson = decode[StationInformationJson](stationInformationJsonSource) match {
//    case Right(stationInformationJson) => stationInformationJson
//  }


//  println(stationInformationJson.data.stations)
//  stationInformationJson.data.stations.find(Station)


//  val stationInformation: Either[Error, StationInformationJson] =

//  Source.fromResource("hadoop_configuration/core-site.xml").mkString