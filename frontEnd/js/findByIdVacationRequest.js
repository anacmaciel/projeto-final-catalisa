
let actualVacations = {}
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
   // if (response.status == 200) {
        let data1 = "";
      let allVacations = data
      if (data == null){
        alert("Vacation request não encontrada")
        return
      }
      
        console.log(allVacations)

        actualVacations.id = actualVacations.id
        actualVacations.daysBalance = actualVacations.daysBalance
        actualVacations.email.actualVacations.email
        document.getElementById("id").value = actualVacations.id
        document.getElementById("vacationDays").value = actualVacations.vacationDays
        document.getElementById("startAt").value = actualVacations.startAt
        document.getElementById("endAt").value = actualVacations.endAt
        document.getElementById("statusVacationRequest").value = actualVacations.statusVacationRequest
        document.getElementById("name").value = actualVacations.name
        document.getElementById("email").value = actualVacations.email
        document.getElementById("daysBalance").value = actualVacations.daysBalance
        document.getElementById("profileEnum").value = actualVacations.profileEnum
        document.getElementById("statusUser").value = actualVacations.statusUser

             
          data1 += `
          <tbody>
              <tr>
              <th scope="row">${allVacations.id} </td>
              <td>${allVacations.vacationDays}</td> 
              <td>${allVacations.startAt}</td> 
              <td>${allVacations.endAt} </td>
              <td>${allVacations.statusVacationRequest}</td> 
              <td>${allVacations.user.name}</td> 
              <td>${allVacations.user.email}</td>
              <td>${allVacations.user.daysBalance}</td>
              <td>${allVacations.user.profileEnum}</td>
              <td>${allVacations.user.statusUser}</td>
              </tr>
          </tbody>
          `
      console.log(data1)
      document.getElementById("findById").innerHTML = data1;
      console.log(todosUsuarios); 
      })
    // //   else{
    // //     alert("Error: " + response.status + " - Preencha o formulário com dados validos");
    // //     console.log(response);
    //  }  
    } catch(error) {
      console.log(error);
    }
   
    

  }
  

  const form = document.getElementById("usuario-form");
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
      const response = await fetch(`http://localhost:8080/vacationsrequest/${actualVacations.id}`, {
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
    const response = await fetch(`http://localhost:8080/vacationsrequest/cancel/${actualVacations.id}`, {
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
  } else{
    alert("Error: " + response.status + " - Preencha o formulário com dados validos");
    console.log(response);
  }  
} catch(error) {
  console.log(error);
}
  
}

function openDeleteButton() {
  // event.preventDefault();
  var myModal = new bootstrap.Modal(document.getElementById("deleteModal"));
  var modalToggle = document.getElementById("deleteModal");

  myModal.show();
}