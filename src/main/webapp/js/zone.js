
layui.use(['element', 'jquery'], function () {
    var element = layui.element;
    var $ = layui.jquery;
    var userInfo;
    try {
        userInfo = $.cookie("user")?$.cookie("user").user:false;
    }catch (e){
        userInfo = null;
    }


    if (true || $.isEmptyObject(userInfo)) {
        /*0表示自身ID*/
        $.ajaxSettings.async = false;
        $.get("/user/info/0", function (res) {
            if (res.status !== 0) {
                $.removeCookie("user");
                $.removeCookie('unLogin');
                $.removeCookie("user",{path:"/my"});
                $.removeCookie('unLogin',{path:"/"});
                window.location.href = "../login.html";
                return false;
            }
            $.cookie("user", {  'user': res});
            userInfo = res;
            setting.show(userInfo);
            if (userInfo) {
                if (userInfo.status === 0) {
                    var sex = userInfo['data'].sex;

                    $("input[title=" + sex + "]").attr("checked", true);
                    $("#date").val(new Date(userInfo['data'].birth).format("yyyy-mm-dd"));
                    $("input[name=city]").val(userInfo['data'].city);
                    $("textarea[name=indroduce]").val(userInfo['data'].indroduce);
                } else {
                    layer.alert(userInfo.msg);
                }
            }
        });
    }
});


