/**
 * Created by aixiaoai on 2016/10/8.
 */
$(function(){
    //进度条结束
    Pace.stop();

    App.init();

    //初始化tab
    Index.initTabLoad();

    var contextPath = $('#contextPath').val();
    
    var mpId = $('#mpId').val();

    //加载公众号信息
    //loadMpInfo(mpId);

    //加载公众号信息
    function loadMpInfo(mpId) {
        $.post(contextPath + "/micro/getMpInfo", { mpId: mpId }, function (response) {
            if (response.code === 0) {
                $('#mpName').text(response.data.microName);
            } else {
                toastr.error(response.message, "获取错误");
            }
        });
    }

    //默认加载第一个tab页
    Index.loadTab(contextPath + "/page/mp/wxMenu/wxMenuManaged.jsp?type=none", "wxMenu");

})