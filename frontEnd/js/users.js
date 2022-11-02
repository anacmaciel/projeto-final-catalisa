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
   
    try {
        const res = await fetch("http://localhost:8080/users", {
          method: 'POST',
          headers: {
           "Content-Type": "application/json",
         },
         body: JSON.stringify(data),
      }).then(function(data){
          console.log("retorno response", data)
      }).then(function(response){
        console.log("retorno response2", response)
      })
      if (res.status == 201) {
        alert("Salvo com sucesso!");
        var myModalEl = document.querySelector("#myModal"); //Id
        var modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
        modal.hide();

        document.getElementById("usuario-form").reset();
      } else{
        alert("Error: " + res.status + " - Preencha o formulário com dados validos");
        console.log(res.text());
      }  
    } catch(error) {
      console.log("catch",error);
    }
}