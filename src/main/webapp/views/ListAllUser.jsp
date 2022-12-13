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
  a.collapse-item.active {
    text-decoration: underline;
    color: blue;
    font-size: large;
  }
  .phanTrang a{
    text-decoration: none;
    cursor: pointer;
    transition: all 0.2s;
    border-radius: 15px;
    padding: 0px 15px;
    background-image: repeating-linear-gradient(117deg, #4e73df70, #eba1e2bd 100px);
  }
  .phanTrang{
    padding: 20px;
    font-size: 20px;
  }
  .EditLink {
    border: none;
    outline: none;
    background: repeating-linear-gradient(45deg, #ff000080, #0000ff85);
    border-radius: 15px;
    padding: 5px 15px;
    color: white;
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
      <div class="container-fluid">
        <div class="card shadow mb-4">
          <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a style="font-weight: bold" class="navbar-brand" href="UserController">List All Users:</a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav mr-auto">
                <!--                                            <li class="nav-item active">
                                                                <a class="nav-link">Role <span class="sr-only">(current)</span></a>
                                                            </li>-->
                <li class="nav-item">
                  <form action="UserController" method="get">
                    <input type="hidden" name="go" value="searchRole">
                    <select class="form-control form-control-user" name="ROLEid" onchange="this.form.submit()">
                      <option ${role == 5 ? "selected" : ""} value="5">All role</option>
                      <option ${role == 1 ? "selected" : ""} value="1">Student</option>
                      <option ${role == 2 ? "selected" : ""} value="2">Trainer</option>
                      <option ${role == 3 ? "selected" : ""} value="3">Author</option>
                      <option ${role == 4 ? "selected" : ""} value="4">Admin</option>
                    </select>
                  </form>
                </li>
                <!--                                        <li class="nav-item active">
                                                            <a class="nav-link">Status <span class="sr-only">(current)</span></a>
                                                        </li>-->
                <li class="nav-item">
                  <form action="UserController" method="get">
                    <input type="hidden" name="go" value="searchByStatus">
                    <select class="form-control form-control-user" name="statu" onchange="this.form.submit()">
                      <option ${status == 2 ? "selected" : ""} value="2">All status</option>
                      <option ${status == 1 ? "selected" : ""} value="1">Active</option>
                      <option ${status == 0 ? "selected" : ""} value="0">Deactive</option>
                    </select>
                  </form>
                </li>
                <li class="nav-item active">
                  <a class="nav-link">Sort By Name <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                  <form action="UserController" method="get">
                    <input type="hidden" name="go" value="sortFullname">
                    <select class="form-control form-control-user" name="sort" onchange="this.form.submit()">
                      <option ${sort == 0 ? "selected" : ""} value="0">None</option>
                      <option ${sort == 1 ? "selected" : ""} value="1">Increase</option>
                      <option ${sort == 2 ? "selected" : ""} value="2">Decrease</option>
                    </select>
                  </form>
                </li>
              </ul>
              <form action="UserController" class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" value="${search}" name="nameAndRoll" placeholder="Search" aria-label="Search">
                <!--<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>-->
                <input class="btn btn-outline-success my-2 my-sm-0" name="submitSearch" type="submit" value="Search" />
              </form>
            </div>
          </nav>
          <p style="text-align: center; font-weight: bold;">Number of users: ${countUser}</p><a style="margin-left: 50px; display: block; width: 110px;" href="UserController?go=addNewUser">Add new User</a>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                  <th>Roll Number</th>
                  <th>FullName</th>
                  <th>Gender</th>
                  <th>Date of Birth</th>
                  <th>Status</th>
                  <th>Role</th>
                  <th>Detail</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="o" items="${listAll}">
                  <tr>
                    <td>${o.roll_number}</td>
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
                    <td>
                      <form id="idS${o.user_id}" action="UserController?go=changeStatus" method="POST">
                        <input type="hidden" name="userID" value="${o.user_id}"/>
                        <select class="form-control form-control-user" name="status" onchange="submitForm(idS${o.user_id})">
                          <option ${o.status == 0 ? "selected" : ""} value="0">Deactivate</option>
                          <option ${o.status == 1 ? "selected" : ""} value="1">Active</option>
                        </select>
                      </form>
                    </td>
                    <td>
                      <form id="id${o.user_id}" action="UserController?go=changeRole" method="POST">
                        <input type="hidden" name="userID" value="${o.user_id}"/>
                        <select class="form-control form-control-user" name="roleID" onchange="submitForm(id${o.user_id})">
                          <option ${o.role_id == 4 ? "selected" : ""} value="4">Admin</option>
                          <option ${o.role_id == 3 ? "selected" : ""} value="3">Author</option>
                          <option ${o.role_id == 2 ? "selected" : ""} value="2">Trainer</option>
                          <option ${o.role_id == 1 ? "selected" : ""} value="1">Student</option>
                        </select>
                      </form>
                    </td>

                    <td><a href="UserController?go=editUser&userID=${o.user_id}">
                                                            <span class="material-symbols-outlined">
                                                                edit
                                                            </span>
                    </a></td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
            <div >
              <a class="btn btn-primary" onclick="${page < numberPage ? "do_back()" : ""}" class="${page == k ? "collapse-item active":""}">Previous</a>
              <a class="btn btn-primary" style="text-decoration: none;" class="collapse-item active">${page} of ${numberPage}</a>
              <a class="btn btn-primary" onclick="${page < numberPage ? "do_next()" : ""}" class="${page == k ? "collapse-item active":""}">Next</a>
            </div>
          </div>
          <!-- /.container-fluid -->
        </div>
      </div>
    </div>
  </div>
</div>
<!-- End of Main Content -->



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

<script src="js/sweetalert.min.js"></script>

<script src="js/SenJS.js"></script>
<script src="js/fnon.min.js"></script>

<!-- End of Content Wrapper -->

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

<script src="js/sweetalert.min.js"></script>

<script>
  function do_back() {
    var url = new URL(window.location.href);
    url.searchParams.set("page", '${page-1}');
    document.location.search = url.search;
  }
</script>

<script>
  function do_next() {
    var url = new URL(window.location.href);
    url.searchParams.set("page", '${page+1}');
    document.location.search = url.search;
  }
</script>
</body>

</html>
