<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<jsp:include page="/general/Head.jsp"></jsp:include>

<body id="page-top">
<div id="wrapper">

    <jsp:useBean id="DAOSen" scope="page" class="com.management.dao.DAOSen"/>
    <jsp:useBean id="DAOTeam" scope="page" class="com.management.dao.teamevaluation.DAOTeam"/>
    <jsp:useBean id="DAOTemEvaluation" scope="page" class="com.management.dao.teamevaluation.DAOTeamEvaluation"/>

    <jsp:include page="/general/Sidebar.jsp"></jsp:include>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">

            <jsp:include page="/general/Header.jsp"></jsp:include>

            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Team Evaluation Details</h1>
                            </div>

                            <c:if test="${Loged.role_id eq 2}">
                                <c:if test="${action eq 'add'}">
                                    <form action="<%=request.getContextPath()%>/TeamEvaluationDetail?action=add"
                                          method="POST">
                                        <input type="hidden" name="teamId" value="${teamId}">
                                        <div class="form-group mb-4">
                                            <label class="font-weight-bold">Trainer</label><br>
                                            <label>Trainer name:</label>
                                            <c:set var="trainerId"
                                                   value="${DAOTemEvaluation.getTrainerByTeamId(teamId)}"/>
                                            <input type="text" class="form-control col-md-6"
                                                   value="${DAOSen.getUserById(trainerId).fullname}" disabled>
                                        </div>
                                        <div class="form-group mb-4">
                                            <label class="font-weight-bold">Team</label><br>
                                            <label>Team leader:</label>
                                            <c:set var="teamLeader"
                                                   value="${DAOTeam.getTeamById(teamId).teamLeader}"/>
                                            <input type="text" class="form-control col-md-6"
                                                   value="${DAOSen.getUserById(teamLeader).fullname}" disabled>
                                        </div>
                                        <div class="form-group mb-4">
                                            <label>Grade:</label>
                                            <input type="text" class="form-control" name="grade"
                                                   placeholder="Enter grade for team ...">
                                        </div>
                                        <div class="form-group mb-4">
                                            <label class="font-weight-bold">Student 's performance</label><br>
                                            <label>What comment for this team?</label>
                                            <textarea class="form-control" rows="3" name="note"
                                                      placeholder="Enter a comment ..."></textarea>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Add evaluation</button>
                                    </form>
                                </c:if>
                                <c:if test="${action eq 'update'}">
                                    <form action="<%=request.getContextPath()%>/TeamEvaluationDetail?action=update"
                                          method="POST">
                                        <input type="hidden" name="teamId" value="${teamEvaluation.team_id}">
                                        <input type="hidden" name="teamEvaluationId"
                                               value="${teamEvaluation.team_eva_id}">

                                        <div class="form-group mb-4">
                                            <label class="font-weight-bold">Trainer</label><br>
                                            <label>Trainer name:</label>
                                            <c:set var="trainerId"
                                                   value="${DAOTemEvaluation.getTrainerByTeamId(teamEvaluation.team_id)}"/>
                                            <input type="text" class="form-control col-md-6"
                                                   value="${DAOSen.getUserById(trainerId).fullname}" disabled>
                                        </div>
                                        <div class="form-group mb-4">
                                            <label class="font-weight-bold">Team</label><br>
                                            <label>Team leader:</label>
                                            <c:set var="teamLeader"
                                                   value="${DAOTeam.getTeamById(teamEvaluation.team_id).teamLeader}"/>
                                            <input type="text" class="form-control col-md-6"
                                                   value="${DAOSen.getUserById(teamLeader).fullname}" disabled>
                                        </div>
                                        <div class="form-group mb-4">
                                            <label>Grade:</label>
                                            <input type="number" class="form-control" name="grade"
                                                   placeholder="Enter grade for team ..."
                                                   value="${teamEvaluation.grade}">
                                        </div>
                                        <div class="form-group mb-4">
                                            <label class="font-weight-bold">Student 's performance</label><br>
                                            <label>What comment for this team?</label>
                                            <textarea class="form-control" rows="3" name="note"
                                                      placeholder="Enter a comment ...">${teamEvaluation.note}</textarea>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Update evaluation</button>
                                    </form>
                                </c:if>
                            </c:if>
                            <c:if test="${Loged.role_id eq 1}">
                                <c:if test="${action eq 'update'}">
                                    <input type="hidden" name="teamId" value="${teamEvaluation.team_id}">
                                    <input type="hidden" name="teamEvaluationId" value="${teamEvaluation.team_eva_id}">

                                    <div class="form-group mb-4">
                                        <label class="font-weight-bold">Trainer</label><br>
                                        <label>Trainer name:</label>
                                        <c:set var="trainerId"
                                               value="${DAOTemEvaluation.getTrainerByTeamId(teamEvaluation.team_id)}"/>
                                        <input type="text" class="form-control col-md-6"
                                               value="${DAOSen.getUserById(trainerId).fullname}" disabled>
                                    </div>
                                    <div class="form-group mb-4">
                                        <label class="font-weight-bold">Team</label><br>
                                        <label>Team leader:</label>
                                        <c:set var="teamLeader"
                                               value="${DAOTeam.getTeamById(teamEvaluation.team_id).teamLeader}"/>
                                        <input type="text" class="form-control col-md-6"
                                               value="${DAOSen.getUserById(teamLeader).fullname}" disabled>
                                    </div>
                                    <div class="form-group mb-4">
                                        <label>Grade:</label>
                                        <input type="number" class="form-control" name="grade"
                                               placeholder="Enter grade for team ..."
                                               value="${teamEvaluation.grade}" disabled>
                                    </div>
                                    <div class="form-group mb-4">
                                        <label class="font-weight-bold">Student 's performance</label><br>
                                        <label>What comment for this team?</label>
                                        <textarea class="form-control" rows="3" name="note"
                                                  placeholder="Enter a comment ..."
                                                  disabled>${teamEvaluation.note}</textarea>
                                    </div>
                                </c:if>
                            </c:if>

                            <c:if test="${Loged.role_id ne 1}">
                                <div class="mt-3">
                                    <a class="text-primary" href="<%=request.getContextPath()%>/TeamManagement">Back to
                                        Manage
                                        page</a>
                                </div>
                            </c:if>
                        </div>
                    </div>
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

<!-- Logout Modal-->
<jsp:include page="/general/LogOut.jsp"></jsp:include>

<jsp:include page="/general/Footer.jsp"></jsp:include>

</body>
</html>
