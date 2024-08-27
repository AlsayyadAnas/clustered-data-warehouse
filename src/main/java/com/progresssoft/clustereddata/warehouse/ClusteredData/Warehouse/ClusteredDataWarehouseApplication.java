package com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse"})
public class ClusteredDataWarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClusteredDataWarehouseApplication.class, args);
	}

}
