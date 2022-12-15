

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/general/Head.jsp"/>

<body id="page-top">


<style>
  .container{
    text-align: center;
  }
</style>
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

        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
          <a href="ExcelTest?ClassId=${classid}"
             class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                  class="fas fa-download fa-sm text-white-50"></i>Download list user</a>
        </div>

        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
          <thead>
          <tr>

            <th>Roll Number</th>
            <th>Image</th>
            <th>FullName</th>
            <th>Gender</th>
            <th>Date of Birth</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Status</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="o" items="${listByClass}">
            <tr>
              <td>${o.roll_number}</td>
              <td>
                <img src="${o.avatar_link}" alt="${o.fullname}" style="height: 4%; width: 4%;vertical-align: middle;">

              </td>
              <td>${o.fullname}</td>
              <td>
                <c:if test="${o.gender == 1}">
                  <% out.print("Female"); %>
                </c:if>
                <c:if test="${o.gender == 2}">
                  <% out.print("Male"); %>
                </c:if>
              </td>

              <td>${o.date_of_birth}</td>
              <td>${o.email}</td>
              <td>${o.mobile}</td>
              <td>
                <form action="UserController?go=changeStatusClass" method="POST">
                  <input type="hidden" name="userID" value="${o.user_id}"/>
                  <input type="hidden" name="class_ID" value="${classid}"/>
                  <select name="status" onchange="this.form.submit()">
                    <option ${o.status == 0 ? "selected" : ""} value="0">Deactive</option>
                    <option ${o.status == 1 ? "selected" : ""} value="1">Active</option>
                  </select>
                </form>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
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

<!-- Page level custom scripts -->
<script src="js/demo/chart-area-demo.js"></script>
<script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>
