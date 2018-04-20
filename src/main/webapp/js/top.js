
window.onscroll = function () {
    scrollFunction()
};
(function () {
    var btn = "<ul class='layui-fixbar'><li class=\"top-btn layui-icon layui-fixbar-top \" lay-type=\"top\">&#xe619;</li></ul>";
    $('body').append(btn);
})();

/**
 * 日期格式化（原型扩展或重载）
 * 格式 YYYY/yyyy/ 表示年份
 * MM/M 月份
 * dd/DD/d/D 日期
 * @param {formatStr} 格式模版
 * @type string
 * @returns 日期字符串
 */
Date.prototype.format = function (formatStr) {
    var str = formatStr;
    var Week = ['日', '一', '二', '三', '四', '五', '六'];
    str = str.replace(/yyyy|YYYY/, this.getFullYear());
    str = str.replace(/mm|MM/, (this.getMonth() + 1) > 9 ? (this.getMonth() + 1).toString() : '0' + (this.getMonth() + 1));
    str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
    return str;
}
Date.prototype.time = function () {
    return this.getFullYear()
        + "-" + (this.getMonth() > 8 ? (this.getMonth() + 1) : "0" + (this.getMonth() + 1))
        + "-" + (this.getDate() > 9 ? this.getDate() : "0" + this.getDate())
        + " " + (this.getHours() > 9 ? this.getHours() : "0" + this.getHours())
        + ":" + (this.getMinutes() > 9 ? this.getMinutes() : "0" + this.getMinutes())
        + ":" + (this.getSeconds() > 9 ? this.getSeconds() : "0" + this.getSeconds());
};
String.prototype.find = function (str) {
    return new RegExp(str).test(this);
};

function getHashStringArgs() {
//取得查询的hash，并去除开头的#号
    var hashStrings = (window.location.hash.length > 0 ? window.location.hash.substring(1) : ""),
//保持数据的对象
        hashArgs = {},
//取得每一项hash对
        items = hashStrings.length > 0 ? hashStrings.split("&") : [], item = null, name = null, value = null, i = 0, len = items.length;
//逐个将每一项添加到hashArgs中
    for (i = 0; i < len; i++) {
        item = items[i].split("=");
        name = decodeURIComponent(item[0]);
        value = decodeURIComponent(item[1]);
        if (name.length > 0) {
            hashArgs[name] = value;
        }
    }
    return hashArgs;
}

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

    if (null == Login || !Login.statue) {
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
            "                <a href=\"../register.html\">注册</a>\n" +
            "<li class=\"layui-nav-item \" style=\"float: right\">\n" +
            "                <a href=\"../login.html\">登录</a>\n" +
            "            </li>\n" +
            "            </li>";
        $(".layui-layout-right").replaceWith(unLogin);
    } else {
        var login = "<a href=\"javascript:;\">我的</a>\n" +
            "                <dl class=\"layui-nav-child\">\n" +
            "                    <dd><a href=\"../my/my.html\">个人中心</a></dd>\n" +
            "                    <dd><a href=\"../my/message.html\">留言消息</a></dd>\n" +
            "                    <dd><a href=\"../my/setting.html#password\">修改密码</a></dd>\n" +
            "                    <dd><a href=\"../my/friends.html\">我的关注</a></dd>\n" +
            "                    <dd><a href=\"javascript:void(0);\" class='logout'>退出</a></dd>\n" +
            "                </dl>";
        $(".layui-layout-right").html(login);
        $(".logout").on('click', function () {
            layui.sessionData('unLogin', null);
            layui.sessionData("user", null);

            layer.confirm("确定退出？",{icon: 3, title:'确定退出'},function (index) {
                window.location.href = "../logout";
                layer.close(index);
            });
            return false;
        });
    }

    layui.use('element', function () {
        var element = layui.element;
        element.init();
    });

})();
