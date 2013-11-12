package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class search extends HttpServlet{
    public void searchPlayer(HttpServletRequest request, Connection conn){
        String searchString = request.getParameter("searchString");
        String basis = request.getParameter("basis");
        String order = request.getParameter("order");
        
        
        if(basis.equals("1")){
            
        }
        else if(basis.equals("2")){
            
        }
        else if(basis.equals("3")){
            
        }
        else if(basis.equals("4")){
            
        }
        else if(basis.equals("5")){
            
        }
    }
}