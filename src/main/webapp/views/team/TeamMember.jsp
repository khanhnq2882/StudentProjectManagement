<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/general/Head.jsp"></jsp:include>

<body id="page-top">


<!-- Page Wrapper -->
<div id="wrapper">

    <jsp:include page="/general/Sidebar.jsp"></jsp:include>

    <jsp:useBean id="DAOSen" scope="page" class="com.management.dao.DAOSen"/>
    <jsp:useBean id="DAOTeam" scope="page" class="com.management.dao.teamevaluation.DAOTeam"/>

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
                        <h6 class="m-0 font-weight-bold text-primary">List member in team: ${DAOTeam.getTeamById(teamId).team_name}</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Roll number</th>
                                    <th>Full Name</th>
                                    <th>Gender</th>
                                    <th>D.O.B</th>
                                    <th>E-mail</th>
                                    <th>Mobile</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="o" items="${listMember}">
                                    <tr>
                                        <td>${o.roll_number}</td>
                                        <td>${o.fullname}</td>
                                        <td>
                                            <c:if test="${o.gender == 1}">
                                                <% out.print("Male"); %>
                                            </c:if>
                                            <c:if test="${o.gender == 2}">
                                                <% out.print("Female"); %>
                                            </c:if>
                                        </td>
                                        <td>${o.date_of_birth}</td>
                                        <td>${o.email}</td>
                                        <td>${o.mobile}</td>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<jsp:include page="/general/LogOut.jsp"></jsp:include>

<jsp:include page="/general/Footer.jsp"></jsp:include>

</body>

</html>

