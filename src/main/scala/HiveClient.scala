package ca.mcit.bigdata.project.bixi

import org.apache.hive.jdbc.HiveDriver

import java.sql.DriverManager

trait HiveClient {
  Class.forName(classOf[HiveDriver].getName)
  val connectionString: String = "jdbc:hive2://quickstart.cloudera:10000/;user=devang.jayswal;"
  val connection = DriverManager.getConnection(connectionString)
  connection.setAutoCommit(true)
  val stmt = connection.createStatement()

}
