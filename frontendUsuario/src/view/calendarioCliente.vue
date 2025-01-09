<template>
  <main class="main-container">
    <!-- Sección del calendario -->
    <section class="calendar-section">
      <h2>Reserva de vehículos</h2>
      <p>Seleccione las fechas y lugares de retiro y entrega del vehículo:</p>
      <!-- Calendarios -->
      <div class="form-container">
        <div class="form-input">
          <label for="fechaRetiro">Fecha de Retiro:</label>
          <input type="date" id="fecha-retiro" v-model="fechaRetiro" />
        </div>
        <div class="form-input">
          <label for="fechaEntrega">Fecha de Entrega:</label>
          <input type="date" id="fechaEntrega" v-model="fechaEntrega" />
        </div>
        <!-- Lugar de retiro -->
        <div class="form-input">
          <label for="lugar-retiro">Lugar de Retiro:</label>
          <select id="lugar-retiro" v-model="lugarRetiro">
            <option value="">Seleccione un lugar</option>
            <option v-for="sucursal in sucursales" :key="sucursal.idSucursal" :value="sucursal.region">
              {{ sucursal.region }}
            </option>
          </select>
        </div>
        <!-- Lugar de entrega -->
        <div class="form-input">
          <label for="lugar-entrega">Lugar de Entrega:</label>
          <select id="lugar-entrega" v-model="lugarEntrega">
            <option value="">Seleccione un lugar</option>
            <option v-for="sucursal in sucursales" :key="sucursal.idSucursal" :value="sucursal.region">
              {{ sucursal.region }}
            </option>
            </select>
        </div>
      </div>
                    
      <button class="submit-button" @click="handleSubmit">Confirmar Datos</button>
    
    </section>
  </main>
</template>
  
<script setup>
// Imports
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router'; 
import Swal from 'sweetalert2';
import axios from 'axios';

// Variables reactivas
const router = useRouter();
const sucursales = ref([]); // regiones cargadas del back
const fechaRetiro = ref("");
const fechaEntrega = ref("");
const lugarRetiro = ref("");
const lugarEntrega = ref("");

// Métodos
const handleSubmit = async () => {
  const hoy = new Date(); // Fecha actual

  console.log("Fechas seleccionadas..");
  console.log("Fecha de retiro:", fechaRetiro.value);
  console.log("Fecha de entrega:", fechaEntrega.value);

  const fechaRetiroDate = toLocalDate(fechaRetiro.value);
  const fechaEntregaDate = toLocalDate(fechaEntrega.value);

  console.log("Fecha de retiro (Date):", fechaRetiroDate);
  console.log("Fecha de entrega (Date):", fechaEntregaDate);

  if (!fechaRetiro.value || !fechaEntrega.value || !lugarRetiro.value || !lugarEntrega.value) {
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

  if (fechaRetiroDate > fechaEntregaDate) {
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

  const diferenciaDias = (fechaEntregaDate - fechaRetiroDate) / (1000 * 60 * 60 * 24);
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
  if (fechaRetiroDate <= hoySinHora) {
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

  // Buscar el ID de la sucursal seleccionada
  const sucursalRetiro = sucursales.value.find((s) => s.region === lugarRetiro.value);
  const sucursalEntrega = sucursales.value.find((s) => s.region === lugarEntrega.value);

  if (!sucursalRetiro || !sucursalEntrega) {
    Swal.fire({
      title: "¡Error!",
      text: "No se pudo encontrar la sucursal seleccionada. Por favor, inténtelo nuevamente.",
      icon: "error",
      confirmButtonText: "OK",
    });
    return;
  }

  console.log("Guardando fechas en localStorage...");
  localStorage.setItem('fechaRetiro', fechaRetiro.value);
  localStorage.setItem('fechaEntrega', fechaEntrega.value);

  console.log("Guardando las sucursales en localStorage...");
  localStorage.setItem('idSucursalRetiro', sucursalRetiro.idSucursal);
  localStorage.setItem('idSucursalEntrega', sucursalEntrega.idSucursal);

  await Swal.fire({
    title: '¡Perfecto!',
    text: 'Se han guardado los datos perfectamete',
    icon: 'success',
    confirmButtonText: 'OK',
    customClass: {
      confirmButton: 'custom-confirm-button'
    }
  });

  //redirigir
  window.location.href = "/verAutosSegunCalendario";
};

// Función para cargar las sucursales
const cargarSucursales = async () => {
  try {
    const token = localStorage.getItem("jwtToken"); // Obtén el token desde el localStorage
    const response = await axios.get("http://localhost:8080/api/sucursales/all", {
      headers: {
        Authorization: `Bearer ${token}`, // Agrega el token en el encabezado
      },
    });
    sucursales.value = response.data; 
    console.log("Sucursales cargadas:", sucursales.value);
  } catch (error) {
    console.error("Error al cargar las sucursales:", error);
    Swal.fire({
      title: "Error",
      text: "No se pudieron cargar las sucursales. Por favor, inténtelo nuevamente.",
      icon: "error",
      confirmButtonText: "OK",
    });
  }
};

const toLocalDate = (fechaString) => {
  const [year, month, day] = fechaString.split("-").map(Number);
  return new Date(year, month - 1, day); 
  // month - 1 porque los meses van de 0 a 11
};

// Cargar las sucursales al montar el componente
onMounted(() => {
  cargarSucursales();
});

</script>

<style>
/* Estilo general */
body {
  font-family: 'Arial', sans-serif;
  margin: 0;
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
}

.main-container {
  max-width: 900px;
  margin: auto;
  padding: 2rem;
}

/* Sección del calendario */
.calendar-section {
  background: #fff;
  border-radius: 15px;
  padding: 2rem;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.section-title {
  font-size: 2rem;
  color: #444;
}

.section-description {
  font-size: 1rem;
  margin-bottom: 1.5rem;
  color: #666;
}

.form-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1.5rem;
}

.form-input label {
  font-weight: bold;
  color: #333;
}

.form-input input,
.form-input select {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 1rem;
}

.submit-button {
  margin-top: 2rem;
  background: #be0a97;
  color: #fff;
  padding: 0.75rem 2rem;
  border: none;
  border-radius: 25px;
  font-size: 1.2rem;
  cursor: pointer;
  transition: all 0.3s ease;
}

.submit-button:hover {
  background: #830b5b;
}

</style>