package com.me.markdown;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;

import java.io.File;
import java.util.List;

/**
 * @author zs
 * @date 2021/11/24
 */
public class FileReadTest {
    public static void main(String[] args) {
        List<File> files = FileUtil.loopFiles("C:\\Users\\simeitol\\Desktop\\Algorithm");
        files.forEach(file -> System.out.println(file.getName()));
    }
}
