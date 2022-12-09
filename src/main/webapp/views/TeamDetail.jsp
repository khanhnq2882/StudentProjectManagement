
<%@ page import="com.management.entity.Team" %>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Group 1 - Team Detail</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="css/SenCss.css" rel="stylesheet">
        <link rel="stylesheet" href="fnon.min.css">
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
                    <%
                    Team team = (Team)request.getAttribute("Team");
                    //ArrayList<Team> vect = (ArrayList<Team>) request.getAttribute("classList");                  
                    %>
                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Team Details</h1>
                                    </div>
                                    <form action="TeamDetail">
                                        <input type="hidden" name="go" value="UpdateDetail" />
                                        <table style="width:100%;max-width: 700px; border: 0;" cellpadding="4" cellspacing="0">
                                            <td style="width:50%">
                                                <label for="className">Class Name:</label><br />
                                                <input type="text" name="classID" value="<%= team.getClass_id()%>" class="form-control" style="width:100%;max-width: 300px;" required/>
                                                <label for="topicCode">Topic Code:</label><br />
                                                <input type="text" name="topicCode" value="<%= team.getTopic_code()%>" class="form-control" style="width:100%;max-width: 300px;" required/>
                                                <label for="topicName">Topic Name:</label><br />
                                                <input type="text" name="topicName" value="<%= team.getTopic_name()%>" class="form-control" style="width:100%;max-width: 300px;" required/>
                                                <label for="gitlabURL">GitLab URL:</label><br />
                                                <input type="text" name="gitlabURL" value="<%= team.getGitlab_url()%>" class="form-control" style="width:100%;max-width: 300px;" required/>
                                                <label for="status">Status:</label><br />
                                            <tr>
                                                <td>
                                                    <br><input type="radio" name="status" value="1" <%=team.getStatus() == 1 ? "checked" : "" %> style="margin-left: 20px" /><a style="font-size: 13px">Active</a>
                                                    <input type="radio" name="status" value="2" <%=team.getStatus() == 2 ? "checked" : "" %> style="margin-left: 30px" /><a style="font-size: 13px">Deactive</a>
                                                </td>
                                            </tr>
                                            </td>
                                        </table>
                                        <br> <br> <input name="submit" type="submit" value="Update" style="width: 80px" />
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

    </body>

</html>

