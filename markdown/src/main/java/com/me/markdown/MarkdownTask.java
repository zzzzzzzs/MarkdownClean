package com.me.markdown;

import com.me.markdown.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MarkdownTask {
    public void mainTask() {
        log.info("这是一个测试程序");
//        AppConfig.searchKeywords.
        log.info(AppConfig.searchKeywords[0]);
    }
}
