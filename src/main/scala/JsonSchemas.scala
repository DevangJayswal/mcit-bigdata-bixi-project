package ca.mcit.bigdata.project.bixi

//  system_information
final case class System(system_id: String, timezone: String)

final case class SystemInfoJson(data: System)

//  station_information
final case class Station(station_id: String, name: String, short_name: String, lat: Double, lon: Double, capacity: Int)

final case class Stations(stations: List[Station])

final case class StationInformationJson(data: Stations)

// gbfs
final case class Feed(name: String, url: String)

final case class Feeds(feeds: List[Feed])

final case class Language(en: Feeds)

final case class GbfsJson(data: Language)
