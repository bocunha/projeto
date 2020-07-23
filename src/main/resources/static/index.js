function clickBotaoEnviar(){

    /*
     DOM - Document Object Model

     através do objeto document referencio qualquer elemento da página pelo seu ID
    */

    var txtEmail = document.getElementById("txtEmail").value;
    var txtSenha = document.getElementById("txtSenha").value;

    //console.log("Email: "+txtEmail+" /  Senha: "+txtSenha);

    // a partir do momento que eu capturo as informações, preciso agora criar uma requisicao 
    // POST para nosso backEnd
    // Fazendo o paralelo com o POSTMAN
    // Defino um URL e um método do tipo POST
    // Defino um body contendo um JSON (do tipo JSON), que tem um objeto do tipo Usuario
    // apenas com email e senha

    // corpo da mensagem
    var msgBody = {   
        email : txtEmail,
        senha : txtSenha
    }

    // agora preciso definir o cabecalho da requisicao
    var cabecalho = {
        method : 'POST',
        body   : JSON.stringify(msgBody),
        headers : {
            'Content-type':'application/json'
        }
    }

    // chamo a funcao FETCH para entrar em contato com o BackEnd. Assim que receber a resposta,
    // chama a função tratarResposta (para poder dar uma mensagem específica a cada código HTTP)
    fetch("http://localhost:8088/login", cabecalho)
        .then(res => tratarResposta(res));
}

function tratarResposta(objeto){
    // dependendo do código, faz uma determinada ação
    // se for código 200 - extrai o JSON do corpo da resposta e chama a função 
    //                     LOGAR passando o objeto de Resposta recebido
    // se for 401 ou 404 exibe uma mensagem de erro na div "msgResposta"

    console.log(objeto);
    if (objeto.status === 200){
        //console.log("Deu certo o login...");
        objeto.json().then(res => logar(res));
    }
    else if (objeto.status === 401){
        document.getElementById("msgResposta").innerHTML = "Senha Inv&aacute;lida";
    }
    else {  // 404
        document.getElementById("msgResposta").innerHTML = "Usu&aacute;rio n&atilde;o encontrado no Sistema";
    }
}

function logar(usuario){
    // armazena a STRING correspondente ao objeto dos dados do usuário no LocalStorage 
    // para indicar que o usuário está conectado e redireciona para a página HOME
    localStorage.setItem("EvtUser", JSON.stringify(usuario) );
    window.location = "home.html";  
}

function verificaTecla(event){
    if (event.keyCode === 13){
        clickBotaoEnviar();
    }

}