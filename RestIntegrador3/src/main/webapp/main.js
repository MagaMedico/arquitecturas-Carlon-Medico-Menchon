 "use strict";

document.getElementById("btnInsertCS").addEventListener("click", () => {
	let student = document.getElementById("studentId").value;
	let career = document.getElementById("carrerId").value;
	
	let item = {
	      "studentId": student,
		  "careerId": career
	};
	
	let url = "http://localhost:8080/RestIntegrador3/rest/careerStudent";
	let contenedor = document.getElementById("contenedor");
	
	fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(item),
    })
      .then((r) => {
        if (!r.ok) {
          contenedor.innerHTML = "Couldn't send data";
        }
      })
      .then(() => {
        contenedor.innerHTML = "Se inserto el usuario " + student + " en la carrera " + career;
      })
      .catch((e) => {
        console.log(e);
      });
});

document.getElementById("btnInsertStudent").addEventListener("click", () => {
	let dni = document.getElementById("dni").value;
	let name = document.getElementById("name").value;
	let lastName = document.getElementById("lastName").value;
	let age = document.getElementById("age").value;
	let gender = document.getElementById("genderS").value;
	let city = document.getElementById("cityS").value;
	let lu = document.getElementById("lu").value;
	
	let item = {
	      "dni": dni,
		  "name": name,
		  "lastName": lastName,
		  "age": age,
	      "gender": gender,
		  "city": city,
		  "lu": lu
	};
	
	let url = "http://localhost:8080/RestIntegrador3/rest/students";
	let contenedor = document.getElementById("contenedorInsert");
	
	fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(item),
    })
      .then((r) => {
        if (!r.ok) {
          contenedor.innerHTML = "Couldn't send data";
        }
      })
      .then(() => {
        contenedor.innerHTML = "Se inserto el usuario";
      })
      .catch((e) => {
        console.log(e);
      });
});

document.getElementById("btnFindStudent").addEventListener("click", () => {
	let lu = document.getElementById("LU").value;

	let url = "http://localhost:8080/RestIntegrador3/rest/students/LU-";
	let contenedor = document.getElementById("result");
	
	fetch(url + lu,{
		method: "GET",
        mode: 'cors'
	})
      .then((r) => {
	console.log(r);
        if (!r.ok) {
          contenedor.innerHTML = "Couldn't send data";
        }
		return r.json();
      })
      .then((json) => {
          contenedor.innerHTML = '';
		  console.log(json);
          contenedor.innerHTML = JSON.stringify(json);
      })
      .catch((e) => {
        console.log(e);
      });
});

document.getElementById("btnFindGender").addEventListener("click", () => {
	let gender = document.getElementById("gender").value;

	let url = "http://localhost:8080/RestIntegrador3/rest/students/";
	let contenedor = document.getElementById("resultGender");
	
	fetch(url + gender,{
		method: "GET",
        mode: 'cors'
	})
      .then((r) => {
	console.log(r);
        if (!r.ok) {
          contenedor.innerHTML = "Couldn't send data";
        }
		return r.json();
      })
      .then((json) => {
          contenedor.innerHTML = '';
		  console.log(json);
          contenedor.innerHTML = JSON.stringify(json);
      })
      .catch((e) => {
        console.log(e);
      });
});

document.getElementById("btnCareerCity").addEventListener("click", () => {
	let career = document.getElementById("career").value;
	let city = document.getElementById("city").value;
	
	let url = "http://localhost:8080/RestIntegrador3/rest/careerStudent/";
	let contenedor = document.getElementById("resultCareerCity");
	
	fetch(url + career + "/" + city,{
		method: "GET",
        mode: 'cors'
	})
      .then((r) => {
	console.log(r);
        if (!r.ok) {
          contenedor.innerHTML = "Couldn't send data";
        }
		return r.json();
      })
      .then((json) => {
          contenedor.innerHTML = '';
		  console.log(json);
          contenedor.innerHTML = JSON.stringify(json);
      })
      .catch((e) => {
        console.log(e);
      });
});