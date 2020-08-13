

function validate() {
    // result boolean variable
    var formIsOkay = true;
    // find the first Name value, then repeat for other fields
    var firstName = document.getElementById("firstName").value;
    var password = document.getElementById("password").value;

    // store error message in variables
    var firstNameError = document.getElementById("firstName-error-message");
    var passwordError = document.getElementById("password-error-message");
    // reset previous error messages
    var errorMessages = document.getElementsByTagName("span");
    for (let i = 0; i < errorMessages.length; i++){
        errorMessages[i].innerHTML = "";
    }

    // check if password is null
    if (!password) {
        passwordError.innerHTML = "Please enter a password";
        formIsOkay = false;
        document.getElementById("password").focus();
    } else if (!password.replace(/\s/g, '').length) { // check if password is spaces only
        passwordError.innerHTML = "Only spaces found";
        formIsOkay = false;
        document.getElementById("password").focus();
    } else if (password.length < 8){ // check for password length
        passwordError.innerHTML = "Password should be at least 8 characters long";
        formIsOkay = false;
        document.getElementById("password").focus();
    } else if (!password.match(/[A-Z]/g)) {
        passwordError.innerHTML = "Password requires at least 1 uppercase letter";
        formIsOkay = false;
        document.getElementById("password").focus();
    } else if (!password.match(/[0-9]/g)) {
        passwordError.innerHTML = "Password requires at least 1 number";
        formIsOkay = false;
        document.getElementById("password").focus();
    }

    return formIsOkay;

}

function validate() {
    // result boolean variable
    var formIsOkay = true;
    // find the first Name value, then repeat for other fields
    var password = document.getElementById("password").value;

    // store error message in variables
    var passwordError = document.getElementById("password-error-message");
    // reset previous error messages
    var errorMessages = document.getElementsByTagName("span");
    for (let i = 0; i < errorMessages.length; i++){
        errorMessages[i].innerHTML = "";
    }

    // check if password is null
    if (!password) {
        passwordError.innerHTML = "Please enter a password";
        formIsOkay = false;
        document.getElementById("password").focus();
    } else if (!password.replace(/\s/g, '').length) { // check if password is spaces only
        passwordError.innerHTML = "Only spaces found";
        formIsOkay = false;
        document.getElementById("password").focus();
    } else if (password.length < 8){ // check for password length
        passwordError.innerHTML = "Password should be at least 8 characters long";
        formIsOkay = false;
        document.getElementById("password").focus();
    } else if (!password.match(/[A-Z]/g)) {
        passwordError.innerHTML = "Password requires at least 1 uppercase letter";
        formIsOkay = false;
        document.getElementById("password").focus();
    } else if (!password.match(/[0-9]/g)) {
        passwordError.innerHTML = "Password requires at least 1 number";
        formIsOkay = false;
        document.getElementById("password").focus();
    }

    return formIsOkay;

}


    function validate() {
        // result boolean variable
        var formIsOkay = true;
        // find the first Name value, then repeat for other fields
        var password = document.getElementById("creditCardNo").value;

        // store error message in variables
        var creditCardError = document.getElementById("creditCard-error-message");
        // reset previous error messages
        var errorMessages = document.getElementsByTagName("span");
        for (let i = 0; i < errorMessages.length; i++){
            errorMessages[i].innerHTML = "";
        }

        // check if creditCard is null
        if (!creditCard) {
            creditCardError.innerHTML = "Please enter your card number";
            formIsOkay = false;
            document.getElementById("creditCardNo").focus();
        } else if (!creditCard.replace(/\s/g, '').length) { // check if creditCard is spaces only
            creditCardError.innerHTML = "Only spaces found";
            formIsOkay = false;
            document.getElementById("creditCardNo").focus();
        } else if (creditCard.length < 16){ // check for credit length
            creditCardError.innerHTML = "Credit card should be at least 16 digits long";
            formIsOkay = false;
            document.getElementById("creditCardNo").focus();
        } else if (!creditCard.match(/[0-9]/g)) {
            creditCardError.innerHTML = "Credit Card requires at least 16 numbers";
            formIsOkay = false;
            document.getElementById("creditCardNo").focus();
        }

        return formIsOkay;

}