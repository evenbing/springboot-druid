package com.easybcp.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easybcp.biz.dao.base.BizUsersMapper;
import com.easybcp.biz.model.BizUsers;
import com.easybcp.biz.service.BizUsersService;

@Service
public class BizUsersServiceImpl implements BizUsersService{

	@Autowired
    private BizUsersMapper usersBeanMapper;
	
	@Transactional(value="secondaryTransactionManager")
	@Override
	public int delete(Long id) {
		 usersBeanMapper.deleteByPrimaryKey(id);
		 throw new RuntimeException("delete test");
	}

	@Override
	public int insert(BizUsers record) {
		return usersBeanMapper.insert(record);
	}

	@Override
	public BizUsers selectOne(Long id) {
		return usersBeanMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BizUsers> selectAll() {
		return usersBeanMapper.selectAll();
	}

	@Override
	public int update(BizUsers record) {
		return usersBeanMapper.updateByPrimaryKey(record);
	}

	@Transactional(propagation=Propagation.REQUIRED,value="secondaryTransactionManager")
	@Override	
	public synchronized  int save(BizUsers record) {
		BizUsers bean=selectOne(record.getId());
		if(bean!=null)
		{
			return update(record);
		}
		else
		{
			return insert(record);
		}
	}    
	
	@Transactional(value="secondaryTransactionManager")
	@Override
	public  int test(BizUsers record) {
		BizUsers bean=new BizUsers();
		bean.setId(1L);
		bean.setUsername("xxxxx");
		bean.setNickname("xxxxxx");
		update(bean);
		return insert(record);
	}    
	
}