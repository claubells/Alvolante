<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import NavbarComp from '@/components/navbarComp.vue'; // Importa el componente

const router = useRouter();
const currentYear = new Date().getFullYear();
const currentMonth = String(new Date().getMonth() + 1).padStart(2, '0'); // Obtiene el mes actual en formato "MM"
const minDate = `${currentYear}-${currentMonth}`;
const maxDate = `${currentYear + 10}-12`; // Permite seleccionar hasta 10 años en el futuro
const expiryDate = ref('');
</script>

<template>
  <main class="main-container">
    <NavbarComp />
    <!-- Sección de contenido principal -->
    <div class="container mt-5">
      <div class="row">
        <!-- Resumen de la compra -->
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
        <!-- Información de pago -->
        <div class="col-md-6">
          <div class="container-custom">
            <h2>Información de Pago</h2>
            <div class="card-details">
              <div class="card-type d-flex justify-content-between">
                <!-- Aquí puedes añadir los logos de las tarjetas -->
              </div>
              <input type="text" class="form-control mt-3" placeholder="Nombre en la tarjeta" value="Elvis Teck" />
              <input type="text" class="form-control mt-3" placeholder="Número de tarjeta" value="1111 2222 3333 4444" />
              <input type="month" class="form-control mt-3" placeholder="Fecha de expiración" :min="minDate" :max="maxDate" v-model="expiryDate" />
              <input type="text" class="form-control mt-3" placeholder="CVV" value="123" />
            </div>
            <div class="total mt-3">
              <p>Precio: $3,000.00</p>
              <p>Envío: $820.00</p>
              <p>Total (impuestos incl.): $3,820.00</p>
              <button class="btn btn-primary btn-block mt-3" @click="toComprobante(idReserva)">Pagar</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import axios from "axios";

export default {
  props: ['idVehiculo'],
  data() {
    return {
      vehiculo: null,
      reserva: {
        fechaInicioReserva: "",
        fechaFinReserva: "",
        quiereExtras: null,
        estadoReserva: null,
        horaReserva: null,
        costoReserva: "",
      },
      idUsuario: 5 // Supongamos que este es el ID del usuario
    };
  },
  methods: {
    async enviarReserva() {
      // Completar los campos necesarios
      this.reserva.fechaInicioReserva = "2024-12-16"; // Ejemplo de fecha de inicio (ajusta según tu lógica)
      this.reserva.fechaFinReserva = "2024-12-20"; // Ejemplo de fecha de fin (ajusta según tu lógica)
      this.reserva.quiereExtras = false; // O true según corresponda
      this.reserva.estadoReserva = 1; // Ejemplo de estado de reserva
      this.reserva.horaReserva = 0; // Ejemplo de hora de reserva
      this.reserva.costoReserva = this.vehiculo.costo; // Asumiendo que el costo viene del vehículo

      try {
        const response = await axios.post(
          `${import.meta.env.VITE_BASE_URL}api/reserva/crear-reserva`,
          this.reserva,
          {
            params: {
              idUsuario: this.idUsuario, // Incluye el idUsuario como parámetro
              idVehiculo: this.idVehiculo
            }
          }
        );
        console.log(response.data);
        if (response.data === 1) {
          alert("No se puede hacer la reserva porque tiene una activa");
        } else if (response.data === 2) {
          alert("Error en fechas");
        } else if (response.data >= 0) {
          alert("Reserva realizada con éxito");
          this.toComprobante(response.data.idReserva); // Redirigir al comprobante con idReserva
        } else {
          alert("Error inesperado");
        }
      } catch (error) {
        console.error("Error:", error);
        alert("No se pudo generar conexión con el servidor");
      }
    },
    toComprobante(idReserva) {
      this.$router.push({ path: `/comprobante/${idReserva}` });
    },
    async fetchVehiculo() {
      try {
        const response = await axios.get(
          `${import.meta.env.VITE_BASE_URL}api/vehiculos/obtenerVehiculoPorId/${this.idVehiculo}`
        );
        this.vehiculo = response.data;
        if (!this.vehiculo) {
          throw new Error("No se encontró el vehículo con el ID proporcionado.");
        }
      } catch (error) {
        console.error("Error al cargar los detalles del vehículo:", error);
        alert("No se pudo cargar la información del vehículo.");
      }
    },
    Volver() {
      window.location.href = "/verAutosSegunCalendario";
    },
    // Otros métodos...
  },
  mounted() {
    this.fetchVehiculo(this.idVehiculo);
  },
};
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
