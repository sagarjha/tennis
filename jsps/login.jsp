<html>
  <head>
    <title>Welcome</title>
    <link href="signupAndLogin/style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  </head>
  <h3 align="center">Welcome to Tennis World! </h3><hr>
  <body bgcolor="#FFFFFF" onLoad="document.loginForm.username.focus()">

    <table width="500" border="0" cellspacing="0" cellpadding="0">
      <tr>
	<td> </td>
      </tr>
      <tr>
	<td>
      </tr>
      <tr>
	<td> </td>
      </tr>
    </table>
    <table width="500" border="0" cellspacing="0" cellpadding="0">
      <tr>
	<td>
	  <table width="500" border="0" cellspacing="0" cellpadding="0">
	    <form name="loginForm" method="post" action="tennis">
	      <tr>
		<td width="401"><div align="right"><b>User Name: </b></div></td>
		<td width="399"><input type="text" name="username"></td>
	      </tr>
	      <tr>
		<td width="401"><div align="right"><b>Password: </b></div></td>
		<td width="399"><input type="password" name="password"></td>
	      </tr>
	      <tr>
		<td width="401"> </td>
		<td width="399"><br><input type="Submit" name="LOGIN" value = "Login"></td>
	      </tr>
	      <tr>
		<td width="401"> </td>
		<td> <a href = "signupAndLogin/register.jsp"> Register </a> </td>
	      </tr>
	    </form>
	  </table>
	</td>
      </tr>
    </table>

  </body>
</html>
