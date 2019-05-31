package com.wt.wata.common.wxutil;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;

/**
 * java发送请求的工具类
 * @author 添柴灬少年
 * @date 2019/5/30 - 11:43
 */
public class WeiXinHttpClient {

    private static Log log = LogFactory.getLog(WeiXinHttpClient.class);

    /**
     * 上传
     *
     * @param url
     * @param file
     * @return
     */
    public static String upload(String url, File file) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (file != null) {
            HttpEntity entity = MultipartEntityBuilder.create().addPart("media", new FileBody(file)).build();
            // 设置表单
            httpPost.setEntity(entity);
            // 设置请求内容类型
            httpPost.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());
        }
        try {
            // 执行请求
            CloseableHttpResponse response = httpClient.execute(httpPost);
            // 响应
            String responseContent = EntityUtils.toString(response.getEntity());
            return responseContent;
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return null;

    }

    public static String sendGet(String url) {
        // 获取一个浏览器对象
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建get请求
        HttpGet httpGet = new HttpGet(url);

        try {
            // 获取响应
            CloseableHttpResponse response = client.execute(httpGet);
            // 返回响应的字符串
            return EntityUtils.toString(response.getEntity(), Consts.UTF_8);

        } catch (ClientProtocolException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;

    }

    public static String sendPost(String url, String contentStr) throws Exception {
        // 获取一个浏览器对象
        CloseableHttpClient client = HttpClients.createDefault();

        // 创建post请求
        HttpPost httpPost = new HttpPost(url);

        HttpPost post = new HttpPost(url);
        List<BasicNameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("msg", contentStr));
        httpPost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));

        // if (!CommonUtil.isBlank(contentStr)) {
        // StringEntity stringEntity = new StringEntity(contentStr,
        // Consts.UTF_8);
        // httpPost.setEntity(stringEntity);
        // }

        // 获取响应
        CloseableHttpResponse response = client.execute(httpPost);
        // 返回响应的字符串
        return EntityUtils.toString(response.getEntity(), Consts.UTF_8);

    }


    /**
     * 发送带xml数据的post请求
     * @param urlStr 请求地址
     * @param xmlInfo xml数据
     * @return
     */
    public static String httpsPostXml(String urlStr, String xmlInfo) {
        try {
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
            // 在输入流里面进行转码
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            out.write(xmlInfo);
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer lines = new StringBuffer();
            String line = "";
            for(line = br.readLine(); line != null; line = br.readLine()){
                lines.append(line);
            }
            return lines.toString();
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("发送post请求，失败");
            return null;
        }
    }
}
