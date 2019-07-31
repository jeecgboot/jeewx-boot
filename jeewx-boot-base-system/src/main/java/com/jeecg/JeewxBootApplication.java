package com.jeecg;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"com.jeecg.*.*.dao","com.jeecg.*.*.*.dao"})
public class JeewxBootApplication {
	public final static Logger log = LoggerFactory.getLogger(JeewxBootApplication.class);

    public static void main(String[] args) throws UnknownHostException {
    	ConfigurableApplicationContext application = SpringApplication.run(JeewxBootApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n----------------------------------------------------------\n\t" +
            "Application Jeewx-Boot is running! Access URLs:\n\t" +
            "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
            "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
            "----------------------------------------------------------");
    }

}