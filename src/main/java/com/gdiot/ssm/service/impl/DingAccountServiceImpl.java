package com.gdiot.ssm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdiot.ssm.dao.DingAccountMapper;
import com.gdiot.ssm.dao.DingDepDataMapper;
import com.gdiot.ssm.entity.DingAccountPo;
import com.gdiot.ssm.entity.DingDepPo;
import com.gdiot.ssm.service.IDingAccountService;
import com.gdiot.ssm.service.IDingDepDataService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhouHR
 */
@Slf4j
@Service("DingAccountService")
public class DingAccountServiceImpl implements IDingAccountService {

	@Autowired
	private DingAccountMapper mDingAccountMapper;

	@Override
	public int insertOne(DingAccountPo mDingAccountPo) {
		return mDingAccountMapper.insertOne(mDingAccountPo);
	}

	@Override
	public int countAccountList(String client) {
		// TODO Auto-generated method stub
		return mDingAccountMapper.countAccountList(client);
	}

	@Override
	public List<DingAccountPo> selectAccountList(String client,int pageNo,int pageSize) {
		int count = mDingAccountMapper.countAccountList(client);
		log.info("selectbyDevId lora wm count="+ count);
		int limit = pageSize;
		int offset = (pageNo-1) * pageSize;
		
		List<DingAccountPo> list = mDingAccountMapper.selectAccountList(client, limit, offset);
		return list;
	}
	
}
