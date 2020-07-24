var templateFoto = `<img src="**FOTO**" width="100%">`;
var templateInfo = `<strong>Nome:</strong> **NOME** <br>
                    <strong>RACF:</strong> **RACF** <br>
                    <strong>EMAIL:</strong> **EMAIL** <br>
                    <strong>DEPARTAMENTO:</strong> <a href="departamento.html?id=**ID**">**DEPTO**</a> <br>
                    <strong>UNIDADE:</strong> **UNIDADE**
                   `;


var url = "https://isidronianos.herokuapp.com"

function preencheInfo(){

    var user = localStorage.getItem("EvtUser");
    if (!user){
        window.location = "index.html";
    }
    else{
        var objUser = JSON.parse(user);  // aqui vou converter a STRING armazenada para um objeto
        var strFoto = templateFoto.replace("**FOTO**", objUser.linkFoto);
        document.getElementById("fotoUser").innerHTML = strFoto;

        var strInfo = templateInfo.replace("**NOME**", objUser.nome)
                                  .replace("**RACF**", objUser.racf)
                                  .replace("**EMAIL**", objUser.email)
                                  .replace("**DEPTO**", objUser.depto.nomedpt)
                                  .replace("**UNIDADE**", objUser.depto.unidade)
                                  .replace("**ID**",objUser.depto.id);
        document.getElementById("infoUser").innerHTML = strInfo;
    }
}

function logout(){
    window.location = "index.html";
    localStorage.removeItem("EvtUser");
}
