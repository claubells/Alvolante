<template>
  <main class="main-container">
    <div class="container mt-5">
      <div class="row">
        <div class="col-md-6">
          <div class="container-custom" v-if="vehiculo">
            <button @click="Volver" class="select-button2">Volver</button>
            <h2>Compra</h2>
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
        </div>
        <div class="col-md-6">
          <div class="container-custom">
            <h2>Detalles de Pago</h2>
            <form @submit.prevent="enviarReserva">
              <div class="form-group">
                <label for="cardNumber">Número de Tarjeta</label>
                <input type="text" class="form-control" id="cardNumber" placeholder="Número de Tarjeta" required>
              </div>
              <div class="form-group">
                <label for="expiryDate">Fecha de Expiración</label>
                <input type="month" class="form-control" id="expiryDate" placeholder="MM/AA" required>
              </div>
              <div class="form-group">
                <label for="cvv">CVV</label>
                <input type="text" class="form-control" id="cvv" placeholder="CVV" required>
              </div>
              <button type="submit" class="btn btn-primary btn-block mt-3">Pagar</button>
              <button type="button" @click="VerBoleta" class="btn btn-primary mt-3">Ver reserva</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';
import reservaServiceInstance from '../services/reservaService';

const router = useRouter();
const route = useRoute();


const reserva = ref({
  fechaInicioReserva: "",
  fechaFinReserva: "",
  quiereExtras: "",
  estadoReserva: 1,
  extrasReserva: "",
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
  router.push('/seleccionVehiculoCliente/' + route.params.idVehiculo);
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
  padding-top: 56px; /* Espacio para el navbar fijo */
}

/* Contenedor personalizado */
.container-custom {
  background-color: #ffffff; /* Fondo blanco */
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
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

.select-button2 {
    margin-top: 0%;
    width: 10%;
    padding: 10px;
    border: none;
    position: absolute;
    align-items: center;
    border-radius: 4px;
    background-color: #ffffff;
    color: rgb(0, 0, 0);
    cursor: pointer;
    margin-left: -13%;
    margin-top: 0%;
    justify-content: center;
    border: 2px solid black;
  }
</style>