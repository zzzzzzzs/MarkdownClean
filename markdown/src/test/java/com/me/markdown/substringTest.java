package com.me.markdown;

import java.io.File;

/**
 * @author zs
 * @date 2021/7/14.
 */
public class substringTest {
    public static void main(String[] args) {
//        picSuffix.contains(file.getName().substring(file.getName().lastIndexOf(".") + 1))) {
        String path = "C:\\Users\\simeitol\\Desktop\\JavaSE基础总结\\assets\\image-20210312191909-0izl957.png";
        int i = path.lastIndexOf(File.separator);
        String substring = path.substring(0, i);
        System.out.println(substring);
    }
}
