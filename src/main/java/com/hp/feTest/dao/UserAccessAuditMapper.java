package com.hp.feTest.dao;

import com.hp.feTest.domain.UserAccessAudit;
import com.hp.feTest.domain.UserAccessAuditChart;

import java.util.List;


public interface UserAccessAuditMapper {

    int insertAudit(UserAccessAudit userAccessAudit);

    List<UserAccessAuditChart> getUserAccessChartData();

    List<UserAccessAudit> getUserAccessList(String value);
}
