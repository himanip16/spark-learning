package com.himani;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;

public class SparkApp {

    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder()
                .appName("CSV Demo")
                .master("local[*]")
                .getOrCreate();

        Dataset<Row> orders = spark.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .csv("data/orders.csv");
        orders.printSchema();
        orders.show();
        Dataset<Row> expensiveOrders =
                orders.filter(col("amount").gt(150));
        expensiveOrders.printSchema();
        expensiveOrders.show();
        expensiveOrders.write()
                .option("header", "true")
                .mode("overwrite")
                .csv("output/orders");
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        spark.stop();
    }
}