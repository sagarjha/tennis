package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class challengehandler extends HttpServlet{
    public void goToChallengePage(Connection conn, HttpServletRequest request, HttpSession session) throws SQLException{
        System.out.println("in goToChallengePage function in challengehandler.java");
        
        // tennis.java will set these 2 ids while calling the function
        int playerChallengingID = Integer.parseInt(session.getAttribute("accountid").toString());
        int playerChallengedID = Integer.parseInt(session.getAttribute("searchotherid").toString());
        
        List <Integer> clubids = new ArrayList<Integer>();
        List <String> clubnames = new ArrayList<String>();
        List<String> dates = new ArrayList<String>();
        
        Statement stmt = conn.createStatement();
        String query = "";
        
        // get all clubs of the player who challenges
        query = "Select c.id as id, c.name as name from club c, player p, member m where p.id =" + 
                playerChallengingID + " and m.playerid = p.id and m.clubid = c.id;";
        System.out.println(query);
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()){
            clubids.add(rs.getInt("id"));
            clubnames.add(rs.getString("name"));
        }
        
        request.setAttribute("playerids", clubids);
        request.setAttribute("playernames", clubnames);
        
        // get all dates 7 days from now
        query = "select datevalue+1 as d1, datevalue+2 as d2,datevalue+3 as d3,datevalue+4 as d4,datevalue+5 as d5,datevalue+6 as d6, datevalue+7 as d7 from constant where constantname = 'time';";
        System.out.println(query);
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
    
    
    public void challengeDetailsFilled(Connection conn, HttpServletRequest request,HttpSession session ) throws SQLException{
        // check if challenge can be added, if so add the challenge
        System.out.println("in challengeDetailsFilled function in challengehandler.java");
        String type = request.getParameter("type").toString();
        int clubid = Integer.parseInt(request.getParameter("clubid").toString());
        String date = request.getParameter("date").toString();
        int slot = Integer.parseInt(request.getParameter("slot").toString());
        int player1id = Integer.parseInt(session.getAttribute("accountid").toString());
        int player2id = Integer.parseInt(session.getAttribute("searchotherid").toString());
        
        String query = "";
        Statement stmt = conn.createStatement();
        ResultSet rs;
        int shouldChallengeBeAccepted = 1;
        
        // check if both the players are free at that time
        query = "select * from match where (player1id = " + player1id + " or player2id = " + player1id + 
                " ) and dateofmatch = '" + date + "' and slotnumber = " + slot + " and status = 'Upcoming';";
        System.out.println(query);
        rs = stmt.executeQuery(query);
        if(rs.next()){
            shouldChallengeBeAccepted = 0;
        }
        
        if(shouldChallengeBeAccepted == 1){
            query = "select * from match where (player1id = " + player2id + " or player2id = " + player2id + 
                    " ) and dateofmatch = '" + date + "' and slotnumber = " + slot + " and status = 'Upcoming';";
            System.out.println(query);
            rs = stmt.executeQuery(query);
            if(rs.next()){
                shouldChallengeBeAccepted = 0;
            }
        }
        
        if(shouldChallengeBeAccepted == 1){
            // check if tournament in that club
        
        }
        
        if(shouldChallengeBeAccepted == 1){
            // check if free court
        
        }
        
        if(shouldChallengeBeAccepted == 1){
            // check if free umpire (in case of competitive)
        }
        
    }
    
}