package com.hp.feTest.dao;

import com.hp.feTest.domain.UserGridObject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rajagova
 * Date: 8/16/12
 * Time: 12:07 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserMapper {

    public List<UserGridObject> getUserList();

    public int updateUsers(UserGridObject userGridObject);

    public int updateRolebyUserId(UserGridObject userGridObject);

    public  long getUserId();

    public int insertNewUser(UserGridObject userGridObject);

    public int insertRoleForUser(UserGridObject userGridObject);

    public int updatePassword(UserGridObject userGridObject);

    public int checkUserValid(UserGridObject userGridObject);

    public int removeAllRoles(UserGridObject userGridObject);

    public int checkNewUser(String username);

    public int updateNewPassword(UserGridObject userGridObject);

    public UserGridObject getUser(String username);
}