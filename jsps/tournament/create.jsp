<html>
  <head>
    <title>Create a Tournament</title>
	<link href="style_light.css" rel="stylesheet" type="text/css">
  </head>
  <h3 align="center">Create a Tournament</h3><hr>
  
  <body>
    <table width="500" border="0" cellspacing="0" cellpading="0">
      <form method = "POST" action = "../tennis">
	<tr>
	  <td width="400"><div align="right"><b><h3>Select no. of rounds:</h3></b> </div>
	  </td>
	  <td width="400"><div align="right">
	      <select id="role" name="round">
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	    </select></div>
	  </td>
	</tr>
	<tr>
	  <td width="400"><div align="right"><b><h3>Select starting slot:</h3></b> </div>
	  </td>
	  <td width="400"><div align="right">
	      <select id="role" name="startingSlot">
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
	    </select></div>
	  </td>
	</tr>
	<tr>
	  <td width="400"><div align="right"><b><h3>Select starting date:</h3></b> </div>
	  </td>
	  <td width="400"><div align="right">
	      <select id="role" name="startDate">
		<%= request.getAttribute("validDates")%>
	    </select></div>
	  </td>
	</tr>
	<tr>
	  <td width="600"><div align="right"><br><button type="Submit" name="CREATETOURNAMENT" value = <%= session.getAttribute("accountid") %> %>Proceed</td></div>
	</tr>
      </form>
    </table>
  </body>

</html>

