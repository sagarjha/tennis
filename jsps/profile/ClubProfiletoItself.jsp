<html>
  <head>
    <title>Tennis World</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <script>
      function myFunction(test)
      {
      alert (test);
      }
    </script>
  </head>
  <body>
    <form name="loginForm" method="post" action="tennis">
      <div id="menu" style="color:#EEEEEE;height:600px;width:300px;float:left;">
	<img style="display:block;" left = '400'   width='250' height='300' src= <%= request.getAttribute("profilePicUrl") %> />
	<div style="margin-left:75px; margin-top:0px;"><p><b><%= request.getAttribute("name")%></b></p></div>

	<table border="0" style="margin-top:1px; margin-left:1px; position:absolute">
	  
	  <tr>
	    <td width="150"><div align="left">No of Courts</div></td>
	    <td width="100"><div align="left"><%= request.getAttribute("numcourts")%></div></td>
	  </tr>
	  <tr>
	    <td width="150"><div align="left">Coaching Slot</div></td>
	    <td width="100"><div align="left"><%= request.getAttribute("coachingslot")%></div></td>
	  </tr>
	  
	</table>
      </div>


      <div id="Details" style="color:#FFD700;height:600px;width:537px;float:right;">
	<table border="0" style="margin-top:50px; margin-left:100px; position:absolute">
	  <tr>
	    <th>Upcoming Matches at the Club</th>
	  <tr>
	    <td width="300"><div align="left"> &bull; Fedrer vs Rafael Nadal on 27th Oct</div></td>
	  </tr>
	  <tr>
	    <td width="300"><div align="left">&bull; Jindal vs Rishiraj on 3rd Nov</div></td>
	  </tr>
	  <tr>
	    <td width="300"><div align="left">&bull; Jha vs Djokovic on 4th Nov</div></td>
	  </tr>
	  <tr>
	    <td width="400"><div align="left">&bull; Gyani vs Somdev Devvarman on 5th Nov</div></td>
	  </tr>
	  <tr>
	    <td width="300"><div align="left"><a href="http://www.yahoo.com">See More</a></div></td>
	    
	    
	  </tr>
	</table>
	
	<table border="0" style="margin-top:250px; margin-left:100px; position:absolute">
	  <tr>
	    <caption><b>Your Members</b></caption>
	  <tr>
	    <td width="33%"><div align="center"><b>Players</b></div></td>
	    <td width="34%"><div align="center"><b>Coaches</b></div></td>
	    <td width="33%"><div align="center"><b>Umpires</b></div></td>
	  </tr>
	  <tr>
	    <td width="150"><div align="left">&bull; <%= request.getAttribute("player1")%></div></td>
	    <td width="150"><div align="left">&bull; <%= request.getAttribute("coach1")%></div></td>
	    <td width="150"><div align="left">&bull; <%= request.getAttribute("umpire1")%></div></td>
	  </tr>
	  <tr>
	    <td width="150"><div align="left">&bull; <%= request.getAttribute("player2")%></div></td>
	    <td width="150"><div align="left">&bull; <%= request.getAttribute("coach2")%></div></td>
	    <td width="150"><div align="left">&bull; <%= request.getAttribute("umpire2")%></div></td>
	  </tr>
	  </tr>
	  <tr>
	    <td width="150"><div align="left">&bull; <%= request.getAttribute("player3")%></div></td>
	    <td width="150"><div align="left">&bull; <%= request.getAttribute("coach3")%></div></td>
	    <td width="150"><div align="left">&bull; <%= request.getAttribute("umpire3")%></div></td>
	  </tr>
	  <tr>
	    <td width="150"><div align="left"><input type="button" onclick='myFunction("<%= request.getAttribute("player")%>")' value="See More"></div></td>
	    <td width="150"><div align="left"><input type="button" onclick='myFunction("<%= request.getAttribute("coach")%>")' value="See More"></div></td>
	    <td width="150"><div align="left"><input type="button" onclick='myFunction("<%= request.getAttribute("umpire")%>")' value="See More"></div></td>
	    
	    
	  </tr>
	</table>
	
	<div id = 'See Tournaments' style="margin-top:20px; margin-left:360px; position:absolute "> <a href="http://www.yahoo.com">Search Page</a>
	</div>
	<div id = 'See Tournaments' style="margin-top:20px; margin-left:460px; position:absolute "> <a href="file:///home/rishirajsingh/3.1/dbms/project/jsps/signupAndLogin/login.jsp">Logout</a>
	</div>
      </div>


      <div id="content" style="color:#EEEEEE;height:600px;width:500px;float:left;">

	<table border="0" style="margin-top:1px; margin-left:1px; position:absolute">
	  <tr>
	    
	    <td width="350"><div align="left"><h2><%= request.getAttribute("name")%></h1></div></td>

</tr>

<tr>
  <td width="100" colspan="2"><div align="left"><%= request.getAttribute("address")%></div></td>
</tr>
<tr>
  <td width="100" colspan="2"><div align="left">Contact : <%= request.getAttribute("emailid")%></div></td>
</tr>
<tr>
  <td width="100" colspan="2"><div align="left">Phone : <%= request.getAttribute("phoneno")%></div></td>
</tr>
<tr>
  <td width="100" colspan="2"><div align="left">About Me : <%= request.getAttribute("description")%></div></td>
</tr>
</table>




<table border="0" style="margin-top:350px; margin-left:0px; position:absolute">
  <tr>
    <td width="200" colspan="2" align="center"><b>Recent News</b></th><td>
  <tr>
    <td width="200" colspan="2" ><div align="left">&bull; <%= request.getAttribute("news1")%></div></td>
  </tr>
  <tr>
    <td width="200" colspan="2" ><div align="left">&bull; <%= request.getAttribute("news2")%></div></td>
  </tr>
  <tr>
    <td width="200" colspan="2"><div align="left">&bull; <%= request.getAttribute("news3")%></div></td>
  </tr>
  
  <tr>
    <td width="200"><div align="left"><input type="button" onclick='myFunction("<%= request.getAttribute("news")%>")' value="See More"></div></td>
    <td width="200"><div align="left"><a href="http://www.yahoo.com">Add News Items</a></div></td>
    
    
  </tr>
</table>

<div id = 'Tournament' style="margin-top:500px; margin-left:100px; position:absolute"> <a href="../tournament/create.jsp">Schedule a Tournament</a>
</div>

<div id = 'Tournament' style="margin-top:530px; margin-left:100px; position:absolute"> <td width="399"><br><input type="Submit" name="CLUBPROFILETOITSELF" value = "View Tournaments Page"></td>
</div>

</div>

</form>

</body>
</html>
