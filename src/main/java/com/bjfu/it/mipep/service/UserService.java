package com.bjfu.it.mipep.service;

import com.bjfu.it.mipep.bean.User;

import com.bjfu.it.mipep.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public List<User> getAllUser() {
        List<User> users=userMapper.selectAllUsers();
        return users;

    }

    public void deleteUserByUserid(String userid) {
        userMapper.deleteByPrimaryKey(userid);
    }
}
