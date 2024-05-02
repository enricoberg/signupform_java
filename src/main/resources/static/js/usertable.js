function updateView(){
    let etitems=document.querySelectorAll(".etitem");
    etitems.forEach(function(etitem){
        if(!etitem.classList.contains("editable")) return;
        etitem.addEventListener("click",()=>{
            event.stopPropagation();
            if(!etitem.classList.contains("etinactive")) return;
            inputToTableItem();
            etitem.classList.remove("etinactive");
            etitem.classList.add("etactive");
            let content=etitem.innerHTML;
            etitem.innerHTML=`<input type="text" class="w-100 text-start" value="${content}"> `;
            localStorage.setItem("content", content);

        });
    })
    document.addEventListener("click",(event)=>{
        if (!event.target.classList.contains("etitem"))
        inputToTableItem();
    
    });
}

function inputToTableItem(){
    let etitems=document.querySelectorAll(".etitem");
    etitems.forEach(function(etitem){
        if(etitem.classList.contains("etactive")){
            etitem.classList.remove("etactive");
            etitem.classList.add("etinactive");
        }
        const inputElement = etitem.querySelector('input[type="text"]');
        
        if (inputElement) {
            
            let content=inputElement.value;
            inputElement.remove();
            etitem.innerHTML=content;           
            //COLOR ONLY THE CHANGED ITEMS
            if(content!=localStorage.getItem("content") &&!etitem.classList.contains("edited") ) {
                etitem.classList.add("edited");
                etitem.classList.add("text-primary");
            }
            
            
        }
    });
}

//FUNCTION THAT LOADS ALL THE USERS FROM THE DATABASE AND DRAWS THE TABLE WITH THE RETRIEVED DATA (RUNS AT PAGE LOAD)
function visualizeUsers(){    
    const target_table=document.querySelector("#targettable");
    target_table.innerHTML=`<div class="row">                    
    <div class="col-1  text-center etheader border rounded-top bg-primary text-light ">
        <h5>ID</h5>
    </div> 
    <div class="col-1 text-center etheader border rounded-top bg-primary text-light ">
        <h5>1<sup>st</sup>Name</h5>
    </div>
    <div class="col-1 text-center etheader border rounded-top bg-primary text-light">
        <h5>2<sup>nd</sup>Name</h5>
    </div>
    <div class="col-2 text-center etheader border rounded-top bg-primary text-light ">
        <h5>Email</h5>
    </div>
    <div class="col-1 text-center etheader border rounded-top bg-primary text-light">
        <h5>Password</h5>
    </div>
    <div class="col-1 text-center etheader border rounded-top bg-primary text-light">
        <h5>Birthdate</h5>
    </div>
    <div class="col-1 text-center etheader border rounded-top bg-primary text-light">
        <h5>Street</h5>
    </div>
    <div class="col-1 text-center etheader border rounded-top bg-primary text-light">
        <h5>Str. n<sup>°</sup></h5>
    </div>
    <div class="col-1 text-center etheader border rounded-top bg-primary text-light">
        <h5>City</h5>
    </div>
    <div class="col-1 text-center etheader border rounded-top bg-primary text-light">
        <h5>Province</h5>
    </div>
    <div class="col-1 text-center etheader border rounded-top bg-primary text-light ">
        <h5>Action</h5>
    </div>     
</div>`;    
    
    axios.get('/accounts/getallusers')
      .then(function (response) {        

        const users = response.data.map(userObj => {
            return new User(
                userObj.id,
                userObj.firstname,
                userObj.secondname,
                userObj.email,
                userObj.password,
                userObj.addressstreet,
                userObj.addressnumber,
                userObj.addresscity,
                userObj.addressprovince,
                userObj.birthdate                
            );
        });
        
        users.forEach((element) => {
            target_table.innerHTML+=`
            <div class="row" style="position: relative;">                    
                <div class="col-1 py-2 text-center etitem  border  bg-light etinactive boxid ">${element.id}</div>
                <div class="col-1 py-2 text-center etitem border  bg-light etinactive editable boxfirst ">${element.firstname}</div>
                <div class="col-1 py-2 text-center etitem border  bg-light etinactive editable boxsecond ">${element.secondname}</div>
                <div class="col-2 py-2 text-center etitem border  bg-light etinactive editable boxemail ">${element.email}</div>
                <div class="col-1 py-2 text-center etitem border  bg-light etinactive editable boxpassword ">${element.password}</div>
                <div class="col-1 py-2 text-center etitem border  bg-light etinactive editable boxbirthdate " >${element.birthdate}</div>
                <div class="col-1 py-2 text-center etitem border  bg-light etinactive triggerrowcontrol editable boxstreet ">${element.addressstreet}</div>
                <div class="col-1 py-2 text-center etitem border  bg-light etinactive triggerrowcontrol editable boxnumber ">${element.addressnumber}</div>
                <div class="col-1 py-2 text-center etitem border  bg-light etinactive triggerrowcontrol editable boxcity ">${element.addresscity}</div>
                <div class="col-1 py-2 text-center etitem border  bg-light etinactive triggerrowcontrol editable boxprovince ">${element.addressprovince}</div>     
                <div class="col-1 py-2 text-center etitem border  bg-light etinactive ">
                    <i class="fa fa-trash-o bg-danger px-3 py-2 mx-2 actionbutton"  onclick="deleteItem(${element.id})" aria-hidden="true"></i> Delete
                    
                </div>                
            </div>`;
          });
          updateView();
      })

}

//FUNCTION TO DELETE ONE USER
function deleteItem(id){
    if(!window.confirm("ARE YOU SURE YOU WANT TO DELETE THIS USER PERMANENTLY?")) return;          
    axios.delete(`/accounts/deleteuser/${id}`)
    .then((response) => {
        visualizeUsers();
    })
    .catch((error) => {
        alert("Something went wrong trying to delete this user");
    });       
}

//FUNCTION TO SAVE THE DATA OF ALL MODIFIED USERS
function saveModifications(){
    inputToTableItem();
    const modified_items=document.querySelectorAll(".edited");
    if(!window.confirm("ARE YOU SURE YOU WANT TO UPDATE ALL "+modified_items.length+" MODIFIED FIELDS?")) return;  
    let processed_items=0;
    
    modified_items.forEach(function(button){
        processed_items++;
        const parentRow = button.closest('.row');
        if (parentRow) {
            const id = parentRow.querySelector('.boxid').innerHTML;
            const first = parentRow.querySelector('.boxfirst').innerHTML;
            const second = parentRow.querySelector('.boxsecond').innerHTML;
            const email = parentRow.querySelector('.boxemail').innerHTML;
            const password = parentRow.querySelector('.boxpassword').innerHTML;
            const street= parentRow.querySelector('.boxstreet').innerHTML;
            const number = parentRow.querySelector('.boxnumber').innerHTML;
            const city = parentRow.querySelector('.boxcity').innerHTML;
            const province = parentRow.querySelector('.boxprovince').innerHTML;
            const birthdate = parentRow.querySelector('.boxbirthdate').innerHTML;

            if(!id || !email || !password || !street || !number || !city || !province || !birthdate || !first || !second) return;
            const user_correct = { 
                                    email: email,                                
                                    password: password,  
                                    firstname: first,
                                    secondname: second,  
                                    addressstreet: street,
                                    addresscity: city,
                                    addressnumber: number,
                                    addressprovince: province,
                                    birthdate: birthdate    
                                };

            axios.put(`/accounts/updateuser/${id}`, user_correct) ;
            if (processed_items==modified_items.length)  setTimeout(()=>{visualizeUsers()},250);         
            
        }
    });
    
}
function logOut(){
    if(!confirm("Are you sure you want to log out of the application?")) return;
    document.cookie = `sessiontoken=; path=/`;  
    document.cookie = `user=; path=/`;  
    location.replace("/");
}
visualizeUsers();




