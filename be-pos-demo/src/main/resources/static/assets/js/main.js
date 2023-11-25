const BASE_URL = "http://localhost:3000/api/";

let SUBTOTAL_VALUE = 0;
let TOTAL_ORDER = 0;
let TOTAL_QTY = 0;
let orders = [];


function toCart(name, price, pathGambar) {

  let orderContainer = document.querySelector(".card-orders-container");

  if(orders.length == 0) {
    let order = {
      produk: name,
      price: price,
      qty: 1
    }

    orders.push(order);

    updateDomOrders(orderContainer, name, price, pathGambar)

  } else if(orders.length > 0) {

    // check if produk exist
    let isAdded = orders.find(order => order.produk === name);

    if(isAdded != undefined) {
      Swal.fire({
        title: 'Warning!',
        text: 'Produk Sudah ditambahkan',
        icon: 'warning',
        confirmButtonText: 'Tutup'
      })
    } else {

      let order = {
        produk: name,
        price: price,
        qty: 1
      }
  
      orders.push(order);
  
      updateDomOrders(orderContainer, name, price, pathGambar)
    }
  }

  // console.log(orders)
  
}


function updateDomOrders(parent, name, price, pathGambar) {

  let formatPrice = new Intl.NumberFormat("id-ID").format(price);

  parent.innerHTML += `
    <div class="card-order d-flex align-items-center flex-wrap" ondblclick='removeOrder(this, "${name}", event)'>
      <div class="card-order-image">
        <img src="assets/images/Brownies-8c400288.jpg" alt="${pathGambar}">
      </div>

      <div class="card-order-content d-flex flex-wrap flex-column justify-content-around align-items-start ps-2">
        <h5 class="fw-medium title-order p-0">${name}</h5>
        <p class="text-primary fw-bold price-order p-0">Rp. ${formatPrice}</p>
      </div>

      <div class="btn-counter mt-2 ms-auto d-flex flex-column align-items-center">
        <button class="btn btn-sm btn-primary fw-bold btn-plus" onClick='addQty(this, ${price}, "${name}")'>+</button>
        <span class="fw-bold" class="qty">1</span>
        <button class="btn btn-sm btn-primary fw-bold btn-minus" onClick='minQty(this, ${price}, "${name}")' disabled>-</button>
      </div>
    </div>`;

  parent.innerHTML += "";

  calculateSubtotal()
  calculateTotal()
}


function updateBill(subtotal = 0, totalOrder = 0) {

  let formatSubtotal = new Intl.NumberFormat("id-ID").format(subtotal);
  let formatTotal = new Intl.NumberFormat("id-ID").format(totalOrder);

  document.querySelector(".subtotal").textContent = "Rp. " + formatSubtotal;
  document.querySelector(".total").textContent = "Rp. " + formatTotal;

}


function calculateSubtotal() {

  // calculate subtotal
  let prices = orders.map(order => order.price);
  let subtotal = prices.reduce((acc, curVal) => acc + curVal, 0)
  SUBTOTAL_VALUE = subtotal;

  updateBill(SUBTOTAL_VALUE)
  console.log({prices, orders})

}


function calculateTotal() {

  // calculate qtys
  let allQty = orders.map(order => order.qty);
  let totalQtys = allQty.reduce((acc, curval) => acc + curval, 0);
  TOTAL_QTY = totalQtys;

  // calculate total
  let totalTaxs = TOTAL_QTY * 500;
  TOTAL_ORDER = SUBTOTAL_VALUE + totalTaxs;


  updateBill(SUBTOTAL_VALUE, TOTAL_ORDER)

}


function removeOrder(card, name, event) {
  // delete order from carts
  event.stopPropagation()
  if(event.target.tagName === "DIV") {
    orders = orders.filter(order => order.produk != name)
    card.remove()
    calculateSubtotal()
    calculateTotal()
  }
}


function addQty(button, price, name) {

  let qtyElem = button.nextElementSibling; // qty spesific produk
  let btnMinus = qtyElem.nextElementSibling; // btn minus spesific produk
  let basePrice = parseInt(price);
  let newPrice = 0;

  let qty = parseInt(qtyElem.textContent); 

  qty += 1;
  qtyElem.innerHTML = qty;

  if(qty > 1) {
    btnMinus.disabled = false;
  }

  // update qty orders
  orders.forEach((order => {
    if (order.produk === name) {
      order.qty = qty
    }
  }))

  // update price order
  newPrice = basePrice * qty;

  orders.forEach((order => {
    if (order.produk === name) {
      order.price = newPrice
    }
  }))

  let temp = new Intl.NumberFormat("id-ID").format(newPrice);
  calculateSubtotal();
  calculateTotal()
  qtyElem.parentElement.parentElement.querySelector(".price-order").textContent = "Rp. " + temp;

  // console.log({curentPrice, updatePrice, price});
  // console.log(orders)

}



function minQty(button, price, name) {

  let qtyElem = button.previousElementSibling; // qty spesific produk
  let basePrice = parseInt(price);
  let newPrice = 0;

  let qty = parseInt(qtyElem.textContent);

  // avoid minus qty
  if(qty === 2) {button.disabled = true;}

  qty -= 1;
  qtyElem.innerHTML = qty;


  // update qty orders
  orders.forEach((order => {
    if (order.produk === name) {
      order.qty = qty
    }
  }))


  // update price order
  orders.forEach((order => {
    if (order.produk === name) {
      newPrice = order.price - basePrice;
      order.price = newPrice;
    }
  }))

  let temp = new Intl.NumberFormat("id-ID").format(newPrice);
  calculateSubtotal();
  calculateTotal()
  qtyElem.parentElement.parentElement.querySelector(".price-order").textContent = "Rp. " + temp;
  
  // console.log({updatePrice})
}


// (function () {
//   Swal.fire({
//     title: 'Perhatian!',
//     text: 'Perhatian Aplikasi ini Teropimasi untuk Desktop/PC/Laptop',
//     icon: 'warning',
//     confirmButtonText: 'Mengerti'
//   })
// })();