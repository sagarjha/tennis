<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Challenge</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  </head>
  <h3 align="center">Challenge the Player</h3><hr>
  <body bgcolor="#FFFFFF" onLoad="document.loginForm.username.focus()">

	<form name="challenge" method ="post" action="../tennis">
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
    	<td width="50%"><div align="center">Select Challenge Type</div></td>
	<td width="50"><div align="center">
	<select id="type" name="type">
  <option value="1">Friendly</option>
  <option value="2">Competitive</option>
	</select> </div></td>
	</tr>
	<tr>
	<td></td>
	</tr>
	<tr>
    	<td width="50%"><div align="center">Select Match Venue</div></td>
	<td width="50"><div align="center">
	<select id="clubname" name="clubname">
	<%
    List<Integer> ids = (List<Integer>)request.getAttribute("clubids");
    List<String> names = (List<String>)request.getAttribute("clubnames");
    for(int i = 0; i < ids.size(); i++){ %>
  	<option value="<%= ids.get(i)%>" ><%= names.get(i) %></option>
  	 <% } %>
	</select> </div></td>
	</tr>
	
	<tr>
    	<td width="50%"><div align="center">Select Date for Match</div></td>
	<td width="50"><div align="center">
	
	<select id="date" name="date">
  <%
    List<String>dates = (List<String>)request.getAttribute("dates");
    for(int i = 0; i < dates.size(); i++){ %>
  	<option value="<%= dates.get(i)%>" ><%= dates.get(i) %></option>
  	 <% } %>
	</select>
	</div></td>
	</tr>
	<tr>
    	<td width="50%"><div align="center">Select Match Slot</div></td>
	<td width="50"><div align="center">
	<select id = "slot" name="slot">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
  <option value="6">6</option>
  <option value="7">7</option>
  <option value="8">8</option>
  <option value="9">9</option>
  <option value="10">10</option>
  <option value="11">11</option>
  <option value="12">12</option>
  <option value="13">13</option>
  <option value="14">14</option>
	</select> </div></td>
	</tr>
                  <tr>
		<td width="401"> </td>
		<td width="399"><br><input type="Submit" name="CHALLENGE" value = "Challenge"></td>
	      </tr>
</table>
</form>
  </body>
</html>