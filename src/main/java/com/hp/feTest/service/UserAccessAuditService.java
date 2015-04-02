package com.hp.feTest.service;

import com.hp.feTest.dao.UserAccessAuditMapper;
import com.hp.feTest.domain.UserAccessAudit;
import com.hp.feTest.domain.UserAccessAuditChart;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rajagova
 * Date: 10/1/12
 * Time: 11:45 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("UserAccessAuditService")
@Transactional(readOnly = false)
public class UserAccessAuditService {

    private Log logger = LogFactory.getLog(getClass());

    @Autowired
    private UserAccessAuditMapper userAccessAuditMapper;

    private int insertAudit(UserAccessAudit userAccessAudit) {
        return userAccessAuditMapper.insertAudit(userAccessAudit);
    }

    public List<UserAccessAudit> getUserAccessList(String dateFilter) {
        return (List<UserAccessAudit>)userAccessAuditMapper.getUserAccessList(dateFilter);
    }
    
    public List<UserAccessAuditChart> getUserAccessChartData(){
        return (List<UserAccessAuditChart>)userAccessAuditMapper.getUserAccessChartData();
    }

    public void insertAudit(String actionType) {
        try {
            logger.info("Audit Message was successfully updated: " + actionType);
        } catch (Exception e) {
            logger.error("Fail to update Audit Log.");
            e.printStackTrace();
        }
    }
}
