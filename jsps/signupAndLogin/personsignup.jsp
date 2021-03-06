<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Tennis World - Sign Up</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  </head>
  <h3 align="center">Sign Up</h3><hr>

  <body>
    <table width="500" border="0" cellspacing="0" cellpading="0">
      <form name="personsignup" method ="post" action="../tennis">
	<tr>
	  <td width="401"><div align="center"><b>Name* : </b></div></td>
	  <td width="399"><input type="text" name="name" required></td>
	</tr>
	<tr>
	  <td width="401"><div align="center"><b>Age* : </b></div></td>
	  <td width="399"><input type="number" name="age" required min="1"></td>
	</tr>
	<tr>
	  <td width="401"><div align="center"><b>Gender* : </b></div></td>
	  <td width="399">
	    <select id="gender" name="gender" required>
	      <option value="Male">Male</option>
	      <option value="Female">Female</option>
	  </select></td>
	</tr>
	<tr>
	  <td width="401"><div align="center"><b>Address* : </b></div></td>
	  <td width="399"><input type="text" name="address" required></td>
	</tr>
	<tr>
	  <td width="401"><div align="center"><b>Email address* : </b></div></td>
	  <td width="399"><input type="text" name="emailaddress" required></td>
	</tr>
	<tr>
	  <td width="401"><div align="center"><b>Phone Number: </b></div></td>
	  <td width="399"><input type="text" name="phoneno"></td>   
	</tr>
	<tr>
	  <td width="401"><div align="center"><b>Username* : </b></div></td>
	  <td width="399"><input type="text" name="username" required></td>
	<%if(session.getAttribute("usernamealreadyexists").toString().equals("1")){ %>
   <td width="1001">Username Already Exists. Please choose another username.</td>
	<% } %> 
	</tr>
	<tr>
	  <td width="401"><div align="center"><b>Password* : </b></div></td>
	  <td width="399"><input type="password" name="password" required></td>
	</tr>
	<tr>
	  <td width="401"><div align="center"><b>Club* : </b></div></td>
	  <td width="399"><input list="clubid" name="clubid">
   <datalist id="clubid">
<%
    List<Integer> clubids = (List<Integer>)request.getAttribute("clubids");
    List<String> clubnames = (List<String>)request.getAttribute("clubnames");
    for(int i = 0; i < clubids.size(); i++){ %>
  <option value="<%= clubids.get(i)%>"><%= clubnames.get(i)%></option>
  <% } %>
</datalist></td>
	</tr>
	<!-- do autocomplete for club -->
	<tr>
	  <td width="600"><div align="right"><br><input type="Submit" name="PERSONSIGNUP" value = "Proceed"></div></td>
	</tr>
      </form>
    </table>
  </body>

</html>
