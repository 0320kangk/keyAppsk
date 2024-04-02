
$(document).ready(function (){
    const url = window.location.pathname
    $('#sign-in').prop('href', '/member/login?redirectURL=' + url )
});