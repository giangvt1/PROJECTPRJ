<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        body {
            background: linear-gradient(145deg, #04364f, #0b5e87);
            color: white;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            max-width: 400px;
            padding: 30px;
            background-color: rgba(20, 35, 50, 0.95);
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.6);
        }
        .form h2 {
            text-align: center;
            margin-bottom: 25px;
            font-size: 26px;
            color: #4CAF50;
            font-weight: bold;
        }
        .inputBox {
            position: relative;
            margin-bottom: 25px;
        }
        .inputBox input {
            width: 100%;
            padding: 12px 12px 12px 40px;
            border: none;
            border-radius: 6px;
            background: #2d3e50;
            color: white;
            font-size: 16px;
            transition: background-color 0.3s, transform 0.2s;
        }
        .inputBox input:focus {
            background-color: #3a5069;
            outline: none;
            transform: scale(1.02);
        }
        .inputBox i {
            position: absolute;
            top: 50%;
            left: 12px;
            transform: translateY(-50%);
            color: #b0b0b0;
        }
        .inputBox input[type="submit"] {
            background: #4CAF50;
            color: white;
            cursor: pointer;
            font-size: 18px;
            transition: background-color 0.3s, transform 0.2s;
        }
        .inputBox input[type="submit"]:hover {
            background: #45a049;
            transform: scale(1.05);
        }
        p {
            text-align: center;
            margin-top: 20px;
            color: #d1d1d1;
        }
        p a {
            color: #4CAF50;
            text-decoration: none;
        }
        p a:hover {
            text-decoration: underline;
        }
        .alert {
            text-align: center;
            color: #ff4c4c;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="<%= request.getContextPath() %>/login" method="POST">
            <div class="form signin">
                <h2>Sign In</h2>
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
                    <input type="submit" value="Login" class="btn btn-primary btn-block">
                </div>
                <p>Not Registered? <a href="<%= request.getContextPath() %>/view/login/registration.jsp" class="create">Create an account</a></p>
            </div>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
