<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/general/Head.jsp"/>

<body class="bg-gradient-primary">

<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-2">Verify your Account</h1>
                                    <p class="mb-4">Please enter the code 6 number we sent into your email</p>
                                </div>
                                <form action="Verify" method="POST" class="user">
                                    <div class="form-group">
                                        <input name="coded" type="text" class="form-control form-control-user"
                                               id="exampleInputEmail" aria-describedby="emailHelp"
                                               placeholder="Enter 6 number code...">
                                    </div>
                                    <div>${err}</div>
                                    <div class="log">
                                        <input type="submit" value="Verify" name="Verify"/>
                                    </div>
                                </form>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="register.html">Create an Account!</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="login.html">Already have an account? Login!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>

<jsp:include page="/general/LogOut.jsp"/>

<jsp:include page="/general/Footer.jsp"/>

${alert eq null ? "" : alert}

</body>

</html>
