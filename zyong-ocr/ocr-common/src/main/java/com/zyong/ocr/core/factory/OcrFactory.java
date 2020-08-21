package com.zyong.ocr.core.factory;

import org.springframework.util.StringUtils;

/**
 * OCR文字识别工厂实例获取
 * @author ZYONG
 * @date 2020/8/1 15:56
 */
public class OcrFactory {
    public static Ocr getInstance(String classPath) {
        if (StringUtils.isEmpty(classPath)) return null;
        Ocr ocr = null;
        try {
            ocr = (Ocr) Class.forName(classPath).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ocr;
    }
}
