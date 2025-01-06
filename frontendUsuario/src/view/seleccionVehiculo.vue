<template>
  <!-- NavBar -->
  <nav class="navbar navbar-expand-lg bg-dark" data-bs-theme="dark">
    <div class="container-fluid p-0">
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
            <a class="nav-link" @click.prevent="contacto" href="#">Contáctanos</a>
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
  <div class="data-container" v-if="vehiculo">
    <div class="data-section">
      <img 
          v-if="vehiculo.fotoVehiculo"
          :src="'data:image/jpeg;base64,' + vehiculo.fotoVehiculo"
          alt="Foto del Vehículo"
          class="vehiculo-imagen"
        />
      <h1>{{ vehiculo.modelo }}</h1>
      <p><strong>Total Vehículo:</strong> {{ vehiculo.idVehiculo }}</p>
      <p><strong>Tipo:</strong> {{ vehiculo.tipo }}</p>
      <p><strong>Color:</strong> {{ vehiculo.color}}</p>
      <p><strong>Asientos:</strong> {{ vehiculo.capacidadPasajeros}}</p>
      <p><strong>Accesorios:</strong> {{ vehiculo.idVehiculo }}</p>
      <p><strong>Fechas:</strong> (información por completar)</p>
      <p><strong>Total Vehículo:</strong> {{ vehiculo.costo }}</p>
      <p><strong>Total:</strong> (vehículo + accesorios)</p>
      <button @click="seleccionarVehiculoAnonimo" class="select-button">  Arrendar  </button>
    </div>
  </div>
  <p v-else>Cargando datos del vehículo...</p>
</template>

<script>

import axios from "axios";

export default {
  props: ['idVehiculo'], // Recibe el ID del vehículo como una propiedad
  data() {
    return {
      vehiculo: null, // Para almacenar los detalles del vehículo
    };
  },
  methods: {
    async fetchVehiculo() {
  try {
    const response = await axios.get(
      `${import.meta.env.VITE_BASE_URL}api/vehiculos/obtenerVehiculoPorId/${this.idVehiculo}`
    );
    this.vehiculo = response.data;
    if (!this.vehiculo) {
      throw new Error("No se encontró el vehículo con el ID proporcionado.");
    }
  } catch (error) {
    console.error("Error al cargar los detalles del vehículo:", error);
    alert("No se pudo cargar la información del vehículo.");
  }
},

seleccionarVehiculoAnonimo() {
  Swal.fire({
    title: '¡Error!',
    text: 'Para seleccionar un vehículo, primero inicie sesión o regístrese.',
    icon: 'warning',
    showDenyButton: true,
    showCancelButton: true,
    confirmButtonText: 'Iniciar sesión',
    denyButtonText: 'Registrarse',
    cancelButtonText: 'Cancelar',
    customClass: {
      confirmButton: 'custom-confirm-button',
      denyButton: 'custom-deny-button',
      cancelButton: 'custom-cancel-button'
    }
    }).then ((result) => {
      if (result.isConfirmed) {
        // Redirigir a la página de inicio de sesión
        window.location.href = '/login'; // Redirige y recarga la página en '/login'
      } else if (result.isDenied) {
        // Redirigir a la página de registro
        window.location.href = '/register';
      }else if (result.isDismissed) {
        // No hacer nada si el usuario cancela la acción
        console.log('El usuario canceló la acción');
      }
    });

},
  },
  mounted() {
    this.fetchVehiculo(this.idVehiculo); // Carga los datos del vehículo al montar el componente
  },
};
</script>


<style>
body {
  background: #8b8b8b;
  color: #020000;
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
.container-fluid{
  display: flex;
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
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  position: fixed; /* Hace que la barra se quede fija en la parte superior */
  top: 0; /* La coloca exactamente en la parte superior */
  left: 0; /* Alinea al inicio del contenido horizontal */
  width: 100%; /* Se asegura de que ocupe todo el ancho */
  z-index: 1000;
  
}
.navbar { margin: 0; width: 100%; }


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
  text-align: center;
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
  margin-top: -10%;
  position: relative;
  margin-left: 5%;
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
  width: 10%;
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

.vehiculo-imagen {
  width: 25%; /* Ajustar el ancho de la imagen del perfil según sea necesario */ 
  display: block; /* Asegura que el margen automático funcione */ 
  margin-left: auto; 
  margin-right: auto;
}

</style>
