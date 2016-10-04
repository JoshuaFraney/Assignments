$(document).ready(function() {

// Set counter default to zero
var counter = 0

// Display total
$("#counter").text(counter)

console.log("counter is", counter);

function largeNum() {
	if(counter > 8){
		$("#counter").addClass("red");
	}else{
		$("#counter").removeClass("red");
	}
}

function evenNum(){
	if(counter % 2 == 0 || counter == 0){
		$("#counter").addClass("italic");
	}else{
		$("#counter").removeClass("italic");
	}
}

function luckySeven(){
	var cntr = counter.toString();
	var n = cntr.search("7");
		if (n !== -1){
			$("#counter").removeClass("red");
			$("#counter").addClass("green");
		}else{
			$("#counter").removeClass("green");
}
}

// When button is clicked
$("#add").click(function(){
  //Add 1 to counter
  counter = counter + 1;
  	
  	largeNum();
  	evenNum();
  	luckySeven();
  	
	$("#counter").text(counter);

});

//Subtract 1 from counter
$("#subtract").click(function(){
  counter = counter - 1;
  
  	largeNum();
	evenNum();
	luckySeven();

  $("#counter").text(counter);
});

// Reset
$("#reset").click(function(){
  counter = 0;
  $("#counter").text(counter);
});



});