<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : addSetting
    Created on : May 18, 2022, 3:54:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:set var="typeValue" value="${sessionScope.typeValue}"></c:set>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <meta name="description" content="">
            <meta name="author" content="">

            <title>Group 1 - Add Setting</title>

            <!-- Custom fonts for this template-->
            <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
            <link
                href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
                rel="stylesheet">

            <!-- Custom styles for this template-->
            <link href="css/sb-admin-2.min.css" rel="stylesheet">
            <link href="css/CaiNayCuaThanh.css" rel="stylesheet">

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
                            <div class="card o-hidden border-0 shadow-lg my-5">
                                <div class="card-body p-0">
                                    <div class="row col-lg-12">
                                        
                                            <div class="p-5">
                                                <h1 class="h3 mb-4 text-gray-800">Add more setting here</h1>
                                                <form method="POST" action="SettingListServlet">
                                                    <input type="hidden" name="go" value="addSetting">


                                                    Setting Type:
                                                    <select class="form-control" id="settingType" name="settingType">
                                                    <c:forEach var="o" items="${listType}">
                                                        <option value="${o.type_id}">${typeValue.get(o.type_id - 1)}</option>
                                                    </c:forEach>
                                                </select>
                                                <br>
                                                Setting Title:
                                                <input class="form-control"  type="text" name="name" required>
                                                <br>
                                                Value:
                                                <input class="form-control"  type="text" name="value" required>
                                                <br>
                                                Order:
                                                <input class="form-control" type="text" name="order" required>
                                                <br>
                                                Note:
                                                <textarea class="form-control" name="note"  rows="4"></textarea>
                                                <br>
                                                Status:
                                                <input type="radio" name="status" value="1" checked>Active
                                                <input type="radio" name="status" value="2">Deactive
                                                <br>
                                                <input class="btn btn-primary" type="submit" name="submit"  value="Add" />


                                            </form>
                                        </div>
                                  

                                </div>
                            </div>
                        </div>

                        <!-- Page Heading -->
                        





                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

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

    </body>
</html>
