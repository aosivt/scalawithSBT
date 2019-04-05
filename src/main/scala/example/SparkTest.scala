package example

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

import scala.xml.MetaData



object SparkTest extends App {
//  val conf = new SparkConf().setAppName("test").setMaster("local")
  val sparkSession = SparkSession.builder.appName("test").master("local").getOrCreate()
  val context = sparkSession.sparkContext

  val data = Array(1,2,3,4,5,6)
  val distData = context.parallelize(data)

  val scheme = StructType(Seq(StructField("id", DataTypes.IntegerType)))

  val rows = distData.map(m=>Row(m))
  val distDataDF = sparkSession.createDataFrame(rows, scheme)
    //  val distDataDF2 = distData.map
    //  distDataDF.printSchema()

  distDataDF.show(2)
  val filter = distData.filter(f => f.equals(3))
  println(filter.reduce((x, y) => x + y))
}
