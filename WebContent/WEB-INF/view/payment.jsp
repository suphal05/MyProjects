
 
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
                      
                       <h4> <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">  
                        <span class="glyphicon glyphicon-plus"></span>Booking Details </a></h4>
                  </div>
                  <div id="collapse1" class="panel-collapse collapse"> 
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
								
								<div class="col-sm-3 fontstyle text-right">Passengers#:</div>
								<div class="col-sm-3 fontstyle text-left">
									<b><span id="fare">${booking.seats}</span></b>
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
				
				<h4> <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
				 <span class="glyphicon glyphicon-minus"></span>Payment Gateway</a></h4>
			</div>
			<div id="collapse2" class="panel-collapse collapse in"> 
			<div class="panel-body">
				<h3 class="red" id="msg2"></h3>
			<form:form method="POST"
		action="makePayment?fare=${fare }"
		modelAttribute="command">
		
				<div class="form-group row">				
					<form:label path="cardNumber"
						class="control-label  col-sm-3 text-right fontstyle"> Card Number:<span
										class="red">*</span>
						 </form:label>
					<div class="col-sm-5">
						<form:input path="cardNumber" class="form-control"  required="true" title="Card number must contain 16 digits" pattern="[0-9]{16}"/>
						  <font color="red">
						<form:errors path="cardNumber" /></font> 
						<br>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="cardHolderName"
						class="control-label  col-sm-3 text-right fontstyle"> Card Holder Name:<span
										class="red">*</span> </form:label>
					<div class="col-sm-5">
						<form:input path="cardHolderName" class="form-control" required="true" title="Card holder name is mandatory"/>
						<font color="red"> 
						<form:errors path="cardHolderName" /></font> 
						<br>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="cvv"
						class="control-label col-sm-3 text-right fontstyle">CVV:<span
										class="red">*</span></form:label>
					<div class="col-sm-2">
						<form:input type="password" path="cvv" size="3" class="form-control" required="true" pattern="[0-9]{3}" title="CVV must contain 3 digits"/>
						 <font color="red" >
						<form:errors path="cvv" /></font> 
						<br>
					</div>
				</div>
				
			<div class="form-group row">
					<form:label path="securePin"
						class="control-label  col-sm-3 text-right fontstyle">3D Secure PIN:<span
										class="red">*</span></form:label>
					<div class="col-sm-3">
						<form:input type="password" path="securePin" class="form-control" required="true" pattern="[0-9]{6}" title="3D secure pin must contain 6 digits"/>
						 <font color="red" >
						<form:errors path="securePin" /></font> 
						<br>
					</div>
				</div>

				 <div class="form-group row">
					<form:label path="expiryMonth"
						class="control-label  col-sm-3 text-right fontstyle">Expiry Date:<span
										class="red">*</span></form:label>
					<div class="col-sm-7">
						<div class="row"><div class="col-sm-3">
						<form:select path="expiryMonth" id="expiryMonth" class="form-control" size="1" > 
			<form:option value="jan">Jan</form:option>
			<form:option value="feb">Feb</form:option>
			<form:option value="mar">Mar</form:option>
			<form:option value="apr">Apr</form:option>
			<form:option value="may">May</form:option>
			<form:option value="jun">Jun</form:option>
			<form:option value="jul">Jul</form:option>
			<form:option value="aug">Aug</form:option>
			<form:option value="sep">Sep</form:option>
			<form:option value="oct">Oct</form:option>
			<form:option value="nov">Nov</form:option>
			<form:option value="dec">Dec</form:option>
			</form:select>
						</div><div class="col-sm-3">
						<form:input path="expiryYear" class="form-control" placeholder="year of expiry" type="year" required="true" title="Expiry year is mandatory" size="3"/></div></div>
					<font color="red" >
						<form:errors path="expiryMonth" /></font> 
						<font color="red" > 
						<form:errors path="expiryYear" /></font>
						<br>
					</div>
				</div> 
				
				<!-- <div class="form-group">
<form:label for="validTill" class="control-label  col-sm-5 text-right fontstyle" path="expiryMonth"> EXPIRY DATE:</form:label>
	<div class="col-sm-7">
<form:input id="validTill" type="month" name="validTill" required="required" class="form-control" path="expiryYear"/>
</div>
</div> -->

<!-- <div class="form-group">
<label for="cvv"><strong class="fontstyle"> CVV</strong></label>
<input type="text" name="cvv" placeholder="XXX" pattern="[0-9]{3}" required="required" class="form-control"/>
			</div> -->
			
			<div class="row margintop-30">		
			<div class="col-sm-10 text-right" >
				<button type="submit" class="btn btn-primary" >Make Payment</button><br><br>
			</div>
		</div>
		
	</form:form>
	</div></div>
</div>
      </div>
      
<br><br>

 <jsp:include page="footer.jsp"></jsp:include> 
	

</body>
<%-- <jsp:include page="Footer.jsp"></jsp:include> --%>
</html>
 