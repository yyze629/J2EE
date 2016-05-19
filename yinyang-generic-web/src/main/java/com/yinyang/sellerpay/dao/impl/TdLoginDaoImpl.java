package com.yinyang.sellerpay.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhgate.common.util.Page;
import com.dhgate.common.util.StringUtil;
import com.dhgate.framework.service.dao.GenericDao;
import com.yinyang.sellerpay.dao.api.dto.PayBusinessDTO;
import com.yinyang.sellerpay.dao.api.dto.TdLoginDTO;
import com.yinyang.sellerpay.dao.po.TdLogin;
import com.yinyang.sellerpay.dao.po.TdPayBusiness;

//@Repository("loginDao")
@Service
public class TdLoginDaoImpl {

    @Resource(name = "dhSellerPayDao")
    private GenericDao dao;

	@Transactional
    public void createTdLogin(TdLoginDTO tdLoginDTO) {
        if (tdLoginDTO == null) {
            return;
        }
        // tdLoginDTO.setCreateDate(new Date());
        TdLogin po = new TdLogin();
        po.dto2po(tdLoginDTO);
        dao.save(po);
		//tdLoginDTO.set${pkFieldVO.CaptialFieldName}(po.get${pkFieldVO.CaptialFieldName}());
    }
	
	@Transactional
    public void updateTdLogin(TdLoginDTO tdLoginDTO) {
        if (tdLoginDTO == null) {// ||tdLoginDTO.get${pkFieldVO.CaptialFieldName}() == null) {
            return;
        }
        // if (StringUtil.isEmpty(tdLoginDTO.getOperator())) {
        //	throw new RuntimeException("operator is null");
        // }
		// tdLoginDTO.setOperateDate(new Date());
		
        TdLogin po = (TdLogin) dao.get(TdLogin.class, tdLoginDTO); //.get${pkFieldVO.CaptialFieldName}());
        if (po == null) {
            return;
        }
        po.dtoMerge2Po(tdLoginDTO);
        dao.update(po);
    }
	
	@Transactional
    public void saveOrUpdateTdLogin(TdLoginDTO tdLoginDTO) {
        if (tdLoginDTO == null){ // ||tdLoginDTO.get${pkFieldVO.CaptialFieldName}() == null) {
            return;
        }
        TdLogin po = this.getTdLogin(tdLoginDTO);
        if (po == null) {
		
            this.createTdLogin(tdLoginDTO);
			
        } else {
		
            po.dtoMerge2Po(tdLoginDTO);
            dao.update(po);
		}
    }
	
	@Transactional
    public void deleteTdLogin(TdLoginDTO tdLoginDTO) {
        if (tdLoginDTO == null) {
            return;
        }
        TdLogin po = dao.get(TdLogin.class,tdLoginDTO);
		if (po == null) {
			return;
		}
		dao.delete(po);
    }	
	
	@Transactional(readOnly = true)
    public TdLogin getTdLogin(TdLoginDTO tdLoginDTO){
        if (tdLoginDTO == null){
            return null;
        }
        TdLogin po = dao.get(TdLogin.class, tdLoginDTO);
        return po;
    }
	
	@Transactional(readOnly = true)
	public TdLoginDTO getLoginByIdAndName(String loginName,String loginPassword){
		TdLoginDTO dto = null;
		if(StringUtils.isBlank(loginName) || StringUtils.isBlank(loginPassword)){
			return dto;
		}
		
        HashMap<String, Object> params = new HashMap<String, Object>();
        StringBuilder hql = new StringBuilder(" from TdLogin a where  1 = 1 ");
	
        hql.append(" and a.isdeleted = :isdeleted");
        params.put("isdeleted", "1");
        
        if(StringUtil.notEmpty(loginName)) {
        	hql.append(" and a.loginName = :loginName");
        	params.put("loginName", loginName);
        }
        
        if(StringUtil.notEmpty(loginPassword)) {
        	hql.append(" and a.loginPassword = :loginPassword");
        	params.put("loginPassword", loginPassword);
        }
        List<TdLoginDTO> query = dao.query(hql.toString(), params);
        
		if(query == null || query.size()<=0){
			return null;
		}
		dto = query.get(0);
		
		return dto;
	}
	
	@Transactional(readOnly = true)
    public List<TdLogin> findTdLoginList(TdLogin qdto) {
		
        if (qdto == null) {
            return null;
        }
		
        StringBuilder hql = new StringBuilder("from TdLogin a where 1 = 1 ");
        Map<String, Object> params = new HashMap<String, Object>();
	
        if(StringUtil.notEmpty(qdto.getLoginid())) {
            hql.append(" and a.loginid = :loginid");
            params.put("loginid", qdto.getLoginid());
        }
	
        if(StringUtil.notEmpty(qdto.getLoginname())) {
            hql.append(" and a.loginname = :loginname");
            params.put("loginname", qdto.getLoginname());
        }
	
        if(StringUtil.notEmpty(qdto.getLoginpassword())) {
            hql.append(" and a.loginpassword = :loginpassword");
            params.put("loginpassword", qdto.getLoginpassword());
        }
	
        if(StringUtil.notEmpty(qdto.getPlaintext())) {
            hql.append(" and a.plaintext = :plaintext");
            params.put("plaintext", qdto.getPlaintext());
        }
	
        if (qdto.getUpdatetime() != null) {
            hql.append(" and a.updatetime = :updatetime");
            params.put("updatetime", qdto.getUpdatetime());
        }
	
        if (qdto.getCreatetime() != null) {
            hql.append(" and a.createtime = :createtime");
            params.put("createtime", qdto.getCreatetime());
        }
	
        if(StringUtil.notEmpty(qdto.getIsdeleted())) {
            hql.append(" and a.isdeleted = :isdeleted");
            params.put("isdeleted", qdto.getIsdeleted());
        }

        return dao.query(hql.toString(), params);
    }
	
	
	@Transactional(readOnly = true)
    public Page findTdLoginDTOPage(TdLogin qdto, int pageNo, int pageSize) {
		
        if (qdto == null) {
            return null;
        }
		
        HashMap<String, Object> params = new HashMap<String, Object>();
        StringBuilder hql = new StringBuilder(" from TdLogin a where  1 = 1 ");
	
        if(StringUtil.notEmpty(qdto.getLoginid())) {
            hql.append(" and a.loginid = :loginid");
            params.put("loginid", qdto.getLoginid());
        }
	
        if(StringUtil.notEmpty(qdto.getLoginname())) {
            hql.append(" and a.loginname = :loginname");
            params.put("loginname", qdto.getLoginname());
        }
	
        if(StringUtil.notEmpty(qdto.getLoginpassword())) {
            hql.append(" and a.loginpassword = :loginpassword");
            params.put("loginpassword", qdto.getLoginpassword());
        }
	
        if(StringUtil.notEmpty(qdto.getPlaintext())) {
            hql.append(" and a.plaintext = :plaintext");
            params.put("plaintext", qdto.getPlaintext());
        }
	
        if (qdto.getUpdatetime() != null) {
            hql.append(" and a.updatetime = :updatetime");
            params.put("updatetime", qdto.getUpdatetime());
        }
	
        if (qdto.getCreatetime() != null) {
            hql.append(" and a.createtime = :createtime");
            params.put("createtime", qdto.getCreatetime());
        }
	
        if(StringUtil.notEmpty(qdto.getIsdeleted())) {
            hql.append(" and a.isdeleted = :isdeleted");
            params.put("isdeleted", qdto.getIsdeleted());
        }

        Page result = dao.getPage(hql.toString(), pageNo, pageSize, params);
		if(result == null || result.getResult() != null || result.getResult().size() < 0){
			 return result;
		}
		List<TdLoginDTO> list = new ArrayList<TdLoginDTO>();
		List<TdLogin> resultList = result.getResult();
		for(TdLogin p: resultList){
			 list.add(p.po2dto());
		}
		result.setResult(list);
		
        return result;
    }
}