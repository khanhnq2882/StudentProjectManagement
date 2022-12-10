<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/general/Head.jsp"/>

<body id="page-top">


<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="/general/Sidebar.jsp"/>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <jsp:include page="/general/Header.jsp"/>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container">
                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <div class="row">
                            <div class="col-lg-5 d-none d-lg-block">
                                <form class="user" action="<%=request.getContextPath()%>/UpdateImg" method="post" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <input type="hidden" class="form-control form-control-user" name="userid"
                                               value="${Loged.user_id}">
                                    </div>
                                    <div class="text-center">
                                        <br>
                                        <img src="<c:url value="/uploads/${Loged.avatar_link}"/>"
                                             style="border-radius: 50%; width: 250px; height: 250px; object-fit: cover;" class="avatar img-circle img-thumbnail mb-3"
                                             alt="avatar">
                                        <h6>Upload a different photo...</h6>
                                        <span style="color: red">${haizz}</span>
                                        <input type="file" name="image"
                                               class="file form-control text-center center-block file-upload mb-3 mx-4">
                                        <input type="submit" value="Save Image"/>
                                    </div>
                                    </hr><br>
                                </form>
                            </div>
                            <div class="col-lg-7">
                                <form class="user" action="<%=request.getContextPath()%>/UpdateProfile" method="post">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">Update your User Profile</h1>
                                        </div>
                                        <div class="form-group">
                                            <input type="hidden" class="form-control form-control-user" name="userid"
                                                   value="${Loged.user_id}">
                                        </div>
                                        <div class="form-group">
                                            <label>Fullname:</label>
                                            <input type="text" class="form-control form-control-user" name="fullname"
                                                   value="${Loged.fullname}">
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-12 mb-6 mb-sm-0">
                                                <label>Gender: </label>
                                                <select name="gender" class="Gender">
                                                    <option value="0" ${Loged.gender == 0 ? "selected" : ""} disabled>Gender
                                                    </option>
                                                    <option value="1" ${Loged.gender == 1 ? "selected" : ""}>Male
                                                    </option>
                                                    <option value="2" ${Loged.gender == 2 ? "selected" : ""}>Female
                                                    </option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>Date of Birth</label>
                                            <input type="date" name="dateofbirth" class="form-control form-control-user"
                                                   value="${Loged.date_of_birth}"
                                                   min="1980-01-01" max="${DateNow}">

                                        </div>
                                        <div class="form-group">
                                            <label>E-mail</label>
                                            <input type="email" class="form-control form-control-user" name="email"
                                                   value="${Loged.email}">
                                        </div>
                                        <div class="form-group">
                                            <label>Mobile</label>
                                            <input type="text" class="form-control form-control-user" name="mobile"
                                                   value="${Loged.mobile == 0 ? "" : Loged.mobile}">

                                        </div>
                                        <div class="form-group">
                                            <label>Facebook link</label>
                                            <input type="text" class="form-control form-control-user" name="fblink"
                                                   value="${Loged.facebook_link eq "" ? "" : Loged.facebook_link}">
                                        </div>
                                        <div>
                                            ${updated}
                                        </div>
                                        <input type="submit" value="Update"/>
                                        <hr>
                                        <hr>
                                    </div>
                                </form>

                            </div>
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
<jsp:include page="/general/LogOut.jsp"/>

<jsp:include page="/general/Footer.jsp"/>

</body>

</html>
