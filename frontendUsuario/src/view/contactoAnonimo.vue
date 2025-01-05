<template>
  <nav class="navbar navbar-expand-lg bg-dark" data-bs-theme="dark">
    <div class="container-fluid">
      <img class="image" src="./media/logoalvolante.png" alt="Logo">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
              <a class="nav-link active" aria-current="page" @click.prevent="goToIntranet" href="#" style="margin-left: 20px;">Inicio</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" @click.prevent="verVehiculos" href="#" style="margin-left: 0px;">Ver Vehículos</a>
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

  <div class="main-content" style="margin-top: 80px;"> 
      <div class="contacto_titulo">Contáctenos</div>
      <!-- Sección de contacto -->
      <section class="contact-section">
        <div class="contact-content">
          <img src="./media/soporte.png" alt="Soporte" class="support-img" /> <!-- Imagen de soporte -->
          <div class="contact-box">
            <img src="./media/logoAV.png" alt="Logo AV" class="logo-img" /> <!-- Imagen del logo -->
            
            <!-- Información de contacto -->
            <h2>Información de contacto:</h2>
            <p>Teléfono: +5693452342</p>
            <p>Email: soporte@alvolante.com</p>
            <p>Dirección: Aeropuerto Santiago,Chile</p>
            <img src="./media/contactoIconos.png" alt="Iconos" class="iconos-img" /> <!-- Imagen de iconos -->
          </div>
        </div>
      </section>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

// Variables reactivas
const vehiculos = ref([]); // Lista para almacenar los vehículos
const router = useRouter();

// Métodos
const fetchVehiculos = async () => {
  try {
    const response = await axios.get(
      import.meta.env.VITE_BASE_URL + "api/vehiculos/all" // Ruta para obtener los autos
    );
    vehiculos.value = response.data;
  } catch (error) {
    console.error("Error al obtener los vehículos:", error);
    alert("No se pudo cargar la lista de vehículos");
  }
};

const registrarse = () => {
  router.push({ path: '/register', query: { register: true } }).then(() => {
    window.location.reload(); // Recarga completa la página
  });
};

const inicioSesion = () => {
  router.push("/login").then(() => {
    window.location.reload(); // Recarga completa la página
  });
};

const verVehiculos = () => {
  router.push('/vehiculosAnonimo').then(() => {
    window.location.reload(); // Recarga completa la página
  });
};

const goToIntranet = () => {
  router.push("/").then(() => {
    window.location.reload(); // Recarga completa la página
  });
};
</script>

<style>

body, html{
background: #8b8b8b;
color: #000;
font-family: Arial, sans-serif;
margin: 0;
padding: 0;
min-height: 100vh;
box-sizing: border-box;
}

body {
  margin: 0;
  font-family: "Arial", sans-serif;
  background: linear-gradient(135deg, #8b8b8b, #8b8b8b);
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
padding: 1rem;
}

.contacto_titulo {
  position: relative; /* Cambiar la posición a relativa */
  top: 10px; /* Ajustar la posición vertical hacia arriba */
  left: 14%;
  font-size: 2.5rem;
  font-weight: bold;
  color: #202020;
}

.contact-section {
display: flex;
justify-content: center; /* Centra el contenido horizontalmente */
align-items: center; /* Centra el contenido verticalmente */
margin-bottom: 40px; /* Espacio debajo de la sección de contacto */
}

.contact-content {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 12px;
  height: 700px;
}

.support-img {
  width: 700px; /* Ajustar el ancho de la imagen de soporte según sea necesario */
  position: relative; /* Cambiar la posición a relativa */ 
  left: -15%; /* Ajustar la posición vertical hacia arriba */
  margin-right: 12rem; /* Ajustar el margen derecho según sea necesario */ 
  margin-left: 30rem; /* Ajustar el margen izquierdo para mover la imagen a la izquierda */
  margin-top: -15rem; /* Ajustar el margen superior para mover la imagen hacia arriba */
}

.contact-box {
  background: rgba(255, 255, 255, 0.8); /* Fondo blanco semitransparente */
  position: relative; /* Cambiar la posición a relativa */ 
  left: -25%; /* Ajustar la posición vertical hacia arriba */
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  margin-right: 1rem;
  margin-left: 1rem;
  margin-top: -15rem; /* Ajustar el margen superior para mover la imagen hacia arriba */
  width: 500px;
}

.logo-img {
  width: 150px; /* Ajustar el ancho de la imagen del logo según sea necesario */
  margin-bottom: 1rem;
  position: relative; /* Cambiar la posición a relativa */ 
  top: -3px; /* Ajustar la posición vertical hacia arriba */
}

.iconos-img {
  width: 150px; /* Ajustar el ancho de la imagen del logo según sea necesario */
  margin-bottom: 1rem;
  position: relative; /* Cambiar la posición a relativa */ 
  top: -1px; /* Ajustar la posición vertical hacia arriba */
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

.vehiculos-container {
display: flex;
flex-wrap: wrap;
gap: 20px;
}

.vehiculo-card {
border: 1px solid #ccc;
border-radius: 8px;
padding: 15px;
width: 250px;
background-color: #ffffff;
box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
text-align: center;
}

.vehiculo-card img {
max-width: 100%;
border-radius: 8px;
margin-bottom: 10px;
}

.vehiculo-card p {
margin: 5px 0;
font-size: 14px;
}

.btn-custom {
background-color: #868686;
color: rgb(255, 255, 255);
border: none;
}

.btn-custom:hover {
background-color: #707070;
}

.nav-link:hover {
background-color: #707070;
}

</style>