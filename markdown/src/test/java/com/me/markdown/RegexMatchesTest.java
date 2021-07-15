package com.me.markdown;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zs
 * @date 2021/7/13.
 */
public class RegexMatchesTest {
    public static void main(String[] args) {
//        String str = "![asd](as)";
//        String pattern = "!\\[.*\\]\\(.+\\)";
//
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(str);
//        System.out.println(m.matches());

//        Pattern pattern1 = Pattern.compile(".*?\\((.*?)\\).*?");
//        Matcher matcher1 = pattern1.matcher("max(A+B)");
//        if (matcher1.matches()) {
//            System.out.println(matcher1.group(1));
//        }

        Pattern pattern1 = Pattern.compile("A");
        Matcher matcher1 = pattern1.matcher("max(A+B)");
        if (matcher1.matches()) {
            System.out.println(matcher1.group(1));
        }


    }
}
