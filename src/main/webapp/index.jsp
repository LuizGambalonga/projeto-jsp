<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">

<title>Curso JSP</title>

<style>
.container {
	position: relative;
	display: flex;
	flex-direction: row;
	height: 100%;
	justify-content: center;
	align-content: center;
	margin-top: 270px;
}

.-form {
	width: 100%;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
}

.-msg {
	text-align: center;
}

.-btn {
	background-color: blue;
	color: white;
	height: 55px;
}
</style>
</head>
<body>
	<div class="container">
		<form action="ServletLogin" method="post" class="row g-3 needs-validation -form">
			<input type="hidden" name="url"
				value="<%=request.getParameter("url")%>">

			<div class="col-md-4">
				<label class="form-label">Login:</label> <input class="form-control"
					name="login" type="text" required>
				<div class="valid-feedback">Informe Login!</div>
			</div>
			<div class="col-md-4">
				<label class="form-label">Senha:</label> <input class="form-control"
					name="senha" type="password" required>
			</div>
			<div class="valid-feedback">Informe Senha</div>
			<div class="col-md-8">
				<input class="form-control -btn" type="submit" value="Acessar">
			</div>
			<div class="col-md-12 -msg">
				<h5>${msg}</h5>
			</div>
		</form>
	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous">
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>
</body>
</html>