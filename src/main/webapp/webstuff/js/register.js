
if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready)
} else {
    
    ready()
}

function ready() {

    register();

}

function register() {

    var registerButton = document.getElementsByClassName('auth-form-btn')[0];
    registerButton.addEventListener('click', onRegisterClick);

    var email = document.getElementById('inputEmail');
    var password = document.getElementById('inputPassword1');
    var confirmPassword = document.getElementById('inputPassword2');



    function onRegisterClick(event) {

        event.preventDefault();

        var emailReg = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        var passReg = /((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%?=*&/]).{5,8})/;

        if (email.value == '' || password.value == '' || confirmPassword.value == '') {
            alert('Please fill in all fields');
            return;
        }

        if(!email.value.match(emailReg)) {
            alert('Please enter a valid email');
            return;
        }

        if (!password.value.match(passReg)) {
            alert('Password must be at least 5 characters long, contain at least one uppercase letter,'+
            'one lowercase letter, one number and one special character');
            password.value = '';
            confirmPassword.value = '';
            return false;
        }

        if (password.value != confirmPassword.value) {
            alert('Passwords do not match');
            password.value = '';
            confirmPassword.value = '';
            return false;
        }


        var user = JSON.stringify({
            email: email.value,
            password: password.value
        });

        ajaxReq('http://localhost:8080/Ecommerce/api/register', 'POST', user, makeRequest);

        function makeRequest(response, status) {

            var response = JSON.parse(response);

            if (status == 200) {
                alert('Registration successful. Please check your email to confirm your account');
            }

            if (response.success == false) {
                alert(response.message);
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