<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="../general/Head.jsp"></jsp:include>

<body id="page-top">

<div id="slide"></div>
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
            <a href="#"> ${oneClass.subject_code} </a>
            <p> / </p>
            <a href="ClassUser4Admin?class_id=${oneClass.class_id}"> ${oneClass.class_code}</a>
          </div>
          <h4 class="h4 two-lines">${oneClass.subject_code} - ${oneClass.subject_name}</h4>
          <span class="span">Class: ${oneClass.class_code}</span>
        </div>
        <div style="margin-top: 200px"></div>

        <c:if test="${Loged.role_id == 4}">
          <ul class="spbw">
            <c:if test="${count < 30}">
              <li><input type="submit" onclick="AddStudent()" class="addStudentinClass" value="Add new Students" /></li>
            </c:if>
            <li>

              <a type="submit" href="uploadExcel?go=uploadE&class_id=${class_id}" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"/>Upload</a>
              <a href="ExcelTest?ClassId=${oneClass.class_id}"
                 class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                      class="fas fa-download fa-sm text-white-50"></i>Download list user</a>
              <a href="\g1\Template_G1.xlsx"
                 class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                      class="fas fa-download fa-sm text-white-50"></i>Download template</a>
            </li>
          </ul>
        </c:if>
        <span onclick="Show()" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Filter</span>
        <ul id="filtera" style="justify-content: flex-end;">
          <li>Search by
            <select id="SearchBy" onchange="SearchUserClass()" class="form-control form-control-user" name="">
              <option value="c.roll_number">Roll Number</option>
              <option value="c.fullname">Name</option>
              <option value="c.email">Email</option>
              <option value="c.mobile">Mobile</option>
              <option value="g.team_name">Team</option>
            </select></li>
          <li>Text Something<input id="searchTxT" oninput="SearchUserClass()" class="form-control form-control-user"></li>
        </ul>
        <div class="card shadow mb-4">
          <div class="card-header py-3">
            <span>Lecturer: <a href="#"> ${oneClass.trainer_name}</a></span>
            <span>${count} ${count > 0 ? "Students in this class" : "Student in this class"}</span>
          </div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered sentable" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                  <th>Stt</th>
                  <th>Avatar</th>
                  <th>Roll Number</th>
                  <th>Name</th>
                  <th>Gender</th>
                  <th>Date Of Birth</th>
                  <th>Team</th>
                  <th>Leader</th>
                  <c:if test="${Loged.role_id == 4}">
                    <th colspan="2">Action</th>
                  </c:if>
                </tr>
                </thead>
                <tfoot>
                <tr>
                  <th>Stt</th>
                  <th>Avatar</th>
                  <th>Roll Number</th>
                  <th>Name</th>
                  <th>Gender</th>
                  <th>Date Of Birth</th>
                  <th>Team</th>
                  <th>Leader</th>
                  <c:if test="${Loged.role_id == 4}">
                    <th colspan="2">Action</th>
                  </c:if>
                </tr>
                </tfoot>
                <tbody>
                <%
                  int i = 1;
                %>
                <c:forEach var="o" items="${list}">
                  <tr>
                    <td><%=i++ %></td>
                    <td><img src="uploads/${o.avatar_link}" alt="avatar" width="100" height="100"></td>
                    <td>${o.roll_number}</td>
                    <td>${o.fullname}</td>
                    <td>${o.gender == 1 ? "Male" : "Female"}</td>
                    <td>${o.date_of_birth}</td>
                    <th>${o.teamName}</th>
                    <th>${o.teamLead == 1 ? "Lead" : "Member"}</th>
                    <c:if test="${Loged.role_id == 4}">
                      <td>
                        <form action="StudentInClassDetail" method="POST">
                          <input type="hidden" name="user" value="${o.user_id}"/>
                          <input type="hidden" name="class" value="${oneClass.class_id}"/>
                          <input type="submit" class="suubmit" value="Detail"/>
                        </form>
                      </td><td>
                      <input type="hidden" id="getclasscode" value="${oneClass.class_code}"/>
                      <input type="hidden" id="getclassuserid" value="${o.idclassuser}"/>
                      <input type="submit" class="suubmit" value="Kick out" onclick="Confirm(${o.idclassuser})"/>
                    </td>
                    </c:if>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>

        <!-- /.container-fluid -->
        <div id="confirm"></div>
      </div>
      <!-- End of Main Content -->

    </div>
    <!-- End of Content Wrapper -->
    <input type="hidden" name="" id="class_id" value="${class_id}" />
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
  <script>
    function changetype() {
      document.querySelector("#page").style.display = "block";
      document.querySelector("#gobtn").style.display = "block";
      document.querySelector("#showpage").style.display = "none";
    }
    function AddStudent() {
      var Add = "Add";
      $.ajax({
        url: "/g1/ClassUser4Admin",
        type: "get",
        data: {
          Add: Add
        },
        success: function (data) {
          var slide = document.getElementById("slide");
          slide.innerHTML = data;
        },
        error: function (xhr) {
        }
      });
    }
    function outAdd() {
      var Add = "OutAdd";
      $.ajax({
        url: "/g1/ClassUser4Admin",
        type: "get",
        data: {
          Add: Add
        },
        success: function (data) {
          var slide = document.getElementById("slide");
          slide.innerHTML = data;
        },
        error: function (xhr) {
        }
      });
    }
    function filterAdd() {
      const SearchBy = document.querySelector(".SearchBy").value;
      const searchTxT = document.querySelector(".searchTxT").value;
      const SearchMajor = document.querySelector(".SearchMajor").value;
      const page = document.querySelector("#page").value;
      var Add = "Add";
      $.ajax({
        url: "/g1/ClassUser4Admin",
        type: "get",
        data: {
          Add: Add,
          SearchBy: SearchBy,
          searchTxT: searchTxT,
          SearchMajor: SearchMajor,
          page: page
        },
        success: function (data) {
          var slide = document.getElementById("slide");
          slide.innerHTML = data;
        },
        error: function (xhr) {
        }
      });
    }
    function IndexAdd(index) {
      const SearchBy = document.querySelector(".SearchBy").value;
      const searchTxT = document.querySelector(".searchTxT").value;
      const SearchMajor = document.querySelector(".SearchMajor").value;
      const page = index;
      var Add = "Add";
      $.ajax({
        url: "/g1/ClassUser4Admin",
        type: "get",
        data: {
          Add: Add,
          SearchBy: SearchBy,
          searchTxT: searchTxT,
          SearchMajor: SearchMajor,
          page: page
        },
        success: function (data) {
          var slide = document.getElementById("slide");
          slide.innerHTML = data;
        },
        error: function (xhr) {
        }
      });
    }
    function SearchUserClass() {
      const SearchBy = document.querySelector("#SearchBy").value;
      const searchTxT = document.querySelector("#searchTxT").value;
      var class_id = document.getElementById("class_id").value;
      var Add = "SearchUserClass";
      $.ajax({
        url: "/g1/ClassUser4Admin",
        type: "get",
        data: {
          Add: Add,
          SearchBya: SearchBy,
          class_id: class_id,
          searchTxTa: searchTxT
        },
        success: function (data) {
          var dataTable = document.getElementById("dataTable");
          dataTable.innerHTML = data;
        },
        error: function (xhr) {
        }
      });
    }
  </script>

  <script src="js/SenJS.js"></script>
  <script src="js/fnon.min.js"></script>
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
    function Show() {
      document.getElementById("filtera").classList.toggle("active");
    }
    function Confirm(sub_id) {
      var id = sub_id;
      var class_code = document.getElementById("getclasscode").value;
      var class_id = document.getElementById("class_id").value;
      var idConfirm = "DeleteClassUser";
      $.ajax({
        url: "/g1/Confirm",
        type: "get",
        data: {
          classuser_id: id,
          idConfirm: idConfirm,
          class_id: class_id,
          class_code: class_code
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
    function ConfirmAdd(sub_id) {
      var id = sub_id;
      var class_code = document.getElementById("getclasscode").value;
      var class_id = document.getElementById("class_id").value;
      var idConfirm = "AddClassUser";
      $.ajax({
        url: "/g1/Confirm",
        type: "get",
        data: {
          user_id: id,
          idConfirm: idConfirm,
          class_id: class_id,
          class_code: class_code
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
  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/chart-area-demo.js"></script>
  <script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>