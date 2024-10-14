package com.smartcom.copyexcelfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.smartcom.copyexcelfile.h2"})
public class CopyExcelFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopyExcelFileApplication.class, args);
    }

}
