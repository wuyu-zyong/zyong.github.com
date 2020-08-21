package com.zyong.ocr.gui.common;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ZYONG
 * @date 2020/8/1 15:02
 */
public class AppConstant {
    public static final String APPP_PATH = System.getProperty("user.dir");
    public static final String IMAGE_CACHE_PATH = "image_cache";
    public static final String IMAGE_SUFFIX = "jpg";
    public static final String TXT_CACHE_PATH = "txt_cache";
    public static final SimpleDateFormat DATA_FORMAT_FILE = new SimpleDateFormat("yyyymmddHHmmss");
    public static final long CUT_SCREENT_WAIT = 1 * 5 * 1000;

    public static final String WAIT_HINT = "正在识别中.....";

    /**
     * 创建本次识别的结果保存文件
     */
    public static final String CURRENT_SAVE_FILE = APPP_PATH + File.separator + TXT_CACHE_PATH + File.separator + DATA_FORMAT_FILE.format(new Date()) + "识别记录.txt";
}
