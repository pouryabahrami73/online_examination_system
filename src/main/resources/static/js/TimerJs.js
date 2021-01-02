const startingMinutes = examTime;
console.log(startingMinutes);
let time = startingMinutes * 60;
const countdown = document.getElementById("countdown");
setInterval(updateCountDown, 1000)
function updateCountDown(){
    const minutes = Math.floor(time / 60);
    let seconds = time % 60;
    countdown.innerHTML = `${seconds} : ${minutes}`;
    time--;
}
