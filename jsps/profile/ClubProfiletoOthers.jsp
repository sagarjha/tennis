<html>
  <head>
    <title>Tennis World</title>
    <link href="profile/style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <script>
      function myFunction(test)
      {
      alert (test);
      }
    </script>
  </head>
  <body>
  <form name="loginForm" method="post" action="../tennis">
 <div id="menu" style="color:#EEEEEE;height:600px;width:300px;float:left;">
<img style="display:block;" left = '400'   width='250' height='300' src= <%= request.getAttribute("profilePicUrl") %> />
<div style="margin-left:75px; margin-top:0px;"><p><%= request.getAttribute("name")%></p></div>

<table border="0" style="margin-top:1px; margin-left:1px; position:absolute">


<!-----------------------------------------------------------Memeber---------------->
<%if(request.getAttribute("Member").toString().equals("0")){ %>
<tr>
      
<!--<td><div align="centre"><button type="button">Become a Member(only visible to player)</button></div></td> -->
<td><div align="centre"><input type="Submit" name="CLUBPROFILEOTHERS" value = "Become a Member"></div></td>
</tr>
<% } %>



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
	  <td width="300"><div align="left"> &bull; <%= request.getAttribute("UpcomingMatches1") %></div></td>
	</tr>
	<tr>
	  <td width="300"><div align="left">&bull; <%= request.getAttribute("UpcomingMatches2") %></div></td>
	</tr>
	<tr>
	  <td width="300"><div align="left">&bull; <%= request.getAttribute("UpcomingMatches3") %></div></td>
	</tr>
	
	<tr>
	  <td width="300"><div align="left"><input type="button" onclick='myFunction("<%= request.getAttribute("UpcomingMatches")%>")' value="See all results"> </div></td>
	  
	  
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
   <td width="100" colspan="2"><div align="left">Contact :   <%= request.getAttribute("emailid")%></div></td>
   </tr>
   <tr>
   <td width="100" colspan="2"><div align="left">Phone :  <%= request.getAttribute("phoneno")%></div></td>
   </tr>
   <tr>
   <td width="100" colspan="2"><div align="left">About It : <%= request.getAttribute("description")%></div></td>
   </tr>
   </table>
   
   
  
  
  <table border="0" style="margin-top:350px; margin-left:0px; position:absolute">
<tr>
<td width="300" colspan="2" align="center"><b>Recent News</b></th><td>
<tr>
   <td width="300" colspan="2" ><div align="left">&bull; <%= request.getAttribute("news1")%></div></td>
   </tr>
   <tr>
   <td width="300" colspan="2" ><div align="left">&bull;<%= request.getAttribute("news2")%></div></td>
    </tr>
   <tr>
   <td width="300" colspan="2"><div align="left">&bull; <%= request.getAttribute("news3")%></div></td>
    </tr>
   
   <tr>
   <td width="200"><div align="left"><input type="button" onclick='myFunction("<%= request.getAttribute("news")%>")' value="See More"></div></td>
  
   
   
   </tr>
  </table>
  
  <!-----------------------------View Club's Tournament----------------------->
  
  <div id = 'Tournament' style="margin-top:530px; margin-left:100px; position:absolute"><input type="button" onclick='myFunction("<%= request.getAttribute("moreTournaments")%>")' value="See Club's Tournaments">
  </div>
  
  </div>
  </form>
  </body>
  </html>
  
  
  
