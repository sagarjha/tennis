<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Search results</title>
    <link href="search/style.css" rel="stylesheet" type="text/css">
  </head>

  <body>  
    <h3 align="center">Search results</h3>
    <form name="searchresultcoach" method ="post" action="../tennis">
    <td width="399"><br><button type="Submit" name="LOGIN" value = "Login">Back to Profile</button></td>
    <hr>
    <%
    List<Integer> ids = (List<Integer>)request.getAttribute("coachids");
    List<String> names = (List<String>)request.getAttribute("coachnames");
    for(int i = 0; i < ids.size(); i++){ %>
    <center><button type="Submit" name="SEARCHRESULTCOACH" value="<%= ids.get(i)%>"><%= names.get(i)%></button></center>
    <br>
    <% } %>
    </form>
  </body>
</html>
