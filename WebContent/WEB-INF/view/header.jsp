<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	

<!-- HEADER -->
	<header id="header">

    
		<nav class="navbar st-navbar navbar-fixed-top" >
		
			<div class="container-fluid">
		         <div class="navbar-header">			 	
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#st-navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
				    	<span class="icon-bar"></span>
				    	<span class="icon-bar"></span>
				    	<span class="icon-bar"></span>
					</button>
					<a class="logo1"  href="${pageContext.servletContext.contextPath}">
						
				<img id="a" src="<c:url value='/resources/images/infygonew.PNG'/>"></img> 
				</a>
	          </div>
	        			
			 	<div class="collapse navbar-collapse" id="st-navbar-collapse">
			<div class="">
					<!-- <ul class="nav navbar-nav nav-links .margintop-5">
					
						<li><a href="#header">Home</a></li>
				        <li><a href="#about-us">About Us</a></li>   
						 <li><a href="#contact">Contact Us</a></li> 
					</ul>    -->
					<ul class="nav navbar-nav navbar-right margintop-5 " >
					
						<li>  <a class="btn btn-md " href="register.htm" style="margin-top:10px;border-top-width:1px">
                        <span class="glyphicon glyphicon-user"></span>&nbsp;Sign Up
                  </a> </li>
				        <li><a class="btn btn-md  nav-links-right  " href="login" style="margin-top:10px;border-top-width:1px">
                        <span class="glyphicon glyphicon-log-in"></span>&nbsp;Login
                  </a></li>   
						 
				 
					</ul> 
						  </div>
				      
	</div>  
		</div><!-- /.navbar-collapse -->
		</nav>

     </header>
	<!-- /HEADER -->