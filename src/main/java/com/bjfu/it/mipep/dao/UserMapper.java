package com.bjfu.it.mipep.dao;

import com.bjfu.it.mipep.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userid);

    List<User> selectAllUsers();
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}