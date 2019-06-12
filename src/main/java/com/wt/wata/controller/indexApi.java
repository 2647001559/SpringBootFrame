package com.wt.wata.controller;

import com.wt.wata.common.wxutil.WeiXinLoginUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目访问主接口
 * @author 添柴灬少年
 * @date 2019/6/10 - 11:12
 */
@Controller
@RequestMapping("index")
public class indexApi {
    private static Logger logger = LoggerFactory.getLogger(indexApi.class);

    @Autowired
    private WeiXinLoginUtil weiXinLoginUtil;

    @Value("${wx.appId}")
    private String appId;           //微信appId

    @Value("${server.home}")
    private String serverHome;      //回调域名
    @Value("${menu.A1}")
    private String menuA1;          //平台开发
    @Value("${menu.A2}")
    private String menuA2;          //平台开发
    @Value("${menu.B1}")
    private String menuB1;          //平台开发

    private StringBuffer url = null; //请求地址

    /**
     * 拉取微信授权
     * 参数可以自己增加，如果这里增加参数，微信公众号上的菜单链接参数保持一致
     * @param menu      菜单标识
     * @param response
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "main" , method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public void main(String menu, HttpServletResponse response, HttpServletRequest request) throws Exception {
        try{
            init();
            String redirect_uri = serverHome + "index/oauth.do?menu=" + menu;   //授权成功回调地址
            /*微信请求地址*/
            url.append("https://open.weixin.qq.com/connect/oauth2/authorize?redirect_uri=").append(redirect_uri)
                    .append("&appid=").append(appId).append("&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect");
            response.sendRedirect(url.toString());
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 微信授权成功后微信的回调地址，中转站，将拿到的用户openId转发给前端页面进行登陆
     * 参数可以自己增加，如果这里增加参数，微信公众号上的菜单链接参数保持一致
     * @param menu      菜单标识
     * @param code      微信返回过来的code
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "oauth", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public void oauth(String menu,HttpServletRequest request,HttpServletResponse response,String code) throws Exception{
        try{
            init();     //初始化
//            WeiXinLoginUtil weiXinLoginUtil = WeiXinLoginUtil.getWeiXinLoginUtil(); //获取登陆工具
            String openId = weiXinLoginUtil.getOpenId(code);                        //获取用户openId
            url.append(serverHome);                                                 //设置请求域名
            String comStr = "?openId="+openId;                                      //设置请求参数
            switch (menu){                                                          //拼接请求
                case "A1":
                    url.append(menuA1);
                    break;
                case "A2":
                    url.append(menuA2);
                    break;
                case "A3":
                    url.append(menuB1);
                    break;
                    default:
                        break;
            }
            url.append(comStr);
            response.sendRedirect(url.toString());                                      //转发
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 微信登陆
     * @param openId    用户的openId
     * @return          登陆信息
     */
    @RequestMapping(value = "wxLogin", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public Map<String,Object> wxLogin(String openId){
        Map<String,Object> map = new HashMap<>();
        try{
            if (openId == null || openId.length() == 0){                //判断参数是否为空
                map.put("code","param_null");
                map.put("code","微信登陆失败");
                logger.error("微信登陆失败，参数：openId缺失");
                return map;
            }
            /*User user = userService.getUserByOpenId(openId);*/        //根据openId从数据库获取用户信息

            /*if (user == null){                                        //判断是否存在该用户
                map.put("code","not_have");
                map.put("message","用户不存在，请先注册");
                logger.error("微信登陆失败，用户不存在："+openId);
                return map;
            }*/

            /*if(user.getPhone() == null || user.getPhone().length() == 0){     //判断用户是否注册（看业务需求，如果业务需求说明，登陆时要判断是否注册，则加上这段）
                map.put("code","unregistered");
                map.put("message","用户尚未注册");
                return map;
            }*/

            /*其他需要登陆时验证的需求，在后面自行添加*/

            /*map.put("code","success");                                //返回成功信息
              map.put("message","登陆成功");
              map.put("openId",openId);
              map.put("userId",user.getUserId());
              */
        }catch (Exception e){
            map.put("code","error");
            map.put("message","服务反应超时");
            logger.error("微信登陆发生异常：{}",e);
        }
        return map;
    }

    /**
     * 微信注册
     * @param openId    用户的openId
     * @param phone     填写的手机号
     * @param code      接收到的短信验证码
     * @return
     */
    @RequestMapping(value = "wxRegistered", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public Map<String,Object> wxRegistered(String openId,String phone,String code){
        Map<String,Object> map = new HashMap<>();
        try{
            if (openId == null || openId.length() == 0 ){
                map.put("code","param_null");
                map.put("code","微信注册失败");
                logger.error("微信注册失败，参数：openid缺失");
                return map;
            }
            if ( phone == null || phone.length() == 0){
                map.put("code","param_null");
                map.put("code","微信注册失败");
                logger.error("微信注册失败，参数：phone缺失");
                return map;
            }
            if (code == null || code.length() == 0){
                map.put("code","param_null");
                map.put("code","微信注册失败");
                logger.error("微信注册失败，参数：code缺失");
                return map;
            }

            /*User user = userService.getUserByOpenId(openId);*/        //根据openId从数据库获取用户信息

            /*if(user != null){                                         //判断数据库是否存在该用户
                map.put("code","user_have");
                map.put("message","用户已注册，请登陆");
                logger.error("微信注册失败，用户已存在："+openId);
                return map;
            }*/

            /*if(!aLiYunSMSReleaseUtil.verifyCode(phone, MD5.GetMD5Code(code), 4)){         //判断短信验证码是否正确，这里用的阿里云的短信服务
                map.put("code","code_error");
                map.put("message","验证码错误");
                logger.error("微信注册失败，验证码错误：openId = "+openId+ ",code = "+code);
                return map;
            }*/

            /*user = new User();*/                                      //用户不存在，实例化对象
//            WeiXinLoginUtil weiXinLoginUtil = WeiXinLoginUtil.getWeiXinLoginUtil(); //获取微信登陆工具
            Map<String,String> userInfo = weiXinLoginUtil.getUserInfo(openId);      //获取用户信息

            /*user.setUserName(userInfo.get("nickname"));                           //保存对象
              user.setProvince(userInfo.get("province"));
              user.setCity(userInfo.get("city"));
              user.setHeadImage(userInfo.get("headimgurl"));
            */

            /*int result = userService.insert(user);*/              //添加到数据库

            /*if(result <= 0){                                      //判断数据库是否添加成功
                map.put("code","fall");
                map.put("message","注册失败");
                logger.error("微信注册失败，数据库添加失败");
                return map;
            }*/

            /*map.put("code","success");
              map.put("message","注册成功");
              map.put("openId",openId);
              */
        }catch (Exception e){
            map.put("code","error");
            map.put("message","服务反应超时");
            logger.error("微信注册发生异常：{}",e);
        }
        return map;
    }


    /*================================= 提供给当前类使用的方法 start =================================*/

    /**
     * 初始化
     * @throws Exception
     */
    public void init() throws Exception{
        try{
            if (url == null){
                url = new StringBuffer();
            }
            if (url.length() > 0){
                url.setLength(0);
            }
        }catch (Exception e){
            throw e;
        }
    }

    /*================================= 提供给当前类使用的方法 end =================================*/


}
