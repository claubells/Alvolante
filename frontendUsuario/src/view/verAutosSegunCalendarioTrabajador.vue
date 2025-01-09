<template>
  <div>
    <!-- Barra lateral -->
    <div class="sidebar">
      <img class="image" src="./media/logoalvolante.png" alt="Logo" />
      <ul>
        <li><a href="#" @click="toInicio"><i class="bi bi-house-door nav-icon"></i> Inicio</a></li>
        <li><a href="#" @click="cierreSesion"><i class="bi bi-box-arrow-right nav-icon"></i> Cerrar sesión</a></li>
      </ul>
    </div>

    <!-- Contenido principal -->
     
    <div class="main-content">
      <h2>Lista de Vehículos</h2>
      <div class="filtro-item">
      <div class="filtros">
        <input v-model="filtro.busqueda" type="text" placeholder="Buscar modelo o tipo..." />
        <select v-model="filtro.tipo">
          <option value="">Tipo de vehículo</option>
          <option value="SUV">SUV</option>
          <option value="Sedan">Sedan</option>
          <option value="City car">City Car</option>
        </select>
        <input v-model.number="filtro.capacidad" type="number" placeholder="Capacidad pasajeros" />
        <input v-model.number="filtro.anio" type="number" placeholder="Año del vehículo" />
        <input v-model="filtro.color" type="text" placeholder="Color" />
        <button class="botonResetear" @click="resetearFiltros">Resetear</button>
        </div>
      </div>
 

      
      <!-- Contenedor de vehículos -->
      <div class="vehiculos-container" v-if="vehiculosFiltrados.length">
        <div v-for="vehiculo in vehiculosFiltrados" :key="vehiculo.idVehiculo" class="vehiculo-card">
          <img
            v-if="vehiculo.fotoVehiculo"
            :src="'data:image/jpeg;base64,' + vehiculo.fotoVehiculo"
            alt="Foto del Vehículo"
            class="vehiculo-imagen"
          />
          <!-- Datos del vehículo -->
          <div class="vehiculo-info">
            <h2>{{ vehiculo.modelo }}</h2>
            <div class="vehiculo-detalles">
              <p><i class="bi bi-people"></i><strong>Pasajeros:</strong> {{ vehiculo.capacidadPasajeros }}</p>
              <p><i class="bi bi-fuel-pump"></i><strong>Combustible:</strong> {{ vehiculo.combustible }}</p>
              <p><i class="bi bi-gear"></i><strong>Transmisión:</strong> {{ vehiculo.tipo }}</p>
            </div>
            <!-- Botón de selección -->
            <button @click="verDetallesVehiculoTrabajador(vehiculo.idVehiculo)" class="select-button">Seleccionar</button>
          </div>
        </div>
      </div>
      <p v-else>No hay vehículos disponibles.</p>
    </div>
  </div>
</template>

<script setup>
// Imports
import { ref, computed, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router'; 

// Variables reactivas
const router = useRouter();
const vehiculos = ref([]);
const filtro = ref({
  busqueda: "",
  tipo: "",
  capacidad: null,
  anio: null,
  color: "",
});

// Computed para los vehículos filtrados
const vehiculosFiltrados = computed(() => {
  return vehiculos.value.filter(vehiculo => {
    const cumpleBusqueda = filtro.value.busqueda === "" || 
      vehiculo.modelo.toLowerCase().includes(filtro.value.busqueda.toLowerCase()) || 
      vehiculo.tipo.toLowerCase().includes(filtro.value.busqueda.toLowerCase());
    const cumpleTipo = filtro.value.tipo === "" || vehiculo.tipo === filtro.value.tipo;
    const cumpleCapacidad = !filtro.value.capacidad || vehiculo.capacidadPasajeros === filtro.value.capacidad;
    const cumpleAnio = !filtro.value.anio || vehiculo.anio === filtro.value.anio;
    const cumpleColor = filtro.value.color === "" || vehiculo.color.toLowerCase().includes(filtro.value.color.toLowerCase());
    return cumpleBusqueda && cumpleTipo && cumpleCapacidad && cumpleAnio && cumpleColor;
  });
});

// Métodos
const cierreSesion = () => window.location.href = "/";
const toInicio = () => window.location.href = "/trabajador";

const fetchVehiculosDispo = async () => {
  try {
    const token = localStorage.getItem("jwtToken"); // Obtén el token del almacenamiento local
    const response = await axios.get("http://localhost:8080/api/vehiculos/all", {
      headers: {
        Authorization: `Bearer ${token}`, // Añade el token al encabezado
      },
    });
    vehiculos.value = response.data; // Asigna los vehículos a la variable
  } catch (error) {
    console.error("Error al obtener los vehículos:", error);
    Swal.fire({
      title: "Error",
      text: "No se pudieron cargar los vehículos disponibles.",
      icon: "error",
    });
  }
};

const verDetallesVehiculoTrabajador = (idVehiculo) => {
  router.push({ name: 'seleccionVehiculoTrabajador', params: { idVehiculo } });
};

const resetearFiltros = () => {
  filtro.value = {
    busqueda: "",
    tipo: "",
    capacidad: null,
    anio: null,
    color: "",
  };
};

onMounted(() => {
  fetchVehiculosDispo(); // Llamamos a la API al cargar
});
</script>

<style>
/* Estilo general */
body {
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
  color: #000;
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
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
  margin-left: 0%;
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
  margin-left: 270px; 
  padding: 20px;
}

h1 {
  font-size: 22px;
  margin-bottom: 10px;
}

/* Vehículos */
.vehiculos-container {
  display: flex;
  flex-wrap: wrap;
  gap: 30px;
  justify-content: center;
}

.vehiculo-card {
  border: 1px solid #ccc;
  border-radius: 12px;
  padding: 20px;
  width: 730px;
  background-color: #fff;
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s;
}

.vehiculo-card img {
  max-width: 60%;
  height: auto;
  border-radius: 12px;
  margin-bottom: 15px;
}

.vehiculo-info {
  text-align: center;
}

.vehiculo-card p {
  margin: 5px 0;
  font-size: 14px;
}

.vehiculo-detalles {
  display: flex;
  gap: 10px;
  margin-top: 15px;
  justify-content: center;
}

.vehiculo-detalles p {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  font-size: 16px;
  background-color: #f9f9f9; 
  padding: 10px 15px; 
  border-radius: 10px; 
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
  border: 1px solid #ddd; 
}

.vehiculo-detalles i {
  font-size: 24px; 
  color: #ff80ab; 
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

  .select-button {
  background-color: #ff80ab;
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  padding: 12px 24px;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease, background-color 0.3s ease;
}

.filtros {
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
  margin-bottom: 20px;
  padding: 15px;
  background-color: #f9f9f9;
  border: 1px solid #000000;
  border-radius: 10px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

.input-filtro, .input-busqueda, .input-rango {
  min-width: 180px; /* Asegura que los inputs no sean demasiado pequeños */
  padding: 10px;
  border: none;
  border-radius: 8px;
  font-size: 14px;
  transition: border-color 0.3s, box-shadow 0.3s;
}

.input-filtro:focus, .input-busqueda:focus, .input-rango:focus {
  border-color: #007bff;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
  outline: none;
  box-shadow: none;
}

.filtro-item {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 10px;
  width: 100%;
}
.filtro-item input {
  min-width: 150px; /* Ajusta el tamaño mínimo */
  padding: 8px;
  border-radius: 6px;
  border: 1px solid #ccc;
}


.botonResetear {
  padding: 10px 20px;
  border: none;
  border-radius: 8px;
  font-size: 14px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s;
}

.botonResetear:hover {
  background-color: #0056b3;
  color: white;
  transform: scale(1.05);
}

.botonResetear:active {
  transform: scale(1);
}

.botonResetear:last-child {
  background-color: #ff80ab;
  color: white;
}

.botonResetear:last-child:hover {
  background-color: #ff80ab;
}

.botonResetear:last-child:active {
  background-color: #ff80ab;
}

</style>