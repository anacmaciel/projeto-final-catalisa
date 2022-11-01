//table - trazer vacations cadastrados na tela
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
      let allUsers = data
      if (data == null){
        alert("Vacation request não encontrada")
        return
      }
      
        console.log(allUsers)
      
          data1 += `
          <tbody>
              <tr>
              <th scope="row">${allUsers.id} </td>
              <td>${allUsers.vacationDays}</td> 
              <td>${allUsers.startAt}</td> 
              <td>${allUsers.endAt} </td>
              <td>${allUsers.statusVacationRequest}</td> 
              <td>${allUsers.user.name}</td> 
              <td>${allUsers.user.email}</td>
              <td>${allUsers.user.daysBalance}</td>
              <td>${allUsers.user.profileEnum}</td>
              <td>${allUsers.user.statusUser}</td>
              </tr>
          </tbody>
          `
      console.log(data1)
      document.getElementById("findById").innerHTML = data1;
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