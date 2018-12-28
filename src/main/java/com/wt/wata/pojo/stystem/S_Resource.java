package com.wt.wata.pojo.stystem;

import com.wt.wata.common.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限资源实体类
 * @author 添柴灬少年
 * @Date    2018/12/15
 * @version 1.0
 */
public class S_Resource implements Serializable {
    private String sId;         //资源id
    private String sName;       //资源名称
    private Integer sLevel;     //资源级别
    private String sUrl;        //资源路径
    private String sParentId;   //父级资源id
    private String sIcon;       //资源图标
    private Integer sStatus;    //资源状态
    private String sCreateBy;   //创建者id
    private Date sCreateTime;   //创建时间
    private String sUpdateBy;   //修改者id
    private Date sUpdateTime;   //修改时间

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getsLevel() {
        return sLevel;
    }

    public void setsLevel(Integer sLevel) {
        this.sLevel = sLevel;
    }

    public String getsUrl() {
        return sUrl;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl;
    }

    public String getsParentId() {
        return sParentId;
    }

    public void setsParentId(String sParentId) {
        this.sParentId = sParentId;
    }

    public String getsIcon() {
        return sIcon;
    }

    public void setsIcon(String sIcon) {
        this.sIcon = sIcon;
    }

    public Integer getsStatus() {
        return sStatus;
    }

    public void setsStatus(Integer sStatus) {
        this.sStatus = sStatus;
    }

    public String getsCreateBy() {
        return sCreateBy;
    }

    public void setsCreateBy(String sCreateBy) {
        this.sCreateBy = sCreateBy;
    }

    public Date getsCreateTime() {
        return sCreateTime;
    }

    public void setsCreateTime(Date sCreateTime) {
        this.sCreateTime = sCreateTime;
    }

    public String getsUpdateBy() {
        return sUpdateBy;
    }

    public void setsUpdateBy(String sUpdateBy) {
        this.sUpdateBy = sUpdateBy;
    }

    public Date getsUpdateTime() {
        return sUpdateTime;
    }

    public void setsUpdateTime(Date sUpdateTime) {
        this.sUpdateTime = sUpdateTime;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("资源id："+getsId()).append("资源名称："+getsName()).append("资源级别："+getsLevel()).append("资源路径："+getsUrl()).append("父级资源id："+getsParentId());
        str.append("资源图标："+getsIcon()).append("资源状态："+getsStatus()).append("创建者id："+getsCreateBy()).append("创建时间："+DateUtil.time01.format(getsCreateTime()));
        str.append("修改者id："+getsUpdateBy()).append("修改时间："+DateUtil.time01.format(getsUpdateTime()));
        return str.toString();
    }
}
