<template>
  <div class="container">
    <!-- Barra lateral -->
    <div class="sidebar">
      <ul>
        <img class="image" src="./media/logoalvolante.png">
        <li><a href="#" @click="toInicio">Inicio</a></li>
        <li><a class="botonSubir" href="#" @click="AgregarVehiculoAdmin">Subir Vehículo</a></li>
        <li><a href="#" @click="cierreSesion">Cerrar sesión</a></li>
      </ul>
    </div>

    <!-- Contenedor principal -->
    <div class="main-content">
      <h1>Lista de Vehículos</h1>

      <!-- Contenedor de vehículos -->
      <div class="vehiculos-container" v-if="vehiculos.length">
        <div v-for="vehiculo in vehiculos" :key="vehiculo.idVehiculo" class="vehiculo-card">
          <img
            v-if="vehiculo.fotoVehiculo"
            :src="'data:image/jpeg;base64,' + vehiculo.fotoVehiculo"
            alt="Foto del Vehículo"
            class="vehiculo-imagen"
          />
          <div class="vehiculo-info">
            <h2>{{ vehiculo.modelo }}</h2>
            <div class="vehiculo-detalles">
              <p><i class="bi bi-people"></i><strong>Pasajeros:</strong> {{ vehiculo.capacidadPasajeros }}</p>
              <p><i class="bi bi-fuel-pump"></i><strong>Combustible:</strong> {{ vehiculo.combustible }}</p>
              <p><i class="bi bi-gear"></i><strong>Transmisión:</strong> {{ vehiculo.tipo }}</p>
            </div>
          </div>
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
import Swal from 'sweetalert2';

// Variables reactivas
const vehiculos = ref([]); // Lista para almacenar los vehículos

// Métodos
const fetchVehiculosDispo = async () => {
  try {
    const token = localStorage.getItem("jwtToken"); // Obtén el token del almacenamiento local
    const response = await axios.get("http://localhost:8080/api/vehiculos/all", {
      headers: {
        Authorization: `Bearer ${token}`, // Añade el token al encabezado
      },
    });
    vehiculos.value = response.data; // Asigna los vehículos a la variable
  } catch (error) {
    console.error("Error al obtener los vehículos:", error);
    Swal.fire({
      title: "Error",
      text: "No se pudieron cargar los vehículos disponibles.",
      icon: "error",
    });
  }
};

const cierreSesion = () => {
  window.location.href = "/"; // Redirecciona a la vista principal
};

const AgregarVehiculoAdmin = () => {
  window.location.href = "/agregarvehiculo"; 
};

const toInicio = () => {
  window.location.href = "/admin"; 
};

const VerVehiculos = () => {
  window.location.href = "/vervehiculos"; 
};

onMounted(() => {
  fetchVehiculosDispo(); // Llama a la función al cargar el componente
});
</script>

<style>
/* Contenedor principal */
body {
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
  color: #000;
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}

.container {
  display: flex;
  min-height: 100vh;
}

/* Barra lateral */
.sidebar {
  width: 250px;
  background-color: #ff80ab;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  position: fixed;
  height: 100%;
}

.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar ul li {
  margin-bottom: 20px;
}

.sidebar ul li a {
  text-decoration: none;
  color: white;
  font-size: 16px;
  display: block;
  padding: 10px;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.sidebar ul li a:hover {
  background-color: #ff4081;
}

.image {
  max-width: 100%;
  margin-bottom: 20px;
  border-radius: 8px;
}

/* Contenedor principal */
.main-content {
  margin-left: 270px;
  padding: 40px;
  flex: 1;
}

h1 {
  font-size: 28px;
  margin-bottom: 30px;
}

/* Contenedor de vehículos */
.vehiculos-container {
  display: flex;
  flex-wrap: wrap;
  gap: 30px;
  justify-content: center;
}

.vehiculo-card {
  border: 1px solid #ccc;
  border-radius: 12px;
  padding: 20px;
  width: 730px;
  background-color: #fff;
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s;
}

.vehiculo-card img {
  max-width: 100%;
  height: auto;
  border-radius: 12px;
  margin-bottom: 15px;
}

.vehiculo-info {
  text-align: center;
}

.vehiculo-detalles {
  display: flex;
  gap: 10px;
  margin-top: 15px;
  justify-content: center;
}

.vehiculo-detalles p {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  font-size: 16px;
  background-color: #f9f9f9; 
  padding: 10px 15px; 
  border-radius: 10px; 
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
  border: 1px solid #ddd; 
}

.vehiculo-detalles i {
  font-size: 24px; 
  color: #ff80ab; 
}

</style>

