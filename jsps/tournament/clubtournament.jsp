<html>
  <head>
    <title><%= request.getAttribute("name")%></title>
    <center><h1><%= request.getAttribute("name")%></h1></center>
    <link href="tournament/style.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <div id="info" style="color:#00FFFF">
      <center>
	<table width="400">
	<form action=tennis>
	  <tr>
	    <td>
	      Club:
	    </td>
	    <td>
	      <%= request.getAttribute("club") %>
	    </td>
	  <tr>
	    <td>
	      Starting Date:
	    </td>
	    <td>
	      <%= request.getAttribute("startdate") %>
	    </td>
	  </tr>
	  <tr>
	    <td>
	      No. of players:
	    </td>
	    <td>
	      <%= request.getAttribute("numplayers") %>
	    </td>
	  </tr>
	  <tr>
	    <td>
	      Prize money:
	    </td>
	    <td>
	      <%= request.getAttribute("prizemoney") %>
	    </td>
	  </tr>
	</table>
      </center>
      <br>
      <br>
      <center><img src="tournament/images/draw.jpg" align="center"></center>
    </div>
    <% %>
    <center> <input type="Submit" style="color: red; background-color: grey" name="Register" value = "Register"> </center>
	<% }%>
	</form>
  </body>
