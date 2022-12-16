<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="<c:url value="/assets/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<script src="<c:url value="/assets/vendor/jquery-easing/jquery.easing.min.js"/>"></script>
<script src="<c:url value="/assets/js/sb-admin-2.min.js"/>"></script>
<script src="<c:url value="/assets/vendor/chart.js/Chart.min.js"/>"></script>
<script src="<c:url value="/assets/js/demo/chart-area-demo.js"/>"></script>
<script src="<c:url value="/assets/js/demo/chart-pie-demo.js"/>"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/dataTables.bootstrap4.min.js"></script>
<script src="<c:url value="/assets/js/demo/datatables.js"/>"></script>
<script src="<c:url value="/assets/js/SenJS.js"/>"></script>
<script src="<c:url value="/assets/js/fnon.min.js"/>"></script>

${alert eq null ? "" : alert}

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