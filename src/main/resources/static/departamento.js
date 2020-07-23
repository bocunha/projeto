var templateFoto = `<img src="**FOTO**" width="100%">`;
var templateInfo = `<strong>Nome:</strong> **NOME** <br>
                    <strong>RACF:</strong> **RACF** <br>
                    <strong>EMAIL:</strong> **EMAIL** <br>
                    <strong>DEPARTAMENTO:</strong> **DEPTO** <br>
                    <strong>UNIDADE:</strong> **UNIDADE**
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

        fetch("http://localhost:8088/departamentos/" + idDepto)
            .then(res => res.json())
            .then(res => PreencheUser(res));

        function PreencheUser(res) {
            var UserDepartamento = "";
            for (i = 0; i < res.length; i++) {
                console.log(res);
                var objUser = res[i];
                var strFoto = templateFoto.replace("**FOTO**", objUser.linkFoto);

                document.getElementById("fotoUser").innerHTML = strFoto;

                var strInfo = templateInfo.replace("**NOME**", objUser.nome)
                    .replace("**RACF**", objUser.racf)
                    .replace("**EMAIL**", objUser.email)
                    .replace("**DEPTO**", objUser.depto.nomedpt)
                    .replace("**UNIDADE**", objUser.depto.unidade)
                    .replace("**ID**", objUser.depto.id);
                document.getElementById("infoUser").innerHTML = strInfo;
                console.log(objUser);
            }
        }
    }
}

function logout() {
    window.location = "index.html";
    localStorage.removeItem("EvtUser");
}