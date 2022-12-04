<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 12/4/2022
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
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

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

</body>

<jsp:include page="/general/LogOut.jsp"/>

<jsp:include page="/general/Footer.jsp"/>
</html>
