<%--
  Created by IntelliJ IDEA.
  User: aixiaoai
  Date: 2016/10/8
  Time: 上午10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../common/common.jsp"%>
<link href="<%=contextPath%>/static/metronic/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
<link href="<%=contextPath%>/static/metronic/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
<link href="<%=contextPath%>/static/metronic/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
<!-- BEGIN PAGE LEVEL PLUGINS -->
<!-- END PAGE LEVEL PLUGINS -->
<link href="<%=contextPath%>/static/css/wxMenu/wxMenu.css" rel="stylesheet" type="text/css" />
<link href="<%=contextPath%>/static/css/base_style.css" rel="stylesheet" type="text/css" />
<%--<input id="mpId" type="hidden" value="<%=request.getParameter("mpId")%>" />--%>
<div class="portlet light bordered">
    <div class="portlet-title tabbable-line">
        <div class="caption">
            <i class="fa fa-weixin font-blue-sharp"></i>
            <span id="mpName" class="caption-subject font-blue sbold uppercase"></span>
        </div>
        <ul class="nav nav-tabs">
            <li class="dropdown active">
                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> 公众号菜单
                    <i class="fa fa-angle-down"></i>
                </a>
                <ul class="dropdown-menu" role="menu">
                    <li>
                        <a href="#wxMenu" tabId="wxMenu" tabUrl="<%=contextPath%>/page/mp/wxMenu/wxMenuManaged.jsp?type=none" data-toggle="tab"> 自定义菜单 </a>
                    </li>
                    <li>
                        <a href="#wxMenu" tabId="wxMenu" tabUrl="<%=contextPath%>/page/mp/wxMenu/wxMenuManaged.jsp?type=condition" data-toggle="tab"> 个性化菜单 </a>
                    </li>
                </ul>
            </li>

            <li class="dropdown">
                <a href="#qrCodeManaged" tabId="qrCodeManaged" tabUrl="<%=contextPath%>/page/mp/qrCodeManaged/qrCodeManaged.jsp" tabindex="-1" data-toggle="tab"> 二维码管理 </a>
            </li>

            <li>
                <a href="#subLogsManaged" tabId="subLogsManaged" tabUrl="<%=contextPath%>/page/mp/subLogsManaged/subLogsManaged.jsp" data-toggle="tab"> 关注日志 </a>
            </li>
        </ul>
    </div>
    <div class="portlet-body">
        <div class="row">
            <div class="col-md-12">
                <div class="tab-content">
                    <div class="tab-pane active" id="wxMenu"></div>
                    <div class="tab-pane fade" id="qrCodeManaged"></div>
                    <div class="tab-pane fade" id="subLogsManaged">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<input id="page-script" type="hidden" value="<%=contextPath%>/static/app/image_lib/functionManaged/functionManaged.js" />