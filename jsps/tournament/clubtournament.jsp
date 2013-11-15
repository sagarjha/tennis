<html>
  <head>
  <form method ="post" action="../tennis">
    <title><%= request.getAttribute("name")%></title>
    <td width="399"><br><button type="Submit" name="LOGIN" value = "Login">Back to Profile</button></td>
    <center><h1><%= request.getAttribute("name")%></h1></center>
    <link href="tournament/style.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    
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
    </div>
    
   <% if(request.getAttribute("registerstall").toString().equals("1")){%>
    <center> <input type="Submit" style="color: red; background-color: grey" name="CLUBTOURNAMENTREGISTER" value = "Register"> </center>
	<% }%>
	
	<% if(request.getAttribute("registerstall").toString().equals("2")){%>
	
	
	<table border="0" style="margin-top:50px; margin-left:450px; position:absolute">
	<tr>
	<td width="399"><div align="left"><font color="yellow"> Choose Discount for the Stall
	
	<select id = "discount" name="discount">

  	<option value="10">10</option>
  	<option value="20">20</option>
  	<option value="30">30</option>
  	<option value="40">40</option>
  	<option value="50">50</option>
  	<option value="60">60</option>
  	<option value="70">70</option>
  	<option value="80">80</option>
  	<option value="90">90</option>
  
   </select> </div></td>
   </tr>
	<tr>
	</tr>
	<tr>
	</tr>
	<tr>
	</tr>
	<tr>	
	<td width="399">
     <input type="Submit" style="color: red; background-color: grey" name="CLUBTOURNAMENTADDSTALL" value = "Add Stall"> </center>
	<% }%></td>
	</tr>
	</form>
  </body>
