//Hide the alerts on page load
$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	
	$("#alertError").hide();
});
// SAVE
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();
	 
	// Form validation
	var status = validateItemForm();
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	 }	
	// If valid
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
		{
			url : "BillsAPI",
			type : type,
			data : $("#formBill").serialize(),
			dataType : "text",
			complete : function(response, status)
			{
				onItemSaveComplete(response.responseText, status);
			}
		});
});

// UPDATE
$(document).on("click", ".btnUpdate", function(event)
{		
	
	 $("#hidItemIDSave").val($(this).data("billid"));
	 $("#billCName").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#billAccNO").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#billDate").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#billUnits").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#billAmount").val($(this).closest("tr").find('td:eq(4)').text());
});

//DELETE
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
		{
			url : "BillsAPI",
			type : "DELETE",
			data : "billID=" + $(this).data("billid"),
			dataType : "text",
			complete : function(response, status)
			{
				onItemDeleteComplete(response.responseText, status);
			}
		});
});

// CLIENT-MODEL
function validateItemForm()
{
	// CODE
	if ($("#billCName").val().trim() == "")
	 {
		return "Insert Customer name.";
	 }
	// NAME
	if ($("#billAccNO").val().trim() == "")
	 {
		return "Insert Account Number.";
	 }
	//PRICE
	if ($("#billDate").val().trim() == "")
	 {
		return "Insert Billind Date.";
	 }
	if ($("#billUnits").val().trim() == "")
	 {
		return "Insert Bill Units.";
	 }
	if ($("#billAmount").val().trim() == "")
	 {
		return "Insert Bill Amount.";
	 }
	// is numerical value
	var tmpAmount = $("#billAmount").val().trim();
		if (!$.isNumeric(tmpAmount))
	 {
			return "Insert a numerical value for Bill Amount.";
	 }
	// convert to decimal price
	 $("#billAmount").val(parseFloat(tmpAmount).toFixed(2));
	// DESCRIPTION
	
	return true;
}
function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	
	else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}
function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}