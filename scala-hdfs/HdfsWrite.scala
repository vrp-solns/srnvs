package com.example

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.PrintWriter;


object HdfsWrite {
    def main(args : Array[String]) {
        println( "Trying to write to HDFS..." )
        val conf = new Configuration()
        // avroStr - contents of this variable will be written into a file
        val avroStr = "Avro String here"
        // set this property fs.defaultFS, according to your env
        //conf.set("fs.defaultFS", "hdfs://localhost/") fs.maprfs.impl=com.mapr.fs.MapRFileSystem
        //conf.set("fs.defaultFS", "maprfs:///mapr/demo.mapr.com/")
        //conf.set("fs.hdfs.impl", classOf[org.apache.hadoop.hdfs.DistributedFileSystem].getName);
        conf.set("fs.maprfs.impl", classOf[com.mapr.fs.MapRFileSystem].getName);
        conf.set("fs.file.impl", classOf[org.apache.hadoop.fs.LocalFileSystem].getName);
        val fs= FileSystem.get(conf)
        // /tmp/avro.txt is the file where the data will be written
        //val output = fs.create(new Path("/tmp/avro.txt"))
        
        try {
            val os = fs.create(new Path("/tmp/avro.txt"))
            os.write(avroStr.getBytes)
        }
        catch {
            case e: Exception => e.printStackTrace
        }
        println("Done!")
        }
}