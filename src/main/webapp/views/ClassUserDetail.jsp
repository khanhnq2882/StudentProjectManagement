
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="../general/Head.jsp"></jsp:include>


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
      <div class="container-fluid">
        <div class="container_head">
          <div class="link" style="color: white">
            <a href="Home"> Dashboard </a>
            <p> / </p>
            <a href="ClassUser"> Class User </a>
            <p> / </p>
            <a href="ClassUserDetail?classid=${one.class_id}">${one.subject_name} </a>
          </div>
          <h4 class="h4 two-lines">${one.subject_name}</h4>
          <span class="span">Course code: ${one.subject_code}</span>
        </div>
        <div class="container_body">
          <div class="container_bodyLeft">
            <ul style="display: flex; justify-content: space-between;">
              <li style="padding-bottom: 10px">Iteration</li>
              <ul>
                <li><select class="search form-control form-control-user" name="">
                  <option>Order</option>
                  <option></option>
                </select></li>
                <li><input class="search form-control form-control-user" placeholder="Search Iteration name" type="text" name="" value="" /></li>
              </ul>
            </ul>
            <c:forEach items="${iter}" var="o">
              <ul class="iter">
                <ul>
                  <c:if test="${o.iter_status == 0}">
                    <span style="background: #807676f7">Not Active</span>
                  </c:if>
                  <c:if test="${o.iter_status == 1}">
                    <span style="background: #cccc00">Upcoming</span>
                  </c:if>
                  <c:if test="${o.iter_status == 2}">
                    <span style="background: #0078D4">Processing</span>
                  </c:if>
                  <c:if test="${o.iter_status == 3}">
                    <span style="background: green">Completed</span>
                  </c:if>
                  <li class="iterName two-lines">${o.iteration_name}</li>
                </ul>
                <li>${o.duration}</li>
              </ul>
            </c:forEach>
          </div>
          <div class="container_bodyRirght">
            Class information
            <div>
              <li>Class: <a href="ClassUser4Admin?class_id=${one.class_id}"> ${one.class_code}</a></li>
              <li>Class Year: <a href="#"> ${one.class_year}</a></li>
              <li>Lecturer: <br>
                <a class="otheracc" href="#"><img class="img-profile rounded-circle"
                                                  src="uploads/vts-2021-12-13_17h33_18.png">
                  ${one.trainer_name}</a>
              </li>
              <li>Email: <span style="color: black"> ${one.trainer_email}</span> </li>
              <li>Author: <span style="color: black"> ${one.author_name}</span> </li>
            </div>
          </div>
        </div>
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
<jsp:include page="../views/LogOut.jsp"></jsp:include>
<jsp:include page="../general/Footer.jsp"></jsp:include>


<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/chart-area-demo.js"></script>
<script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>
