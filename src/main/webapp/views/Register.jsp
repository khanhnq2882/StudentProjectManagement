<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<jsp:include page="/general/Head.jsp"/>

<body class="bg-gradient-primary">

<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                        </div>
                        <form id="form" action="Register" method="POST" class="user">
                            <div class="form-group">
                                <input name="user" type="text" class="form-control form-control-user" id="user"
                                       oninput="validation1()" placeholder="Username" value="${puser}">
                                <span id="text1"></span>
                            </div>
                            <div class="form-group">
                                <input name="name" type="text" class="form-control form-control-user" id="name"
                                       oninput="validation2()" placeholder="Full Name" value="${pname}">
                                <span id="text2"></span>
                            </div>
                            <div class="form-group">
                                <input name="email" type="email" class="form-control form-control-user" id="email"
                                       oninput="validation()" placeholder="Email Address" value="${pemail}">
                                <span id="text"></span>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input name="pass" type="password" class="form-control form-control-user"
                                           oninput="validation3()" id="pass" placeholder="Password" value="${ppass}">
                                    <span id="text3"></span>
                                </div>
                                <div class="col-sm-6">
                                    <input name="repass" type="password" class="form-control form-control-user"
                                           id="exampleRepeatPassword" placeholder="Repeat Password" value="${prepass}">
                                </div>
                            </div>
                            <div>${err}</div>
                            <div class="log">
                                <input type="submit" value="Register Account" name="Register"/>
                            </div>
                            <hr>
                            <a href="index.html" class="btn btn-google btn-user btn-block">
                                <i class="fab fa-google fa-fw"></i> Register with Google
                            </a>
                            <a href="index.html" class="btn btn-facebook btn-user btn-block">
                                <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook
                            </a>
                        </form>
                        <hr>
                        <div class="text-center">
                            <a class="small" href="Forgot_Password">Forgot Password?</a>
                        </div>
                        <div class="text-center">
                            <a class="small" href="Login_sen">Already have an account? Login!</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<jsp:include page="/general/LogOut.jsp"/>

<jsp:include page="/general/Footer.jsp"/>

</body>

</html>
