layui.use(['jquery', 'layer'], function () {
    var layer = layui.layer;
    var $ = layui.jquery;
    $(".f-show").on('click', ".f-opt", function () {
            var node = this.className;
            if (node.find('send')) {
                console.log("send");
                var toId = $(this).attr("data-id");
                /*获取ID放前面，不然会获取的是加载层*/
                layer.prompt({
                    formType: 2,
                    value: '',
                    title: '发送消息',
                }, function (value, index, elem) {
                    console.log(value); //得到value

                    var idx = layer.load(1);

                    $.ajax({
                        async: false
                        , type: 'post'
                        , url: "/msg/" + toId
                        , data: {'data': aes.encrypt(value)}
                        , success: function (res) {
                            layer.close(idx);
                            if (res.status === 0) {
                                layer.msg(res.msg);
                            } else {
                                layer.msg(res.msg);
                            }
                            layer.close(index);
                            page(getHashStringArgs()['!offset'] ? getHashStringArgs()['!offset'] : 1);

                            return false;
                        }
                        , error: function (res) {
                            layer.close(idx);
                            layer.alert("网络错误，错误码：" + res.status);
                            layer.close(index);
                            return false;
                        }
                    });

                });
                return false;
            } else if (node.find('zone')) {
                console.log("zone");
                /*return false;*/
            } else if (node.find('cancel')) {
                /*取消关注*/
                console.log("cancel");
                var tabNode = $(this);
                var toId = tabNode.attr("data-id");
                layer.confirm('确定取消关注' + $(this).attr("data-name"), {icon: 3, title: '提示'}, function (index) {
                    //do something
                    console.log("sure remove " + toId);
                    var idx = layer.load(1);

                    $.ajax({
                        async: false
                        , type: 'delete'
                        , url: "/friend/" + toId
                        , success: function (res) {
                            layer.close(idx);
                            if (res.status === 0) {
                                layer.msg(res.msg);
                                tabNode.parent().parent().parent().parent().remove();
                            } else {
                                layer.msg(res.msg);
                            }
                            return false;
                        }
                        , error: function (res) {
                            layer.close(idx);
                            layer.alert("网络错误，错误码：" + res.status);
                            return false;
                        }
                    });
                });
                return false;
            } else if (node.find('delete-msg')) {
                /*删除消息*/
                console.log("delete-msg");
                var tabNode = $(this);
                var delId = tabNode.attr("data-id");
                layer.confirm('确定删除消息?', {icon: 3, title: '提示'}, function (index) {
                    console.log("sure remove " + delId);
                    var idx = layer.load(1);
                    $.ajax({
                        async: false
                        , type: 'delete'
                        , url: "/msg/" + delId
                        , success: function (res) {
                            layer.close(idx);
                            if (res.status === 0) {
                                layer.msg(res.msg);
                                tabNode.parent().parent().parent().parent().remove();
                            } else {
                                layer.msg(res.msg);
                            }
                            return false;
                        }
                        , error: function (res) {
                            layer.close(idx);
                            layer.alert("错误码：" + res.status);
                            return false;
                        }
                    });
                });
                return false;
            }

            else {
                console.log(this.className);
            }
        }
    );
});