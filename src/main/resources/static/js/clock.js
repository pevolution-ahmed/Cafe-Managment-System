 function startTimer() {
    
		var hoursLabel = document.querySelector(".hours");
		var minutesLabel = document.querySelector(".minutes");
    var secondsLabel = document.querySelector(".seconds");
    var hInput = document.querySelector('.inp1')
    var mInput = document.querySelector('.inp2')
    var sInput = document.querySelector('.inp3')
    var totalSeconds = 0;
    setInterval(setTime, 1000);

		function setTime() {
      ++totalSeconds;
		  sInput.value = secondsLabel.innerHTML = pad(totalSeconds % 60);
		  mInput.value = minutesLabel.innerHTML = pad(parseInt(totalSeconds / 60));
      hInput.value = hoursLabel.innerHTML = pad(parseInt(totalSeconds / (60 * 60)));
      
		}
	
		function pad(val) {
		  var valString = val + "";
		  if (valString.length < 2) {
		    return "0" + valString;
		  } else {
		    return valString;
		  }
		}
};