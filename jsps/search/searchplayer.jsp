<html>
  <head>
    <title>Search for players</title>
  </head>
  
  <h3 align="center">Search for players</h3><hr>

  <body>
    <table width="500" border="0" cellspacing="0" cellpading="0">
      <tr>
	<td width="200"> <div align="left">
	    Search String
	</div> </td>
	<td  width="150"> <div align="right">
	    Search basis
	</div> </td>
	<td  width="150"> <div align="right">
	    Result order
	</div> </td>
      </tr>
      <tr>
	<td width="100"> <div align="right"> <input type="text" name="searchString" required> </div></td>
	<td  width="400"> <div align="right">
	    <select id="basis" name="basis">
              <option value="1">Name</option>  
	      <option value="6">Locality</option>
	      <option value="2">Club</option>
	      <option value="3">Coached by</option>
	      <option value="4">Gender</option>	
	      <option value="5">Age</option>	
	  </select></div>
	</td>
	<td  width="400"> <div align="right">
	    <select id="order" name="order">
              <option value="1">Name</option>  
	      <option value="3">Age</option>
	      <option value="2">Rating</option>
	  </select></div> 
	</td>
      </tr>
      <tr>
	<td width="200"><div align="right"><br><input type="Submit" name="SEARCHPLAYER" value = "Search"></div></td>
      </tr>
    </table>
  </body>
</html>
