package com.me.markdown;

import com.me.markdown.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
@Slf4j
public class MarkdownTask {

    // md中图片路径的内容
    public List mdPicContexts = new ArrayList<>();
    public List parseMdPicContexts = new ArrayList<>();
    // 将md中图片的路径保存在HashSet中
    public HashSet<String> mdPicPaths = new HashSet<>();
    // 保存存储图片的路径
    public List<String> realPicPath = new ArrayList<>();

    public ArrayList<String> noUsePicPath = new ArrayList<>();
    // 创建存放图片的根目录
    File movePicRootPathTmp = null;
    // 用来判断图片的后缀
    List<String> picSuffix = new ArrayList<String>() {{
        add("jpg");
        add("png");
    }};

    // TODO 目前是把所有的图片放在了一个HashSet，这样搜索的性能差，但是实现起来简单，后期再优化
    // TODO 复杂的模板匹配（包括网页）
    // TODO　完善的日志
    public void mainTask() {
        long startTime = System.currentTimeMillis();
        movePicRootPathTmp = new File(AppConfig.noteRootPath + "Tmp");
        if (!movePicRootPathTmp.exists()) {
            movePicRootPathTmp.mkdirs();
        }

        doClean(AppConfig.noteRootPath);
        findNoUsePath();
        movePic();
        long endTime = System.currentTimeMillis();
        log.info("用时{}ms清理完成", endTime - startTime);
    }

    /**
     * @author: zs
     * @Date 2021/7/14
     * 移动图片的路径
     */
    public void movePic() {
        for (String ele : noUsePicPath) {

            String mvPicFileStr = ele.replace(new File(AppConfig.noteRootPath).getPath(), movePicRootPathTmp.getPath());

            int i = mvPicFileStr.lastIndexOf(File.separator);
            String movePicDirTmp = mvPicFileStr.substring(0, i);
            File movePicDirTmpFile = new File(movePicDirTmp);
            if (!movePicDirTmpFile.exists()) {
                movePicDirTmpFile.mkdirs();
            }
            File oriPicFile = new File(ele);
            File mvPicFile = new File(mvPicFileStr);
            // 移动图片
            if (oriPicFile.renameTo(mvPicFile)) {
                log.info("路径：" + oriPicFile + " 移动成功");
            } else {
                log.info("路径：" + oriPicFile + " 移动失败");
            }
        }
    }

    /**
     * @author: zs
     * @Date 2021/7/14
     * 查找未使用的图片路径
     */
    public void findNoUsePath() {
        for (String ele : realPicPath) {
            if (!mdPicPaths.contains(ele)) {
                noUsePicPath.add(ele);
            }
        }
    }

    /**
     * @author: zs
     * @Date 2021/7/14
     * 用来清理图片
     */
    public void doClean(String path) {
        // 获取当前路径下所有的子文件和路径
        File[] files = new File(path).listFiles();

        // 遍历files
        for (File file : files) {
            String parentFile = file.getParent();
            String lastFileName = parentFile.substring(parentFile.lastIndexOf(File.separator) + 1);
            // 如果是文件夹
            if (file.isDirectory()) {
                // 如果是文件夹，则继续执行递归
                doClean(file.getAbsolutePath());
            } else {
                // 读取md文件的图片路径内容，
                if (file.getName().endsWith(".md")) {
                    mdPicContexts.addAll(getMdPicContexts(file.getAbsolutePath()));
                    // 解析参数
                    parseMdPicContexts.addAll(parseMdPicContexts(mdPicContexts));
                    // 将md中图片的路径保存在HashSet中
                    mdPicPaths.addAll(joinMdPaths(file.getParent(), parseMdPicContexts));
                } else if (picSuffix.contains(file.getName().substring(file.getName().lastIndexOf(".") + 1)) && AppConfig.picPaths.contains(lastFileName)) {
                    // 如果是图片格式的后缀
                    realPicPath.add(file.getAbsolutePath());
                    log.info("将 {} 加入真实图片缓存", file.getAbsolutePath());
                }
            }
        }
    }

    /**
     * @author: zs
     * @Date 2021/7/14
     * 将md中所有的图片路径进行拼接，将当前的目录的路径拼接上Md中的图片路径
     */
    private HashSet<String> joinMdPaths(String path, List<String> list) {
        HashSet<String> set = new HashSet<>();
        for (String ele : list) {
            // TODO 后期这里需要优化
            String replace = ele.replace("/", "\\");
            set.add(path + File.separator + replace);
        }
        return set;
    }

    /**
     * @author: zs
     * @Date 2021/7/14
     * 将里面的MD文件里面的内容获取到
     */
    private List<String> parseMdPicContexts(List<String> list) {
        ArrayList<String> parseList = new ArrayList<>();
        // 匹配md文件中图片的内容
        Pattern pattern = Pattern.compile(".*?\\((.*?)\\).*?");

        for (String ele : list) {
            Matcher matcher = pattern.matcher(ele);
            if (matcher.matches()) {
                String ss = matcher.group(1).split(" ")[0];
                log.info("未解析的md路径为：{}, 原始md内容为：{}",ss, ele);
                if(ss.substring(0, 2).contains("./")){
                    String substring = ss.substring(2);
                    log.info("解析以后的md中图片对应的路径{}", substring);
                    parseList.add(substring);
                }else {
                    log.info("解析以后的md中图片对应的路径{}", ss);
                    parseList.add(ss);
                }
            }
//            if (matcher.matches()) {
//                parseList.add(matcher.group(1).split(" ")[0]);
//            }
        }
        return parseList;
    }


    /**
     * @author: zs
     * @Date 2021/7/13
     * 匹配图片路径
     */
    private boolean matcherPic(String pattern, String context) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(context);
        return m.matches();
    }


    /**
     * @author: zs
     * @Date 2021/7/13
     * 获取MD文件对于的所有图片内容
     * 类似：![img](assets/Proxy方法参数说明-20210622211812-ytt6ohd.jpg)
     */
    public List<String> getMdPicContexts(String path) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        ArrayList<String> picContexts = new ArrayList<>();

        // 图片名称
        // 图片路径存储格式：![image-20200603100128164](Typora 瘦身.assets/image-20200603100128164.png)
        /*
            \[.*\]：[image-20200603100128164]
                . ：匹配任意字符
                * ：出现0次或多次
            \(.+\)：(IDEA快捷键.assets/image-20200603100128164.png)
                . ：匹配任意字符
                + ：出现1次或多次
         */
        // 正则匹配规则
        String pattern = ".*!\\[.*\\]\\(.+\\).*";
        try {
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // 如果匹配成功
                if (matcherPic(pattern, line)) {
                    log.info("md中图片内容：" + line);
                    picContexts.add(line);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            // 关闭缓冲区
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return picContexts;
    }


/********* EOF ************/
}
