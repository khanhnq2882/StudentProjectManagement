<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/general/Head.jsp"></jsp:include>


    <body id="page-top">


        <style>
            .EditLink {
                background-color: #a054e8d8;
                color: rgb(209, 216, 222);
                padding: 1px 15px;
                border-radius: 15px;
                border: solid 1px black;
                color: aliceblue;
                box-shadow: 1px 1px rgb(159 156 156);
            }
        </style>

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
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">All Issues:</h6>
                                    <form action="IssueController?go=searchStatus" method="post">
                                        <select class="form-control form-control-user" onchange="this.form.submit()" name="statusSearch">
                                            <option value="0" ${statusSerach == 0 ? "selected" : ""}>All status</option>
                                        <option value="1" ${statusSerach == 1 ? "selected" : ""}>To Do</option>
                                        <option value="2" ${statusSerach == 2 ? "selected" : ""}>Pending</option>
                                        <option value="3" ${statusSerach == 3 ? "selected" : ""}>Done</option>
                                    </select>
                                </form>
                                <image
                                    <ul class="spbw">
                                    <li>
                                        <form action="IssueController?go=syncIssue" method="post">
                                            <input type="hidden" name="team_id" value="${teamID}" />
                                            <input type="submit" value="Sync From GiLab" />
                                        </form>
                                        <!--<a href="IssueController?go=syncIssue">Sync from GitLab</a>-->
                                    </li>
                                    <!--<li><a href="IssueController?go=addIssue">Add new Issue</a></li>-->
                                    <!--                                    <li>
                                                                            <a type="submit" href="IssueFile?go=uploadIssue&team_id=" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"/>Upload</a>
                                                                            <a href="ExcelTest?ClassId=" 
                                                                               class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                                                                    class="fas fa-download fa-sm text-white-50"></i>Download list issue</a>
                                                                            <a href="\g1\Template_G1.xlsx" 
                                                                               class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                                                                    class="fas fa-download fa-sm text-white-50"></i>Download template</a>
                                                                        </li>-->
                                </ul>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Title</th>
                                                <th>Description</th>
                                                <th>Assignee</th>
                                                <th>Created_at</th>
                                                <th>Due date</th>
                                                <th>Label</th>
                                                <th>Status</th>
                                                <!--<th>Action</th>-->
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="o" items="${listTeam}">
                                                <tr>
                                                    <td>${o.issue_title}</td>
                                                    <td>${o.description}</td>
                                                    <td>
                                                        <form action="IssueController" method="post" >
                                                            <input type="hidden" name="go" value="changeAss" />
                                                            <input type="hidden" name="issue_ID" value="${o.issue_id}" />
                                                            <select disabled id="assignee" class="form-control form-control-user" name="assign_ID" onchange="this.form.submit()" >
                                                                <c:forEach var="a" items="${listAss}">
                                                                    <option  value="${o.assignee_id}" ${a.user_id == o.assignee_id ? "selected" : ""}>${a.fullname}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </form>
                                                    </td>
                                                    <td>${o.created_at}</td>
                                                    <td>${o.due_date}</td>
                                                    <td>${o.label}</td>
                                                    <td>
                                                        <form action="IssueController?go=changeStatusTeam" method="post">
                                                            <input type="hidden" name="issueid" value="${o.issue_id}"/>
                                                            <select class="form-control form-control-user" name="status" onchange="this.form.submit()">
                                                                <option ${o.status == 1 ? "selected" : ""} value="1">To Do</option>
                                                                <option ${o.status == 2 ? "selected" : ""} value="2">Pending</option>
                                                                <option ${o.status == 3 ? "selected" : ""} value="3">Done</option>
                                                            </select>
                                                        </form>
                                                    </td>
                                                    <!--<td><a class="EditLink" href="IssueController?go=updateIssue&issueID=">Edit</a></td>-->
                                                    <!--                                                    <td>
                                                                                                            <a class="EditLink" href="IssueController?go=updateIssue&issueID=">Edit</a>
                                                                                                            <a onclick="return confirm('Are you sure you want to delete this issue?')" class="EditLink" href="IssueController?go=removeIssue&issueID=">Delete</a>
                                                                                                        </td>-->
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>    
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <script src="js/SenJS.js"></script>
            <script src="js/fnon.min.js"></script>
            <script src="js/sweetalert.min.js"></script>
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

            <!--            <script>
                                                                            function changeOption() {
                                                                                var go = "IssueSync";
                                                                                var id = document.querySelector(".id").value
                                                                                $.ajax({
                                                                                    url: "/g1/IssueController",
                                                                                    type: "get",
                                                                                    data: {
                                                                                        go: go,
                                                                                        id: id
                                                                                    },
                                                                                    success: function (data) {
                                                                                        var confirm = document.querySelector(".go");
                                                                                        confirm.innerHTML = data;
                                                                                    },
                                                                                    error: function (xhr) {
                                                                                    }
                                                                                });
                                                                            }
                        </script>-->

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


