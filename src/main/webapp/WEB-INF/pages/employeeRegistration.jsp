<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Employee registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body class=" bg-dark">

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


<div class="container" style="margin-top: 40px;">
    <%
        if (request.getAttribute("errorMessage") != null) {
            out.print("<div class=\"card\" style=\"border-radius: 25px;border: 2px solid #73AD21;\" >\n" +
                    "        <h5 class=\"card-header\" style=\"color: red\">Unable to Create new Employee</h5>\n" +
                    "        <div class=\"card-body\">");
            out.println(request.getAttribute("errorMessage"));
            out.println("</div>\n" +
                    "    </div>");
            request.removeAttribute("errorMessage");
        }
    %>
    <div class="card border-dark mb-3" style="border-radius: 25px;border: 2px solid #73AD21;">
        <div class="card-header">
            Add New Employee
        </div>

        <form:form modelAttribute="newEmployee" action="addNewEmployee.html" method="post">

            <div class="card-body">

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">Employee Id</span>
                    </div>
<%--                    <input name="employeeId" type="text" class="form-control" placeholder="Employee Id"--%>
<%--                           aria-label="employeeId"--%>
<%--                           required>--%>
                    <form:input cssClass="form-control" path="employeeId" placeholder="Employee Id" required="required"></form:input>

                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">First and last name</span>
                    </div>
<%--                    <input name="firstName" type="text" class="form-control" placeholder="First Name" required>--%>
                    <form:input cssClass="form-control" path="firstName" placeholder="First Name" required="required"></form:input>
<%--                    <input name="lastName" type="text" class="form-control" placeholder="Last Name" required>--%>
                    <form:input cssClass="form-control" path="lastName" placeholder="Last Name" required="required"></form:input>
                </div>
                <br>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">Department Id</span>
                    </div>
<%--                    <input name="departmentId" type="text" class="form-control" placeholder="Department Id"--%>
<%--                           aria-label="employeeId"--%>
<%--                           required>--%>
                    <form:input cssClass="form-control" pattern="[1-9]{1}[0-9]{1,2}" path="departmentId" placeholder="Department Id" required="required"></form:input>
                </div>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">Date of joing and Date of Birth</span>
                    </div>
<%--                    <input name="dateOfJoining" type="date" class="form-control" placeholder="Date of Joining" required>--%>
                    <form:input cssClass="form-control" type="date" path="dateOfJoining" placeholder="Employee Id" required="required"></form:input>
<%--                    <input name="dateOfBirth" type="date" class="form-control" placeholder="Date of Birth" required>--%>
                    <form:input cssClass="form-control" type="date" path="dateOfBirth" placeholder="Employee Id" required="required"></form:input>
                </div>
                <br>

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <label class="input-group-text">Grade</label>
                    </div>
                        <form:select path = "grade" cssClass="custom-select">
                            <form:option value = "NONE" label = "Select"/>
                            <form:options items = "${grade}" />
                        </form:select>
                </div>


                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Designation</span>
                        </div>
    <%--                    <input name="designation" type="text" class="form-control" placeholder="Designation "--%>
<%--                           aria-label="employeeId">--%>
                    <form:input cssClass="form-control" path="designation" placeholder="Designation" required="required"></form:input>
                </div>

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <label class="input-group-text">Gender</label>
                    </div>
                    <form:select path = "gender" cssClass="custom-select">
                        <form:option value = "NONE" label = "Select"/>
                        <form:options items = "${gender}" />
                    </form:select>
                </div>

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">₹</span>
                    </div>
<%--                    <input name="basePay" type="text" class="form-control" placeholder="Salary"--%>
<%--                           aria-label="Amount (to the nearest rupees)"--%>
<%--                           required>--%>
                    <form:input cssClass="form-control" pattern="[1-9]{1}[0-9]{1,2,3,4,5,6,7}" path="basePay" placeholder="Salary" required="required"></form:input>
                    <div class="input-group-append">
                        <span class="input-group-text">.00</span>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary" style="width: 321px">Add employee</button>
            </div>
        </form:form>
    </div>
</div>
</div>
</body>
</html>