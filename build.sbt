name := "mcit-bigdata-bixi-project"

version := "0.1"

scalaVersion := "2.13.5"

val hadoopVersion = "2.6.0"

libraryDependencies += "org.apache.hadoop" % "hadoop-common" % hadoopVersion
libraryDependencies += "org.apache.hadoop" % "hadoop-hdfs" % hadoopVersion

val circeVersion = "0.12.3"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

// https://mvnrepository.com/artifact/io.circe/circe-generic-extras
libraryDependencies += "io.circe" %% "circe-generic-extras" % "0.13.1-M4"

// https://docs.scala-lang.org/overviews/compiler-options/index.html
scalacOptions ++= Seq("-Ymacro-annotations")

resolvers +=
  "Cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos/"

// https://mvnrepository.com/artifact/org.apache.hive/hive-jdbc
libraryDependencies += "org.apache.hive" % "hive-jdbc" % "1.1.0-cdh5.16.2"

idePackagePrefix := Some("ca.mcit.bigdata.project.bixi")
