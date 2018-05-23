<%--@elvariable id="article" type="com.amoxu.entity.Article>"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>话题-乐热评</title>
    <!-- base需要放到head中 -->
    <base href=" <%=basePath%>">
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="./alicon/iconfont.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/comment.css">
    <script src="js/aes.js"></script>
    <link rel="stylesheet" href="css/article.css">

</head>
<body class="mbg">

<!-- 你的HTML代码 -->
<div class="layui-header header header-doc ">
    <div class="layui-main pbg">
        <a class="logo" href="./index.html"><img src="img/logo.png"/>
        </a>
        <ul class="layui-nav layui-layout-left" lay-filter="">
            <li class="layui-nav-item layui-this"><a href="./index.html">首页</a></li>
            <li class="layui-nav-item"><a href="./search.html">搜索</a></li>

            <li class="layui-nav-item"><a href="./event.html">动态</a></li>
            <li class="layui-nav-item layui-layout-right">
            </li>
        </ul>
    </div>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</div>


<div class="layui-main pbg">
    <div class="layui-row">
        <div class="layui-col-md9 clearfix">
            <div class="article ">
                <h2 class="article-title">${article.getName()}</h2>
                <div class="clearfix">
                    <div class="author">文 / <a href="#" target="_blank"
                                               class="ws16 W_fb S_txt1">${article.getAuthor()}</a></div>
                    <span class="ctime">时间：<cite>${article.getCtime()}</cite></span>
                </div>
                <hr>
                <div class="card_detail" style="margin-left: 17px">

                    <div class="card_text ws14">
                        <div class="article-content">
                            ${article.getContent()}
                        </div>
                    </div>
                    <div class="layui-row">
                        <ul class="inline">
                            <li class="like" data-id="${article.id}"><a href="javascript:;"><i
                                    class="iconfont icon-dianzan"></i>
                                <num>${article.likes}</num>
                            </a></li>
                            <li class="comment" data-id="${article.id}"><a href="javascript:;"><i
                                    class="layui-icon"></i></a>
                            </li>
                            <li class="share" data-id="${article.id}"><a href="javascript:;"><i
                                    class="layui-icon"></i></a></li>
                            <li class="report" data-id="${article.id}"><a href="javascript:;"><i
                                    class="layui-icon"></i></a>
                            </li>
                        </ul>
                        <div class="commentAll">
                            <div class="reviewArea clearfix"></div>
                            <div class="comment-show">
                                <ul class="hf-list-con"></ul>
                            </div>
                        </div>
                        <div class="comment-pl-block clearfix"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-col-md3">
            <div class="layui-row ">
                <div class="layui-row ws18 up25">活动</div>
                <hr>
                <ul>
                    <div class="announcement">
                        <li><a>活动1..............</a></li>
                        <li><a>活动2..............</a></li>
                        <li><a>活动3..............</a></li>
                    </div>
                </ul>
                <hr>
            </div>
            <div class="layui-row">
                <div class="ws18">附近</div>
                <hr>
                <ul class="nearby"></ul>
                <hr>
            </div>
        </div>


    </div>
    <div class="footer">
        &copy;Copyright 2018 乐热评<br>
        感谢：<a>Layui</a>,<a>Bootstrap</a>,<a>网易云</a>,<a>QQ音乐</a>,<a>酷我音乐</a>...<br>
        联系我们：<a href="mailto:amoxuk@aliyun.com">amoxuk#aliyun.com(#换成@)</a>
    </div>


</div>
<script src="layui/layui.js"></script>


<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script src="js/jquery.session.js"></script>
<script type="text/javascript" src="js/jquery.flexText.js"></script>
<script src="js/common.js"></script>
<script src="js/top.js"></script>

<script>
    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use(['element', 'layer'], function () {
        var element = layui.element;
        var layer = layui.layer;

        /*创建回复按钮*/
        (function () {
            $(".comment").on('click', function () {
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
        /*举报*/
        (function report() {
            $('.report').on('click', function () {
                var cid = $(this).attr('data-id');
                var type = getType(this);
                /**
                 * 1001 动态
                 * 1002 话题
                 * 1003 热评
                 * 1004 音乐分享*/

                var idx = 1005;

                layer.prompt({
                    formType: 2,
                    title: '举报理由',
                }, function (value, index, elem) {
                    $.ajax({
                        url: './report/' + idx + '/' + cid
                        , type: 'post'
                        , data: {'data': value}
                        , success: function (res) {
                            layer.alert(res.msg);
                            return false;
                        },
                        error: function (res) {
                            layer.alert("错误码：" + res.status + "<br>" + res.statusText);
                            return false;
                        }
                    });
                    layer.close(index);
                });
            });
        })();
        /*回复*/
        (function () {
            $('.card_detail').on('click', '.hf-pl', function () {
                var oThis = $(this);
                //获取输入内容
                console.log("this is in self page");
                var content = oThis.siblings('.flex-text-wrap').find('.comment-input').val();
                console.log(content);
                if (content.replace(/^ +| +$/g, '') === '' || content.trim().length < 1) {
                    /*输入内容为空*/
                    layer.msg("输入内容为空。");
                    return false;
                } else {
                    var loadIdx = layer.load(1);
                    var rcid = oThis.attr("data-id");
                    if (!oThis.parents('.comment-show').length) {
                        rcid = 0;
                        /*如果是根列表回复，回复ID设置为0*/
                    }
                    var bcid = oThis.parents('.commentAll').siblings('.inline').find('.comment').attr("data-id");
                    if (!bcid) {
                        bcid = rcid;
                    }

                    $.ajax({
                        url: '/article/' + bcid + "/" + rcid
                        , type: 'post'
                        , data: {"data": aes.encrypt(content)}
                        , success: function (res) {
                            layer.close(loadIdx);
                            if (res.status === 0) {
                                layer.msg("发表成功");
                                var oHtml = buildReply(res.data);
                                addReply(oHtml, oThis);
                                return false;
                            } else {
                                layer.alert(res.msg);
                            }
                            return false;
                        },
                        error: function (res) {
                            layer.close(loadIdx);
                            layer.alert("错误码：" + res.status + "<br>" + res.statusText);
                            return false;
                        }
                    });


                }
            });
        })();
        <!--分享-->
        (function () {
            $('.share').on('click', function () {
                var dom = $(this);
                var did = dom.attr('data-id');
                var text = "给你分享一个来自乐热评的消息：" +
                    dom.parents('.card_detail').find('.card_text').text().substring(0, 128) +
                    "\n点击查看原文：http://localhost/article/" + did;
                layer.prompt({
                    formType: 2,
                    value: text,
                    title: '复制内容分享',
                }, function (value, index, elem) {
                    layer.close(index);
                });
            });
        })();
        <!--点赞-->
        (function () {
            $('.card_detail').on('click', '.like,a.date-dz-z', function () {
                var thumbNum = $(this).find('num').html();
                console.log("当前点赞数:" + thumbNum);
                var dom = this;
                if ($.cookie('user') == null) {
                    layer.msg("点赞需要登录。");
                    return false;
                } else {
                    var cid = $(this).attr('data-id');
                    var links;
                    if ($(this).attr('class').find('like')) {
                        links = '/like/share/' + cid
                    } else
                        links = '/like/shareComment/' + cid
                    $.ajax({

                        url: links
                        , type: 'post'
                        , success: function (res) {
                            layer.msg(res.msg);
                            if (res.status === 0) {
                                /*操作成功*/
                                if (res.count === 1) {
                                    /*点赞*/
                                    thumbNum++;
                                    if ($(dom).find('.iconfont').length) {
                                        $(dom).find('.iconfont').addClass('icon-dianzan1').removeClass('icon-dianzan');
                                    } else {
                                        $(dom).addClass('date-dz-z-click');
                                        $(dom).find('.date-dz-z-click-red').addClass('red');
                                    }
                                    $(dom).find('num').html(thumbNum);

                                } else {
                                    /*取消点赞*/
                                    thumbNum--;
                                    if ($(dom).find('.iconfont').length) {
                                        /*主页面*/
                                        $(dom).find('.iconfont').addClass('icon-dianzan').removeClass('icon-dianzan1');
                                    } else {
                                        $(dom).removeClass('date-dz-z-click red');
                                        $(dom).find('.date-dz-z-click-red').removeClass('red');
                                    }
                                    $(dom).find('num').html(thumbNum);

                                }
                            } else {
                                layer.msg(res.msg);
                            }
                        }
                        , error: function (res) {
                            layer.msg("错误码：" + res.status + "<br>" + res.statusText);
                        }
                    });
                    return false;
                }
            });
        })();


        layui.use('flow', function () {
            var $ = layui.jquery; //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
            var flow = layui.flow;
            flow.load({
                elem: '.hf-list-con' //指定列表容器
                , scrollElem: '.comment-pl-block'
                , done: function (page, next) { //到达临界点（默认滚动触发），触发下一页
                    var lis = [];
                    if (page === 1) {
                        lis.push('<li class="all-pl-con new-tab">\n' +
                            '以下是最新评论:\n' +
                            '</li>');
                    }
                    //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
                    var id = window.location.href.split("/");

                    $.get('/article/comment/reply/' + id.pop() + '?limit=10&offset=' + (page - 1) * 10, function (res) {
                        //假设你的列表返回在data集合中

                        if (res.data.length > 0) {
                            lis.push(buildReply(res.data));
                        }

                        //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                        //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                        next(lis.join(''), page < res.count / 10);
                    });
                }
            });
        });


    });
</script>
<script type="text/javascript" src="js/nearby.js"></script>
</body>
</html>
</%--@elvariable id="article" type="com.amoxu.entity.Article>