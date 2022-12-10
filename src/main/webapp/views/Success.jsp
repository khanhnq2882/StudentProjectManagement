<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/general/Head.jsp"></jsp:include>

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
      <div class="container">

        <div class="Success_all">
          <a class="Succ" href="${link}">
            <img src="img/cai nay hoi la.png" alt="" ><br>
            <h2>Your change has done successfully!!!</h2>
          </a>
        </div>


        <style>
          img {
            width: 50%;
          }
          .Success_all {
            align-self: center;
            text-align: center;
          }
        </style>




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

<!-- Logout Modal-->
<jsp:include page="/general/LogOut.jsp"></jsp:include>

<!-- Bootstrap core JavaScript-->
<jsp:include page="/general/Footer.jsp"></jsp:include>

</body>

</html>
