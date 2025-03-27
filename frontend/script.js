document.addEventListener("DOMContentLoaded", atualizarOpcoesMensalidades);

function atualizarOpcoesMensalidades() {
    const tipo = document.getElementById("tipoFinanciamento").value;
    const selectMensalidades = document.getElementById("numeroMensalidades");

    selectMensalidades.innerHTML = "";

    const maxMeses = tipo === "INTERNAL" ? 48 : 60;

    for (let i = 12; i <= maxMeses; i += 12) {
        let option = document.createElement("option");
        option.value = i;
        option.textContent = `${i} meses`;
        selectMensalidades.appendChild(option);
    }
}
function calculaFinanciamento() {
    let valor = document.getElementById("valorViatura").value.replace(",", ".");
    let tipo = document.getElementById("tipoFinanciamento").value;
    let meses = document.getElementById("numeroMensalidades").value;

    if (!valor || isNaN(valor) || valor <= 0) {
        alert("Por favor, insira um valor válido para o veículo.");
        return;
    }

    fetch("http://localhost:8080/v1/api/simulator/calculate", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            financingType: tipo,
            numberOfInstallments: parseInt(meses),
            vehicleValue: parseFloat(valor)
        })
    })
    .then(response => response.json())
    .then(data => {
        let installmentAmount = data.installmentAmount;
        document.getElementById("resultado").innerHTML =
            "Valor da Prestação :" + installmentAmount.toFixed(2) + "€";

        localStorage.setItem("financiamentoData", JSON.stringify({
            vehicleValue: parseFloat(valor),
            financingType: tipo,
            numberOfInstallments: parseInt(meses),
            installmentAmount: valorPrestacao
        }));

        document.getElementById("btnSalvar").disabled = false;
    })
    .catch(error => console.error("Erro ao calcular:", error));
}

function irParaSalvar() {
    window.location.href = "salvar.html";
}
document.addEventListener("DOMContentLoaded", () => {
    const financiamentoData = JSON.parse(localStorage.getItem("financiamentoData"));

    document.getElementById("resValorViatura").textContent = financiamentoData.valorViatura.toFixed(2);
    document.getElementById("resTipoFinanciamento").textContent = financiamentoData.tipoFinanciamento;
    document.getElementById("resNumeroMensalidades").textContent = financiamentoData.numeroMensalidades;
    document.getElementById("resValorPrestacao").textContent = financiamentoData.valorPrestacao.toFixed(2);
});

function salvaFinanciamento() {
    const financiamentoData = JSON.parse(localStorage.getItem("financiamentoData"));
    let nome = document.getElementById("nome").value;
    let contacto = document.getElementById("contacto").value;

    if (!nome || !contacto) {
        alert("Por favor, preencha nome e contacto.");
        return;
    }

    fetch("http://localhost:8080/v1/api/simulator/save", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            tipoFinanciamento: financiamentoData.tipoFinanciamento,
            numeroMensalidades: financiamentoData.numeroMensalidades,
            valorViatura: financiamentoData.valorViatura,
            valorPrestacao: financiamentoData.valorPrestacao,
            cliente: {
                nome: nome,
                contacto: contacto
            }
        })
    })
    .then(response => response.text())
    .then(data => {
        alert("Financiamento salvo com sucesso!");
        localStorage.removeItem("financiamentoData"); // Limpa os dados após salvar
        window.location.href = "index.html";
    })
    .catch(error => console.error("Erro ao salvar:", error));
}


