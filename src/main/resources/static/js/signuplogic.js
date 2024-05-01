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
    const termsaccepted = document.querySelector("#accepttermscheck").checked;
    if (submitting) if(!form.classList.contains("was-validated")) form.classList.add("was-validated");
    if(email=="@" || firstname=="" || secondname=="" || birth=="" || addressstreet=="" || addressnumber=="" || addresscity=="" || addressprovince=="" || password=="" || repeat =="" ) {
        sendFormError("Error: please fill all mandatory fields","signupError");
        return;}
    else if (password!=repeat) {
        sendFormError("Error: passwords are not matching","signupError");
        return; }
    else if(age<18 ){
        sendFormError("You have to be at least 18 years old to register","signupError");
        return;
    }    
    if(!termsaccepted) {
         sendFormError("You must accept the terms first","signupError");
         return;}
    
    const user_data= {
        email: email,
        password: password,
        firstname: firstname,
        secondname: secondname,
        addressstreet: addressstreet,
        addresscity: addresscity,
        addressnumber: addressnumber,
        addressprovince: addressprovince,
        birthdate: birth
    }
    axios.post(`/auth/signup`, user_data)
    .then((response) => {        
        switch(response.data){
            case 0:
                alert("Impossible to signup: parameters are missing");
                break;
            case 1:
                alert("Succesfully signed up, you can now login");
                location.reload();
                break;
            case 2:
                alert("Error: user already exists");
                break;
            case 3:
                alert("The email you provided is not valid");
                break;
            case 4:
                alert("Error: the password does not satisfy the minimum safety requirements");
                break;
            default:
                alert("Unknown server error");
        };

    })
    .catch((error) => {
        alert("Something went wrong trying to signup");
    }); 
    
    
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
    
}



