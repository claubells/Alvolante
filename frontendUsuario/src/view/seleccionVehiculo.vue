<template>
  <!-- NavBar -->
  <nav class="navbar navbar-expand-lg bg-dark" data-bs-theme="dark">
    <div class="container-fluid">
      <img class="image" src="./media/logoalvolante.png" alt="Logo">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" @click.prevent="Intranet" href="#">Inicio</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" @click.prevent="contacto" href="#">Contactanos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled custom-text" aria-disabled="true">Usted está en la vista anónima</a>
          </li>
        </ul>
        <li class="d-flex" role="search">
          <button class="btn btn-custom me-2" @click.prevent="inicioSesion">Iniciar sesión</button>
          <button class="btn btn-custom" @click.prevent="registrarse">Registrarse</button>
        </li>
      </div>
    </div>
  </nav>

  <!-- Contenedor principal con el formulario -->
  <div class="data-container">
    <div class="data-section">
      <!--
      <img
          v-if="vehiculo.fotoVehiculo"
          :src="'data:image/jpeg;base64,' + vehiculo.fotoVehiculo"
          alt="Foto del Vehículo"
          class="vehiculo-imagen"
        /> -->
      <h1>MODELO</h1>
      <p><strong>Tipo:</strong> </p> <!--tipo de vehiculo-->
      <p><strong>Color:</strong> </p> <!-- Color -->
      <p><strong>Asientos:</strong> </p> <!-- asientos-->
      <p><strong>Accesorios:</strong> </p> <!-- accesorios -->
      <p><strong>FECHAS:</strong> </p> <!-- fechas - cantidada de dias -->
      <p><strong>Total Vehículo:</strong> </p> <!-- precio vehiculo-->
      <p><strong>Total:</strong> {{ vehicle.tipo }}</p> <!-- precio vehiculo+accesorios-->
      <button @click="seleccionarVehiculo" class="select-button">Seleccionar</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import vehiculosAnonimo from "./vehiculosAnonimo.vue";
export default {
  data() {
    return {
      vehiculos: [], // Lista para almacenar los vehículos
    };
  },
  data() {
    return {
      vehicle: {
        codigoACRISS: "",
        patente: "",
        numeroChasis: "",
        modelo: "",
        marca: "",
        kilometraje: "",
        anio: "",
        costo: "",
        tipo: "",
        color: "",
        capacidadPasajeros: "",
        fechaUltimoMantenimiento: "",
        estadoVehiculo: "",
        fotoVehiculo: null, // Almacenará la imagen en Base64  
      },
    };
  },
  methods: {
    // para la foto
    onFileChange(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.vehicle.fotoVehiculo = e.target.result.split(",")[1]; // Solo el contenido Base64
        };
        reader.readAsDataURL(file);
      }
    },
    // enviar datos al backend
    async submitForm() {
      try {
        // agrupa los datos y los manda al backend
        const response = await axios.post(
          import.meta.env.VITE_BASE_URL + 'api/vehiculos/crear-vehiculo',
          this.vehicle)
          console.log(response.data) // respuesta del backend
          //imprimir alertas enviadas por backend
        if (response.data == 4) {
          alert("Patente repetida");
        }
        if (response.data == 2) {
          alert("Numero de chasis repetido");
        } 
        if (response.data == 0) {
          alert("Vehículo ingresado con éxito");
        }

        // error de la bd 
        } catch (error) {
          console.error("Error:", error);
          alert("No se pudo generar conexión con el servidor");
        }
    },
    Intranet() {
      // Lógica para navegar al inicio
    },
    contacto() {
      // Lógica para navegar a la página de contacto
    },
    inicioSesion() {
      // Lógica para manejar el inicio de sesión
    },
    registrarse() {
      // Lógica para manejar el registro
    }
  }
};
</script>

<style>
body {
  background: #8b8b8b;
  color: #000;
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  justify-content: center;
}

.container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.image {
  max-height: 60px;
  max-width: 55px;
  margin-left: -20px;
  margin-top: auto;
  

  
}

.main-content {
  margin-top: 80px; 
  padding: 40px;
  flex: 1;
}

.nav-link {
color: #ffffff !important; 
}

.navbar-brand {
color: #ffffff !important; 
}

.navbar-text {
color: #ffffff !important; 
}

.custom-text {
font-size: 1.2rem; 
color: #999999 !important; 
margin-left: 500px;
 
}
h1 {
  color: #202020;
  font-size: 24px;
  margin-bottom: 20px;
}
.btn-custom {
background-color: #868686; /* Color de fondo personalizado */
color: rgb(255, 255, 255); 
border: none; 
}

.btn-custom:hover {
background-color: #707070; /* Color al pasar el mouse por encima */
}

.nav-link:hover {
background-color: #707070;
}

/* Estilos para el Contenedor Principal y el Formulario */
.data-container {
  padding: 20%;
  height: 90%;
  width: 90%;
  justify-content: center;
  
}

.data-title {
  font-size: 150%;
  margin-bottom: 20px;
}

.data-section {
  background-color: #fff;
  padding: 10%;
  font-size: 100%;
  border-radius: 4rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.data-section h1 {
  margin-bottom: 3%;
  font-size: 150%;
  font-weight: bold;
}

.data-section form {
  display: flex;
  flex-direction: column;
}

.data-section form label {
  margin-top: 10px;
  font-weight: bold;
}

.data-section form input {
  padding: 10px;
  border: 1px solid #ced4da;
  border-radius: 4px;
  margin-bottom: 10px;
}

.data-section form button {
  padding: 10%;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  margin-top: 10px;
}

.data-section form button:hover {
  background-color: #0056b3;
}

.select-button {
  margin-top: 0%;
  padding: 10px;
  border: none;
  position: absolute;
  align-items: center;
  border-radius: 4px;
  background-color: #28a745;
  color: white;
  cursor: pointer;
  margin-left: 16%;
  justify-content: center;
}

.edit-button:hover {
  background-color: #218838;
}
</style>
