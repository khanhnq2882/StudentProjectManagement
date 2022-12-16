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

                <h1 class="h3 mb-4 text-gray-800 container">Update team here</h1>
                <div class="container">
                    <form action="<%=request.getContextPath()%>/TeamManagement?action=updateTeam" method="POST">
                        <input type="hidden" name="teamId" value="${team.team_id}">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Team name:</label>
                                    <input type="text" class="form-control" placeholder="Enter team name ..."
                                           name="teamName"
                                           value="${team.team_name}"
                                           required>
                                </div>
                                <div class="form-group">
                                    <label>Team leader:</label>
                                    <select class="form-control" name="teamLeader" required>
                                        <option value="" disabled selected>Select a team leader</option>
                                        <c:forEach var="o" items="${studentList}">
                                            <option value="${o.user_id}" ${o.user_id eq team.teamLeader ? "selected" : ""}>${o.fullname}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Class:</label>
                                    <select class="form-control" name="classId" required>
                                        <option value="" disabled selected>Select a class</option>
                                        <c:forEach var="o" items="${classList}">
                                            <option value="${o.id}" ${o.id eq team.class_id ? "selected" : ""}>${o.classCode}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Topic Code</label>
                                    <input type="text" class="form-control" placeholder="Enter topic code ..."
                                           name="topicCode"
                                           value="${team.topic_code}" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Topic Name</label>
                                    <input type="text" class="form-control" placeholder="Enter topic name ..."
                                           name="topicName"
                                           value="${team.topic_name}" required>
                                </div>
                                <div class="form-group">
                                    <label>Gitlab url:</label>
                                    <input type="text" class="form-control" placeholder="Enter gitlab url ..."
                                           name="gitlabUrl"
                                           value="${team.gitlab_url}" required>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="status" id="active"
                                           value="1" ${team.status eq 1 ? "checked" : ""}>
                                    <label class="form-check-label" for="active">Active</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="status" id="deActive"
                                           value="2" ${team.status eq 2 ? "checked" : ""}>
                                    <label class="form-check-label" for="deActive">DeActive</label>
                                </div>
                            </div>
                        </div>
                        <br>
                        <button type="submit" class="btn btn-primary">Update Team</button>
                    </form>
                    <div class="mt-3">
                        <a class="text-primary" href="<%=request.getContextPath()%>/TeamManagement">Back to Manage
                            page</a>
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

