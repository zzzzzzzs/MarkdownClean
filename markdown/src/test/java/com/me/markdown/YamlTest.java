package com.me.markdown;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @author zs
 * @date 2021/7/13.
 */
public class YamlTest {
    public static void main(String[] args) throws IOException {

        InputStream resource = YamlTest.class.getClassLoader().getResourceAsStream("application.yaml");

        if (Objects.nonNull(resource)) {
            Yaml yaml = new Yaml();
        }
    }
}