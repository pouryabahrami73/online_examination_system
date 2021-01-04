const startingMinutes = examTime;
console.log(startingMinutes);
let time = startingMinutes * 60;
const countdown = document.getElementById("countdown");
do {
    setInterval(updateCountDown, 1000)
} while (time != 0);
function updateCountDown() {
    const minutes = Math.floor(time / 60);
    let seconds = time % 60;
    countdown.innerHTML = `${seconds} : ${minutes}`;
    time--;
}
if(time == 0){
    //finish exam
    //redirect to finish page
}