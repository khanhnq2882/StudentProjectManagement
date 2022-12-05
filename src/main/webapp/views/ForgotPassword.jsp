<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

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
                                    <h1 class="h4 text-gray-900 mb-2">Forgot Your Password?</h1>
                                    <p class="mb-4">We get it, stuff happens. Just enter your email address below
                                        and we'll send you a link to reset your password!</p>
                                </div>
                                <form action="<%=request.getContextPath()%>/ForgotPassword" method="POST" class="user">
                                    <div class="form-group">
                                        <input name="email" type="email" class="form-control form-control-user"
                                               id="exampleInputEmail" aria-describedby="emailHelp"
                                               placeholder="Enter Email Address..." required>
                                    </div>
                                    <div class="log">
                                        <input type="submit" value="Reset Password"/>
                                    </div>

                                </form>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="Register">Create an Account!</a>
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

    </div>

</div>

<jsp:include page="/general/Footer.jsp"/>

${alert eq null ? "" : alert}

</body>

</html>