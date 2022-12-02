<%-- 
    Document   : Sidebar
    Created on : May 20, 2022, 6:16:29 PM
    Author     : asus
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" style="z-index: 10" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="Home">
        <div class="sidebar-brand-icon rotate-n-15">
            <img src="img/cai nay hoi la.png" style="max-height: 50px">
        </div>
        <div class="sidebar-brand-text mx-3">Group 1 Project</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item">
        <a class="nav-link" href="Home">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Nav Item - Admin-->
    <c:if test="${Loged.role_id == 4}">
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
               aria-expanded="true" aria-controls="collapseUtilities">
                <i class="fas fa-fw fa-cog"></i>
                <span>Admin</span>
            </a>
            <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                 data-parent="#accordionSidebar">            
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Admin Setting:</h6>
                    <a class="collapse-item" href="SettingListServlet">Setting</a>
                    <a class="collapse-item" href="UserController">User</a>
                    <a class="collapse-item" href="SubjectList">Subject</a>
                    <a class="collapse-item" href="ShowAllClass">Class</a>
                </div>
            </div>
        </li>
    </c:if>

    <!-- Nav Item - Admin-->
    <c:if test="${Loged.role_id >= 3}">
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fas fa-fw fa-cog"></i>
                <span>Author</span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Author Setting:</h6>
                    <a class="collapse-item" href="SubjectSettingList">Subject Setting</a>
                    <a class="collapse-item" href="IterationListServlet">Iteration</a>
                    <a class="collapse-item" href="Criteria">Criteria</a>
                </div>
            </div>
        </li>
    </c:if>

    <!-- Nav Item - Trainer-->
    <c:if test="${Loged.role_id >= 2}">
        <li class="nav-item">
            <a class="nav-link" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true"
               aria-controls="collapsePages">
                <i class="fas fa-fw fa-folder"></i>
                <span>Trainer</span>
            </a>
            <div id="collapsePages" class="collapse" aria-labelledby="headingPages"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Trainer Setting:</h6>
                    <a class="collapse-item"  href="ClassUser">Classes</a>
                    <a class="collapse-item"  href="ClassUser4Admin">Class Students</a>
                    <a class="collapse-item"  href="MilestoneListServlet">Milestone</a>
                    <a class="collapse-item"  href="TeamList">Team</a>
                    <a class="collapse-item"  href="FeatureListTrainer">Feature</a>
                    <a class="collapse-item"  href="FunctionListTrainer">Function</a>
                </div>
            </div>
        </li>
    </c:if>

    <!-- Nav Item - Student-->
    <li class="nav-item">
        <a class="nav-link" href="#" data-toggle="collapse" data-target="#collapseStudent" aria-expanded="true"
           aria-controls="collapseStudent">
            <i class="fas fa-fw fa-folder"></i>
            <span>Student</span>
        </a>
        <div id="collapseStudent" class="collapse" aria-labelledby="headingPages"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Student Setting:</h6>
               
                <a class="collapse-item"  href="FeatureList">Feature</a>
                <a class="collapse-item"  href="FunctionList">Function</a>
                <a class="collapse-item"  href="#">Tracking</a>
                <a class="collapse-item"  href="#">Loc</a>
            </div>
        </div>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
