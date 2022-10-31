 //table - trazer usuários cadastrados na tela
 fetch("http://localhost:8080/users").then((data)=>{
    return data.json();
  }).then((allUsers) =>{
      let data1 = "";
      allUsers.map((values) =>{
          data1 += `
          <tbody>
              <tr>
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
      document.getElementById("allDicesUsers").innerHTML = data1;
      console.log(todosUsuarios); 
  })