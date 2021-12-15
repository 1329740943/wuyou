package com.demo.wuyou.test;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.awt.*;
import java.math.BigInteger;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class HttpDemo {
    public static final Logger log= LoggerFactory.getLogger(HttpDemo.class);
    public static void main(String[] args) {
        //格力token请求地址
        String greetoken="https://oa.gree.cn:8883/ecustom/servlets/IERP-getAccessToken.json";
        //格力相关资料查看地址
        String greerequest="https://oa.gree.cn:8883/ecustom/servlets/IERP-requestInfo.html";
        //手机号
        String phone="13676027200";//方明熊
        //md 等于 手机号+2021-04-27 +MD5加密
        String mdKey = phone + "2021-04-27";
        mdKey = StringToMD5(mdKey);
        HashMap data = new HashMap();
        data.put("mobile",phone);
        data.put("md",mdKey);
        StringBuffer url = new StringBuffer(greetoken);
        log.info("请求地址:"+url);
        log.info("token请求参数:"+data);
        JSONObject json = JSONUtil.parseObj(HttpUtil.get(greetoken, data));
        log.info("接口返回参数:"+json);
        Object token = json.get("TOKEN");
        log.info("token:"+token);
        HashMap requestdata = new HashMap();
        requestdata.put("token", token.toString());
        requestdata.put("mobile", phone);
        requestdata.put("requestid", "176325");
        log.info("请求参数:"+requestdata);
        String openUrl = Getfullurl(greerequest, requestdata);
        log.info("打开的地址:"+openUrl);
        try {
            OpenUrl(openUrl);
        } catch (Exception e) {
            log.error("error:"+e.getMessage());
            e.printStackTrace();
        }
    }
    //调用默认浏览器打开指定地址
    private static void OpenUrl(String url) throws Exception {
        Desktop desktop = Desktop.getDesktop();//桌面类,可以用来启动程序,构建实例化对象
        if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {//是否支持桌面和浏览器
            URI uri = new URI(url);
            desktop.browse(uri);//用默认浏览器打开指定地址
        }
    }
    //地址与参数拼接生成行的带参GET地址
    public static String Getfullurl(final String url, final Map<String, String> param) {
        final Map<String, Object> result = new HashMap<String, Object>();
        final StringBuffer urlFull = new StringBuffer(url);
        if (param.size() > 0) {
            urlFull.append("?");
            for (String key : param.keySet()) {
                String value = param.get(key);
                if (value != null && value.length() > 0) {
                    urlFull.append(key).append("=").append(value).append("&");
                }
            }
            urlFull.deleteCharAt(urlFull.length() - 1);
        }
        return urlFull.toString();
    }
    //MD5加密
    public static String StringToMD5(final String plainText) {
//        Spring 自带的MD5加密
        System.out.println(DigestUtils.md5DigestAsHex("13676027200".getBytes()));
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密出问题了!");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length();i++) {
            md5code = "0" + md5code;
        }
        System.out.println(md5code);
        return md5code;
    }

}
