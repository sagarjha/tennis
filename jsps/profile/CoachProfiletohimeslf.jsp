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
<div style="margin-left:75px; margin-top:0px;"><p><%= request.getAttribute("name")%></p></div>

<table border="0" style="margin-top:1px; margin-left:1px; position:absolute">
<tr>
   <td width="150"><div align="left">Rating</div></td>
	 <td width="100"><div align="left"><%= request.getAttribute("rating")%> </div></td>
   </tr>
   <tr>
   <td width="150"><div align="left">Experience</div></td>
	 <td width="100"><div align="left"><%= request.getAttribute("experience")%> Years</div></td> 
   </tr>
   
   </table>
</div>


<div id="Details" style="color:#FFD700;height:600px;width:537px;float:right;">

<div id = 'See Tournaments' style="margin-top:20px; margin-left:360px; position:absolute "> <a href="../search/search.jsp">Search Page</a>
  </div>
   <div id = 'See Tournaments' style="margin-top:20px; margin-left:460px; position:absolute "> <input type="Submit" name="LOGOUT" value = "Logout">
  </div>
  <table border="0" style="margin-top:250px; margin-left:100px; position:absolute">
<tr>
<th>Club News</th>
<%
if(request.getAttribute("news1")!=null)
{
%>
<tr>
   <td width="400"><div align="left">&bull; <%= request.getAttribute("news1")%></div></td>
   </tr>
<%
}
if(request.getAttribute("news2")!=null)
{
%>
   <tr>
   <td width="400"><div align="left">&bull; <%= request.getAttribute("news2")%></div></td>
    </tr>
<%
}
if(request.getAttribute("news3")!=null)
{
%>
   <tr>
   <td width="400"><div align="left">&bull; <%= request.getAttribute("news3")%></div></td>
    </tr>
<%
}
%>   
   <tr>
   <td width="400"><div align="left"> <input type="button" onclick='myFunction("<%= request.getAttribute("morenews")%>")' value="More News"> </div></td>
   <!--<td width="400"><div align="left"><a href="http://www.yahoo.com">See More</a></div></td> -->
   
   
   </tr>
  </table>
  
  
  
  
</div>


<div id="content" style="color:#EEEEEE;height:600px;width:500px;float:left;">

<table border="0" style="margin-top:1px; margin-left:1px; position:absolute">
<tr>
   
   <td width="350"><div align="left"><h2><%= request.getAttribute("name")%></h1></div></td>
   
   </tr>
   <tr>
   <td width="100"><div align="left"><%= request.getAttribute("gender")%></div></td>
   <td width="100"><div align="left"><%= request.getAttribute("age")%> Years</div></td>
   </td>
   </tr>
   <tr>
   <td width="100" colspan="2"><div align="left"><%= request.getAttribute("address")%></div></td>
</tr>
   <tr>
   <td width="100" colspan="2"><div align="left">Contact :  <%= request.getAttribute("emailid")%></div></td>
   </tr>
   <tr>
   <td width="100" colspan="2"><div align="left">Phone : <%= request.getAttribute("phoneno")%></div></td>
   </tr>
   <tr>
   <td width="100" colspan="2"><div align="left">About Him : <%= request.getAttribute("description")%></div></td>
   </tr>
   </table>
   
   
  
  
  <table border="0" style="margin-top:300px; margin-left:0px; position:absolute">
<tr>
<td width="200" colspan="2" align="center"><b>Your Clubs</b></th><td>
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
<!--   <td width="400"><div align="center"><a href="http://www.yahoo.com">See More</a></div></td> -->
     <td width="400"><div align="left"> <input type="button" onclick='myFunction("<%= request.getAttribute("moreclub")%>")' value="More Clubs"> </div></td>
   
   
   </tr>
  </table>
  <table border="0" style="margin-top:450px; margin-left:-40px; position:absolute">
<tr>

<tr>
	<td width="100%" colspan="3"><div align="center"><b>Add a Coaching Slot</b></div></td>
	
	</tr>
	<tr>
   <td width="150"><div align="center">Choose Club</div></td>
<!--   <td width="200"><div align="center"><select>
  <option value="volvo">Dadar Gymkhana</option>
  <option value="saab">IIT Bombay</option>
  <option value="mercedes">Bombay Gymkhana</option>
  <option value="audi">Wimbeldon</option>
	</select> </div></td>
	<td width="150"><div align="center"><Button>Add Coaching Slot</Button></div></td>
   
   

   -->
   <td width="200"><input list="newclub" name="newclub" >
   <datalist id="newclub">
<%
    List<Integer> ids = (List<Integer>)request.getAttribute("coachids");
    List<String> names = (List<String>)request.getAttribute("coachnames");
    for(int i = 0; i < ids.size(); i++){ %>
  <option value="<%= ids.get(i)%>"><%= names.get(i)%></option>
  <% } %>
</datalist>
   </td>
<!--   	<td width="150"><div align="center"><Button>Add Coaching Slot</Button></div></td> -->
  <td width="399"><br><input type="Submit" name="addcoachingslot" value = "Add Coaching Slot"></td>
   </tr>   
  </table>
   
 <div id = 'Tournament' style="margin-top:530px; margin-left:100px; position:absolute"> <a href="../tournament/tournament.jsp">View Upcoming Tournaments</a>
  
  </div>
  </form>
  </body>
  </html>
  
