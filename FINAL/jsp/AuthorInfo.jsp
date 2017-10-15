<HTML>
<HEAD><TITLE>Author Information</TITLE></HEAD>
<BODY>

<%@ page language="java" import="java.sql.*" %>
<%! int AuthorID; %>

<H1 ALIGN="center">Your Author Details</H1>

<TABLE WIDTH="100%">
<% 
Connection conn=null;

	Statement stmt = null;
	ResultSet rs;
	rs=null;

AuthorID = Integer.valueOf(request.getParameter("AuthorID")).intValue();

String username = "root";
			String password = "WEBEL";
			String url = "jdbc:mysql://localhost/test";

   Class.forName("com.mysql.jdbc.Driver").newInstance();
   conn = DriverManager.getConnection(url,username,password);
   
    stmt = conn.createStatement();
   String sql = "SELECT FROM AuthorMaster WHERE ID =" + AuthorID ;
   rs = stmt.executeQuery(sql);
 
   rs.next();
%>

 <TR><TD ALIGN="right" WIDTH="50%">Author Name:</TD>
     <TD WIDTH="50%"><%= rs.getString("AuthorName") %></TD>
 </TR>
 <TR><TD ALIGN="right" WIDTH="50%">Address:</TD>
     <TD WIDTH="50%"><%= rs.getString("Address") %></TD>
 </TR>
 <TR><TD ALIGN="right" WIDTH="50%">Degree:</TD>
     <TD WIDTH="50%"><%= rs.getString("Degree") %></TD>
 </TR>
 <TR><TD ALIGN="right" WIDTH="50%">Designation:</TD>
     <TD WIDTH="50%"><%= rs.getString("Designation") %></TD>
 </TR>
 <TR><TD ALIGN="right" WIDTH="50%">PhoneNumber:</TD>
      <TD WIDTH="50%"><%= rs.getString("PhoneNumber") %></TD>
  </TR>

</TABLE>

<BR>

</BODY>
</HTML>
