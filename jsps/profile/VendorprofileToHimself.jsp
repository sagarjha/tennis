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
  
 <div id="menu" style="color:#EEEEEE;height:600px;width:300px;float:left;">
   <img style="display:block;" left = '400'   width='250' height='300' src= <%= request.getAttribute("profilePicUrl") %> />
<div style="margin-left:75px; margin-top:0px;"><p><%= request.getAttribute("name")%></p></div>
</div>


<div id="Details" style="color:#FFD700;height:600px;width:537px;float:right;">

  
  <table border="0" style="margin-top:50px; margin-left:50px; position:absolute">
<tr>
<caption>Available Products</caption>


<tr>
	<td width="33%"><div align="center"><b>Item</b></div></td>
	<td width="33%"><div align="center"><b>Brand</b></div></td>
	<td width="33%"><div align="center"><b>Price</b></div></td>
	</tr>
<% if(request.getAttribute("type1") != null){ %>	
	<tr>
   <td width="150"><div align="center"> <%= request.getAttribute("type1") %></div></td>
   <td width="150"><div align="center"> <%= request.getAttribute("brand1") %></div></td>
   <td width="150"><div align="center"> <%= request.getAttribute("price1") %></div></td>
   </tr>
<% } 
if( request.getAttribute("type2") != null){ %>  
   <tr>
   <td width="150"><div align="center"> <%= request.getAttribute("type2") %></div></td>
   <td width="150"><div align="center"> <%= request.getAttribute("brand2") %></div></td>
   <td width="150"><div align="center"> <%= request.getAttribute("price2") %></div></td>
    </tr>
<% } 
if( request.getAttribute("type3") != null){ %>    
   <tr>
   <td width="150"><div align="center"> <%= request.getAttribute("type3") %></div></td>
   <td width="150"><div align="center"> <%= request.getAttribute("brand3") %></div></td>
   <td width="150"><div align="center"> <%= request.getAttribute("price3") %></div></td>
    </tr>
<% } %>    
    <tr>
   <td width="450"><div align="center"><input type="button" onclick='myFunction("<%= request.getAttribute("allItems")%>")' value="See More"> </div></td>
   </tr>
   
  </table>
  
  <table border="0" style="margin-top:200px; margin-left:50px; position:absolute">
<tr>

<tr>
	<td width="100%"><div align="center"><b>Manage Your List</b></div></td>
	
	</tr>
	<tr>
   <td width="450"><div align="center"><a href="../Functionality/additem.jsp">Add Item</a></div></td>
   
   </tr>
   <tr>
   <td width="450"><div align="center"><a href="../tournament/tournament.jsp">Change Price</a></div></td>
   
    </tr>
   
  </table>
  
  <div id = 'See Tournaments' style="margin-top:20px; margin-left:360px; position:absolute "> <a href="../search/search.jsp">Search Page</a>
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
   <td width="100" colspan="2"><div align="left">Contact : <%= request.getAttribute("emailid")%> </div></td>
   </tr>
   <tr>
   <td width="100" colspan="2"><div align="left">Phone : <%= request.getAttribute("phoneno")%></div></td>
   </tr>
   <tr>
   <td width="100" colspan="2"><div align="left">About Us : <%= request.getAttribute("description")%> </div></td>
   </tr>
   </table>
   
   
   
   
   
   <div id = 'Tournament' style="margin-top:530px; margin-left:100px; position:absolute"> <a href="../tournament/tournament.jsp">View Upcoming Tournaments</a>
  
  
  
  </div>
  
  
  
  
