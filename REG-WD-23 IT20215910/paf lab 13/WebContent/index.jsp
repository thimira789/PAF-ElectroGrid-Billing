<%@ page import="com.Bill"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta charset="ISO-8859-1">
	<title>Billing Management </title>
	
	<!-- Mobile Specific Metas -->
	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<!-- Font-->
	
	<link rel="stylesheet" type="text/css" href="css/lora-font.css">
	<link rel="stylesheet" type="text/css" href="css/raleway-font.css">
	<link rel="stylesheet" type="text/css" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">
	
	<!-- datepicker -->
	
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
	
	<!-- Main Style Css -->
	
    <link rel="stylesheet" href="css/style.css"/>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery.min.js"></script>
	<script src="Components/Bills.js"></script>
	
</head>
<body class="form-v3">
	<div class="page-content">
		<div class="form-v3-content">
			<form class="form-detail" id="formBill" name="formBill" method="post" action="index.jsp">
				<h2>Bill Sheet</h2>
				<br>
				<p class="text">Fill Electricity Bill Details</p>
				<div class="form-group">
					<div class="form-row form-row-1">
						<label for="full-name">CUSTOMER NAME:</label>
						<input type="text" id="billCName" name="billCName" class="input-text" required>
					</div>
					<div class="form-row form-row-1">
						<label for="your-email">ACCOUNT NO:</label>
						<input type="text" id="billAccNO" name="billAccNO" class="input-text" required>
					</div>
				</div>
				<br>
				<div class="form-group form-group-1">
					
					<div class="form-row form-row-2">
						<label for="person">UNITS:</label>
						<input type="number"  class="input-text" id="billUnits" name="billUnits" value="1" title="Qty" size="4" pattern="[0-9]*" inputmode="numeric">
					</div>
					<div class="form-row form-row-1">
						<label for="your-email">AMOUNT(LKR):</label>
						<input type="text" id="billAmount" name="billAmount" class="input-text" required>
					</div>
					
				</div>
				<div class="form-group form-group-1">
					<div class="form-row form-row-3">
							<label for="date">DATE:</label>
							<input type="text" id="billDate" name="billDate" class="date"  placeholder="8/9/2022">
					</div>
				</div>
				<div class="form-checkbox">
					<label class="container"><p>By billing, you agree to the <a href="#" class="text">Terms of Service</a></p>
					  	<input type="checkbox" name="checkbox">
					  	<span class="checkmark"></span>
					</label>
				</div>
				<div class="form-row-last">
					<input id="btnSave" name="btnSave" type="button" value="Save" class="register" >
					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				</div>
				<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div  id="divItemsGrid">
			 <%
				 Bill billingObj = new Bill(); 
				 out.print(billingObj.readbill()); 
			 %>
		</div>
			</form>
		</div>
	</div>
	
	
	<!-- Jquery -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 	<script src="js/jquery-ui.min.js"></script>
	<script type="text/javascript">
		$( "#billDate" ).datepicker({
	        dateFormat: "MM - DD - yy",
	        showOn: "both",
	        buttonText : '<i class="zmdi zmdi-calendar-alt"></i>',
	    });
	</script>
</body>
</html>