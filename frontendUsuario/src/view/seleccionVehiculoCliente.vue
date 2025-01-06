<template>
  <div class="data-container" v-if="vehiculo">
    <div class="data-section">
      <button @click="Volver" class="select-button2">Volver</button>
      <img 
          v-if="vehiculo.fotoVehiculo"
          :src="'data:image/jpeg;base64,' + vehiculo.fotoVehiculo"
          alt="Foto del Vehículo"
          class="vehiculo-imagen"
        />
        
      <h1>{{ vehiculo.modelo }}</h1>
      <p><strong>ID Vehículo:</strong> {{ vehiculo.idVehiculo }}</p>
      <p><strong>Tipo:</strong> {{ vehiculo.tipo }}</p>
      <p><strong>Color:</strong> {{ vehiculo.color }}</p>
      <p><strong>Asientos:</strong> {{ vehiculo.capacidadPasajeros }}</p>
      <p><strong>Accesorios:</strong> {{ vehiculo.accesorios }}</p>
      <p><strong>Fechas:</strong> (información por completar)</p>
      <p><strong>Total Vehículo:</strong> {{ vehiculo.costo }}</p>
      <p><strong>Total:</strong> (vehículo + accesorios)</p>
      <button @click="verDetallesVehiculoPago(vehiculo.idVehiculo)" class="select-button">Arrendar</button>
    </div>
  </div>
  <p v-else>Cargando datos del vehículo...</p>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter,useRoute } from 'vue-router';
import axios from 'axios';

const router = useRouter();
const route = useRoute();
const vehiculo = ref(null);


const fetchVehiculo = async () => {
  try {
    const idVehiculo = route.params.idVehiculo; // Obtén el idVehiculo de los parámetros de la ruta
    const token = localStorage.getItem("jwtToken"); // Obtén el token del almacenamiento local
    const response = await axios.get(`http://localhost:8080/api/vehiculos/obtenerVehiculoPorId/${idVehiculo}`, {
      headers: {
        Authorization: `Bearer ${token}`, // Añade el token al encabezado
      },
    });
    vehiculo.value = response.data; // Asigna el vehículo a la variable
  } catch (error) {
    console.error("Error al obtener el vehículo:", error);
    alert("No se pudo cargar la información del vehículo.");
  }
};

const guardarIdVehiculo = (idVehiculo) => {
  localStorage.setItem("idVehiculo", idVehiculo);
};

guardarIdVehiculo(route.params.idVehiculo);

const verDetallesVehiculoPago = (idVehiculo) => {
  router.push({ name: 'pago', params: { idVehiculo } });
};

const Volver = () => window.location.href = "/verAutosSegunCalendario";

onMounted(() => {
  fetchVehiculo(); // Llama a la función al cargar el componente
});
</script>
  
<style>
body {
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
  color: #020000;
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  display: flex;
  justify-content: center;
}

.container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.container-fluid {
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

.custom-text {
  font-size: 1.2rem;
  color: #999999 !important;
  margin-left: auto;
  margin-right: auto;
}

h1 {
  color: #202020;
  font-size: 24px;
  margin-bottom: 20px;
  text-align: center;
}

.btn-custom {
  background-color: #868686;
  color: #fff;
  border: none;
}

.btn-custom:hover {
  background-color: #707070;
}

.nav-link:hover {
  background-color: #707070;
}

.data-container {
  position: relative;
  margin-left: 0%;
  padding: 20%;
  width: 100%;
  display: flex;
  justify-content: center;
  background-color: transparent;
}

.data-title {
  font-size: 1.5rem;
  margin-bottom: 20px;
}

.data-section {
  background-color: #ffffff;
  padding: 10%;
  font-size: 1rem;
  border-radius: 2rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  margin-top: -30%;
}

.data-section h1 {
  margin-bottom: 3%;
  font-size: 1.5rem;
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



.data-section form button {
  padding: 10px;
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
  width: 10%;
  padding: 10px;
  border: none;
  position: absolute;
  align-items: center;
  border-radius: 4px;
  background-color: #28a745;
  color: rgb(255, 255, 255);
  cursor: pointer;
  margin-left: 4.5%;
  justify-content: center;
}

.select-button2 {
  margin-top: 0;
  width: 10%;
  padding: 10px;
  border: none;
  position: absolute;
  align-items: center;
  border-radius: 4px;
  background-color: #ffffff;
  color: #000;
  cursor: pointer;
  margin-left: -3%;
  margin-top: -4%;
  justify-content: center;
  border: 2px solid black;
}

.select-button:hover,
.select-button2:hover {
  background-color: #218838;
}

.vehiculo-imagen {
  width: 35%;
  display: block;
  margin-left: auto;
  margin-right: auto;
  border: 2px solid black;
}

.form-check {
  margin-bottom: 10px;
}
</style>