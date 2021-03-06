<%--
  Created by IntelliJ IDEA.
  User: rajagova
  Date: 8/16/12
  Time: 12:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String contextPath = request.getContextPath();
%>
<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">--%>
<!DOCTYPE html>

<html>
<head>
    <title>FE Test Module for New Hire v1.01 @CMS - Hewlett Packard Sales (M) Sdn Bhd</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/scripts/ext.js/4.1.1/resources/css/ext-all.css">
    <meta http-equiv="X-UA-Compatible" content="IE=5, IE=8, IE=9, IE=10" >


    <style type="text/css">
        .ux-filtered-column {
            font-style: italic !important;
            font-weight: bold !important;
        }

        .ux-gridfilter-text-icon {
            background-image: url(<%=contextPath%>/images/find.png) !important;
        }

        .ux-rangemenu-icon {
            display: block;
            height: 16px;
            background: no-repeat 5px center;
        }

        .ux-rangemenu-gt {
            background-image: url(<%=contextPath%>/images/greater_than.png) !important;
        }

        .ux-rangemenu-lt {
            background-image: url(<%=contextPath%>/images/less_than.png) !important;
        }

        .ux-rangemenu-eq {
            background-image: url(<%=contextPath%>/images/equals.png) !important;
        }

        .icon-grid {
            background-image:url(<%=contextPath%>/images/grid.png) !important;
        }
        .icon-refresh {
            background-image:url(<%=contextPath%>/images/table_refresh.png) !important;
        }
        .icon-add-user {
            background-image:url(<%=contextPath%>/images/user_add.gif) !important;
        }
        .icon-sync {
            background-image:url(<%=contextPath%>/images/sync2.png) !important;
        }
        .icon-save {
            background-image:url(<%=contextPath%>/images/save.png) !important;
        }
        .icon-search {
            background-image:url(<%=contextPath%>/images/search16.png) !important;
        }
        .icon-reset {
            background-image:url(<%=contextPath%>/images/undo.png) !important;
        }
        .icon-back {
            background-image:url(<%=contextPath%>/images/back-icon.png) !important;
        }
        .icon-app-go {
            background-image:url(<%=contextPath%>/images/application_go.png) !important;
        }
        .icon-remove {
            background-image:url(<%=contextPath%>/images/remove.png) !important;
        }
        .icon-add {
            background-image:url(<%=contextPath%>/images/add.png) !important;
        }
        .icon-deploy {
            background-image:url(<%=contextPath%>/images/bottom2.gif) !important;
        }
        .icon-clearfilter {
            background-image:url(<%=contextPath%>/images/clear_filter.png) !important;
            height: 16px;
            width: 16px;
        }

        .icon-trash {
            background-image:url(<%=contextPath%>/images/trash.png) !important;
            height: 16px;
            width: 16px;
        }

        .icon-undeploy {
            background-image:url(<%=contextPath%>/images/undo2.png) !important;
            height: 16px;
            width: 16px;
        }

        .icon-filter {
            background-image:url(<%=contextPath%>/images/filter.png) !important;
            height: 16px;
            width: 16px;
        }

        .x-action-col-icon {
            height: 16px;
            width: 16px;
            margin-right: 8px;
        }

        .bmenu {
            background-image: url(<%=contextPath%>/images/menu-show.gif) !important;
        }

        .menu-title{
            background: #D6E3F2;
            border-style: solid;
            border-color:#DAE6F4 #99bbe8 #99bbe8 #DAE6F4;
            border-width: 1px;
            margin:-2px -2px 0;
            color:#15428b;
            font:bold 10px tahoma,arial,verdana,sans-serif;
            display:block;
            padding:3px;
        }

    </style>

    <script type="text/javascript" src="<%=contextPath%>/scripts/ext.js/4.1.1/ext-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/scripts/ext.js/startPanel.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/scripts/ext.js/newMainLayout.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/scripts/ext.js/userManagement.js"></script>
    <%-- <script type="text/javascript" src="<%=contextPath%>/scripts/ext.js/userAccessGrid.js"></script> --%>



    <script type="text/javascript">
        Ext.onReady(function(){
            startPanel();
        })
    </script>
</head>
<body>
    <div style="display:none;">

        <!-- Start page content -->
        <div id="start-div">
            <div style="float:left;" ><img src="<%=contextPath%>/images/HP.gif" /></div>
            <div style="margin-left:100px;">
                <p></p>
                <p>Select an item on the left to begin.</p>
            </div>
        </div>
    </div>
</body>
</html>