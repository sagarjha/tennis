package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class Accredit extends HttpServlet{
    public void handleAccredition (int id, HttpServletRequest request, Connection conn) throws SQLException{
	System.out.println("In Accredit.java");
	Statement stmt = conn.createStatement();
	String query = "select match.id,dateofmatch,slotnumber,player1.name as player1name, player2.name as player2name from competitive,match,player as player1, player as player2 where player1.id=player1id and player2.id=player2id and competitive.id=match.id and umpireid = " + id + " and (status = 'Accreditation Pending' or status = 'Upcoming') order by dateofmatch;";
	System.out.println(query);
	
	ResultSet rs = stmt.executeQuery(query);
	
	String toBeAccredited = "";

	int count = 0;
	
	while (rs.next()) {
	    count++;
	    toBeAccredited += "<tr>\n <td>\n" + rs.getString("player1name") + "\n </td>\n <td>\n " + rs.getString("player2name") + "\n </td>\n <td>\n " + rs.getString("dateofmatch") + "\n </td>\n <td>\n " + rs.getString("slotnumber") + "\n </td>\n <td>\n <select id = \"" + count + "\" name = \"match" + count + "\">\n <option value=\"" + rs.getString("id") + ":1" + "\">select</option>\n <option value=\"" + rs.getString("id") + ":2" + "\">Player1</option>\n <option value=\"" + rs.getString("id") + ":3" + "\">Player2</option>\n </select>\n </td>\n </tr>\n";
	}
	request.setAttribute("accreditationRows",toBeAccredited);
    }
    
    public void handleAccreditionOfMatches (int id, HttpServletRequest request, Connection conn) throws SQLException{
	System.out.println("In handleAccreditionOfMatches of Accredit.java");

	Statement stmt = conn.createStatement();
	ResultSet rs;
	
	int count = 1;
	
	while (request.getParameter("match" + count) != null) {
	    String[] value = request.getParameter("match" + count).toString().split(":");
	    count++;
	    int matchid = Integer.parseInt(value[0]);
	    if (!value[1].equals("1")) {
		try {
		    conn.setAutoCommit(false);
		    String query = "select player1id,player2id,dateofmatch,slotnumber from match where id = " + matchid;
		    System.out.println(query);
		    rs = stmt.executeQuery(query);
		    rs.next();
		    int winnerID = rs.getInt("player1id"), loserID = rs.getInt("player2id");
		    if (value[1].equals("3")) {
			winnerID = rs.getInt("player2id");
			loserID = rs.getInt("player1id");
		    }
		    String date = rs.getString("dateofmatch");
		    int slotofmatch = rs.getInt("slotnumber");
		
		    query = "Update Match Set Status = 'Completed' where ID = " + matchid;
		    System.out.println(query);
		    stmt.execute(query);
		
		    query = "Select IDValue from Constant where ConstantName = 'record'";
		    rs = stmt.executeQuery(query);
		    rs.next();
		    int numRecords = rs.getInt("IDValue");

		    query = "select Clb.Name as clubname from Club as Clb, Match as M where M.ID=" + matchid + "and M.ClubID=Clb.ID";
		    System.out.println(query);
		    rs = stmt.executeQuery(query);
		    rs.next();
		    String clubName = rs.getString("clubname");
				
		    query = "select P.Name from Player as P where P.ID=" + winnerID;
		    System.out.println(query);
		    rs = stmt.executeQuery(query);
		    rs.next();
		    String winnerName = rs.getString("name");
		
		    query = "select P.Name from Player as P where P.ID=" + loserID;
		    System.out.println(query);
		    rs = stmt.executeQuery(query);
		    rs.next();
		    String loserName = rs.getString("name");
		
		    String winnerRecord = "Defeated " + loserName + " at " + clubName + " on " + date;
		    String loserRecord = "Lost to " + winnerName + " at " + clubName + " on " + date;
		    
		    query = "Insert into Record values(" + numRecords + ", 'Public', '" + winnerRecord + "', " + winnerID + ")";
		    System.out.println(query);
		    stmt.execute(query);
		    numRecords++;

		    query = "Insert into Record values(" + numRecords + ", 'Public', '" + loserRecord + "', " + loserID + ")";
		    System.out.println(query);
		    stmt.execute(query);
		    numRecords++;

		    query = "Update Constant set IDValue = " + numRecords + " where ConstantName = 'record'";
		    System.out.println(query);
		    stmt.execute(query);
		    
		    query = "Update Match Set WinnerID =" + winnerID + " where ID = " + matchid;
		    System.out.println(query);
		    stmt.execute(query);

		    query = "Update Player Set NumMatchesPlayed = NumMatchesPlayed +1 where ID = " + winnerID;
		    System.out.println(query);
		    stmt.execute(query);
		    
		    query = "Update Player Set NumMatchesWOn = NumMatchesWon +1 where ID = " + winnerID;
		    System.out.println(query);
		    stmt.execute(query);
		    
		    query = "Update Player Set NumMatchesPlayed = NumMatchesPlayed +1 where ID =" + loserID;
		    System.out.println(query);
		    stmt.execute(query);

		    query = "Update Player Set highestratingachieved = greatest(rating+1,highestratingachieved) where ID =" + winnerID;
		    System.out.println(query);
		    stmt.execute(query);
		    
		    query = "Update Player Set rating = rating +1 where ID =" + winnerID;
		    System.out.println(query);
		    stmt.execute(query);
		    
		    query = "Update Player Set rating = rating -1 where rating > 1 and ID =" + loserID;
		    System.out.println(query);
		    stmt.execute(query);

		    query = "Update Constant Set Slotvalue = case when (Datevalue <= '" + date + "' and  Slotvalue <= " + slotofmatch + ") then " + slotofmatch + " else Slotvalue end where ConstantName = 'time'";
		    System.out.println(query);
		    stmt.execute(query);
		    
		    query = "Update Constant Set Datevalue = case when Datevalue < '" + date + "' then '" + date + "' else Datevalue end where ConstantName = 'time'";
		    System.out.println(query);
		    stmt.execute(query);
		    
		    query = "Select Datevalue from Constant where ConstantName = 'time'";
		    System.out.println(query);
		    rs = stmt.executeQuery(query);
		    rs.next();
		    String currentDate = rs.getString("Datevalue");

		    query = "Select Slotvalue from Constant where ConstantName = 'time'";
		    System.out.println(query);
		    rs = stmt.executeQuery(query);
		    rs.next();
		    String currentSlot = rs.getString("Slotvalue");

		    query = "Update Match Set Status = 'Accreditation Pending' where (Match.DateOfMatch < '" + currentDate + "'OR ( Match.DateOfMatch = '" + currentDate + "' AND Match.SlotNumber < " + currentSlot + ")) AND Match.status = 'Upcoming' and id <> " + matchid;
		    System.out.println(query);
		    stmt.execute(query);
		    
		    query = "Delete from Match where Status = 'Challenge' AND ((Match.DateOfMatch < '" + currentDate + "') OR ( Match.DateOfMatch = '" + currentDate + "' AND Match.SlotNumber < " + currentSlot + "))";
		    System.out.println(query);
		    stmt.execute(query);
		    
		    // check if the match being accredited is part of some tournament
		    query = "Select C.TournamentID,T.numplayers,C.Round from Tournament as T, Competitive as  C where C.ID = " + matchid + " AND C.TournamentID = T.ID";
		    System.out.println(query);
		    rs = stmt.executeQuery(query);
		    if (rs.next()) {
			int tournamentID = rs.getInt("tournamentid");
			int round = rs.getInt("round");
			int numPlayers = rs.getInt("numPlayers");
			// Check if the tournament ends with this
			if ((numPlayers == 8 & round == 3) | (numPlayers == 16 & round == 4) | (numPlayers == 32 & round == 5)) {
			    query = "Delete from Outlet where TournamentID = " + tournamentID;
			    System.out.println(query);
			    stmt.execute(query);
			    query = "Update tournament set winnerid = " + winnerID + ", runnerupID = " + loserID + " where ID = " + tournamentID;
			    System.out.println(query);
			    stmt.execute(query);
			}
			else {
			    // check if the round has ended
			    query = "select count(*) as cnt from match, competitive where match.id=competitive.id and winnerid is null and tournamentid = " + tournamentID +" and round = " + round;
			    System.out.println(query);
			    Statement stmt1 = conn.createStatement();    
			    ResultSet rs1 = stmt1.executeQuery(query);
			    rs1.next();
			    int count1 = rs1.getInt("cnt");
			    if (count1 == 0) {
				query = "select winnerid from match, competitive where match.id=competitive.id and tournamentid = " + tournamentID +" and round = " + round;
				System.out.println(query);
				rs1 = stmt1.executeQuery(query);
				query = "Select min(m.id) as minmatchid from match m, competitive c where m.id=c.id and c.round = " + (round + 1) + " and c.tournamentid = " + tournamentID;
				System.out.println(query);
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(query);
				rs2.next();
				int minmatchid = rs2.getInt("minmatchid");
				int runningMatchID = minmatchid;
				while(rs1.next()) {
				    int pid1 = rs1.getInt("winnerid");
				    rs1.next();
				    int pid2 = rs1.getInt("winnerid");
				    query = "update match set player1id = " + pid1 + ", player2id = " + pid2 + " where id = " + runningMatchID;
				    System.out.println(query);
				    stmt2.execute(query);
				    runningMatchID++;
				}
			    }
			}
		    }

		    conn.commit();
		    
		}
		
		catch(SQLException e)
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
	    }
	}
    }
}
