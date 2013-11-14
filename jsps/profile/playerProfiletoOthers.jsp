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
  <form method="post" action="../tennis">
 <div id="menu" style="color:#EEEEEE;height:600px;width:300px;float:left;">
<img style="display:block;" left = '400'   width='250' height='300' src= <%= request.getAttribute("profilePicUrl") %> />
<div style="margin-left:75px; margin-top:0px;"><p><%= request.getAttribute("name") %></p></div>



<!-- ---************************************Challenge Player: Should be visible only to other player**************************************** -->
<%if(request.getAttribute("challengeoption").toString().equals("0")){ %>
<!--<div style="margin-left:65px; margin-top:0px;"><a href="file:///home/rishirajsingh/3.1/dbms/project/jsps/signupAndLogin/Challenge.jsp"><button type="button">Challenge the Player</a></button></div> -->

<div style="margin-left:65px; margin-top:0px;"><input type="Submit" name="CHALLENGEPLAYER" value = "Challenge Player"></div>
<% } %>



<table border="0" style="margin-top:1px; margin-left:1px; position:absolute">
<tr>
   <td width="150"><div align="left">Matches Played</div></td>
   <td width="100"><div align="left"><%= request.getAttribute("numMatchesPlayed")%></div></td>
   </tr>
   <tr>
   <td width="150"><div align="left">Won</div></td>
   <td width="100"><div align="left"><%= request.getAttribute("numMatchesWon")%></div></td>
   </tr>
   <tr>
   <td width="150"><div align="left">lost</div></td>
   <td width="100"><div align="left"><%= request.getAttribute("numMatchesLost")%></div></td>
   </tr>
   <tr>
   <td width="150"><div align="left">Rating</div></td>
   <td width="100"><div align="left"><%= request.getAttribute("rating")%> </div></td>
   </tr>
      <tr>
   <td width="150"><div align="left">Highest Rating</div></td>
   <td width="100"><div align="left"><%= request.getAttribute("highestRatingAchieved")%></div></td>
   </tr>
   </table>
</div>


<div id="Details" style="color:#FFD700;height:600px;width:537px;float:right;">
<table border="0" style="margin-top:50px; margin-left:100px; position:absolute">
<tr>
<th>Upcoming Matches</th>
<tr>
   <td width="300"><div align="left">&bull; <%= request.getAttribute("UpcomingMatches1") %></div></td>
   </tr>
   <tr>
   <td width="300"><div align="left">&bull; <%= request.getAttribute("UpcomingMatches2") %></div></td>
    </tr>
   <tr>
   <td width="300"><div align="left">&bull; <%= request.getAttribute("UpcomingMatches3") %>/div></td>
    </tr>
   <tr>
	    <td width="300"><div align="left"><input type="button" onclick='myFunction("<%= request.getAttribute("UpcomingMatches")%>")' value="See all results"> </div></td>
	</tr>
  </table>
  
  <table border="0" style="margin-top:250px; margin-left:100px; position:absolute">
<tr>

<!-- ---************************************His Clubs**************************************** -->
	<th>His Clubs<th>
	
	</tr>
	<%
if(request.getAttribute("club1")!=null)
{
%>
<tr>
   <td width="200" ><div align="center">&bull; <%= request.getAttribute("club1")%></div></td>   
   </tr>
<%
}
if(request.getAttribute("club2")!=null)
{
%>
   <tr>
   <td width="200" colspan="2" ><div align="center">&bull; <%= request.getAttribute("club2")%></div></td>
    </tr>
<%
}
if(request.getAttribute("club3")!=null)
{
%>
   <tr>
   <td width="200" colspan="2"><div align="center">&bull; <%= request.getAttribute("club3")%></div></td>
    </tr>
<%
}
%>  
   <tr>
   <td width="200"><div align="left"><input type="button" onclick='myFunction("<%= request.getAttribute("moreClubs")%>")' value="See all Clubs"> </div></td>
   
   </tr>
  </table>
 <div id = 'See Tournaments' style="margin-top:20px; margin-left:420px; position:absolute "> <button type="Submit" name="LOGIN" value = "Login">Back to Profile</button></div>   
  
  
</div>


<div id="content" style="color:#EEEEEE;height:600px;width:500px;float:left;">

<table border="0" style="margin-top:1px; margin-left:1px; position:absolute">
<tr>
   
   <td width="350"><div align="left"><h2><%= request.getAttribute("name")%></h1></div></td>
   
   </tr>
   <tr>
   <td width="100"><div align="left"><%= request.getAttribute("gender")%></div></td>
   <td width="100"><div align="left"><%= request.getAttribute("age")%> years</div></td>
   </td>
   </tr>
   <tr>
   <td width="100" colspan="2"><div align="left"><%= request.getAttribute("address")%> </div></td>
</tr>
   <tr>
   <td width="100" colspan="2"><div align="left">Contact : <%= request.getAttribute("emailid")%> </div></td>
   </tr>
   <tr>
   <td width="100" colspan="2"><div align="left">Phone : <%= request.getAttribute("phoneno")%></div></td>
   </tr>
   <tr>
   <td width="100" colspan="2"><div align="left">About Him : <%= request.getAttribute("description")%> </div></td>
  <table border="0" style="margin-top:300px; margin-left:50px; position:absolute">
<tr>
<th>Recent Results</th>
<tr>
   <td width="300"><div align="left">&bull; <%= request.getAttribute("publicNotes1") %></div></td>
   </tr>
   <tr>
   <td width="300"><div align="left">&bull; <%= request.getAttribute("publicNotes2") %></div></td>
    </tr>
   <tr>
   <td width="300"><div align="left">&bull; <%= request.getAttribute("publicNotes3") %></div></td>
    </tr>
    <tr>
   
   <td width="300"><div align="left"><input type="button" onclick='myFunction("<%= request.getAttribute("publicNotes")%>")' value="See all results"> </div></td>
   </table>
   
 </form>
</body>
</html>
