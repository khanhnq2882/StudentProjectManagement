<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <h1 class="h3 mb-4 text-gray-800">Add more Iteration here</h1>
                <div class="InputForm">
                    <form method="POST" action="IterationListServlet">
                        <input type="hidden" name="go" value="addIteration">
                        <table >
                            <tbody>
                            <tr>
                                <td>Subject Code: <td>
                                <select class="form-control form-control-user id" id="subjectId" name="subjectId">
                                    <c:forEach var="o" items="${listSubCode}">
                                        <option value="${o.subject_id}">${o.subject_code}</option>
                                    </c:forEach>

                                </select>

                            </tr>
                            <tr>
                                <td> Iteration Name: </td>
                                <td><input class="form-control form-control-user id" type="text" name="name" required></td>
                            </tr>
                            <tr>
                                <td>Duration: </td>
                                <td><input class="form-control form-control-user id" type="text" name="duration" placeholder="Ex: 1 day/month,...." required></td>
                            </tr>
                            <tr>
                                <td>Status: </td>
                                <td><input type="radio" name="status" value="1" checked>Active
                                    <input type="radio" name="status" value="2">Deactive</td>
                            </tr>
                            <tr>
                                <td>Note </td>
                                <td><textarea class="form-control form-control-user id" name="note"></textarea></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input class="SubBut" type="submit" value="Add" name="submit"></td>
                            </tr>
                            </tbody>
                            <p style="color: red">${thongbao}</p>
                        </table>
                    </form>
                </div>
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
