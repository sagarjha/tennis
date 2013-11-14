<html>
  <head>
    <title>Schedule</title>
    <link href="style.css" rel="stylesheet" type="text/css">
  </head>

  <body>
    <form method = "POST" action = "tennis">
      <center> <b>Pending Challenges<br><br></b>
	<table width="1000" border="1">
	  <tr>
	    <th width="200">
	      Opponent
	    </th>
	    <th width="200">
	      Venue
	    </th>
	    <th width="200">
	      Date
	    </th>
	    <th width="200">
	      Slot
	    </th>
	    <th width="200">
	      Choose Winner
	    </th>
	  </tr>
	  <%= request.getAttribute("pendingChallenges") %>
	</table>
      </center>
      <br> <br>
      <center> <input type="Submit" style="color: blue; background-color: grey" name="REPLYTOCHALLENGE" value = "acceptReject"> </center>
    </form>
  </body>
