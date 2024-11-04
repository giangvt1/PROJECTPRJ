<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Production Plans</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #343a40; /* Dark background */
                color: #ffffff;
            }
            .table thead th {
                background-color: #495057; /* Darker header */
                color: #ffffff;
            }
            .table tbody tr {
                background-color: #6c757d; /* Light grey rows */
                color: #ffffff;
            }
            .table tbody tr:hover {
                background-color: #495057; /* Darker on hover */
            }
            .btn-custom {
                background-color: #e94560; /* Custom button color */
                color: #ffffff;
            }
            .btn-custom:hover {
                background-color: #dc3545; /* Lighter shade on hover */
                color: #ffffff;
            }
            h1 {
                color: #e94560; /* Accent color */
            }
            .btn-back {
                background-color: #ff6b6b;
                color: #ffffff;
                position: absolute;
                left: 15px;
                top: 15px;
            }
            .header, .footer {
                background-color: #23272a;
                padding: 15px;
                text-align: center;
                color: #ffffff;
            }
        </style>
    </head>
    <body>
        <!-- Success Alert (if creation was successful) -->
        <c:if test="${not empty sessionScope.createSuccess}">
            <script>
                alert("Create successful!");
            </script>
            <!-- Remove the success attribute from session to prevent repeat alerts -->
            <c:remove var="createSuccess" scope="session" />
        </c:if>
        <div class="header">
            <h2>Lists of Production Plan</h2>
        </div>
        <a href="<%= request.getContextPath() %>/view/login/navigation.jsp" class="btn btn-back">Back</a>

        <div class="container mt-5">
            <h1 class="text-center mb-4">Production Plans</h1>
            <table class="table table-hover mt-4">
                <thead>
                    <tr>
                        <th>Plan ID</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Product Name</th>
                        <th>Estimated Effort</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${plans}" var="plan">
                        <c:if test="${not empty plan.campaigns}">
                            <c:forEach items="${plan.campaigns}" var="campaign">
                                <tr>
                                    <td>${plan.id}</td>
                                    <td>${plan.start}</td>
                                    <td>${plan.end}</td>
                                    <td>${campaign.product.name}</td>
                                    <td>${campaign.estimatedeffort}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty plan.campaigns}">
                            <tr>
                                <td>${plan.id}</td>
                                <td>${plan.start}</td>
                                <td>${plan.end}</td>
                                <td colspan="2" class="text-center">No campaigns available</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    <c:if test="${empty plans}">
                        <tr>
                            <td colspan="5" class="text-center">No plans available</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
            <div class="text-center">
                <a href="create" class="btn btn-custom">Create New Plan</a>
            </div>
        </div>
        <div class="footer mt-4">
            <p>&copy; 2024 Production Management System</p>
        </div>
        <!-- Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
