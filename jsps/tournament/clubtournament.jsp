<html>
  <head>
    <title><%= request.getAttribute("name")%></title>
    <center><h1><%= request.getAttribute("name")%></h1></center>
    <link href="tournament/style.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <form method ="post" action="../tennis">
    <div id="info" style="color:#00FFFF">
      <center>
	<table width="400">
	
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
    
   <% if(request.getAttribute("registerstall").toString().equals("1")){%>
    <center> <input type="Submit" style="color: red; background-color: grey" name="Register" value = "Register"> </center>
	<% }%>
	
	<% if(request.getAttribute("registerstall").toString().equals("2")){%>
    <center> <input type="Submit" style="color: red; background-color: grey" name="Register" value = "Add Stall"> </center>
	<% }%>
	
	</form>
  </body>
