<html>
  <head>
    <title>Tournaments</title>
    <link href="tournament/style_light.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
    <form method="post" action = "../tennis">
     <td width="399"><br><button type="Submit" name="LOGIN" value = "Login">Back to Profile</button></td>
 
      <center> <b> Upcoming Tournaments <br><br></b>
	<table width="1000" border="1">
	  <tr>
	    <th width="200">
	      Name
	    </th>
	    <th width="200">
	      Club
	    </th>
	    <th width="200">
	      Starting Date
	    </th>
	    <th width="200">
	      No. of players
	    </th>
	    <th width="200">
	      Prize money
	    </th>
	  </tr>
	  
	  <%= request.getAttribute("upcomingTournamentsData") %>
	  
	</table>
      </center>
      <br> <br> <br>
      <center> <b> Ongoing Tournaments <br><br></b>
	<table width="1000" border="1">
	  <tr>
	    <th width="200">
	      Name
	    </th>
	    <th width="200">
	      Club
	    </th>
	    <th width="200">
	      Starting Date
	    </th>
	    <th width="200">
	      No. of players
	    </th>
	    <th width="200">
	      Prize money
	    </th>
	  </tr>
	  <%= request.getAttribute("ongoingTournamentsData") %>
	</table>
      </center>
    </form>
  </body>
