<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/general/Head.jsp"/>

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
                <h1>List all Subject</h1>
                <div class="container_head">
                    <div class="link" style="color: white">
                        <a href="Home"> Dashboard </a>
                        <p> / </p>
                        <a href="ClassUser"> List all Subject </a>
                    </div>
                </div>
                <span style="margin-top: 100px">${arr}</span>
                <input style="margin-top: 4rem" oninput="searchByName(this)" class="form-control form-control-user"
                       name="txt" type="text" value="${txtSearch}"
                       placeholder="Search mã môn hoặc tên môn học ở đây" id="hintForm">
                <ul style="justify-content: space-between;">
                    <ul style="justify-content: flex-start;">
                        <li>
                            <span>Order by</span>
                            <select id="orderne" class="form-control form-control-user" style="width: auto"
                                    onchange="order()">
                                <option value="subject_code">Subject Code Name</option>
                                <option value="subject_name">Subject Name</option>
                                <option value="subject_id">Time Added</option>
                            </select>
                        </li>
                        <li>
                            <span>Order Type</span>
                            <select id="ordernea" class="form-control form-control-user" style="width: auto"
                                    onchange="order()">
                                <option value="">Increase</option>
                                <option value="desc">Decrease</option>
                            </select>
                        </li>
                    </ul>
                    <li class="a adddbtn">
                        <c:if test="${Loged.role_id == 4}">
                            <a>
                                <ion-icon name="add-circle-outline"></ion-icon>
                                Add new Subject</a><br>
                        </c:if>
                    </li>
                </ul>
                <div class="add ${active}">
                    <form action="SubjectList" method="POST">
                        <ul>
                            <li>
                                <input oninput="validationCode()" id="scode" class="form-control form-control-user"
                                       type="text" name="code" value="${code}" placeholder="Subject Code"/>
                                <span id="txtcode" style="color: red"></span>
                            </li>
                            <li>
                                <input oninput="validationName()" id="sname" class="form-control form-control-user"
                                       type="text" name="name" value="${name}" placeholder="Subject Name"/>
                                <span id="txtname" style="color: red"></span>
                            </li>
                            <li>
                                <select onchange="validateAuthor()" id="aname" name="author"
                                        class="form-control form-control-user">
                                    <option value="name">Author's Name</option>
                                    <c:forEach var="o" items="${listU}">
                                        <option ${author == o.user_id ? "selected" : ""}
                                                value="${o.user_id}">${o.fullname}</option>
                                    </c:forEach>
                                </select>
                                <span id="txtaname" style="color: red"></span>
                            </li>
                            <input type="submit" value="Add" name="add" style="margin-left: 20px"/>
                        </ul>

                    </form>
                </div>
                <c:if test="${Loged.role_id == 4}">
                    <input id="het" type="hidden" value="">
                    <ul id="slide">
                        <span class="spn">${count1} Subject(s) found</span>
                        <input oninput="searchByName(this)" class="search form-control form-control-user" name="txt"
                               type="hidden" value=""
                               placeholder="Search mã môn hoặc tên môn học ở đây">
                        <c:forEach var="o" items="${list}">
                            <li>
                                <div class="count box">
                                    <a href="ShowAllClass?go=showBySubject&subjectId=${o.subject_id}"
                                       style="color: black"><h5 class="two-lines">
                                        (${o.subject_code}) ${o.subject_name}</h5></a>
                                    <span><ion-icon name="person"></ion-icon> Author: ${o.author_name}</span><br>
                                    <span> <c:if
                                            test="${o.status == 1}"></c:if> Status: ${o.status == 1 ? "Active" : "Not Active"}</span><br>
                                    <div class="aa">
                                        <a class="a" style="margin-right: 15px" href="UpdateSubject?id=${o.subject_id}">
                                            <ion-icon name="refresh"></ion-icon>
                                            Update</a>
                                        <a class="a" href="ShowAllClass?go=showBySubject&subjectId=${o.subject_id}">Go
                                            to your course
                                            <ion-icon style="margin-left: 3px" name="arrow-forward"></ion-icon>
                                        </a>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${Loged.role_id != 4}">
                    <input id="het" type="hidden" value="">
                    <ul id="slide">
                        <span class="spn">${count} Subject(s) found</span>
                        <input oninput="searchByName(this)" class="search form-control form-control-user" name="txt"
                               type="hidden" value=""
                               placeholder="Search mã môn hoặc tên môn học ở đây">
                        <c:forEach var="o" items="${list2}">
                            <li>
                                <div class="count box">
                                    <a href="#" style="color: black"><h5 class="two-lines">
                                        (${o.subject_code}) ${o.subject_name}</h5></a>
                                    <span><ion-icon name="person"></ion-icon> Author: ${o.author_name}</span><br>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
            <!-- /.container-fluid -->
            <div id="confirm"></div>
        </div>
        <!-- End of Main Content -->
        <input type="text" name="" readonly="" value="" class="forScrollDownFunc"/>
        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2021</span>
                </div>
            </div>
        </footer>

        <!-- End of Footer -->
    </div>
    <!-- End of Content Wrapper -->


    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
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
    function Confirm(sub_id) {
        let id = sub_id;
        var idConfirm = "test";
        $.ajax({
            url: "/g1/Confirm",
            type: "get",
            data: {
                subject_id: id,
                idConfirm: idConfirm
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
</script>

</body>

</html>
