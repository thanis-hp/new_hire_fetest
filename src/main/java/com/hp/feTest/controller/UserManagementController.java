package com.hp.feTest.controller;

import com.hp.feTest.domain.UserAccessAudit;
import com.hp.feTest.domain.UserAccessAuditChart;
import com.hp.feTest.domain.UserGridObject;
import com.hp.feTest.service.UserAccessAuditService;
import com.hp.feTest.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserManagementController {

    private Log logger = LogFactory.getLog(getClass());

    private UserService service;

    private UserAccessAuditService userAccessAuditService;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @Autowired
    public void setUserAccessAuditService(UserAccessAuditService service) {
        this.userAccessAuditService = service;
    }


    @RequestMapping(value = "/getUserAccessList.htm", method = RequestMethod.GET)
    @ResponseBody
    public HashMap getUserAccessList(@RequestParam String dateFilter) {
        logger.info("GETUSERACCESSLIST: DateFilter" + dateFilter);
        List<UserAccessAudit> userAccessAudits = userAccessAuditService.getUserAccessList(dateFilter);

        logger.info("GETUSERACCESSLIST: List:" + userAccessAudits.size());

        HashMap<String, List<UserAccessAudit>> modelMap = new HashMap<String, List<UserAccessAudit>>();
        modelMap.put("accessList", userAccessAudits);
        logger.info("GETUSERACCESSLIST: " + userAccessAudits.toString());
        return modelMap;
    }

    @RequestMapping(value = "/getUserAccessChart.htm", method = RequestMethod.GET)
    @ResponseBody
    public HashMap getUserAccessChart() {
        List<UserAccessAuditChart> userAccessAuditChartData = userAccessAuditService.getUserAccessChartData();

        HashMap<String, List<UserAccessAuditChart>> modelMap = new HashMap<String, List<UserAccessAuditChart>>();
        modelMap.put("accessChartData", userAccessAuditChartData);
        logger.info(userAccessAuditChartData);
        return modelMap;
    }

    @RequestMapping(value = "/getUserList.htm", method = RequestMethod.GET)
    @ResponseBody
    public HashMap getUserList() {
        List<UserGridObject> userGridObjects = service.getUserList();

        HashMap<String, List<UserGridObject>> modelMap = new HashMap<String, List<UserGridObject>>();
        modelMap.put("users", userGridObjects);
        logger.info(userGridObjects);
        return modelMap;
    }

    @RequestMapping(value = "updateUsers.htm", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> updateUsers(@RequestParam String dataArray) {
        String result = "{success:true}";

        try {

            JSONArray jsonMainArr = new JSONObject(dataArray).getJSONArray("users");

            logger.info("JSON Array: " + jsonMainArr.toString());
            for (int i = 0; i < jsonMainArr.length(); i++) {
                JSONObject childJSONObject = jsonMainArr.getJSONObject(i);

                UserGridObject userGridObject = new UserGridObject();
                userGridObject.setUserId(childJSONObject.getString("userId"));
                userGridObject.setName(childJSONObject.getString("name"));
                userGridObject.setDept(childJSONObject.getString("dept"));
                userGridObject.setRole(childJSONObject.getString("role"));
                userGridObject.setEnabled(childJSONObject.getString("enabled"));


                logger.info("Updating Users: " + userGridObject.toString());
                service.updateUsers(userGridObject);

                logger.info("Updating Users Role: " + userGridObject.toString() + "|");
                int test = service.updateRolebyUserId(userGridObject);
                logger.info("Updating Users Role: " + userGridObject.toString() + "|" +test);
                if (test <= 0) {
                    service.insertRoleForUser(userGridObject);
                }
            }
            userAccessAuditService.insertAudit("USER:UPDATE");
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result = "{success:false}";
            return new ResponseEntity<String>(result, HttpStatus.OK);
        }
    }

    public static String md5(String input) {
        String md5 = null;
        if(null == input) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);

            if(md5.length() < 32) md5 = "0" + md5;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    @RequestMapping(value = "insertNewUsers.htm", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> insertNewUsers(@RequestParam String name, @RequestParam String userName,
                                                 @RequestParam String dept, @RequestParam String role) {
        String result = "{success:true}";
        try {

            if (null == name || null == userName || null == dept || null == role) {
                result = "{success:false}";
                return new ResponseEntity<String>(result, HttpStatus.OK);
            }
            
            String userId = Long.toString(service.getUserId());

            UserGridObject userGridObject = new UserGridObject();
            userGridObject.setUserId(userId);
            userGridObject.setName(name);
            userGridObject.setUserName(userName);
            userGridObject.setDept(dept);
            userGridObject.setRole(role);
            userGridObject.setPassword(md5("password"));

            logger.info("Inserting User: " + userGridObject.toString());
            service.insertNewUser(userGridObject);
            service.insertRoleForUser(userGridObject);
            result = "{success:true}";
        } catch (Exception e) {
            e.printStackTrace();
            result = "{success:false}";
        }

        userAccessAuditService.insertAudit("USER:NEW");
        return new ResponseEntity<String>(result, HttpStatus.OK);


    }


    @RequestMapping(value = "getUserTree.htm", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<String> getUserAccessTree(Model model) {
        String result = "{text: '.', children:[{ text: 'Welcome Tester | Role: Test', expanded:true, children:[{ text: 'Administration', expanded:true, children:[{ text: 'User', expanded:true, children:[{ text: 'User Management', id: 'userMgmt', leaf: true }]}]}]}]}";
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "updatePassword.htm", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> updatePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        String result = "{success:false}";

        User test = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String remoteAddr = ((WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getRemoteAddress();

        try {
            logger.info("User Details:: " + test.toString());

            UserGridObject userGridObject = new UserGridObject();
            userGridObject.setUserName(test.getUsername());
            userGridObject.setPassword(md5(oldPassword));

            logger.info("Check Valid User: " + userGridObject.toString());

            if (service.checkUserValid(userGridObject)) {
                userGridObject.setPassword(md5(newPassword));
                logger.info("Updating Users: " + userGridObject.toString());
                service.updatePassword(userGridObject);
                userAccessAuditService.insertAudit("USER:CHANGE PASSWORD|"+remoteAddr);
                result = "{success:true}";
                return new ResponseEntity<String>(result, HttpStatus.OK);
            }


        } catch (Exception e) {
            e.printStackTrace();
            result = "{success:false}";
        }
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "updateNewPassword.htm", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> updateNewPassword(@RequestParam String newPassword) {
        String result;

        User test = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String remoteAddr = ((WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getRemoteAddress();

        try {
            logger.info("User Details:: " + test.toString());

            UserGridObject userGridObject = new UserGridObject();
            userGridObject.setUserName(test.getUsername());
            userGridObject.setPassword(md5(newPassword));

            logger.info("Updating Users: " + userGridObject.toString());
            service.updateNewPassword(userGridObject);
            userAccessAuditService.insertAudit("USER:NEW PASSWORD|" + remoteAddr);
            result = "{success:true}";
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result = "{success:false}";
        }
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }


}
