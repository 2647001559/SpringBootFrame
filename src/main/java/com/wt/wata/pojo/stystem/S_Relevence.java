package com.wt.wata.pojo.stystem;

import java.io.Serializable;

/**
 * 角色资源关联实体类
 * @author 添柴灬少年
 * @Date    2018/12/15
 * @version 1.0
 */
public class S_Relevence implements Serializable {
    private String reId;        //主键
    private String reRid;       //角色id
    private String reSid;       //资源id

    public String getReId() {
        return reId;
    }

    public void setReId(String reId) {
        this.reId = reId;
    }

    public String getReRid() {
        return reRid;
    }

    public void setReRid(String reRid) {
        this.reRid = reRid;
    }

    public String getReSid() {
        return reSid;
    }

    public void setReSid(String reSid) {
        this.reSid = reSid;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("主键："+getReId()).append("角色id："+getReRid()).append("角色id："+getReRid()).append("资源id："+getReSid());
        return str.toString();
    }
}
