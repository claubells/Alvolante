<template>
  <main class="main-container">
    <div class="container mt-5">
      <div class="row">
        <div class="col-md-6">
          <div class="container-custom" v-if="vehiculo">
            <button @click="Volver" class="select-button2">Volver</button>
            <h2>Resumen de Compra</h2>
            <div class="vehiculo-resumen">
              <img v-if="vehiculo.fotoVehiculo" :src="'data:image/jpeg;base64,' + vehiculo.fotoVehiculo" alt="Foto del Vehículo" class="vehiculo-imagen" />
              <div class="vehiculo-datos">
                <h4>Datos del vehículo:</h4>
                <p>{{ vehiculo.modelo + ", " + vehiculo.tipo + ". " + vehiculo.anio }}</p>
              </div>
            </div>
            <!-- Mostrar sucursales de retiro y entrega -->
            <div class="sucursal-resumen mt-4">
              <h4>Sucursal de Retiro:</h4>
              <p>{{ sucursalRetiro.direccion + ", " + sucursalRetiro.region }}</p>
              <h4>Sucursal de Entrega:</h4>
              <p>{{ sucursalEntrega.direccion + ", " + sucursalEntrega.region }}</p>
            </div>
          </div>
          <p v-else>Cargando datos del vehículo...</p>
        </div>
        <div class="col-md-6">
          <div class="container-custom">
            <h2>Detalles de Pago</h2>
            <form @submit.prevent="enviarReserva">
              <h4 v-if="vehiculo">Precio vehículo: ${{ vehiculo.costo }}</h4>
              <h4>Extras: -</h4>
              <h4 v-if="vehiculo">Total: ${{ vehiculo.costo }} (con IVA incluido)</h4>
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
              <button type="button" @click="VerBoletas" class="btn btn-primary mt-3">Ver boletas</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script setup>
// Importaciones necesarias
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';
import Swal from 'sweetalert2';

// Definición de variables reactivas
const router = useRouter();
const route = useRoute();
const vehiculo = ref(null);
const sucursalRetiro = ref({ direccion: "", region: "" });
const sucursalEntrega = ref({ direccion: "", region: "" });

const reserva = ref({
  fechaInicioReserva: localStorage.getItem("fechaRetiro") || "",
  fechaFinReserva: localStorage.getItem("fechaEntrega") || "",
  estadoReserva: 1,
  costoReserva: "",
  extrasReserva: "",
  idVehiculo: localStorage.getItem("idVehiculo"),
});

const idUsuario = ref(localStorage.getItem("idUsuario"));

// Función para obtener los datos del vehículo
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
    await Swal.fire({
      title: '¡Error!',
      text: 'No se pudo cargar la información del vehículo.',
      icon: 'error',
      confirmButtonText: 'Aceptar',
      customClass: {
        confirmButton: 'custom-confirm-button'
      }
    });
  }
};

// Función para cargar las sucursales desde el localStorage
const cargarSucursales = async () => {
  try {
    const idSucursalRetiro = localStorage.getItem("idSucursalRetiro");
    const idSucursalEntrega = localStorage.getItem("idSucursalEntrega");

    const token = localStorage.getItem("jwtToken"); // Obtén el token del almacenamiento local

    if (idSucursalRetiro) {
      const responseRetiro = await axios.get(`http://localhost:8080/api/sucursales/${idSucursalRetiro}`, {
        headers: {
          Authorization: `Bearer ${token}`, // Añade el token al encabezado
        },
      });

      console.log("Sucursal de retiro: \n -Region:", responseRetiro.data.region);
      console.log(" -Dirección", responseRetiro.data.direccion);
      sucursalRetiro.value = responseRetiro.data || "No seleccionado";
    } else {
      sucursalRetiro.value = "No seleccionado";
    }

    if (idSucursalEntrega) {
      const responseEntrega = await axios.get(`http://localhost:8080/api/sucursales/${idSucursalEntrega}`, {
        headers: {
          Authorization: `Bearer ${token}`, // Añade el token al encabezado
        },
      });

      console.log("Sucursal de entrega: \n -Region:", responseEntrega.data.region);
      console.log(" -Dirección", responseEntrega.data.direccion);
      sucursalEntrega.value = responseEntrega.data || "No seleccionado";
    } else {
      sucursalEntrega.value = "No seleccionado";
    }
  } catch (error) {
    console.error("Error al cargar las sucursales:", error);
  }
};



// Función para enviar la reserva
const enviarReserva = async () => {
  try {
    const token = localStorage.getItem("jwtToken"); // Obtén el token del almacenamiento local
    const response = await axios.post('http://localhost:8080/api/reserva/crear-reserva', reserva.value, {
      headers: {
        Authorization: `Bearer ${token}`, // Añade el token al encabezado
        'Content-Type': 'application/json'
      }
    });
    Swal.fire({
      title: '¡Éxito!',
      text: '¡Reserva realizada exitosamente!',
      icon: 'success',
      confirmButtonText: 'OK',
      customClass: {
        confirmButton: 'custom-confirm-button'
      }
    });
  } catch (error) {
    console.error("Error al realizar la reserva:", error);
    await Swal.fire({
      title: '¡Error!',
      text: 'No se pudo realizar la reserva.',
      icon: 'warning',
      confirmButtonText: 'OK',
      customClass: {
        confirmButton: 'custom-confirm-button'
      }
    });
  }
};

// Función para volver a la selección de vehículos
const Volver = () => {
  router.push('/seleccionVehiculoCliente/' + route.params.idVehiculo);
};

// Redireccionar a la página de comprobantes de pago
const VerBoletas = () => window.location.href = "/historialComprobante";

// Cargar datos al montar el componente
onMounted(() => {
  fetchVehiculo(); // Llama a la función al cargar el componente
  cargarSucursales(); // Carga las sucursales al montar el componente
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