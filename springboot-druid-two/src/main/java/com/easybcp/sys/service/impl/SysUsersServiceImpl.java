package com.easybcp.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easybcp.sys.dao.base.SysUsersMapper;
import com.easybcp.sys.model.SysUsers;
import com.easybcp.sys.service.SysUsersService;

@Service
public class SysUsersServiceImpl implements SysUsersService{

	@Autowired
    private SysUsersMapper usersBeanMapper;
	
	@Transactional(value="primaryTransactionManager")
	@Override
	public int delete(Long id) {
		 usersBeanMapper.deleteByPrimaryKey(id);
		 throw new RuntimeException("delete test");
	}

	@Override
	public int insert(SysUsers record) {
		return usersBeanMapper.insert(record);
	}

	@Override
	public SysUsers selectOne(Long id) {
		return usersBeanMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysUsers> selectAll() {
		return usersBeanMapper.selectAll();
	}

	@Override
	public int update(SysUsers record) {
		return usersBeanMapper.updateByPrimaryKey(record);
	}

	@Transactional(propagation=Propagation.REQUIRED,value="primaryTransactionManager")
	@Override	
	public synchronized  int save(SysUsers record) {
		SysUsers bean=selectOne(record.getId());
		if(bean!=null)
		{
			return update(record);
		}
		else
		{
			return insert(record);
		}
	}    
	
	@Transactional(value="primaryTransactionManager")
	@Override
	public  int test(SysUsers record) {
		SysUsers bean=new SysUsers();
		bean.setId(1L);
		bean.setUsername("xxxxx");
		bean.setNickname("xxxxxx");
		update(bean);
		return insert(record);
	}    
	
}