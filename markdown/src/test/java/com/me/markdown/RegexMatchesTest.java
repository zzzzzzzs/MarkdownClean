package com.me.markdown;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zs
 * @date 2021/7/13.
 */
public class RegexMatchesTest {
    public static void main(String[] args) {
        String str = "![asd](as)";
        String pattern = "!\\[.*\\]\\(.+\\)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }
}
