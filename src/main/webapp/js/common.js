var setting = {
    show: function showInfo(res) {
        $('.head').attr("src", !res['data'].icon ? this.innerHTML : res['data'].icon);
        $('.tit').html(res['data'].nickname);//nickname
        $('.data i').html(res['data'].sex === '男' ? '&#xe662;' : '&#xe661;');//sex
        $('.data ').find('li')[1].innerHTML = Math.floor((Date.now() - res['data'].birth) / 1000 / 60 / 60 / 365 / 24) + '岁';//old
        $('.data ').find('li')[2].innerHTML = res['data'].city;//city
        $('.inf ')[1].innerHTML = !res['data']['indroduce'] ? '无。' : res['data']['indroduce'];//city
        $('.inf ')[3].innerHTML = res['data'].email;//city
    }
    ,error:function (res) {
        layer.msg('错误码：' + res.status);
    }
    ,permission:function (res) {

        var permit = res;
        for (var key in permit) {
            var dds=$('select[name='+key+']').parent().find("dd");
            for(var i=0;i<dds.length;i++){
                if(dds[i].getAttribute("lay-value")==permit[key]){
                    dds[i].click();
                }
            }
        }
        /*class选择器 调用点击事件 控制是否出现在附近*/
        if (permit['nearby'] === 1) {
            $('.layui-unselect.layui-form-switch:not(.layui-form-onswitch)').click();
        }else {
            $('.layui-unselect.layui-form-switch.layui-form-onswitch').click();
        }

    }
};

(function () {
    $(".reply").on('click', function () {
        if ($(this).parent().next().html().length > 0) {
            $(".commentAll").html("");
            $(this).parent().next().html("");
            $('.content').flexText();

        } else {
            $(".commentAll").html("");
            $(this).parent().next().html(
                "                            <!--评论区域 begin-->\n" +
                "                            <div class=\"reviewArea clearfix\">\n" +
                "                                <textarea class=\"content comment-input\" placeholder=\"请输入评论。\" ></textarea>\n" +
                "                                <a href=\"javascript:;\" class=\"plBtn\">评论</a>\n" +
                "                            </div>\n" +
                "                            <!--评论区域 end-->\n" +
                "                            <!--回复区域 begin-->\n" +
                "                            <div class=\"comment-show\">\n" +
                "                                <div class=\"comment-show-con clearfix\">\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                            <!--回复区域 end-->\n");
            $('.content').flexText();
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


<!--点击评论创建评论条-->

(function () {
    $('.commentAll').on('click', '.plBtn', function () {
        var myDate = new Date();
        //获取当前年
        var year = myDate.getFullYear();
        //获取当前月
        var month = myDate.getMonth() + 1;
        //获取当前日
        var date = myDate.getDate();
        var h = myDate.getHours();       //获取当前小时数(0-23)
        var m = myDate.getMinutes();     //获取当前分钟数(0-59)
        if (m < 10) m = '0' + m;
        var s = myDate.getSeconds();
        if (s < 10) s = '0' + s;
        var now = year + '-' + month + "-" + date + " " + h + ':' + m + ":" + s;
        //获取输入内容
        var oSize = $(this).siblings('.flex-text-wrap').find('.comment-input').val();
        console.log(oSize);
        //动态创建评论模块
        oHtml = '<div class="comment-show-con clearfix"><div class="comment-show-con-img pull-left"><img src="images/header-img-comment_03.png" alt=""></div> <div class="comment-show-con-list pull-left clearfix"><div class="pl-text clearfix"> <a href="javascript:;" class="comment-size-name">David Beckham : </a> <span class="my-pl-con">&nbsp;' + oSize + '</span> </div> <div class="date-dz"> <span class="date-dz-left pull-left comment-time">' + now + '</span> <div class="date-dz-right pull-right comment-pl-block"><a href="javascript:;" class="removeBlock">删除</a> <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a> </div> </div><div class="hf-list-con"></div></div> </div>';
        if (oSize.replace(/(^\s*)|(\s*$)/g, "") != '') {
            $(this).parents('.reviewArea ').siblings('.comment-show').prepend(oHtml);
            $(this).siblings('.flex-text-wrap').find('.comment-input').prop('value', '').siblings('pre').find('span').text('');
        }
    })
})();


<!--点击回复动态创建回复块-->

(function () {

    $('.commentAll').on('click', '.comment-show .pl-hf', function () {
        //获取回复人的名字
        var fhName = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
        //回复@
        var fhN = '回复@' + fhName;
        //var oInput = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.hf-con');
        var fhHtml = '<div class="hf-con pull-left"> <textarea class="content comment-input hf-input" placeholder="""></textarea> <a href="javascript:;" class="hf-pl">评论</a></div>';
        //显示回复
        if ($(this).is('.hf-con-block')) {
            $(this).parents('.date-dz-right').parents('.date-dz').append(fhHtml);
            $(this).removeClass('hf-con-block');
            $('.content').flexText();
            $(this).parents('.date-dz-right').siblings('.hf-con').find('.pre').css('padding', '6px 15px');
            //console.log($(this).parents('.date-dz-right').siblings('.hf-con').find('.pre'))
            //input框自动聚焦
            $(this).parents('.date-dz-right').siblings('.hf-con').find('.hf-input').val('').focus().val(fhN);
        } else {
            $(this).addClass('hf-con-block');
            $(this).parents('.date-dz-right').siblings('.hf-con').remove();
        }
    });
})();

<!--评论回复块创建-->

(function () {


    $('.commentAll').on('click', '.comment-show .hf-pl', function () {
        var oThis = $(this);
        var myDate = new Date();
        //获取当前年
        var year = myDate.getFullYear();
        //获取当前月
        var month = myDate.getMonth() + 1;
        //获取当前日
        var date = myDate.getDate();
        var h = myDate.getHours();       //获取当前小时数(0-23)
        var m = myDate.getMinutes();     //获取当前分钟数(0-59)
        if (m < 10) m = '0' + m;
        var s = myDate.getSeconds();
        if (s < 10) s = '0' + s;
        var now = year + '-' + month + "-" + date + " " + h + ':' + m + ":" + s;
        //获取输入内容
        var oHfVal = $(this).siblings('.flex-text-wrap').find('.hf-input').val();
        console.log(oHfVal)
        var oHfName = $(this).parents('.hf-con').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
        var oAllVal = '回复@' + oHfName;
        if (oHfVal.replace(/^ +| +$/g, '') == '' || oHfVal == oAllVal) {

        } else {
            $.getJSON("json/pl.json", function (data) {
                var oAt = '';
                var oHf = '';
                $.each(data, function (n, v) {
                    delete v.hfContent;
                    delete v.atName;
                    var arr;
                    var ohfNameArr;
                    if (oHfVal.indexOf("@") == -1) {
                        data['atName'] = '';
                        data['hfContent'] = oHfVal;
                    } else {
                        arr = oHfVal.split(':');
                        ohfNameArr = arr[0].split('@');
                        data['hfContent'] = arr[1];
                        data['atName'] = ohfNameArr[1];
                    }

                    if (data.atName == '') {
                        oAt = data.hfContent;
                    } else {
                        oAt = '回复<a href="javascript:;" class="atName">@' + data.atName + '</a> : ' + data.hfContent;
                    }
                    oHf = data.hfName;
                });

                var oHtml = '<div class="all-pl-con"><div class="pl-text hfpl-text clearfix"><a href="javascript:;" class="comment-size-name">我的名字 : </a><span class="my-pl-con">' + oAt + '</span></div><div class="date-dz"> <span class="date-dz-left pull-left comment-time">' + now + '</span> <div class="date-dz-right pull-right comment-pl-block"> <a href="javascript:;" class="removeBlock">删除</a> <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a> </div> </div></div>';
                oThis.parents('.hf-con').parents('.comment-show-con-list').find('.hf-list-con').css('display', 'block').prepend(oHtml) && oThis.parents('.hf-con').siblings('.date-dz-right').find('.pl-hf').addClass('hf-con-block') && oThis.parents('.hf-con').remove();
            });
        }
    })
})();


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
<!--点赞-->
(function () {
    $('.commentAll').on('click', '.comment-show .date-dz-z', function () {
        var zNum = $(this).find('.z-num').html();
        if ($(this).is('.date-dz-z-click')) {
            zNum--;
            $(this).removeClass('date-dz-z-click red');
            $(this).find('.z-num').html(zNum);
            $(this).find('.date-dz-z-click-red').removeClass('red');
        } else {
            zNum++;
            $(this).addClass('date-dz-z-click');
            $(this).find('.z-num').html(zNum);
            $(this).find('.date-dz-z-click-red').addClass('red');
        }
    })
})();

/**
 * obj,now,oSize,nick
 *
 */
function addList(obj, now, oSize, nick) {
    console.log(oSize);
    //动态创建评论模块
    var oHtml = '<div class="comment-show-con clearfix">' +
        '<div class="comment-show-con-img pull-left">' +
        '<img src="images/header-img-comment_03.png" alt="">' +
        '</div> ' +
        '<div class="comment-show-con-list pull-left clearfix">' +
        '<div class="pl-text clearfix"> ' +
        '<a href="javascript:;" class="comment-size-name">'
        + nick + //昵称
        '</a>' +
        ' <span class="my-pl-con">&nbsp;'
        + oSize + //内容
        '</span>' +
        ' </div> ' +
        '<div class="date-dz"> ' +
        '<span class="date-dz-left pull-left comment-time">'
        + now + // 时间
        '</span> ' +
        '<div class="date-dz-right pull-right comment-pl-block">' +
        '<a href="javascript:;" class="removeBlock">删除</a> ' +
        '<a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> ' +
        '<span class="pull-left date-dz-line">|</span> ' +
        '<a href="javascript:;" class="date-dz-z pull-left">' +
        '<i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a> ' +
        '</div> </div><div class="hf-list-con"></div></div> </div>';
    if (oSize.replace(/(^\s*)|(\s*$)/g, "") != '') {
        $('.comment-show').prepend(oHtml);//添加回复
        //清空输入框
        $('.comment-show').siblings('.flex-text-wrap').find('.comment-input').prop('value', '').siblings('pre').find('span').text('');
    }
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


