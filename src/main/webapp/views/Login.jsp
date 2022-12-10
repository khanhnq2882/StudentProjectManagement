<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/general/Head.jsp"/>

<body class="bg-gradient-primary">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                </div>
                                <form class="user" action="<%=request.getContextPath()%>/Login_sen" method="POST">
                                    <div class="form-group">
                                        <input name="user" type="text" value="${user == null ? username : user}"
                                               class="form-control form-control-user"
                                               id="exampleInputEmail" aria-describedby="emailHelp"
                                               placeholder="Enter Username or Email..." required>
                                    </div>
                                    <div class="form-group">
                                        <input name="pass" type="password" value="${pass == null ? password : pass}"
                                               class="form-control form-control-user"
                                               id="exampleInputPassword" placeholder="Password" required>
                                    </div>
                                    <div class="form-group">
                                        <div class="custom-control custom-checkbox small">
                                            <input ${password != null ? "checked" : ""} name="remember" type="checkbox"
                                                                                        class="custom-control-input"
                                                                                        id="customCheck">
                                            <label class="custom-control-label" for="customCheck">Remember Me</label>
                                        </div>
                                    </div>
                                    <div>${mess} ${mailmess} ${err}</div>
                                    <div class="log">
                                        <input type="submit" value="Login" name="Login"/>
                                    </div>
                                    <hr>
                                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/g1/LoginGoogleHandler&response_type=code
                                               &client_id=13820389587-da7sp0qdsk592ou8v2ji25m5a0f4gj4c.apps.googleusercontent.com&approval_prompt=force"
                                       class="btn btn-google btn-user btn-block">
                                        <i class="fab fa-google fa-fw"></i> Login with Google
                                    </a>
                                    <a href="index.html" class="btn btn-facebook btn-user btn-block">
                                        <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                                    </a>
                                </form>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="<%=request.getContextPath()%>/ForgotPassword">Forgot Password?</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="Register">Create an Account!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/general/Footer.jsp"/>

</body>
</html>