package com.zyong.ocr.core.baiduyun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyong.ocr.core.factory.Ocr;
import com.zyong.ocr.core.util.HttpUtils;
import com.zyong.ocr.core.util.Img2Base64;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 百度云文字识别
 * @author ZYONG
 * @date 2020/7/30 22:46
 */
public class BaiduyunOcr extends Ocr {


    @Override
    public String charRecognition(InputStream img, String separator) {
        return null;
    }

    @Override
    public String charRecognition(String img, String separator) {
        String txt = "";
        try {
            txt = postImg(Img2Base64.getImgBase64StrExcludePrefix(new File(img)),null, separator);
            System.out.println("识别内容：" + txt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return txt;
    }

    @Override
    public List<String> charRecognition(File[] files, String separator) {
        List<String> strings = new ArrayList<>();
        for (File file : files) {
            String filePath = file.getAbsolutePath();
            String txt = this.charRecognition(filePath, separator);
            strings.add(txt);
        }
        return strings;
    }


    private String postImg(String base64Img, String languageType, String separator) throws IOException {
        long currentTime = System.currentTimeMillis();
        System.out.println(">>>>>>>开始识别");
        StringBuffer stringBuffer = new StringBuffer();
        languageType = languageType == null ? APIConfigConstant.LANGUAGE_DEFAULT : languageType;

        Headers.Builder builder = new Headers.Builder();
        builder.add("Content-Type", "application/x-www-form-urlencoded");

        RequestBody body = RequestBody.create( MediaType.parse("application/x-www-form-urlencoded"),
                "image=" + base64Img + "&detect_language=true&language_type=" + languageType+"&access_token="+this.getToken());

        try {
            ResponseBody res = HttpUtils.post(APIConfigConstant.OCRAPI , body,
                    builder);

            JSONObject jsonObject = JSONObject.parseObject(res.string());

            List<Map<String, String>> list =  (List<Map<String, String>>) jsonObject.get("words_result");
            if (null==list) {
                return null;
            }
            for (Map<String, String> map : list) {
                stringBuffer.append(map.get("words") + separator);
            }
        } catch (Exception e) {

            e.printStackTrace();

        }
        System.out.println(">>>>>>>识别结束，耗时："+ (System.currentTimeMillis() - currentTime) + " ms");
        return stringBuffer.toString();

    }

    /**
     * 获取token
     * @return
     */
    private String getToken() throws IOException {
        String token = null;
        JSONObject jsonObject = null;
        // 从缓存中获取token,如果不存在或者过期则重新获取token
        File tokenFile = new File(APIConfigConstant.TOKEN_CACHE_PATH);
        if (tokenFile.exists()){
            jsonObject = JSON.parseObject(FileUtils.readFileToString(tokenFile, "UTF-8"));
            Long timestamp = jsonObject.getLong("timestamp");
            // 计算是否过期
            if (System.currentTimeMillis() - timestamp < APIConfigConstant.TOKEN_EXPIRES_IN){
                // 没有过期直接返回
                token = jsonObject.getString("access_token");
                System.out.println("缓存中获取token:" + jsonObject.getString("access_token"));
                return token;
            }
        }

        System.out.println("重新获取token....");
        String url = "?grant_type=" + APIConfigConstant.GRANTTYPE + "&client_id=" + APIConfigConstant.CLIENTID + "&client_secret=" + APIConfigConstant.CLIENTSECRET;
        try {
            ResponseBody res = HttpUtils.post(APIConfigConstant.TOKENAPI + url, null);
            jsonObject =JSONObject.parseObject(res.string());
            token = jsonObject.getString("access_token");
            System.out.println("网络获取token:" + jsonObject.getString("access_token"));
            // 写入缓存文件
            JSONObject cacheJson = new JSONObject();
            cacheJson.put("access_token",token);
            cacheJson.put("timestamp",System.currentTimeMillis());
            // 删除原缓存
            if (tokenFile.exists()) tokenFile.delete();
            FileUtils.writeStringToFile(tokenFile, cacheJson.toJSONString(),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return token;
    }
}
