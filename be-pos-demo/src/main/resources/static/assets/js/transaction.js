const modalRincian = document.getElementById('modalRincian')
const modalSucces = document.getElementById("modalSuccess");


const orderReq = {
  customer: "",
  orders: null,
  amount: 0,
};

modalRincian.addEventListener('show.bs.modal', event => {

  const rincianTable = event.target.querySelector(".rincian-table table");
  const rincianTotal = event.target.querySelector(".rincian-total p");


  // orders from main.js file (array)
  let existingOrder = orders;
  let formatPrice = 0;
  let formatTotal = 0

  existingOrder.forEach((order, i) => {
    let row = rincianTable.insertRow(i + 1)
    let cell1 = row.insertCell(0)
    let cell2 = row.insertCell(1)
    let cell3 = row.insertCell(2)

    row.classList.add("newTr")
    formatPrice = new Intl.NumberFormat("id-ID").format(order.price);
    cell1.innerHTML = order.produk
    cell2.innerHTML = order.qty
    cell3.innerHTML = "Rp. " + formatPrice
  })

  // TOTAL_ORDER from main.js file (number)
  formatTotal = new Intl.NumberFormat("id-ID").format(TOTAL_ORDER);
  rincianTotal.innerHTML = formatTotal;

})

// delete row produk when batal
modalRincian.addEventListener("hidden.bs.modal", evt => {

  const rincianTable = evt.target.querySelector(".rincian-table table");

  //delete all rows
  let allRowTr = rincianTable.querySelectorAll("tr.newTr");
  allRowTr.forEach(tr => tr.remove())

})


document.querySelector(".form-bayar").addEventListener("submit", processOrder)

function processOrder(evt) {

  const rincianTable = document.querySelector(".rincian-table table");
  if (rincianTable.rows.length == 1) {
    Swal.fire({
      title: 'Warning',
      text: 'Masukan minimal 1 Produk',
      icon: "warning",
      confirmButtonText: "Oke"
    })
  }

  evt.preventDefault();

  let customer = document.querySelector(".field-customer .customer").value;
  let nominal = document.querySelector(".field-customer .nominal").value;

  if(customer == "" || nominal == "") {
    Swal.fire({
      title: 'Transaksi Gagal!',
      text: 'Silahkan Masukan Nama dan Nominal',
      icon: 'error',
      confirmButtonText: 'Tutup'
    })
    return;
  }

  orderReq.customer = customer;
  // orders from main.js file (array)
  orderReq.orders = orders;
  orderReq.amount = nominal;


  // Close modal
  const getModalRincian = bootstrap.Modal.getInstance("#modalRincian");
  getModalRincian.hide()

  // show loading page
  showLoadingPage()
  
  // request ajax to BE
  $.ajax({
     url: BASE_URL + "new-order",
//    url: "http://localhost:3000/test",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(orderReq),
    success: function(res, status) {
      setTimeout(() => succesOrder(res), 1500);
    },
    error: function(xhr, status, err) {
      if(status) {
        errorOrder()
      }
    }
  })

}


function succesOrder(res) {

  // close loading
  Swal.close();


  let tableChange = modalSucces.querySelector(".table-change")
  //clear table dlu
  tableChange.innerHTML = "";
  tableChange.innerHTML += `
  <tr>
    <th>Nama Customer</th>
    <td>${orderReq.customer}</td>
  </tr>
  <tr>
    <th>Nominal Uang</th>
    <td>Rp. ${orderReq.amount}</td>
  </tr>

  <tr>
    <th>Kembalian</th>
    <th>Rp. ${res.change}</th>
  </tr>`;


  // show modal Succes
  const getModalSucces = bootstrap.Modal.getOrCreateInstance("#modalSuccess")
  setTimeout(() => getModalSucces.show(), 500)

}


// delete order card
modalSucces.addEventListener("hidden.bs.modal", evt => {

  let orderCards = document.querySelectorAll(".card-order");

  orderCards.forEach(order => order.remove())

  clearOrders()
})


function clearOrders() {
  orders = []
  orderReq.amount = 0
  orderReq.customer = ""
  orderReq.orders = null
}


function showLoadingPage() {

  Swal.fire({
    title: 'Mohon Tunggu',
    text: 'Transaksi sedang di proses',
    iconHtml: "<span class='loader'></span>",
    iconColor: "#ffffff",
    allowOutsideClick: false,
    allowEnterKey: false,
    showConfirmButton: false,
  })

}


function errorOrder() {

  // close loading
  Swal.close();

  Swal.fire({
    title: 'Error',
    text: 'Transaksi gagal di proses',
    icon: "error",
    confirmButtonText: "Tutup"
  })
}
