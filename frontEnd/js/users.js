const form = document.getElementById("usuario-form");
function openSubmitButton() {
    // event.preventDefault();
    var myModal = new bootstrap.Modal(document.getElementById("myModal"));
    var modalToggle = document.getElementById("myModal");
  
    myModal.show();
  }

async function msg() {
    const formData = new FormData(form); // Pega o formulário e joga em uma variável 
    const data = Object.fromEntries(formData); //Se torna em objeto
    console.log( JSON.stringify(data))
    return
    try {
        const response = await fetch("http://localhost:8080/users", {
          method: 'POST',
          headers: {
           "Content-Type": "application/json",
         },
         body: JSON.stringify(data),
      });
      if (response.status == 201) {
        alert("Salvo com sucesso!");
        var myModalEl = document.querySelector("#myModal"); //Id
        var modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
        modal.hide();

        document.getElementById("usuario-form").reset();
      } else{
        alert("Error: " + response.status);
        console.log(response);
      }  
    } catch(error) {
      console.log(error);
    }
}
































































// const form = document.getElementById("usuario-form")

// form.addEventListener('submit', event => { // ouvir até o submit ser acionado
//     event.preventDefault();

//     const formData = new FormData(form); // Pega o formulário e java em uma variável 
//     const data = Object.fromEntries(formData); // Se torna em objeto 

//     fetch("http://localhost:8080/users", {
//         method: 'POST',
//         headers: {
//             'Content-Type':'application/json'
//         },
//         body: JSON.stringify(data)
//     }).then(res => { 
//         alert("Salvo com sucesso!")
//          res.json();} )
//         .then(data => {
//             alert("Salvo data")
//             console.log(data)
//         })
//         .catch(error => console.log(error));
// });

// function msg() {
//     window.location.reload(true);
//   }

 