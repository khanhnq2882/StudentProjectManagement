<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<%@ page import="com.management.entity.Criteria" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/general/Head.jsp"></jsp:include>

<body id="page-top">
<!-- Page Wrapper -->
<style>
  input[type="reset"] {
    border: none;
    outline: none;
    background: repeating-linear-gradient(45deg, #ff000080, #0000ff85);
    border-radius: 15px;
    padding: 5px 15px;
    color: white;
  }
  .update{
    margin-top: 10px;
  }
</style>
<div id="wrapper">

  <jsp:include page="/general/Sidebar.jsp"></jsp:include>

  <div id="content-wrapper" class="d-flex flex-column">

    <!-- Main Content -->
    <div id="content">

      <jsp:include page="/general/Header.jsp"></jsp:include>

      <%
        ArrayList<Criteria> vect = (ArrayList<Criteria>) request.getAttribute("IterList");

        int iter = Integer.parseInt((String)request.getAttribute("iter"));

      %>
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
                  <form class="user" action="CriteriaDetail" method="post" >
                    <input type="hidden" name="go" value="updateCriteria"/>
                    <input type="hidden" name="criteria_id" value="${ct.criteria_id}" />

                    <h3>Update Criteria</h3>
                    Iteration:
                    <select  class="form-control"  name="iteration" >
                      <%
                        for (Criteria o : vect) { %>
                      <option  value="<%=o.getIteration_id()%>" <%=o.getIteration_id() == iter ? "selected" : "" %>><%=o.getSubject_code()%>-<%=o.getIteration_name()%></option>>
                      <%       }
                      %>
                    </select>
                    Title(*):
                    <input  class="form-control" maxlength="140" type="text" name="title" value="${ct.evaluation_title}" required/>
                    Weight(*):
                    <br> <input style="width:350px" type="text" name="weight" value="${ct.evaluation_weight}" required/>%
                    <br>  Loc(*):
                    <input class="form-control" type="text" name="loc" value="${ct.max_loc}" required />
                    Order(*):
                    <br>  <input style="width:350px" type="text" name="order" value="${ct.criteria_order}"/>
                    <br>Status:
                    <br>  <input type="radio" name="status" value="1" ${ct.status == 1?"checked":""}>Active
                    <input style="margin-left: 30px;" type="radio" name="status" value="2" ${ct.status == 2?"checked":""}>Deactive
                    <span style="margin-left:35px"> Team Evaluation:</span>
                    <input type="checkbox" name="evaluation" value="true" ${ct.team_evaluation == true?"checked":""}  />
                    <br>   Description:
                    <textarea  class="form-control" name="description">${ct.description}</textarea><br>
                    <input type="submit" name="submit" class="update" value="Update" />

                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- /.container-fluid -->

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
  <script src="js/SenJS.js"></script>
  <script src="js/fnon.min.js"></script>
  <!-- Footer -->
  <!--            <footer class="sticky-footer bg-white">
                  <div class="container my-auto">
                      <div class="copyright text-center my-auto">
                          <span>Copyright &copy; Your Website 2021</span>
                      </div>
                  </div>
              </footer>-->
  <!-- End of Footer -->

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
  <i class="fas fa-angle-up"></i>
</a>

<jsp:include page="/general/LogOut.jsp"></jsp:include>

<jsp:include page="/general/Footer.jsp"></jsp:include>

</body>

</html>

