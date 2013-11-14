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
                stmt.executeUpdate(query);
                query="delete from Match where (player1id="+my_id+" or player2id="+my_id+") and dateofmatch > '"+start_date+"' and dateofmatch < '"+end_date+"' and status='Challenge';";
                stmt.executeUpdate(query);
                
                //******************************************************************
                //If the number of players in the tournament equal the required
                
                
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