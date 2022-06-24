
function upperCase() {
    document.getElementById("userOutputField").value = document.getElementById("userInputField").value.toUpperCase();
}

function lowerCase() {
    document.getElementById("userOutputField").value = document.getElementById("userInputField").value.toLowerCase();
}

function camelCaseLower() {
    document.getElementById("userOutputField").value = snakeCaseToCamelCaseLower(document.getElementById("userInputField").value);
}

function camelCaseUpper() {
    document.getElementById("userOutputField").value = snakeCaseToCamelCaseUpper(document.getElementById("userInputField").value);
}

function snakeCase() {
    let text = document.getElementById("userInputField").value;
    document.getElementById("userOutputField").value = camelToSnakeCase(text.substring(0, 1).toLowerCase() + text.substring(1));

    this.userOutputField = this.camelToSnakeCase(this.userInputField.substring(0, 1).toLowerCase() + this.userInputField.substring(1));
}


/* To copy Text from Textbox */
function copyInputMessage() {
    var inputElement = document.getElementById("userOutputField");
    inputElement.select();
    document.execCommand('copy');
    inputElement.setSelectionRange(0, 0);
}

/* snake case to camel case */
const snakeCaseToCamelCaseLower = input => input.split('_').reduce((res, word, i) => i === 0 ? word.toLowerCase() : `${res}${word.charAt(0).toUpperCase()}${word.substr(1).toLowerCase()}`, '');

/* snake case to camel case */
const snakeCaseToCamelCaseUpper = input => input.split('_').reduce((res, word, i) => i === 0 ? word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() : `${res}${word.charAt(0).toUpperCase()}${word.substr(1).toLowerCase()}`, '');


/* camel Case to snake case */
const camelToSnakeCase = str => str.replace(/[A-Z]/g, letter => `_${letter.toLowerCase()}`);

