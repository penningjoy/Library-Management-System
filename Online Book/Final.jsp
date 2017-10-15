<html>

<head>
<title>Order placed</title>
</head>

<body bgcolor="#CCFFFF">

	<CENTER>
	<H1> <u>Online Shopping Cart</u> </H1>
	<H2>Order Details</H2></CENTER>
	<BR>
	<a href="Logout.jsp" ><font size="4"  >Click here to Logout</font></a>

	<%@  page errorPage="errorpage.jsp" language="java"  import="java.sql.*,java.util.*,java.text.*"  %>
	
<%! 
int OrderID,price;
%>
<%
	
	String user_src=(String)session.getValue("user");
   	if (user_src!= null)
   	{
		try{
	   	Connection conn;
		conn=null;
		ResultSet rs=null;
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		conn =DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 

				
		PreparedStatement stat1=null,stat_sel=null,stat_ins=null;
		
		OrderID=(int)(10000*Math.random()+1);
		price=Integer.parseInt((String)session.getValue("bPrice"));
		int counter=0;
		counter=Integer.parseInt((String)session.getValue("TotalSel"));

		int[] bookid=new int[counter];
		int[] bk_quantity=new int[counter];
		int org_bkqty=0;
		int i=0;
		
		String ins_query="";
		String sel_qtyQuery="";
		String ins_qty="";		
		
		for(i=1;i<=counter;i++)
		{
			ResultSet rs_sql=null;
			if((String)session.getValue("chk_var"+i)!=null)
			{
				bookid[i-1]=Integer.parseInt((String)session.getValue("chk_var"+i));
			}
			
			if((String)session.getValue("bookqty"+i)!=null)
			{
				bk_quantity[i-1]=Integer.parseInt((String)session.getValue("bookqty"+i));
			}	
			
			ins_query="INSERT INTO ORDER_DETAILS VALUES(?,?,?)";
			stat1=conn.prepareStatement(ins_query);
			stat1.setInt(1,OrderID);
			stat1.setInt(2,bookid[i-1]);
			stat1.setInt(3,bk_quantity[i-1]);
			int rs_int=0;
			
			rs_int=stat1.executeUpdate();	
			sel_qtyQuery="SELECT QUANTITY FROM BOOK_DETAILS WHERE BOOKID = ?";
					

			
			stat_sel = conn.prepareStatement(sel_qtyQuery);			
			stat_sel.setInt(1,bookid[i-1]);
			rs_sql=stat_sel.executeQuery();
			if(rs_sql.next())
			{
				org_bkqty=rs_sql.getInt(1);
			}
			rs_sql.close();
			String ns_qty="UPDATE BOOK_DETAILS SET QUANTITY=? WHERE BOOKID=?";
			stat_ins=conn.prepareStatement(ns_qty);
			
			stat_ins.setInt(1,org_bkqty-bk_quantity[i-1]);
			stat_ins.setInt(2,bookid[i-1]);
			rs_int=stat_ins.executeUpdate();	
		

			}		
			}catch(Exception e){}



		%>
		<form name="newsrc"  method="POST">
		<BR><font size="3"><b>
		Your order has been successfully placed.
		<BR><BR>Order Number is : <%=OrderID%>
		<BR><BR>Total Amount is : <%=price%>
		<BR><BR>Date of Order is:
		<%
               
		out.println((new java.util.Date()).toLocaleString());
		%>
		<BR><BR>
	        Your Order will be delivered within next 48 working hours

		
<%
	
	String card_no=request.getParameter("Card");
	Connection con= null;
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	con =DriverManager.getConnection("jdbc:odbc:Data","scott","tiger");
	java.util.Date now = new java.util.Date();
	DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
        String s1 = df1.format(now);			
	try
         {
        String str = "insert into order_table values(?,?,?,?)";
	PreparedStatement stat= con.prepareStatement(str);

	stat.setInt(1,OrderID);
	stat.setString(2,user_src);
	stat.setInt(3,price);
	stat.setString(4,s1);
	
	int x = stat.executeUpdate();
        
        }catch(Exception e){out.println(e); }
        
	}	
	else
	{
		response.sendRedirect("Unauthorised.htm");
	} 	

	%>	




</body>

</html>
