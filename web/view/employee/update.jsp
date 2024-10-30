<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Employee</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #343a40; /* Dark background */
            color: #ffffff;
        }
        .container {
            margin-top: 50px;
            background-color: #495057; /* Container background */
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        }
        h2 {
            color: #e94560; /* Accent color for headings */
        }
        .form-control {
            background-color: #6c757d; /* Input background */
            color: #ffffff;
            border: 1px solid #ced4da; /* Input border */
        }
        .form-control:focus {
            background-color: #5a6268; /* Darker input background on focus */
            color: #ffffff;
            border-color: #e94560; /* Change border color on focus */
        }
        .btn-primary {
            background-color: #e94560; /* Custom button color */
            border-color: #e94560; /* Button border color */
        }
        .btn-primary:hover {
            background-color: #dc3545; /* Lighter shade on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center">Update Employee Information</h2>
        <form action="update" method="POST">
            <input type="hidden" name="id" value="${requestScope.employee.id}" />

            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" name="name" value="${requestScope.employee.name}" required/>
            </div>
            <div class="form-group">
                <label for="dob">Date of Birth:</label>
                <input type="date" class="form-control" name="dob" value="${requestScope.employee.dob}" required/>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" class="form-control" name="address" value="${requestScope.employee.address}" required/>
            </div>
            <div class="form-group">
                <label>Gender:</label>
                <div class="form-check">
                    <input type="radio" class="form-check-input" name="gender" value="male" id="male"
                           ${requestScope.employee.gender ? "checked" : ""}/>
                    <label class="form-check-label" for="male">Male</label>
                </div>
                <div class="form-check">
                    <input type="radio" class="form-check-input" name="gender" value="female" id="female"
                           ${!requestScope.employee.gender ? "checked" : ""}/>
                    <label class="form-check-label" for="female">Female</label>
                </div>
            </div>
            <div class="form-group">
                <label for="d_id">Department:</label>
                <select class="form-control" name="d_id" required>
                    <c:forEach items="${requestScope.depts}" var="d">
                        <option value="${d.id}" ${requestScope.employee.dept.id == d.id ? "selected" : ""}>
                            ${d.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
