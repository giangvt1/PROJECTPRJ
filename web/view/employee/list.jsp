<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of Employees</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        body {
            background-color: #1a1a2e;
            color: #e0e0e0;
            font-family: 'Arial', sans-serif;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
        }
        .header {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 15px 30px;
            background-color: #16213e;
            color: #e94560;
            font-size: 28px;
            font-weight: bold;
        }
        .container {
            padding: 40px 20px;
            flex: 1;
        }
        h1 {
            color: #e94560;
            margin-bottom: 30px;
        }
        .table {
            background-color: #222831;
            color: #e0e0e0;
        }
        .table thead th {
            background-color: #393e46;
            color: #e0e0e0;
        }
        .table tbody tr {
            background-color: #222831;
            color: #e0e0e0;
        }
        .btn-warning {
            background-color: #e94560;
            border: none;
            transition: background-color 0.3s;
        }
        .btn-warning:hover {
            background-color: #d6336c;
        }
        .btn-danger {
            background-color: #d6336c;
            border: none;
            transition: background-color 0.3s;
        }
        .btn-danger:hover {
            background-color: #b82b55;
        }
        .back-button {
            position: absolute;
            left: 20px;
            top: 20px;
            background-color: #e94560;
            border: none;
            color: white;
            padding: 6px 12px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s;
        }
        .back-button:hover {
            background-color: #d6336c;
        }
    </style>
    <script>
        function deleteEmployee(id) {
            var result = confirm("Are you sure?");
            if (result) {
                document.getElementById("formDelete" + id).submit();
            }
        }
    </script>
</head>
<body>
    <button type="button" class="back-button" onclick="window.location.href = '<%= request.getContextPath() %>/view/login/navigation.jsp'">Back</button>
    <header class="header">
        List of Employees
    </header>
    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Dob</th>
                    <th>Address</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="e" items="${emps}">
                    <tr>
                        <td>${e.id}</td>
                        <td>${e.name}</td>
                        <td>${e.gender ? "Male" : "Female"}</td>
                        <td>${e.dob}</td>
                        <td>${e.address}</td>
                        <td>
                            <a class="btn btn-warning btn-sm" href="update?id=${e.id}">Edit</a>
                            <input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="deleteEmployee(${e.id})"/>
                            <form action="delete" method="POST" id="formDelete${e.id}" style="display:inline;">
                                <input type="hidden" name="id" value="${e.id}"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
