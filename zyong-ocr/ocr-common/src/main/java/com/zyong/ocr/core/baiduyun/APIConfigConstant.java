package com.zyong.ocr.core.baiduyun;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * @author ZYONG
 * @date 2020/8/1 17:10
 */
public class APIConfigConstant {
    public static final String LANGUAGE_DEFAULT="CHN_ENG";//默认使用 中文+英文
    public static final String LANGUAGE_ENG="ENG";//使用 英文
    public static final String GRANTTYPE = "client_credentials";
    public static final String CLIENTID = "自己上百度云免费申请";
    public static final String CLIENTSECRET = "自己上百度云免费申请";
    public static final String TOKENAPI = "https://aip.baidubce.com/oauth/2.0/token";
    public static final String OCRAPI = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
    /**
     * token有效期 ms
     * 1个月
     */
    public static final long TOKEN_EXPIRES_IN = 30 * 24 * 60 * 60 * 1000L;

    public static final String TOKEN_CACHE_PATH = System.getProperty("user.dir") + File.separator + "token_cache.json";
}
