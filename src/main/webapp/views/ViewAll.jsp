<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/general/Head.jsp"/>

<body id="page-top">


<!-- Page Wrapper -->
<div id="wrapper">

  <!-- Sidebar -->
  <jsp:include page="../general/Sidebar.jsp"></jsp:include>
  <!-- End of Sidebar -->

  <!-- Content Wrapper -->
  <div id="content-wrapper" class="d-flex flex-column">

    <!-- Main Content -->
    <div id="content">

      <!-- Topbar -->
      <jsp:include page="../general/Header.jsp"></jsp:include>
      <!-- End of Topbar -->

      <!-- Begin Page Content -->
      <div class="container">
        <div class="container_head">
          <div class="link" style="color: white">
            <a href="Home"> Dashboard </a>
            <p> / </p>
            <a href="ClassUser"> Class User </a>
          </div>
          <h4 class="h4 two-lines">List All Subject you have joined</h4>
        </div>
        <ul id="slide" style="margin-top: 7rem">
          <span class="spn">${count} Subject(s) found</span>
          <input oninput="searchByName(this)" class="search form-control form-control-user" name="txt" type="hidden" value=""
                 placeholder="Search mã môn hoặc tên môn học ở đây">
          <c:forEach var="o" items="${listCU}">
            <li>
              <div class="count box" style="height: 160px">
                <a href="ClassUserDetail?classid=${o.class_id}" style="color: black"><h5 class="two-lines">(${o.subject_code}) ${o.subject_name}</h5></a>
                <span><ion-icon name="briefcase"></ion-icon> Class:</span><a href="#" > ${o.class_code}</a><br>
                <span><ion-icon name="person"></ion-icon> Lecturer:<a href="#" > ${o.trainer_name}</a></span><br>
                <div class="aa">
                  <a class="a" href="ClassUserDetail?classid=${o.class_id}">Go to your course  <ion-icon style="margin-left: 3px" name="arrow-forward"></ion-icon></a>
                </div>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
      <!-- /.container-fluid -->

    </div>
    <!-- End of Main Content -->


  </div>
  <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
  <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<jsp:include page="../general/LogOut.jsp"></jsp:include>
<jsp:include page="/general/Footer.jsp"/>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/chart.js/Chart.min.js"></script>
<script src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons.js"></script>
<!-- Page level custom scripts -->
<script src="js/demo/chart-area-demo.js"></script>
<script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>
