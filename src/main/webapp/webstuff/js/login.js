
if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready)
} else {
    ready()
}

function ready() {

    login();

}

function login() {

    var loginButton = document.getElementsByClassName('auth-form-btn')[0];
    loginButton.addEventListener('click', onLoginClick);

    var email = document.getElementById('inputEmail');
    var password = document.getElementById('inputPassword');



    function onLoginClick(event) {

        event.preventDefault();

        var emailReg = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        var passReg = /((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%?=*&/]).{5,8})/;

        if (email.value == '' || password.value == '') {
            alert('Please fill in all fields');
            return;
        }

        if(!email.value.match(emailReg)) {
            alert('Please enter a valid email');
            return;
        }

        loginButton.disabled = true;

        var user = JSON.stringify({
            email: email.value,
            password: password.value
        });

        ajaxReq('http://omoka.ml/api/login', 'POST', user, makeRequest);

        function makeRequest(response, status) {

            var response = JSON.parse(response);

            if (!status == 200) {
                alert('Error: ' + response.message);
                loginButton.disabled = false;
                return;
            }

            if (!response.success) {
                alert(response.message);
                loginButton.disabled = false;
                return;
            }
            else {
                window.location.href = 'index.html';
                return;
            }
        }
    }
}

function ajaxReq(url, method, data, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(data);
    xhr.onload = function () {
        callback(xhr.response,xhr.status);
    };
}