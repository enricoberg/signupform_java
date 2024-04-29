function switchMode(){
    const signupform=document.querySelector(".signupform");
    const loginform = document.querySelector(".loginform");
    const activeform= !signupform.classList.contains("hidden")? signupform : loginform;
    const otherform = !signupform.classList.contains("hidden")? loginform : signupform;    
    activeform.classList.add("hidden");
    otherform.classList.remove("hidden");
    event.preventDefault();
}
function validateLogin(submitting){
    const email = document.querySelector("#signinEmail").value;
    const password = document.querySelector("#signinPassword").value;
    if(email=="" || password=="") sendFormError("You must insert your credentials first","signinError");


    event.preventDefault();
}

function validateSignup(submitting){
    const form=document.querySelector(".signupform");
    
    const email = document.querySelector("#email1").value +"@" + document.querySelector("#email2").value;
    const firstname = document.querySelector("#firstname").value;
    const secondname = document.querySelector("#secondname").value;
    const birth= document.querySelector("#birth").value;
    const age = getAge(birth);
    const addressstreet = document.querySelector("#addressstreet").value;
    const addressnumber= document.querySelector("#addressnumber").value;
    const addresscity = document.querySelector("#addresscity").value;
    const addressprovince = document.querySelector("#addressprovince").value;
    const password = document.querySelector("#inputPassword").value;
    const repeat = document.querySelector("#repeatPassword").value;
    const termsaccepted = document.querySelector("#accepttermscheck").value;
    
    if(email=="@" || firstname=="" || secondname=="" || birth=="" || addressstreet=="" || addressnumber=="" || addresscity=="" || addressprovince=="" || password=="" || repeat =="" ) sendFormError("Error: please fill all mandatory fields","signupError");
    else if (password!=repeat) sendsFormError("Error: passwords are not matching","signupError");
    
    

    if (submitting) if(!form.classList.contains("was-validated")) form.classList.add("was-validated");
}


function getAge(dateString) {
    let today = new Date();
    let birthDate = new Date(dateString);
    let age = today.getFullYear() - birthDate.getFullYear();
    let m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    return age;
}
function sendFormError(message,cssId){
    const signuperror= document.querySelector("#"+cssId);
    if (signuperror.classList.contains("hidden")) signuperror.classList.remove("hidden");
        signuperror.innerHTML=message;
    event.preventDefault();
}