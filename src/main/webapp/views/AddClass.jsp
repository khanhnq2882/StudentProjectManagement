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


            <div class="container card">
                <div class="card-body">
                    <%
                        String update = (String) request.getAttribute("update");
                        if(update == null){
                            update = "";
                        } %>


                    <form action="ShowAllClass?go=<%=update.equals("updateClass") ? "updateClass":"AddClass" %>"
                          id="a<%=update %>" method="POST" >
                        <input  class="form-control form-control-user" type="hidden" name="go" value="<%=update.equals("updateClass") ? "updateClass":"AddClass" %>" />
                        <strong>     Class Code </strong>
                        <input  class="form-control form-control-user" type="text" name="class_code" value="${class_code}" placeholder="Example: AA1234"/>
                        Trainer

                        <select name="trainer" class="form-control form-control-user">
                            <option value="">--Change here--</option>
                            <c:forEach var="o" items="${vectT}" >
                                <option value="${o.user_id}" ${o.user_id == trainer ? "selected" : ""}>${o.roll_number} - ${o.fullname}</option>
                            </c:forEach>
                        </select>

                        <strong>
                            Subject </strong>
                        <select name="subject" class="form-control form-control-user">
                            <option value="">--Change here--</option>
                            <c:forEach var="o" items="${listSub}">
                                <option value="${o.subject_id}" ${o.subject_id == subject ? "selected" : ""}>${o.subject_name}</option>
                            </c:forEach>
                        </select>

                        <strong>    Class year </strong>
                        <br>
                        <input  class="form-control form-control-user" type="number" min="1"
                                max="2022" step="1" name="class_Year" value="${class_Year != null ? class_Year : "2022"}"/>
                        <strong>   Class term </strong>
                        <select name="class_term" class="form-control form-control-user">
                            <option value="">--Change here--</option>
                            <c:forEach var="k" begin="1" end="9">
                                <option value="${k}" ${class_term == k ? "selected":""}>term #${k}</option>
                            </c:forEach>
                        </select>
                        <strong> Block 5 class  </strong>
                        <label for="true"><input type="radio" id="true" name="block5" value="1" ${block5 == 1 ? "checked":""} ${block5 == null ? "checked" : ""}/>True</label>
                        <label for="false"><input type="radio" id="false" name="block5" value="0" ${block5 == 0 ? "checked":""}/>False</label>
                        <br>
                        <strong>    status    </strong>
                        <label for="act">
                            <input id="act" type="radio" name="status" value="1" ${status == 1 ? "checked":""} ${status == null ? "checked" : ""}/>Active</label>
                        <label for="dea"><input type="radio" id="dea"  name="status" value="0" ${status == 0 ? "checked":""}/>Deactive</label>

                        <br>

                        <% if(update.equals("updateClass")){ %>
                        <input class="btn btn-primary" type="hidden" name="ClassId" value="${cid}" />
                        <input class="btn btn-primary" type="submit" name="submit" value="Update Class" />
                        <%  }else{ %>
                        <input class="btn btn-primary" type="submit" name="submit" value="Add Class" />
                        <%}  %>


                        <input class="btn btn-primary" type="reset" name="reset" value="Reset"/>
                    </form>

                    <span class="ThongBao">    <h2>${mess}</h2> </span>

                    <style>
                        span.ThongBao {
                            color: red;
                            text-align: center;
                        }
                    </style>

                </div>
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <script src="js/sweetalert.min.js"></script>
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

            function Cool() {
                swal("Good job!", "You clicked the button!", "success");
            }
        </script>

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2021</span>
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
<jsp:include page="/general/Footer.jsp"></jsp:include>


</body>

</html>
