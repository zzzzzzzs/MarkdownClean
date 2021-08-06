package com.me.markdown.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author zs
 * @date 2021/7/13.
 */
@Configuration
public class AppConfig {

    // 图片路径
    public static HashSet<String> picPaths;
    // 笔记的根路径
    public static String noteRootPath;

    public static final String[] checkArray(String str) {
        if (str == null) throw new RuntimeException("路径为空");

        String[] split = str.split(",");
        return split;
    }

    @Value("${picPaths}")
    public void setPicPaths(String str) {
        if (str == null) throw new RuntimeException("空，请检查");
        String[] split = str.split(",");

        picPaths = new HashSet<>(Arrays.asList(str.split(",")));
    }

    @Value("${noteRootPath}")
    public void setNoteRootPath(String str) {
        if (str == null) throw new RuntimeException("路径为空");
        AppConfig.noteRootPath = str;
    }

}
