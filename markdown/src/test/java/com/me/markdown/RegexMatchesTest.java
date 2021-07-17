package com.me.markdown;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zs
 * @date 2021/7/13.
 */
public class RegexMatchesTest {
    public static void main(String[] args) {
//        String str = "![image](assets/20210224165941-e3i7qzj.png \"image\")";
//        String pattern = "!\\[.*\\]\\(.+\\)";
//
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(str);
//        System.out.println(m.matches());

//        [![ezgif.com-optimize(3).gif](https://static.cnbetacdn.com/article/2020/1007/daee539e7b87dee.gif)](https://static.cnbetacdn.com/article/2020/1007/daee539e7b87dee.gif)



        Pattern pattern1 = Pattern.compile(".*?]\\((.*?)\\).*?");
        Matcher matcher1 = pattern1.matcher("[![ezgif.com-optimize(3).gif](https://static.cnbetacdn.com/article/2020/1007/daee539e7b87dee.gif)](https://static.cnbetacdn.com/article/2020/1007/daee539e7b87dee.gif)");
        if (matcher1.matches()) {

            System.out.println(matcher1.group(1));

//            String ss = matcher1.group(1).split(" ")[0];
//            System.out.println(ss.substring(0, 2));
//            if(ss.substring(0, 2).contains("./")){
//                System.out.println(ss.substring(2));
//            }
        }

//        Pattern pattern1 = Pattern.compile("A");
//        Matcher matcher1 = pattern1.matcher("max(A+B)");
//        if (matcher1.matches()) {
//            System.out.println(matcher1.group(1));
//        }


    }
}
