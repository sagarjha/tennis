<html>
  <head>
    <title>Search for coaches</title>
  </head>

  <h3 align="center">Search for coaches</h3><hr>

  <body>
   <form name="searchcoach" method ="post" action="../tennis">
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
	      <option value="2">Club</option>
	  </select></div>
	</td>
	<td  width="400"> <div align="right">
	    <select id="order" name="order">
              <option value="1">Name</option>  
	      <option value="4">Age</option>
	      <option value="2">Rating</option>
	      <option value="3">Experience</option>
	  </select></div> 
	</td>
      </tr>
      <tr>
	<td width="200"><div align="right"><br><input type="Submit" name="SEARCHCOACH" value = "Search"></div></td>
      </tr>
    </table>
    </form>
  </body>
</html>

