package com.me.markdown.config;

import cn.hutool.core.io.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author zs
 * @date 2021/7/13.
 */
@Configuration
public class AppConfig {

    // 图片路径
    public static HashSet<String> picFileName;
    // 笔记的根路径
    public static String noteRootPath;
    // 图片后缀
    public static HashSet<String> picSuffixs;
    // 文件后缀
    public static HashSet<String> fileSuffixs;

    public static final String[] checkArray(String str) {
        if (str == null) throw new RuntimeException("路径为空");

        String[] split = str.split(",");
        return split;
    }

    @Value("${picPaths}")
    public void setPicPaths(String str) {
        if (str == null) throw new RuntimeException("空，请检查");
        String[] split = str.split(",");

        picFileName = new HashSet<>(Arrays.asList(str.split(",")));
    }

    @Value("${picSuffixs}")
    public void setPicSuffixs(String str) {
        if (str == null) throw new RuntimeException("空，请检查");
        String[] split = str.split(",");

        picSuffixs = new HashSet<>(Arrays.asList(str.split(",")));
    }

    @Value("${noteRootPath}")
    public void setNoteRootPath(String str) {
        if (str == null) throw new RuntimeException("路径为空");
        // 修复路径
        AppConfig.noteRootPath = FileUtil.normalize(str);
    }

    @Value("${fileSuffixs}")
    public void setFileSuffixs(String str) {
        if (str == null) throw new RuntimeException("路径为空");
        String[] split = str.split(",");
        fileSuffixs = new HashSet<>(Arrays.asList(str.split(",")));
    }

}
