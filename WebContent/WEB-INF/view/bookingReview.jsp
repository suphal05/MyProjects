
 
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

<link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>"
	type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value='/resources/css/newstyle.css'/>"
	type="text/css" media="all">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" 
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">

$(function(){

$('.collapse').on('shown.bs.collapse', function(){

$(this).parent().find(".glyphicon-plus").removeClass("glyphicon-plus").addClass("glyphicon-minus");

}).on('hidden.bs.collapse', function(){

$(this).parent().find(".glyphicon-minus").removeClass("glyphicon-minus").addClass("glyphicon-plus");

});

})

</script>
</head>
<body>

	<jsp:include page="header2.jsp"></jsp:include>


	
		
			 <div class="panel-group flightDetails" id="accordion">
            <br>
            <div class="panel panel-info">
                  <div class="panel-heading">
                      
                       <h4> <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">  <span class="glyphicon glyphicon-minus"></span>Booking Details </a></h4>
                  </div>
                  <div id="collapse1" class="panel-collapse collapse in"> 
                  <div class="panel-body">
                  
                  	
                  
                       
                        <form:form>
							<div class="row margintop-10">
								<div class="col-sm-3 fontstyle text-right">Journey Date:
								</div>
								<div class="col-sm-3 fontstyle text-left">
									<b><span id="jdate">${booking.departureDate}</span></b>
								</div>
								<div class="col-sm-3 fontstyle text-right">Departure Time:
								</div>
								<div class="col-sm-3 fontstyle text-left">
									<b><span id="time">${booking.departureTime}</span></b>
								</div>
							</div>
							<div class="row margintop-10">
								<div class="col-sm-3 fontstyle text-right">Origin:</div>
								<div class="col-sm-3 fontstyle text-left">
									<b><span id="origin">${booking.source}</span></b>
								</div>
								<div class="col-sm-3 fontstyle text-right">Destination:</div>
								<div class="col-sm-3 fontstyle text-left">
									<b><span id="destination">${booking.destination}</span></b>
								</div>
							</div>
							<div class="row margintop-10">
								<div class="col-sm-3 fontstyle text-right">Airline:</div>
								<div class="col-sm-3 fontstyle text-left">
									<b><span id="jdate">${booking.airlines}</span></b>
								</div>
								<div class="col-sm-3 fontstyle text-right">Flight Id:</div>
								<div class="col-sm-3 fontstyle text-left">
									<b><span id="origin">${booking.flightId}</span></b>
								</div>

							</div>

							<div class="row margintop-10">

								<div class="col-sm-3 fontstyle text-right">Fare:</div>
								<div class="col-sm-3 fontstyle text-left">
									<b>Rs. <span id="fare">${booking.fare}</span></b>
								</div>
								
								
							</div>
							<div class="row margintop-30">

								<div class="col-sm-11 fontstyle text-right"><a href="${pageContext.servletContext.contextPath}/modifyBooking" class="btn btn-info">Modify Booking </a></div>
								
																
							</div>

							
						</form:form>
                  </div></div>
            </div>
            <!-- second panel for payment -->
            
           <div class="panel panel-info">
					<div class="panel-heading">
					<form:form>
				
				<h4> <a data-toggle="collapse" data-parent="#accordion" href=""><span class="glyphicon glyphicon-plus"></span></a>
				<a   href="addPassengerDetails" >ADD PASSENGER</a></h4>
				 </form:form>
			</div>
			
	</div></div>



 <jsp:include page="footer.jsp"></jsp:include> 
	

</body>
<%-- <jsp:include page="Footer.jsp"></jsp:include> --%>
</html>
 