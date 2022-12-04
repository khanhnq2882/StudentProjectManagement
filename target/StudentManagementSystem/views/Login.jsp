<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Group 1 - Login</title>

        <!-- Custom fonts for this template-->
        <link href="<c:url value="/assets/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="<c:url value="/assets/css/sb-admin-2.min.css"/>" rel="stylesheet">
        <link rel="icon" href="<c:url value="/assets/img/cai nay hoi la.png"/>" type="image/gif" sizes="16x16">
        <link href="<c:url value="/assets/css/SenCss.css"/>" rel="stylesheet">
    </head>

    <body class="bg-gradient-primary">

        <div class="container">

            <!-- Outer Row -->
            <div class="row justify-content-center">

                <div class="col-xl-10 col-lg-12 col-md-9">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                                <div class="col-lg-6">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                        </div>
                                        <form class="user" action="<%=request.getContextPath()%>/Login_sen" method="POST">
                                            <div class="form-group">
                                                <input name="user" type="text" value="${user == null ? username : user}" class="form-control form-control-user"
                                                       id="exampleInputEmail" aria-describedby="emailHelp"
                                                       placeholder="Enter Username or Email..." required >
                                            </div>
                                            <div class="form-group">
                                                <input name="pass" type="password" value="${pass == null ? password : pass}" class="form-control form-control-user"
                                                       id="exampleInputPassword" placeholder="Password" required >
                                            </div>
                                            <div class="form-group">
                                                <div class="custom-control custom-checkbox small">
                                                    <input ${password != null ? "checked" : ""} name="remember" type="checkbox" class="custom-control-input" id="customCheck">
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
                                            <a class="small" href="Forgot_Password">Forgot Password?</a>
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

        <!-- Bootstrap core JavaScript-->
        <script src="<c:url value="/assets/vendor/jquery/jquery.min.js"/>"></script>
        <script src="<c:url value="/assets/vendor/bootstrap/js/bootstrap.bundle.js"/>"></script>

        <!-- Core plugin JavaScript-->
        <script src="<c:url value="/assets/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

        <!-- Custom scripts for all pages-->
        <script src="<c:url value="/assets/js/sb-admin-2.min.js"/>"></script>

    </body>

</html>