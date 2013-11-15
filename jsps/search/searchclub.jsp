<html>
  <head>
    <title>Search for clubs</title>
    <link href="style.css" rel="stylesheet" type="text/css">
  </head>

  <h3 align="center">Search for clubs</h3><hr>

  <body>
   <form name="searchclub" method ="post" action="../tennis">
    <table width="350" border="0" cellspacing="0" cellpading="0">
    
      <tr>
	<td width="200"> <div align="left">
	    Search String
	</div> </td>
	<td  width="150"> <div align="right">
	    Search basis
	</div> </td>
      </tr>
      <tr>
	<td width="100"> <div align="right"> <input type="text" name="searchString" required> </div></td>
	<td  width="400"> <div align="right">
	    <select id="basis" name="basis">
              <option value="1">Name</option> 
	      <option value="3">Location</option>
	      <option value="2">Coach</option>
	  </select></div>
	</td>
      </tr>
      <tr>
	<td width="200"><div align="right"><br><input type="Submit" name="SEARCHCLUB" value = "Search"></div></td>
      </tr>
    </table>
    </form>
  </body>
</html>

