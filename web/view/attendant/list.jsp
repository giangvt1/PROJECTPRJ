<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Attendant List</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #343a40; /* Dark background */
                color: #ffffff;
                display: flex;
                flex-direction: column;
                min-height: 100vh;
            }
            .table thead th {
                background-color: #e94560; /* Accent color for header */
                color: #ffffff;
            }
            .table tbody tr {
                background-color: #6c757d; /* Light grey rows */
                color: #ffffff;
            }
            .table tbody tr:hover {
                background-color: #495057; /* Darker on hover */
            }
            .btn-back {
                background-color: #ff6b6b;
                color: #ffffff;
                position: absolute;
                left: 15px;
                top: 15px;
            }
            .header {
                background-color: #23272a;
                padding: 15px;
                text-align: center;
                color: #ffffff;
            }
            h2 {
                color: #e94560; /* Accent color for title */
            }
            .container {
                flex: 1;
            }
            .footer {
                background-color: #23272a;
                padding: 15px;
                text-align: center;
                color: #ffffff;
                position: relative;
                bottom: 0;
                width: 100%;
            }
        </style>
    </head>
    <body>
        <!-- Back Button -->
        <a href="<%= request.getContextPath() %>/view/login/navigation.jsp" class="btn btn-back">Back</a>

        <!-- Header -->
        <div class="header">
            <h2>Attendant List</h2>
        </div>

        <!-- Table -->
        <div class="container mt-5">
            <h2 class="text-center mb-4">List of Attendants</h2>
            <table class="table table-hover mt-4">
                <thead>
                    <tr>
                        <th>Employee ID</th>
                        <th>Employee Name</th>
                        <th>Date</th>
                        <th>Actual Quantity</th>
                        <th>Ordered Quantity</th>
                        <th>Alpha</th>
                        <th>Salary</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="attendant" items="${attendant}">
                        <c:if test="${not empty attendant.ws and not empty attendant.ws.e}">
                            <tr>
                                <td>${attendant.ws.e.id}</td> <!-- Employee ID -->
                                <td>${attendant.ws.e.name}</td> <!-- Employee Name -->
                                <td>${attendant.ws.sc.date}</td> <!-- Schedule Campaign Date -->
                                <td>${attendant.quantity}</td> <!-- Attendant Quantity -->
                                <td>${attendant.ws.sc.quantity}</td> <!-- Schedule Campaign Quantity -->
                                <td>${attendant.alpha}</td> <!-- Alpha -->
                                <td>${attendant.alpha * 8 * 8000 * 30} VND</td> <!-- Calculated Salary -->
                            </tr>
                        </c:if>
                    </c:forEach>
                    <c:if test="${empty attendant}">
                        <tr>
                            <td colspan="7" class="text-center">No attendants available</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>

        <!-- Footer -->
        <div class="footer">
            <p>&copy; 2024 Production Management System</p>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
