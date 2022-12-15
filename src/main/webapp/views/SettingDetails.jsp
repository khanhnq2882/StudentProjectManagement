
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
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
      <div class="container">
        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-12">
                <div class="p-5">
                  <div class="text-center">
                  </div>
                  <form action="SettingDetail">
                    <input type="hidden" name="go" value="UpdateDetail" />
                    <h3>Update Setting</h3>
                    <input  class="form-control" type="hidden" name="setting_id" value="${s.setting_id}" />
                    Setting Type:
                    <input  class="form-control" type="text" name="settingType" value="${s.type_id}" hidden="" />${typeValue.get(s.type_id - 1)}
                    <br> Order(*):
                    <input  class="form-control" type="text" name="order" value="${s.display_order}" required/>
                    Setting Title(*):
                    <input class="form-control" type="text" name="lessontype" value="${s.setting_title}" required />
                    Value:
                    <input class="form-control"  type="text" name="value" value="${s.setting_value}" style=""/>
                    Status:
                    <br>   <input type="radio" name="status" value="1" ${s.status == 1?"checked":""}/>Active
                    <input type="radio" name="status" value="2" ${s.status == 2?"checked":""} style="margin-left: 100px" />Deactive
                    <br>Description:
                    <textarea  class="form-control" name="description">${s.note}</textarea>
                    <br>
                    <input type="submit" name="submit" class="update"  value="Update" />


                  </form>
                </div>
              </div>
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

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

</body>

</html>
