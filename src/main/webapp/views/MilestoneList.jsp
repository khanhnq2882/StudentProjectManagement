<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/general/Head.jsp"></jsp:include>

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
                <div></div>
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Milestone Table</h6>
                    </div>

                    Class Code
                    <select onchange="CallAjax()" class="form-control form-control-user" id="classId" name="classId">
                        <option value="all">All Class Code</option>
                        <c:forEach var="o" items="${listClassCode}">
                            <option value="${o.class_id}">${o.classCode}</option>
                        </c:forEach>

                    </select>
                    Iteration Name:<input oninput="CallAjax()" class="form-control form-control-user" type="text"
                                          id="iteId" name="iteId">


                    <div>Click <a class="btn btn-outline-primary" href="MilestoneListServlet?go=addMilestone">here</a>
                        to add Milestone
                    </div>


                    <div>All Of Classes: <c:forEach var="o" items="${listClassCode}">
                        <a class="btn btn-outline-primary"
                           href="MilestoneListServlet?go=EachClass&classId=${o.class_id}">${o.classCode} </a>
                    </c:forEach>
                        <div>

                            <div class="card-body">
                                <div class="table-responsive">

                                    <table id="slide" class="table table-bordered" width="100%" cellspacing="0">

                                        <thead>
                                        <tr>
                                            <th>Milestone Name</th>
                                            <th>Iteration Name</th>
                                            <th>Class Code</th>
                                            <th>From Date</th>
                                            <th>To Date</th>
                                            <th>Status</th>
                                            <th>Action</th>

                                        </tr>
                                        </thead>

                                        <tbody>
                                        <c:forEach var="o" items="${list}">
                                            <tr>
                                                <td>${o.milestone_name}</td>
                                                <td>${o.iterationName}</td>
                                                <td>${o.classCode}</td>
                                                <td>${o.from_date}</td>
                                                <td>${o.to_date}</td>
                                                <td>
                                                    <form id="idS${o.milestone_id}"
                                                          action="MilestoneListServlet?go=updateStatus" method="Post">
                                                        <input type="hidden" name="mileId" value="${o.milestone_id}">
                                                        <select class="form-control form-control-user id" id="status"
                                                                name="status"
                                                                onchange="submitForm(idS${o.milestone_id})">
                                                            <option value="1" ${o.status == 1 ? "selected" : ""}>Open
                                                            </option>
                                                            <option value="2" ${o.status == 2 ? "selected" : ""}>
                                                                Closed
                                                            </option>
                                                            <option value="3" ${o.status == 3 ? "selected" : ""}>
                                                                Cancelled
                                                            </option>
                                                        </select>
                                                    </form>
                                                </td>
                                                <td><a class="text text-primary"
                                                       href="MilestoneListServlet?go=updateMilestone&mileId=${o.milestone_id}&iteId=${o.interation_id}&classId=${o.class_id}">
                                                    <ion-icon size="large" name="create"></ion-icon>
                                                </a>
                                                    <a class="text text-danger"
                                                       href="MilestoneListServlet?go=deleteMilestone&mileId=${o.milestone_id}&iteId=${o.interation_id}&classId=${o.class_id}">
                                                        <ion-icon size="large" name="trash"></ion-icon>
                                                    </a></td>

                                            </tr>
                                        </c:forEach>

                                        </tbody>
                                    </table>

                                    <c:if test="${page>1}">
                                        <a href="MilestoneListServlet?go=listAllMilestone&page=${page - 1}">pre</a>
                                    </c:if>

                                    Page ${page}
                                    <c:if test="${page!=countPage}">
                                        <a href="MilestoneListServlet?go=listAllMilestone&page=${page + 1}">next</a>
                                    </c:if>
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

        <jsp:include page="/general/Footer.jsp"></jsp:include>

        <script>
            function CallAjax() {
                var classId = document.getElementById("classId").value;
                var iteName = document.getElementById("iteId").value;
                var Ajax = "Ajax";
                $.ajax({
                    url: "/g1/MilestoneListServlet",
                    type: "get",
                    data: {
                        Ajax: Ajax,
                        classId: classId,
                        iteId: iteName
                    },
                    success: function (data) {
                        var slide = document.getElementById("slide");
                        slide.innerHTML = data;
                    },
                    error: function (xhr) {
                    }
                });
            }
        </script>

</body>

</html>
