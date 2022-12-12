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
            <div class="container">

                <div class="row">
                    <div class="col-lg-12 card">
                        <div class="p-5 card-body">
                            <div>
                                <h5 style="font-weight: bold">Update Iteration</h5>
                            </div>
                            <br>
                            <form action="IterationListServlet" method="POST">
                                <input type="hidden" name="go" value="updateIteration"/>
                                <input type="hidden" name="iteId" value="${iteUpdate.iteration_id}"/>

                                <strong>Subject Code:</strong>
                                <input class="form-control form-control-user id" hidden value="${iteUpdate.subject_id}"
                                       name="subjectId"> ${iteUpdate.subject_code}
                                <br>
                                <br>
                                <strong>Iteration Name:</strong>
                                <input class="form-control form-control-user id" type="text" name="name"
                                       value="${iteUpdate.iteration_name}" required>
                                <br>
                                <strong>Duration: </strong>
                                <input class="form-control form-control-user id" type="text" name="duration"
                                       value="${iteUpdate.duration}">
                                <br>
                                <strong>Status:</strong>
                                <input type="radio" name="status" value="1" ${iteUpdate.status == 1 ? "checked" : ""}>Active
                                <input type="radio" name="status" value="2" ${iteUpdate.status == 2 ? "checked" : ""}>Deactivate
                                <br>
                                <br>
                                <strong>Note: </strong>
                                <textarea class="form-control form-control-user" name="note"
                                          value="${iteUpdate.note}"></textarea>
                                <br>

                                <input class="btn btn-primary" type="submit" value="Save" name="submit">
                                <input class="btn btn-primary" type="reset" value="Reset">

                            </form>
                            <span style="color: red; font-weight: bold;">${thongbao}</span>
                        </div>
                        <!-- /.container-fluid -->
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
<jsp:include page="/general/Footer.jsp"></jsp:include>


</body>
</html>