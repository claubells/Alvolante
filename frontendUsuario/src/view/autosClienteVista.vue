<template>
  <div class="container">
    <div class="main-content">
      <h1>Lista de Vehículos</h1>
      <div class="vehiculos-container" v-if="vehiculos.length">
        <div v-for="vehiculo in vehiculos" :key="vehiculo.idVehiculo" class="vehiculo-card">
          <img
            v-if="vehiculo.fotoVehiculo"
            :src="'data:image/jpeg;base64,' + vehiculo.fotoVehiculo"
            alt="Foto del Vehículo"
            class="vehiculo-imagen"
          />
          <p><strong>Patente:</strong> {{ vehiculo.patente }}</p>
          <p><strong>Modelo:</strong> {{ vehiculo.modelo }}</p>
          <p><strong>Marca:</strong> {{ vehiculo.marca }}</p>
          <p><strong>Año:</strong> {{ vehiculo.anio }}</p>
          
        </div>
      </div>
      <p v-else>No hay vehículos disponibles.</p>
    </div>
  </div>
</template>
  
<script setup>
// Imports
import { ref, onMounted } from 'vue';
import axios from "axios";

// Variables reactivas
const vehiculos = ref([]); // Lista para almacenar los vehículos

// Métodos
const fetchVehiculos = async () => {
  try {
    const response = await axios.get(
      import.meta.env.VITE_BASE_URL + "api/vehiculos/all" //ruta para obtener los autos
    );
    vehiculos.value = response.data;
  } catch (error) {
    console.error("Error al obtener los vehículos:", error);
    alert("No se pudo cargar la lista de vehículos");
  }
};

const cierreSesion = () => window.location.href = "/";

onMounted(() => {
  fetchVehiculos(); // Llama a la función al cargar el componente
});
</script>
  
<style>
/* Contenedor principal */
.container {
  display: flex;
  min-height: 100vh;
}

/* Estilos generales */
body {
  margin: 0;
  font-family: "Arial", sans-serif;
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
  color: #000;
  padding: 0;
}

.main-container {
padding: 1rem;
}

/* Barra de navegación */
.navbar {
display: flex;
justify-content: space-between;
align-items: center;
padding: 1rem 2rem;
background-color: #ff80ab;
color: #fff;
box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
position: fixed; /* Hace que la barra se quede fija en la parte superior */
top: 0; /* La coloca exactamente en la parte superior */
left: 0; /* Alinea al inicio del contenido horizontal */
width: 100%; /* Se asegura de que ocupe todo el ancho */
z-index: 1000;

}

.logo {
font-size: 1.5rem;
font-weight: bold;
}

.nav-links a {
color: #fff;
margin-left: 1.5rem;
text-decoration: none;
font-size: 1rem;
transition: color 0.3s ease;
}

.nav-links a:hover {
color: #ffd1dc;
}
.contacto_titulo {
position: relative; /* Cambiar la posición a relativa */ 
top: 100px; /* Ajustar la posición vertical hacia arriba */
left: 1%;
font-size: 2.5rem;
font-weight: bold;
color: #633434;
}

/* Sección de contacto */
.contact-section {
display: flex;
justify-content: flex-end;
align-items: center;
height: calc(100vh - 80px); /* Ajustar según la altura de la barra de navegación */
}

.contact-content {
display: flex;
align-items: center;
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
  background-color: #fff;
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
</style>
