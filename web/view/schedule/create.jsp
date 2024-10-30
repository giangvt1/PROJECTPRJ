<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Schedule Campaign</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #2c2f33;
            color: #ffffff;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
        }
        .header, .footer {
            background-color: #23272a;
            padding: 15px;
            text-align: center;
            color: #ffffff;
        }
        .btn-back {
            background-color: #ff6b6b;
            color: #ffffff;
            position: absolute;
            left: 15px;
            top: 15px;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 5px;
        }
        .btn-back:hover {
            background-color: #e64545;
        }
        .form-group label {
            color: #ffffff;
        }
        .btn-primary {
            background-color: #4CAF50;
            border: none;
        }
        .btn-primary:hover {
            background-color: #45a049;
        }
        .container {
            flex: 1;
            padding: 20px;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <div class="header">
        <h2>Create a new Schedule Campaign</h2>
    </div>
    
    <!-- Back Button -->
    <a href="<%= request.getContextPath() %>/view/login/navigation.jsp" class="btn btn-back">Back</a>
    
    <!-- Main Content -->
    <div class="container mt-4">
        <h2>Create Schedule Campaign</h2>
        <form action="${pageContext.request.contextPath}/schedule/create" method="POST">
            <div class="form-group">
                <label for="pc_id">Plan Campaign:</label>
                <select name="pc_id" id="pc_id" class="form-control" required>
                    <c:forEach var="planCampaign" items="${planCampaigns}">
                        <option value="${planCampaign.id}">${planCampaign.product.name} - ${planCampaign.id}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="date">Date:</label>
                <input type="date" id="date" name="date" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="shift">Shift (K):</label>
                <input type="text" id="shift" name="shift" maxlength="1" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" class="form-control" required min="0">
            </div>
            <button type="submit" class="btn btn-primary">Create</button>
        </form>
    </div>
    
    <!-- Footer -->
    <div class="footer">
        <p>&copy; Contact Information: company_A@email.com | Phone: 0147896352</p>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
