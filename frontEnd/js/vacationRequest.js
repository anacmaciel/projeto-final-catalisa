const form = document.getElementById("usuario-form")

form.addEventListener('submit', event => { // ouvir até o submit ser acionado
    event.preventDefault();

    const formData = new FormData(form); // Pega o formulário e java em uma variável 
    const data = Object.fromEntries(formData); // Se torna em objeto 

    fetch("http://localhost:8080/user/vacationsrequest", {
        method: 'POST',
        headers: {
            'Content-Type':'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => res.json())
        .then(data => console.log(data))
        .catch(error => alert('Falha na requisicao'));
});

function msg() {   
    window.location.reload(true);
  }
  