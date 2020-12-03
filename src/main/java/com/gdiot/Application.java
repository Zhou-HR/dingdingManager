package com.gdiot;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.gdiot.ssm.service.AsyncService;
import com.gdiot.ssm.task.DataSenderTask;
import com.gdiot.ssm.util.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@MapperScan("com.gdiot.ssm.dao")
@EnableScheduling
public class Application {

    @Autowired
    private static AsyncService asyncService;
    private static ApplicationContext context;

    public static void main(String[] args) {
//		SpringApplication.run(SpMeterDataApplication.class, args);
        log.info("-------------SpMeterDataApplication run----------------");

        SpringApplication application = new SpringApplication(Application.class);
        context = application.run(args);

        SpringContextUtils mSpringContextUtils = new SpringContextUtils();
        mSpringContextUtils.setApplicationContext(context);

    }
}
