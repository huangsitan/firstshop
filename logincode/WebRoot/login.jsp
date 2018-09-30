<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta charset="UTF-8">
<title>登录界面</title>
<script src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
$(function(){
    $("#logform").submit(function(){
        if(!checkInput()){
         
            return false;
        }
    }
    );
    
    //更换验证码
     $("#codeimg").click(function(){
        $(this).attr("src","getcode?"+Math.random());
       }
     );
});

function checkInput(){
    //判断用户名
    if($("input[name=username]").val()==null || $("input[name=username]").val()==""){
        alert("用户名不能等于空");
        $("input[name=username]").focus();
        return false;
    }
    //判断密码
    if($("input[name=password]").val()==null || $("input[name=password]").val()==""){
        alert("密码不能等于空");
        $("input[name=password]").focus();
        return false;
    }
    //判断验证码
    if($("input[name=valCode]").val()==null || $("input[name=validateCode]").val()==""){
        alert("验证码不能为空");
        $("input[name=valCode]").focus();
        return false;
    }
    return true;
}

</script>
</head>
<body>
    <div id="loginpanelwrap">
        <div>
            <div>登录</div>
        </div>
        <form action="dolog" method="post" id="logform">
            <div>
                <div>
                    <label>用户名:</label>
                    <input type="text" name="username" />
                </div>
            </div>
            <div>
                <label>密码：</label>
                <input type="password" name="password" />
            </div>
            <div>
                <label>验证码:</label>
                <input type="text" name="valCode"/>
                <img id="codeimg"  src="getcode">
            </div>
            <div>
                <span></span>
                <input type="submit" id="loginform_submit" value="登录" />
            </div>
        </form>
    </div>
</body>
</html>