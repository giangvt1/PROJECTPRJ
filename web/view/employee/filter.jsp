<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Filter Employees</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet"/>
        <style>
            body {
                background-color: #1a1a2e;
                color: #f5f5f5; /* Bright white text */
                font-family: 'Arial', sans-serif;
            }
            h2 {
                color: #ffb3b3; /* Brightened red for better readability */
            }
            .form-group label {
                font-weight: bold;
                color: #f5f5f5; /* Brighter label color */
            }
            .btn-custom {
                background-color: #ff8787;
                color: #ffffff;
                border: none;
                transition: background-color 0.3s;
            }
            .btn-custom:hover {
                background-color: #ff5a5a;
            }
            .table-striped tbody tr:nth-of-type(odd) {
                background-color: #2b2b4a;
            }
            .table-striped tbody tr:nth-of-type(even) {
                background-color: #333354;
            }
            .table thead th {
                background-color: #16213e;
                color: #f5f5f5; /* Brightened heading text */
            }
            .table td, .table th {
                color: #f5f5f5; /* Bright white text for table cells */
            }
            .form-control {
                background-color: #393e46;
                color: #f5f5f5;
                border: 1px solid #4e4e50;
            }
            .form-control:focus {
                border-color: #ff8787;
                box-shadow: none;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="text-center mb-4">Filter Employees</h2>
            <form action="filter" method="GET" class="mb-4">
                <div class="form-group">
                    <label for="id">Id:</label>
                    <input type="text" class="form-control" name="id" value="${param.id}"/>
                </div>
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" name="name" value="${param.name}"/>
                </div>
                <div class="form-group">
                    <label>Gender:</label><br/>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" ${param.gender eq "male"?"checked":""} name="gender" value="male"/>
                        <label class="form-check-label">Male</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" ${param.gender eq "female"?"checked":""} name="gender" value="female"/>
                        <label class="form-check-label">Female</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" ${param.gender eq null || param.gender eq "both"?"checked":""} value="both"/>
                        <label class="form-check-label">Both</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="dob">Dob - From:</label>
                    <input type="date" class="form-control" name="from" value="${param.from}"/> 
                    <label for="dob">To:</label>
                    <input type="date" class="form-control" name="to" value="${param.to}"/>
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" class="form-control" name="address" value="${param.address}"/>
                </div>
                <div class="form-group">
                    <label for="d_id">Department:</label>
                    <select class="form-control" name="d_id">
                        <option value="-1">----ALL----</option>
                        <c:forEach items="${requestScope.depts}" var="d">
                            <option ${param.d_id ne null && d.id eq param.d_id?"selected":""} value="${d.id}">${d.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-custom">Search</button>
            </form>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Gender</th>
                        <th>Dob</th>
                        <th>Address</th>
                        <th>Department</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.emps}" var="e">
                        <tr>
                            <td>${e.id}</td>
                            <td>${e.name}</td>
                            <td>${e.gender ? "Male" : "Female"}</td>
                            <td>${e.dob}</td>
                            <td>${e.address}</td>
                            <td>${e.dept.name}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
