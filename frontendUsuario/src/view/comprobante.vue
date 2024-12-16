<script setup>

  
import NavbarComp from '@/components/navbarComp.vue'; // Importa el componente
import navbarComp from '@/components/navbarComp.vue';
</script>
<template>
    <NavbarComp /> 
    
    <section class="contact-section">
    <h1>¡Reserva realizada con exito!</h1>
      <div class="contact-content">
        <div class="contact-box">
          
          
          <h2>comprobante de reserva</h2>
          <!-- Verifica si la reserva existe antes de mostrar los datos -->
          <p v-if="reserva">Fecha de retiro: {{ reserva.fechaInicioReserva }}</p>
          <p v-if="reserva">Código de reserva: {{ reserva.idReserva }}</p>
          <p v-else>Cargando datos de la reserva...</p>
        </div>
      </div>
    </section>
    <h3>Debe mostrar este comprobante al momento de retirar el vehiculo</h3>
</template>
<script>
import { useRoute } from 'vue-router';
import axios from 'axios';

export default {
  data() {
    return {
      reserva: null,  // Aquí almacenaremos los datos de la reserva
    };
  },
  methods: {
    async getReservaIdUsuario(idUsuario) {
      try {
        // Solicita la reserva correspondiente al idUsuario
        const response = await axios.get(`api/reserva/usuario/${idUsuario}`);
        this.reserva = response.data;  // Guarda la reserva en el estado
      } catch (error) {
        console.error("Error al obtener la reserva:", error);
      }
    }
  },
  mounted() {
    // Obtén el idUsuario de los parámetros de la ruta
    const route = useRoute();
    const idUsuario = route.params.idUsuario;  // Obtén el idUsuario desde la URL
    this.getReservaIdUsuario(idUsuario);  // Llama a la API con el idUsuario
  }
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
  padding: 1rem;
}


.contacto_titulo {
  position: relative; /* Cambiar la posición a relativa */ 
  top: 100px; /* Ajustar la posición vertical hacia arriba */
  left: 1%;
  font-size: 2.5rem;
  font-weight: bold;
  color: #633434;
}

/* Sección de contacto */
.contact-section {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  height: calc(100vh - 80px); /* Ajustar según la altura de la barra de navegación */
}

.contact-content {
  display: flex;
  align-items: center;
}

.support-img {
  width: 700px; /* Ajustar el ancho de la imagen de soporte según sea necesario */
  position: relative; /* Cambiar la posición a relativa */ 
  left: 10%; /* Ajustar la posición vertical hacia arriba */
  margin-right: 12rem; /* Ajustar el margen derecho según sea necesario */ 
  margin-left: -15rem; /* Ajustar el margen izquierdo para mover la imagen a la izquierda */
  margin-top: 3rem; /* Ajustar el margen superior para mover la imagen hacia arriba */
}

.contact-box {
  background: rgba(255, 255, 255, 0.8); /* Fondo blanco semitransparente */
  position: relative; /* Cambiar la posición a relativa */ 
  left: -120%; /* Ajustar la posición vertical hacia arriba */
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  margin-top: 1rem; /* Ajustar el margen superior para mover la imagen hacia arriba */
  width: 500px;
}

.logo-img {
  width: 150px; /* Ajustar el ancho de la imagen del logo según sea necesario */
  margin-bottom: 1rem;
  position: relative; /* Cambiar la posición a relativa */ 
  top: -3px; /* Ajustar la posición vertical hacia arriba */
}

.iconos-img {
  width: 150px; /* Ajustar el ancho de la imagen del logo según sea necesario */
  margin-bottom: 1rem;
  position: relative; /* Cambiar la posición a relativa */ 
  top: -1px; /* Ajustar la posición vertical hacia arriba */
}
.contact-section h1 {
  font-size: 5rem;
  color: #ff4081;
  position:flex;
  margin-bottom: 30%;
  margin-right:-10%;

}

.contact-section h2 {
  font-size: 1,9rem;
  color: #ff6f61;
  margin-top: 8%;
  position: relative; /* Cambiar la posición a relativa */ 
  top: -45px; /* Ajustar la posición vertical hacia arriba */
}

.contact-section p {
  font-size: 1.2rem;
  color: #757575;
  margin-bottom: 1rem;
  position: relative; /* Cambiar la posición a relativa */ 
  top: -40px; /* Ajustar la posición vertical hacia arriba */
}
</style>