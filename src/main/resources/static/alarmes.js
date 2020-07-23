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

var url = "https://isidronianos.herokuapp.com"


function gerarRelatorioAlarmes() {
    var inicioData = document.getElementById("inicioFiltro").value;
    var fimData = document.getElementById("fimFiltro").value;

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

    fetch(url+"/eventos/alarmes/periodo", cabecalho)
        .then(res => res.json())
        .then(res => preencheTabelaAlarme(res));
}

function preencheTabelaAlarme(res) {
    var tabela = "";

    for (i = 0; i < res.length; i++) {
        var evento = res[i];

        var strLinha = templateLinha.replace("**QTDE**", evento.qtde)
            .replace("**ALARME**", evento.nomeAlarme),
        tabela = tabela + strLinha;
    }
    document.getElementById("relatorio").innerHTML = tabela;
    geraGrafico(res);

}

function gerarRelatorioConsolidado() {

    var cabecalho = {
        method: 'GET'
    }

    fetch(url+"/eventos/alarmes/top", cabecalho)
        .then(res => res.json())
        .then(res => preencheTabela(res));
        geraGrafico(res);
}

function preencheTabela(res) {
    var tabela = "";

    for (i = 0; i < res.length; i++) {
        var evento = res[i];
        
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

function geraGrafico(res) {
    var canvas = document.getElementById("canvas");
    var ctx = canvas.getContext("2d");
    var colors = ['#4CAF50', '#00BCD4', '#E91E63', '#FFC107', '#9E9E9E'];
    var angles = [Math.PI * 0.3, Math.PI * 0.7, Math.PI * 0.2, Math.PI * 0.4];
    var beginAngle = 0;
    var endAngle = 0;
    var total_alarmes = 0;
    var y = 200
    for (i = 0; i < res.length; i++) {
        total_alarmes = total_alarmes + res[i].qtde;
    }

    for (i = 0; i < res.length; i++) {
        var angulo = 2 * Math.PI * (res[i].qtde / total_alarmes);
        beginAngle = endAngle;
        endAngle = endAngle + angulo;

        ctx.beginPath();

        ctx.fillStyle = colors[i % colors.length];


        ctx.moveTo(200, 200);
        y = y + 20;
        ctx.arc(200, 200, 120, beginAngle, endAngle);
        ctx.lineTo(200, 200);
        ctx.stroke();
        ctx.font = '14px Calibri';
        ctx.fillRect(350, y - 14, 18, 18);
        ctx.fillText(res[i].nomeAlarme + " - " + res[i].qtde +
            " (" + (res[i].qtde / total_alarmes * 100).toFixed(1) + "% )", 375, y);


        ctx.fill();
    }
}


function exportarPDF(table_id) {
    var conteudoTabela = document.getElementById(table_id).innerHTML;
    var conteudoGrafico = document.getElementById("canvas").toDataURL(); 
    var win = window.open('', '', 'height=700,width=700');

    win.document.write('<html><head>  <script type="text/javascript" src="alarmes.js"></script>');
    win.document.write('<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"');
    win.document.write('integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">');

    win.document.write('</head>');
    win.document.write('<body>');
    win.document.write('<table id="' + table_id + '" class="table table-striped table-hover">')
    win.document.write('<div class="row"></div>')
    win.document.write('<div class="col-12">')
    win.document.write(conteudoTabela);
    win.document.write('</table>');
    win.document.write('</div></div>');
    win.document.write('<img id=graph src="' + conteudoGrafico + '" onload=printPDF()>')
    win.document.write('</body></html>');
    }

function printPDF (){
        print();
        close();
}

function exportarCSV(table_id) {
    var rows = document.querySelectorAll('table#' + table_id + ' tr');
    var csv = [];
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll('td, th');
        for (var j = 0; j < cols.length; j++) {
            var data = cols[j].innerText.replace(/(\r\n|\n|\r)/gm, '').replace(/(\s\s)/gm, ' ')
            data = data.replace(/"/g, '""');
            row.push('"' + data + '"');
        }
        csv.push(row.join(';'));
    }
    var csv_string = csv.join('\n');
    // Download it
    var filename = 'Alarmes' + new Date().toLocaleDateString() + '.csv';
    var link = document.createElement('a');
    link.style.display = 'none';
    link.setAttribute('target', '_blank');
    link.setAttribute('href', 'data:text/csv;charset=utf-8,' + encodeURIComponent(csv_string));
    link.setAttribute('download', filename);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}
