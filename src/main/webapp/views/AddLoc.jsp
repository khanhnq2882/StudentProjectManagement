<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 
    Document   : AddLoc
    Created on : Jun 24, 2022, 9:38:54 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>G1 - Dashboard</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="css/SenCss.css" rel="stylesheet">
        <link rel="stylesheet" href="fnon.min.css">
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">

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
                            <div class="card">
                                <div class="row">

                                    <div class="col-lg-12 card-body">
                                        <div class="p-5 ">

                                        <c:if test="${upd == 1234}">
                                            <div class="text-center ">
                                                <h1 class="h4 text-gray-900 mb-4">Update LOC evaluation</h1>
                                            </div>
                                            <form class="user" action="LocEvalue" method="POST">
                                                <input name="go" type="hidden" value="updateLoc" >
                                                <input name="tracking" type="hidden" value="${tracking}" >
                                                <input name="eid" type="hidden" value="${eid}" >

                                                <div class="form-group">
                                                    <p>Date created</p>
                                                    <input name="dateCreate" type="date" value="${locU.evaluation_time}" class="form-control " 
                                                           id="exampleInputEmail"  required>
                                                </div>
                                                <div class="form-group">
                                                    <p>Notes</p>
                                                    <textarea name="note" class="form-control  note" rows="4" cols="20">${locU.evaluation_note}</textarea>
                                                </div>

                                                <div class="form-group">
                                                    <p>Complexity</p>

                                                    <select class="form-control " name="comp">

                                                        <c:forEach var="o" items="${complex}">
                                                            <option value="${o.setting_value}" ${locU.complexity_id == o.setting_value ? "selected":""}>${o.display_order} (${o.setting_value})</option>
                                                        </c:forEach>

                                                    </select>
                                                </div>

                                                <div class="form-group">
                                                    <p>Quality</p>
                                                    <select class="form-control " name="qual">
                                                        <c:forEach var="o" items="${quality}">
                                                            <option value="${o.setting_value}" ${locU.quality_id == o.setting_value ? "selected":""}>${o.display_order} (${o.setting_value}%)</option>
                                                        </c:forEach>

                                                    </select>
                                                </div>

                                            </c:if>

                                            <c:if test="${upd != 1234}">
                                                <div class="text-center">
                                                    <h1 class="h4 text-gray-900 mb-4">Add more LOC evaluation</h1>
                                                </div>
                                                <form class="user" action="LocEvalue" method="POST">
                                                    <input type="hidden" name="go" value="AddLoc" />
                                                    <input name="tracking" type="hidden" value="${tracking}" >
                                                    <div class="form-group">
                                                        <p>Date created</p>
                                                        <input name="dateCreate" type="date" value="${today}" class="form-control " 
                                                               id="exampleInputEmail"  required>
                                                    </div>
                                                    <div class="form-group">
                                                        <p>Notes</p>
                                                        <textarea name="note" class="form-control  note" rows="4" cols="20"></textarea>
                                                    </div>
                                                    <div class="form-group">
                                                        <p>Complexity</p>

                                                        <select class="form-control " name="comp">

                                                            <c:forEach var="o" items="${complex}">
                                                                <option value="${o.setting_value}">${o.display_order} (${o.setting_value})</option>
                                                            </c:forEach>

                                                        </select>
                                                    </div>

                                                    <div class="form-group">
                                                        <p>Quality</p>
                                                        <select class="form-control " name="qual">
                                                            <c:forEach var="o" items="${quality}">
                                                                <option value="${o.setting_value}">${o.display_order} (${o.setting_value}%)</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </c:if>

                                                <div class="log">

                                                    <c:if test="${upd == 1234}">
                                                        <input type="submit" value="Update" name="submit">
                                                    </c:if>

                                                    <c:if test="${upd != 1234}">
                                                        <input type="submit" value="Add" name="submit">
                                                    </c:if>


                                                </div>


                                            </form>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <!-- /.container-fluid -->

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
        <jsp:include page="/general/LogOut.jsp"></jsp:include>

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

    </body>

</html>
