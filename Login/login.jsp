<!DOCTYPE html>
<html>
<head>

<!--META-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Zephyr</title>

<!--STYLESHEETS-->
<link href="./css/style.css" rel="stylesheet" type="text/css" />



</head>
<body>

<!--WRAPPER-->

<div id="login-wrapper">

<!--LOGIN FORM-->
<form id="login-wrapper" name="login-form" class="login-form" action="j_security_check" method="post">
	
	<div class="login-content">
		<table class="signin-table">
		  <tbody>
		  <tr>
			
			<td class="signin-primary">
				<input name="j_username" type="text" class="input j_username" placeholder="Email" onfocus="this.value=''"/>
			</td>
			<td class="signin-secondary">
				<input name="j_password" type="password" class="input j_password" placeholder="Password" onfocus="this.value=''"/>
			</td>
			<td class="signin-button">
				<input type="submit" name="login-button" value="Log In" class="login-button" />
			</td>
		  </tr>
		  </tbody>
		 </table>
	</div>
</form>
</div>
<div id="reg-wrapper">
<form id = "reg-wrapper" name="reg-form" class="reg-form" action="RegServlet" method="get">

	<!--HEADER-->
    <div class="header">
    <!--TITLE--><h1>Register Today</h1><!--END TITLE-->
    <!--DESCRIPTION--><span></span><!--END DESCRIPTION-->
    </div>
    <!--END HEADER-->
	
	<!--CONTENT-->
    <div class="content">

	<!--Name--><input name="reg-name" type="text" class="input full_name" placeholder="Full Name" onfocus="this.value=''" /><!--END Full_Name-->
			
    <!--EMAIL--><input name="reg-email" type="text" class="input reg-email" placeholder="Email" onfocus="this.value=''"/><!--END EMAIL-->
	<!--PASSWORD--><input name="reg-password" type="password" class="input reg-password" placeholder="Password" onfocus="this.value=''" /><!--END PASSWORD-->
	
   
    <!--END CONTENT-->
    
    <!--FOOTER-->
    <div class="footer" style="text-align:center;">
    <!--REGISTER BUTTON--><input type="submit" name="reg-button" value="Register" class="reg-button" /><!--END REGISTER BUTTON-->
    
    </div>
	
	 </div>
    <!--END FOOTER-->

</form>
<!--END LOGIN FORM-->


</div>

<!--END WRAPPER-->

<!--GRADIENT--><div class="gradient"></div><!--END GRADIENT-->

</body>
</html>
