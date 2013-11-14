package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class challengehandler extends HttpServlet{
    public void goToChallengePage(Connection conn, HttpServletRequest request, HttpSession session){
        System.out.println("in goToChallengePage function in challengehandler.java");
        try{
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

            request.setAttribute("clubids", clubids);
            request.setAttribute("clubnames", clubnames);

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
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    public void challengeDetailsFilled(Connection conn, HttpServletRequest request,HttpSession session ) {
        // check if challenge can be added, if so add the challenge
        System.out.println("in challengeDetailsFilled function in challengehandler.java");
        
        try{
            String type = request.getParameter("type").toString();
            int clubid = Integer.parseInt(request.getParameter("clubid").toString());
            String date = request.getParameter("date").toString();
            int slot = Integer.parseInt(request.getParameter("slot").toString());
            int player1id = Integer.parseInt(session.getAttribute("accountid").toString());
            int player2id = Integer.parseInt(session.getAttribute("searchotherid").toString());
            String displayMessage = "";

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
                // check if players are part of any tournament which also runs on that date
                query = "select p.id from player p , plays , tournament t where p.id = plays.playerid " + 
                        " and (p.id = "+ player1id + " or p.id = " + player2id  + ")" + 
                        " and t.id = plays.tournamentid and t.startdate < '" + date + "' and" +
                        " (select max(m.dateofmatch) from match m, competitive c where m.id = c.id and c.tournamentid = t.id) > '"
                        + date + "' ;";
                rs = stmt.executeQuery(query);
                if(rs.next()){
                    shouldChallengeBeAccepted = 0;
                }
            }

            if(shouldChallengeBeAccepted == 1){
                // check if tournament in that club which also runs on that date
                query = "select t.id from tournament t where t.clubid = " + clubid + 
                        " and t.startdate < '" + date + "' and" +
                        " (select max(m.dateofmatch) from match m, competitive c where m.id = c.id and c.tournamentid = t.id) > '"
                        + date + "' ;";
                rs = stmt.executeQuery(query);
                if(rs.next()){
                    shouldChallengeBeAccepted = 0;
                }
            }

            if(shouldChallengeBeAccepted == 1){
                // check if free court
                int numMatches = 0, numCourts =0;
                    query= "select count(*) as num from match m where m.dateofmatch ='" + date + "' and m.slotnumber = " +
                            slot + " and m.clubid=" + clubid + ";";
                    rs = stmt.executeQuery(query);
                    if(rs.next()){
                        numMatches = rs.getInt("num");
                    }
                    
                    query = "select numcourts from club where club.id = " + clubid + ";";
                    rs = stmt.executeQuery(query);
                    if(rs.next()){
                        numCourts = rs.getInt("numcourts");
                    }
                    
                    if(numCourts <= numMatches){
                        shouldChallengeBeAccepted = 0;
                    }
            }

            if(shouldChallengeBeAccepted == 1){
                // check if free umpire (in case of competitive)
                if(type.equals("2")){
                    int numMatches = 0, numUmpires =0;
                    query= "select count(*) as num from match m where m.dateofmatch ='" + date + "' and m.slotnumber = " +
                            slot + " and m.clubid=" + clubid + ";";
                    rs = stmt.executeQuery(query);
                    if(rs.next()){
                        numMatches = rs.getInt("num");
                    }
                    
                    query = "select count(*) as num from umpire u where u.clubid = " + clubid + ";";
                    rs = stmt.executeQuery(query);
                    if(rs.next()){
                        numUmpires = rs.getInt("num");
                    }
                    
                    if(numUmpires <= numMatches){
                        shouldChallengeBeAccepted = 0;
                    }
                }
            }

            ////////
            if(shouldChallengeBeAccepted == 1){
                // add challenge to match table ....
                query = "select idvalue from constant where constantname= 'match';";
                rs = stmt.executeQuery(query);
                int newmatchid = 0;
                if(rs.next()){
                    newmatchid = rs.getInt("idvalue");
                }
                
                try{
                    
                    conn.setAutoCommit(false);
                    
                    query="Update Constant  set IDValue = IDValue + 1 where ConstantName = 'match';";
                    stmt.executeUpdate(query);	
                    System.out.println(query);
                    
                    if(type.equals("1")){
                        // friendly challenge match
                        query = "insert into match values (" + newmatchid + "," + clubid + ",'" + date + "'," 
                                + slot + "," + player1id + "," + player2id + ",'Challenge',NULL,'Friendly');";
                        stmt.executeUpdate(query);	
                        System.out.println(query);
                    }
                    else{
                        // competitive challenge match
                        query = "insert into match values (" + newmatchid + "," + clubid + ",'" + date + "'," 
                                + slot + "," + player1id + "," + player2id + ",'Challenge',NULL,'Competitive');";
                        stmt.executeUpdate(query);	
                        System.out.println(query);
                        
                        /*query = "insert into competitive values("+ newmatchid + ");";
                        stmt.executeUpdate(query);	
                        System.out.println(query);*/
                    }
                    
                    conn.commit();
                }
                
                catch(Exception e)
                {   
                    System.out.println(e);
                    if (conn != null) 
                    {
                        try 
                        {
                            System.err.print("Transaction is being rolled back");
                            conn.rollback();
                        } 
                        catch(SQLException excep) 
                        {
                            System.out.println(excep);
                        }
                    }
                }
                finally
                {
                    try
                    {
                        conn.setAutoCommit(true);
                    }
                    catch(SQLException excep2)
                    {
                        System.out.println(excep2);
                    }
                }
                
                displayMessage = "You have successfully challenged the player";

            }
            else{
                // not add anything
                displayMessage = "Challenge is not successful. Either club refuses to host the match or the players are not free at that time.";
            }
            System.out.println(displayMessage);
            request.setAttribute("display", displayMessage);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void acceptRejectChallenge(Connection conn, HttpServletRequest request, HttpSession session) throws SQLException{
	System.out.println("In acceptRejectChallenge of challengehandler.java");
	Statement stmt = conn.createStatement();
	int playerID = 1;
	ResultSet rs = stmt.executeQuery ("select M.Player1ID, M.Player2ID, Clb.Name, M.DateOfMatch, M.SlotNumber from Match as M, Club as Clb where ((M.player1ID=" + playerID + ") or (M.Player2ID=" + playerID+ ")) and M.ClubID=Clb.ID and M.Status='Challenge';");
	String pendingChallenges = "";
	while (rs.next()) {
	    pendingChallenges += "<tr>\n <td>\n Sahil\n </td>\n <td>\n Sagar Club\n </td>\n <td>\n 10/11/2013\n <td>\n 13\n </td>\n <td>\n <select>\n <option value=\"1\">select</option>\n <option value=\"2\">Accept</option>\n <option value=\"3\">Reject</option>\n </select>\n </tr>\n </td>";
	}
    }
    
}
