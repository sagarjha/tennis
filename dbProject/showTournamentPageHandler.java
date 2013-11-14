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
	rs = stmt.executeQuery(query);
	rs.next();
	request.setAttribute("club",rs.getString("name"));
        
        int my_id=Integer.parseInt(session.getAttribute("accountid").toString());
        
        //Show register button if player
        //Checking if it is a player
        query="select * from player where id="+my_id+";";
        rs = stmt.executeQuery(query);
        System.out.println(query);
        request.setAttribute("registerstall",0);
        if(rs.next())
        {
            System.out.println("aaaaaaah");
            //Checking if the tournament belongs to a club in which the player is a member
            query="select * from member as m,tournament as t where m.playerid="+my_id+" and t.clubid=m.clubid and t.id="+id+";";
            rs = stmt.executeQuery(query);
            System.out.println(query);
            if(rs.next())
            {
                //See if the player is taking part in any tournament (inlcuding this) during the period
                String start_date="";
                String end_date="";
                query="select max(m.dateofmatch) as enddate from match as m, competitive as c where m.id=c.id and m.status='Upcoming' and c.tournamentid="+id+";";
                rs = stmt.executeQuery(query);
                System.out.println(query);
                rs.next();
                end_date=rs.getString("enddate");
                System.out.println("Printing end date:"+end_date);
                query="select startdate from tournament where id="+id+";";
                rs = stmt.executeQuery(query);
                System.out.println(query);
                rs.next();
                start_date=rs.getString("startdate");
                
                
                //Checking matches which belong in that period
                query="select * from match where (player1id="+my_id+" or player2id="+my_id+") and (dateofmatch < '"+end_date+"' and dateofmatch > '"+start_date+"');";
                rs = stmt.executeQuery(query);
                System.out.println(query);
                if(rs.next()){}
                else
                {
                    //Check if there is any overlapping tournament in which the player has registered
                    query="select t.id from plays p, tournament t where p.playerid = "+my_id+" and p.tournamentid = t.id and not ( t.startdate < '"+start_date+"' and (select max(m.dateofmatch) from match m, competitive c where m.id = c.id and c.tournamentid = t.id) <'"+ start_date+"') and not (t.startdate > '"+end_date+"' and (select max(m.dateofmatch) from match m, competitive c where m.id = c.id and c.tournamentid = t.id) > '"+end_date+"');";
                    rs = stmt.executeQuery(query);
                    System.out.println(query);
                    if(rs.next()){}
                    else
                    {
                        //Check if the there are some empty seats for players in the tournament
                        query="select count(*) as num from plays where tournamentid="+id+";";
                        rs = stmt.executeQuery(query);
                        System.out.println(query);
                        int num_players=rs.getInt("num");
                        query="select numplayers from tournament where id="+id+";";
                        System.out.println(query);
                        rs = stmt.executeQuery(query);
                        int cap_players=rs.getInt("numplayers");
                        if((cap_players-num_players)>0)
                        {
                            
                            request.setAttribute("registerstall",1);        //1 means register button for player  
                        }
                                              
                    }

                }
                
                
            }
        }
        
        //Show add stall button if vendor
        query="select * from vendor where id="+my_id+";";
        rs = stmt.executeQuery(query);
        System.out.println(query);
        if(rs.next())
        {
            query="Select * from Outlet where vendorID ="+ my_id+" and TournamentID ="+ id+";";
            rs = stmt.executeQuery(query);
            System.out.println(query);
            if(rs.next()){}
            else
                request.setAttribute("registerstall",2);        //2 means register button for vendor
        }
    }
}
