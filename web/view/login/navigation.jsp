<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Navigation</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
                justify-content: space-between;
                align-items: center;
                padding: 15px 30px;
                background-color: #16213e;
            }
            .logo {
                font-size: 28px;
                font-weight: bold;
                color: #e94560;
            }
            .nav-buttons {
                display: flex;
                align-items: center;
            }
            .nav-buttons input {
                margin-left: 15px;
                background-color: #e94560;
                border: none;
                color: white;
                padding: 12px 24px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .nav-buttons input:hover {
                background-color: #d6336c;
            }
            .dropdown {
                position: relative;
                display: inline-block;
                margin-left: 15px;
            }
            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #16213e;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
                border-radius: 5px;
            }
            .dropdown:hover .dropdown-content {
                display: block;
            }
            .dropdown-content a {
                color: white;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }
            .dropdown-content a:hover {
                background-color: #3e4c59;
            }
            .container {
                text-align: center;
                padding: 40px 20px;
                flex: 1;
            }
            .section {
                margin-bottom: 40px;
            }
            .section h3 {
                color: #e94560;
                margin-bottom: 20px;
            }
            .section input[type="button"] {
                margin: 10px;
                background-color: #e94560;
                border: none;
                color: white;
                padding: 12px 24px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .section input[type="button"]:hover {
                background-color: #d6336c;
            }
            .footer {
                background-color: #16213e;
                padding: 15px 0;
                text-align: center;
            }
            .footer p {
                margin: 5px 0;
                color: #e0e0e0;
            }
        </style>
    </head>
    <body>
        <header class="header">
            <div class="logo">Company A</div>
            <div class="nav-buttons">
                <c:choose>
                    <c:when test="${not empty sessionScope.account}">
                        <div class="dropdown">
                            <input type="button" value="${sessionScope.account.displayname}">
                            <div class="dropdown-content">
                                <a href="#">Email: ${sessionScope.account.gmail}</a>
                                <a href="#">Phone: ${sessionScope.account.phone}</a>
                            </div>
                        </div>
                        <input type="button" value="Logout" onclick="window.location.href = '<%= request.getContextPath() %>/logout'">
                    </c:when>
                    <c:otherwise>
                        <input type="button" value="Login" onclick="window.location.href = '<%= request.getContextPath() %>/login'">
                    </c:otherwise>
                </c:choose>
            </div>
        </header>
        <div class="container">
            <div class="section">
                <h3>Employee</h3>
                <input type="button" value="Create Employee" onclick="window.location.href = '<%= request.getContextPath() %>/employee/create'">
                <input type="button" value="Filter Employees" onclick="window.location.href = '<%= request.getContextPath() %>/employee/filter'">
                <input type="button" value="List of Employees" onclick="window.location.href = '<%= request.getContextPath() %>/employee/list'">
            </div>
            <div class="section">
                <h3>Production Plan</h3>
                <input type="button" value="Create Production Plan" onclick="window.location.href = '<%= request.getContextPath() %>/productionplan/create'">
                <input type="button" value="Production Plan List" onclick="window.location.href = '<%= request.getContextPath() %>/productionplan/list'">
            </div>
            <div class="section">
                <h3>Schedule Campaign</h3>
                <input type="button" value="Create Campaign Schedule" onclick="window.location.href = '<%= request.getContextPath() %>/schedule/create'">
                <input type="button" value="List Campaign Schedule" onclick="window.location.href = '<%= request.getContextPath() %>/schedule/list'">
            </div>
        </div>
        <footer class="footer">
            <p>Contact Information: company_A@email.com | Phone: 
                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
                </body>
                </html>
