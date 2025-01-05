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
        <h2>Trabajador</h2>
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
            <!-- Datos del vehículo -->
            <p><strong>Patente:</strong> {{ vehiculo.patente }}</p>
            <p><strong>Modelo:</strong> {{ vehiculo.modelo }}</p>
            <p><strong>Marca:</strong> {{ vehiculo.marca }}</p>
            <p><strong>Año:</strong> {{ vehiculo.anio }}</p>

            <!-- Botón de selección -->
            <button @click="verDetallesVehiculoTrabajador(vehiculo.idVehiculo)" class="select-button">Seleccionar</button> 
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

const fetchVehiculos = async () => { // funcion para obtener los vehiculos
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

const verDetallesVehiculoTrabajador = (idVehiculo) => {
  router.push({ name: 'seleccionVehiculoCliente', params: { idVehiculo } });
};

onMounted(() => {
  fetchVehiculos(); // Llamamos a la API al cargar
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

/* Iconos */
.nav-icon {
  margin-right: 5px;
  font-size: 1.1rem;
  vertical-align: middle;
  }
  .nav-icon.large {
    font-size: 1.3rem;
  }
</style>