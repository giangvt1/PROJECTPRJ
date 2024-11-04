<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Detail Plan</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                background-color: #1a1a2e;
                color: #ffffff; /* Brighter white text */
                font-family: 'Arial', sans-serif;
            }
            .container {
                max-width: 900px;
            }
            h2 {
                color: #ffcccc; /* Brighter pink for readability */
                margin-bottom: 20px;
            }
            .btn-custom {
                background-color: #ff8787;
                color: #ffffff;
                border: none;
                transition: background-color 0.3s;
            }
            .btn-custom:hover {
                background-color: #ff5a5a;
            }
            .table-striped tbody tr:nth-of-type(odd) {
                background-color: #2b2b4a;
            }
            .table-striped tbody tr:nth-of-type(even) {
                background-color: #333354;
            }
            .table thead th {
                background-color: #16213e;
                color: #ffffff; /* Brightened heading text */
            }
            .table td, .table th {
                color: #ffffff; /* Bright white text for table cells */
            }
            .back-btn {
                position: absolute;
                left: 20px;
                top: 20px;
                background-color: #ff8787;
                color: #ffffff;
                padding: 8px 12px;
                border: none;
                border-radius: 4px;
                font-size: 14px;
                transition: background-color 0.3s;
            }
            .back-btn:hover {
                background-color: #ff5a5a;
            }
        </style>
    </head>
    <body>
        <form action="productionplan/detail" method="get">
            <button type="button" class="back-btn" onclick="window.history.back();">Back</button>
            <div class="container mt-5">
                <h2 class="text-center">Detail Plan</h2>
                <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Employee ID</th>
                            <th>Employee Name</th>
                            <th>Product Name</th>
                            <th>Shift</th>
                            <th>Ordered Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="detail" items="${details}">
                            <tr>
                                <td>${detail.e.id}</td>
                                <td>${detail.e.name}</td>
                                <td>${detail.p.name}</td>
                                <td>${detail.sc.shift}</td>
                                <td>${detail.sc.quantity}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
