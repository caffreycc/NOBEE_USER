<%--
  Created by IntelliJ IDEA.
  User: songj
  Date: 2017/6/19
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../common/common.jsp"%>

<!-- 引入基本插件样式文件 -->
<link href="<%=contextPath%>/static/metronic/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=contextPath%>/static/metronic/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="<%=contextPath%>/static/metronic/global/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="<%=contextPath%>/static/metronic/global/css/plugins.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=contextPath%>/static/metronic/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css"/>
<link href="<%=contextPath%>/static/css/base_style.css" rel="stylesheet" type="text/css"/>
<!-- 引入基本插件样式结束 -->
<div id="app" v-show="init" style="...">
    <!-- 布局：其中可以通过组件中slot的name往该位置插入html -->
    <my-portlet>
        <!-- 标题 -->
        <span slot="title">图库管理</span>
        <!-- 展开收缩 -->
        <a href="" slot="tools" class="collapse"></a>
        <div slot="body" class="form">
            <my-form ref="queryForm" class="form-horizontal" method="post">
                <div class="form-body">
                    <div class="row">
                        <div class="col-md-6">
                            <my-confirmation-btn title="是否确定重置业务字典？" ok-label="确定" cancel-label="取消" @ok="synchDictInfo">重置业务字典
                                <i class="fa fa-remove"></i>
                            </my-confirmation-btn>
                        </div>
                    </div>
                </div>
            </my-form>
        </div>
        <div slot="body" class="form">
            <my-datatables ref="datatables" :columns="columns" :ajax-url="queryUrl" :ajax-data="queryData"></my-datatables>
        </div>
    </my-portlet>
</div>
<input id="page-script" type="hidden" value="<%=contextPath%>/static/app/dict_info/dict_info_managed.js" />