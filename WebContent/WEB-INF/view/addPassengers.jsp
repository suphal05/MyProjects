
 
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
	<script src="js/dynamic_list_helper.js" type="text/javascript"></script>

	
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
                      
                     <h4>   <a data-toggle="collapse" data-parent="#accordion" href="#collapse1"><h4> <span class="glyphicon glyphicon-plus"></span> Booking Details </a></h4>
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
				
				<h4> <a data-toggle="collapse" data-parent="#accordion" href="#collapse2"><h4><span class="glyphicon glyphicon-minus"></span>PASSENGER DETAILS</a></h4>
			</div>
			<div id="collapse2" class="panel-collapse collapse in"> 
			<div class="panel-body">
				<h3 class="red" id="msg2"></h3>
			<form:form action="${contextPath}/editPassengerListContainer"
						modelAttribute="passengerListContainer" id="personListForm">
								<table class="table">
									<thead>
										<tr>
											<th>Name<span
										class="red">*</span></th>
											<th>Age<span
										class="red">*</span></th>
											<th>Gender<span
										class="red">*</span></th>
										</tr>
									</thead>
									<tbody id="personListContainer">
										<c:forEach items="${passengerListContainer.passengerList}"
											var="passenger" varStatus="i" begin="0">
											<tr class="person margintop-15 padding-3">
												<td><form:input
														path="passengerList[${i.index}].passengerName"
														class="form-control" id="name${i.index}" required="true" title="Name must contain 2 to 15 characters" pattern=".{2,15}"/></td>
												<td><form:input type="text" pattern="^\d{1,3}$"
														title="Age should be in 1 to 3 digits"
														path="passengerList[${i.index}].age" class="form-control"
														id="age${i.index}" size="4"/></td>
												<td><form:select path="passengerList[${i.index}].gender"
													 id="gender${i.index}" class="form-control" required="true">
													
													 <form:option value="M">Male</form:option>
													 <form:option value="F">Female</form:option>
													 </form:select></td>
														
												<td><font><strong class="fontstyle" ><a href="#" class="removePerson ">Remove
														Passenger</a></strong></font></td>
											</tr>
										</c:forEach>
										<%-- 
                                          IMPORTANT 
                                          There must always be one row.
                                          This is to allow the JavaScript to clone the row.
                                          If there is no row at all, it cannot possibly add a new row.
                                      
                                          If this 'default row' is undesirable 
                                              remove it by adding the class 'defaultRow' to the row
                                          I.e. in this case, class="person" represents the row.
                                          Add the class 'defaultRow' to have the row removed.
                                          This may seem weird but it's necessary because 
                                          a row (at least one) must exist in order for the JS to be able clone it.
                                          <tr class="person"> : The row will be present
                                          <tr class="person defaultRow"> : The row will not be present
                                      --%>
										<c:if
											test="${passengerListContainer.passengerList.size() == 0}">
											<tr class="person defaultRow margintop-15 padding-3">
												<td><input type="text" name="passengerList[].name"
													value="" /></td>
												<td><input type="text" name="passengerList[].age"
													value="" /></td>
													<td><input type="text" name="passengerList[].gender"
													value="" /></td>
											
														<td>	<font><strong class="fontstyle" ><a href="#"class="removePerson" >Remove
																Passenger</a></strong></font></td>
												

											</tr>

										</c:if>


									</tbody>

								</table><br><br>
								 <div class=" col-sm-3">
								<font><strong class="fontstyle">
								 <a href="#" id="addPerson"  >Add Passenger</a>&nbsp;&nbsp;
								</strong></font> <br> <br>
								</div>
								<div class=" col-sm-3" >
								<font><strong class="fontstyle">
								<a href="?f=" >Reset </a>
								</strong></font>   <br> <br>
								</div>
								
									<div class="col-sm-offset-2 col-sm-3 " >
								<font><strong class="fontstyle">&nbsp;&nbsp;
								<button type="submit" class="btn btn-info fontstyle"
								style="margin-top: 2px; margin-left: 25px; padding:5px" name="proceed" value="Proceed To Pay">Proceed
								To Pay</button>
								</strong></font> <br> <br>
								</div>
								
								
						
						
						<div class="col-md-12 text-center">
					<div class="text-center text-danger">${message}</div>
				</div>
				
						
						</form:form>
	</div></div>
</div>
      </div>
      



			
	 <jsp:include page="footer.jsp"></jsp:include> 
	

      <script type="text/javascript">
            function rowAdded(rowElement) {
                //clear the imput fields for the row
                $(rowElement).find("input").val('');
                //may want to reset <select> options etc
                
                //in fact you may want to submit the form
                saveNeeded();
            }
            function rowRemoved(rowElement) {
                saveNeeded();
              document.getElementById("personListForm").submit();
               /*  alert( "Removed Row HTML:\n" + $(rowElement).html() ); */
               alert( "Removed");
            }
            
            function saveNeeded() {
                $('#submit').css('color','red');
                $('#submit').css('font-weight','bold');
                if( $('#submit').val().indexOf('!') != 0 ) {
                    $('#submit').val( '!' + $('#submit').val() );
                }
            }
            
           
            
            $(document).ready( function() {
                var config = {
                    rowClass : 'person',
                    addRowId : 'addPerson',
                    removeRowClass : 'removePerson',
                    formId : 'personListForm',
                    rowContainerId : 'personListContainer',
                    indexedPropertyName : 'passengerList',
                   indexedPropertyMemberNames : 'passengerName,age,gender',
                    rowAddedListener : rowAdded,
                    rowRemovedListener : rowRemoved
                    
                };
                new DynamicListHelper(config);
            });
        </script>



</body>

</html>
 