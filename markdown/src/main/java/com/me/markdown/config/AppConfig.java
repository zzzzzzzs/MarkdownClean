package com.me.markdown.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author zs
 * @date 2021/7/13.
 */
@Configuration
public class AppConfig {

    public static String[]  searchKeywords;
    public static Integer mock_count=1000;
    public static final  String[] checkArray(String str){

        if(str==null){
            throw new RuntimeException("搜索词为空");
        }

        String[] split = str.split(",");
        return  split;

    }
    @Value("${mock.search.keyword}")
    public void setSearchKeywords(String keywords){
        AppConfig.searchKeywords= checkArray(keywords);
    }

}
