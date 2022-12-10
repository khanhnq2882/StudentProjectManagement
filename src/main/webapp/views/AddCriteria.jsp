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
            <div class="container">
                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <!-- 404 Error Text -->
                                    <form class="user" action="CriteriaDetail" method="post">
                                        <input type="hidden" name="go" value="addCriteria"/>
                                        <h3>Add New Criteria</h3>
                                        Iteration(*):
                                        <select class="form-control" name="iteration" required>
                                            <c:forEach var="o" items="${IterList}">
                                                <option value="${o.iteration_id}">${o.subject_code}-${o.iteration_name}</option>
                                            </c:forEach>
                                        </select>
                                        Title(*):
                                        <input class="form-control" maxlength="140" type="text" name="title"
                                               value="${txtTitle}" required/>
                                        Weight(*):
                                        <br> <input style="width:350px" type="text" name="weight" value="${txtWeight}"
                                                    required/>%
                                        <div style="color: red">${errW}</div>
                                        LOC(*):
                                        <input class="form-control" type="text" name="loc" value="${txtLoc}" required/>
                                        <div style="color: red">${errL}</div>
                                        Order(*):
                                        <br> <input style="width:350px" type="text" name="order" value="${txtOrder}"
                                                    required/>
                                        <div style="color: red">${errO}</div>
                                        Status:</br>
                                        <input type="radio" name="status" value="1" checked>Active
                                        <input style="margin-left: 30px" type="radio" name="status" value="2">Deactive
                                        <span style="margin-left:35px"> Team Evaluation:</span>
                                        <input type="checkbox" name="evaluation" value="true"/>
                                        <br>Description:
                                        <textarea class="form-control" name="description"></textarea><br>
                                        <input type="submit" name="submit" class="update" value="Add"/>


                                    </form>
                                </div>
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
