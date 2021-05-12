package ca.mcit.bigdata.project.bixi

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

import java.io.{BufferedWriter, OutputStreamWriter}

trait HadoopClient {

  val hadoopConf = new Configuration()

  val coreSiteXmlPath: String = getClass.getResource("/hadoop_configuration/core-site.xml").getPath
  val hdfsSiteXmlPath: String = getClass.getResource("/hadoop_configuration/hdfs-site.xml").getPath

  hadoopConf.addResource(new Path(coreSiteXmlPath))
  hadoopConf.addResource(new Path(hdfsSiteXmlPath))

  java.lang.System.setProperty("HADOOP_USER_NAME", "devang.jayswal")

  // create the client, creating Hadoop filesystem using configuration
  val hdfs: FileSystem = FileSystem.get(hadoopConf)

  // helper function to create file on HDFS with writer
  def createFileOnHdfsAndWrite(path: String): BufferedWriter = {
    new BufferedWriter(new OutputStreamWriter(hdfs.create(new Path(path))))
  }

}
