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

<img style="display:block;" left = '400' width='250' height='300' src= <%= request.getAttribute("profilePicUrl") %> />


<div style="margin-left:75px; margin-top:0px;"><p><%= request.getAttribute("name")%></p></div>



<table border="0" style="margin-top:1px; margin-left:1px; position:absolute">

<tr>
   <td width="150"><div align="left">Rating</div></td>


   <td width="100"><div align="left"><%= request.getAttribute("rating")%></div></td>


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
   <td width="100" colspan="2"><div align="left">Contact : <%= request.getAttribute("emailid")%></div></td>


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
<td width="200" colspan="2" align="center"><b>His Clubs</b></th><td>


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
<!-- <td width="400"><div align="center"><a href="http://www.yahoo.com">See More</a></div></td> -->


     <td width="400"><div align="left"> <input type="button" onclick='myFunction("<%= request.getAttribute("moreclubs")%>")' value="More Clubs"> </div></td>


   
   
   </tr>
  <table border="0" style="margin-top:450px; margin-left:-40px; position:absolute">


<tr>




<!-- Rate the coach---------------------------------------------------------------------->
<tr>
        <td width="100%" colspan="3"><div align="center"><b>Rate this Coach </b></div></td>


        
        </tr>
        <tr>
   <td width="150"><div align="center">Choose rating</div></td>


   <td width="200"><div align="center"><select>

  <option value="volvo">1</option>
  <option value="saab">2</option>
  <option value="mercedes">3</option>


  <option value="audi">4</option>
        </select> </div></td>
        <td width="150"><div align="center"><Button>Go</Button></div></td>


   
   
   </tr>
   
   
  </table>
   
  
 
  
  </div>
