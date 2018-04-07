window.onscroll = function () {
    scrollFunction()
};
(function () {
    var btn = "<ul class='layui-fixbar'><li class=\"top-btn layui-icon layui-fixbar-top \" lay-type=\"top\">&#xe619;</li></ul>";
    $('body').append(btn);
})();


function scrollFunction() {
    console.log(121);
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        $(".top-btn")[0].style.display = "block";
    } else {
        $(".top-btn")[0].style.display = "none";
    }
}

// 点击按钮，返回顶部
$(".top-btn").on("click", function () {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
});
/*设置本地存储，当用户登录显示用户个人中心*/
(function () {

    var Login = layui.sessionData('unLogin');

    if (true != Login.statue) {
        /*
        <a href="javascript:;">我的</a>
                <dl class="layui-nav-child">
                    <dd><a href="./my/my.html">个人中心</a></dd>
                    <dd><a href="./my/message.html">留言消息</a></dd>
                    <dd><a href="./my/setting.html#password">修改密码</a></dd>
                    <dd><a href="./my/friends.html">我的关注</a></dd>
                    <dd><a href="./login-out">退出</a></dd>
                </dl>


        */
        var unLogin =
            "            <li class=\"layui-nav-item \" style=\"float: right\">\n" +
            "                <a href=\"./register.html\">注册</a>\n" +
            "<li class=\"layui-nav-item \" style=\"float: right\">\n" +
            "                <a href=\"./login.html\">登录</a>\n" +
            "            </li>\n" +
            "            </li>";
        $(".layui-layout-right").replaceWith(unLogin);
    } else {
        var login = "<a href=\"javascript:;\">我的</a>\n" +
            "                <dl class=\"layui-nav-child\">\n" +
            "                    <dd><a href=\"./my/my.html\">个人中心</a></dd>\n" +
            "                    <dd><a href=\"./my/message.html\">留言消息</a></dd>\n" +
            "                    <dd><a href=\"./my/setting.html#password\">修改密码</a></dd>\n" +
            "                    <dd><a href=\"./my/friends.html\">我的关注</a></dd>\n" +
            "                    <dd><a href=\"javascript:void(0);\" class='logout'>退出</a></dd>\n" +
            "                </dl>";
        $(".layui-layout-right").html(login);
        $(".logout").on('click', function () {
            layui.sessionData('unLogin', {key: 'statue', value: true});
            window.location.href = "./logout";
        });
    }

    layui.use('element', function () {
        var element = layui.element;
        element.init();
    });

})();
