package com.yinyang.sellerpay.web.util;

import java.io.Serializable;


public class UserSession implements Serializable {
	private static final long serialVersionUID = 1L;
	private String systemuserid;
	private String supplierid;
	private String domainname;
	private String nickname;
	private String emailisvalid;
	private String mobileisvalid;
	//dhgate,第三方认证的状态
	private String authstatus;
	private String verifydetailstatus;
	//0,1 个人 企业
	private String isenterprise;
	/**是否已经阅读了chinapost的协议 0：未阅读，1：已经阅读*/
    private String isreadchina;
    /**是否已经阅读了HKPost的协议 0：未阅读，1：已经阅读*/
    private String isreadhkpost;
    /**是否已经阅读了海运的协议 0：未阅读，1：已经阅读*/
    private String isreadocean;
    /**是否已经阅读了新加坡小包的协议 0：未阅读，1：已经阅读*/
    private String isreadsingaporepost;

    private String idcardno;
    private String idcardname;
    private String personmobileno;
    private String picurl1;
    private String picurl2;
    private String picurl3;
    private String personalemailaddress;

    //是不是VIPZAONE的用户
    private boolean vipzoneUser;
    //以下是为了给AccountLinking用
	private String cookieString;


    //如果是K，这个supplier的数量限制就[+500]。
    private String storeopened;

    /**是否为power seller  1：是，0：不是*/
    private String ispowerseller;

    //确定用户是否白名单用户 0不在,1:在
    private String iswhitelist;

    //是否为factory用户
    private long isfactory;
    //0:seller用户 1：factory用户 2:体验用户
    private String maxlevel;
    private String curlevel;
    //seller当前等级标识
    private String checktype;
    //TS：Top Seller, OS：Onramp Seller, NS：New Seller,BS：Baby Seller
    //supplier 是否支持设置产品属性 0：不支持 1：支持
    private String isSupportAttr;

    //supplier 是否支持设置VIP产品 0：不支持 1：支持
    private String isSupportVIP;

    private String isSupportDHPort; //是否支持DHPort  0：不支持 1：支持
   

    private String userType;
    //true:现在和历史上是factory用户 false:不是
    private boolean isHistoryFactory;

    private String factoryregtype;
    private String factorydetail;
    //member onlyzone 权限 0 无权限  1有权限
    private String ismemberonlyzone;
    //seller 金银铜
    private String grade;
    //是否是im使用用户
    private String imuser;
    //未读站内信的数量
    private String noReadmess;
    //未读的垃圾站内信数量
    private String noReadLess;
    
    //新版站内信未读询盘数量NoReadLess
    private int noReadProduct;
    //新版站内信未读订单数量
    private int noReadOrder;
    //新版站内信未读系统数量
    private int noReadSystem;
    //新版站内信未读垃圾箱数量
    private int noReadDustbin;
    
    //CS卖留言新留言数量
    private String unReadCsmsg;
    //直通车产品
    private String quickup;

    private long evaluatestatus;	//'评审是否通过，0老用户，1新用户待审，2新用户通过，3新用户审核失败' 4 地址验证通过  5 新用户通TNS产品少于5
    //是否要显示地址验证
    private long isShowAddressValidate;//0不要 1要   2 已经发了平信  
    private long servicelevel;
    //子账户id
    private String suppliersubid;
    
    private long isSkuSupplier;  //0不是  1是 SKU模板权限

    //操作人员
    private String operateor;

    //是否要修改密码操作
    private String passwordupdateshow; 	//0不用 1用
    private String sellerStep;
    //left menu show id
    private String  initLeftShow;
    /* 关闭新手导航标签 true 关闭 false 没有关闭 */
    private boolean closeNewBuyer;
    private String l2supplierid;  //seller产品分区Id
    private String ip;
    private String macaddress;
    
    
    /** 0否 1是 */
    private String isGoldUser;
    
    /* 卖家分级项目 获得卖家星级   **/
    private Long supplierrank;
    
    
    public Long getSupplierrank() {
		return supplierrank;
	}

	public void setSupplierrank(Long supplierrank) {
		this.supplierrank = supplierrank;
	}

	public String getSellerStep() {
		return sellerStep;
	}

	public void setSellerStep(String sellerStep) {
		this.sellerStep = sellerStep;
	}
	

    public long getIsShowAddressValidate() {
		return isShowAddressValidate;
	}

	public void setIsShowAddressValidate(long isShowAddressValidate) {
		this.isShowAddressValidate = isShowAddressValidate;
	}

	public String getIsreadocean() {
		return isreadocean;
	}

	public void setIsreadocean(String isreadocean) {
		this.isreadocean = isreadocean;
	}

	public String getIsmemberonlyzone() {
		return ismemberonlyzone;
	}

	public void setIsmemberonlyzone(String ismemberonlyzone) {
		this.ismemberonlyzone = ismemberonlyzone;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the iswhitelist
	 */
	public String isIswhitelist() {
		return iswhitelist;
	}

	/**
	 * @param iswhitelist the iswhitelist to set
	 */
	public void setIswhitelist(String iswhitelist) {
		this.iswhitelist = iswhitelist;
	}

	public String getIspowerseller() {
        return ispowerseller;
    }

    public void setIspowerseller(String ispowerseller) {
        this.ispowerseller = ispowerseller;
    }

    public String getIsreadchina() {
        return isreadchina;
    }

    public void setIsreadchina(String isreadchina) {
        this.isreadchina = isreadchina;
    }

	public String getDomainname() {
		return domainname;
	}

	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSystemuserid() {
		return systemuserid;
	}

	public void setSystemuserid(String systemuserid) {
		this.systemuserid = systemuserid;
	}

	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

    public String getEmailisvalid() {
        return emailisvalid;
    }

    public void setEmailisvalid(String emailisvalid) {
        this.emailisvalid = emailisvalid;
    }

    public String getIsenterprise() {
        return isenterprise;
    }

    public void setIsenterprise(String isenterprise) {
        this.isenterprise = isenterprise;
    }

    public String getAuthstatus() {
        return authstatus;
    }

    public void setAuthstatus(String authstatus) {
        this.authstatus = authstatus;
    }

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    public String getPersonmobileno() {
        return personmobileno;
    }

    public void setPersonmobileno(String personmobileno) {
        this.personmobileno = personmobileno;
    }

    public String getPicurl1() {
        return picurl1;
    }

    public void setPicurl1(String picurl1) {
        this.picurl1 = picurl1;
    }

    public String getPicurl2() {
        return picurl2;
    }

    public void setPicurl2(String picurl2) {
        this.picurl2 = picurl2;
    }

    public String getPicurl3() {
        return picurl3;
    }

    public void setPicurl3(String picurl3) {
        this.picurl3 = picurl3;
    }

    public String getPersonalemailaddress() {
        return personalemailaddress;
    }

    public void setPersonalemailaddress(String personalemailaddress) {
        this.personalemailaddress = personalemailaddress;
    }

    public String getVerifydetailstatus() {
        return verifydetailstatus;
    }

    public void setVerifydetailstatus(String verifydetailstatus) {
        this.verifydetailstatus = verifydetailstatus;
    }

    public String getIdcardname() {
        return idcardname;
    }

    public void setIdcardname(String idcardname) {
        this.idcardname = idcardname;
    }

	public String getCookieString() {
		return cookieString;
	}

	public void setCookieString(String cookieString) {
		this.cookieString = cookieString;
	}

    public boolean isVipzoneUser() {
        return vipzoneUser;
    }

    public void setVipzoneUser(boolean vipzoneUser) {
        this.vipzoneUser = vipzoneUser;
    }

    public String getStoreopened() {
        return storeopened;
    }

    public void setStoreopened(String storeopened) {
        this.storeopened = storeopened;
    }

	public long getIsfactory() {
		return isfactory;
	}

	public void setIsfactory(long isfactory) {
		this.isfactory = isfactory;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFactoryregtype() {
		return factoryregtype;
	}

	public void setFactoryregtype(String factoryregtype) {
		this.factoryregtype = factoryregtype;
	}

	public boolean isHistoryFactory() {
		return isHistoryFactory;
	}

	public void setHistoryFactory(boolean isHistoryFactory) {
		this.isHistoryFactory = isHistoryFactory;
	}

	public String getFactorydetail() {
		return factorydetail;
	}

	public void setFactorydetail(String factorydetail) {
		this.factorydetail = factorydetail;
	}

	public String getImuser() {
		return imuser;
	}

	public void setImuser(String imuser) {
		this.imuser = imuser;
	}

	public String getNoReadmess() {
		return noReadmess;
	}

	public void setNoReadmess(String noReadmess) {
		this.noReadmess = noReadmess;
	}

	public String getNoReadLess() {
		return noReadLess;
	}

	public void setNoReadLess(String noReadLess) {
		this.noReadLess = noReadLess;
	}

	public String getQuickup() {
		return quickup;
	}

	public void setQuickup(String quickup) {
		this.quickup = quickup;
	}

	public String getChecktype() {
		return checktype;
	}

	public void setChecktype(String checktype) {
		this.checktype = checktype;
	}

	public String getIsSupportAttr() {
		return isSupportAttr;
	}

	public void setIsSupportAttr(String isSupportAttr) {
		this.isSupportAttr = isSupportAttr;
	}

	public String getIsSupportVIP() {
		return isSupportVIP;
	}

	public void setIsSupportVIP(String isSupportVIP) {
		this.isSupportVIP = isSupportVIP;
	}

	public long getEvaluatestatus() {
		return evaluatestatus;
	}

	public void setEvaluatestatus(long evaluatestatus) {
		this.evaluatestatus = evaluatestatus;
	}

//	public String getIsSupportProductReturn() {
//		return isSupportProductReturn;
//	}
//
//	public void setIsSupportProductReturn(String isSupportProductReturn) {
//		this.isSupportProductReturn = isSupportProductReturn;
//	}
//
//	public String getIsSupportOneDay() {
//		return isSupportOneDay;
//	}
//
//	public void setIsSupportOneDay(String isSupportOneDay) {
//		this.isSupportOneDay = isSupportOneDay;
//	}

    public long getServicelevel() {
        return servicelevel;
    }

    public void setServicelevel(long servicelevel) {
        this.servicelevel = servicelevel;
    }

	public String getIsreadhkpost() {
		return isreadhkpost;
	}

	public void setIsreadhkpost(String isreadhkpost) {
		this.isreadhkpost = isreadhkpost;
	}

//    public String getIscanoverwinter() {
//        return iscanoverwinter;
//    }
//
//    public void setIscanoverwinter(String iscanoverwinter) {
//        this.iscanoverwinter = iscanoverwinter;
//    }

	public String getCurlevel() {
		return curlevel;
	}

	public void setCurlevel(String curlevel) {
		this.curlevel = curlevel;
	}

//	public long getIsDhportUser() {
//        return isDhportUser;
//    }
//
//    public void setIsDhportUser(long isDhportUser) {
//        this.isDhportUser = isDhportUser;
//    }


	public String getMaxlevel() {
		return maxlevel;
	}

	public void setMaxlevel(String maxlevel) {
		this.maxlevel = maxlevel;
	}

	public String getOperateor() {
		return operateor;
	}

	public void setOperateor(String operateor) {
		this.operateor = operateor;
	}

	public String getSuppliersubid() {
		return suppliersubid;
	}

	public void setSuppliersubid(String suppliersubid) {
		this.suppliersubid = suppliersubid;
	}


    public long getIsSkuSupplier() {
        return isSkuSupplier;
    }

    public void setIsSkuSupplier(long isSkuSupplier) {
        this.isSkuSupplier = isSkuSupplier;
    }

	public String getPasswordupdateshow() {
		return passwordupdateshow;
	}

	public void setPasswordupdateshow(String passwordupdateshow) {
		this.passwordupdateshow = passwordupdateshow;
	}

	public String getInitLeftShow() {
		return initLeftShow;
	}

	public void setInitLeftShow(String initLeftShow) {
		this.initLeftShow = initLeftShow;
	}

	public String getMobileisvalid() {
		return mobileisvalid;
	}

	public void setMobileisvalid(String mobileisvalid) {
		this.mobileisvalid = mobileisvalid;
	}

	public String getIsreadsingaporepost() {
		return isreadsingaporepost;
	}

	public void setIsreadsingaporepost(String isreadsingaporepost) {
		this.isreadsingaporepost = isreadsingaporepost;
	}

    public boolean isCloseNewBuyer() {
        return closeNewBuyer;
    }

    public void setCloseNewBuyer(boolean closeNewBuyer) {
        this.closeNewBuyer = closeNewBuyer;
    }

	public String getIsSupportDHPort() {
		return isSupportDHPort;
	}

	public void setIsSupportDHPort(String isSupportDHPort) {
		this.isSupportDHPort = isSupportDHPort;
	}

	public String getL2supplierid() {
		return l2supplierid;
	}

	public void setL2supplierid(String l2supplierid) {
		this.l2supplierid = l2supplierid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

	public int getNoReadProduct() {
		return noReadProduct;
	}

	public void setNoReadProduct(int noReadProduct) {
		this.noReadProduct = noReadProduct;
	}

	public int getNoReadOrder() {
		return noReadOrder;
	}

	public void setNoReadOrder(int noReadOrder) {
		this.noReadOrder = noReadOrder;
	}

	public int getNoReadSystem() {
		return noReadSystem;
	}

	public void setNoReadSystem(int noReadSystem) {
		this.noReadSystem = noReadSystem;
	}

	public int getNoReadDustbin() {
		return noReadDustbin;
	}

	public void setNoReadDustbin(int noReadDustbin) {
		this.noReadDustbin = noReadDustbin;
	}

	

    public String getUnReadCsmsg() {
        return unReadCsmsg;
    }

    public void setUnReadCsmsg(String unReadCsmsg) {
        this.unReadCsmsg = unReadCsmsg;
    }

	public String getIsGoldUser() {
		return isGoldUser;
	}

	public void setIsGoldUser(String isGoldUser) {
		this.isGoldUser = isGoldUser;
	}

}
