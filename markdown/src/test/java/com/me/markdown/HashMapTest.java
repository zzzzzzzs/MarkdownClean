package com.me.markdown;

import java.util.HashMap;

/**
 * @author zs
 * @date 2021/7/13.
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "1");
        hashMap.put("1", "2");
        System.out.println(hashMap.toString());
    }
}
