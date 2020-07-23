var templateLinha = `
<tr>
<td>**DATA**</td>
<td>**ALARME**</td>
<td>**HOSTNAME**</td>
<td>**IP**</td>
</tr>
`;

function checkLogin() {
    var user = localStorage.getItem("EvtUser");
    if (!user) {
        window.location = "index.html";
    }
}

function gerarRelatorio() {
    var inicioData = document.getElementById("inicioFiltro").value;
    var fimData = document.getElementById("fimFiltro").value;
    console.log(inicioData, fimData);

    var msgBody = {
        inicio: inicioData,
        fim: fimData
    }

    var cabecalho = {
        method: 'POST',
        body: JSON.stringify(msgBody),
        headers: {
            'Content-type': 'application/json'
        }
    }

    fetch("http://localhost:8088/eventos/periodo", cabecalho)
        .then(res => res.json())
        .then(res => preencheTabela(res));
}

function preencheTabela(res) {
    console.log(res);
    var tabela = "";

    for (i = 0; i < res.length; i++) {
        var evento = res[i];

        var strLinha = templateLinha.replace("**DATA**", evento.data)
            .replace("**ALARME**", evento.alarme.nome)
            .replace("**HOSTNAME**", evento.equipamento.hostname)
            .replace("**IP**", evento.equipamento.endIp)
            .replace("**ID**", evento.equipamento.id);
        tabela = tabela + strLinha;
    }
    document.getElementById("relatorio").innerHTML = tabela;
}

function logout(){
    window.location = "index.html";
    localStorage.removeItem("EvtUser");
}