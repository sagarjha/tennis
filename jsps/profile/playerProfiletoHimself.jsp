<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
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
	<p><font color="black"><b><%= request.getAttribute("name") %> </font></b></p>
	<table border="0" style="margin-top:1px; margin-left:1px; position:absolute">
	  <tr>
	    <td width="150"><div align="left"><font color="black">Matches Played</div></td>
	    <td width="100"><div align="left"><font color="black"><%= request.getAttribute("numMatchesPlayed")%> </div></td>
	  </tr>
	  <tr>
	    <td width="150"><div align="left"><font color="black">Won</div></td>
	    <td width="100"><div align="left"><font color="black"><%= request.getAttribute("numMatchesWon")%> </div></td>
	  </tr>
	  <tr>
	    <td width="150"><div align="left"><font color="black">lost</div></td>
	    <td width="100"><div align="left"><font color="black"><%= request.getAttribute("numMatchesLost")%> </div></td>
	  </tr>
	  <tr>
	    <td width="150"><div align="left"><font color="black">Rating</div></td>
	    <td width="100"><div align="left"><font color="black"><%= request.getAttribute("rating")%> </div></td>
	  </tr>
	  <tr>
	    <td width="150"><div align="left"><font color="black">Highest Rating</div></td>
	    <td width="100"><div align="left"><font color="black"><%= request.getAttribute("highestRatingAchieved")%> </div></td>
	  </tr>
	</table>
      </div>


      <div id="Details" style="color:#EEEEEE;height:600px;width:537px;float:right;">
	<table border="0" style="margin-top:50px; margin-left:100px; position:absolute">
	  <tr>
	    <th><font color="black">Upcoming Matches</th>
	  </tr>
	  <tr>
	    <td width="300"><div align="left"><font color="black">&bull; <%= request.getAttribute("UpcomingMatches1") %></div></td>
	  </tr>
	  <tr>
	    <td width="300"><div align="left"><font color="black">&bull; <%= request.getAttribute("UpcomingMatches2") %></div></td>
	  </tr>
	  <tr>
	    <td width="300"><div align="left"><font color="black">&bull; <%= request.getAttribute("UpcomingMatches3") %> </div></td>
	  </tr>
	  <tr>
	    <td width="300"><div align="left"><font color="black"><input type="button" onclick='myFunction("<%= request.getAttribute("UpcomingMatches")%>")' value="See all Upcoming Matches"> </div></td>
	  </tr>
	</table>
	
	<table border="0" style="margin-top:250px; margin-left:100px; position:absolute">
	  <tr>
	    <th><font color="black">Club News</th>
	  </tr>
	  <tr>
	    <td width="400"><div align="left"><font color="black">&bull; <%= request.getAttribute("news1") %> </div></td>
	  </tr>
	  <tr>
	    <td width="400"><div align="left"><font color="black">&bull; <%= request.getAttribute("news2") %> </div></td>
	  </tr>
	  <tr>
	    <td width="400"><div align="left"><font color="black">&bull; <%= request.getAttribute("news3") %> </div></td>
	  </tr>
	  
	  <tr>
	    <td width="400"><div align="left"><input type="button" onclick='myFunction("<%= request.getAttribute("news")%>")' value="See More"> </div></td>
	  </tr>
	</table>
	
	



<!---------------------------------------------Register for coaching----------------------->


<!--	<div id = 'Coaching' style="margin-top:400px; margin-left:100px; position:absolute"> <a href="http://www.yahoo.com">Register for Coaching</a></div>	-->

<table border="0" style="margin-top:400px; margin-left:100px; position:absolute">	
<tr>
   <!--CLubs -->
   <td width="50"><div align="left"><font color="black">Choose Club</div></td>

   <td width="200"><input list="newclub" name="newclub">
   <datalist id="newclub">
<%
    List<Integer> clubids = (List<Integer>)request.getAttribute("clubids");
    List<String> clubnames = (List<String>)request.getAttribute("clubnames");
    for(int i = 0; i < clubids.size(); i++){ %>
  <option value="<%= clubids.get(i)%>"><%= clubnames.get(i)%></option>
  <% } %>
</datalist>
   </td>
   </tr>
	  
	  <tr>
   <!--Coaches -->
      <td width="50"><div align="left"><font color="black">Choose Coach</div></td>

   <td width="200"><input list="newcoach" name="newcoach">
   <datalist id="newcoach">
<%
    List<Integer> coachids = (List<Integer>)request.getAttribute("coachids");
    List<String> coachnames = (List<String>)request.getAttribute("coachnames");
    for(int i = 0; i < coachids.size(); i++){ %>
  <option value="<%= coachids.get(i)%>"><%= coachnames.get(i)%></option>
  <% } %>
</datalist>
   </td>
   </tr>
	  
	  <tr>

  <td width="399"><br><input type="Submit" name="PLAYERADDCOACHINGSLOT" value = "Add Coaching Slot"></td>
   </tr>
   </table> 


<div id = 'Challenges'  style="margin-top:550px; margin-left:100px; position:absolute "> <button type="Submit" name="SEEPENDINGCHALLENGES" value = "See Pending Challenges"> See Pending Challenges

	</div>
	<div id = 'See Tournaments' style="margin-top:580px; margin-left:100px; position:absolute "> <br><input type="Submit" name="VIEWTOURNAMENTSPAGE" value = "View Tournaments Page"></td>
</div>
<div id = 'See Tournaments' style="margin-top:20px; margin-left:360px; position:absolute "> <a href="../search/search.jsp">Search Page</a>
</div>
<div id = 'See Tournaments' style="margin-top:20px; margin-left:460px; position:absolute "> <input type="Submit" name="LOGOUT" value = "Logout">
</div>
</div>


<div id="content" style="color:#EEEEEE;height:600px;width:500px;float:left;">


  <table border="0" style="margin-top:1px; margin-left:1px; position:absolute">
    <tr>
      
      <td width="350"><div align="left"><h2><font color="black"><%= request.getAttribute("name")%></h2></div></td>
      <td width="200"><div align="left"> <input type="button" onclick='myFunction("<%= request.getAttribute("privateNotes")%>")' value="My Notes"> </div></td>
      <!-- <a href="http://www.yahoo.com">My Notes</a> -->
    </tr>
    <tr>
      <td width="100"><div align="left"><font color="black"><%= request.getAttribute("gender")%></div></td>
      <td width="100"><div align="left"><font color="black"><%= request.getAttribute("age")%> years</div></td>
</td>
</tr>
<tr>
  <td width="100" colspan="2"><div align="left"><font color="black"><%= request.getAttribute("address")%> </div></td>
</tr>
<tr>
  <td width="100" colspan="2"><div align="left"><font color="black">Contact : <%= request.getAttribute("emailid")%> </div></td>
</tr>
<tr>
  <td width="100" colspan="2"><div align="left"><font color="black">Phone : <%= request.getAttribute("phoneno")%> </div></td>
</tr>
<tr>
  <td width="100" colspan="2"><div align="left"><font color="black">About Me : <%= request.getAttribute("description")%> </div></td></tr>
</table>

<table border="0" style="margin-top:300px; margin-left:50px; position:absolute">
  <tr>
    <th><font color="black">Recent Results</th>
  <%
if(request.getAttribute("publicNotes1")!=null)
{
%>
  <tr>
    <td width="300"><div align="left"><font color="black">&bull; <%= request.getAttribute("publicNotes1") %></div></td>
  </tr>
  <%
}
if(request.getAttribute("publicNotes2")!=null)
{
%>
  <tr>
    <td width="300"><div align="left"><font color="black">&bull; <%= request.getAttribute("publicNotes2") %></div></td>
  </tr>
  <%
}
if(request.getAttribute("publicNotes3")!=null)
{
%>
  <tr>
    <td width="300"><div align="left"><font color="black">&bull; <%= request.getAttribute("publicNotes3") %></div></td>
  </tr>
  <%
}
%>
  <tr>
    
    <td width="300"><div align="left"><input type="button" onclick='myFunction("<%= request.getAttribute("publicNotes")%>")' value="See all results"> </div></td>
</table>
<div id = 'Coaching' style="margin-top:440px; margin-left:50px; position:absolute"> <font color="black"> &bull;<%= request.getAttribute("club1") %> </font></div>
<div id = 'Coaching' style="margin-top:460px; margin-left:50px; position:absolute"> <font color="black">&bull; <%= request.getAttribute("club2") %> </font></div>
<div id = 'Coaching' style="margin-top:480px; margin-left:50px; position:absolute"> <font color="black"> &bull; <%= request.getAttribute("club3") %> </font></div>
<div id = 'Coaching' style="margin-top:500px; margin-left:50px; position:absolute"> <input type="button" onclick='myFunction("<%= request.getAttribute("allclubs")%>")' value="View all clubs">
</div>
<div id = 'Coaching' style="margin-top:530px; margin-left:50px; position:absolute"> <a href="Functionality/addprivaterecord.jsp">Add Private Records</a>
</div>
</div>
</form>
</body>
</html>
