 //table - trazer usuários cadastrados na tela
 fetch("http://localhost:8080/users").then((data)=>{
    return data.json();
  }).then((todosUsuarios) =>{
      let data1 = "";
      todosUsuarios.map((values) =>{
          data1 += `
          <tbody>
              <tr>
                  <th scope="row">${values.id} </td>
                  <td>${values.name}</td> 
                  <td>${values.email}</td> 
                  <td>${values.birthDate} </td>
                  <td>${values.hiringDate}</td> 
                  <td>${values.daysBalance}</td> 
                  <td>${values.profileEnum}</td> 
                  <td>${values.statusUser}</td> 

              </tr>
          </tbody>
          `
      })
      document.getElementById("usuarioDados").innerHTML = data1;
      console.log(todosUsuarios); 
  })