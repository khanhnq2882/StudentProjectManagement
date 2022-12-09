<%-- 
    Document   : addTeam
    Created on : Jun 15, 2022, 10:32:34 AM
    Author     : tqbao
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Group 1 - Add Subject Setting</title>

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

                            <!-- Page Heading -->
                            <h1 class="h3 mb-4 text-gray-800 container">Add team here</h1>
                            <div class="container">
                                <form method="POST" action="TeamList">
                                    <input type="hidden" name="go" value="addTeam">
                                    <table >
                                        <tbody>
                                            <tr>
                                                <td>Class:</td>
                                                <td>
                                                <select id="class" name="class">
                                                    <c:forEach var="o" items="${listClass}">
                                                        <option value="${o.id}" width="500px">${o.classCode}</option>
                                                    </c:forEach>
                                                </select></td>
                                        </tr>
                                        <tr>
                                            <td>Topic Code*:</td>
                                            <td>
                                                <input type="text" name="topicCode" width="500px" required>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Topic Name*:</td>
                                            <td><input type="text" name="topicName" width="500px" required></td>
                                        </tr>
                                        <tr>
                                            <td>GitLab URL:</td>
                                            <td><input type="text" name="gitlabURL" width="500px"></td>
                                        </tr>
                                        <tr>
                                            <td>Status:</td>
                                            <td><input type="radio" name="status" value="1" checked>Active
                                                <input type="radio" name="status" value="2">Deactive</td>
                                        </tr>
                                        <tr>
                                            <td>Team Leader</td>
                                            <td>
                                                <select id="classTeam" name="classTeam" width="500px">
                                                    <c:forEach var="o" items="${listLeader}">
                                                        <option value="${o.team_name}">${o.team_name}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><input class="SubBut" type="submit" value="Add" name="submit"></td>
                                        </tr>
                                    </tbody>
                                </table>                     
                            </form>
                        </div>
                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2020</span>
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

    </body>
</html>

