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

  
  
  
  
  
</div>


<div id="content" style="color:#EEEEEE;height:600px;width:500px;float:left;">

<table border="0" style="margin-top:1px; margin-left:1px; position:absolute">
<tr>
   
   <td width="350"><div align="left"><h2><%= request.getAttribute("name") %></h1></div></td>
   
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
   <td width="100" colspan="2"><div align="left">About Her :<%= request.getAttribute("description")%></div></td>
   </tr>
   </table>
  
  </div>
  
  </form>
  </body>
  </html>
  
