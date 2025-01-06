<template>
  <div>
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
          <!-- Botón de selección -->
          <button @click="verDetallesVehiculo(vehiculo.idVehiculo)" class="select-button">Seleccionar</button> 
        </div>
      </div>
      </div>
      <p v-else>No hay vehículos disponibles.</p>
    </div>
  </div>
</template> 

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import Swal from 'sweetalert2';
import { useRouter } from 'vue-router';

const vehiculos = ref([]);
const router = useRouter();

const fetchVehiculosDispo = async () => {
  try {
    const token = localStorage.getItem("jwtToken");
    const response = await axios.get("http://localhost:8080/api/vehiculos/all", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    vehiculos.value = response.data;
  } catch (error) {
    console.error("Error al obtener los vehículos:", error);
    Swal.fire({
      title: "Error",
      text: "No se pudieron cargar los vehículos disponibles.",
      icon: "error",
    });
  }
};

const verDetallesVehiculo = (idVehiculo) => {
  router.push(`/seleccionVehiculoCliente/${idVehiculo}`);
};

onMounted(() => {
  fetchVehiculosDispo();
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

.container {
  display: flex;
  min-height: 100vh;
}

.image {
  max-width: 100%;
  margin-bottom: 20px;
  border-radius: 8px;
}

/* Contenedor principal */
.main-content {
  padding: 40px;
  flex: 1;
}

h1 {
  font-size: 24px;
  margin-bottom: 20px;
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