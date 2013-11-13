package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class challengehandler extends HttpServlet{
    public void goToChallengePage(Connection conn, ServletRequest request) throws SQLException{
        System.out.println("in goToChallengePage function in challengehandler.java");
        
        // tennis.java will set these 2 ids while calling the function
        int playerChallengingID = Integer.parseInt(request.getAttribute("accountid").toString());
        int playerChallengedID = Integer.parseInt(request.getAttribute("searchotherid").toString());
        
        List <Integer> clubids = new ArrayList<Integer>();
        List <String> clubnames = new ArrayList<String>();
        List<String> dates = new ArrayList<String>();
        
        Statement stmt = conn.createStatement();
        String query = "";
        
        // get all clubs of the player who challenges
        query = "Select c.id as id, c.name as name from club c, player p, member m where p.id =" + 
                playerChallengingID + " and m.playerid = p.id and m.clubid = c.id;";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()){
            clubids.add(rs.getInt("id"));
            clubnames.add(rs.getString("name"));
        }
        
        request.setAttribute("playerids", clubids);
        request.setAttribute("playernames", clubnames);
        
        // get all dates 7 days from now
        query = "select datevalue as d1, datevalue+1 as d2,datevalue+2 as d3,datevalue+3 as d4,datevalue+4 as d5,datevalue+5 as d6, datevalue+6 as d7 from constant where constantname = 'time';";
        rs = stmt.executeQuery(query);
        if(rs.next()){
            dates.add(rs.getString("d1"));
            dates.add(rs.getString("d2"));
            dates.add(rs.getString("d3"));
            dates.add(rs.getString("d4"));
            dates.add(rs.getString("d5"));
            dates.add(rs.getString("d6"));
            dates.add(rs.getString("d7"));
        }
        request.setAttribute("dates", dates);
    }
}