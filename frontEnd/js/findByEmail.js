//table - trazer usuários cadastrados na tela
async function findUserByEmail(){
    const email = document.getElementById("email").value

    try {

    const response = await fetch(`http://localhost:8080/users/${email}`, {
        method: 'GET',
        headers: {
        "Content-Type": "application/json",
        },
    }).then((response) => response.json())
    .then((data) => {console.log(data)
   // if (response.status == 200) {
        let data1 = "";
      let allUsers = data
      if (data == ""){
        alert("Usuário não encontrado")
        return
      }
      
        console.log(allUsers)
      
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
    //   else{
    //     alert("Error: " + response.status + " - Preencha o formulário com dados validos");
    //     console.log(response);
    //   }  
    } catch(error) {
      console.log(error);
    }
    
   
      
  }