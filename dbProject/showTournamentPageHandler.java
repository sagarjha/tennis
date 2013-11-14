package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class showTournamentPageHandler extends HttpServlet{
    public void handleShow (Connection conn, ServletRequest request, HttpSession session) throws SQLException{
	System.out.println("In showTournamentPageHandler.java");
	Statement stmt = conn.createStatement();
	
	String id = request.getParameter("TOURNAMENTVIEW");
	session.setAttribute("tournamentid",id);
	String query = "select * from tournament where id =" + id + ";";
	System.out.println(query);
	ResultSet rs = stmt.executeQuery(query);
	rs.next();
	request.setAttribute("name",rs.getString("name"));
	request.setAttribute("startdate",rs.getString("startDate"));
	request.setAttribute("numplayers",rs.getString("numplayers"));
	request.setAttribute("prizemoney",rs.getString("prizemoney"));
	Statement stmt1 = conn.createStatement();
	query = "select name from club where id=" + rs.getString("clubid");
	System.out.println(query);
	ResultSet rs1 = stmt.executeQuery(query);
	rs1.next();
	request.setAttribute("club",rs1.getString("name"));
        
        int my_id=Integer.parseInt(session.getAttribute("accountid").toString());
        
        //Show register button if player
        query="select * from player where id="+my_id+";";
        rs = stmt.executeQuery(query);
        request.setAttribute("registerstall",0);
        if(rs.next())
            request.setAttribute("registerstall",1);        //1 means register button for player
        
        
        
        //Show add stall button if vendor
        query="select * from vendor where id="+my_id+";";
        rs = stmt.executeQuery(query);
        if(rs.next())
        {
            query="Select * from Outlet where vendorID ="+ my_id+" and TournamentID ="+ id+";";
            rs = stmt.executeQuery(query);
            if(rs.next()){}
            else
                request.setAttribute("registerstall",2);        //2 means register button for vendor
        }
    }
}
