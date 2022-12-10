<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/general/Head.jsp"/>

<body id="page-top">

<div id="wrapper">

    <jsp:include page="/general/Sidebar.jsp"></jsp:include>

    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <jsp:include page="/general/Header.jsp"></jsp:include>

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
                                                    <input value="${re_pass}" required name="re_pass" type="password" class="form-control form-control-user" id="exampleInputPassword1" placeholder="Your new password">
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

<jsp:include page="/general/LogOut.jsp"></jsp:include>

<jsp:include page="/general/Footer.jsp"/>

</body>

</html>
