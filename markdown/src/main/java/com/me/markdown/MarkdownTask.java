package com.me.markdown;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.dfa.WordTree;
import com.me.markdown.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Slf4j
public class MarkdownTask {
    // md 文件中的图片地址
    List<String> mdPicPaths = new ArrayList<>();
    // 图片文件的路径
    Map<String, String> picPaths = new HashMap<>();
    // 创建存放图片的根目录
    String movePicRootPathTmp;
    // DFA
    WordTree tree = new WordTree();

    public void mainTask() {
        long startTime = System.currentTimeMillis();
        // 使用时间做图片的路径，为了以后图片找回
        String date = DateUtil.format(DateTime.now(), "yyyy-MM-dd-hh-mm-ss");
        movePicRootPathTmp = AppConfig.noteRootPath + date + "Tmp";
        FileUtil.mkdir(movePicRootPathTmp);
        List<File> fileList = FileUtil.loopFiles(AppConfig.noteRootPath);

        // 第一次遍历先将所有存放图片的路径读取出来
        fileList.forEach(file -> {
            // 读取存放图片的路径（只读取picPaths中定义的路径）
            if (AppConfig.picFileName.contains(file.getParentFile().getName())) {
                // 如果文件名包含图片后缀
                if (AppConfig.picSuffixs.contains(FileUtil.getSuffix(file))) {
                    picPaths.put(file.getName(), file.getAbsolutePath());
                    tree.addWord(file.getName());
                }
            }
        });
        // 第二次遍历读取文档的内容
        fileList.forEach(file -> {
            // 读取markdown中的内容，将图片格式提取出来
            if (AppConfig.fileSuffixs.contains(FileUtil.getSuffix(file))) {
                FileReader fileReader = new FileReader(file);
                List<String> contList = fileReader.readLines();
                // 遍历每行句子
                contList.forEach(line -> {
                    String match = tree.match(line);
                    if (match != null) { // 命中
                        picPaths.remove(match); //将命中的删除，保留不在md中引用的图片
                    }
                });
            }
        });
        for (Map.Entry<String, String> entry : picPaths.entrySet()) {
            String movePath = FileUtil.normalize(entry.getValue()).replaceAll(AppConfig.noteRootPath, movePicRootPathTmp);
            FileUtil.move(FileUtil.file(entry.getValue()), FileUtil.file(movePath), true);
        }
        log.info("用时{}ms清理完成", System.currentTimeMillis() - startTime);
    }

}