package com.easybcp.biz.service;

import java.util.List;

import com.easybcp.biz.model.BizUsers;

public interface BizUsersService {
	int delete(Long id);

	int insert(BizUsers record);

	BizUsers selectOne(Long id);

	List<BizUsers> selectAll();

	int update(BizUsers record);

	int save(BizUsers record);

	int test(BizUsers record);
}