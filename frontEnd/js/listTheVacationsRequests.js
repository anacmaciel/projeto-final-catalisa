 //table - trazer vacations request cadastrados na tela
 fetch("http://localhost:8080/vacationsrequest").then((data)=>{
    return data.json();
  }).then((allVacations) =>{
      let data1 = "";
      allVacations.map((values) =>{
          data1 += `
          <tbody>
              <tr>
                  <th scope="row">${values.id} </td>
                  <td>${values.vacationDays}</td> 
                  <td>${values.startAt}</td> 
                  <td>${values.endAt} </td>
                  <td>${values.statusVacationRequest}</td> 
                  <td>${values.user.name}</td> 
                  <td>${values.user.email}</td>
                  <td>${values.user.daysBalance}</td>
                  <td>${values.user.profileEnum}</td>
                  <td>${values.user.statusUser}</td>
              </tr>
          </tbody>
          `
      })
      document.getElementById("allDicesVacationRequest").innerHTML = data1;
      console.log(allVacations); 
  })