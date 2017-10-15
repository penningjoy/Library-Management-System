<HTML>
<HEAD>
<TITLE>Modify Book Details</TITLE>
</HEAD>

<BODY bgcolor="#CCFFFF">

	<CENTER>
	<H1> Administering Online Shopping Cart </H1>
	<H2>Modify Book Details</H2>
	</CENTER>
	<BR>
	<HR>
	<BR>
	<BR>
	<a href="Logout.jsp" ><font size="4"  >Click here to Logout</font></a>
	
	<%@ page errorPage="errorpage.jsp" import="java.net.*" %>
	<%@ page import="java.io.*" %>
	<%@ page import="java.sql.*" %>
	<%
		Connection con = null;
		
		PreparedStatement stat = null;
			
		ResultSet rs = null;
		String query=null;
		int ctr=0;
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con = DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 
		
			
		query="Select a.*,b.categoryname from book_details a, category_details b where a.categoryid=b.categoryid";
		stat = con.prepareStatement(query);
		
		rs=stat.executeQuery();
	%>
		<br><h3>List of Available Books:</h3>
		<form name="Mod" action="Modify_Book.jsp">
		<table border=1>
		<tr>
		<td>S.No</td>
		<td>Book Id</td>
		<td>Book Name</td>
		<td>Author Name</td>
		<td>Publisher</td>
		<td>Edition</td>
		<td>Price (in Rs.)</td>
		<td>Quantity Available(Nos.)</td>
		<td>Description</td>
		<td>Category</td>
		</tr>
		<% 
		int int_BOOKID,int_CATEGORYID,int_PRICE,int_QUANTITY;
		String str_TITLE,str_AUTHOR,str_PUBLISHER,str_EDITION,str_DESCRIPTION;
		while(rs.next())
		{
			ctr=ctr+1;
			out.println("<tr><td>"+ctr);
			int_BOOKID=rs.getInt(1);
			out.println("<td>");
			out.println("<input type=radio name=R1 value="+int_BOOKID+">");
					
			int_CATEGORYID=rs.getInt(2);
						
			str_TITLE=rs.getString(3);
			out.println("<td>");
			out.println(str_TITLE);
			
			str_AUTHOR=rs.getString(4);
			out.println("<td>");
			out.println(str_AUTHOR);
				
			str_PUBLISHER=rs.getString(5);
			out.println("<td>");
			out.println(str_PUBLISHER);
			
			str_EDITION=rs.getString(6);
			out.println("<td>");
			out.println(str_EDITION);
			
			int_PRICE=rs.getInt(7);
			out.println("<td>");
			out.println(int_PRICE);
			
			int_QUANTITY=rs.getInt(8);
			out.println("<td>");
			out.println(int_QUANTITY);

			str_DESCRIPTION=rs.getString(9);
			out.println("<td>");
			out.println(str_DESCRIPTION);

			out.println("<td>");
			out.println(rs.getString(10));
			
			out.println("</tr>");
		}
	%>
		<tr>
			<td><input type="submit" value="   Modify    "></td>
			<td><input type="Reset" value="   Clear    "></td>
		</tr>
		</table>
		</form>
	<%
		}
		catch(Exception e)
		{
		out.print("Error = " + e + "<HR>");
	}
	%>
</BODY>
</HTML>
