package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class addcoachingslotplayer extends HttpServlet{
    public String addcoachingslotplayerHandler (HttpServletRequest request, HttpSession session, Connection conn) throws SQLException{
        System.out.println("Add coaching slot player");
        String redirectJsp="/signupAndLogin/operationsuccessful.jsp";
        String sameJsp="/profile/playerProfiletoHimself.jsp";
        String query="";
        
        String clubid=request.getParameter("newclub");
        String coachid=request.getParameter("newcoach");
        int my_id=Integer.parseInt(session.getAttribute("accountid").toString());
        int flag=0; //If this stays 0 till the end then we dont update;
        
        Statement stmt = conn.createStatement();
        query="select * from teachesat where coachid="+coachid+" and clubid="+clubid+";";
        ResultSet rs = stmt.executeQuery (query);
        if(rs.next())
        {
            flag=1;
            System.out.println("1");
        }
        
        if(flag==1)
        {
            try
            {

                /*query="Select * from Member where PlayerID = PID and ClubID = myClubID;";
                ResultSet rs = stmt.executeQuery (query);
                if(rs.next())
                {
                    flag=1;
                }

                query="Select * from TeachesAt where CoachID = myCoachID and ClubID = myClubID;";
                rs = stmt.executeQuery (query);
                if(rs.next())
                {
                    flag=1;
                }
                */
 
                conn.setAutoCommit(false);
                query="Insert into Training values ("+my_id+", "+coachid+", "+clubid+");";
                stmt.executeUpdate(query);
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
        }
        else        //redirect back to the profile and show the error message that coach does not belong to the club chosen
        {
            session.setAttribute("coachnotinclub",1);
            return sameJsp;
        }
        return redirectJsp;
    }
}