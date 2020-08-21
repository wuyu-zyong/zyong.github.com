package com.zyong.ocr.core.factory;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author ZYONG
 * @date 2020/8/1 15:51
 */
public abstract class Ocr {
    /**
     * 文字识别
     * @param img 图片流
     * @return 字符List
     */
    public abstract String charRecognition(InputStream img, String separator);
    public abstract String charRecognition(String img, String separator);
    public abstract List<String> charRecognition(File[] files, String separator);
}
