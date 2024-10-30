<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
              integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet"/>
        <style>
            body {
                background-color: #07435F;
                color: white;
                font-family: Arial, sans-serif;
            }
            .container {
                max-width: 400px;
                margin-top: 100px;
                padding: 20px;
                background-color: #1E2A38;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            }
            .form h2 {
                text-align: center;
                margin-bottom: 20px;
            }
            .inputBox {
                position: relative;
                margin-bottom: 20px;
            }
            .inputBox input {
                padding: 10px;
                border: 1px solid #4CAF50;
                border-radius: 4px;
                background: #3E4C59;
                color: white;
                transition: border-color 0.3s;
            }
            .inputBox input:focus {
                border-color: #4CAF50;
                outline: none;
            }
            .inputBox i {
                position: absolute;
                top: 10px;
                left: 10px;
                color: #ccc;
            }
            .inputBox span {
                position: absolute;
                top: 50%;
                left: 40px;
                transform: translateY(-50%);
                color: #aaa;
            }
            .inputBox input[type="submit"] {
                background: #4CAF50;
                cursor: pointer;
                border: none;
                color: white;
                border-radius: 4px;
            }
            .inputBox input[type="submit"]:hover {
                background: #45a049;
            }
            p {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <form action="<%= request.getContextPath() %>/register" method="POST">
                <div class="form register">
                    <h2>Register</h2>
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger" role="alert">${errorMessage}</div>
                    </c:if>
                    <div class="form-group inputBox">
                        <input type="text" name="username" class="form-control" required="required" placeholder="Username">
                        <i class="fa-regular fa-user"></i>
                    </div>
                    <div class="form-group inputBox">
                        <input type="password" name="password" class="form-control" required="required" placeholder="Password">
                        <i class="fa-solid fa-lock"></i>
                    </div>
                    <div class="form-group inputBox">
                        <input type="text" name="displayname" class="form-control" required="required" placeholder="Display Name">
                        <i class="fa-regular fa-user"></i>
                    </div>
                    <div class="form-group inputBox">
                        <input type="email" name="email" class="form-control" required="required" placeholder="Email">
                        <i class="fa-solid fa-envelope"></i>
                    </div>
                    <div class="form-group inputBox">
                        <input type="text" name="phone" class="form-control" required="required" placeholder="Phone">
                        <i class="fa-solid fa-phone"></i>
                    </div>
                    <div class="form-group">
                        <input type="submit" value="Register" class="btn btn-primary btn-block">
                    </div>
                    <p>Already have an account? <a href="<%= request.getContextPath() %>/login.jsp" class="login">Sign In</a></p>
                </div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
