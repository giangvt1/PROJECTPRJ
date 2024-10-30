<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Employee</title>
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
            justify-content: center;
            align-items: center;
            padding: 15px 30px;
            background-color: #16213e;
            color: #e94560;
            font-size: 28px;
            font-weight: bold;
            position: relative;
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
        .container {
            text-align: center;
            padding: 40px 20px;
            flex: 1;
        }
        .container h2 {
            color: #e94560;
            margin-bottom: 30px;
        }
        .card {
            background-color: #222831;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
            padding: 20px;
            color: #e0e0e0;
            max-width: 600px;
            margin: auto;
        }
        .form-control {
            background-color: #393e46;
            color: #eeeeee;
            border: 1px solid #4e4e50;
        }
        .form-control:focus {
            border-color: #e94560;
            box-shadow: none;
        }
        .btn-primary {
            background-color: #e94560;
            border: none;
            color: white;
            padding: 12px 24px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            margin: 10px 0;
        }
        .btn-primary:hover {
            background-color: #d6336c;
        }
        .footer {
            background-color: #16213e;
            padding: 15px 0;
            text-align: center;
            color: #e0e0e0;
        }
    </style>
</head>
<body>
    <header class="header">
        <button type="button" class="back-button" onclick="window.location.href = '<%= request.getContextPath() %>/view/login/navigation.jsp'">Back</button>
        Company A
    </header>
    <div class="container">
        <h2>Create New Employee</h2>
        <div class="card">
            <form action="create" method="POST">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" name="name" required/>
                </div>
                <div class="form-group">
                    <label>Gender:</label><br/>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" value="male" required/>
                        <label class="form-check-label">Male</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" value="female"/>
                        <label class="form-check-label">Female</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="dob">Date of Birth:</label>
                    <input type="date" class="form-control" name="dob" required/>
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" class="form-control" name="address" required/>
                </div>
                <div class="form-group">
                    <label for="d_id">Department:</label>
                    <select class="form-control" name="d_id" required>
                        <c:forEach items="${requestScope.depts}" var="d">
                            <option value="${d.id}">${d.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Create</button>
            </form>
        </div>
    </div>
    <footer class="footer">
        <p>Contact Information: company_A@email.com | Phone: 0147896352</p>
    </footer>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
