function zone(id) {
    return '"../user/user.html?id=' + id + '"';
}

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
            '<textarea class="content comment-input hf-input" data-name="' + fhName +
            '" placeholder="' + fhN + '"></textarea> <a href="javascript:;" class="hf-pl" data-id="'
            + $(this).attr('data-id') + '">评论</a></div>';
        //显示回复
        if (!$(this).is('.hf-con-block')) {
            $(this).parents('.card_detail').find('.reviewArea').html(fhHtml);
            $(this).addClass('hf-con-block');
            $('.content').flexText();
            $('.content').parents('flex-text-wrap').find('pre').css('padding', '6px 15px');
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
<!--点击回复动态创建回复块 主栏目-->
(function () {
    $('#content.comment').on('click', '.date-dz-pl.pl-hf', function () {
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


/*添加回复列表*/
function addReply(oHtml, dom) {
    dom.parents('.commentAll').find('.hf-list-con').css('display', 'block').prepend(oHtml)
    && dom.parents('.commentAll').siblings('.inline').find('.comment').removeClass('hf-con-block')/*移出回复输入框css块*/
    && dom.parents('.hf-con').remove();/*移出回复输入框*/
}

/*构造子回复列表*/
function buildReply(data) {
    var str = '';
    if (data instanceof Array) {
        $.each(data, function (idx, data) {
            build(data);
        });
    }else{
        build(data);
    }
    return str;
    function build(data) {
        var likeClazz = "";
        if (data.userLike) {
            likeClazz = 'class="date-dz-z pull-left date-dz-z-click"><i  class="date-dz-z-click-red red">';
        } else {
            likeClazz = 'class="date-dz-z pull-left"><i  class="date-dz-z-click-red">';
        }
        str += "<li class=\"all-pl-con\" data-id='"+data.cid+"' >" +
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
    var oHtml = '<li><div class="card clearfix">' +
        '<div class="comment-show-con-img pull-left">' +
        '<img src="' + res.sendUser.icons + '" alt="">' +
        '</div> ' +
        '<div class="comment-show-con-list pull-left clearfix">' +
        '<div class="pl-text clearfix"> ' +
        '<a href="javascript:;" class="comment-size-name">'
        + res.sendUser.nickname + //昵称
        '</a>' +
        ' <span class="my-pl-con">&nbsp;'
        + res.content + //内容
        '</span>' +
        ' </div> ' +
        '<div class="date-dz"> ' +
        '<span class="date-dz-left pull-left comment-time">'
        + res.ctime + // 时间
        '</span> ' +
        '<div class="date-dz-right pull-right comment-pl-block">' +
        /*   '<a href="javascript:;" class="removeBlock" data-id="' + res.cid + '">删除</a> ' +*/
        '<a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left" data-id="' + res.cid + '">回复</a> ' +
        '<span class="pull-left date-dz-line">|</span> ' +
        '<a data-id="' + res.cid + '"href="javascript:;" ' +
        'class="date-dz-z pull-left"><i  class="date-dz-z-click-red">' +
        '</i>赞 (<num >' + res.likes + '</num>)</a> ' +
        '</div> </div><div class="hf-list-con"></div></div> </div></li>';
    /*if (oSize.replace(/(^\s*)|(\s*$)/g, "") != '') {*/
    /*判断内容是否为空*/

    $('#content').prepend(oHtml);


}



