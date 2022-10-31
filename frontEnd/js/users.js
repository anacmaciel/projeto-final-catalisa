const form = document.getElementById("usuario-form")

form.addEventListener('submit', event => { // ouvir até o submit ser acionado
    event.preventDefault();

    const formData = new FormData(form); // Pega o formulário e java em uma variável 
    const data = Object.fromEntries(formData); // Se torna em objeto 

    fetch("http://localhost:8080/users", {
        method: 'POST',
        headers: {
            'Content-Type':'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => { 
        alert("Salvo com sucesso!")
         res.json();} )
        .then(data => {
            alert("Salvo data")
            console.log(data)
        })
        .catch(error => console.log(error));
});

function msg() {
    window.location.reload(true);
  }

 