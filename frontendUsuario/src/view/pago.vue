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
    <!-- Barra de navegación -->
    <!-- <nav class="navbar">
      <div class="logo">Al Volante</div>
      <div class="nav-links">
        <a href="#" @click.prevent="toInicio">Inicio</a>
        <a href="#" @click.prevent="toPerfilCliente">Perfil</a>
        <a href="#" @click.prevent="toContact">Contacto</a>
        <a href="#" @click.prevent="logout">Cerrar Sesión</a>
      </div>
    </nav> -->
    <NavbarComp /> 
    <!-- Sección de contenido principal -->
    <div class="container mt-5">
      <div class="row">
        <!-- Resumen de la compra -->
        <div class="col-md-6">
          <div class="container-custom" v-if="vehiculo">
            <button @click="Volver" class="select-button2">  Volver  </button>
            <h2>Compra</h2>
            <h3>Resumen</h3>
            <div class="vehiculo-resumen">
              <img 
                v-if="vehiculo.fotoVehiculo"
                :src="'data:image/jpeg;base64,' + vehiculo.fotoVehiculo"
                alt="Foto del Vehículo"
                class="vehiculo-imagen"
              />
              <div class="vehiculo-datos">
                <h5>{{ vehiculo.modelo }}</h5>
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
              <p>Nombre en la tarjeta</p>
              <input type="text" class="form-control mt-3" placeholder="Nombre en la tarjeta">
              <p>Número de tarjeta</p>
              <input type="text" class="form-control mt-3" placeholder="Número de tarjeta">
              <p>Fecha de expiración</p>
              <input type="month" class="form-control mt-3" placeholder="Fecha de expiración" :min="minDate" :max="maxDate" v-model="expiryDate">
              <p>CVV</p>
              <input type="text" class="form-control mt-3" placeholder="CVV">
            </div>
            <div class="total">
              <p>Precio: ${{ vehiculo.costo }}</p>
              <button class="btn btn-primary btn-block mt-3">Pagar</button>
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
    };
  },
  methods: {
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
    verDetallesVehiculoPago(idVehiculo) {
window.location.href = `/pago/${idVehiculo}`;
    },
    Volver() {
  // Implementar la lógica de selección de vehículo, por ejemplo, redirigir o guardar datos
    window.location.href = "/verAutosSegunCalendario";
  },
  /* logout() {
      localStorage.removeItem("login"); // Limpia el almacenamiento local
      window.location.href = "/"; // Redirige al login
    },
    toContact() {
      window.location.href = "/contacto";
    },
    toCalendario(){
      window.location.href = "/calendarioCliente"; 
      
    },
    toPerfilCliente(){
      window.location.href = "/perfilCliente";
    },
    toInicio(){
      window.location.href = "/user"; // donde esta la vista inicio?????????
    }, */
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
.container-custom h2{
  font-weight: bold;
  font-size: 180%;
}
.container-custom h3{
  font-weight: bold;
  font-size: 130%;
  margin-left: 2%;
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
.vehiculo-datos h5 {
  margin: 0;
  font-size: 22px;
  font-weight: 700;
  color: #333;
}
.vehiculo-datos p {
  margin: 0;
  font-size: 16px;
  color: #333;
}
.vehiculo-precio h4{
  font-weight: bold;
  font-size: 20px;
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
  .card-details p {
    margin-top: 2%;
    font-weight: bold;
  }
  .total{
    margin-top: 2%;
    font-size: 20px;
    font-weight: bold;
  }

</style>