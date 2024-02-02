//selecting the HTML tags
var s1 = document.querySelector("i #upgrade");
var clkme = document.querySelectorAll("#buttons .CartButton");

//for reseting the cart value
s1.addEventListener("click", function(){
  s1.innerHTML = 0;
})

/*
end here of reseting 
for adding the cart value or items to buy
*/

clkme.forEach(function(button){
  button.addEventListener("click", function(){
    s1.textContent++;
  })
})
//adding the cart value or items to buy end here



// side navbar active maker goes here
function toggleMenu() {
  var menuBar = document.getElementById('menuBar');
  menuBar.classList.toggle('active');
}
// side navbar active maker end here

