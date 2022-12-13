<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <c:set var="typeValue" value="${sessionScope.typeValue}"></c:set>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Group 1 - List Setting</title>

  <!-- Custom fonts for this template -->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link
          href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">
  <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">

  <!-- Custom styles for this page -->
  <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
  <link href="css/CaiNayCuaThanh.css" rel="stylesheet">

</head>
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

        <!-- Page Heading -->


        <!-- DataTales Example -->
        <div class="card shadow mb-4">
          <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Setting Table</h6>
          </div>

          <div>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
              <form style="display: flex;" action="SearchSet" method="POST">
                <label for="typeId"></label>
                <select class="form-control form-control-user" name="typeId" id="typeId">
                  <option value="all">All setting types</option>
                  <c:forEach var="o" items="${typeList}">
                    <option value="${o.type_id}" ${ID1==o.type_id ? "selected" : ""}>${typeValue.get(o.type_id - 1)}</option>
                  </c:forEach>

                </select>
                <label for="status"></label>
                <select class="form-control form-control-user" name="status" id="status">
                  <option value="all" ${sta1.equals("all") ? "selected" : ""}>All statuses</option>
                  <option value="1" ${sta1 == 1 ? "selected" : ""}>Activate</option>
                  <option value="2" ${sta1 == 2 ? "selected" : ""}>Deactivate</option>
                </select>
                <input class="form-control mr-sm-2" type="text" name="SearchName" placeholder="Enter setting title">
                <input type="submit" value="Search" name="submit" class="LinkHere">

              </form>
            </nav>

            <a class="btn btn-outline-primary" href="SettingListServlet?go=addSetting">Add Setting</a>
            <a class="btn btn-outline-success" href="SettingListServlet?go=syncLabel">Sync Label from GitLab</a>
          </div>


          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered"  width="100%" cellspacing="0">
                <thead>
                <tr>
                  <th>Setting Type</th>
                  <th>Title</th>
                  <th>Value</th>
                  <th>Order</th>
                  <th>Status</th>
                  <th>Action</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="o" items="${SettingList}">
                  <tr>
                    <td>${typeValue.get(o.type_id - 1)}</td>
                    <td>${o.setting_title}</td>
                    <td>${o.setting_value}</td>
                    <td>${o.display_order}</td>
                    <td>
                      <form id="idS${o.setting_id}" action="SettingListServlet?go=updateStatus" method="Post">
                        <input type="hidden" name="settingId" value="${o.setting_id}">
                        <select class="form-control form-control-user id" id ="status" name="status" onchange="submitForm(idS${o.setting_id})">
                          <option value="1" ${o.status == 1 ? "selected" : ""}>Activate</option>
                          <option value="2" ${o.status == 2 ? "selected" : ""}>Deactivate</option>

                        </select>
                      </form>
                    </td>

                    <td>
                      <a class="text text-primary" href="SettingDetail?go=UpdateSetting&setting_id=${o.setting_id}&Type=${o.type_id}"><ion-icon size="large" name="create"></ion-icon></a>
                      <a class="text text-danger" data-toggle="modal" data-target="#exampleModal${o.setting_id}"><ion-icon size="large" name="trash"></ion-icon></a>
                    </td>
                  </tr>


                  <!-- Button trigger modal -->

                  <!-- Modal -->
                  <div class="modal fade" id="exampleModal${o.setting_id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <h5 class="modal-title" id="exampleModalLabel">Delete Setting</h5>
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                          </button>
                        </div>
                        <div class="modal-body">
                          Are you sure about that?
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                          <a type="button" href="SettingListServlet?go=DeleteSetting&setting_id=${o.setting_id}&Type=${o.type_id}" class="btn btn-primary">Save changes</a>
                        </div>
                      </div>
                    </div>
                  </div>


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

    <!-- Footer -->
    <footer class="sticky-footer bg-white">
      <div class="container my-auto">
        <div class="copyright text-center my-auto">
          <span>Copyright &copy; Your Website 2020</span>
        </div>
      </div>
    </footer>
    <!-- End of Footer -->

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
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

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

<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
<script src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons.js"></script>

<script src="js/sweetalert.min.js"></script>

</body>
</html>
