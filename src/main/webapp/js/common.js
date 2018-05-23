/*主评论回复 创建回复模块*/
/*(function () {
    $("#content .comment").on('click', ".reply",function () {

        var dom = $(this).parent().next().children(".reviewArea");
        if (dom.html().length > 0) {
            dom.html("");
            $('.content').flexText();
        } else {

           var name =  $(this).parents('.layui-row').siblings('.card_nick').find('a').html();
           var dataId = $(this).find('a').attr('data-id');
            dom.html(
                "<textarea class=\"content comment-input\" placeholder=\"请输入评论。\" data-name=\""+name+"\"></textarea>\n" +
                "<a href=\"javascript:;\" class=\"plBtn\" data-id=\""+dataId+"\">评论</a>\n"
            );
            $('.content').flexText();
        }

    });
})();*/
(function () {
    $("#content.comment").on('click', ".comment", function () {
        if (!$.cookie("user")) {
            layer.msg("请登录后评论");
            return false;
        }
        //获取回复人的名字
        var fhName = $(this).parents('.card_detail').find('.S_txt1').html();
        //回复@
        var fhN = '回复@' + fhName + "：";
        //var oInput = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.hf-con');
        var fhHtml = '<div class="hf-con pull-left"> ' +
            '<pre class="pre" style="padding: 6px 15px;"><span></span><br></pre>' +
            '<textarea class="content comment-input hf-input" data-name="' + fhName +
            '" placeholder="' + fhN + '"></textarea> <a href="javascript:;" class="hf-pl" data-id="'
            + $(this).find('a').attr('data-id') + '">评论</a></div>';
        //显示回复
        if (!$(this).is('.hf-con-block')) {
            $(this).parents('.card_detail').find('.reviewArea').html(fhHtml);
            $(this).addClass('hf-con-block');
            $('.content').flexText();
            //input框自动聚焦
            $(this).parents('.card_detail').find('.hf-input').val('').focus().val();
        } else {
            $(this).removeClass('hf-con-block');
            $(this).parents('.card_detail').find('.hf-con').remove();
        }
    });
})();
(function () {
    $(".commentAll").on('click', ".comment", function () {
        if (!$.cookie("user")) {
            layer.msg("请登录后评论");
            return false;
        }
        //获取回复人的名字
        var fhName = $(this).parents('.card_detail').find('.S_txt1').html();
        //回复@
        var fhN = '回复@' + fhName + "：";
        //var oInput = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.hf-con');
        var fhHtml = '<div class="hf-con pull-left"> ' +
            '<pre class="pre" style="padding: 6px 15px;"><span></span><br></pre>' +
            '<textarea class="content comment-input hf-input" data-name="' + fhName +
            '" placeholder="' + fhN + '"></textarea> <a href="javascript:;" class="hf-pl" data-id="'
            + $(this).attr('data-id') + '">评论</a></div>';
        //显示回复
        if (!$(this).is('.hf-con-block')) {
            $(this).parents('.card_detail').find('.reviewArea').html(fhHtml);
            $(this).addClass('hf-con-block');
            $('.content').flexText();
            //input框自动聚焦
            $(this).parents('.card_detail').find('.hf-input').val('').focus().val();
        } else {
            $(this).removeClass('hf-con-block');
            $(this).parents('.card_detail').find('.hf-con').remove();
        }
    });
})();

<!--textarea限制字数-->


/*        function keyUP(t) {
            var len = $(t).val().length;
            if (len > 139) {
                $(t).val($(t).val().substring(0, 140));
            }
        }*/


<!--包括主栏目和子栏目 点击回复动态创建回复块-->
(function () {
    $('.commentAll').on('click', '.comment-show .pl-hf', function () {
        if (!$.cookie("user")) {
            layer.msg("请登录后评论");
            return false;
        }
        //获取回复人的名字
        var fhName = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
        //回复@
        var fhN = '回复@' + fhName + "：";
        //var oInput = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.hf-con');
        var fhHtml = '<div class="hf-con pull-left"> ' +
            '<textarea class="content comment-input hf-input" data-name="' + fhName +
            '" placeholder="' + fhN + '"></textarea> <a href="javascript:;" class="hf-pl" data-id="'
            + $(this).attr('data-id') + '">评论</a></div>';
        //显示回复
        if ($(this).is('.hf-con-block')) {
            $(this).parents('.date-dz-right').parents('.date-dz').append(fhHtml);
            $(this).removeClass('hf-con-block');
            $('.content').flexText();
            $(this).parents('.date-dz-right').siblings('.hf-con').find('.pre').css('padding', '6px 15px');
            //console.log($(this).parents('.date-dz-right').siblings('.hf-con').find('.pre'))
            //input框自动聚焦
            $(this).parents('.date-dz-right').siblings('.hf-con').find('.hf-input').val('').focus().val();
        } else {
            $(this).addClass('hf-con-block');
            $(this).parents('.date-dz-right').siblings('.hf-con').remove();
        }
    });
})();

<!--评论回复块创建-->
/*OLD   过时 暂时无用*/
(function () {

    $('.commentAll').on('click', '.comment-show .hf-pl', function () {
        if (window.location.href.includes('event.html')) {
            return false;
        }
        var oThis = $(this);
        //获取输入内容
        console.log("this is in js page");

        var oHfVal = $(this).siblings('.flex-text-wrap').find('.hf-input').val();
        console.log(oHfVal);

        if (oHfVal.replace(/^ +| +$/g, '') === '' || oHfVal.trim().length < 1) {
            /*输入内容为空*/
        } else {
            var data = {
                receiveName: "往事1995"
                , content: "123456"
                , time: Date.now()
                , sendName: "烟雨江南"
                , likes: 100
            };
            /*addReply(data, oThis);*/
        }
    })
})();

/*添加回复列表*/
function addReply(oHtml, dom) {
    dom.parents('.commentAll').find('.hf-list-con').css('display', 'block').prepend(oHtml)
    && dom.parents('.commentAll').siblings('.inline').find('.comment').removeClass('hf-con-block')/*移出回复输入框css块*/
    && dom.parents('.hf-con').remove();
    /*移出回复输入框*/
}

/*构造子回复列表*/
function buildReply(data, uid, receiveUserName) {
    var str = '';
    if (arguments.length === 1) {
        if (data instanceof Array) {
            $.each(data, function (idx, data) {
                var likeClazz = "";
                if (data.userLike) {
                    likeClazz = 'class="date-dz-z pull-left date-dz-z-click"><i  class="date-dz-z-click-red red">';
                } else {
                    likeClazz = 'class="date-dz-z pull-left"><i  class="date-dz-z-click-red">';
                }
                str += "<li class=\"all-pl-con\">" +
                    "<div class=\"pl-text hfpl-text clearfix\">" +
                    "<a href=" + zone(data.uid) + " class=\"comment-size-name\">" +
                    data.sendUser.nickname +
                    "</a><span class=\"my-pl-con\">回复<a href=" + zone(data.receiveUser.uid) + " class=\"atName\">@" +
                    data.receiveUser.nickname +
                    "</a> : " +
                    data.content +
                    "</span></div><div class=\"date-dz\"> <span class=\"date-dz-left pull-left comment-time\">" +
                    data.ctime +
                    "</span> <div class=\"date-dz-right pull-right comment-pl-block\">  " +
                    "<a href=\"javascript:;\" class=\"date-dz-pl pl-hf pull-left hf-con-block\" data-id='" +
                    data.cid +
                    "'>回复</a> " +
                    "<span class=\"pull-left date-dz-line\">|</span> " +
                    '<a data-id="' + data.cid + '" href="javascript:;" ' +
                    likeClazz +
                    '</i>赞 (<num>' + data.likes + '</num>)</a> </div> </div>';
            });
        }
        return str;
    } else {
        var likeClazz = "";
        if (data.userLike) {
            likeClazz = 'class="date-dz-z pull-left date-dz-z-click"><i  class="date-dz-z-click-red red">';
        } else {
            likeClazz = 'class="date-dz-z pull-left"><i  class="date-dz-z-click-red">';
        }
        str = "<li class=\"all-pl-con\">" +
            "<div class=\"pl-text hfpl-text clearfix\">" +
            "<a href=" + zone(data.sendUser.uid) + " class=\"comment-size-name\">" +
            data.sendUser.nickname +
            "</a><span class=\"my-pl-con\">回复<a href='" + zone(uid) + "' class=\"atName\">@" +
            receiveUserName +
            "</a> : " +
            data.content +
            "</span></div><div class=\"date-dz\"> <span class=\"date-dz-left pull-left comment-time\">" +
            data.ctime +
            "</span> <div class=\"date-dz-right pull-right comment-pl-block\">  " +
            "<a href=\"javascript:;\" class=\"date-dz-pl pl-hf pull-left hf-con-block\" data-id='" +
            data.cid +
            "'>回复</a> " +
            "<span class=\"pull-left date-dz-line\">|</span> " +
            '<a data-id="' + data.cid + '" href="javascript:;" ' +
            likeClazz +
            '</i>赞 (<num>' + data.likes + '</num>)</a> </div> </div>';
        return str;
    }
}


<!--删除评论块-->
(function () {
    $('.commentAll').on('click', '.removeBlock', function () {
        var oT = $(this).parents('.date-dz-right').parents('.date-dz').parents('.all-pl-con');
        if (oT.siblings('.all-pl-con').length >= 1) {
            oT.remove();
        } else {
            $(this).parents('.date-dz-right').parents('.date-dz').parents('.all-pl-con').parents('.hf-list-con').css('display', 'none')
            oT.remove();
        }
        $(this).parents('.date-dz-right').parents('.date-dz').parents('.comment-show-con-list').parents('.comment-show-con').remove();

    });
})();


/**
 * obj,now,oSize,nick
 *
 */
function addList(res) {
    //发布动态
    var html =
        '<li class="card clearfix">' +
        '<div class="float_left">' +
        '<img src="';

    html += res.sendUser.icons ? res.sendUser.icons : $.cookie("user").user.icons;

    html += '" class="head"></div>' +
        '<div class="card_detail">' +
        '<div class="card_nick">' +
        '<a href=' + zone(res.sendUser.uid) + ' target="_blank" class="W_f14 W_fb S_txt1">' + res.sendUser.nickname +
        '</a></div><div class="card_time">' + res.ctime +
        '</div><div class="card_text ws14">' + res.content +
        '</div><div class="layui-row">' +
        '<ul class="inline"><li class="like" data-id="' + res.cid +
        '"><a href="javascript:;"><i class="iconfont icon-dianzan"></i>' +
        '<num>0</num></a></li><li class="comment" data-id="' + res.cid +
        '"><a href="javascript:;">' +
        '<i class="layui-icon"></i></a></li><li class="share" data-id="' + res.cid +
        '"><a href="javascript:;">' +
        '<i class="layui-icon"></i></a></li><li class="report" data-id="' + res.cid +
        '"><a href="javascript:;">' +
        '<i class="layui-icon"></i></a></li></ul><div class="commentAll"><div class="reviewArea clearfix">' +
        '</div><div class="comment-show"><ul class="hf-list-con"></ul></div></div><div class="comment-pl-block clearfix">' +
        '<a href="../event/comment.html?id=' + res.cid +
        '" class="float_right">查看更多</a></div></div></div></li>';
    $('#content').prepend(html);


}

/*刷新页面时根据hash确定显示页面*/
(function () {
    var hash = window.location.hash;
    if (hash.includes("#")) {
        $(".setting:not(.layui-row)").hide();
        $(hash).show();
    }
})();
/*根据点击内容隐藏和显示对应div*/
(function () {
    $(".setting a").on("click", function () {
        /*var hash = window.location.hash;*/
        $(".setting:not(.layui-row)").hide();
        $(this.hash).show();
    });
})();
/*根据hash改变修改显示和隐藏对应div*/
(function () {
    window.onhashchange = function () {
        if (window.location.hash.includes("#")) {
            $(".setting:not(.layui-row)").hide();
            $(window.location.hash).show();
        }
    };
})();




