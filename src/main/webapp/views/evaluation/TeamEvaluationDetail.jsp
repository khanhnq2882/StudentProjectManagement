<%@ page import="com.management.entity.Team" %>
<%@ page import="com.management.entity.TeamEvaluation" %>
<%@ page import="com.management.entity.User" %>
<%@ page import="com.management.entity.Class_s" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<jsp:include page="/general/Head.jsp"></jsp:include>

<body id="page-top">
<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="/general/Sidebar.jsp"></jsp:include>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <jsp:include page="/general/Header.jsp"></jsp:include>
            <%
                Team team = (Team) request.getAttribute("Team");
                TeamEvaluation tEva = (TeamEvaluation) request.getAttribute("TeamEvaluation");
                User user = (User) request.getAttribute("User");
                Class_s class_s = (Class_s) request.getAttribute("Class_s");
            %>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Team Evaluation Details</h1>
                            </div>
                            <form action="TeamList?go=listAllTeam&cid=4" method="post"
                                  onsubmit="return ValidateForm(this);">
                                <input type="hidden" name="go" value="UpdateEval"/>
                                <c:if test="${Loged.role_id == 2}">
                                    <table style="width:100%;max-width: 700px; border: 0;" cellpadding="4"
                                           cellspacing="0">
                                        <tr>
                                            <td colspan="2">
                                                <br/> <b>Team</b>
                                            </td>
                                        </tr>
                                        <td style="width:50%">
                                            <label>Team leader name*:</label><br/>
                                            <input name="LeaderName" type="text" maxlength="100"
                                                   value="<%= user.getFullname()%>" class="form-control"
                                                   style="width:100%;max-width: 300px;" readonly/>
                                            <label>Evaluation ID:</label><br/>
                                            <input name="LeaderName" type="text" maxlength="100" value="${o.eva_id}"
                                                   class="form-control" style="width:100%;max-width: 300px;" readonly/>
                                            <label>Criteria ID:</label><br/>
                                            <input name="LeaderName" type="text" maxlength="100"
                                                   value="${o.criteria_id}" class="form-control"
                                                   style="width:100%;max-width: 300px;" readonly/>
                                            <label>Grade:</label><br/>
                                            <input name="Grade" type="text" maxlength="100" pattern="\d*"
                                                   title="You must input a number" value="<%= tEva.getGrade()%>"
                                                   class="form-control" style="width:100%;max-width: 300px;"/>
                                        </td>
                                        <tr>
                                            <td>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <br/> <b>Student's performance</b>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <label>What comment for this team?</label>
                                                <textarea name="comment" rows="7" cols="40" value="<%= tEva.getNote()%>"
                                                          class="form-control note"
                                                          style="width:100%;max-width: 650px;"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <br/>
                                                * - required fields
                                                <input name="skip_Submit" type="submit" value="Update"/>
                                            </td>
                                        </tr>
                                    </table>
                                </c:if>
                                <c:if test="${Loged.role_id == 1}">
                                    <table style="width:100%;max-width: 700px; border: 0;" cellpadding="4"
                                           cellspacing="0">
                                        <tr>
                                            <td colspan="2">
                                                <br/> <b>Trainer</b>
                                            </td>
                                        </tr>
                                        <td style="width:50%">
                                            <label>Trainer name: <label><%= class_s.getTrainerId()%>
                                            </label></label><br/>
                                        </td>
                                        <tr>
                                            <td colspan="2">
                                                <br/> <b>Team</b>
                                            </td>
                                        </tr>
                                        <td style="width:50%">
                                            <label>Team leader name: <%= user.getFullname()%>
                                            </label><br>
                                            <label>Grade: <%= tEva.getGrade()%>
                                            </label><br>
                                        </td>
                                        <tr>
                                            <td>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <br/> <b>Student's performance</b>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <label><%= tEva.getNote()%>
                                                </label>
                                            </td>
                                        </tr>
                                    </table>
                                </c:if>
                            </form>
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
