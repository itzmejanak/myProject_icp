document.addEventListener('DOMContentLoaded', function () {
  var container = document.getElementById("container");
  container.classList.add('active');
});

// Your existing toggle code
var toggle = document.getElementById("toggle");
var container = document.getElementById("container");

toggle.onclick = function () {
  container.classList.toggle('active');
};