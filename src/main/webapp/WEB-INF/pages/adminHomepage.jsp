<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Admin homepage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body class="bg-dark">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="adminHomepage.html" style="margin-left:210px"><h4>Employee management</h4></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto"></ul>
        <form action="logout.html" method="post">
            <input type="submit" class="btn btn-danger" value="Logout" style="margin-right: 210px">
        </form>
    </div>
</nav>
<div class="container" style="margin-top: 50px">
<%--    <%--%>
<%--        if (mode) {--%>
<%--            out.print("<div class=\"card\" style=\"border-radius: 25px;border: 2px solid #73AD21;\">\n" +--%>
<%--                    "        <h5 class=\"card-header\" style=\"color:green;\">Success</h5>\n" +--%>
<%--                    "        <div class=\"card-body\">");--%>
<%--            out.print(config.getServletContext().getAttribute("successMessage"));--%>
<%--            out.print("</div>\n" +--%>
<%--                    "    </div>");--%>
<%--            request.removeAttribute("successMessage");--%>
<%--        }--%>
<%--    %>--%>
    <%
        if (request.getAttribute("successMessage") != null) {
            out.print("<div class=\"card\" style=\"border-radius: 25px;border: 2px solid #73AD21;\">\n" +
                    "        <h5 class=\"card-header\" style=\"color:green;\">Success</h5>\n" +
                    "        <div class=\"card-body\">");
            out.print(request.getAttribute("successMessage"));
            out.print("</div>\n" +
                    "    </div>");
            request.removeAttribute("successMessage");
        }
    %>
    <div class="row">
        <div class="col-md-4">
            <div class="card border-dark mb-3" style="border-radius: 25px;border: 2px solid #73AD21;">
                <div class="card-body ">
<%--                    <a style="width: 100%;height: 100px" class="btn btn-outline-success" href="addNewEmployee.html" value="Insert Employee">Insert Employee</a>--%>
                    <form action="getEmployeeRegistrationForm.html" method="post">
                        <input style="width: 100%;height: 100px" class="btn btn-outline-success" type="submit" value="Insert Employee">
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card border-dark mb-3" style="border-radius: 25px;border: 2px solid #73AD21;">
                <div class="card-body ">
                    <form action="showAllEmployees.html" method="get">
                        <input hidden name="next" value="null">
                        <input  style="width: 100%;height: 100px" class="btn btn-outline-success" type="submit" value="Show All Employees">
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card border-dark mb-3" style="border-radius: 25px;border: 2px solid #73AD21;">
                <div class="card-body ">
                    <form action="searchEmployee.html" method="post">
                        <input style="width: 100%;height: 100px" class="btn btn-outline-success" type="submit" value="Edit Employee">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
