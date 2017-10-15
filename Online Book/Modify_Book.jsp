<HTML>
<HEAD>
<TITLE>Modify Book</TITLE>
</HEAD>

<BODY bgcolor="#CCFFFF">

	<CENTER>
	<H1> Administering Online Shopping Cart </H1>
	<H2>Modify Book Details</H2>
	</CENTER>
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
		int book_sel = Integer.parseInt(request.getParameter("R1"));

		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con = DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 



		query="Select a.*,b.categoryname from book_details a, category_details b where a.bookid=? and a.categoryid=b.categoryid";
	
		stat = con.prepareStatement(query);
		stat.setInt(1,book_sel);

		rs=stat.executeQuery();
		if(rs.next())
		{
	%>
		<br><h3>Details of Selected Book:</h3>
		<form name="Mod" action="Final_Mod_Book.jsp">
		<input type="hidden" name="categoryid" value="<%=rs.getString(2)%>">
		<table border=1>
		<tr>
		<td>Book Id</td><td><input type="hidden" name="bookid" value="<%=book_sel%>"><%=book_sel%> </td></tr>
		<tr>
		<td>Book Name</td><td><input type="text" name="btitle" value=<%=rs.getString(3)%>></td>
		</tr><tr>
		<td>Author Name</td><td><input type="text" name="bauthor" value=<%=rs.getString(4)%>></td>
		</tr><tr>
		<td>Publisher</td><td><input type="text" name="bpub" value=<%=rs.getString(5)%>></td>
		</tr><tr>
		<td>Edition</td><td><input type="text" name="bedition" value=<%=rs.getString(6)%>></td>
		</tr><tr>
		<td>Price (in Rs.)</td><td><input type="text" name="bprice" value=<%=rs.getString(7)%>></td>
		</tr><tr>
		<td>Quantity Available(Nos.)</td><td><input type="text" name="bqty" value=<%=rs.getString(8)%>></td>
		</tr><tr>
		<td>Description</td><td><input type="text" name="bdesc" value=<%=rs.getString(9)%>></td>
		</tr><tr>
		<td>Category</td><td><input type="text" name="bcat" value=<%=rs.getString(10)%>></td>
		</tr><tr>
		<tr><td><input type="submit" value=" Save Changes "></td><td><input type="Reset" value="    Clear    "></td></tr>
		</table>
		</form>
		<%
		}
	}
		catch(Exception e)
		{
			out.print("Error = " + e + "<HR>");
		}
	%>
</BODY>
</HTML>
