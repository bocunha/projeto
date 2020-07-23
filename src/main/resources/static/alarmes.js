function checkLogin() {
    var user = localStorage.getItem("EvtUser");
    if (!user) {
        window.location = "index.html";
    }
}

var templateLinha = `
<tr>
<td>**QTDE**</td>
<td>**ALARME**</td>
</tr>
`;


function gerarRelatorioAlarmes() {
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

    fetch("https://isidrianos.herokuapp.com/eventos/alarmes/periodo", cabecalho)
        .then(res => res.json())
        .then(res => preencheTabelaAlarme(res));
}

function preencheTabelaAlarme(res) {
    console.log(res);
    var tabela = "";

    for (i = 0; i < res.length; i++) {
        var evento = res[i];
        var estiloLinha;
        if (i % 2 == 0) {
            estiloLinha = "linhaPar";
        }
        else {
            estiloLinha = "linhaImpar";
        }

        var strLinha = templateLinha.replace("**QTDE**", evento.qtde)
            .replace("**ALARME**", evento.nomeAlarme),
        tabela = tabela + strLinha;
    }
    document.getElementById("relatorio").innerHTML = tabela;
}



function gerarRelatorioConsolidado() {

    var cabecalho = {
        method: 'GET'
    }

    fetch("http://isidrianos.herokuapp.com/eventos/alarmes/top", cabecalho)
        .then(res => res.json())
        .then(res => preencheTabela(res));
}

function preencheTabela(res) {
    console.log(res);
    var tabela = "";

    for (i = 0; i < res.length; i++) {
        var evento = res[i];
        
        console.log(top.nome);
        var strLinha = templateLinha.replace("**QTDE**", evento.qtde)
            .replace("**ALARME**", evento.nomeAlarme),
        tabela = tabela + strLinha;
    }

    document.getElementById("relatorio").innerHTML = tabela;
}

function logout(){
    window.location = "index.html";
    localStorage.removeItem("EvtUser");
}