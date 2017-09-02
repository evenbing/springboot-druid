package com.easybcp.sys.dao.base;

import com.easybcp.sys.model.SysUsers;
import java.util.List;

public interface SysUsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUsers record);

    SysUsers selectByPrimaryKey(Long id);

    List<SysUsers> selectAll();

    int updateByPrimaryKey(SysUsers record);
}