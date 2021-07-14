package com.me.markdown;

import java.io.File;

/**
 * @author zs
 * @date 2021/7/14.
 */
public class renameToTest {
    public static void main(String[] args) {
        // 目标目录已有一个同名文件
        File file = new File("C:\\Users\\simeitol\\Desktop\\1111.txt");
        File newFile = new File("C:\\Users\\simeitol\\Desktop\\222.txt");
        if (file.renameTo(newFile)){
            System.out.println("success");
        }

    }
}
