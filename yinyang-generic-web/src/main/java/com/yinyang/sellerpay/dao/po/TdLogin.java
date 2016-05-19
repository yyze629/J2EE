package com.yinyang.sellerpay.dao.po;

import java.util.Date;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dhgate.common.util.StringUtil;
import com.yinyang.sellerpay.dao.api.dto.TdLoginDTO;

@Entity
@Table(name = "TD_LOGIN")
public class TdLogin implements Serializable {
	private static final long serialVersionUID = 1L;
	private String  loginid;
	private String  loginname;
	private String  loginpassword;
	private String  plaintext;
	private Date  updatetime;
	private Date  createtime;
	private String  isdeleted;
	
	/** default constructor */
	public TdLogin() {}
	
	/** full constructor */
	public TdLogin(String  loginid , String  loginname , String  loginpassword , String  plaintext , Date  updatetime , Date  createtime , String  isdeleted  ) {
	this.loginid = loginid;
	this.loginname = loginname;
	this.loginpassword = loginpassword;
	this.plaintext = plaintext;
	this.updatetime = updatetime;
	this.createtime = createtime;
	this.isdeleted = isdeleted;
	}
	
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	
	//@Id
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "loginId", nullable = false, length = 50)
	public String getLoginid() {
		return this.loginid;
	}
	
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	@Column(name = "loginName", nullable = false, length = 32)
	public String getLoginname() {
		return this.loginname;
	}
	
	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}
	
	@Column(name = "loginPassword",  length = 32)
	public String getLoginpassword() {
		return this.loginpassword;
	}
	
	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}
	
	@Column(name = "plaintext",  length = 50)
	public String getPlaintext() {
		return this.plaintext;
	}
	
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name = "updateTime", nullable = false, length = 19)
	public Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name = "createTime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}
	
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@Column(name = "isDeleted", nullable = false, length = 2)
	public String getIsdeleted() {
		return this.isdeleted;
	}
	

    public void dto2po(TdLoginDTO dto) {
    	if (dto == null) {
    		return;
    	}
	this.loginid = dto.getLoginid();
	this.loginname = dto.getLoginname();
	this.loginpassword = dto.getLoginpassword();
	this.plaintext = dto.getPlaintext();
	this.updatetime = dto.getUpdatetime();
	this.createtime = dto.getCreatetime();
	this.isdeleted = dto.getIsdeleted();
    }

    public TdLoginDTO po2dto() {
        TdLoginDTO dto = new TdLoginDTO();
        dto.setLoginid(this.loginid);
        dto.setLoginname(this.loginname);
        dto.setLoginpassword(this.loginpassword);
        dto.setPlaintext(this.plaintext);
        dto.setUpdatetime(this.updatetime);
        dto.setCreatetime(this.createtime);
        dto.setIsdeleted(this.isdeleted);
        return dto;
    }
	
    public void dtoMerge2Po(TdLoginDTO dto) {
        if (dto == null) {
            return;
        }
	
        if (StringUtil.notEmpty(dto.getLoginid())) {
            this.loginid = dto.getLoginid();
        }
	
        if (StringUtil.notEmpty(dto.getLoginname())) {
            this.loginname = dto.getLoginname();
        }
	
        if (StringUtil.notEmpty(dto.getLoginpassword())) {
            this.loginpassword = dto.getLoginpassword();
        }
	
        if (StringUtil.notEmpty(dto.getPlaintext())) {
            this.plaintext = dto.getPlaintext();
        }
	
        if (dto.getUpdatetime() != null) {
            this.updatetime = dto.getUpdatetime();
        }
	
        if (dto.getCreatetime() != null) {
            this.createtime = dto.getCreatetime();
        }
	
        if (StringUtil.notEmpty(dto.getIsdeleted())) {
            this.isdeleted = dto.getIsdeleted();
        }
    }	
	
	
}