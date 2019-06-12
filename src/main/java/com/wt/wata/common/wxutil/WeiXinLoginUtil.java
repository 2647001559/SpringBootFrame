package com.wt.wata.common.wxutil;

import com.alibaba.fastjson.JSONObject;
import com.wt.wata.common.CacheUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信登陆工具
 * @author 添柴灬少年
 * @date 2019/5/30 - 10:32
 */
@Component
@Scope("singleton")
public class WeiXinLoginUtil {
///*================================= 单例模式设置 和 初始化方法 start =================================*/
//    /*单例模式  volatile:防止实例在初始化时的指令重排序*/
//    private static volatile WeiXinLoginUtil  weiXinLoginUtil = null;
//    /*构造器私有化*/
//    private WeiXinLoginUtil(){}
//
//    /**
//     * 获取单例
//     * @return
//     */
//    public static WeiXinLoginUtil getWeiXinLoginUtil(){
//        if (weiXinLoginUtil == null){                           //判断单例是否初始化
//            synchronized (WeiXinLoginUtil.class){               //锁定初始化，防止在多线程下，实例化多个单例
//                if (weiXinLoginUtil == null){                   //判断单例是否初始化，防止单例重复初始化或初始化不同的单例
//                    weiXinLoginUtil = new WeiXinLoginUtil();    //初始化单例
//                }
//            }
//        }
//        return weiXinLoginUtil;                                 //返回单例
//    }

    /*成员变量   拼接请求地址*/
    private StringBuffer strUrl = null;

    @Value("${wx.appId}")
    private String appId;   //微信appid
    @Value("${wx.appSecret}")
    private String appSecret;   //微信appSecret

    /**
     * 初始化成员变量
     */
    private void init() throws Exception{
        try{
            if (strUrl == null){
                strUrl = new StringBuffer();
            }
            if (strUrl.length() > 0){
                strUrl.setLength(0);
            }
        }catch (Exception e){
            throw e;
        }
    }
/*================================= 单例模式设置 和 初始化方法 end =================================*/





/*=================================   提供给外部使用的方法 start   =================================*/
    /**
     * 获取openId标识
     * @param code 前端传过来的code
     * @throws Exception
     */
    public String getOpenId(String code) throws Exception{
        try{
            init();
            if (code == null || code.equals("")){
                throw new Exception("微信登陆工具 --- 获取openId标识 --- 参数code为空值");
            }
            strUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=").append(appId)
                    .append("&secret=").append(appSecret)
                    .append("&code=").append(code).append("&grant_type=authorization_code");
            return saveData(strUrl,"获取openId标识");
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 获取access_token凭证
     * @param openId    从数据库拿到的openId
     * @return  返回"-1" 需要重新登陆
     * @throws Exception
     */
    public String getAccessToken(String openId) throws Exception{
        try{
            init();
            if (openId == null || openId.equals("")){
                throw new Exception("微信登陆工具 --- 获取access_token凭证 --- 参数openId为空值");
            }
            if (verifyExpiredAccessToken(openId)){
                if (verifyExpiredRefreshToken(openId)){
                    return "-1";
                }
                refreshAccessToken(openId);
            }
            return CacheUtil.get(openId + "_tokenA").toString();
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 获取微信用户信息
     * @return  nickname：用户昵称 sex：用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 province：用户个人资料填写的省份 city：普通用户个人资料填写的城市
     *          country：国家，如中国为CN headimgurl：用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     *          privilege ：用户特权信息，json 数组，如微信沃卡用户为（chinaunicom） unionid：只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * @throws Exception
     */
    public Map<String,String> getUserInfo(String openId) throws Exception{
        Map<String,String> map = new HashMap<>();
        try{
            init();
            strUrl.append("https://api.weixin.qq.com/sns/userinfo?access_token=").append(CacheUtil.get(openId + "_tokenA"))
                    .append("&openid=").append(openId).append("&lang=zh_CN");
            String result = WeiXinHttpClient.sendGet(strUrl.toString());
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.containsKey("errcode")){
                throw new Exception("微信登陆工具 -- 获取微信用户信息 -- 微信请求返回错误码 ： "+jsonObject.get("errcode"));
            }
            map.put("nickname",jsonObject.get("nickname").toString());
            map.put("province",jsonObject.get("province").toString());
            map.put("city",jsonObject.get("city").toString());
            map.put("country",jsonObject.get("country").toString());
            map.put("headimgurl",jsonObject.get("headimgurl").toString());
            map.put("privilege",jsonObject.get("privilege").toString());
            map.put("unionid",jsonObject.get("unionid").toString());
            return map;
        }catch (Exception e){
            throw e;
        }
    }
/*=================================   提供给外部使用的方法 end   =================================*/




/*================================= 提供给当前类使用的方法 start =================================*/
    /**
     * 验证access_token是否过期
     * @return true 已过期     false   未过期
     */
    private boolean verifyExpiredAccessToken(String openId) throws Exception{
        try{
            if (openId == null || openId.equals("")){
                throw new Exception("微信登陆工具 --- 验证access_token是否过期 --- 参数openId为空值");
            }
            Object object =  CacheUtil.get(openId + "_tokenA");
            if (object != null){
                return false;
            }
        }catch (Exception e){
            throw e;
        }
        return true;
    }

    /**
     * 验证refresh_token是否过期
     * @param openId
     * @return  true 已过期  false 未过期
     * @throws Exception
     */
    private boolean verifyExpiredRefreshToken(String openId) throws Exception{
        try{
            if (openId == null || openId.equals("")){
                throw new Exception("微信登陆工具 --- 验证refresh_token是否过期 --- 参数openId为空值");
            }
            Object object =  CacheUtil.get(openId + "_tokenB");
            if (object != null){
                return false;
            }
        }catch (Exception e){
            throw e;
        }
        return true;
    }

    /**
     * 刷新access_token
     * @param openId
     * @throws Exception
     */
    private void refreshAccessToken(String openId) throws Exception{
        try{
            init();
            if (openId == null || openId.equals("")){
                throw new Exception("微信登陆工具 --- 刷新access_token --- 参数openId为空值");
            }
            String refresh_token = CacheUtil.get(openId + "_tokenB").toString();
            strUrl.append("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=").append(appId)
                    .append("&grant_type=refresh_token&refresh_token=").append(refresh_token);
            saveData(strUrl,"刷新access_token");
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 发送请求，并保存进缓存
     * @param url
     */
    private String saveData(StringBuffer url,String message) throws Exception{
        String result = WeiXinHttpClient.sendGet(strUrl.toString());
        if (result.equals("")){
            throw new Exception("微信登陆工具 --- " + message + " --- http请求返回空值");
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.containsKey("errcode")){
            throw new Exception("微信登陆工具 -- " + message + " -- 微信请求返回错误码 ： "+jsonObject.get("errcode"));
        }
        if (CacheUtil.get(jsonObject.get("openid").toString()) != null){
            CacheUtil.remove(jsonObject.get("openid").toString());
            CacheUtil.remove(jsonObject.get("openid").toString() + "_tokenA");
            CacheUtil.remove(jsonObject.get("openid").toString() + "_tokenB");
        }
        CacheUtil.put(jsonObject.get("openid").toString(),jsonObject);
        CacheUtil.put(jsonObject.get("openid").toString() + "_tokenA",jsonObject.get("access_token"),7200*1000);
        CacheUtil.put(jsonObject.get("openid").toString() + "_tokenB",jsonObject.get("refresh_token"),60*60*24*30*1000);
        return jsonObject.get("openid").toString();
    }
/*================================= 提供给当前类使用的方法 end =================================*/
}
