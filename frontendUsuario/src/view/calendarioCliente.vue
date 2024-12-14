<script setup>
import { useRouter } from 'vue-router'; 
import NavbarComp from '@/components/navbarComp.vue'; // Importa el componente

const router = useRouter();
</script>

<template>
    <main class="main-container">
      <!-- Barra de navegación -->
      
      <NavbarComp /> 
  
      <!-- Sección del calendario -->
      <section class="calendar-section">
        <h2>Arriendo de Vehículos</h2>
        <p>Seleccione las fechas y lugares de retiro y entrega del vehículo:</p>
  
        <div class="form-container">
          <div class="form-input">
            <label for="fecha-retiro">Fecha de Retiro:</label>
            <input type="date" id="fecha-retiro" v-model="fechaRetiro" />
          </div>
          <div class="form-input">
            <label for="fecha-entrega">Fecha de Entrega:</label>
            <input type="date" id="fecha-entrega" v-model="fechaEntrega" />
          </div>
          <div class="form-input">
            <label for="lugar-retiro">Lugar de Retiro:</label>
            <select id="lugar-retiro" v-model="lugarRetiro">
              <option value="">Seleccione un lugar</option>
              <option value="PICHILEMU">PICHILEMU</option>
              <option value="OSORNO">OSORNO</option>
              <option value="PUTUE">PUTUE</option>
            </select>
          </div>
          <div class="form-input">
            <label for="lugar-entrega">Lugar de Entrega:</label>
            <select id="lugar-entrega" v-model="lugarEntrega">
              <option value="">Seleccione un lugar</option>
              <option value="Santiago">PICHILEMU</option>
              <option value="OSORNO">OSORNO</option>
              <option value="PUTUE">PUTUE</option>
            </select>
          </div>
        </div>
  
        <button class="submit-button" @click="handleSubmit">Confirmar Datos</button>
      </section>
    </main>
  </template>
  
  <script>
  
  export default {
  data() {
    return {
      fechaRetiro: "",
      fechaEntrega: "",
      lugarRetiro: "",
      lugarEntrega: "",
    };
  },
  methods: {
    handleSubmit() {
      if (!this.fechaRetiro || !this.fechaEntrega || !this.lugarRetiro || !this.lugarEntrega) {
        alert("Por favor complete todos los campos.");
        return;
      }

      if (new Date(this.fechaRetiro) > new Date(this.fechaEntrega)) {
        alert("La fecha de retiro no puede ser posterior a la fecha de entrega.");
        return;
      }

      window.location.href = "/verAutosSegunCalendario";
    },
    /* logout() {
      localStorage.removeItem("login");
      window.location.href = "/";
    },
    toContact() {
        window.location.href = "/contacto";
    },
    toPerfilCliente() {
      window.location.href = "/perfilCliente";
    },
    logout() {
      localStorage.removeItem("login"); // Limpia el almacenamiento local
      window.location.href = "/"; // Redirige al login
    },
    toInicio() {
      window.location.href = "/user"; // Redirecciona a la vista de inicio
    },
    toPerfilCliente() {
      window.location.href = "/perfilCliente"; // Redirecciona a perfil de cliente
  }, */
}

};
  </script>
  
  <style>
  body {
    margin: 0;
    font-family: "Arial", sans-serif;
    background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
  }
  
  
  
  /* Sección del calendario */
  .calendar-section {
    text-align: center;
    margin: 2rem;
    padding-top: 5rem;
  }
  
  .form-container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 1.5rem;
    margin: 2rem 0;
    justify-content: center;
  }
  
  .form-input {
    display: flex;
    flex-direction: column;
    gap: 0.5rem;
  }
  
  .form-input label {
    font-size: 1rem;
    color: #333;
  }
  
  .form-input input,
  .form-input select {
    padding: 0.5rem;
    border: 1px solid #ccc;
    border-radius: 8px;
    font-size: 1rem;
  }
  
  .submit-button {
    background: #ff80ab;
    color: #fff;
    border: none;
    padding: 0.75rem 1rem;
    border-radius: 8px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .submit-button:hover {
    background: #ff4081;
  }
  </style>