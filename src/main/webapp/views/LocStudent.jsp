<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 
    Document   : LocStudent
    Created on : Jul 7, 2022, 6:06:53 PM
    Author     : ASUS
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

        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">

    </head>

    <body id="page-top">


        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <jsp:include page="/general/Sidebar.jsp"></jsp:include>
                <!-- End of Sidebar -->

                <!-- Content Wrapper -->
                <div id="content-wrapper" class="d-flex flex-column">

                    <!-- Main Content -->
                    <div id="content">

                        <!-- Topbar -->
                    <jsp:include page="/general/Header.jsp"></jsp:include>
                        <!-- End of Topbar -->

                        <!-- Begin Page Content -->
                        <div class="container-fluid">


                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">All your LOC here</h6>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <div >
                                            <div class="row">
                                                <div class="col-sm-12 col-md-6">
                                                    <div  class="dataTables_filter">
                                                        <form action="LocStudent?go=show" method="post">

                                                            <label>
                                                                Class:
                                                                <select class="form-control form-control-user" name="class" onchange="this.form.submit()">
                                                                <c:forEach var="o" items="${vectC}">
                                                                    <option value="${o.id}" ${o.id == class ? "selected" : ""}>${o.classCode}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </label>
                                                        <label>
                                                            Milestone:
                                                            <select class="form-control form-control-user" name="Iter" onchange="this.form.submit()">
                                                                <c:forEach var="o" items="${vectM}">
                                                                    <option value="${o}" ${o == mile ? "selected" : ""}>${o}</option>
                                                                </c:forEach>

                                                            </select>
                                                        </label>
                                                        <label>
                                                            Function:
                                                            <select class="form-control form-control-user" name="Iter" onchange="this.form.submit()">
                                                                <c:forEach var="o" items="${vectF}">
                                                                    <option value="${o}" ${o == mile ? "selected" : ""}>${o}</option>
                                                                </c:forEach>

                                                            </select>
                                                        </label>
                                                        <input type="submit" value="Search" class="btn btn-primary"  />
                                                    </form>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">

                                                <table class="table table-bordered"  width="100%" cellspacing="0" role="grid" style="width: 100%;">
                                                    <thead>
                                                        <tr role="row">
                                                            <th class="sorting sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-sort="ascending" style="width: 103px;">evaluation_time</th>

                                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" style="width: 49px;">complexity</th>
                                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" style="width: 34px;">quality(%)</th>
                                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" style="width: 82px;">Loc</th>

                                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" style="width: 58px;">Milestone name</th>
                                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" style="width: 58px;">Function name</th>
                                                        </tr>
                                                    </thead>
                                                    <tfoot>
                                                        <tr role="row">
                                                            <th class="sorting sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-sort="ascending" style="width: 103px;">evaluation_time</th>

                                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" style="width: 49px;">complexity</th>
                                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" style="width: 34px;">quality(%)</th>
                                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" style="width: 82px;">Loc</th>

                                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" style="width: 58px;">Milestone name</th>
                                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" style="width: 58px;">Function name</th>
                                                        </tr>
                                                    </tfoot>
                                                    <tbody>
                                                        <c:forEach var="o" items="${loc}">
                                                            <tr class="odd">
                                                                <td class="sorting_1">${o.evaluation_time}</td>

                                                                <td>${o.complexity_id}</td>
                                                                <td>${o.quality_id}</td>
                                                                <td>${o.complexity_id * o.quality_id /100}</td>

                                                                <td>${o.milestoneID}</td>
                                                                <td>${o.functionID}</td>
                                                            </tr>
                                                        </c:forEach>

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12 col-md-7">
                                                <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
                                                    <strong>
                                                        Your total LOC: ${total} / 360
                                                    </strong>
                                                </div>
                                                <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">

                                                    <c:if test="${total < 360}">
                                                        <h5 class="text-danger">  ${mess}</h5>
                                                    </c:if>
                                                    <c:if test="${total >= 360}">
                                                        <h5 class="text-success">  ${mess}</h5>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>





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
        <jsp:include page="/general/LogOut.jsp"></jsp:include>

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



        <script src="js/sweetalert.min.js"></script>

    </body>

</html>
