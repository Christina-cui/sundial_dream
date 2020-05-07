$(function () {

    //登录输入框效果
    $('.form_text_ipt input').focus(function () {
        $(this).parent().css({
            'box-shadow': '0 0 3px #bbb',
        });
    });
    $('.form_text_ipt input').blur(function () {
        $(this).parent().css({
            'box-shadow': 'none',
        });
        //$(this).parent().next().hide();
    });

    //表单验证
//		if($(this).val()==""){
//			$(this).css({
//				'color':'red',
//			});
//			$(this).parent().css({
//				'border':'solid 1px red',
//			});
//			//$(this).parent().next().find('span').html('helow');
//			$(this).parent().next().show();
//		}else{
//			$(this).css({
//				'color':'#ccc',
//			});
//			$(this).parent().css({
//				'border':'solid 1px #ccc',
//			});
//			$(this).parent().next().hide();
//		}
    $(function () {
        $.noConflict();
        $.validator.addMethod("regrules", function (value, element, param) {
            if (param.test(value)) {
                return true;
            }
            return false;
        });
        $.validator.addMethod("checkPhone", function (value, element, param) {
            var flag = false;
            $.ajax({
                url: "<%=basePath%>checkPhone",
                data: {"name": value},
                async: false,
                dataType: "html",
                success: function (data) {
                    if (data) {
                        alert("该电话号码已被注册");
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
            })
            return flag;
        });
        $.validator.addMethod("checkName", function (value, element, param) {
            var flag = false;
            $.ajax({
                url: "${pageContext.request.contextPath}/userModel/checkUsername.do",
                data: {"name": value},
                async: false,
                dataType: "html",
                success: function (data) {
                    if (data) {
                        alert("该用户名已注册");
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
            })
            return flag;
        });

        $.validator.addMethod("checkEmail", function (value, element, param) {
            var flag = false;
            $.ajax({
                url: "<%=basePath%>checkEmail",
                data: {"name": value},
                async: false,
                dataType: "html",
                success: function (data) {
                    if (data) {
                        alert("该邮箱已被注册");
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
            })
            return flag;
        });


        $("#frm").validate({
            rules: {
                username: {
                    required: true,
                    minlength: 2,
                    maxlength: 20,
                    checkName: true
                },
                password: {
                    required: true,
                    regrules: /^\w{4,15}$/
                },
                phone: {
                    required: true,
                    checkPhone: true,
                    regrules: /^\d{11}$/
                },
                email: {
                    required: true,
                    email: true
                },
                sex: "required",
                repassword: {
                    required: true,
                    equalTo: "#password"
                }
            },
            messages: {
                username: {
                    required: "用户名不可为空",
                    minlength: "用户名最小3个字符",
                    maxlength: "用户名最大20个字符",
                    checkName: "用户名已被占用"
                },
                password: {
                    required: "密码不可为空",
                    regrules: "密码必须由4-15位数字字母下划线组成"
                },
                phone: {
                    required: "手机号不可为空",
                    checkPhone: "该手机号已被占用",
                    regrules: "手机号不符合规范"
                },
                email: {
                    required: "邮箱不可为空",
                    email: "邮箱不符合规范"
                },
                sex: "性别必须选择一个",
                repassword: {
                    required: "密码不可为空",
                    equalTo: "两次密码输入不一致"
                }
            },
            //修改错误信息的位置：error：错误信息的标签    element：当前被验证的元素组件
            errorPlacement: function (error, element) {
                error.appendTo(element.parent());
            },
            //失去光标时触发验证：element失去焦点的元素
            onfocusout: function (element) {
                $(element).valid();
            }
        });
    })
})
