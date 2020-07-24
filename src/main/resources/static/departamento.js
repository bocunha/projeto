var templateLinha = `<tr><td><img src="**FOTO**" width="100%"></td>
                    <td><strong>Nome:</strong> **NOME** <br>
                    <strong>RACF:</strong> **RACF** <br>
                    <strong>EMAIL:</strong> **EMAIL** <br>
                    <strong>DEPARTAMENTO:</strong> **DEPTO** <br>
                    <strong>UNIDADE:</strong> **UNIDADE** </td> </tr>
                   `;





var url = "https://isidronianos.herokuapp.com"

function preencheInfo() {

    var user = localStorage.getItem("EvtUser");
    if (!user) {
        window.location = "index.html";
    }
    else {

        var search = window.location.search;
        var idDepto = search.substr(4);

        console.log("Id Depto = " + idDepto);

        fetch(url+ "/departamentos/" + idDepto)
            .then(res => res.json())
            .then(res => PreencheUser(res));

        function PreencheUser(res) {
            var tabela = "";
            console.log(Object.keys(res.usarUsuario).length);
            console.log(res);
        
            var objUser = JSON.parse(user);  // aqui vou converter a STRING armazenada para um objeto

            for (i = 0; i < Object.keys(res).length; i++) {
                var User = res.usarUsuario[i];
                console.log(User);
                var strLinha = templateLinha.replace("**NOME**", User.nome)
                    .replace("**RACF**", User.racf)
                    .replace("**EMAIL**", User.email)
                    .replace("**DEPTO**", objUser.depto.nomedpt)
                    .replace("**UNIDADE**", objUser.depto.unidade)
                    .replace("**FOTO**", User.linkFoto);
                    console.log(strLinha);
                tabela = tabela + strLinha;
            }
            document.getElementById("relatorio").innerHTML = tabela;
        }
    }
}

function logout() {
    window.location = "index.html";
    localStorage.removeItem("EvtUser");
}