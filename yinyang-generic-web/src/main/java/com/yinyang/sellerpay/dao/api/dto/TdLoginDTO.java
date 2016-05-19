package com.yinyang.sellerpay.dao.api.dto;

import java.util.Date;
import java.io.Serializable;

@SuppressWarnings("serial")
public class TdLoginDTO  implements Serializable {
    private String  loginid;
    private String  loginname;
    private String  loginpassword;
    private String  plaintext;
    private Date  updatetime;
    private Date  createtime;
    private String  isdeleted;
	
    /** default constructor */
    public TdLoginDTO() {}
	
    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }
	
    public String getLoginid() {
        return this.loginid;
    }
	
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
	
    public String getLoginname() {
        return this.loginname;
    }
	
    public void setLoginpassword(String loginpassword) {
        this.loginpassword = loginpassword;
    }
	
    public String getLoginpassword() {
        return this.loginpassword;
    }
	
    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }
	
    public String getPlaintext() {
        return this.plaintext;
    }
	
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
	
    public Date getUpdatetime() {
        return this.updatetime;
    }
	
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
	
    public Date getCreatetime() {
        return this.createtime;
    }
	
    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }
	
    public String getIsdeleted() {
        return this.isdeleted;
    }

	@Override
	public String toString() {
		return "TdLoginDTO [loginid=" + loginid + ", loginname=" + loginname
				+ ", loginpassword=" + loginpassword + ", plaintext="
				+ plaintext + ", updatetime=" + updatetime + ", createtime="
				+ createtime + ", isdeleted=" + isdeleted + "]";
	}
	
    
}