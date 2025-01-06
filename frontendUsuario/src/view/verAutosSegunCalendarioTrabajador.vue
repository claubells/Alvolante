<template>
  <div>
      <!-- Barra lateral -->
      <div class="sidebar">
      <img class="image" src="./media/logoalvolante.png" alt="Logo" />
      <ul>
        <li><a href="#" @click="toInicio"><i class="bi bi-house-door nav-icon"></i> Inicio</a></li>
      <li><a href="#" @click="cierreSesion"><i class="bi bi-box-arrow-right nav-icon"></i> Cerrar sesión</a></li>
      </ul>
    </div>

    <!-- Contenido principal -->
    <div class="main-content">
      <h2>Lista de Vehículos</h2>
      <!-- Contenedor de vehículos -->
      <div class="vehiculos-container" v-if="vehiculos.length">
        <div v-for="vehiculo in vehiculos" :key="vehiculo.idVehiculo" class="vehiculo-card">
          <img
            v-if="vehiculo.fotoVehiculo"
            :src="'data:image/jpeg;base64,' + vehiculo.fotoVehiculo"
            alt="Foto del Vehículo"
            class="vehiculo-imagen"
          />
          <!-- Datos del vehículo -->
          <div class="vehiculo-info">
          <h2>{{ vehiculo.modelo }}</h2>
          <div class="vehiculo-detalles">
              <p><i class="bi bi-people"></i><strong>Pasajeros:</strong> {{ vehiculo.capacidadPasajeros }}</p>
              <p><i class="bi bi-fuel-pump"></i><strong>Combustible:</strong> {{ vehiculo.combustible }}</p>
              <p><i class="bi bi-gear"></i><strong>Transmisión:</strong> {{ vehiculo.tipo }}</p>
            </div>
          <!-- Botón de selección -->
          <button @click="verDetallesVehiculoTrabajador(vehiculo.idVehiculo)" class="select-button">Seleccionar</button> 
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
import axios from 'axios';
import { useRouter } from 'vue-router'; 

// Variables reactivas
const router = useRouter();
const vehiculos = ref([]);

// Métodos
const cierreSesion = () => window.location.href = "/";
const toInicio = () => window.location.href = "/trabajador";

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

const verDetallesVehiculoTrabajador = (idVehiculo) => {
  router.push({ name: 'seleccionVehiculoAdmin', params: { idVehiculo } });
};

onMounted(() => {
  fetchVehiculosDispo(); // Llamamos a la API al cargar
});
</script>


<style>
/* Estilo general */
body {
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
  color: #000;
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}


/* Barra lateral */
.sidebar {
  width: 250px;
  background-color: #ff80ab;
  padding: 15px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  position: fixed;
  height: 100%;
  margin-left: 0%;
}

.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar ul li {
  margin-bottom: 12px;
}

.sidebar ul li a {
  text-decoration: none;
  color: white;
  font-size: 15px;
  display: block;
  padding: 7px;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.sidebar ul li a:hover {
  background-color: #ff4081;
}

.image {
  max-width: 100%;
  margin-bottom: 10px;
  border-radius: 8px;
}

/* Contenedor principal */
.main-content {
  margin-left: 270px; 
  padding: 20px;
}

h1 {
  font-size: 22px;
  margin-bottom: 10px;
}

/* Vehículos */
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
  max-width: 60%;
  height: auto;
  border-radius: 12px;
  margin-bottom: 15px;
}

.vehiculo-info {
  text-align: center;
}

.vehiculo-card p {
  margin: 5px 0;
  font-size: 14px;
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

/* Iconos */
.nav-icon {
  margin-right: 5px;
  font-size: 1.1rem;
  vertical-align: middle;
  }
  .nav-icon.large {
    font-size: 1.3rem;
  }

  .select-button {
  background-color: #ff80ab;
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  padding: 12px 24px;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease, background-color 0.3s ease;
}
</style>