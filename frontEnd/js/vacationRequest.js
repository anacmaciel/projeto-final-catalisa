const form = document.getElementById("vacationsRequest-form");
function openSubmitButton() {
    var myModal = new bootstrap.Modal(document.getElementById("myModal"));
    var modalToggle = document.getElementById("myModal");
  
    myModal.show();
  }

async function msg() {
    const formData = new FormData(form); // Pega o formulário e joga em uma variável 
    const data = Object.fromEntries(formData); //Se torna em objeto

    try {
        const response = await fetch("http://localhost:8080/user/vacationsrequest", {
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

        document.getElementById("vacationsRequest-form").reset();
      } else{
        alert("Error: " + response.status + " - Preencha o formulário com dados validos");
        console.log(response);
      }  
    } catch(error) {
      console.log(error);
    }
}