<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
        <link rel="icon" href="img/cai nay hoi la.png" type="image/gif" sizes="16x16">

    </head>

    <body id="page-top">

    <jsp:useBean id="DAOSen" scope="page" class="com.management.dao.DAOSen" />

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
                            <div > </div>
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary"><a href="MilestoneListServlet">Milestone Table </a> / Class: ${classCode.classCode}</h6>
                            </div>

                            <div> All Of Classes:<c:forEach var="o" items="${listClassCode}">
                                    <a class="btn btn-outline-primary" href="MilestoneListServlet?go=EachClass&classId=${o.class_id}">${o.classCode} </a> 
                                </c:forEach></div>

                            <div> Click <a href="MilestoneListServlet?go=addMilestone">here</a> to add Milestone</div>
                            <div><a class="btn btn-outline-success" href="MilestoneListServlet?go=Sync&classId=${classCode.class_id}"> Sync All Milestone </a></div>                                                      
                            <p style="color: greenyellow">${thongbao}</p>
                            <div class="card-body">
                                <div class="table-responsive">

                                    <table id="slide" class="table table-bordered" width="100%" cellspacing="0">

                                        <thead>
                                            <tr>
                                                <th>Milestone Name</th>
                                                <th>Iteration Name</th>                                                                                        
                                                <th>Class</th>
                                                <th>From Date</th>
                                                <th>To Date</th>
                                                <th>Status</th>
                                                <th>Action</th>

                                            </tr>
                                        </thead>

                                        <tbody>
                                            <c:forEach var="o" items="${listMileByClass}">
                                                <tr>
                                                    <td>${o.milestone_name}</td>    
                                                    <td>${o.iterationName}</td>
                                                    <td>${DAOSen.getClassById(o.class_id).classCode}</td>
                                                    <td>${o.from_date}</td>
                                                    <td>${o.to_date}</td>
                                                    <td>
                                                        <form id="idS${o.milestone_id}" action="MilestoneListServlet?go=updateStatus" method="Post">
                                                            <input type="hidden" name="mileId" value="${o.milestone_id}">
                                                            <select class="form-control form-control-user id" id ="status" name="status" onchange="submitForm(idS${o.milestone_id})">
                                                                <option value="1" ${o.status == 1 ? "selected" : ""}>Open</option>
                                                                <option value="2" ${o.status == 2 ? "selected" : ""}>Closed</option>
                                                                <option value="3" ${o.status == 3 ? "selected" : ""}>Cancelled</option>
                                                            </select>
                                                        </form>
                                                    </td>                                                    
                                                    <td><a class="text text-primary" href="MilestoneListServlet?go=updateMilestone&mileId=${o.milestone_id}&iteId=${o.interation_id}&classId=${o.class_id}"><ion-icon size="large" name="create"></ion-icon> </a>
                                                        <a class="text text-danger" href="MilestoneListServlet?go=deleteMilestone&mileId=${o.milestone_id}&iteId=${o.interation_id}&classId=${o.class_id}"> <ion-icon size="large" name="trash"></ion-icon></a></td>

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

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
                                                                function CallAjax() {
                                                                    var classId = document.getElementById("classId").value;
                                                                    var iteName = document.getElementById("iteId").value;
                                                                    var Ajax = "Ajax";
                                                                    $.ajax({
                                                                        url: "/g1/MilestoneListServlet",
                                                                        type: "get",
                                                                        data: {
                                                                            Ajax: Ajax,
                                                                            classId: classId,
                                                                            iteId: iteName
                                                                        },
                                                                        success: function (data) {
                                                                            var slide = document.getElementById("slide");
                                                                            slide.innerHTML = data;
                                                                        },
                                                                        error: function (xhr) {
                                                                        }
                                                                    });
                                                                }
        </script>

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

        <script src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons.js"></script>

        <script src="js/sweetalert.min.js"></script>

    </body>

</html>

