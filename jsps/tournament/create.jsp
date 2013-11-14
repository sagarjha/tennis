<html>
  <head>
    <title>Create a Tournament</title>
	<link href="style_light.css" rel="stylesheet" type="text/css">
  </head>
  <h3 align="center">Create a Tournament</h3><hr>
  
  <body>
    <table width="500" border="0" cellspacing="0" cellpading="0">
      <form method = "POST" action = "tennis">
	<tr>
	  <td width="400"><div align="right"><b><h3>Select no. of rounds:</h3></b> </div>
	  </td>
	  <td width="400"><div align="right">
	      <select id="role" name="role">
		<option value="1">3</option>
		<option value="2">4</option>
		<option value="3">5</option>
	    </select></div>
	  </td>
	</tr>
	<tr>
	  <td width="400"><div align="right"><b><h3>Select starting slot:</h3></b> </div>
	  </td>
	  <td width="400"><div align="right">
	      <select id="role" name="role">
		<option value="1">7</option>
		<option value="2">8</option>
		<option value="3">9</option>
		<option value="4">10</option>
		<option value="5">11</option>
		<option value="6">12</option>
		<option value="7">13</option>
		<option value="8">14</option>
		<option value="9">15</option>
	    </select></div>
	  </td>
	</tr>
	<tr>
	  <td width="400"><div align="right"><b><h3>Select starting date:</h3></b> </div>
	  </td>
	  <td width="400"><div align="right">
	      <select id="role" name="role">
		<option value="1">29/10/13</option>
		<option value="2">30/10/13</option>
		<option value="3">31/10/13</option>
		<option value="4">01/11/13</option>
		<option value="5">02/11/13</option>
		<option value="6">03/11/13</option>
		<option value="7">04/11/13</option>
		<option value="8">05/11/13</option>
		<option value="9">06/11/13</option>
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

