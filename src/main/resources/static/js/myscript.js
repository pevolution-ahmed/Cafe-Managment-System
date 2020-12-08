
var hoursLabel = document.querySelector(".hours");
var minutesLabel = document.querySelector(".minutes");
var secondsLabel = document.querySelector(".seconds");
var hInput = document.querySelector('.inp1')
var mInput = document.querySelector('.inp2')
var sInput = document.querySelector('.inp3')
    
//Define vars to hold time values
let seconds = 0;
let minutes = 0;
let hours = 0;

//Define vars to hold "display" value
let displaySeconds = 0;
let displayMinutes = 0;
let displayHours = 0;

//Define var to hold setInterval() function
let interval = null;

//Define var to hold stopwatch status
let status = "stopped";

//Stopwatch function (logic to determine when to increment next value, etc.)
function stopWatch(){

    seconds++;

    //Logic to determine when to increment next value
    if(seconds / 60 === 1){
        seconds = 0;
        minutes++;

        if(minutes / 60 === 1){
            minutes = 0;
            hours++;
        }

    }

    //If seconds/minutes/hours are only one digit, add a leading 0 to the value
    if(seconds < 10){
        displaySeconds = "0" + seconds.toString();
    }
    else{
        displaySeconds = seconds;
    }

    if(minutes < 10){
        displayMinutes = "0" + minutes.toString();
    }
    else{
        displayMinutes = minutes;
    }

    if(hours < 10){
        displayHours = "0" + hours.toString();
    }
    else{
        displayHours = hours;
    }

    //Display updated time values to user
 
          sInput.value = secondsLabel.innerHTML = displaySeconds;
		  mInput.value = minutesLabel.innerHTML = displayMinutes;
          hInput.value = hoursLabel.innerHTML = displayHours;

}



function startStop(){

	
    if(status === "stopped"){

        //Start the stopwatch (by calling the setInterval() function)
        interval = window.setInterval(stopWatch, 1000);
        document.getElementById("startStop").innerHTML = "Pause";
        
		status = "started";
        

    }
    else{

        window.clearInterval(interval);
        document.getElementById("startStop").innerHTML = "Start";
        status = "stopped";

    }

}

//Function to reset the stopwatch
function reset(){

    window.clearInterval(interval);
    seconds = 0;
    minutes = 0;
    hours = 0;
    sInput.value = secondsLabel.innerHTML = "00";
		  mInput.value = minutesLabel.innerHTML = "00";
          hInput.value = hoursLabel.innerHTML = "00";
    document.getElementById("startStop").innerHTML = "Start";

}


