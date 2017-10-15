<%@  page errorPage="errorpage.jsp" language="java"  import="java.sql.*"  %>
<html>
<head>
<title>Search Results</title>
</head>
<body bgcolor="#CCFFFF">

<CENTER>
<H1> <u>Online Shopping Cart</u> </H1>
<font size="5" >Search Results</font>
</CENTER>
<BR>
<a href="Logout.jsp" ><font size="4"  >Click here to Logout</font></a>

<%
	
	String str_searchvar=null;
	int chk_ctr=0;
	String str_colvar=null;
	String tab_var="";
	String user_src=(String)session.getValue("user");
	System.out.println("0");	
	if (user_src!=null)
   	{
		String query=null;
		Connection conn;
		conn=null;
		ResultSet rs;
		rs=null;
		if(request.getParameter("R1").equals("Title"))
		{
			str_searchvar=request.getParameter("BookTitle");
			str_colvar="TITLE";
		}
		if(request.getParameter("R1").equals("Category"))	
		{
			chk_ctr=1;
			str_searchvar=request.getParameter("BookCategory");
			str_colvar="Categoryname";
		}
		if(request.getParameter("R1").equals("Publisher"))	
		{
			str_searchvar=request.getParameter("BookPublisher");
			str_colvar="Publisher";
		}
		if(request.getParameter("R1").equals("Author"))
		{
			str_searchvar=request.getParameter("BookAuthor");
			str_colvar="Author";
		}		
		if (request.getParameter("R2")==null)
		{
			tab_var="BOOK_DETAILS";		
		}
		else
		{
		// to get the name of the table in which the search to be done-------
			if (request.getParameter("R2").equals("A"))
			{
				tab_var="TEMP_DETAIL";
			}
			else
			{
				tab_var="BOOK_DETAILS";
			}
		}


	System.out.println(str_searchvar);
       	query = "select a.* from "+tab_var+" a, category_details b where a."+str_colvar+" = ? and a.CATEGORYID = b.CATEGORYID"; 
	System.out.println("1");
	try
	{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	conn =DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 
	int ctr=0;
	System.out.println("2");
	PreparedStatement stm = conn.prepareStatement(query);
	stm.setString(1,str_searchvar);
	rs = stm.executeQuery();
%>

			<form name="cart" action="Inter_Cart.jsp" method="POST">
			<table border=1>
			<tr>
			<td>S.No</td>
			<td>Select</td>
			<td>Book Name</td>
			<td>Author Name</td>
			<td>Publisher</td>
			<td>Edition</td>
			<td>Price (in Rs.)</td>
			<td>Quantity Available(Nos.)</td>
			<td>Description</td>
			</tr>

<%
	while(rs.next())
	{
	System.out.println("inside while");
	ctr=ctr+1;
	out.println("<tr><td>"+ctr+"</td>");
	int int_BOOKID=0;
	int_BOOKID=rs.getInt(1);
	System.out.println(int_BOOKID);	
	out.println("<td><input type=checkbox name=chk"+ctr+" value="+int_BOOKID+">");
				
	int int_CATEGORYID=rs.getInt(2);
					
	String str_TITLE=rs.getString(3);
	out.println("<td>");
	out.println(str_TITLE);
	out.println("</td>");

	String str_AUTHOR=rs.getString(4);
	out.println("<td>");
	out.println(str_AUTHOR);
	out.println("</td>");
		
	
	String str_PUBLISHER=rs.getString(5);
	out.println("<td>");
	out.println(str_PUBLISHER);
	out.println("</td>");
	
		
	String str_EDITION=rs.getString(6);
	out.println("<td>");
	out.println(str_EDITION);
	out.println("</td>");
		
	int int_PRICE=rs.getInt(7);
	out.println("<td>");
	out.println(int_PRICE);
	out.println("</td>");
		
	int int_QUANTITY=rs.getInt(8);
	out.println("<td>");
	out.println(int_QUANTITY);
	out.println("</td>");

	String str_DESCRIPTION=rs.getString(9);
	out.println("<td>");
	out.println(str_DESCRIPTION);
	out.println("</td></tr>");

	String ins_query ="INSERT INTO temp_detail VALUES (?,?,?,?,?,?,?,?,?)";
	
	PreparedStatement stat_ins= conn.prepareStatement(ins_query);
	stat_ins.setInt(1,int_BOOKID);
	stat_ins.setInt(2,int_CATEGORYID);
	stat_ins.setString(3,str_TITLE);
	stat_ins.setString(4,str_AUTHOR);
	stat_ins.setString(5,str_PUBLISHER);
	stat_ins.setString(6,str_EDITION);
	stat_ins.setInt(7,int_PRICE);
	stat_ins.setInt(8,int_QUANTITY);
	stat_ins.setString(9,str_DESCRIPTION); 

	int x = stat_ins.executeUpdate();	
	System.out.println(x);

	}	
		
			String str_ctr=String.valueOf(ctr);
			session.putValue("ctr_val",str_ctr);

%>
	</table>
	<br>
	<input type="submit" value="    Add to Cart    " name="Add">	
	</form>

<%

	rs.close();
	stm.close();
	}
	catch(Exception e){}
	}
	else
		{
			response.sendRedirect("Unauthorised.htm");
		}
%>
</body>
</html>	