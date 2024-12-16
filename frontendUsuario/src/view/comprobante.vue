<script>
import NavbarComp from '@/components/navbarComp.vue';
import axios from "axios";

export default {
  props: ['idUsuario'],
  data() {
    return {
      reserva: null,
    };
  },
  methods: {
    async fetchReservaById(idUsuario) {
      try {
    const response = await axios.get(
      `${import.meta.env.VITE_BASE_URL}api/reserva/reservaPorId/${this.idUsuario}`
    );
    if (response.data) {
      this.reserva = response.data;
    } else {
      console.error("No se encontraron datos del vehículo.");
    }
  } catch (error) {
    console.error("Error al cargar los detalles del vehículo:", error);
    alert("No se pudo cargar la información del vehículo.");
  }
  },
  },
  mounted() {
    this.fetchReservaById(this.idUsuario);
  }
};
</script>

<template>
  <NavbarComp />
  <section class="contact-section">
    <h1>¡Reserva realizada con éxito!</h1>
    <div class="contact-content">
      <div class="contact-box">
        <h2>Comprobante de reserva</h2>
        <p v-if="reserva">Fecha retiro: {{ reserva.fechaInicioReserva }}</p>
        <p v-if="reserva">Código reserva: {{ reserva.idReserva }}</p>
        <p v-else>Cargando datos de la reserva...</p>
      </div>
    </div>
  </section>
  <h3>Debe mostrar este comprobante al momento de retirar el vehículo</h3>
</template>


<style>
/* Estilos generales */
body {
  margin: 0;
  font-family: "Arial", sans-serif;
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
}

.contact-section {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  height: calc(100vh - 80px);
}

.contact-content {
  display: flex;
  align-items: center;
}

.contact-box {
  background: rgba(255, 255, 255, 0.8);
  position: relative;
  left: -120%;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  margin-top: 1rem;
  width: 500px;
}

.contact-section h1 {
  font-size: 5rem;
  color: #ff4081;
  margin-bottom: 30%;
  margin-right: -10%;
}

.contact-section h2 {
  font-size: 1.9rem;
  color: #ff6f61;
  margin-top: 8%;
  position: relative;
  top: -45px;
}

.contact-section p {
  font-size: 1.2rem;
  color: #757575;
  margin-bottom: 1rem;
  position: relative;
  top: -40px;
}
</style>
