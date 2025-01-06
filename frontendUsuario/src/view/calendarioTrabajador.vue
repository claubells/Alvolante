<template>
  <div class="container">

    <!-- Barra lateral -->
    <div class="sidebar">
      <img class="image" src="./media/logoalvolante.png" alt="Logo" />
      <ul>
        <li><a href="#" @click="toInicio"><i class="bi bi-house-door nav-icon"></i> Inicio</a></li>
        <li><a href="#" @click="cierreSesion"><i class="bi bi-box-arrow-right nav-icon"></i> Cerrar sesión</a></li>
      </ul>
    </div>

    <!-- Contenido principal -->
    <section class="calendar-section">
      <h2>Arriendo de vehículos</h2>
      <p>Seleccione las fechas y lugares de retiro y entrega del vehículo:</p>

      <!-- Contenedor del formulario -->
      <div class="form-container">
        <!-- Calendario -->
        <div class="form-input">
          <label for="fecha-entrega">Fecha de Entrega:</label>
          <input type="date" id="fecha-entrega" v-model="fechaEntrega" />
        </div>

        <!-- Lugar de entrega -->
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
      
      <!-- Botón de confirmación -->
      <button class="btn btn-block mt-3" style="background-color: #ff80ab; color: white;" @click="handleSubmit">Confirmar Datos</button>
    </section>
  </div>
</template>
  
<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router'; 
import Swal from 'sweetalert2';

// Variables reactivas
const router = useRouter();
const fechaEntrega = ref('');
const lugarEntrega = ref('');

// Métodos
const AgregarVehiculoAdmin = () => window.location.href = "/agregarvehiculo";
const toInicio = () => window.location.href = '/trabajador';
const cierreSesion = () => window.location.href = '/';
const handleSubmit = () => {
  const hoy = new Date(); // Fecha actual
  const hoySinHora = new Date(hoy.getFullYear(), hoy.getMonth(), hoy.getDate()); // Solo fecha, sin hora
  const fechaEntregaDate = new Date(fechaEntrega.value);

  // Verificar si los campos requeridos están completos
  if (!fechaEntrega.value || !lugarEntrega.value) {
    Swal.fire({
      title: '¡Cuidado!',
      text: 'Por favor complete todos los campos',
      icon: 'warning',
      confirmButtonText: 'OK',
      customClass: {
        confirmButton: 'custom-confirm-button',
      },
    });
    return;
  }

  // Verificar que la fecha de entrega no sea anterior a la fecha actual
  if (fechaEntregaDate < hoySinHora) {
    Swal.fire({
      title: '¡Cuidado!',
      text: 'La fecha de entrega no puede ser anterior a hoy',
      icon: 'warning',
      confirmButtonText: 'OK',
      customClass: {
        confirmButton: 'custom-confirm-button',
      },
    });
    return;
  }

  // Verificar que el periodo de arriendo no exceda los 30 días
  const diferenciaDias = (fechaEntregaDate - hoySinHora) / (1000 * 60 * 60 * 24);
  if (diferenciaDias > 30) {
    Swal.fire({
      title: '¡Cuidado!',
      text: 'El arriendo no puede ser por más de 30 días',
      icon: 'warning',
      confirmButtonText: 'OK',
      customClass: {
        confirmButton: 'custom-confirm-button',
      },
    });
    return;
  }

  window.location.href = "/verAutosSegunCalendarioTrabajador";
};
</script>

<style>
/* Estilos generales */
body {
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
  color: #000;
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}

.calendar-section{
  margin-top: 2%;
  margin-left: 35%;
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

.container {
  display: flex;
  min-height: 100vh;
}

/* Barra lateral */
.sidebar {
  width: 250px;
  background-color: #ff80ab;
  padding: 15px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  position: fixed;
  height: 100%;
  margin-left: -13%;
}

.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar ul li {
  margin-bottom: 12px;
}

.sidebar ul li a {
  text-decoration: none;
  color: white;
  font-size: 15px;
  display: block;
  padding: 7px;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.sidebar ul li a:hover {
  background-color: #ff4081;
}

.image {
  max-width: 100%;
  margin-bottom: 10px;
  border-radius: 8px;
}

/* Contenedor principal */
.main-content {
  margin-left: 70px;
  margin-top: 50px;
  padding: 10px;
  flex: 1;
}

h1 {
  font-size: 22px;
  margin-bottom: 10px;
}

.action-buttons {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 30px;
  margin-left: -70%;
}

.action-btn {
  background-color: #ff4081;
  color: white;
  font-size: 20px;
  padding: 15px 40px;
  border: none;
  border-radius: 50px;
  width: 80%;
  max-width: 300px;
  margin: 10px 0;
  cursor: pointer;
  transition: transform 0.3s, background-color 0.3s;
}

.action-btn:hover {
  background-color: #ff80ab;
  transform: scale(1.05);
}

@media (max-width: 768px) {
  .action-buttons {
    width: 100%;
  }
  .action-btn {
    width: 90%;
    font-size: 18px;
  }
}
/* Iconos */
.nav-icon {
  margin-right: 5px;
  font-size: 1.1rem;
  vertical-align: middle;
  }
  .nav-icon.large {
    font-size: 1.3rem;
  }
</style>