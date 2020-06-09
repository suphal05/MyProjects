<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>InfyGo</title>

<!--<link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>"
	type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value='/resources/css/newstyle.css'/>"
	type="text/css" media="all"> -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/custom.css'/>"
	type="text/css" media="all">
<link rel="stylesheet"
	href="<c:url value='/resources/css/newstyle.css'/>"
	type="text/css" media="all">

<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<jsp:include page="header2.jsp"></jsp:include>

	<br>
	<br>
	<div class=" panel-group ticketDetails "
		style="width: 70%; margin-left: 200px;">
		<br>
		<div class="panel-body text-center">
			<form:form method="POST">


				<br>
				<div class="panel panel-primary ">
					<div class="panel-heading">
						<h4>Ticket Details</h4>
					</div>
					<div class="panel-body">
						<form:form method="POST">
							<div class="row text-center fontstyle">
								<c:if test="${ paymentMessage ne null}">
									<h3 class="text-center text-success">${paymentMessage}</h3>
								</c:if>
								<c:if test="${ pnrMessage ne null}">
									<h3 class="text-center text-success">${pnrMessage}<span><font
											size="6">${booking.pnr}</font></span>

									</h3>
								</c:if>
								<br> Click <a href="login"> &nbsp;here </a>to login.
							</div>

							<a href="downloadTicket.pdf">Click to download ticket</a>
					</div>
			</form:form>
		</div>
	</div>


	</form:form>
	</div>

	</div>


	<!-- Feedback Form -->
	<div class="panel-group feedbackDetails"
		style="width: 70%; margin-left: 200px">
		<br>
		<div class="panel-body">


			<!-- <div class="panel-group feedbackDetails" > -->
			<br>
			<div class="panel panel-primary">
				<div class="panel-heading text-center">
					<h4>Feedback</h4>
				</div>
				<div class="panel-body">

					<section id="feedback" class="feedback">
						<div class="container">
							<div class="row">
								<div class="col-md-12">
									<div class="section-title">
										<h4 class="capital">Submit Ur Feedback</h4>
										<span class="st-border"></span>
									</div>
								</div>

								<div class="col-sm-7 col-sm-offset-1">
									<form id="feedbackForm" class="feedback-form"
										name="feedback-form" method="post">
										<div class="row">

											<br> Reasons 2 fly? <br> <input
												type="radio" name="business" value="business" checked>
											Business <br> <input type="radio" name="personalReasons"
												value="personalReasons "> Personal Reasons <br>

											<br> How do you book your air tickets? <br> <input
												type="radio" name="direct" value="direct" checked>
											Direct from airlines <br> <input type="radio"
												name="online" value="online "> Travels<br> <br> <br> R U frequent flyer with our airline? <br> <input
												type="radio" name="always1" value="always1" checked>
											Always <br> <input type="radio" name="most1"
												value="most1"> Mostly <br> <input
												type="radio" name="half1" value="half1"> Sometimes <br> <input type="radio" name="once1"
												value="once1"> Only Once <br> <input
												type="radio" name="never1" value="never1"> Not At All <br>
											<br> <br> Is additional fees required to choose your own seat on the plane? <br> <input type="radio"
												name="always2" value="always2" checked> Always <br>
											<input type="radio" name="most2" value="most2"> Mostly <br> <input type="radio" name="half2"
												value="half2"> Sometimes <br> <input
												type="radio" name="once2" value="once2"> Once in a
											while <br> <input type="radio" name="never2"
												value="never2"> Not At All <br> <br> <br>
											Is InfyGO airlines your most favorite? <br>
											<input type="radio" name="yes" value="yes" checked>
											Yes <br> <input type="radio" name="no" value="no ">
											No <br> <br>

											<div class="col-sm-12">
												<input type="submit" name="submit" value="Submit Feedback"
													class="btn btn-send">
											</div>

										</div>
									</form>
								</div>
							</div>
						</div>
					</section>

				</div>
			</div>



		</div>

	</div>
	<!-- Feedback Form -->
	</div>


	<!-- JS -->
	<script type="text/javascript"
		src="<c:url value='/WEB-INF/js/jquery.min.js'/>"></script>
	<!-- jQuery -->
	<script type="text/javascript"
		src="<c:url value='/WEB-INF/js/jquery.ajax-cross-origin.min.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value='/WEB-INF/js/bootstrap.min.js'/>"></script>
	<!-- Bootstrap -->
	<script>
		var $feedbackForm = $('#feedbackForm');

		$feedbackForm
				.on(
						'submit',
						function(event) {
							event.preventDefault();
							bootbox
									.alert("Thanks for contacting us!! Our team will connect to you soon.");
							$feedbackForm.get(0).reset()
							
						});
		
	</script>
	<script type="text/javascript"
		src="<c:url value='/WEB-INF/js/custom.js'/>"></script>
	<!-- custom -->

	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
