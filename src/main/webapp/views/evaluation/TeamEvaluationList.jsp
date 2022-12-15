<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<jsp:include page="/general/Head.jsp"></jsp:include>

<body id="page-top">
<div id="wrapper">
    <jsp:include page="/general/Sidebar.jsp"></jsp:include>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <jsp:include page="/general/Header.jsp"></jsp:include>
            <div class="container-fluid">
                <h1 class="h3 mb-2 text-gray-800">Team Evaluation List</h1>
                <div class="card shadow mb-4">
                    <nav class="navbar navbar-expand-lg navbar-light bg-light">
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav mr-auto">

                            </ul>
                        </div>
                    </nav>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Team Evaluation ID</th>
                                    <th>Evaluation name</th>
                                    <th>Criteria</th>
                                    <th>Team Name</th>
                                    <th>Grade</th>
                                    <th>Note</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="o" items="${TeamEvalList}">
                                    <tr>
                                        <td>${o.team_eva_id}</td>
                                        <td>${o.eva_id}</td>
                                        <td>${o.criteria_id}</td>
                                        <td>${o.team_id}</td>
                                        <td>${o.grade}</td>
                                        <td>${o.note}</td>
                                        <td><a class="" href="TeamEvaluationDetail?go=UpdateEval&team_id=${o.team_id}">
                                            <ion-icon name="document-text-outline"></ion-icon>
                                            View evaluation</a></td>
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
