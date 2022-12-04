<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/4/2022
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>G1 - Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <!--        <link href="css/SenCss.css" rel="stylesheet">-->
    <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">

</head>

<body id="page-top">


<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="Sidebar.jsp"></jsp:include>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <jsp:include page="Header.jsp"></jsp:include>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <!--                        doi mat khau-->
            <div class="container">
                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">




                            <div class="card-body p-0">
                                <!-- Nested Row within Card Body -->
                                <div class="row">

                                    <div class="col-lg-12">
                                        <div class="p-5">
                                            <div class="text-center">
                                                <h1 class="h4 text-gray-900 mb-4">Change Your Password</h1>
                                            </div>
                                            <form class="user" action="ChangePassWord" method="POST">
                                                <input type="hidden" name="go" value="ChangeForm" />
                                                <div class="form-group">
                                                    <input name="PassNow" type="password" class="form-control form-control-user"
                                                           value="${PassNow}"
                                                           id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Your current password..." required>
                                                </div>
                                                <div class="form-group">
                                                    <input value="${passNew}" required name="passNew" type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Your new password">
                                                </div>
                                                <div class="form-group">
                                                    <input value="${re_pass}" required name="re_pass" type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Your new password">
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-12 text-center">
                                                        <span class="text-center ${ok == 1 ? "text-success":"text-danger"}" >${mess_p}</span>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-5"></div>

                                                    <div class="col-sm-7">

                                                        <br>
                                                        <input class="btn btn-primary " type="submit" name="submit" value="Change" />
                                                    </div>

                                                </div>




                                            </form>


                                        </div>
                                    </div>
                                </div>
                            </div>



                        </div>
                    </div>
                </div>
            </div>


            <div class="container">

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2021</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<jsp:include page="LogOut.jsp"></jsp:include>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/chart-area-demo.js"></script>
<script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>

