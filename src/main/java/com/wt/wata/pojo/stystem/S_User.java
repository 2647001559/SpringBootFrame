package com.wt.wata.pojo.stystem;

import com.wt.wata.common.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户实体类
 * @author 添柴灬少年
 * @Date    2018/12/15
 * @version 1.0
 */
public class S_User implements Serializable {
    private String uId;             //用户id
    private String uAccount;        //用户账号
    private String uPassword;       //用户密码
    private String uName;           //用户名称
    private String uRoleId;         //用户角色id
    private Integer uStatus;        //用户状态  1：正常    2：禁用
    private String uHead;           //用户头像
    private String uCreateBy;       //创建者id
    private Date uCreateTime;       //创建时间
    private String uUpdateBy;       //修改者id
    private String uUpdateTime;     //修改时间

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuAccount() {
        return uAccount;
    }

    public void setuAccount(String uAccount) {
        this.uAccount = uAccount;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuRoleId() {
        return uRoleId;
    }

    public void setuRoleId(String uRoleId) {
        this.uRoleId = uRoleId;
    }

    public Integer getuStatus() {
        return uStatus;
    }

    public void setuStatus(Integer uStatus) {
        this.uStatus = uStatus;
    }

    public String getuCreateBy() {
        return uCreateBy;
    }

    public void setuCreateBy(String uCreateBy) {
        this.uCreateBy = uCreateBy;
    }

    public Date getuCreateTime() {
        return uCreateTime;
    }

    public void setuCreateTime(Date uCreateTime) {
        this.uCreateTime = uCreateTime;
    }

    public String getuUpdateBy() {
        return uUpdateBy;
    }

    public void setuUpdateBy(String uUpdateBy) {
        this.uUpdateBy = uUpdateBy;
    }

    public String getuUpdateTime() {
        return uUpdateTime;
    }

    public void setuUpdateTime(String uUpdateTime) {
        this.uUpdateTime = uUpdateTime;
    }

    public String getuHead() {
        return uHead;
    }

    public void setuHead(String uHead) {
        this.uHead = uHead;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("用户id："+getuId()).append("用户账号："+getuAccount()).append("用户密码："+getuPassword()).append("用户名称："+getuName());
        str.append("用户角色id："+getuRoleId()).append("用户状态："+getuStatus()).append("用户头像："+getuHead()).append("创建者id："+getuCreateTime());
        str.append("创建时间："+DateUtil.time01.format(getuCreateTime())).append("修改者id："+getuUpdateBy()).append("修改时间："+DateUtil.time01.format(getuUpdateTime()));
        return str.toString();
    }
}
