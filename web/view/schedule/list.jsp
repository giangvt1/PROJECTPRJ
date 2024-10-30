<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Schedule Campaign List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #1a1a2e;
            color: #e0e0e0;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .container {
            flex: 1;
            padding: 20px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .table {
            margin-top: 20px;
            background-color: #16213e;
            color: white;
            border-radius: 8px;
            overflow: hidden;
        }
        .table th, .table td {
            padding: 15px;
            text-align: center;
        }
        .table th {
            background-color: #e94560;
        }
        .table tbody tr:nth-child(odd) {
            background-color: #1e2d39;
        }
        .table tbody tr:nth-child(even) {
            background-color: #16213e;
        }
        .footer {
            background-color: #16213e;
            padding: 10px;
            text-align: center;
            color: #e0e0e0;
        }
        .btn-back {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
            position: absolute;
            top: 10px;
            left: 10px;
            border: 2px solid #e0e0e0;
            font-weight: bold;
        }
        .btn-back:hover {
            background-color: #45a049;
            border-color: #ffffff;
        }
    </style>
</head>
<body>
    <a href="<%= request.getContextPath() %>/view/login/navigation.jsp" class="btn-back">Back</a>
    <div class="container">
        <h2 class="text-center">Schedule Campaign List</h2>
        <form action="<%= request.getContextPath() %>/schedule/list" method="POST" class="text-center">
            <div class="form-group">
                <label for="planId">Enter Plan ID:</label>
                <input type="text" id="planId" name="planId" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        <c:if test="${not empty campaigns}">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Plan ID</th>
                        <th>Date</th>
                        <th>Shift</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="campaign" items="${campaigns}">
                        <tr>
                            <td>${campaign.id}</td>
                            <td>${campaign.date}</td>
                            <td>${campaign.shift}</td>
                            <td>${campaign.pros.name}</td>
                            <td>${campaign.quantity}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
    <footer class="footer">
        <p>&copy; 2024 Company Name. All rights 
    </footer>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>


