<%-- 
    Document   : HomeExample
    Created on : May 20, 2022, 12:55:19 PM
    Author     : asus
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="/general/Head.jsp"></jsp:include>
<body id="page-top">

<div id="slide"></div>
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
                <div class="container_head">
                    <div class="link" style="color: white">
                        <a href="Home"> Dashboard </a>
                        <p> / </p>
                        <a href="Tracking"> Tracking </a>
                        <p> / </p>
                        <a href="AddTracking"> Add Tracking</a>
                    </div>
                    <h4 class="h4 two-lines"></h4>
                    <span class="span"></span>
                </div>
                <div style="margin-top: 150px"></div>
                <div class="card shadow mb-4">
                    <div class="card-header py-3"> Add new Tracking
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <form action="UpdateTracking" method="POST">
                                <li>
                                    Team: <br>
                                    <select class="form-control form-control-user team_id" name="team_id">
                                        <c:forEach var="o" items="${lteam}">
                                            <option value="${o.team_id}">${o.team_name}</option>
                                        </c:forEach>
                                    </select>
                                </li>
                                <li>
                                    Milestone: <br>
                                    <select class="form-control form-control-user milestone_id" name="milestone_id">
                                        <c:forEach var="o" items="${lMilestone}">
                                            <option value="${o.milestone_id}">${o.iterationName}</option>
                                        </c:forEach>
                                    </select>
                                    <span id=""></span>
                                </li>
                                <li>
                                    Function: <br>
                                    <select class="form-control form-control-user function_id" name="function_id">
                                        <c:forEach var="o" items="${lFunction}">
                                            <option value="${o.function_id}">${o.function_name}</option>
                                        </c:forEach>
                                    </select>
                                    <span id=""></span>
                                </li>
                                <li>
                                    Assigner: <br>
                                    <select class="form-control form-control-user assigner_id" name="assigner_id">
                                        <c:forEach var="o" items="${lNotStudent}">
                                            <option value="${o.user_id}">${o.fullname}</option>
                                        </c:forEach>
                                    </select>
                                    <span id=""></span>
                                </li>
                                <li>
                                    Assignee: <br>
                                    <select class="form-control form-control-user assignee_id" name="assignee_id">
                                        <c:forEach var="o" items="${lStudent}">
                                            <option value="${o.user_id}">${o.fullname}</option>
                                        </c:forEach>
                                    </select>
                                    <a style="color: blue; cursor: pointer;" onclick="changeOption()">Assign me</a>
                                    <span id=""></span>
                                </li>
                                <li>
                                    Note: <br>
                                    <textarea name="note" class="form-control form-control-user tracking_note" rows="4"
                                              cols="20">${tracking_note == null ? a.tracking_note : tracking_note}</textarea>
                                    <span id=""></span>
                                </li>
                                <li>${updates}
                                    Update: <br>
                                    <textarea name="note" class="form-control form-control-user updates" rows="4"
                                              cols="20">${updates == null ? a.update : updates}</textarea>
                                    <span id=""></span>
                                </li>
                                <li>
                                    Status: <br>
                                    <select name="status" class="form-control form-control-user statuses">
                                        <option ${status == 1 ? "selected" : ""} value="1">Planed</option>
                                        <option ${status == 2 ? "selected" : ""} value="2">Analysed</option>
                                        <option ${status == 3 ? "selected" : ""} value="3">Designed</option>
                                        <option ${status == 4 ? "selected" : ""} value="4">Coded</option>
                                        <option ${status == 5 ? "selected" : ""} value="5">Integrated</option>
                                        <option ${status == 6 ? "selected" : ""} value="6">Submitted</option>
                                        <option ${status == 7 ? "selected" : ""} value="7">Evaluated</option>
                                        <option ${status == 8 ? "selected" : ""} value="8">Rejected</option>
                                    </select>
                                </li>
                            </form>
                            <input type="submit" onclick="Confirm()" class="form-control form-control-user ongoingeval"
                                   value="Add" style="width: 100px"/>
                        </div>
                    </div>
                </div>

                <!-- /.container-fluid -->
                <div id="confirm"></div>
            </div>

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
    <jsp:include page="/general/Footer.jsp"></jsp:include>
    <script>
        function changeOption() {
            var idConfirm = "UpdateTracking";
            var id = document.querySelector(".id").value
            $.ajax({
                url: "/g1/UpdateTracking",
                type: "get",
                data: {
                    idConfirm: idConfirm,
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

        function Confirm() {
            var team_id = document.querySelector(".team_id").value;
            var milestone_id = document.querySelector(".milestone_id").value;
            var function_id = document.querySelector(".function_id").value;
            var assigner_id = document.querySelector(".assigner_id").value;
            var assignee_id = document.querySelector(".assignee_id").value;
            var tracking_note = document.querySelector(".tracking_note").value;
            var updates = document.querySelector(".updates").value;
            var statuses = document.querySelector(".statuses").value;
            var idConfirm = "AddTracking";
            $.ajax({
                url: "/g1/Confirm",
                type: "get",
                data: {
                    idConfirm: idConfirm,
                    team_id: team_id,
                    milestone_id: milestone_id,
                    function_id: function_id,
                    assigner_id: assigner_id,
                    assignee_id: assignee_id,
                    tracking_note: tracking_note,
                    updates: updates,
                    statuses: statuses
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

</body>

</html>
