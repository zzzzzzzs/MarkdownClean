package com.me.markdown;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author zs
 * @date 2021/7/17.
 */
public class DateTest {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        System.out.println(dateTime.format(formatter));
    }
}
