<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Production Plan</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #2c2f33;
            color: #ffffff;
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
    </style>
</head>
<body>
    <!-- Header -->
    <div class="header">
        <h2>Production Management System</h2>
    </div>

    <!-- Back Button -->
    <a href="previousPageURL" class="btn btn-back">Back</a>

    <!-- Main Content -->
    <div class="container mt-4">
        <h2>Create Production Plan</h2>
        <form id="productionPlanForm" action="<%= request.getContextPath() %>/productionplan/create" method="POST">
            <div class="form-group">
                <label for="from">Start Date:</label>
                <input type="date" class="form-control" id="from" name="from" required>
            </div>
            <div class="form-group">
                <label for="to">End Date:</label>
                <input type="date" class="form-control" id="to" name="to" required>
            </div>
            <div class="form-group">
                <label for="d_id">Department:</label>
                <select class="form-control" id="d_id" name="d_id" required>
                    <c:forEach var="dept" items="${depts}">
                        <option value="${dept.id}">${dept.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>Products:</label>
                <c:forEach var="product" items="${products}">
                    <div class="form-row align-items-center mb-2">
                        <div class="col-auto">
                            <input type="checkbox" name="pro_id" value="${product.id}" id="product${product.id}" class="form-check-input product-checkbox">
                            <label for="product${product.id}" class="form-check-label">${product.name}</label>
                        </div>
                        <div class="col">
                            <input type="number" name="quantity${product.id}" placeholder="Quantity" class="form-control quantity-input" min="0" disabled>
                        </div>
                        <div class="col">
                            <input type="number" name="effort${product.id}" placeholder="Estimated Effort" class="form-control effort-input" min="0" step="0.01" disabled>
                        </div>
                    </div>
                </c:forEach>
                <div id="productError" class="text-danger" style="display:none;">Please select at least one product.</div>
            </div>
            <button type="submit" class="btn btn-primary">Create Plan</button>
        </form>
    </div>

    <!-- Footer -->
    <div class="footer mt-4">
        <p>&copy; 2024 Production Management System</p>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        // JavaScript to enable or disable quantity and effort inputs based on checkbox selection
        document.querySelectorAll('.product-checkbox').forEach(function (checkbox) {
            checkbox.addEventListener('change', function () {
                const isChecked = this.checked;
                const quantityInput = this.closest('.form-row').querySelector('.quantity-input');
                const effortInput = this.closest('.form-row').querySelector('.effort-input');
                quantityInput.disabled = !isChecked;
                effortInput.disabled = !isChecked;
            });
        });

        // Client-side validation for product selection
        document.getElementById('productionPlanForm').addEventListener('submit', function (event) {
            const productCheckboxes = document.querySelectorAll('.product-checkbox');
            const isAnyProductSelected = Array.from(productCheckboxes).some(checkbox => checkbox.checked);

            if (!isAnyProductSelected) {
                event.preventDefault();  // Prevent form submission
                document.getElementById('productError').style.display = 'block';
            } else {
                document.getElementById('productError').style.display = 'none';
            }
        });
    </script>
</body>
</html>
