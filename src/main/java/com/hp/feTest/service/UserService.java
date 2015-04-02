package com.hp.feTest.service;

import com.hp.feTest.dao.UserMapper;
import com.hp.feTest.domain.UserGridObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rajagova
 * Date: 8/16/12
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("UserService")
@Transactional(readOnly = false )
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public List<UserGridObject> getUserList() {
        return (List<UserGridObject>)userMapper.getUserList();
    }

    public int updateUsers(UserGridObject userGridObject) {
        return (int)userMapper.updateUsers(userGridObject);
    }

    public int updateRolebyUserId(UserGridObject userGridObject) {
        return (int)userMapper.updateRolebyUserId(userGridObject);
    }

    public long getUserId() {
        return (long)userMapper.getUserId();
    }

    public int insertNewUser(UserGridObject userGridObject) {
        return (int)userMapper.insertNewUser(userGridObject);
    }

    public int insertRoleForUser(UserGridObject userGridObject) {
        return (int)userMapper.insertRoleForUser(userGridObject);
    }

    public int updatePassword(UserGridObject userGridObject){
        return (int)userMapper.updatePassword(userGridObject);
    }

    public boolean checkUserValid(UserGridObject userGridObject){
        return 1 == userMapper.checkUserValid(userGridObject);
    }

    public int removeAllRoles(UserGridObject userGridObject){
        return (int)userMapper.removeAllRoles(userGridObject);
    }

    public boolean checkNewUser(String username) {
        return 1 == userMapper.checkNewUser(username);

    }

    public int updateNewPassword(UserGridObject userGridObject) {
        return (int)userMapper.updateNewPassword(userGridObject);
    }

    public UserGridObject getUser(String username){
        return (UserGridObject)userMapper.getUser(username);
    }
}
