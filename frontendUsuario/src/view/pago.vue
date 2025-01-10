<template>
  <header class="navbar navbar-expand-lg navbar-light bg-light w-100">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav w-100 d-flex justify-content-between">
        <li class="nav-item flex-fill text-center">
          <a class="nav-link disabled fw-bold" href="#" tabindex="-1" aria-disabled="true">Seleccionar sucursal y fechas</a>
        </li>
        <li class="nav-item flex-fill text-center">
          <a class="nav-link disabled fw-bold" href="#" tabindex="-1" aria-disabled="true">Seleccionar vehiculo</a>
        </li>
        <li class="nav-item flex-fill text-center">
          <a class="nav-link disabled fw-bold" href="#" tabindex="-1" aria-disabled="true">Confirmar reserva</a>
        </li>
        <li class="nav-item flex-fill text-center">
          <a class="nav-link active fw-bold custom-link" aria-current="page" aria-disabled="true">Pagar</a>
        </li>
      </ul>
    </div>
  </div>
</header>
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
const nombreCliente = ref(localStorage.getItem("nombreCliente"));
const rutCliente = ref(localStorage.getItem("rutCliente"));


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

// Función para enviar la reserva y generar la boleta
const enviarReserva = async () => {
  try {
    const token = localStorage.getItem("jwtToken"); // Obtén el token del almacenamiento local
    const responseReserva = await axios.post('http://localhost:8080/api/reserva/crear-reserva', reserva.value, {
      headers: {
        Authorization: `Bearer ${token}`, // Añade el token al encabezado
        'Content-Type': 'application/json'
      }
    });

    const iva = (reserva.value.costoReserva * 0.19).toFixed(2);

    // Generar boleta después de crear la reserva
    const responseBoleta = await axios.post('http://localhost:8080/api/boleta/generarBoleta', {
      fecha_emision: new Date().toISOString().split('T')[0],
      hora_emision: new Date().toLocaleTimeString(),
      iva: iva,
      subtotal: reserva.value.costoReserva,
      total: reserva.value.costoReserva + iva,
      direccion_emisor: sucursalRetiro.value.direccion,
      idUsuario: idUsuario.value,
      forma_pago: "Crédito",
      nombre_cliente: nombreCliente.value,
      nombre_emisor: "Trabajador Alvolante",
      rut_cliente: rutCliente.value,
      rut_emisor: "12345678-9" // Rut emisor random
    }, {
      headers: {
        Authorization: `Bearer ${token}`, // Añade el token al encabezado
        'Content-Type': 'application/json'
      }
    });

    Swal.fire({
      title: '¡Éxito!',
      text: '¡Reserva y boleta realizadas exitosamente!',
      icon: 'success',
      confirmButtonText: 'OK',
      customClass: {
        confirmButton: 'custom-confirm-button'
      }
    });
  } catch (error) {
    console.error("Error al realizar la reserva o generar la boleta:", error);
    await Swal.fire({
      title: '¡Error!',
      text: 'No se pudo realizar la reserva o generar la boleta.',
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

// Función para ver la boleta
const VerBoleta = () => {
  router.push('/boleta/' + route.params.idVehiculo);
};

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