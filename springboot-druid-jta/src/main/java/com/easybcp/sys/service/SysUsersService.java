package com.easybcp.sys.service;

import java.util.List;

import com.easybcp.sys.model.SysUsers;

public interface SysUsersService {
	int delete(Long id);

	int insert(SysUsers record);

	SysUsers selectOne(Long id);

	List<SysUsers> selectAll();

	int update(SysUsers record);

	int save(SysUsers record);

	int test(SysUsers record);
}