package com.zyong.ocr.core.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.*;
import java.net.URLEncoder;

/**
 * 图片转换Base64字符串
 * @author ZYONG
 * @date 2020/8/1 16:29
 */
public class Img2Base64 {
    public static String getImgBase64Str(File imgFile) throws IOException {
        String encode = null;
        try( FileInputStream inputStream = new FileInputStream(imgFile);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(data)) != -1){
                outputStream.write(data,0,len);
            }
            encode = URLEncoder.encode(Base64.encode(outputStream.toByteArray()), "UTF-8");
        }
        return encode;
    }

    /**
     * img to base64 str , exclude prefix suchas: data:image/png;base64,
     * @return
     */
    public static String getImgBase64StrExcludePrefix(File imgFile) throws IOException {
        return getImgBase64Str(imgFile).replaceAll("data:image/png;base64,","");
    }

}
