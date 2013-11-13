package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class Accredit extends HttpServlet{
    public void handleAccredition (int id, HttpServletRequest request, Connection conn) throws SQLException{
	System.out.println("In Accredit.java");
	Statement stmt = conn.createStatement();
	String query = "select match.id,dateofmatch,slotnumber,player1.name as player1name, player2.name as player2name from competitive,match,player as player1, player as player2 where player1.id=player1id and player2.id=player2id and competitive.id=match.id and umpireid = " + id + " and status = 'Accreditation Pending';";
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
	    if (value[1].equals("2")) {
		try {
		    conn.setAutoCommit(false);
		
		    String query = "select player1id,player2id,dateofmatch,slotnumber from match where id = " + matchid;
		    System.out.println(query);
		    rs = stmt.executeQuery(query);
		    rs.next();
		    int winnerID = rs.getInt("player1id"), loserID = rs.getInt("player2id");
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
		
		    String winnerRecord = "Defeated " + loserName + "at " + clubName + " on " + date;
		    String loserRecord = "Lost to " + winnerName + "at " + clubName + " on " + date;
		    
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
		    
		    query = "Update Match Set WinnerID =" + winnerID + "where ID = " + matchid;
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
		    
		    query = "Update Constant Set Slotvalue = case when (Datevalue <= '" + date + "' and  Slotvalue <= " + slotofmatch + ") then " + slotofmatch + " else Slotvalue end where ConstantName = 'time'";
		    System.out.println(query);
		    stmt.execute(query);
		    
		    query = "Update Constant Set Datevalue = case when Datevalue < " + date + " then " + date + " else Datevalue end where ConstantName = 'time'";
		    System.out.println(query);
		    stmt.execute(query);
		    
		    String currentDate = "Select Datevalue from Constant where ConstantName = 'time'";
		    String currentSlot = "Select Slotvalue from Constant where ConstantName = 'time'";

		    query = "Update Match Set Status = 'Accreditation Pending' where Match.DateOfMatch < " + currentDate + "OR ( Match.DateOfMatch = " + currentDate + "AND Match.SlotNumber < " + currentSlot + ") AND Match.status = 'Upcoming'";
		    System.out.println(query);
		    stmt.execute(query);
		    
		    query = "Delete from Match where Status = 'Challenge' AND ((Match.DateOfMatch < " + currentDate + ") OR ( Match.DateOfMatch = " + currentDate + " AND Match.SlotNumber < " + currentSlot + "))";
		    System.out.println(query);
		    stmt.execute(query);
		    
		    query = "Select C.TournamentID from Tournament as T, Competitive as  C where C.ID = " + matchid + " AND C.TournamentID = T.ID AND C.Round = log(T.numPlayers)/log(2)";
		    System.out.println(query);
		    rs = stmt.executeQuery(query);
		    while(rs.next()) {
			int tournamentID = rs.getInt("id");
			query = "Delete from Outlet where TournamentID = " + tournamentID;
			System.out.println(query);
			stmt.execute(query);
			query = "Update tournament set winnerid = " + winnerID + ", runnerID = " + loserID + " where ID = " + tournamentID;
			System.out.println(query);
			stmt.execute(query);
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
	    else if (value[1].equals("3")) {
		
	    }
	}

    }
}
