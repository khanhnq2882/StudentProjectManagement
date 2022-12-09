<%@ page import="com.management.entity.User" %>
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
        
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.0/font/bootstrap-icons.css">

        <title>Group 1 - Team list</title>

        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link href="css/css379.css" rel="stylesheet">
        
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

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
                    <%
                        User user = (User)request.getAttribute("User");
                    %>
                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Team List</h1>

                        <div>   
                            <form action="SearchSet" method="POST">
                                <label for="status"></label>
                                <select class="SelectDrop" name="status" id="status">
                                    <option value="all" ${sta1.equals("all") ? "selected" : ""}>All statuses</option>
                                    <option value="1" ${sta1 == 1 ? "selected" : ""}>Activate</option>
                                    <option value="2" ${sta1 == 2 ? "selected" : ""}>Deactivate</option>
                                </select>
                                <a class="LinkHere" href="TeamList?go=addTeam">Add Team</a>
                            </form>
                        </div>                                                             


                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary"></h6>
                                <c:if test="${Loged.role_id == 2}">
                                    <a href="FeatureListTrainer">Show All Feature</a
                                </c:if>
                            </div>




                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Team ID</th>
                                                <th>Class Name</th>
                                                <th>Topic Code</th>
                                                <th>Topic Name</th>
                                                <th>GitLab URL</th>
                                                <th>Leader Name</th>
                                                <th>Status</th>
                                                <th>Issues</th>
                                                 <c:if test="${Loged.role_id == 1}">
                                                 <th>Feature</th></c:if>
                                                <th>Action</th>
                                                <th>Update Status</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>Team ID</th>
                                                <th>Class Name</th>
                                                <th>Topic Code</th>
                                                <th>Topic Name</th>
                                                <th>GitLab URL</th>
                                                <th>Leader Name</th>
                                                <th>Status</th>
                                                <th>Issues</th>
                                                 <c:if test="${Loged.role_id == 1}">
                                                 <th>Feature</th></c:if>
                                                <th>Action</th>
                                                <th>Update Status</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach var="o" items="${TeamList}">
                                                <tr>
                                                    <td><a href="TeamList?go=teamMember&team_id=${o.team_id}">${o.team_id}</a></td>
                                                    <td>${o.class_id}</td>
                                                    <td>${o.topic_code}</td>
                                                    <td>${o.topic_name}</td>
                                                    <td>${o.gitlab_url}</td>
                                                    <td>
                                                        <a class="" href="IssueController?go=listIssueByUser&team_id=${o.team_id}">${o.team_name}</a></td>
                                                    <td>
                                                        <c:if test="${o.status == 1}">
                                                            <% out.print("Activate"); %>
                                                        </c:if>
                                                        <c:if test="${o.status == 2}">
                                                            <% out.print("Deactivate"); %>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <a class="" href="IssueController?go=listByTeam&team_id=${o.team_id}">View Team's Issue of Team ${o.team_id}</a>
                                                    </td>
                                                    <c:if test="${Loged.role_id == 1}">
                                                        <td><a href="FeatureList">View Feature</a></td></c:if>
                                                    <td>
                                                        <c:if test="${Loged.role_id == 2}">
                                                            <a class="" href="TeamDetail?go=UpdateTeam&team_id=${o.team_id}"><ion-icon name="create-outline"></ion-icon>Edit</a>
                                                        </c:if>
                                                            <br>
                                                            <a class="" href="TeamEvaluationList?go=listAllTeamEval&team=${o.team_id}"><ion-icon name="document-text-outline"></ion-icon>View evaluation list</a>
                                                    </td>
                                                    <td>
                                                        <form method="POST" action="TeamList?go=listAllTeam&cid=${o.team_id}">
                                                            <input type="hidden" name="go" value="updateStatus">
                                                            <c:if test="${o.status == 2}">
                                                                <input type="hidden" name="cid" value="${o.team_id}" readonly>
                                                                <input type="hidden" name="teamId" value="${o.team_id}" readonly>
                                                                <input type="hidden" name="status" value="${o.team_id}" readonly>
                                                                <input type="submit" name="submit" value="Activate">
                                                            </c:if>
                                                            <c:if test="${o.status == 1}">
                                                                <input type="hidden" name="cid" value="${o.team_id}" readonly>
                                                                <input type="hidden" name="teamId" value="${o.team_id}" readonly>
                                                                <input type="hidden" name="status" value="${o.team_id}" readonly>
                                                                <input type="submit" name="submit" value="Deactivate">
                                                            </c:if>
                                                        </form>    
                                                    </td>
                                                </tr>                    
                                            </c:forEach>
                                        </tbody>
                                    </table>
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

            <!-- Page level plugins -->
            <script src="vendor/datatables/jquery.dataTables.min.js"></script>
            <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

            <!-- Need copy for use alert-->
            <script src="js/SenJS.js"></script>
            <script src="js/fnon.min.js"></script>
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    Fnon.Hint.Init({
                        zIndex: 9900,
                    });
                    // Hint
                    var message = "${message}";
                    var theme = "${theme}";
                    var title = "${title}";
                    var position = "right-top";
                    var animation = "slide-left";
                    Fnon.Hint[theme](message, {
                        title,
                        position,
                        animation,
                    })
                });
        </script>
        <!-- End Need copy for use alert-->

        <script>
            function Confirm(sub_id) {
                let id = sub_id;
                var idConfirm = "test";
                $.ajax({
                    url: "/g1/Confirm",
                    type: "get",
                    data: {
                        subject_id: id,
                        idConfirm: idConfirm
                    },
                    success: function (data) {
                        var confirm = document.getElementById("confirm");
                        confirm.innerHTML = data;
                        $("#confirm1").modal("show");
                    },
                    error: function (xhr) {
                    }
                });
            }
        </script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>

    </body>
</html>
