// navbar.js
document.addEventListener("DOMContentLoaded", function () {
    // Fetch and inject the navigation bar into the current page
    fetch("index.html")
        .then(response => response.text())
        .then(html => {
            document.body.insertAdjacentHTML("afterbegin", html);
        })
        .catch(error => console.error("Error fetching navbar:", error));
});
