<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<jsp:include page="/general/Head.jsp"></jsp:include>

<body id="page-top">
<!-- Page Wrapper -->
<div id="wrapper">

    <jsp:include page="/general/Sidebar.jsp"></jsp:include>

    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <jsp:include page="/general/Header.jsp"></jsp:include>

            <div class="container-fluid">

                <h1 style="font-weight: bold" class="h3 mb-2 text-gray-800"> Criteria List</h1>
                <ul class="spbw">
                    <li><a id="add" style="" type="submit" href="CriteriaDetail?go=add"
                           class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Add new criteria</a></li>
                    <li>
                        <form style="display: flex !important;" action="CriteriaSearch" method="POST">
                            <select class="form-control form-control-user" name="subject" id="subject">
                                <option value="all">All Subject</option>
                                <c:forEach var="o" items="${subjectList}">
                                    <option value="${o.subject_id}" ${Id==o.subject_id ? "selected" : ""}>${o.subject_name}</option>
                                </c:forEach>

                            </select>
                            <input type="text" name="searchName" placeholder="Search Iteration and Loc"
                                   value="${txtSearch}">
                            <input type="submit" value="Search" name="submit" class="btn btn-primary">
                        </form>
                    </li>
                </ul>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">

                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">

                                <thead>
                                <tr>
                                    <th>Subject</th>
                                    <th>Iteration</th>
                                    <th>Title</th>
                                    <th>Weight</th>
                                    <th>LOC</th>
                                    <th>Team</th>
                                    <th>Order</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="o" items="${CriteriaList}">
                                    <tr>
<%--                                        <td visibility: hidden>${o.criteria_id}</td>--%>
<%--                                        <td visibility: hidden>${o.subject_id}</td>--%>
                                        <td>${o.subject_code}</td>
                                        <td>${o.iteration_name}</td>
                                        <td>${o.evaluation_title}</td>
                                        <td>${o.evaluation_weight}%</td>
                                        <td>${o.max_loc}</td>
                                        <td>
                                            <c:if test="${o.team_evaluation == true}">
                                                <% out.print("Yes"); %>
                                            </c:if>
                                            <c:if test="${o.team_evaluation == false}">
                                                <% out.print("No"); %>
                                            </c:if>
                                        </td>
                                        <td>${o.criteria_order}</td>
                                        <td>
                                            <form id="idS${o.criteria_id}" action="Criteria?go=updateStatus"
                                                  method="POST">
                                                <input type="hidden" name="criId" value="${o.criteria_id}">
                                                <select class="form-control form-control-user" name="status"
                                                        onchange="submitForm(idS${o.criteria_id})">
                                                    <option ${o.status == 2 ? "selected" : ""} value="2">Deactivate
                                                    </option>
                                                    <option ${o.status == 1 ? "selected" : ""} value="1">Active</option>
                                                </select>
                                            </form>
                                        </td>
                                        <td>
                                            <a class=""
                                               href="CriteriaDetail?go=Update&cid=${o.criteria_id}&Iter=${o.iteration_id}&Sub=${o.subject_id}"><span
                                                    class="material-symbols-outlined">
                                                                edit
                                            </span></a>

                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="paging">

                                <c:forEach begin="1" end="${maxP}" var="i">
                                    <a class="active" href="Criteria?index=${i}">${i}</a>
                                </c:forEach>

                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->


        </div>

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
<jsp:include page="/general/LogOut.jsp"></jsp:include>

<jsp:include page="/general/Footer.jsp"></jsp:include>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        Fnon.Hint.Init({
            zIndex: 9900,
        });
        // Hint
        var message = "${message}";
        var theme = "${theme}";
        var title = "${title}";
        var position = "right-top";
        var animation = "slide-left";
        Fnon.Hint[theme](message, {
            title,
            position,
            animation,
        })
    });
</script>
<script>
    function submitForm(form) {
        swal({
            title: "Are you sure?",
            text: "This form will be submitted",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then(function (isOkay) {
                if (isOkay) {
                    form.submit();
                }
            });
        return false;
    }
</script>

</body>
</html>
