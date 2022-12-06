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
            <div class="container">

                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h3 class="m-0 font-weight-bold text-primary">Show all class</h3>
                        <h4 class="m-0 font-weight-bold text-warning">

                        </h4>

                    </div>
                    <div class="card-body">

                        <div>

                            <nav class="navbar navbar-expand-lg navbar-light ">
                                <span class="navbar-brand" >Filter</span>
                                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                    <span class="navbar-toggler-icon"></span>
                                </button>

                                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                    <form class="form-inline my-2 my-lg-0" action="ShowAllClass?go=ViewAllClass" method="POST">
                                        <ul class="navbar-nav mr-auto">
                                            <li class="nav-item dropdown">
                                                Trainer:
                                                <select class="form-control" name="trainerName">
                                                    <option value="">-View All-</option>
                                                    <c:forEach var="o" items="${allTra}">
                                                        <option value="${o.user_id}" ${trainerName==o.user_id ? "selected" : ""}>${o.fullname}</option>
                                                    </c:forEach>
                                                </select>

                                            </li>

                                            <li class="nav-item dropdown">
                                                Status:
                                                <select class="form-control" name="ClaSta">
                                                    <option value="2" ${sta == 2 ? "selected":""}>All</option>
                                                    <option value="1" ${sta == 1 ? "selected":""}>Ongoing</option>
                                                    <option value="0" ${sta == 0 ? "selected":""}>Ended</option>
                                                </select>

                                            </li>

                                        </ul>

                                        <input class="form-control mr-sm-2" type="text" name="searchClass" value="${search}" placeholder="Search" aria-label="Search">
                                        <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Search</button>


                                    </form>

                                </div>
                                <div class="float-right">
                                    <a class="text text--" href="ShowAllClass?go=AddClass">Add more class</a>
                                </div>
                            </nav>



                        </div>
                        <div class="table-responsive">
                            <span>There are: ${totalResult} results</span>
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>

                                    <th>Class code</th>
                                    <th>Trainer</th>
                                    <th>subject name</th>

                                    <th>class term</th>
                                    <th>block 5 ?</th>
                                    <th>Status</th>
                                    <th >Action</th>

                                </tr>
                                </thead>
                                <tfoot>
                                <tr>

                                    <th>Class code</th>
                                    <th>Trainer</th>
                                    <th>subject name</th>

                                    <th>class term</th>
                                    <th>block 5 ?</th>
                                    <th>Status</th>
                                    <th >Action</th>

                                </tr>
                                </tfoot>
                                <tbody>

                                <c:forEach var="o" items="${vect}">
                                    <tr>
                                        <form  method="POST" action="UpdateLecture"  id="Lec${o.id}">

                                            <input type="hidden" name="ClassID" value="${o.id}" />

                                            <td>
                                                <a href="ClassUser4Admin?class_id=${o.id}">${o.classCode}</a>
                                            </td>
                                            <td>

                                                <select class="form-control form-control-user" name="Lecture" onchange="submitForm(Lec${o.id})">
                                                    <c:forEach var="te" items="${vectT}">
                                                        <option value="${te.roll_number}" ${te.fullname == o.trainerId ? "selected":""}>
                                                                ${te.roll_number} -
                                                                ${te.fullname}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                        </form>


                                        </td>
                                        <td>${o.subjectId}</td>

                                        <td>${o.classTerm}</td>
                                        <td>${o.block5Class == 1 ? "True":"False"}</td>
                                        <td>
                                                <span class="">
                                                        ${o.status == 1 ? "Ongoing" : "Ended"}
                                                </span>
                                        </td>
                                        <td>
                                            <a href="ShowAllClass?go=updateClass&ClassId=${o.id}">
                                                    <span class="material-symbols-outlined">
                                                        edit
                                                    </span></a>

                                            <a href="TeamList?go=listAllTeam&cid=${o.id}" >
                                                    <span class="material-symbols-outlined">
                                                        group
                                                    </span></a>

                                            <a href="ClassUser4Admin?class_id=${o.id}"><span class="material-symbols-outlined">
visibility
</span></a>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                            <c:forEach var="k" begin="1" end="${numberOfPages}" >
                                <a href="ShowAllClass?go=ViewAllClass&page=${k}&searchClass=${search}" class="${page == k ? "collapse-item active":""}">  Page ${k}</a>
                            </c:forEach>
                        </div>
                    </div>
                </div>



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

<jsp:include page="/general/Footer.jsp"></jsp:include>


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

    function getSelectValue()
    {
        var selectedValue = document.getElementById("list").value;
        console.log(selectedValue);
    }
    getSelectValue();

</script>

<style>
    form {
        margin: 20px 20px;
    }


    a.collapse-item.active {
        text-decoration: underline;
        color: blue;
        font-size: large;
    }

    input[type="text"] {
        text-align: center;
        width: 15rem;
        transition: all 0.2s;
        border-radius: 10px;
    }

    /*            input[type="text"]:focus {
                    transition: all 0.2s;
                    width: 25rem;
                }*/

    input[type="submit"] {
        cursor: pointer;
        transition: all 0.2s;
        border-radius: 15px;
        padding: 0px 15px;
        background-image: repeating-linear-gradient(117deg, #4e73df70, #eba1e2bd 100px);
    }

    input[type="submit"]:hover {
        transition: all 0.2s;
        background-image: repeating-linear-gradient(117deg, #406cef70, #ed73dfbd 100px);
    }
</style>





</body>

</html>
