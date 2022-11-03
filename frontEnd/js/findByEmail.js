//table - trazer usuários cadastrados na tela
let actualUser = {} 
async function findUserByEmail(){
    const email = document.getElementById("emailSearch").value

    try {

    const response = await fetch(`http://localhost:8080/users/${email}`, {
        method: 'GET',
        headers: {
        "Content-Type": "application/json",
        },
    }).then((response) => response.json())
    .then((data) => {console.log(data)
  
    
        let data1 = "";
      let allUsers = data
      if (data.status == 404){
        alert("Usuário não encontrado")
        return
      }
      
        console.log(allUsers)
      
        actualUser.id = allUsers.id
        actualUser.daysBalance = allUsers.daysBalance
        actualUser.email = allUsers.email
        document.getElementById("name").value = allUsers.name
        document.getElementById("email").value = allUsers.email
        document.getElementById("birthDate").value = allUsers.birthDate
        document.getElementById("hiringDate").value = allUsers.hiringDate
        document.getElementById("profileEnum").value = allUsers.profileEnum
        document.getElementById("daysBalance").value = allUsers.daysBalance

   
          data1 += `
          <tbody>
              <tr>
                  <td>${allUsers.name}</td> 
                  <td>${allUsers.email}</td> 
                  <td>${allUsers.birthDate} </td>
                  <td>${allUsers.hiringDate}</td> 
                  <td>${allUsers.daysBalance}</td> 
                  <td>${allUsers.profileEnum}</td> 
                  <td>${allUsers.statusUser}</td> 

              </tr>
          </tbody>
          `
      console.log(data1)
      document.getElementById("findByEmail").innerHTML = data1;
      console.log(todosUsuarios); 
      })
   
    } catch(error) {
      console.log(error);
    }
    
   
      
  }


  const form = document.getElementById("usuario-form");
function openSubmitButton() {
    var myModal = new bootstrap.Modal(document.getElementById("myModal"));
    var modalToggle = document.getElementById("myModal");
  
    myModal.show();
  }

 async function updateUser() {
    const formData = new FormData(form); // Pega o formulário e joga em uma variável 
    const data = Object.fromEntries(formData); //Se torna em objeto
    console.log( JSON.stringify(data))
   
    let objectUpdate = {
      name: data.name,
      birthDate: data.birthDate, 
      hiringDate: data.hiringDate,
      daysBalance: data.daysBalance,
      profileEnum: data.profileEnum,
      statusUser: "ACTIVE",
      
    }
    console.log(objectUpdate)



    try {
        const response = await fetch(`http://localhost:8080/users/${actualUser.id}`, {
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

  async function disableUser() {
    
    try {
      const response = await fetch(`http://localhost:8080/users/inactive/${actualUser.email}`, {
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
    var myModal = new bootstrap.Modal(document.getElementById("deleteModal"));
    var modalToggle = document.getElementById("deleteModal");
  
    myModal.show();
  }