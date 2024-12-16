<script setup>
import { useRouter } from 'vue-router'; 
import NavbarComp from '@/components/navbarComp.vue'; // Importa el componente
import Swal from 'sweetalert2';

const router = useRouter();
</script>

<template>
    <main class="main-container">

      <!-- Barra de navegación -->
       
      
      <NavbarComp /> 
      
      <!-- Sección del calendario -->
      <section class="calendar-section">
        <h2>Reserva de vehículos</h2>
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
              <option value="Sucursal Arica">Sucursal Arica</option>
            <option value="Sucursal Iquique">Sucursal Iquique</option>
            <option value="Sucursal Antofagasta">Sucursal Antofagasta</option>
            <option value="Sucursal Copiapó">Sucursal Copiapó</option>
            <option value="Sucursal La Serena">Sucursal La Serena</option>
            <option value="Sucursal Valparaíso">Sucursal Valparaíso</option>
            <option value="Sucursal Santiago">Sucursal Santiago</option>
            <option value="Sucursal Rancagua">Sucursal Rancagua</option>
            <option value="Sucursal Talca">Sucursal Talca</option>
            <option value="Sucursal Chillán">Sucursal Chillán</option>
            <option value="Sucursal Concepción">Sucursal Concepción</option>
            <option value="Sucursal Puerto Montt">Sucursal Puerto Montt</option>
            <option value="Sucursal Coyhaique">Sucursal Coyhaique</option>
            <option value="Sucursal Punta Arenas">Sucursal Punta Arenas</option>
            <option value="Sucursal Valdivia">Sucursal Valdivia</option>
            </select>
          </div>
          <div class="form-input">
            <label for="lugar-entrega">Lugar de Entrega:</label>
            <select id="lugar-entrega" v-model="lugarEntrega">
              <option value="">Seleccione un lugar</option>
              <option value="Sucursal Arica">Sucursal Arica</option>
            <option value="Sucursal Iquique">Sucursal Iquique</option>
            <option value="Sucursal Antofagasta">Sucursal Antofagasta</option>
            <option value="Sucursal Copiapó">Sucursal Copiapó</option>
            <option value="Sucursal La Serena">Sucursal La Serena</option>
            <option value="Sucursal Valparaíso">Sucursal Valparaíso</option>
            <option value="Sucursal Santiago">Sucursal Santiago</option>
            <option value="Sucursal Rancagua">Sucursal Rancagua</option>
            <option value="Sucursal Talca">Sucursal Talca</option>
            <option value="Sucursal Chillán">Sucursal Chillán</option>
            <option value="Sucursal Concepción">Sucursal Concepción</option>
            <option value="Sucursal Puerto Montt">Sucursal Puerto Montt</option>
            <option value="Sucursal Coyhaique">Sucursal Coyhaique</option>
            <option value="Sucursal Punta Arenas">Sucursal Punta Arenas</option>
            <option value="Sucursal Valdivia">Sucursal Valdivia</option>
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
  const hoy = new Date(); // Fecha actual
  const fechaRetiro = new Date(this.fechaRetiro);
  const fechaEntrega = new Date(this.fechaEntrega);

  if (!this.fechaRetiro || !this.fechaEntrega || !this.lugarRetiro || !this.lugarEntrega) {
    Swal.fire({
      title: '¡Cuidado!',
      text: 'Por favor complete todos los campos',
      icon: 'warning',
      confirmButtonText: 'OK',
      customClass: {
        confirmButton: 'custom-confirm-button'
      }
    });
    return;
  }

  if (fechaRetiro > fechaEntrega) {
    Swal.fire({
      title: '¡Cuidado!',
      text: 'La fecha de retiro no puede ser posterior a la fecha de entrega',
      icon: 'warning',
      confirmButtonText: 'OK',
      customClass: {
        confirmButton: 'custom-confirm-button'
      }
    });
    return;
  }

  const diferenciaDias = (fechaEntrega - fechaRetiro) / (1000 * 60 * 60 * 24);
  if (diferenciaDias > 30) {
    Swal.fire({
      title: '¡Cuidado!',
      text: 'El arriendo no puede ser por más de 30 días',
      icon: 'warning',
      confirmButtonText: 'OK',
      customClass: {
        confirmButton: 'custom-confirm-button'
      }
    });
    return;
  }

  const hoySinHora = new Date(hoy.getFullYear(), hoy.getMonth(), hoy.getDate());
  if (fechaRetiro <= hoySinHora) {
    Swal.fire({
      title: '¡Cuidado!',
      text: 'La fecha de retiro debe ser al menos un día después de hoy',
      icon: 'warning',
      confirmButtonText: 'OK',
      customClass: {
        confirmButton: 'custom-confirm-button'
      }
    });
    return;
  }

  window.location.href = "/verAutosSegunCalendario";
}   
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

  .custom-confirm-button {
  background-color: #ff80ab !important; 
  color: white !important;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 1rem;
}

.custom-confirm-button:hover {
  background-color: #ff80ab !important; 
}
  
  </style>