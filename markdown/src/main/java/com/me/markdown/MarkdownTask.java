package com.me.markdown;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import com.me.markdown.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class MarkdownTask {
    // md 文件中的图片地址
    List<String> mdPicPaths = new ArrayList<>();
    // 图片文件的路径
    List<String> picPaths = new ArrayList<>();
    // 创建存放图片的根目录
    String movePicRootPathTmp;


    // TODO 目前是把所有的图片放在了一个HashSet，这样搜索的性能差，但是实现起来简单，后期再优化
    // TODO 复杂的模板匹配（包括网页）
    // TODO　完善的日志
    public void mainTask() {
        long startTime = System.currentTimeMillis();
        // 使用时间做图片的路径，为了以后图片找回
        String date = DateUtil.format(DateTime.now(), "yyyy-mm-dd-hh-mm-ss");
        movePicRootPathTmp = AppConfig.noteRootPath + date + "Tmp";
        FileUtil.mkdir(movePicRootPathTmp);
        List<File> fileList = FileUtil.loopFiles(AppConfig.noteRootPath);
        fileList.forEach(file -> {
            // 读取存放图片的路径（只读取picPaths中定义的路径）
            if (AppConfig.picFileName.contains(file.getParentFile().getName())) {
                picPaths.add(file.getAbsolutePath());
                return;
            }
            // 读取markdown中的内容，将图片格式提取出来
            if (AppConfig.fileSuffixs.contains(FileUtil.getSuffix(file))) {
                FileReader fileReader = new FileReader(file);
                List<String> contList = fileReader.readLines();
                contList.forEach(line -> {
                    log.info(line);
                });
            }
        });
        log.info("用时{}ms清理完成", System.currentTimeMillis() - startTime);
    }

}