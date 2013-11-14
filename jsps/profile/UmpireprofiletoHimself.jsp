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
    <form method="POST" action="../tennis">
 <div id="menu" style="color:#EEEEEE;height:600px;width:300px;float:left;">
   <img style="display:block;" left = '400'   width='250' height='300' src= <%= request.getAttribute("profilePicUrl") %> />
<div style="margin-left:75px; margin-top:0px;"><p><%= request.getAttribute("name")%></p></div>

<table border="0" style="margin-top:1px; margin-left:1px; position:absolute">
<tr>
   <td width="150"><div align="left">Club</div></td>
   <td width="100"><div align="left"><%= request.getAttribute("clubname")%></div></td>
   </tr>
   <tr>
   <td width="150"><div align="left">Experience</div></td>
   <td width="100"><div align="left"><%= request.getAttribute("experience")%> Years</div></td>
   </tr>
   
   </table>
</div>


<div id="Details" style="color:#FFD700;height:600px;width:537px;float:right;">
<table border="0" style="margin-top:50px; margin-left:100px; position:absolute">
<tr>
<th>Upcoming Matches</th>
<% if(request.getAttribute("match1") != null){ %>
<tr>
   <td width="300"><div align="left">&bull;<%= request.getAttribute("match1")%></div></td>
   </tr>
<% } 
if( request.getAttribute("match2") != null){ %>    
   <tr>
   <td width="300"><div align="left">&bull;<%= request.getAttribute("match2")%></div></td>
    </tr>
 <% } 
if( request.getAttribute("match3") != null){ %>    
   <tr>
   <td width="300"><div align="left">&bull;<%= request.getAttribute("match3")%></div></td>
    </tr>
    
 <% } 
if( request.getAttribute("match4") != null){ %>    
   <tr>
   <td width="300"><div align="left">&bull;<%= request.getAttribute("match4")%></div></td>
    </tr>
   
 <% } 
if( request.getAttribute("match5") != null){ %>    
   <tr>
   <td width="300"><div align="left">&bull;<%= request.getAttribute("match5")%></div></td>
    </tr>         
  <% } %>  
   <tr>
   <td width="300"><div align="left"><input type="button" onclick='myFunction("<%= request.getAttribute("allMatches")%>")' value="See More"></div></td>
   
   </tr>
  </table>
  
  <table border="0" style="margin-top:350px; margin-left:100px; position:absolute">
   <td width="300"><div align="left"><br><input type="Submit" name="AccreditUmpire" value = "Matches to be Accredited"> </div></td>
   </tr>
  </table>
  
  
 <div id = 'See Tournaments' style="margin-top:20px; margin-left:360px; position:absolute "> <a href="../search/search.jsp">Search Page</a>
  </div>
   <div id = 'See Tournaments' style="margin-top:20px; margin-left:460px; position:absolute "> <input type="Submit" name="LOGOUT" value = "Logout">
  </div>
</div>


<div id="content" style="color:#EEEEEE;height:600px;width:500px;float:left;">

<table border="0" style="margin-top:1px; margin-left:1px; position:absolute">
<tr>
   
   <td width="350"><div align="left"><h2> <%= request.getAttribute("name") %> </h1></div></td>
   
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
   <td width="100" colspan="2"><div align="left">About Me : <%= request.getAttribute("description")%></div></td>
   </tr>
   </table>
   
   
<div id = 'Tournament' style="margin-top:530px; margin-left:100px; position:absolute"> <td width="399"><br><input type="Submit" name="VIEWTOURNAMENTSPAGE" value = "View Tournaments Page"></td>
  
  
  </div>
    </form>
  </body>
</html>
  
  
  
  
