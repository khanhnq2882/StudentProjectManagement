<%@ page import="com.management.util.EncodeSring" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/general/Head.jsp"/>

<body id="page-top">

<div id="wrapper">

    <jsp:include page="/general/Sidebar.jsp"></jsp:include>

    <jsp:useBean id="DAOSen" scope="page" class="com.management.dao.DAOSen"/>

    <div id="content-wrapper" class="d-flex flex-column">

        <div id="content">

            <jsp:include page="/general/Header.jsp"></jsp:include>

            <div class="container-fluid">
                <h1>List all Subject</h1>
                <div class="container_head">
                    <div class="link" style="color: white">
                        <a href="Home"> Dashboard </a>
                        <p> / </p>
                        <a href="ClassUser"> List all Subject </a>
                    </div>
                </div>
                <form action="<%=request.getContextPath()%>/SubjectList?action=search" method="post"
                      style="margin-top: 90px; margin-bottom: 25px; ">
                    <div class="d-flex align-items-end">
                        <div class="mr-4" style="width: 350px;">
                            <input class="form-control form-control-user"
                                   name="subjectCode" type="text" value="${subjectCode}"
                                   placeholder="Input a subject/subject code ...">
                        </div>
                        <div class="mr-4">
                            <span>Filter by Author</span>
                            <select class="form-control form-control-user" name="authorId" style="width: auto">
                                <option selected disabled>Select Author</option>
                                <option ${authorId == "" ? "selected" : ""} value="">All</option>
                                <c:forEach var="o" items="${listAuthor}">
                                    <option ${authorId == o.user_id ? "selected" : ""}
                                            value="${o.user_id}">${o.fullname}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <c:if test="${Loged.role_id == 4}">
                            <div class="mr-4">
                                <span>Filter by Status</span>
                                <select class="form-control form-control-user" name="status" style="width: auto">
                                    <option selected disabled>Select status</option>
                                    <option value="" ${status == "" ? "selected" : ""}>All</option>
                                    <option value="1" ${status == 1 ? "selected" : ""}>Active</option>
                                    <option value="2" ${status == 2 ? "selected" : ""}>InActive</option>
                                </select>
                            </div>
                        </c:if>
                        <div class="mr-4">
                            <button type="submit" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                </form>
                <c:if test="${Loged.role_id == 4}">
                    <a class="font-weight-bold text-primary" type="button" data-toggle="modal" data-target="#addModal">Add
                        new subject</a><br>
                </c:if>
                <span class="spn">${countResult} Subject(s) found</span>
                <ul id="slide">
                    <c:forEach var="o" items="${list}">
                        <li>
                            <div class="count box">
                                <a href="ShowAllClass?go=showBySubject&subjectId=${o.subject_id}"
                                   style="color: black"><h5 class="two-lines">
                                    (${o.subject_code}) ${o.subject_name}</h5></a>
                                <span><ion-icon
                                        name="person"></ion-icon> Author: ${DAOSen.getUserById(o.author_id).fullname}</span><br>
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
            </div>

            <%
                String subjectCode = (String) request.getAttribute("subjectCode");
                String authorId = (String) request.getAttribute("authorId");
                String status = (String) request.getAttribute("status");

                StringBuilder paramPaginate = new StringBuilder();

                if (StringUtils.isNotEmpty(subjectCode)) {
                    if (StringUtils.isNotEmpty(paramPaginate)) {
                        paramPaginate.append("&");
                    }
                    paramPaginate.append("subjectCode=" + subjectCode);
                }
                if (StringUtils.isNotEmpty(authorId)) {
                    if (StringUtils.isNotEmpty(paramPaginate)) {
                        paramPaginate.append("&");
                    }
                    paramPaginate.append("authorId=" + authorId);
                }
                if (StringUtils.isNotEmpty(status)) {
                    if (StringUtils.isNotEmpty(paramPaginate)) {
                        paramPaginate.append("&");
                    }
                    paramPaginate.append("status=" + status);
                }

                String encodeStr = EncodeSring.encode(paramPaginate.toString());
                String paramPaginateUrl = StringUtils.isNotEmpty(encodeStr) ? "param=" + encodeStr + "&" : "";
            %>

            <nav aria-label="...">
                <ul class="pagination justify-content-end" style="padding-right: 7rem;">
                    <c:if test="${lastPage<1 || lastPage < indexPage}">
                        <h1>Not found</h1>
                    </c:if>
                    <c:if test="${lastPage >= 1}">
                        <li class="page-item ${indexPage eq 1 ? "disabled" : ""}">
                            <c:if test="${indexPage eq 1}">
                                <span class="page-link">Previous</span>
                            </c:if>
                            <c:if test="${indexPage ne 1}">
                                <a class="page-link" href="<%=request.getContextPath()%>/SubjectList?<%=paramPaginateUrl%>index=${indexPage - 1}">Previous</a>
                            </c:if>
                        </li>

                        <c:forEach begin="1" end="${lastPage}" var="i">
                            <li class="page-item ${i eq indexPage ? "active" : ""}">
                                <c:if test="${i eq indexPage}">
                                    <span class="page-link">${i}<span class="sr-only">(current)</span></span>
                                </c:if>
                                <c:if test="${i ne indexPage}">
                                    <a class="page-link" href="<%=request.getContextPath()%>/SubjectList?<%=paramPaginateUrl%>index=${i}">${i}</a>
                                </c:if>
                            </li>
                        </c:forEach>

                        <li class="page-item ${indexPage eq lastPage ? "disabled" : ""}">
                            <c:if test="${indexPage eq lastPage}">
                                <span class="page-link">Next</span>
                            </c:if>
                            <c:if test="${indexPage ne lastPage}">
                                <a class="page-link" href="<%=request.getContextPath()%>/SubjectList?<%=paramPaginateUrl%>index=${indexPage + 1}">Next</a>
                            </c:if>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>

    </div>

    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
</div>

<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Modal for add-->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <form action="<%=request.getContextPath()%>/SubjectList?add" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    <ul>
                        <li class="mb-3">
                            <input oninput="validationCode()" id="scode" class="form-control form-control-user"
                                   type="text" name="scode" placeholder="Subject Code ..."/>
                            <span id="txtcode" style="color: red"></span>
                        </li>
                        <li class="mb-3">
                            <input oninput="validationName()" id="sname" class="form-control form-control-user"
                                   type="text" name="sname" placeholder="Subject Name ..."/>
                            <span id="txtname" style="color: red"></span>
                        </li>
                        <li class="mb-3">
                            <select onchange="validateAuthor()" id="aname" name="aname"
                                    class="form-control form-control-user">
                                <option selected disabled>Select a option ...</option>
                                <c:forEach var="o" items="${listAuthor}">
                                    <option ${author == o.user_id ? "selected" : ""}
                                            value="${o.user_id}">${o.fullname}</option>
                                </c:forEach>
                            </select>
                            <span id="txtaname" style="color: red"></span>
                        </li>
                    </ul>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <button class="btn btn-primary" type="submit" data-dismiss="modal">Add</button>
                </div>
            </div>
        </form>
    </div>
</div>


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

</body>

</html>
