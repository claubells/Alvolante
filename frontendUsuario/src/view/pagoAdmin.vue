<template>
  <main class="main-container">
    <div class="container mt-5">
      <div class="content-wrapper">
        <div class="container-custom" v-if="vehiculo">
          <button @click="Volver" class="select-button2">Volver</button>
          <h2><i class="bi bi-cart"></i> Compra </h2>
          <h3>Resumen</h3>
          <div class="vehiculo-resumen">
            <img v-if="vehiculo.fotoVehiculo" :src="'data:image/jpeg;base64,' + vehiculo.fotoVehiculo" alt="Foto del Vehículo" class="vehiculo-imagen" />
            <div class="vehiculo-datos">
              <p>{{ vehiculo.modelo }}</p>
              <p>{{ vehiculo.tipo }}</p>
            </div>
            <div class="vehiculo-precio ml-auto">
              <h4>$ {{ vehiculo.costo }}</h4>
            </div>
          </div>
        </div>
        <p v-else>Cargando datos del vehículo...</p>
        <div class="container-custom-e mt-3">
          <h2><i class="bi bi-credit-card"></i> Estado Pago</h2>
          <button type="button" @click="verBoleta" class="action-btn mt-3" style="margin-right: 10px; width: 100%;color: #ffffff;">Aceptado</button>
          <button type="button" @click="paymentError" class="action-btn mt-3" style="margin-right: 10px; width: 100%;color: #ffffff;">Denegado</button>
        </div>
      </div>
    </div>
  </main>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';
import vehicleServicesInstance from '../services/vehicleService';
import VerBoleta from './verBoleta.vue';
import Swal from 'sweetalert2';

const router = useRouter();
const route = useRoute();
const vehiculo = ref(null);
const reserva = ref({
  fechaInicioReserva: "2024-12-16",
  fechaFinReserva: "2024-12-20",
  quiereExtras: false,
  estadoReserva: 1,
  horaReserva: 0,
  costoReserva: 0,
});

const idUsuario = ref(localStorage.getItem("idUsuario"));

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
    reserva.value.costoReserva = vehiculo.value.costo; // Asigna el costo del vehículo a la reserva
  } catch (error) {
    console.error("Error al obtener el vehículo:", error);
    alert("No se pudo cargar la información del vehículo.");
  }
};

const enviarReserva = async () => {
  try {
    const response2 = await vehicleServicesInstance.enviarReserva(reserva.value ,idUsuario.value, route.params.idVehiculo);
    reserva.value = response2.data; // Asigna la reserva a la variable
    reserva.value.idUsuario = idUsuario.value; // Asigna el idUsuario a la reserva
    reserva.idVehiculo = vehiculo.value.idVehiculo; // Asigna el idVehiculo a la reserva
  } catch (error) {
    console.error("Error:", error);
  }
};

const Volver = () => {
  router.push('/trabajador');
  
};
const verBoleta = () => {
  router.push('/rellenarDatosClienteAdmin');
};
const paymentError = async () => {
  await Swal.fire({
        title: '¡Error!',
        text: 'No se pudo procesar el Pago\n',
        icon: 'warning',
        confirmButtonText: 'OK',
        customClass: {
        confirmButton: 'custom-confirm-button'
      }});
  router.push('/trabajador');
};

onMounted(() => {
  fetchVehiculo(); // Llama a la función al cargar el componente
});
</script>


<style>
/* Estilos generales */
body {
  margin: 0;
  font-family: "Arial", sans-serif;
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
}

.main-container {
  padding-top: 0px; /* Espacio para el navbar fijo */
  display: flex;
  justify-content: center; /* Center the main container horizontally */
}

.content-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center; /* Center the containers horizontally */
  width: 100%;
}

/* Contenedor personalizado */
.container-custom, .container-custom-e {
  background-color: #ffffff; /* Fondo blanco */
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  width: 80%; /* Adjust the width as needed */
}

/* Estilos para Resumen de Compra */
.vehiculo-resumen {
  display: flex;
  align-items: center;
}

.vehiculo-imagen {
  width: 100px;
  border-radius: 8px;
  margin-right: 20px;
}

.vehiculo-datos {
  display: flex;
  flex-direction: column;
}

.vehiculo-precio {
  margin-left: auto;
  text-align: right;
}

.vehiculo-datos p, .vehiculo-precio h4 {
  margin: 0;
  font-size: 16px;
  color: #333;
}

.button-container {
  display: flex;
  justify-content: center; /* Center the buttons horizontally */
  gap: 20px; /* Space between buttons */
}

.margin-right {
  margin-right: 10px; /* Adjust the value as needed */
}

.select-button2 {
  margin-top: 0%;
  width: 10%;
  padding: 10px;
  border: none;
  position: absolute;
  align-items: center;
  border-radius: 4px;
  background-color: #fa2170;
  color: rgb(255, 254, 254);
  cursor: pointer;
  margin-left: -13%;
  margin-top: 0%;
  justify-content: center;
  border: 2px solid rgba(0, 0, 0, 0);
}
.action-btn{
  background-color: #fa2170;
  color: white;
  font-size: 16px;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}
</style>