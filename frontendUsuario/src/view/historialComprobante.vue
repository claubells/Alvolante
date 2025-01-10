<template>
    <div class="profile-container">
        <img src="./media/profilePic.png" alt="Foto de Persona" class="profile-img" /> <!-- Imagen de persona -->
        <div class="button-container">
          <div class="nombre-cliente"> {{ usuario?.nameParam }}</div> 
          <button @click="toPerfilCliente"><i class="bi bi-person nav-icon"></i> Perfil</button>
          <button @click="toHistorialReserva"><i class="bi bi-journal nav-icon"></i> Historial de Reservas</button>
          <button @click="toHistorialComprobantes"><i class="bi bi-card-checklist nav-icon"></i> Comprobantes de Pago</button>
          <button @click="logout"><i class="bi bi-box-arrow-right nav-icon"></i> Cerrar Sesión</button>
        </div>
      </div>

    <h1>Comprobantes de Pagos</h1>
    <div class="boleta-container mt-5">
      <div v-if="boletas.length">
        <div v-for="boleta in boletas" :key="boleta.idBoleta" class="boleta-card">
          <p><strong>ID Boleta:</strong> {{ boleta.idBoleta }}</p>
          <p><strong>Nombre Emisor:</strong> {{ boleta.nombreEmisor }}</p>
          <p><strong>Rut Emisor:</strong> {{ boleta.rutEmisor }}</p>
          <p><strong>Dirección Emisor:</strong> {{ boleta.direccionEmisor }}</p>
          <p><strong>Nombre Cliente:</strong> {{ boleta.nombreCliente }}</p>
          <p><strong>Rut Cliente:</strong> {{ boleta.rutCliente }}</p>
          <p><strong>Fecha Emisión:</strong> {{ formatDate(boleta.fechaEmision) }}</p>
          <p><strong>Hora Emisión:</strong> {{ boleta.horaEmision }}</p>
          <p><strong>Subtotal:</strong> {{ boleta.subtotal }}</p>
          <p><strong>IVA:</strong> {{ boleta.iva }}</p>
          <p><strong>Costo Total:</strong> ${{ boleta.total }}</p>
          <p><strong>Forma de Pago:</strong> {{ boleta.formaPago }}</p>
        </div>
      </div>
      <p v-else>No hay boletas disponibles.</p>
    </div>
  </template>
  
  <script setup>
// Imports
import { ref, onMounted } from 'vue';
import { userService } from '../services/userService';
import axios from "axios";
import Swal from 'sweetalert2';

// Variables reactivas
const boletas = ref([]); // Lista para almacenar las boletas
const usuario = ref('');

// Métodos
const fetchBoletas = async () => {
  try {
    const token = localStorage.getItem("jwtToken"); // Obtén el token del almacenamiento local
    const response = await axios.get("http://localhost:8080/api/boleta/obtenerBoletaPorId", {
      headers: {
        Authorization: `Bearer ${token}`, // Añade el token al encabezado
      },
    });
    boletas.value = response.data; // Asigna las boletas a la variable
  } catch (error) {
    console.error("Error al obtener las boletas:", error);
    Swal.fire({
      title: "Error",
      text: "No se pudieron cargar las boletas disponibles.",
      icon: "error",
    });
  }
};

const loadUserData = async () => {
  try {
    const idUsuario = localStorage.getItem('userId')
    usuario.value = await userService.getUserById(idUsuario)
    console.log('Datos del id usuario:', idUsuario);

  } catch (error) {
    console.error('Error al cargar datos del usuario:', error)
  }
};

onMounted(loadUserData);

const formatDate = (dateString) => {
  if (!dateString) return "Fecha no disponible";

  const date = new Date(dateString);
  const day = date.getDate(); // Día del mes
  const monthNames = [
    "enero del", "febrero del", "marzo del", "abril del", "mayo del", "junio del",
    "julio del", "agosto del", "septiembre del", "octubre del", "noviembre del", "diciembre del",
  ];
  const month = monthNames[date.getMonth()]; // Nombre del mes
  const year = date.getFullYear(); // Año

  return `${day} ${month} ${year}`;
};

// Cargar datos al montar el componente
onMounted(() => {
    fetchBoletas(); // Llama a la función al cargar el componente
});

// Redireccionar a la página perfilCliente
const toPerfilCliente = () => window.location.href = "/perfilCliente";

// Redireccionar a la página del historial de Reservas
const toHistorialReserva = () => window.location.href = "/historialReserva";

// Redireccionar a la página de comprobantes de pago
const toHistorialComprobantes = () => window.location.href = "/historialComprobante";

// Redireccionar a la página de inicio
const logout = () => {
  localStorage.clear();
  window.location.href = "/";
};
</script>

<style>
/* Contenedor principal */
body {
background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
color: #000;
font-family: Arial, sans-serif;
margin: 0;
padding: 0;
}

.container {
display: flex;
min-height: 100vh;
}
/* Contenedor principal */
.main-content {
margin-left: 270px;
padding: 40px;
flex: 1;
}

h1 {
font-size: 28px;
margin-bottom: 30px;
margin-left: 42.56%;
}

/* Contenedor de vehículos */
.boleta-container {
display: flex;
flex-wrap: wrap;
gap: 30px;
justify-content: center;

}

.boleta-card {
border: 1px solid #ffd1dc;
border-radius: 15px;
padding: 20px;
width: 350px;
background: #fff;
box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
text-align: center;
transition: transform 0.3s, box-shadow 0.3s;
}



.boleta-card:hover {
transform: scale(1.05);
box-shadow: 0 12px 20px rgba(0, 0, 0, 0.2);
}

.boleta-card p {
font-size: 16px;
margin: 10px 0;
color: #555;
}

.boleta-card p strong {
color: #000000;
}

p {
font-size: 18px;
color: #333;
}

p[v-else] {
text-align: center;
font-size: 20px;
color: #ff0095;
}
</style>
  
  