<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
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

                <!-- Page Heading -->

                <div>

                </div>


                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Iteration Table</h6>
                    </div>
                    <div style="padding-left: 30px">
                        <nav class="navbar navbar-expand-lg navbar-light bg-light">
                            <form style="display: flex;" action="IterationListServlet" method="POST">
                                <p>Subject Code:</p>
                                <select class="form-control form-control-user" id="subjectId" name="subjectId">
                                    <option value="all">List all</option>
                                    <c:forEach var="o" items="${listSubId}">
                                        <option value="${o.subject_id}" ${subId == o.subject_id ? "selected" :""}>${o.subject_code}</option>
                                    </c:forEach>

                                </select>
                                <p>Iteration Name:</p> <input class="form-control mr-sm-2" type="text" name="IteName">
                                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="submit"
                                       value="search">
                            </form>
                        </nav>

                        <div><a class="btn btn-outline-primary" href="IterationListServlet?go=addIteration">Add
                            Iteration</a></div>
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">

                            <table class="table table-bordered" width="100%" cellspacing="0">

                                <thead>
                                <tr>

                                    <th>subject_code</th>
                                    <th>iteration_name</th>
                                    <th>duration</th>
                                    <th>Status</th>

                                    <th class="text-center">Action</th>

                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="o" items="${list}">
                                    <tr>

                                        <td>${o.subject_code}</td>
                                        <td>${o.iteration_name}</td>
                                        <td>${o.duration}</td>
                                        <td>
                                            <form id="idS${o.iteration_id}"
                                                  action="IterationListServlet?go=updateStatus" method="Post">
                                                <input type="hidden" name="iteId" value="${o.iteration_id}">
                                                <select class="form-control form-control-user id" id="status"
                                                        name="status" onchange="submitForm(idS${o.iteration_id})">
                                                    <option value="1" ${o.status == 1 ? "selected" : ""}>Active</option>
                                                    <option value="2" ${o.status == 2 ? "selected" : ""}>Deactive
                                                    </option>
                                                </select>
                                            </form>
                                        </td>

                                        <td class="text-center"><a class="text text-primary"
                                                                   href="IterationListServlet?go=updateIteration&iteId=${o.iteration_id}&subjectId=${o.subject_id}">
                                            Update
                                        </a>

                                            <a class="text text-danger"
                                               href="IterationListServlet?go=deleteIteration&iteId=${o.iteration_id}&subjectId=${o.subject_id}">
                                                Delete
                                            </a></td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>

                            <c:if test="${page>1}">
                                <a class="btn btn-outline-secondary"
                                   href="IterationListServlet?go=listAllIteration&page=${page - 1}">pre</a>
                            </c:if>

                            ${page}
                            <c:if test="${page!=countPage}">
                                <a class="btn btn-outline-secondary"
                                   href="IterationListServlet?go=listAllIteration&page=${page + 1}">next</a>
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
<jsp:include page="/general/Footer.jsp"></jsp:include>

</body>
</html>
