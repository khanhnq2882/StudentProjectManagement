<%-- 
    Document   : HomeForStu
    Created on : May 25, 2022, 10:41:13 PM
    Author     : ASUS
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<h1> Hello Student, <strong>${Loged.fullname}! </strong></h1>

<div class="row">
    
    <div class="col-xl-3 col-md-6 mb-4">
        <a href="LocStudent?go=&Tid=${Loged.user_id}">
            <div class="card border-left-primary shadow h-100 py-2">

                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">

                            <div class="h5 mb-0 font-weight-bold text-gray-800">View your loc</div>

                        </div>
                    </div>
                </div>

            </div>
        </a>
    </div>
    
     
        
        
</div>