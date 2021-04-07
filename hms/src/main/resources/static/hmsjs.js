
var ourRequest=new XMLHttpRequest();
 ourRequest.open('GET','http://localhost:8080/hms/doctors');
 ourRequest.onload=function(){
	 var ourData=JSON.parse(ourRequest.responseText);
	 console.log(ourData[0]);
 };
 ourRequest.send();