/*显示附近的人 放在最后 当页面加载完成再加载附近的人*/
(function () {
    if ($('ul.nearby').length) {
        $.get('/nearby', function (res) {
            if (res.status === 1) {
                layer.msg(res.msg);
                return;
            }
            var html = "";
            html += '<div class="layui-show-lg-inline-block"><div class="inline">';
            for (var i = 0; i < res.count / 2; i++) {
                html += '<li><img src="' + res.data[i].icons + '" class="head"/></li>';
            }
            html += '</div></div>';
            html += '<div class="layui-show-lg-inline-block"><div class="inline">';
            for (var i = res.count / 2; i < res.count; i++) {
                html += '<li><img src="' + res.data[i].icons + '" class="head"/></li>';
            }
            html += '</div></div>';
            $('ul.nearby').html(html);
        });
    }
})();
