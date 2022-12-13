<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<jsp:include page="/general/Head.jsp"></jsp:include>

<body id="page-top">

<div id="wrapper">

    <jsp:include page="/general/Sidebar.jsp"></jsp:include>

    <jsp:useBean id="DAOSen" scope="page" class="com.management.dao.DAOSen"/>

    <div id="content-wrapper" class="d-flex flex-column">

        <div id="content">
            <jsp:include page="/general/Header.jsp"></jsp:include>
            <div class="container-fluid">
                <h1 class="h3 mb-2 text-gray-800">Team List</h1>

                <div class="card shadow mb-4">
                    <div class="card-header justify-content-end py-3">
                        <c:if test="${sessionScope.Loged.role_id eq 2}">
                            <a class="btn btn-primary mr-3" href="<%=request.getContextPath()%>/FeatureListTrainer">Show
                                All Feature</a>
                        </c:if>
                        <a class="btn btn-primary" href="<%=request.getContextPath()%>/TeamManagement?action=addTeam">Add
                            Team</a>
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Team ID</th>
                                    <th>Class Name</th>
                                    <th>Topic Code</th>
                                    <th>Topic Name</th>
                                    <th>GitLab URL</th>
                                    <th>Leader Name</th>
                                    <th>Status</th>
                                    <th>Issues</th>
                                    <c:if test="${Loged.role_id == 1}">
                                        <th>Feature</th>
                                    </c:if>
                                    <th>Action</th>
                                    <th>Update Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="o" items="${teamList}">
                                    <tr>
                                        <td><a href="TeamManagement?go=teamMember&team_id=${o.team_id}">${o.team_id}</a>
                                        </td>
                                        <td>${o.class_id}</td>
                                        <td>${o.topic_code}</td>
                                        <td>${o.topic_name}</td>
                                        <td>${o.gitlab_url}</td>
                                        <td>
                                            <a class=""
                                               href="IssueController?go=listIssueByUser&team_id=${o.team_id}">${o.team_name}</a>
                                        </td>
                                        <td>
                                            <c:if test="${o.status == 1}">
                                                <% out.print("Activate"); %>
                                            </c:if>
                                            <c:if test="${o.status == 2}">
                                                <% out.print("Deactivate"); %>
                                            </c:if>
                                        </td>
                                        <td>
                                            <a class="" href="IssueController?go=listByTeam&team_id=${o.team_id}">View
                                                Team's Issue of Team ${o.team_id}</a>
                                        </td>
                                        <c:if test="${Loged.role_id == 1}">
                                            <td><a href="FeatureList">View Feature</a></td>
                                        </c:if>
                                        <td>
                                            <div class="d-flex justify-content-center" style="font-size: 20px;">
<%--                                                <c:if test="${Loged.role_id == 2}">--%>
                                                    <a class="mr-2" href="<%=request.getContextPath()%>/TeamEvaluationList?go=listAllTeamEval&team=${o.team_id}"><i
                                                            class="fa-solid fa-eye"></i></a>
<%--                                                </c:if>--%>
                                                <a href="<%=request.getContextPath()%>/TeamManagement?action=updateTeam&teamId=${o.team_id}"><i
                                                        class="fa-solid fa-pen-to-square"></i></a>
                                            </div>
                                        </td>
                                        <td>
                                            <form method="POST" action="TeamManagement?go=listAllTeam&cid=${o.team_id}">
                                                <input type="hidden" name="go" value="updateStatus">
                                                <c:if test="${o.status == 2}">
                                                    <input type="hidden" name="cid" value="${o.team_id}" readonly>
                                                    <input type="hidden" name="teamId" value="${o.team_id}" readonly>
                                                    <input type="hidden" name="status" value="${o.team_id}" readonly>
                                                    <input type="submit" name="submit" value="Activate">
                                                </c:if>
                                                <c:if test="${o.status == 1}">
                                                    <input type="hidden" name="cid" value="${o.team_id}" readonly>
                                                    <input type="hidden" name="teamId" value="${o.team_id}" readonly>
                                                    <input type="hidden" name="status" value="${o.team_id}" readonly>
                                                    <input type="submit" name="submit" value="Deactivate">
                                                </c:if>
                                            </form>
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

<style>
    #dataTable_filter {
        display: flex;
        justify-content: end;
    }

    #dataTable_filter .dropdown-menu {
        padding: 0.2rem 0;
    }

    #dataTable_filter .dropdown-menu {
        max-height: 300px;
        overflow-y: auto;
    }

    .btn-filter button {
        height: calc(1.5em + 0.5rem + 2px);
        padding-top: 0.25rem;
        padding-bottom: 0.25rem;
        padding-left: 0.5rem;
        font-size: 0.875rem;
    }
</style>

<script>
    $(document).ready(function () {
        let html = '      <div class="btn-filter btn-group mb-2 mr-2 ml-2">\n' +
            '       <button class="btn btn-outline-primary dropdown-toggle filter-room-btn text-truncate" type="button" data-toggle="dropdown" aria-expanded="false">${DAOSen.getClassById(classId).classCode}</button>\n' +
            '       <div class="dropdown-menu dropdown-menu-right">\n';
        <c:forEach items="${classList}" var="o">
        html += '        <a class="dropdown-item text-truncate ${o.id eq classId ? "active" : ""}" href="<%=request.getContextPath()%>/TeamManagement?classId=${o.id}">${o.classCode}</a>\n';
        </c:forEach>
        html += '       </div>\n' +
            '      </div>\n';

        $('#dataTable_filter').append(html);
    });
</script>

</body>
</html>
