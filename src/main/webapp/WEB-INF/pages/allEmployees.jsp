<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>All employees</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
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

<div class="container">

    <div class="card" style="margin-top: 50px;border-radius: 25px">
        <div class="card-header">
            All employees(Showing 5 records per page)
        </div>
        <div class="card-body">
                <form:form modelAttribute="employee" action="recordsPerPage.html" method="post">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">Set records per page</span>
                    </div>

<%--                    <input name="count" type="number" class="form-control" placeholder="records per page"--%>
<%--                           required>--%>
                    <form:input type="number" cssClass="form-control" path="count" placeholder="Records per page" required="required"></form:input>

                    <input type="submit" class="btn btn-info" value="update">
                </div>
                </form:form>
            <hr>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Emp Id</th>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">Joining</th>
                        <th scope="col">DOB</th>
                        <th scope="col">Department Id</th>
                        <th scope="col">Grade</th>
                        <th scope="col">Designation</th>
                        <th scope="col">Gender</th>
                        <th scope="col">Base Pay</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="employee" items="${allEmployees}">
                    <tr >
                        <td>${employee.employeeId}</td>
                        <td>${employee.firstName}</td>
                        <td>${employee.lastName}</td>
                        <td>${employee.dateOfJoining}</td>
                        <td>${employee.dateOfBirth}</td>
                        <td>${employee.departmentId}</td>
                        <td>${employee.grade}</td>
                        <td>${employee.designation}</td>
                        <td>${employee.gender}</td>
                        <td>${employee.basePay}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <hr>

            <div class="container">
                <ul class="list-inline">
                    <li class="list-inline-item">
<%--                        <form class="form-inline pull-right" method="get" action="showAll">--%>
<%--                            <input type="submit" value="Previous" class="btn btn-light">--%>
<%--                        </form>--%>
                        <a href="showAllEmployees.html?next=false" class="form-inline pull-right btn btn-light">Previous</a>
                    </li>
                    <li class="list-inline-item">
<%--                        <form class="form-inline pull-right" method="post" action="showAll">--%>
<%--                            <input type="submit" value="Next" class="btn btn-primary d-inline">--%>
<%--                        </form>--%>
                        <a href="showAllEmployees.html?next=true" class="form-inline pull-right btn btn-primary d-inline">Next</a>
                    </li>
                </ul>
            </div>

        </div>

</body>
</html>