package com.sina.sina;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SinaApplication {
    
    public static void main(String[] args) {
        /*try {
            System.setOut(new PrintStream(new FileOutputStream("/home/mphj/Logs/visitor-manager/" + new Timestamp(Calendar.getInstance().getTime().getTime()).toString() + "_" + UUID.randomUUID().toString() + ".log")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SinaApplication.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        SpringApplication.run(SinaApplication.class, args);
    }

    @PostConstruct
    void setupTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tehran"));
    }
}
