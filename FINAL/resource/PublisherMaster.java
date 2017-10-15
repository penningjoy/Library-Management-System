import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class PublisherMaster extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection conn = null;
		ResultSet rs;
		Statement stmt = null;

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String query = null;

		try
		{
			String username = "root";
			String password = "WEBEL";
			String url = "jdbc:mysql://localhost/test";

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e)
		{
			out.println("Sorry failed to connect to the Database. " + e.getMessage());
		}

		if("D".equals(request.getParameter("hidMode")) && conn != null)
		{
			try
			{
				stmt = conn.createStatement();
				query = "DELETE FROM PublisherMaster WHERE PublisherID IN(" + request.getParameter("hidSelDel") + ")";
				stmt.executeUpdate(query);
				response.sendRedirect("/FINAL/MyServlets/PublisherMaster");
			}
			catch(Exception e)
			{
				out.println("Sorry failed to delete values from the database table. " + e.getMessage());
			}
		}
		if("U".equals(request.getParameter("hidMode")) && conn != null)
		{
			try
			{
				stmt = conn.createStatement();
				query = "UPDATE PublisherMaster SET PublisherName = '" + request.getParameter("txtPublisherName") + "', Address = '" + request.getParameter("txtAddress") + "', Edition = '" + request.getParameter("txtEdition") + "', PhoneNumber = '" + request.getParameter("txtPhoneNumber") + "' WHERE PublisherID = '" + request.getParameter("hidPublisherID")  + "'";
				stmt.executeUpdate(query);
				response.sendRedirect("/FINAL/MyServlets/PublisherMaster");
			}
			catch(Exception e)
			{
				out.println("Sorry failed to update values from the database table. " + e.getMessage());
			}
                }
        if("S".equals(request.getParameter("hidMode")) && conn != null)
		{
			try
			{
				stmt = conn.createStatement();
				query = "SELECT PublisherID FROM PublisherMaster  (PublisherID = '" + request.getParameter("txtPublisherID") + "')";
				stmt.executeUpdate(query);
				response.sendRedirect("/FINAL/MyServlets/PublisherMaster");
			}
			catch(Exception e)
			{
				out.println("Sorry failed to update values from the database table. " + e.getMessage());
			}

		}
		if("I".equals(request.getParameter("hidMode")) && conn != null)
		{
			String PublisherName = request.getParameter("txtPublisherName");
			String Address = request.getParameter("txtAddress");
			String Edition = request.getParameter("txtEdition");

			String PhoneNumber = request.getParameter("txtPhoneNumber");

			try
			{
				if(PublisherName.length() > 0 && Address.length() > 0 && Edition.length() > 0 && PhoneNumber.length() > 0)
				{
					stmt = conn.createStatement();
					query = "INSERT INTO PublisherMaster (PublisherName, Address, Edition, PhoneNumber) VALUES ('" + PublisherName + "','" + Address + "','" + Edition + "','" + PhoneNumber + "')";
					stmt.executeUpdate(query);
					response.sendRedirect("/FINAL/MyServlets/PublisherMaster");
				}
				else
				{
					out.println("Publisher details cannot be left blank.");
				}
			}
			catch(Exception e)
			{
				out.println("Sorry failed to insert values into the Database table. " + e.getMessage());
			}
		}
		out.println("<HTML>");
			out.println("<HEAD>");
				out.println("<SCRIPT LANGUAGE='JavaScript'>");
					out.println("function setMode() {");
					out.println("document.frmPublisher.txtPublisherName.value='';");
					out.println("document.frmPublisher.txtAddress.value='';");
					out.println("document.frmPublisher.txtEdition.value='';");

					out.println("document.frmPublisher.txtPhoneNumber.value='';");

					out.println("}");

					out.println("function setDelMode()");
					out.println("{");
					out.println("document.frmPublisher.hidMode.value='D';");
					out.println("formDeleteValues('hidSelDel');");
					out.println("}");

					out.println("function formDeleteValues(hidden)");
					out.println("{");
					out.println("var selValues = '';");
					out.println("for (i=0;i<document.forms[0].elements.length;i++)");
					out.println("{");
					out.println("if(document.forms[0].elements[i].type == \"checkbox\")");
					out.println("{");
					out.println("if (document.forms[0].elements[i].checked == true) {");
					out.println("selValues = selValues + document.forms[0].elements[i].value + \",\";");
					out.println("}");
					out.println("}");
					out.println("}");
					out.println("if (selValues.length < 1)");
					out.println("{");
					out.println("alert(\"Please choose records you wish to delete.\");");
					out.println("}");
					out.println("else");
					out.println("{");
					out.println("selValues = selValues.substring(0,selValues.length-1);");
					out.println("eval(\"document.forms[0].\"+hidden+\".value = '\" +selValues+\"';\");");
					out.println("document.forms[0].submit();");
					out.println("}");
					out.println("}");

					out.println("function setEditMode(PublisherID, PublisherName, Address, Qualification, Email, Age, Sex, PhoneNumber)");
					out.println("{");
					out.println("document.frmPublisher.hidPublisherID.value = PublisherID;");
					out.println("document.frmPublisher.txtPublisherName.value = PublisherName;");
					out.println("document.frmPublisher.txtAddress.value = Address;");
					out.println("document.frmPublisher.txtEdition.value = Edition;");

					out.println("document.frmPublisher.txtPhoneNumber.value = PhoneNumber;");

					out.println("document.frmPublisher.hidMode.value='U';");
					out.println("}");

				out.println("</SCRIPT>");
				out.println("<TITLE>Publisher Master Form</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY BGCOLOR='pink'>");
				out.println("<FORM ACTION='/FINAL/MyServlets/PublisherMaster' METHOD='post' NAME='frmPublisher'>");
					out.println("<INPUT NAME='hidMode' TYPE='hidden' VALUE='I'>");
					out.println("<INPUT NAME='hidSelDel' TYPE='hidden'>");
					out.println("<INPUT NAME='hidPublisherID' TYPE='hidden'>");
					out.println("<TABLE ALIGN='center' BGCOLOR='pink' CELLPADDING='0' CELLSPACING='0' NAME='tblouter' WIDTH='50%'>");
						out.println("<TR HEIGHT='200' VALIGN='top'>");
							out.println("<TD ALIGN='center' COLSPAN='10'>");
								out.println("<TABLE ALIGN='center' BGCOLOR='pink' BORDER='1' BORDERCOLOR='maroon' CELLPADDING='2' CELLSPACING='0' NAME='tblFirstChild' WIDTH='100%'>");
									out.println("<TR>");
										out.println("<TD ALIGN='left' COLSPAN='2' BGCOLOR='maroon'>");
											out.println("<FONT COLOR='pink'>Publisher Master</FONT>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Publisher Name</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='35' NAME='txtPublisherName' TYPE='text' SIZE='25'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Address</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='35' NAME='txtAddress' TYPE='text' SIZE='25'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Edition</TD>");
										out.println("<TD ALIGN='left'>");
								       		out.println("<INPUT MAXLENGTH='35' NAME='txtEdition' TYPE='text' SIZE='25'>");
										out.println("</TD>");
									out.println("</TR>");

									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Phone Number</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='255' NAME='txtPhoneNumber' TYPE='text' SIZE='20'>");
										out.println("</TD>");
									out.println("</TR>");

									out.println("<TR>");
										out.println("<TD COLSPAN='2' ALIGN='right'>");
											out.println("<INPUT NAME='cmdSubmit' TYPE='submit' VALUE='Save'>");
											out.println("<INPUT NAME='cmdCancel' onclick='setMode();' TYPE='button' VALUE='Cancel'>");
										out.println("</TD>");
									out.println("</TR>");
								out.println("</TABLE>");
							out.println("</TD>");
						out.println("</TR>");
					out.println("</TABLE>");

			if(conn != null)
			{
				try
				{
					stmt = conn.createStatement();
					query = "SELECT * FROM PublisherMaster";
					rs = stmt.executeQuery(query);
					out.println("<TABLE ALIGN='center' BORDER='1' WIDTH='50%' BORDERCOLOR='skyblue' CELLPADDING='0' CELLSPACING='0' NAME='tblSecondChild'>");
						out.println("<TR BGCOLOR='black'>");
							out.println("<TD WIDTH='12%' ALIGN='center'><INPUT NAME='cmdDelete' TYPE='button' VALUE='Delete' onClick='setDelMode();'></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Publisher Name</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Address</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Edition</FONT></TD>");

							out.println("<TD><FONT COLOR='#FFFFFF'>Phone Number</FONT></TD>");

						out.println("</TR>");
					if(rs!=null)
					{
						while(rs.next())
						{
							out.println("<TR>");
								out.println("<TD><INPUT TYPE='checkbox' NAME='chk" + rs.getString("PublisherID") + "' VALUE='" + rs.getString("PublisherID") + "'></TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("PublisherID") + "', '" + rs.getString("PublisherName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Edition") + "', '" + rs.getString("PhoneNumber") + "');\">" + rs.getString("PublisherName") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("PublisherID") + "', '" + rs.getString("PublisherName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Edition") + "', '" + rs.getString("PhoneNumber") + "');\">" + rs.getString("Address") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("PublisherID") + "', '" + rs.getString("PublisherName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Edition") + "', '" + rs.getString("PhoneNumber") + "');\">" + rs.getString("Edition") + "</TD>");


								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("PublisherID") + "', '" + rs.getString("PublisherName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Edition") + "', '" + rs.getString("PhoneNumber") + "');\">" + rs.getString("PhoneNumber") + "</TD>");
							    out.println("</TR>");
						}
					}
						out.println("</TABLE>");
						conn.close();
				}
				catch(Exception e)
				{
					out.println("Sorry Failed to execute the query. " + e.getMessage());
				}
			}
			out.println("</FORM>");
		out.println("</BODY>");
	out.println("</HTML>");
	out.close();
	}
}
