 //table - trazer vacations request cadastrados na tela
 fetch("http://localhost:8080/user/vacationsrequest").then((data)=>{
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
                  <td>${values.user}</td> 
                  <td>${values.id}</td> 
                  <td>${values.name}</td>
                  <td>${values.email}</td>
                  <td>${values.birthDate}</td>
                  <td>${values.hiringDate}</td>
                  <td>${values.daysBalance}</td>
                  <td>${values.profileEnum}</td>
                  <td>${values.statusUser}</td>
              </tr>
          </tbody>
          `
      })
      document.getElementById("allDicesVacationRequest").innerHTML = data1;
      console.log(allVacations); 
  })