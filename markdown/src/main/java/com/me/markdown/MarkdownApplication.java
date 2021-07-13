package com.me.markdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MarkdownApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MarkdownApplication.class, args);
        MarkdownTask markdownTask = context.getBean(MarkdownTask.class);
        markdownTask.mainTask();
    }

}
