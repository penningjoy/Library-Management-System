<html>
<body bgcolor="#CCFFFF">
<%@  page language="java" import="java.sql.*"   %>

<%
	Connection conn;
	conn=null;
	ResultSet rs;
	rs=null;

	String FirstName=request.getParameter("FirstName");
	String LastName=request.getParameter("LastName");
	String EmailAddress=request.getParameter("EmailAddress");
	String UserName=request.getParameter("UserName");
	String Password=request.getParameter("Password");
	String AboutMe=request.getParameter("AboutMe");
	
	
	int flag=0;
	
	try
	{
	        String username = "root";
		String password = "WEBEL";
		String url = "jdbc:mysql://localhost/test";
			
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(url,username,password);
		PreparedStatement stat= conn.prepareStatement("SELECT * FROM user_auth where UserName=?" );
		
            stat.setString(1,UserName);
		rs = stat.executeQuery();
            out.println("query executed");
		if(rs.next())
		{
			flag=1;
			%>
			
			<b>User name already exists</b>
			Click<A HREF="/FINAL/login.html"> here</A>
			
			<%
		}
		else
		{
			flag=0; // conn.close();
		}
	}
	catch(Exception E)
	{
		out.println("Error  "+E);
	}
	if(flag == 0)
	{
		try
		{

		PreparedStatement stat1=conn.prepareStatement("INSERT INTO user_auth values(?,?)");
			stat1.setString(1,UserName);
			stat1.setString(2,Password);

			PreparedStatement stat=conn.prepareStatement("INSERT INTO user values(?,?,?,?,?,?)");
			stat.setString(1,FirstName);
			stat.setString(2,LastName);
			stat.setString(3,EmailAddress);
			stat.setString(4,UserName);
			stat.setString(5,Password);
			stat.setString(6,AboutMe);
			stat.executeUpdate(); 
                        stat1.executeUpdate(); 
			flag=0;	
			response.sendRedirect("/FINAL/login.html");
		}
		catch(Exception E)
		{
			out.println("Error inserting value"+E);
		}	
		finally
		{
			rs.close();
			conn.close();
		}
		
	}
	
%>
</body>
</html>
