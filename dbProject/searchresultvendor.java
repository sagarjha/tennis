package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class searchresultvendor extends HttpServlet{
    public String searchresultvendorHandler (HttpServletRequest request, Connection conn, HttpSession session) throws SQLException{
        String redirectJsp="/profile/playerProfiletoOthers.jsp";
        
        return redirectJsp;
    }
}