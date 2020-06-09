<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta>

<title>Airline Reservation System</title>

<!-- Main CSS file -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/newstyle.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/responsive.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>"
	type="text/css" media="all" />
<!-- Fonts -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
	rel="stylesheet" type="text/css">

<!-- Favicon -->
<link rel="shortcut icon" href="/resources/travel/InfyGo.ico">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
</head>
<body data-spy="scroll" data-target="#footer">


	<!-- HEADER -->
	<header id="header">


		<nav class="navbar st-navbar navbar-fixed-top">

			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#st-navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="logo1" href=""><img
						src="<c:url value='/resources/images/infygonew.PNG'/>" alt=""></a>
						 
				</div>

				<div class="collapse navbar-collapse" id="st-navbar-collapse">
					<div class="">
						<ul class="nav navbar-nav nav-links">

							<li><a href="#header">Home</a></li>
							<li><a href="#about-us">About Us</a></li>
							<li><a href="#contact">Contact Us</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right margintop-5">

							<li><a class="btn btn-xs  nav-links-right"
								href="${pageContext.servletContext.contextPath}/register"> <span class="glyphicon glyphicon-user"></span>&nbsp;Sign
									Up
							</a></li>
							<li><a class="btn btn-xs .nav-links-right" href="${pageContext.servletContext.contextPath}/login">
									<span class="glyphicon glyphicon-log-in"></span>&nbsp;Login
							</a></li>


						</ul>
					</div>

				</div>
			</div>
			<!-- /.navbar-collapse -->
		</nav>

	</header>
	<!-- /HEADER -->
	
	<!-- SLIDER -->
	<section id="slider" class="margintop-10">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font class="fontstyle"><b>IRS with Angular and Spring MVC</b></font>
		<div id="home-carousel" class="carousel slide" data-ride="carousel">
			<div class="carousel-inner">
			<c:if test="${logoutMessage ne null }">
        			 <div class="text-center text-danger"><h4 class="fontstyle"><strong>${logoutMessage}</strong></h4></div>  <br>
      				     
   				</c:if>
   				
				
				<div class="item active" style="background-image: url(<c:url value='/resources/imgs/382266112.jpg'/>)">
					<br><br>
					<div class="container">
						<div class="col-sm-6">
								<p class="head " style="color:white">Connecting flight</p>
								<p class="carousel-content  " style="color:white">Taking more than one flight to
									get to your final destination? At InfyGo, we make connecting
									flights much easier!</p>
						</div>
					</div>					
				</div> 
				<div class="item" style="background-image: url(<c:url value='/resources/imgs/586266182.jpg'/>)">
					<br><br>
					<div class="container">
						<div class="col-sm-6">
								<p class="head " style="color:white">InfyGo Meals onboard</p>
								<p class="carousel-content " style="color:white">Tickle your taste buds and enjoy
									sumptuous meals onboard!</p>
						</div>
					</div>					
				</div> 
			
			<div class="item" style="background-image: url(<c:url value='/resources/imgs/346909778.jpg'/>)">
					<br><br>
					<div class="container">
						<div class="col-sm-6">
								<p class="head " style="color:white">Hospitality</p>
								<p class="carousel-content " style="color:white">More convenience and comfort
									with our hospitality and services!</p>
						</div>
					</div>					
				</div> 
				

			</div>
			<ol class="carousel-indicators">
				<li data-target="#home-carousel" data-slide-to="0" class="active"></li>
				<li data-target="#home-carousel" data-slide-to="1"></li>
				<li data-target="#home-carousel" data-slide-to="2"></li>
			</ol>
		</div>
		<!--/-carousel-->

	</section>
	<!-- /SLIDER -->


	<!-- ABOUT US -->
	<section id="about-us">
		<div class="container">
			<div class="row">
				<div class="col-sm-12 ">
					<h1 class="capital">
						<strong>About Us</strong>
					</h1>
					<span class="st-border"></span>
					<div class="about-us ">
						<br>
						<p>
							<b>Why InfyGo?<br></b> InfyGo is one of the leading player in online Air bookings in 
								India which provides great offers like lowest airfares with exclusive discounts for regular 
								customers with a hassel free online booking experience. InfyGo is user friendly and its 
								easy to complete your flight booking with just a few clicks away!
						</p>
						<br>

						<p>
							<b>Book Flights with InfyGo<br></b>With InfyGO you get an ultimate online booking 
							experience with premium customer service by providing dedicated helpline support 24/7 
							with over 2 million delighted customers. InfyGo takes pride by enabling customer 
							satisfaction!					</p>
						<br>

						<p>
							<b>Book Domestic Flights with InfyGo<br></b> InfyGo has a dominant position in the 
							domestic flights sector by providing the cheapest fare and have a great experience by 
							providing the tickets at lowest price.
						</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- /ABOUT US -->

	<!-- CONTACT -->
	<section id="contact" class="contact">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="section-title">
						<h1 class="capital">
							<strong>Contact us</strong>
						</h1>
						<span class="st-border"></span>
					</div>
				</div>
				<div class="col-sm-4 contact-info">
					<!-- <p class="contact-content">MService.</p>-->
					<br>
					<p class="st-address">
						<i class="fa fa-map-marker"></i> Airport Sales Counter No-01,
						Bengaluru International Airport,Devanhalli, Bengaluru 560300
					</p>
					<p class="st-phone">
						<i class="fa fa-mobile"></i> 09949596999
					</p>
					<p class="st-email">
						<i class="fa fa-envelope-o"></i> InfyGo@infosys.com
					</p>
					<p class="st-website">
						<i class="fa fa-globe"></i> www.InfyGo.com
					</p>

				</div>
				<div class="col-sm-7 col-sm-offset-1">
					<form id="contactForm" class="contact-form" name="contact-form"
						method="post" >
						<div class="row">
							<div class="col-sm-6">
								<input type="text" name="name" required="required"
									placeholder="Name*" >
							</div>
							<div class="col-sm-6">
								<input type="email" name="email" required="required"
									placeholder="Email*" >
							</div>
							<div class="col-sm-6">
								<input type="text" name="mobile" required="required"
									placeholder="Mobile*" pattern="[789][0-9]{9}">
							</div>
							<div class="col-sm-6">
								<datalist id="fdlist">
									<option>Feedback</option>
									<option>Complaint</option>
								</datalist>
								<input type="text" list="fdlist" name="casestype"
									placeholder="Case type:feedback/complaint*" required >


							</div>
							<div class="col-sm-12">
								<textarea name="message" required="required" cols="30" rows="7"
									placeholder="Message*" ></textarea>
							</div>
							<div class="col-sm-12">
								<input type="submit" name="submit" value="Send Message"
									class="btn btn-send"/>
									<br>
									<br>
									<br>
									<br>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<!-- /CONTACT -->



	<!-- FOOTER -->
	<%-- <footer id="footer">
		<div class="container">


			<div class="row">
				<div class="col-sm-6 col-sm-push-6 footer-social-icons padding-15">
					<span>Follow us:</span> <a href=""><img
						src="<c:url value='/resources/images/fb.png'/>"></img></a> <a href=""><img
						src="<c:url value='/resources/images/gp.png'/>"></img></a> <a href=""><img
						src="<c:url value='/resources/images/yt.png'/>"></img></a> <a href=""><img
						src="<c:url value='/resources/images/t.png'/>"></img></a>
				</div>
				<!-- /SOCIAL ICONS -->
				<div class="col-sm-6 col-sm-pull-6 padding-15">
					<p>
						&copy; 2017 <a href="${pageContext.servletContext.contextPath}">InfyGo</a>. All Rights Reserved.
					</p>
				</div>
			</div>

		</div>
	</footer> --%>
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- /FOOTER -->



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
		var $contactForm = $('#contactForm');

		$contactForm
				.on(
						'submit',
						function(event) {
							event.preventDefault();
							bootbox
									.alert("Thanks for contacting us!! Our team will connect to you soon.");
							$contactForm.get(0).reset()
							
						});
		
	</script>
	<script type="text/javascript"
		src="<c:url value='/WEB-INF/js/custom.js'/>"></script>
	<!-- custom -->



</body>
</html>