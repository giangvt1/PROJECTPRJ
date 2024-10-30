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
                background: linear-gradient(135deg, #07435F, #0A5275);
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
                padding: 20px;
                background-color: rgba(30, 42, 56, 0.9);
                border-radius: 8px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
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
                width: 100%;
                padding: 10px 10px 10px 40px;
                border: none;
                border-radius: 4px;
                background: #3E4C59;
                color: white;
                transition: background-color 0.3s;
            }
            .inputBox input:focus {
                background-color: #32414A;
                outline: none;
            }
            .inputBox i {
                position: absolute;
                top: 10px;
                left: 10px;
                color: #ccc;
            }
            .inputBox input[type="submit"] {
                background: #4CAF50;
                cursor: pointer;
                border: none;
                color: white;
                transition: background-color 0.3s;
            }
            .inputBox input[type="submit"]:hover {
                background: #45a049;
            }
            p {
                text-align: center;
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
