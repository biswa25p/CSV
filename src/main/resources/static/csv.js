var count = 0;
var users = [];
function Upload() {
	var fileUpload = document.getElementById("fileUpload");
	var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
	if (regex.test(fileUpload.value.toLowerCase())) {
		if (typeof (FileReader) != "undefined") {
			var reader = new FileReader();
			reader.onload = function(e) {
				var table = document.createElement("tbody");
				var rows = e.target.result.split("\n");
				for (var i = 1; i < rows.length; i++) {
					var cells = rows[i].split(",");
					if (cells.length > 1) {
						var row = table.insertRow(-1);
						for (var j = 0; j < cells.length; j++) {
							var cell = row.insertCell(-1);
							cell.innerHTML = cells[j];
						}
					}
				}
				var dvCSV = document.getElementById("tableMain");
				dvCSV.innerHTML = "";
				var tableHead = document.createElement("tHead");
				tableHead.innerHTML = "<tr><th>First Name</th><th>Last Name</th><th>Date Of Birth(mm/dd/yyyy)</th><th>Address Line 1</th><th>Address Line 2</th><th>City</th><th>State</th><th>Country</th><th>Postal Code</th></tr>";
				dvCSV.appendChild(tableHead);
				dvCSV.appendChild(table);
				validate();
			}
			reader.readAsText(fileUpload.files[0]);

		} else {
			alert("This browser does not support HTML5.");
		}
	} else {
		alert("Please upload a valid CSV file.");
	}

}
function validate() {
	count = 0;
	users = [];
	var table = document.getElementById("tableMain");
	for (var r = 1; r < table.rows.length; r++) {
		var row = table.rows[r];
		var firstName, lastName, dob, line1, line2, city, state, country, postalCode;

		for (var j = 0, col; col = row.cells[j]; j++) {
			//iterate through columns
			//columns would be accessed using the "col" variable assigned in the for loop
			if (j == 0) {
				if (col.innerHTML.length > 20) {
					col.style.color = "red";
					count++;
				} else {
					firstName = col.innerHTML;
				}
			}
			if (j == 1) {
				if (col.innerHTML.length > 20) {
					col.style.color = "red";
					count++;
				} else {
					lastName = col.innerHTML;
				}
			}

			if (j == 2) {
				var regex = /((0[1-9]|1[0-2])\/((0|1)[0-9]|2[0-9]|3[0-1])\/((19|20)\d\d))$/;
				if (regex.test(col.innerHTML)) {
					var parts = col.innerHTML.split("/");
					var dtDOB = new Date(parts[0] + "/" + parts[1] + "/" + parts[2]);
					var dtCurrent = new Date();
					if (dtCurrent.getFullYear() - dtDOB.getFullYear() < 24 || dtCurrent.getFullYear() - dtDOB.getFullYear() > 70) {
						col.style.color = "red";
						count++;
					} else {
						dob = col.innerHTML;
					}
				} else {
					col.style.color = "red";
					count++;
				}
			}
			if (j == 3) {
				if (null == col.innerHTML) {
					line1 = "";
				} else {
					line1 = col.innerHTML;
				}
			}
			if (j == 4) {
				if (null == col.innerHTML) {
					line2 = "";
				} else {
					line2 = col.innerHTML;
				}
			}
			if (j == 5) {
				if (null == col.innerHTML) {
					city = "";
				} else {
					city = col.innerHTML;
				}
			}
			if (j == 6) {
				if (null == col.innerHTML) {
					state = "";
				} else {
					state = col.innerHTML;
				}
			}
			if (j == 7) {
				if (null == col.innerHTML) {
					country = "";
				} else {
					country = col.innerHTML;
				}
			}
			if (j == 8) {
				var reg = /^[1-9][0-9]{5}$/;
				if (!reg.test(parseInt(col.innerHTML))) {
					col.style.color = "red";
					count++;
				} else {
					if (null == col.innerHTML) {
						postalCode = null;
					} else {
						postalCode = parseInt(col.innerHTML);
					}
				}
			}

		}
		var user = {
			"firstName": firstName,
			"lastName": lastName,
			"dob": dob,
			"line1": line1,
			"line2": line2,
			"city": city,
			"state": state,
			"country": country,
			"postalCode": postalCode
		};
		users.push(user);
	}
	if (count > 0) {
				document.getElementById('submit').disabled = true;
				alert("Some Data Are Invalid");
			} else {
				document.getElementById('submit').disabled = false;
				
			}
}

function createTable(data) {
	var table = document.getElementById("resiltData");
	table.innerHTML ="";
	var row = table.insertRow(0);
	
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);
	var cell6 = row.insertCell(5);
	var cell7 = row.insertCell(6);
	var cell8 = row.insertCell(7);
	var cell9 = row.insertCell(8);
	var cell10 = row.insertCell(9);
	
	cell1.innerHTML = "First Name";
	cell2.innerHTML = "Last Name";
	cell3.innerHTML = "Date Of Birth(mm/dd/yyyy)";
	cell4.innerHTML = "Age";
	cell5.innerHTML = "Address Line 1";
	cell6.innerHTML = "Address Line 2";
	cell7.innerHTML = "City";
	cell8.innerHTML = "State";
	cell9.innerHTML = "Country";
	cell10.innerHTML = "Postal Code";
	for (var i = 0; i < data.length; i++) {
	
		var row = table.insertRow(i+1);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
		var cell6 = row.insertCell(5);
		var cell7 = row.insertCell(6);
		var cell8 = row.insertCell(7);
		var cell9 = row.insertCell(8);
		var cell10 = row.insertCell(9);
		
		cell1.innerHTML = data[i].firstName;
		cell2.innerHTML = data[i].lastName;
		cell3.innerHTML = data[i].dob;
		cell4.innerHTML = data[i].age;
		cell5.innerHTML = data[i].line1;
		cell6.innerHTML = data[i].line2;
		cell7.innerHTML = data[i].city;
		cell8.innerHTML = data[i].state;
		cell9.innerHTML = data[i].country;
		cell10.innerHTML = data[i].postalCode;
	}
}
$(document).ready(function() {
	$('#add').css("display" , "block");
	$('#view').css("display" , "none");
	
	$("#submit").click(function() {

		$.ajax({
			type: "post",
			url: "http://localhost:1234/user/add",
			data: JSON.stringify(users),
			dataType: "json",
			contentType: "application/json",
			"success": function(da) {
				alert("Users added successfully");
			},
			"error": function(xhr) {
				alert('Request Status: ' + xhr.status
					+ ' Status Text: ' + xhr.statusText
					+ ' ' + xhr.responseText);
				// alert("error");
			}
		});

	});

	$("#addUser").click(function() {
	
		$('#add').css("display" , "block");
		$('#view').css("display" , "none");
		$('#addUser').removeClass("btn btn-default");
		$('#viewUser').removeClass("btn btn-primary");
		$('#viewUser').removeClass("btn btn-default");
		$('#addUser').removeClass("btn btn-primary");
		$('#viewUser').addClass("btn btn-default");
		$('#addUser').addClass("btn btn-primary");
	});
	
	$("#viewUser").click(function() {
			$.ajax({
			type: "get",
			url: "http://localhost:1234/user/view",
			//data: JSON.stringify(users),
			dataType: "json",
			contentType: "application/json",
			"success": function(da) {
				createTable(da.results);
			},
			"error": function(xhr) {
				alert('Request Status: ' + xhr.status
					+ ' Status Text: ' + xhr.statusText
					+ ' ' + xhr.responseText);
				// alert("error");
			}
		});
		$('#add').css("display" , "none");
		$('#view').css("display" , "block");
		$('#addUser').removeClass("btn btn-default");
		$('#viewUser').removeClass("btn btn-primary");
		$('#viewUser').removeClass("btn btn-default");
		$('#addUser').removeClass("btn btn-primary");
		$('#addUser').addClass("btn btn-default");
		$('#viewUser').addClass("btn btn-primary");
		
	});
	$("#fileUpload").change(function() {
		$("#tableMain").html("");
	});
	
	
});