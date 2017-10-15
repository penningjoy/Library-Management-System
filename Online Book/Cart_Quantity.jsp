<html>

<head>
<title>Shopping Cart</title>
</head>

<body bgcolor="#CCFFFF">
	
	<CENTER>
	<H1> <u>Online Shopping Cart</u> </H1>
	</CENTER>
	<BR>
	<a href="Logout.jsp" ><font size="4"  >Click here to Logout</font></a>
	<%@  page errorPage="errorpage.jsp" language="java"  import="java.sql.*"  %>
	<%
	int i;
	String user_src=null;
	user_src=(String)session.getValue("user");
	if (user_src!=null)
   	{
	   int counter=0;	
	   counter=Integer.parseInt((String)session.getValue("TotalSel"));%>		
		
	<script language="Javascript">
	function check_sum()
	{
		var cost=0;
		x_avlqty=new Array(<%=counter%>);
		y_orderqty=new Array(<%=counter%>);
		z_price=new Array(<%=counter%>);
		for (var i=0;i<<%=counter%>;i++)
		{
			z_price[i]=(document.shop.elements[i*4].value);
			x_avlqty[i]=document.shop.elements[i*4+1].value;
			y_orderqty[i]=document.shop.elements[i*4+2].value;
			if(parseInt(y_orderqty[i])<0)
			{
				alert("Book quantity cannot be Negative");
				return false;
			}			
			if (parseInt(x_avlqty[i])<parseInt(y_orderqty[i]))
			{
				alert("Order Quantity Cannot be greater than available quantity");
				return false;
			}
			else
			{
				document.shop.elements[i*4+3].value=((parseInt(y_orderqty[i]))*(parseInt(z_price[i])));
				cost=cost+parseInt(document.shop.elements[i*4+3].value);
			}
		}
		
		document.shop.tot_val.value=cost;
	}
	</script>
	<%
		int[] bookid=new int[counter];
		Connection conn;
		conn=null;
		ResultSet rs;
		rs=null;
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		conn =DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 
	
		PreparedStatement stat=null;
		int ctr=0,bookprice=0;
		for(i=1;i<=counter;i++)
		{
			if((String)session.getValue("chk_var"+i)!=null)
			{
				bookid[i-1]=Integer.parseInt((String)session.getValue("chk_var"+i));
			}
		}
		session.putValue("BookArr",bookid);	
	%>
	
		<h3>Your Cart Details are as follows:</h3>
		<form name="shop" action="Shop.jsp" method="POST">
		<table border=1>
		<tr>
		<td>S.No</td>
		<td>Book Name</td>
		<td>Price per unit (in Rs.)</td>
		<td>Quantity Available</td>
		<td>Quantity Purchased</td>
		<td>Amount</td>
		</tr>	
		<%
		int price=0;
		int qty_avbl=0;
		for (int j=1;j<=counter;j++)
		{
			out.println("<tr><td>"+j);
			out.println("</td>");
	
			String query="select title, price,quantity from book_details where bookid=?";
			stat= conn.prepareStatement(query);
			stat.setInt(1,bookid[j-1]);
			rs = stat.executeQuery();
        
			while (rs.next())
			{
				out.println("<td>");
				out.println(rs.getString(1));out.println("</td>");
				price=rs.getInt(2);%>
				<td><input type="hidden" name="bk_price<%=j%>" value=<%=price%></td>				
				<%out.println(price);
				qty_avbl=rs.getInt(3);%>
				<td><input type=hidden name="qty_avl<%=j%>" value=<%=qty_avbl%>><%=qty_avbl%></td>
				<td><input type=text name="qty<%=j%>" value="0" onChange="return check_sum();"></td>
				<td><input type=text name="cal_amt<%=j%>"></td>
			<%
			}%>
			</tr>
			
			<%
			rs.close();
			bookprice=bookprice+price;
			session.putValue("bPrice",String.valueOf(bookprice));
			
			out.println("</tr>");
		}%>
		<tr><td><td><td><td><td>Total Amount(in Rs.)<td><input type="text" name="tot_val" value="0"></td></tr>
		</table>
		<br>
		<input type=submit value="Place Order">
		</form>
		<br><hr>
<%}
else
	{
		response.sendRedirect("Unauthorised.htm");
	}
%>
	
</body>
</html>
