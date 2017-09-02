package com.easybcp.biz.dao.base;

import com.easybcp.biz.model.BizUsers;
import java.util.List;

public interface BizUsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BizUsers record);

    BizUsers selectByPrimaryKey(Long id);

    List<BizUsers> selectAll();

    int updateByPrimaryKey(BizUsers record);
}