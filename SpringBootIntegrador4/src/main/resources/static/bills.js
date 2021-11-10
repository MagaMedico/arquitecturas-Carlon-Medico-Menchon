"use strict"

function inicio() {
	mostrarTabla();
	document.getElementById("btnInsertBillProduct").addEventListener("click", ()=>{
		let date = document.getElementById("date").value;
		let quantity = document.getElementById("quantity").value;
		let bill_bill_id = document.getElementById("bill_bill_id").value;
		let product_id = document.getElementById("product_id").value;
		
		let item = {
		      "date": date,	  
			  "quantity": quantity,
			  "bill.billId": bill_bill_id,
			  "product.id": product_id
		};
		
		let url = "http://localhost:8080/bills";
		let contenedor = document.getElementById("contenedorInsert");
		
		fetch(url, {
	      method: "POST",
	      headers: { "Content-Type": "application/json" },
	      body: JSON.stringify(item),
	    })
	      .then((r) => {
	        if (!r.ok) {
	          contenedor.innerHTML = "No se ha podido insertar la factura, revise los datos ingresados e intente nuevamente";
	        }
	      })
	      .then(() => {
	        contenedor.innerHTML = "Se ha insertado la factura con exito";
	        document.getElementById("date").value = "";
	        document.getElementById("quantity").value = "";
	        document.getElementById("bill_bill_id").value = "";
	        document.getElementById("product_id").value = "";
	        mostrarTabla();
	      })
	      .catch((e) => {
	        console.log(e);
	      });
	 });
}

function mostrarTabla() {
	let url = "http://localhost:8080/bills";
	let tbody = document.getElementById("body");
	let contenedor = document.getElementById("contenedorInsert");
	
    fetch(url, {
        method: "GET",
        mode: "cors",
    }).then(respuesta => {
        if (respuesta.ok) {
            respuesta.json().then(json => {
                tbody.innerHTML = " ";
	console.log(json)
	;                for (let data of json) {
                    let tr = document.createElement("tr");
                    tbody.appendChild(tr);
                    let td1 = document.createElement("td");
                    let td2 = document.createElement("td");
                    let td3 = document.createElement("td");
                    let td4 = document.createElement("td");
                    let td5 = document.createElement("td");
                    let td6 = document.createElement("td");
                    let td7 = document.createElement("td");
                    let btnBorrar = document.createElement("button");
                    btnBorrar.innerHTML = "borrar";
                    let btnEditar = document.createElement("button");
                    btnEditar.innerHTML = "editar";
                    let id = data.id;
                    btnBorrar.addEventListener("click", () => borrar(id));
                    btnEditar.addEventListener("click", () => editar(id));
                    td1.innerText = id;
                    td2.innerText = data.date;
                    td3.innerText = data.quantity;
                    td4.innerText = data.bill.billId;
                    td5.innerText = data.product.id;
                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    tr.appendChild(td3);
                    tr.appendChild(td4);
                    tr.appendChild(td5);
                    tr.appendChild(td6);
                    tr.appendChild(td7);
                    td6.appendChild(btnBorrar)
                    td7.appendChild(btnEditar);
                }
            })
        } else {
            contenedor.innerHTML = "<h1>Error - Failed URL!</h1>";
        }
    }).catch(error => {
        console.log(error);
        contenedor.innerHTML = "<h1>Error - Conection Failed!</h1>";
    });
}

function borrar(id){
	let url = "http://localhost:8080/bills";
	let contenedor = document.getElementById("contenedorBorrar");
	
	fetch(url + "/" + id, {
        method: "DELETE",
        mode: 'cors',
    }).then(respuesta => {
        if (respuesta.ok) {
            contenedor.innerHTML = "Se ha borrado correctamente";
            mostrarTabla();
        } else {
            contenedor.innerHTML = "No puede borrar una factura asociada a un cliente";
        }
    }).catch(error => {
        console.log(error);
        contenedor.innerHTML = "<h1>Error - Conection Failed!</h1>";
	});
}

function editar(id){

	let form = document.getElementById("editar");
	let label = document.createElement("label");
	label.innerHTML = "Editar una factura: ";
	
	let br1 = document.createElement("br");
	let input1 = document.createElement("input");
	input1.type = "text";
	input1.value = id;
	input1.id = "idI";
	input1.readOnly = "true";
	
	let br2 = document.createElement("br");
	let input2 = document.createElement("input");
	input2.type = "date";
	input2.placeholder = "Inserte una nueva fecha";
	input2.id = "dateI";
	
	let br3 = document.createElement("br");
	let input3 = document.createElement("input");
	input3.type = "number";
	input3.placeholder = "Inserte nueva cantidad";
	input3.id = "quantityI";
	
	let br4 = document.createElement("br");
	let input4 = document.createElement("input");
	input4.type = "number";
	input4.placeholder = "Inserte la factura cliente asociada";
	input4.id = "bill_bill_idI";
	
	let br5 = document.createElement("br");
	let input5 = document.createElement("input");
	input5.type = "number";
	input5.placeholder = "Inserte el producto asociado";
	input5.id = "product_idI";
	
	let br6 = document.createElement("br");
	let boton = document.createElement("input");
	boton.type = "button";
	boton.value = "EDITAR";
	boton.id = "enviar";
	
	form.appendChild(label);
	form.appendChild(br1);
	form.appendChild(input1);
	form.appendChild(br2);
	form.appendChild(input2);
	form.appendChild(br3);
	form.appendChild(input3);
	form.appendChild(br4);
	form.appendChild(input4);
	form.appendChild(br5);
	form.appendChild(input5);
	form.appendChild(br6);
	form.appendChild(boton);
	
	document.getElementById("enviar").addEventListener("click", () => {
		let contenedor = document.getElementById("contenedorEditar");
		
		let idBillProduct = id;
		let date = document.getElementById("dateI").value;
		let quantity = document.getElementById("quantityI").value;
		let bill_bill_id = document.getElementById("bill_bill_idI").value;
		let product_id = document.getElementById("product_idI").value;
	
		let b = getBill(bill_bill_id);
		let p = getProduct(product_id);
		console.log(b);
		console.log(p);
		let item = {
		      "id": idBillProduct,	  
			  "date": date,
			  "quantity": quantity,
			  "bill": b,
			  "product": p
		};
		let url = "http://localhost:8080/bills";
		
		fetch(url + "/" + idBillProduct, {
	      method: "PUT",
	      headers: { "Content-Type": "application/json" },
	      body: JSON.stringify(item),
	    })
	      .then((r) => {
	        if (!r.ok) {
	          contenedor.innerHTML = "No se ha podido editar la factura, revise los datos ingresados e intente nuevamente";
	        }
	      })
	      .then(() => {
	        contenedor.innerHTML = "Se ha editado la factura con exito";
	        form.innerHTML = "";
	        mostrarTabla();
	      })
	      .catch((e) => {
	        console.log(e);
	      });
	});
}

function getBill(id){
	let url = "http://localhost:8080/b";
	fetch(url + "/" + id, {
        method: "GET",
        mode: 'cors',
    }).then(respuesta => {
        if (respuesta.ok) {
            return respuesta;
        } else {
            console.log("No existe una factura con ese id");
						return null;
        }
    }).catch(error => {
        console.log(error);
	});
}
function getProduct(id){
	let url = "http://localhost:8080/products";
	fetch(url + "/" + id, {
        method: "GET",
        mode: 'cors',
    }).then(respuesta => {
        if (respuesta.ok) {
            return respuesta;
        } else {
            console.log("No existe un producto con ese id");
						return null;
        }
    }).catch(error => {
        console.log(error);
	});
}
document.addEventListener("DOMContentLoaded", inicio());