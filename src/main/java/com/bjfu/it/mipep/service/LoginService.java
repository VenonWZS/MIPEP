package com.bjfu.it.mipep.service;

import com.bjfu.it.mipep.bean.User;
import com.bjfu.it.mipep.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;
    public int loginService(String userid,String password)
    {
        User u=userMapper.selectByPrimaryKey(userid);
        if(u==null)
        {

            return 0;

        }
        else
        {
            if(u.getPassword().equals(password))
            {
                if(u.getUsertype().equals("admin"))
                    return 2;
                else{
                    return 1;
                }
            }
            else {

                return 0;
            }
        }
    }
    public User getLoginUser(String userid)
    {
        User u=userMapper.selectByPrimaryKey(userid);
        return u;
    }

    public int creatAccount(User user) {
        User u=userMapper.selectByPrimaryKey(user.getUserid());
        if(u==null)
        {
            userMapper.insert(user);
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
