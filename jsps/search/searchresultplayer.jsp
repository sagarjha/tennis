<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Search results</title>
  </head>

  <body>  
    <h3 align="center">Search results</h3><hr>
    <form name="searchresultplayer" method ="post" action="../tennis">
    <%
    List<Integer> ids = (List<Integer>)request.getAttribute("playerids");
    List<String> names = (List<String>)request.getAttribute("playernames");
    for(int i = 0; i < ids.size(); i++){ %>
    <center><button type="Submit" name="SEARCHRESULTPLAYER" value="<%= ids.get(i)%>"><%= names.get(i)%></button></center>
    <br>
    <% } %>
    </form>
  </body>
</html>
