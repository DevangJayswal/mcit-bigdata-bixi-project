# mcit-bigdata-bixi-project
MCIT Big Data final project as part of curriculum.

https://docs.google.com/document/d/15t6oJcHPbZEYvve1_2DVP6gLZyKY91nbC4RnZcXTdeQ/edit?usp=sharing

# JAVRO, a friendly editor to create AVRO schemas
https://javro.github.io/

# DESCRIPTION
## Skillset: 
HDFS, Hive, JSON, CSV, Parquet, Scala, SQL, REST API, Spark (Core, SQL, Streaming), Kafka, Confluent Schema Registry, Avro, Docker

## Sprint 1: 
Created a data warehouse on HDFS. Analyzed the JSON source data to better understand data structure and relationship between files. Data modelling using UML diagram. Downloaded a feed of JSON data and load them into a staging tables on Hive

## Sprint 2: 
Created the dimensions to be used as reference data in Hive/HDFS. Implemented a program to run ETL for JSON files. This process includes converting JSON files to CSV and writing them in a directory on the HDFS. Used JSON library Circe to parse JSON documents into CSV records. Created external tables to enable Hive to access CSV files on HDFS. Used Hive JDBC to enrich and execute a join between two external tables and load data into the final managed table with format of Parquet.

## Sprint 3: 
Created pipeline to process historical CSV records using Spark and Kafka. Created two Kafka topics - one for producing CSV records and one for producing data in Avro Format after enrichment. Used Kafka console producer to read the CSV data in small batches and produced it onto relevant Kafka Topic. Created the Avro schema that represents final enriched data and registered that schema under respective Subject in Confluent Schema Registry. Implemented a Spark application to curate CSV records with the Parquet records. Read the Parquet file from HDFS and convert it into Data Frame. Used Spark Streaming with Kafka to consume CSV records from Kafka topic in the form of stream. The moment we receive the stream of a CSV record, convert the stream into Data Frame. After getting data into Data Frame, used Spark SQL to enrich them and produced the enriched data to Kafka Topic in Avro Generic format.
