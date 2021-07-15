package com.me.markdown;

import com.me.markdown.config.AppConfig;

import java.io.File;

/**
 * @author zs
 * @date 2021/7/15.
 */
public class mkdirTest {
    public static void main(String[] args) {
        File rootPathTmp = new File("C:\\Users\\simeitol\\Desktop\\aaa\\bbb\\ccc");
        if(!rootPathTmp.exists()){
            rootPathTmp.mkdirs();
        }
    }
}
