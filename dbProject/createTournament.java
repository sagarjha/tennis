package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class createTournament extends HttpServlet{
    
    public void handleScheduleTournament (Connection conn, HttpServletRequest request) throws SQLException{
	System.out.println("In handleScheduleTournament.java");
	String date;
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery("Select datevalue from constant where constantname='time'");
	rs.next();
	date = rs.getString("datevalue");
	String dateOptions = "";
	for (int i = 16; i <= 30; ++i) {
	    rs = stmt.executeQuery("Select date '" + date + "' + integer '" + i + "' as futureDate");
	    rs.next();
	    dateOptions += "<option value='" + rs.getString("futureDate") + "'>" + rs.getString("futureDate") + "</option>\n";
	}

	System.out.println(dateOptions);
	request.setAttribute("validDates",dateOptions); 
	
    }
    
    public boolean handleCreateTournament (Connection conn, ServletRequest request) throws SQLException {
	System.out.println("In createTournament.java");
	int clubid = Integer.parseInt(request.getParameter("CREATETOURNAMENT"));
	int numRounds = Integer.parseInt(request.getParameter("round"));
	System.out.println("The value of numRounds is " + numRounds);
	int numPlayers;
	if (numRounds == 3) {
	    numPlayers = 8;
	}
	else if (numRounds == 4) {
	    numPlayers = 16;
	}

	else {
	    numPlayers = 32;
	}

	int startingSlot = Integer.parseInt(request.getParameter("startingSlot"));
	String startDate = request.getParameter("startDate");

	Statement stmt = conn.createStatement();
	Statement stmt1 = conn.createStatement();
	ResultSet rs = stmt.executeQuery("select idvalue from constant where constantname='match';");
	rs.next();
	int matchid = rs.getInt("idvalue");
	rs = stmt.executeQuery("select idvalue from constant where constantname='tournament';");
	rs.next();
	int tournamentid = rs.getInt("idvalue");
	stmt = conn.createStatement();
	
	String insert = genTournMatches(numRounds,startingSlot,startDate,matchid, stmt, stmt1,request,tournamentid);
	System.out.println(insert);
	String query = "select count(*) as cnt from tournament t where t.clubid=" + clubid + " and not ((select max(m.dateofmatch) from match m, competitive c where m.id = c.id and c.tournamentid = t.id) < '" + startDate + "') and not (t.startdate > (date '" + startDate + "' + integer '" + request.getAttribute("span") + "'));";
	System.out.println(query);
	rs = stmt.executeQuery(query);
	rs.next();
	if (rs.getInt("cnt") != 0) {
	    return false;
	}
	query = "Insert into Tournament values( " + tournamentid + ", 'Mumbai Open', '" + startDate + "', " + numPlayers + ", 20000, " + startingSlot + ", NULL, NULL, " + clubid + ")";
	System.out.println(query);
	stmt.execute(query);

	String[] insertStatements = insert.split(";");
	try{
	    conn.setAutoCommit(false);

	    for (int i = 0; i < insertStatements.length; ++i) {
		stmt.execute(insertStatements[i]);
	    }

	    query = "Update constant set idvalue = idvalue + " + (numPlayers-1) + " where constantname='match'";
	    System.out.println(query);
	    stmt.execute(query);

	    query = "Update constant set idvalue = idvalue + " + 1 + " where constantname='tournament'";
	    System.out.println(query);
	    stmt.execute(query);

	    conn.commit();
	}

	catch (Exception e) {
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

	finally {
	    try {
		conn.setAutoCommit(true);
	    }
	    catch(SQLException excep2) {
		System.out.println(excep2);
	    }
	}
	
	return true;
    }
    
    String genTournMatches (int numRounds, int startingSlot, String startDate, int matchid, Statement stmt, Statement stmt1, ServletRequest request, int tournamentid) throws SQLException{

	System.out.println("In genTournMatches");

	int clubid = Integer.parseInt(request.getParameter("CREATETOURNAMENT"));
	String query = "Select * from club where id = " + clubid;
	System.out.println(query);
	ResultSet rs = stmt.executeQuery(query);
	query = "Select id from umpire where clubid = " + clubid;
	System.out.println(query);
	ResultSet rs1 = stmt1.executeQuery(query);
	
	rs.next();
	rs1.next();

	String insert = "";
	int id = rs.getInt("id");
	int courts = rs.getInt("numcourts");
	
	int numPlayers;
	if (numRounds == 3) {
	    numPlayers = 8;
	}
	else if (numRounds == 4) {
	    numPlayers = 16;
	}

	else {
	    numPlayers = 32;
	}

	int dayT = 0;
	int round = 1;
	while (numPlayers != 1) {
	    int courtsOccupied = 0;
	    int runningSlot = startingSlot;
	    for (int i = 0; i < numPlayers; i+=2) {
		insert += "insert into match (id,clubid,dateofmatch,slotnumber,status,matchtype) values (" +  matchid + ", " + id + ", (select date '" + startDate + "' + integer '" + dayT + "'), " + runningSlot + ", 'Upcoming', 'Competitive');\n";
		insert += "insert into competitive (id, umpireid, round, tournamentid) values (" + matchid + "," + rs1.getInt("id") + ", " + round + ", " + tournamentid + ");\n";
		System.out.println(insert);
		if (courtsOccupied == courts | !rs1.next()) {
		    // reset rs1
		    query = "Select id from umpire where clubid = " + id;
		    System.out.println(query);
		    rs1 = stmt1.executeQuery(query);
		    rs1.next();
		    
		    courtsOccupied = 0;
		    if (runningSlot == 14) {
			runningSlot = startingSlot;
			dayT++;
		    }
		    else
			runningSlot++;
		}
		matchid++;
	    }
	    courtsOccupied = 0;
	    dayT++;
	    runningSlot = startingSlot;
	    round++;
	    // reset rs1
	    query = "Select id from umpire where clubid = " + id;
	    System.out.println(query);
	    rs1 = stmt1.executeQuery(query);
	    rs1.next();
	    numPlayers /= 2;
	}
	request.setAttribute ("span",dayT);
	return insert;
    }
}
