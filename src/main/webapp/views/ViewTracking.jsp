<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="/general/Head.jsp"></jsp:include>

<body id="page-top">

<div id="slide"></div>
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
                <div class="container_head">
                    <div class="link" style="color: white">
                        <a href="Home"> Dashboard </a>
                        <p> / </p>
                        <a href="TrackingList"> Tracking </a>
                    </div>
                    <h4 class="h4 two-lines">${OneClass.subject_code} - ${OneClass.subject_name}</h4>
                    <span class="span">Class: ${OneClass.class_code}</span>
                </div>
                <div style="margin-top:160px"></div>
                <ul class="spbw">
                    <li><a type="submit" href="AddTracking?ClassId=${OneClass.class_id}"
                           class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"/>Add new Tracking</a>
                    <li>
                        <form style="display: flex !important;" action="TrackingList" method="POST" id="Form">
                            <select id="SearchBy" class="form-control form-control-user" name="class_id">
                                <c:forEach var="o" items="${classes}">
                                    <option ${o.classCode == OneClass.class_code ? "selected" : ""}
                                            value="${o.id}">${o.classCode}</option>
                                </c:forEach>
                            </select>
                            <form style="display: flex !important;" action="TrackingList" method="POST" id="Form">
                                <select id="SearchBy" class="form-control form-control-user" name="team_id">
                                    <option value="">All Team</option>
                                    <c:forEach var="o" items="${team}">
                                        <option ${o.team_id == team_id ? "selected" : ""}
                                                value="${o.team_id}">${o.team_name}</option>
                                    </c:forEach>
                                </select>
                                <select id="SearchBy" class="form-control form-control-user" name="func_id">
                                    <option value="">All Function</option>
                                    <c:forEach var="o" items="${function}">
                                        <option ${o.function_id == func_id ? "selected" : ""}
                                                value="${o.function_id}">${o.function_name}</option>
                                    </c:forEach>
                                </select>
                                <input placeholder="Text Something" name="searchTxT" id="searchTxT" value="${searchTxT}"
                                       class="form-control form-control-user">
                                <input id="submit" type="submit" name="Search" value="Search" style="
                                               background: #0073ca;
                                               border-radius: .35rem;
                                               "/>
                                <div class="form-control" style="
                                             display: flex;
                                             width: auto;
                                             justify-content: center;
                                             align-items: center;" onclick="DaiRa()">
                                    <ion-icon name="arrow-dropdown-circle"></ion-icon>
                                </div>
                                <ul class="daira" style="
                                            position: absolute;
                                            display: flex;
                                            flex-direction: column;
                                            z-index: 100;
                                            align-items: flex-start;
                                            background: white;
                                            border-radius: 10px;
                                            height: 0;
                                            right: 0;
                                            overflow: hidden;
                                            transition: height 0.5s ease-in-out;
                                            margin-top: 37px;
                                            ">
                                    <style>
                                        .daira li {
                                            height: 5px;
                                        }

                                        .daira.active {
                                            padding-bottom: 15px;
                                            height: 150px !important;
                                            border: 1px solid #d1d3e2;
                                        }
                                    </style>
                                    <li><input ${SearchBy.equals("c.milestone_name") ? "checked" : ""} type="radio"
                                                                                                       name="SearchBy"
                                                                                                       value="c.milestone_name"/>Milestone
                                    </li>
                                    <li><input ${SearchBy.equals("e.fullname") ? "checked" : ""} type="radio"
                                                                                                 name="SearchBy"
                                                                                                 value="e.fullname"/>Assigner
                                    </li>
                                    <li><input ${SearchBy.equals("f.fullname") ? "checked" : ""} type="radio"
                                                                                                 name="SearchBy"
                                                                                                 value="f.fullname"/>Assignee
                                    </li>
                                </ul>
                                <input id="pagees" type="hidden" name="page" value="${page}"/>
                                <input id="order" type="hidden" name="order" value=""/>

                            </form>
                    </li>
                </ul>
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        ${count} Tracking(s) found
                    </div>
                    <div class="card-body">

                        <div class="table-responsive">
                            <table class="table table-bordered sentable" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th id=" order by b.team_name" onclick="OrderBy(this)">Team
                                        <ion-icon name="arrow-dropdown"></ion-icon>
                                    </th>
                                    <th id=" order by c.milestone_name" onclick="OrderBymilestone_name(this)">MileStone
                                        <ion-icon name="arrow-dropdown"></ion-icon>
                                    </th>
                                    <th id=" order by d.function_name" onclick="OrderBy(this)">Function
                                        <ion-icon name="arrow-dropdown"></ion-icon>
                                    </th>
                                    <th id=" order by e.fullname" onclick="OrderBy(this)">Assigner
                                        <ion-icon name="arrow-dropdown"></ion-icon>
                                    </th>
                                    <th id=" order by f.fullname" onclick="OrderBy(this)">Assignee
                                        <ion-icon name="arrow-dropdown"></ion-icon>
                                    </th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Team</th>
                                    <th>MileStone</th>
                                    <th>Function</th>
                                    <th>Assigner</th>
                                    <th>Assignee</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach var="o" items="${list}">
                                <tr>
                                    <td>${o.team_name}</td>
                                    <td>${o.milestone_name}</td>
                                    <td>${o.function_name}</td>
                                    <td>${o.assigner_name}</td>
                                    <td>${o.assignee_name}</td>
                                    <td>
                                        <select onchange="Confirm(this)" name="${o.tracking_id}"
                                                class="form-control form-control-user statuses">
                                            <option ${o.status == 1 ? "selected" : ""} value="1">Planed</option>
                                            <option ${o.status == 2 ? "selected" : ""} value="2">Analysed</option>
                                            <option ${o.status == 3 ? "selected" : ""} value="3">Designed</option>
                                            <option ${o.status == 4 ? "selected" : ""} value="4">Coded</option>
                                            <option ${o.status == 5 ? "selected" : ""} value="5">Integrated</option>
                                            <option ${o.status == 6 ? "selected" : ""} value="6">Submitted</option>
                                            <option ${o.status == 7 ? "selected" : ""} value="7">Evaluated</option>
                                            <option ${o.status == 8 ? "selected" : ""} value="8">Rejected</option>
                                        </select>
                                    </td>
                                    <c:if test="${o.status < 7}">
                                        <td><a href="UpdateTracking?id=${o.tracking_id}&ClassId=${OneClass.class_id}">
                                            <ion-icon name="create" md="md-create"></ion-icon>
                                        </a></td>
                                    </c:if>
                                    <c:if test="${o.status >= 7}">
                                        <td><a>
                                            <ion-icon name="remove"></ion-icon>
                                        </a></td>
                                    </c:if>
                                </tr>
                                </tbody>
                                </c:forEach>
                            </table>
                            <div class="paging">
                                <c:if test="${pages != 1}">
                                    <a href="#" onclick="CongPage()"> << </a>
                                </c:if>
                                <p id="showpage" onclick="changetype()">${pages} of ${max}</p>
                                <input name="page" oninput="changePage()" id="page" type="number" style="display: none"
                                       max="" min="1" value="${pages}"/>
                                <input id="gobtn" onclick="SubmitForm()" type="submit" style="display: none"
                                       value="Go"/>
                                <c:if test="${pages < max}">
                                    <a href="#" onclick="TruPage()"> >> </a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- /.container-fluid -->
                <div id="confirm"></div>
            </div>

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

    <script>
        function OrderBy(a) {
            document.querySelector("#order").value = a.id;
            document.querySelector("#submit").click();
        }

        function OrderBymilestone_name(a) {
            document.querySelector("#order").value = a.id;
            document.querySelector("#pagees").value = ${pages};
            document.querySelector("#submit").click();
        }

        function changePage() {
            document.querySelector("#pagees").value = document.querySelector("#page").value;
        }

        function CongPage() {
            document.querySelector("#pagees").value = ${pages - 1};
            document.querySelector("#submit").click();
        }

        function TruPage() {
            document.querySelector("#pagees").value = ${pages + 1};
            document.querySelector("#submit").click();
        }

        function SubmitForm() {
            document.querySelector("#Form").submit;
            document.querySelector("#submit").click();
        }

        function changetype() {
            document.querySelector("#page").style.display = "block";
            document.querySelector("#gobtn").style.display = "block";
            document.querySelector("#showpage").style.display = "none";
        }

        function DaiRa() {
            var likeda = document.querySelector(".daira");
            likeda.classList.toggle("active");
        }

        function Confirm(id) {
            var status = id.value;
            var id = id.name;
            var idConfirm = "UpdateTracking";
            $.ajax({
                url: "/g1/Confirm",
                type: "get",
                data: {
                    idConfirm: idConfirm,
                    status: status,
                    id: id
                },
                success: function (data) {
                    var confirm = document.getElementById("confirm");
                    confirm.innerHTML = data;
                    $("#confirm1").modal("show");
                },
                error: function (xhr) {
                }
            });
        }

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

</body>

</html>
