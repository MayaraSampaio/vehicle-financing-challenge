document.addEventListener("DOMContentLoaded", updateInstallmentOptions);

function updateInstallmentOptions() {
    const type = document.getElementById("financingType").value;
    const selectInstallments = document.getElementById("numberOfInstallments");

    selectInstallments.innerHTML = "";

    const maxMonths = type === "INTERNAL" ? 48 : 60;

    for (let i = 12; i <= maxMonths; i += 12) {
        let option = document.createElement("option");
        option.value = i;
        option.textContent = `${i} meses`;
        selectInstallments.appendChild(option);
    }
}
function calculateFinancing() {
    let value = document.getElementById("vehicleValue").value.replace(",", ".");
    let type = document.getElementById("financingType").value;
    let months = document.getElementById("numberOfInstallments").value;

    if (!value || isNaN(value) || value <= 0) {
        alert("Por favor, insira um valor válido para o veículo.");
        return;
    }
    fetch("http://localhost:8080/v1/api/simulator/calculate", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            financingType: type,
            numberOfInstallments: parseInt(months),
            vehicleValue: parseFloat(value)
        })
    })
    .then(response => response.json())
    .then(data => {
        let installmentAmount = data.installmentAmount;
        document.getElementById("result").innerHTML =
            "Valor da Prestação :" + installmentAmount.toFixed(2) + "€";

        localStorage.setItem("financingData", JSON.stringify({
            vehicleValue: parseFloat(value),
            financingType: type,
            numberOfInstallments: parseInt(months),
            installmentAmount: installmentAmount
        }));

        document.getElementById("btnSalvar").disabled = false;
    })
    .catch(error => console.error("Erro ao calcular:", error));
}
function goToSave() {
    window.location.href = "save.html";
}

document.addEventListener("DOMContentLoaded", () => {
    const financingData = JSON.parse(localStorage.getItem("financingData"));

    document.getElementById("resvehicleValue").textContent = financingData.vehicleValue.toFixed(2);
    document.getElementById("resfinancingType").textContent = financingData.financingType;
    document.getElementById("resnumberOfInstallments").textContent = financingData.numberOfInstallments;
    document.getElementById("resinstallmentAmount").textContent = financingData.installmentAmount.toFixed(2);
});
function saveFinancing() {
    const financingData = JSON.parse(localStorage.getItem("financingData"));
    let name = document.getElementById("name").value;
    let contact = document.getElementById("contact").value;

    if (!name || !contact) {
        alert("Por favor, preencha nome e contacto.");
        return;
    }
    fetch("http://localhost:8080/v1/api/simulator/save", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            financingType: financingData.financingType,
            numberOfInstallments: financingData.numberOfInstallments,
            vehicleValue: financingData.vehicleValue,
            installmentAmount: financingData.installmentAmount,
            client: {
                name: name,
                contact: contact
            }
        })
    })
    .then(response => response.text())
    .then(data => {
        alert("Financiamento salvo com sucesso!");
        localStorage.removeItem("financingData"); // Clear data after saving
        window.location.href = "index.html";
    })
    .catch(error => console.error("Erro ao salvar:", error));
}
