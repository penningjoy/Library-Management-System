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
<%@page language="java"  import="java.sql.*"  %>


<%
 	out.println("Starting search......");
	
	String str_searchvar=null;
	int chk_ctr=0;
	String str_colvar=null;
	String tab_var="";
	String user_src=(String)session.getValue("user");
	out.println(user_src);	

	if (user_src!=null)
   	{
		out.println(user_src);

		String query=null;
		Connection conn;
		conn=null;
		ResultSet rs;
		rs=null;

	
		if(request.getParameter("R1").equals("Title"))
		{
			str_searchvar=request.getParameter("BookTitle");
			str_colvar="Title";
			out.println("Inside title.........." + str_searchvar);                     
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

		tab_var="BOOK_DETAILS";
	





/* not in use	else
		{  
		// to get the name of the table in which the search to be done-------
			System.out.println("inside else for table selection");
			System.out.println(request.getParameter("R2"));
			if (request.getParameter("R2").equals("A"))
			{
				tab_var="TEMP_DETAIL";
			}
			else
			{
				tab_var="BOOK_DETAILS";
			}
		}
	*/


		//-------- whether search is by category or other criteria-----------
		out.println(chk_ctr);
		if (chk_ctr==0)
		{
			out.println("Inside counter,..............." + chk_ctr);
		//	System.out.println(tab_var+ str_colvar+str_searchvar);
		//	query="SELECT a.*,b.categoryname FROM "+tab_var+" a,CATEGORY_DETAILS b where UPPER(a."+str_colvar+")=UPPER('"+str_searchvar+"') and a.categoryid=b.categoryid";
			query="SELECT a.*,b.categoryname FROM book_details a,CATEGORY_DETAILS b where UPPER(a.Title)=UPPER(?) and a.categoryid=b.categoryid";
		//	query="SELECT a.* FROM book_details a";
		//	System.out.println("query written");
		}
		else
		{
			out.println("Else Inside counter,..............." + chk_ctr);
			//  query="SELECT a.*,b.categoryname FROM book_details a,CATEGORY_DETAILS b where UPPER(a.Title)=UPPER(?) and a.categoryid=b.categoryid";
			//  query="SELECT a.*,b.categoryname FROM "+tab_var+" a,CATEGORY_DETAILS b where UPPER(b."+str_colvar+")=UPPER('"+str_searchvar+"') and a.categoryid=b.categoryid";                         
		} 
	}
	
	
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection conn =DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 
			PreparedStatement stat = conn.prepareStatement(query);
		//	stat.setString(1,"C");		
		//	Statement stat= conn.createStatement();
			Statement stat_del= conn.createStatement( );
			Statement stat_ins= conn.createStatement( );
		//      System.out.println("connection established");
			out.println("Connection established.............");
			rs = stat.executeQuery();
			
		//	System.out.println(rs.next());
		//	System.out.println("query executed");
  			int ctr=0;  
	}catch(Exception e){}
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
			<td>Category</td>
			</tr>
		<%  /*
			ResultSet rs1=null;
			String delete_query="Delete from TEMP_DETAIL";
			String ins_query=null;
			int int_BOOKID,int_CATEGORYID,int_PRICE,int_QUANTITY;
			String str_TITLE,str_AUTHOR,str_PUBLISHER,str_EDITION,str_DESCRIPTION;
			
			int rs_del=stat_del.executeUpdate(delete_query);
			
			System.out.println("printing records");
			rs.next();
	//		System.out.println(rs.getString(3));		

		while(rs.next())
		{
			System.out.println("inside while");
			ctr=ctr+1;
			out.println("<tr><td>"+ctr);
			int_BOOKID=0;
			int_BOOKID=rs.getInt(1);
			System.out.println(int_BOOKID);	
			out.println("<td><input type=checkbox name=chk"+ctr+" value="+int_BOOKID+">");
					
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
			ins_query="INSERT INTO TEMP_DETAIL VALUES("+int_BOOKID+","+int_CATEGORYID+",'"+str_TITLE+"','"+str_AUTHOR+"','"+str_PUBLISHER+"','"+str_EDITION+"',"+int_PRICE+","+int_QUANTITY+",'"+str_DESCRIPTION+"')";
			int rs_ins=stat_ins.executeUpdate(ins_query);
			}
		
			String str_ctr=String.valueOf(ctr);
			session.putValue("ctr_val",str_ctr);
			Statement stat_combo = conn.createStatement();
			ResultSet rscombo=null;
			String query1="select * from category_details";
			rscombo=stat_combo.executeQuery(query1);   
	*/	%></table>
			<br>
			<input type="submit" value="    Add to Cart    " name="Add">
			</form>
			<HR>
			<p ><font size="5">Search More</font></p>
			<form name="newsrc" action="Search.jsp" method="POST">
			<table border=1 >
			<tr>	
			<td><input type="radio" value="Category" checked name="R1"></td>
			<td>Search based on Category</td>
			<td>
	  		<select size="1" name="BookCategory">
      			<option selected value="Select">Select Category</option>
			<% /*
			while(rscombo.next())
			{
				String category=rscombo.getString(2);
				*/ %>
    			<option value="<%//= category%>"><%//= category%></option>
  			<% 
  // }

%>
    			</select>
			</td>	
			</tr>
			<tr>
			<td>
			<tr>
      				<td><input type="radio" name="R1" value="Title"></td>
     	 			<td>Search by Book Title</td>
      				<td><input type="text" name="BookTitle" size="25"></td>
    			</tr>
    			<tr>
        			<td><input type="radio" name="R1" value="Author"></td>
      				<td>Search by Book Author</td>
      				<td><input type="text" name="BookAuthor" size="25"></td>
    			</tr>
    			<tr>
      				<td><input type="radio" name="R1" value="Publisher"></td>
     				<td>Search by Book Publisher</td>
      				<td><input type="text" name="BookPublisher" size="25"></td>
    			</tr>
    	
    			<tr>
    				<td><input type="radio" name="R2" value="A">Advanced Search</td>
    				<td><input type="radio" name="R2" value="N" checked>New Search</td>
    				<td><input type=submit value="Search"></td>
    			</tr>    

			</table>
	
			</form>			
			<%  /*   }
 			catch(Exception E)
			{
				out.println("Error  "+E);
			}
			finally
			{
				rs.close();
				conn.close();
			}
		}
	
		else
		{
			response.sendRedirect("Unauthorised.htm");
		}
   */	%>
</body>
</html>
