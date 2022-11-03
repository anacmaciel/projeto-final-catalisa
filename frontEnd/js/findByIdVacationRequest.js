let allVacations = {}
async function findById(){
    const id = document.getElementById("id").value

    try {

    const response = await fetch(`http://localhost:8080/vacationsrequest/${id}`, {
        method: 'GET',
        headers: {
        "Content-Type": "application/json",
        },
    }).then((response) => response.json())
    .then((data) => {console.log(data)
  
       
        let data1 = "";
      allVacations = data
      if (data.status == 404){
        alert("Pedido de férias não encontrado")
        return
      }
      
        console.log(allVacations)

      
        document.getElementById("id").value =allVacations.id
        document.getElementById("vacationDays").value = allVacations.vacationDays
        document.getElementById("startAt").value =allVacations.startAt
        document.getElementById("endAt").value =allVacations.endAt
        document.getElementById("statusVacationRequest").value =allVacations.statusVacationRequest
        document.getElementById("name").value = allVacations.user.name
        document.getElementById("email").value = allVacations.user.email
        document.getElementById("daysBalance").value = allVacations.user.daysBalance
        document.getElementById("profileEnum").value =allVacations.user.profileEnum
        document.getElementById("statusUser").value =allVacations.user.statusUser

     
      })
  
    } catch(error) {
      console.log(error);
    }
   
    

  }
  

  const form = document.getElementById("vacationsRequest-form");
function openSubmitButton() {
    // event.preventDefault();
    var myModal = new bootstrap.Modal(document.getElementById("myModal"));
    var modalToggle = document.getElementById("myModal");
  
    myModal.show();
  }

 async function updateVacation() {
    const formData = new FormData(form); // Pega o formulário e joga em uma variável 
    const data = Object.fromEntries(formData); //Se torna em objeto
    console.log( JSON.stringify(data))

    


    let objectUpdate = {
      vacationDays: data.vacationDays,
      startAt: data.startAt,
      statusVacationRequest: "CREATED",
    }
    console.log(objectUpdate)



    try {
      const response = await fetch(`http://localhost:8080/vacationsrequest/${allVacations.id}`, {
        method: 'PUT',
        headers: {
         "Content-Type": "application/json",
       },
       body: JSON.stringify(objectUpdate),
    });
    if (response.status == 200) {
      alert("Atualizado com sucesso!");
      var myModalEl = document.querySelector("#myModal"); //Id
      var modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
      modal.hide();

      document.getElementById("usuario-form").reset();
      document.getElementById("vacationsRequest-form").reset();
    } else{
      alert("Error: " + response.status + " - Preencha o formulário com dados validos");
      console.log(response);
    }  
  } catch(error) {
    console.log(error);
  }
}

async function canceledVacationsRequest(){

  try {
    const response = await fetch(`http://localhost:8080/vacationsrequest/cancel/${allVacations.id}`, {
      method: 'DELETE',
      headers: {
       "Content-Type": "application/json",
     },
  });
  if (response.status == 200) {
    alert("Inativado com sucesso!");
    var myModalEl = document.querySelector("#deleteModal"); //Id
    var modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    modal.hide();

    document.getElementById("usuario-form").reset();
    document.getElementById("vacationsRequest-form").reset();
  } else{
    alert("Error: " + response.status + " - Preencha o formulário com dados validos");
    console.log(response);
  }  
} catch(error) {
  console.log(error);
}
  
}

function openDeleteButton() {
  var myModal = new bootstrap.Modal(document.getElementById("deleteModal"));
  var modalToggle = document.getElementById("deleteModal");

  myModal.show();
}