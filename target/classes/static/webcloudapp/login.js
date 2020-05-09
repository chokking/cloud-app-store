let loginType = 1
layui.use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
    $ = layui.jquery;


    form.on('radio(loginType)', function (data) {
        loginType = data.value
    });

    //登录按钮
    form.on("submit(login)", function (data) {
        $.post(app.serverUrl + "/user/adminLogin", {
            username: $("#userName").val(),
            password: $("#password").val(),
            loginType: loginType
        }, function (data) {
            if (data.code == 0) {
                app.saveToken(data.data.token)
                app.setData("admin", JSON.stringify(data.data.user))
                //跳转操作
                setTimeout(function () {
                    if (loginType == 1) {
                        window.location.href = "index.html";
                    }else{
                        window.location.href = "index1.html";
                    }

                }, 1000);
            } else {
                layer.msg(data.msg, {icon: 2})
                // $(this).text("登录中...").removeClass("layui-disabled");
            }
        })
        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function (e) {
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function () {
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function () {
        $(this).parent().removeClass("layui-input-focus");
        if ($(this).val() != '') {
            $(this).parent().addClass("layui-input-active");
        } else {
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
