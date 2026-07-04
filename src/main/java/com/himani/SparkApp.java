package com.himani;

import org.apache.spark.sql.SparkSession;

public class SparkApp {

    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder()
                .appName("Hello Spark")
                .master("local[*]")
                .getOrCreate();

        System.out.println("Hello Spark");
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        spark.stop();
    }
}