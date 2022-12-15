<%@ page import="com.management.util.AbstractConstants" %>
<%@ page import="com.management.util.Alert" %>
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
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="o" items="${teamList}">
                                    <tr class="row${o.team_id}">
                                        <td>${o.team_id}</td>
                                        <td>${DAOSen.getClassById(o.class_id).classCode}</td>
                                        <td>${o.topic_code}</td>
                                        <td>${o.topic_name}</td>
                                        <td>${o.gitlab_url}</td>
                                        <td>${DAOSen.getUserById(o.teamLeader).fullname}</td>
                                        <td>${o.status eq 1 ? "Activate" : "DeActivate"}</td>
                                        <td>
                                            <div id="action-dropdown" class="dropdown">
                                                <a class="dropdown-toggle" type="button" data-toggle="dropdown"
                                                   aria-expanded="false"><i class="fa-solid fa-gear"></i></a>
                                                <div class="dropdown-menu">
                                                    <a class="dropdown-item"
                                                       href="<%=request.getContextPath()%>/TeamManagement?action=updateTeam&teamId=${o.team_id}">Update
                                                        team</a>
                                                    <a class="dropdown-item btn-delete" type="button"
                                                       data-toggle="modal" data-target="#deleteModal">Delete Team</a>
                                                    <a class="dropdown-item"
                                                       href="<%=request.getContextPath()%>/IssueController?go=listByTeam&team_id=${o.team_id}">View
                                                        Issue</a>
                                                    <input type="hidden" name="teamId" value="${o.team_id}">
                                                </div>
                                            </div>
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

<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete Team</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure to delete this Team?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn-delete btn btn-danger">Delete</button>
            </div>
        </div>
    </div>
</div>

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

<script>
    var teamId;
    $('#dataTable .btn-delete').on('click', function () {
        teamId = $(this).parent().find('input[name="teamId"]').val();
        console.log(teamId);
    });

    $('#deleteModal .btn-delete').on('click', function () {
        $.ajax({
            url: "TeamManagement",
            dataType: 'json',
            type: "post",
            data: {
                action: 'deleteTeam',
                teamId: teamId
            },
            success: function (result) {
                console.log(result);
                if (result === '<%=Alert.SUCCESS%>') {
                    swal("", "Delete team successfully!", '<%=Alert.SUCCESS%>');
                    $('.row' + teamId).hide();
                } else {
                    swal("", "Delete team failed!", '<%=Alert.ERROR%>');
                    $('.row' + teamId).hide();
                }
                $('#deleteModal').modal('hide');
            }, error: function (e) {
                swal("", "Delete team failed!", "error");
                $('#deleteModal').modal('hide');
            }
        });
    })
</script>

</body>
</html>
