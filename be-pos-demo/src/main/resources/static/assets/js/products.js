const BASE_URL = "http://localhost:3000/api/";

function editProduk(element, id) {

  const options = {
    url: BASE_URL + `products/${id}`,
    type: "GET",
    contentType: "application/json",
    success: function(res, stat) {
      updateForm(res)
    }
  }

  $.ajax(options);
}


function updateForm(response) {

  const nameProduk = document.getElementById("produk")
  const image = document.getElementById("existimage")
  const desc = document.getElementById("desc")
  const category = document.getElementById("category")
  const stok = document.getElementById("stok")
  const title = document.querySelector(".title-form")
  const btnModify = document.querySelector(".btn-modify")
  const inputHidden = document.getElementById("idProduk")

  inputHidden.value = response.idProduk;

  // get options
  let categoryOptions = category.options; // htmlCollection

  title.textContent = "Update Produk";
  nameProduk.value = response.name;
  image.classList.remove("d-none")
  image.innerHTML = response.pathGambar;
  desc.value = response.description;

  Array.from(categoryOptions).forEach((cat, i) => {
    if(cat.value === response.category.name) {
      cat.selected = true;
    }
  })

  stok.value = response.stok;
  btnModify.textContent = "Simpan";


  // adding btn cancel
  let btnCancel = `<button type="button" class="btn btn-outline-danger fw-medium w-100 btn-cancel" onClick="cancelUpdate(this)">Batal</button>`;

  btnModify.insertAdjacentHTML("afterend", btnCancel)


}


function cancelUpdate(element) {
  const nameProduk = document.getElementById("produk")
  const image = document.getElementById("existimage")
  const desc = document.getElementById("desc")
  const category = document.getElementById("category")
  const stok = document.getElementById("stok")
  const title = document.querySelector(".title-form")
  const btnModify = document.querySelector(".btn-modify")
  const inputHidden = document.getElementById("idProduk")

  nameProduk.value = "";
  image.textContent = ""
  image.classList.add("d-none")
  desc.value = ""
  category.options[0].selected = true;
  stok.value = ""
  title.textContent = "Tambah Produk"
  inputHidden.value = ""
  btnModify.textContent = "Tambah"

  element.remove()
}


function submitProduk() {

  const btnModify = document.querySelector(".btn-modify")
  const form = document.getElementById("form-produk")

  if(btnModify.textContent == "Simpan") {

    Swal.fire({
      title: 'Update Produk!',
      text: 'Apakah anda yakin?',
      icon: 'info',
      confirmButtonText: "Iya",
      showCancelButton: true,
      cancelButtonText: "Tidak",
      reverseButtons: true,
    }).then((result) => {
      if(result.isConfirmed === true) {
        form.setAttribute("action", "/put-produk")
        form.submit();
        return;
      }
    })

  }

  if(btnModify.textContent == "Tambah") {
    form.setAttribute("action", "/post-produk")
    if(validate()) {
      form.submit();
    }
  }


}


function deleteProduk(id) {

  let conf = confirm("Yakin untuk hapus Produk?");

  if(conf) {
    const options = {
      url: BASE_URL + `products/${id}`,
      type: "DELETE",
      contentType: "application/json",
      success: function(res, stat) {
  
        Swal.fire({
          title: 'Berhasil Hapus Produk',
          icon: 'success',
          confirmButtonText: "Tutup",
        })
  
        setTimeout(() => {
          window.location.reload(true)
        }, 2000)
  
      }
    }
  
    $.ajax(options);
  }

}

function validate() {

  const nameProduk = document.getElementById("produk")
  const image = document.getElementById("image")
  const desc = document.getElementById("desc")
  const category = document.getElementById("category")
  const stok = document.getElementById("stok")

  let errors = [];
  let errorStrings = ""

  if(nameProduk.value === "") {
    errors.push("Nama Produk tidak boleh kosong!")
  }

  if(image.value === "" || image == null) {
    errors.push("Gambar tidak boleh kosong")
  }

  if(desc.value === "") {
    errors.push("Description tidak boleh kosong")
  }

  if(category.value == "none") {
    errors.push("category harus dipilih")
  }

  if(stok.value == "" || stok.value == 0) {
    errors.push("Stok minimal 1")
  }


  if(errors.length == 0) {return true}

  errors.forEach((err, i) => {
    errorStrings+= `<p class="text-danger mb-1">${err}</p>`
  })

  Swal.fire({
    titleText: 'Gagal Tambah!',
    html: errorStrings,
    icon: 'error',
    confirmButtonText: "Tutup",
  })

  return false;
}