<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : HomeExample
    Created on : May 20, 2022, 12:55:19 PM
    Author     : asus
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
                            <h1 class="h3 mb-4 text-gray-800">Add more Milestone here</h1>
                            <div class="InputForm">
                                <form method="POST" action="MilestoneListServlet">
                                    <input type="hidden" name="go" value="addMilestone">

                                        <tbody>
                                            <tr>
                                                <td>Milestone Name</td>
                                                <td><input class="form-control form-control-user id" type="text" name="MileName" required></td>
                                                <td>Iteration Name</td>
                                                <td>
                                                    <select class="form-control form-control-user id" id="iteId" name="iteId">
                                                    <c:forEach var="o" items="${listAllIteName}">
                                                        <option value="${o.interation_id}">${o.iterationName}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Class Code</td>
                                            <td><select class="form-control form-control-user id" id="classId" name="classId">
                                                    <c:forEach var="o" items="${listClassCode}">
                                                        <option value="${o.class_id}">${o.classCode}</option>
                                                    </c:forEach>

                                                </select></td>

                                        </tr>
                                        <tr>
                                            <td>From Date</td>
                                            <td><input class="form-control form-control-user id" type="date" name="fromDate" required placeholder="yyyy-mm-dd" ></td>
                                        </tr>
                                        <tr>
                                            <td>To Date</td>
                                            <td><input class="form-control form-control-user id" type="date" name="toDate" required placeholder="yyyy-mm-dd" ></td>
                                        </tr>
                                        <tr>
                                            <td>Status </td><br>
                                            <td><input type="radio" name="status" value="1" checked>Open
                                                <input type="radio" name="status" value="2">Closed
                                                <input type="radio" name="status" value="3">Cancelled
                                            </td>
                                        </tr>
                                        <br>
                                           
                                            <input class="SubBut" type="submit" value="Add" name="submit">
                                        
                                    </tbody>
                            </form>
                        </div>
                            <p style="color: red">${thongbao}</p>



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
