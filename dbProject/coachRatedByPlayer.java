package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class coachRatedByPlayer extends HttpServlet{
    public void coachRated (HttpServletRequest request, Connection conn, HttpSession session) throws SQLException{
        int playerid = Integer.parseInt(session.getAttribute("accountid").toString());
        int coachid =  Integer.parseInt(session.getAttribute("searchotherid").toString());
        String rating = request.getParameter("coachrating").toString();
        String query = "";
        Statement stmt = conn.createStatement();
        ResultSet rs;
        
           
            if(rating.equals("1")){
                query = "Update coach set rating = rating - 20 where id = " + coachid + ";";
            }
            if(rating.equals("2")){
                query = "Update coach set rating = rating - 10 where id = " + coachid + ";";
            }
            if(rating.equals("4")){
                query = "Update coach set rating = rating + 10 where id = " + coachid + ";";
            }
            if(rating.equals("5")){
                query = "Update coach set rating = rating + 20 where id = " + coachid + ";";
            }
            if(rating.equals("3")){
                query = "Update coach set rating = rating where id = " + coachid + ";";
            }
            System.out.println(query);
            stmt.executeUpdate(query);
           
    }
}