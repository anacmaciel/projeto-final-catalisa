const form = document.getElementById("usuario-form");
function openSubmitButton() {
    
    var myModal = new bootstrap.Modal(document.getElementById("myModal"));
    var modalToggle = document.getElementById("myModal");
  
    myModal.show();
  }

async function msg() {
    const formData = new FormData(form); 
    const data = Object.fromEntries(formData); 
    console.log( JSON.stringify(data))
   
    try {
        const response = await fetch("http://localhost:8080/users", {
          method: 'POST',
          headers: {
           "Content-Type": "application/json",
         },
         body: JSON.stringify(data),

      })
      if (response.status == 201) {
        alert("Salvo com sucesso!");
        var myModalEl = document.querySelector("#myModal"); //Id
        var modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
        modal.hide();

        document.getElementById("usuario-form").reset();
      } else{
        alert("Error: " + response.status + " - Preencha o formulário com dados validos");
        console.log(response.text());
      }  
    } catch(error) {
      console.log("catch",error);
    }
}