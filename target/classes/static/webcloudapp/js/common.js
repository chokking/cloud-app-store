window.app = {
    serverUrl: "http://localhost",

    saveToken: function (token) {
        localStorage.setItem("token", token)
    },

    logout: function () {
        localStorage.clear()
        window.location.reload()
    },

    getToken: function () {
        return localStorage.getItem("token")
    },

    getUserInfor: function () {
        return JSON.parse(localStorage.getItem("user"))
    },

    setUserInfor: function (data) {
        return localStorage.setItem("user", data)
    },

    setData: function (key, data) {
        return localStorage.setItem(key, data)
    },

    getData: function (key) {
        return localStorage.getItem(key)
    },

    removeData: function (key) {
        return localStorage.removeItem(key)
    },

    checkLogin: function ($) {
        let token = this.getToken()
        if (!token) {
            layer.msg("客官,请先登录", {icon: 1})
            setTimeout(function () {
                window.location.href = "login.html"
            }, 1500)
        }
    },

    getQueryString: function () {
        let qs = location.search.substr(1), // 获取url中"?"符后的字串
            args = {}, // 保存参数数据的对象
            items = qs.length ? qs.split("&") : [], // 取得每一个参数项,
            item = null,
            len = items.length;

        for (var i = 0; i < len; i++) {
            item = items[i].split("=");
            var name = decodeURIComponent(item[0]),
                value = decodeURIComponent(item[1]);
            if (name) {
                args[name] = value;
            }
        }
        return args;
    },
    //检验手机/邮箱格式
    checkMobile: function (s) {
        let regu = /^[1][0-9][0-9]{9}$/;
        let re = new RegExp(regu);
        if (re.test(s) || app.checkEmail(s)) {
            return true;
        } else {
            alert("手机/邮箱格式错误!");
            return false;
        }
    },

    checkEmail: function (email) {
        var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if (reg.test(email)) {
            return true
        } else {
            return false
        }
    }

}
