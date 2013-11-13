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
    <form name="searchresultvendor" method ="post" action="../tennis">
    <%
    List<Integer> ids = (List<Integer>)request.getAttribute("vendorids");
    List<String> names = (List<String>)request.getAttribute("vendornames");
    for(int i = 0; i < ids.size(); i++){ %>
    <center><button type="Submit" name="SEARCHRESULTVENDOR" value="<%= ids.get(i)%>"><%= names.get(i)%></button></center>
    <br>
    <% } %>
    </form>
  </body>
</html>
