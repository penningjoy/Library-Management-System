<html>
<head>
<title>Logged out</title>
</head>
<body bgcolor="#CCFFFF">
	<CENTER>
	<H1> <u>Online Shopping Cart</u> </H1>
	</CENTER>
	
	<%@  page errorPage="errorpage.jsp" language="java" %>
	<%
	session.invalidate();
	%>
        <center>
	<BR><BR><BR><BR><b>You have logged out successfully.
        <BR><BR>Return to Shopping Cart? <a href="Login.htm">Click here</a></b>
        </center>
</body>
</html>
