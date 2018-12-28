package com.wt.wata.pojo.stystem;

import com.wt.wata.common.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色实体
 * @author 添柴灬少年
 * @Date    2018/12/15
 * @version 1.0
 */
public class S_Role implements Serializable {
    private String rId;         //角色id
    private String rName;       //角色名称
    private Integer rLevel;     //角色级别
    private String rCreateBy;   //创建者id
    private Date rCreateTime;   //创建时间
    private String rUpdateBy;   //修改者id
    private Date rUpdateTime;   //修改时间

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public Integer getrLevel() {
        return rLevel;
    }

    public void setrLevel(Integer rLevel) {
        this.rLevel = rLevel;
    }

    public String getrCreateBy() {
        return rCreateBy;
    }

    public void setrCreateBy(String rCreateBy) {
        this.rCreateBy = rCreateBy;
    }

    public Date getrCreateTime() {
        return rCreateTime;
    }

    public void setrCreateTime(Date rCreateTime) {
        this.rCreateTime = rCreateTime;
    }

    public String getrUpdateBy() {
        return rUpdateBy;
    }

    public void setrUpdateBy(String rUpdateBy) {
        this.rUpdateBy = rUpdateBy;
    }

    public Date getrUpdateTime() {
        return rUpdateTime;
    }

    public void setrUpdateTime(Date rUpdateTime) {
        this.rUpdateTime = rUpdateTime;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("角色id："+getrId()).append("角色名称："+getrName()).append("角色级别："+getrLevel()).append("创建者id："+getrCreateBy());
        str.append("创建时间："+DateUtil.time01.format(getrCreateTime())).append("修改者id："+getrUpdateBy()).append("修改时间："+DateUtil.time01.format(getrUpdateTime()));
        return str.toString();
    }
}
