<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/general/Head.jsp"/>

<body id="page-top">
<div id="wrapper">

    <jsp:include page="/general/Sidebar.jsp"></jsp:include>

    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">

            <jsp:include page="/general/Header.jsp"></jsp:include>

            <c:if test="${Loged.role_id == 4}">
                <div class="container-fluid">
                    <jsp:include page="/general/HomeForAd.jsp"></jsp:include>
                </div>
            </c:if>
            <c:if test="${Loged.role_id == 1}">
                <div class="container-fluid">
                    <jsp:include page="/general/HomeForStu.jsp"></jsp:include>
                    <div class="row">
                        <c:forEach var="o" items="${vectC}">
                            <div class="col-xl-3 col-md-6 mb-4">
                                <a href="ClassTrainer?go=detailClass&cid=${o.id}">
                                    <div class="card border-left-primary shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">(${o.classCode})
                                                        - ${o.subjectId}</div>
                                                    <div class="h6 mb-0 font-weight-bold text-gray-800">
                                                        Trainer: ${o.trainerId}
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
            <c:if test="${Loged.role_id == 2}">
                <div class="container-fluid">
                    <jsp:include page="/general/HomeForTrai.jsp"></jsp:include>
                </div>
            </c:if>
            <c:if test="${Loged.role_id == 3}">
                <div class="container-fluid">
                    <jsp:include page="/general/HomeForAuthor.jsp"></jsp:include>
                </div>
            </c:if>
        </div>
    </div>
</div>
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<jsp:include page="/general/LogOut.jsp"></jsp:include>

<jsp:include page="/general/Footer.jsp"/>

</body>
</html>