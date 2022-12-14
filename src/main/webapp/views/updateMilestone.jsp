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
                        <div class="container">


                            <div class="row">
                                <div class="col-lg-12 card" >
                                    <div class="p-5 card-body">
                                        <div>
                                            <h5 style="font-weight: bold">Update Milestone</h5>
                                        </div>
                                        <br>
                                        <form  action="MilestoneListServlet" method="POST">
                                            <input type="hidden" name="go" value="updateMilestone" />
                                            

                                        <strong>Milestone Name:</strong>
                                        <input class="form-control form-control-user id" type="hidden" value="${listMile.milestone_id}" name="mileId">${listMile.milestone_name}
                                        <br> 
                                        <br>
                                        <strong>Iteration Name:</strong>
                                        <input class="form-control form-control-user id" type="hidden" value="${listMile.interation_id}" name="iteId">${listMile.iterationName} 
                                        <br> 
                                        <br>
                                        <strong> Class Code: </strong>
                                        <input class="search" type="hidden" name="classId" value="${listMile.class_id}" >${listMile.classCode}
                                        <br> 
                                        <br>
                                        <strong>From Date: </strong>
                                        <input class="form-control form-control-user id" type="date" name="fromDate" value="${listMile.from_date}" required>
                                        <br>
                                        <strong>To Date: </strong>
                                        <input class="form-control form-control-user id" type="date" name="toDate" value="${listMile.to_date}" required>
                                        <br>
                                        <strong>Status:</strong>
                                        <input type="radio" name="status" value="1" ${listMile.status == 1 ? "checked" : ""} > Open
                                        <input type="radio" name="status" value="2" ${listMile.status == 2 ? "checked" : ""}> Closed
                                        <input type="radio" name="status" value="3" ${listMile.status == 3 ? "checked" : ""}> Cancelled
                                        <br>
                                        <br>                     

                                        <input class="btn btn-primary" type="submit" value="Save" name="submit" >
                                        <input class="btn btn-primary" type="reset" value="Reset">

                                    </form>
                                    <span style="color: red; font-weight: bold;">${thongbao}</span>
                                </div> 
                                <!-- /.container-fluid -->
                            </div>
                        </div>



<!--                        <h1 class="h3 mb-4 text-gray-800">Update Milestone here</h1>
                        <div class="InputForm">
                            <form method="POST" action="MilestoneListServlet">
                                <input type="hidden" name="go" value="updateMilestone">


                                <tbody>
                                    <tr>
                                        <td> Milestone Name:</td>                                           
                                        <td> <input class="form-control form-control-user id" type="hidden" value="${listMile.milestone_id}" name="mileId">${listMile.milestone_name} </td><br>                                                                                                              
                                </tr>
                                <tr>
                                    <td> Iteration Name: <td>                                          
                                        <input class="form-control form-control-user id" type="hidden" value="${listMile.interation_id}" name="iteId">${listMile.iterationName}   <br>                                                                                                                            
                                </tr>
                                <tr>
                                    <td> Class Code: </td>
                                    <td><input class="search" type="hidden" name="classId" value="${listMile.class_id}" >${listMile.classCode}</td><br>
                                </tr>
                                <tr>
                                    <td>From Date: </td>
                                    <td><input class="form-control form-control-user id" type="date" name="fromDate" value="${listMile.from_date}" required></td>
                                </tr>    
                                <tr>
                                    <td>To Date: </td>
                                    <td><input class="form-control form-control-user id" type="date" name="toDate" value="${listMile.to_date}" required></td>
                                </tr> 
                                <tr>
                                    <td>Status: </td>
                                    <td><input type="radio" name="status" value="1" ${listMile.status == 1 ? "checked" : ""} > Open
                                        <input type="radio" name="status" value="2" ${listMile.status == 2 ? "checked" : ""}> Closed
                                        <input type="radio" name="status" value="3" ${listMile.status == 3 ? "checked" : ""}> Cancelled
                                    </td>                                            
                                </tr>
                                <tr>
                                    <td></td><br>
                                <td><input class="SubBut" type="submit" value="Update" name="submit"></td>
                                </tr>
                                </tbody>

                            </form>
                        </div>
                        <p style="color: red">${thongbao}</p>-->



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
