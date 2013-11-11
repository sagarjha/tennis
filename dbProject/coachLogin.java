package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

class coachLogin extends HttpServlet{
    public void coachLoginHandler (int id, HttpServletRequest request, ResultSet rs, Statement stmt, Connection conn) throws SQLException{
	// set various attributes required for displaying the correct information on the profile page
        int coachingStartYear = rs.getInt("coachingstartyear");
        int yearOfbirth = rs.getInt("yearOfBirth");
        request.setAttribute("name",rs.getString("name"));		    
	request.setAttribute("gender",rs.getString("gender"));
//	request.setAttribute("age",2013 - rs.getInt("yearOfBirth"));
	request.setAttribute("address", rs.getString("address"));
	request.setAttribute("emailid", rs.getString("emailid"));
	request.setAttribute("phoneno", rs.getString("phonenum"));
	request.setAttribute("description", rs.getString("description"));

	request.setAttribute("rating",rs.getString("rating"));

	// More Club and More News
	{
	    String query = "select clubid from teachesat where coachid="+id+";";
	    System.out.println(query);
	    rs = stmt.executeQuery (query);
            Statement stmt2 = conn.createStatement();
            Statement stmt3 = conn.createStatement();
	    String records = "";
            String record_news="";
            int clubid=0;
            int club1id=0;
            int club2id=0;
            int club3id=0;
            String club=null;
            String club1=null;
            String club2=null;
            String club3=null;
            String news=null;
            String news1=null;
            String news2=null;
            String news3=null;
            
	    int count = 1;
            int count2=1;    //for news
            String clubName="";
            String clubNews="";
	    while (rs.next()) {
                clubid=rs.getInt("clubid");
                clubName="select name from club where id="+clubid+";";
                clubNews="select description from news where clubid="+clubid+";";
                ResultSet rs2=stmt2.executeQuery(clubName);
                ResultSet rs3=stmt3.executeQuery(clubNews);
                if(rs2.next())
                {
                    //Club Name Part
                    club=rs2.getString("name");
                    if(count==1)
                    {
                        club1id=rs.getInt("clubid");
                        club1=rs2.getString("name");
                        request.setAttribute("club1",club1);
                    }
                    if(count==2)
                    {
                        club2id=rs.getInt("clubid");
                        club2=rs2.getString("name");
                        request.setAttribute("club2",club2);
                    }
                    if(count==3)
                    {
                        club3id=rs.getInt("clubid");
                        club3=rs2.getString("name");
                        request.setAttribute("club3",club3);
                    }
                    records += count + ". " + club + "\\n";
                    count++;
                }
                
                //News for each club
                while(rs3.next())
                {
                    news=rs3.getString("description");
                    if(count2==1)
                    {
                        news1=rs3.getString("description");
                        request.setAttribute("news1",news1);
                    }
                    if(count2==2)
                    {
                        news2=rs3.getString("description");
                        request.setAttribute("news2",news2);
                    }
                    if(count2==3)
                    {
                        news3=rs3.getString("description");
                        request.setAttribute("news3",news3);
                    }
                    record_news+= count2+". "+ news + "\\n";
                    count2++;
                }
                
	    }
	    System.out.println(records);
            request.setAttribute("morenews",record_news);
	    request.setAttribute("moreclub",records);
	}
        
        //Calculating Age and Experience
        {
            String query = "Select extract(year from (select datevalue from constant where constantname = 'time')) as year;";
            rs=stmt.executeQuery(query);
            if(rs.next())
            {
                request.setAttribute("age", rs.getInt("year") - yearOfbirth);
                request.setAttribute("experience",rs.getInt("year") - coachingStartYear);
            }
        }
        
	
	
    }
}
