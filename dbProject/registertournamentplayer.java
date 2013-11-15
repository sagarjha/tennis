package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class registertournamentplayer extends HttpServlet{
    public String registertournamentplayerHandler (Connection conn, ServletRequest request, HttpSession session) throws SQLException{
            
            String redirectJsp="/signupAndLogin/operationsuccessful.jsp";
            String query=";";
            int tournamentid=Integer.parseInt(session.getAttribute("tournamentid").toString());
            int my_id=Integer.parseInt(session.getAttribute("accountid").toString());
            Statement stmt = conn.createStatement();
            String start_date=session.getAttribute("tStartDate").toString();
            String end_date=session.getAttribute("tEndDate").toString();
            try
            {
                conn.setAutoCommit(false);
                query="Insert into Plays values("+my_id+", "+tournamentid+");";
		System.out.println(query);
                stmt.executeUpdate(query);
		System.out.println(query);
                query="delete from Match where (player1id="+my_id+" or player2id="+my_id+") and dateofmatch > '"+start_date+"' and dateofmatch < '"+end_date+"' and status='Challenge';";
                stmt.executeUpdate(query);
                
                //******************************************************************
                //If the number of players in the tournament equal the required
		query = "Select numPlayers from tournament where id = " + tournamentid;
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		int numPlayers = rs.getInt("numplayers");
		query = "Select count(*) as cnt from plays where tournamentid = " + tournamentid;
		rs = stmt.executeQuery(query);
		rs.next();
		int numParticipants = rs.getInt("cnt");

		if (numParticipants == numPlayers) {
		    int minMatchID,maxMatchID;
		    query = "Select min(m.id) as minmatchid from match m, competitive c where m.id=c.id and c.tournamentid = " + tournamentid;
		    System.out.println(query);
		    rs = stmt.executeQuery(query);
		    rs.next();
		    minMatchID = rs.getInt("minmatchid");
		    query = "Select max(m.id) as maxmatchid from match m, competitive c where m.id=c.id and c.tournamentid = " + tournamentid;
		    System.out.println(query);
		    rs = stmt.executeQuery(query);
		    rs.next();
		    maxMatchID = rs.getInt("maxmatchid");

		    query = "Select playerid,rating from player,plays where plays.playerid = player.id and plays.tournamentid = " + tournamentid + " order by rating";
		    System.out.println(query);
		    rs = stmt.executeQuery(query);
		    Statement stmt1 = conn.createStatement();
		    int runningMatchID = minMatchID;
		    while(rs.next()) {
			int pid1 = rs.getInt("playerid");
			rs.next();
			int pid2 = rs.getInt("playerid");
			query = "update match set player1id = " + pid1 + ", player2id = " + pid2 + " where id = " + runningMatchID;
			System.out.println(query);
			stmt1.execute(query);
			runningMatchID++;
		    }
		    
		}
                
                //******************************************************************
                
                
                
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
            return redirectJsp;
    }
}
