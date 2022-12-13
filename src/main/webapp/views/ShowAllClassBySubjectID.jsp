<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
      <div class="container-fluid">

        <div class="card shadow mb-4">
          <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">All class by subject</h6>
          </div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                  <th>Class code</th>
                  <th>Trainer</th>
                  <th>subject name</th>
                  <th>class year</th>
                  <th>class term</th>
                  <th>block 5 ?</th>
                  <th>Status</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                  <th>Class code</th>
                  <th>Trainer</th>
                  <th>subject name</th>
                  <th>class year</th>
                  <th>class term</th>
                  <th>block 5 ?</th>
                  <th>Status</th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach var="o" items="${vect}">
                  <tr>
                    <td><a href="ClassUser4Admin?class_id=${o.id}"> ${o.classCode}</a></td>
                    <td>  ${o.trainerId}  </td>
                    <td>${o.subjectId}</td>
                    <td>${o.classYear}</td>
                    <td>${o.classTerm}</td>
                    <td>${o.block5Class == 1 ? "True":"False"}</td>
                    <td>${o.status == 1 ? "Ongoing" : "Ended"}</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
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
<jsp:include page="/general/LogOut.jsp"></jsp:include>

<!-- Bootstrap core JavaScript-->
<jsp:include page="/general/Footer.jsp"></jsp:include>

</body>

</html>