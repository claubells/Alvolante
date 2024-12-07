<script setup>
import { useRouter } from 'vue-router'; // Importar el router
import axios from 'axios'; // Importar axios
import { ref, onMounted } from 'vue'; // Importar ref y onMounted para la reactividad

const router = useRouter(); // Usar el router
const nombreCliente = ref(''); // Declarar una variable reactiva para el nombre del cliente

// Obtener el userId desde el almacenamiento local
const userId = localStorage.getItem('userId');

// Función para obtener los detalles del usuario
const obtenerDetallesUsuario = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/usuarios/id`, {
      params: {
        id: userId
      }
    });
    nombreCliente.value = response.data.name; // Asumimos que la respuesta contiene el nombre del usuario
  } catch (error) {
    console.error("Error al obtener los detalles del usuario:", error);
  }
};

// Obtener los detalles del usuario al montar el componente
onMounted(() => {
  if (userId) {
    obtenerDetallesUsuario();
  } else {
    nombreCliente.value = 'Nombre Usuario'; // Valor por defecto si no hay userId
  }
});
</script>




<template>
  <main class="main-container">
    <!-- Barra de navegación -->
    <nav class="navbar">
      <div class="logo">Al Volante</div>
      <div class="nav-links">
        <a href="#" @click.prevent="toInicio">Inicio</a>
        <a href="#" @click.prevent="toPerfilCliente">Perfil</a>
        <a href="#">Opciones</a>
        <a href="#" @click.prevent="toContact">Contacto</a>
        <a href="#" @click.prevent="logout">Cerrar Sesión</a>
      </div>
    </nav>
    
    <!-- Contenedor principal que incluye la barra lateral y el contenido central -->
    <div class="content-container">
      <!-- Rectángulo a la izquierda -->
      <div class="profile-container">
        <img src="./media/profilePic.png" alt="Foto de Persona" class="profile-img" /> <!-- Imagen de persona -->
        <div class="button-container">
          <div class="nombre-cliente">{{ nombreCliente }}</div> <!-- Mostrar el nombre del cliente -->
          <button @click="toPerfilCliente">Perfil</button>
          <button @click="toHistorialArriendo">Historial de Arriendo</button>
          <button @click="toComprobantesPago">Comprobantes de Pago</button>
          <button @click="logout">Cerrar Sesión</button>
        </div>
      </div>

      <!-- Contenedor central -->
      <div class="central-container">
        <!-- Contenedor para "Datos Cliente" -->
        <div class="data-container">
          <h2 class="data-title">Datos Cliente</h2>
          <div class="data-section">
            <h3>Datos Personales</h3>
            <p><strong>Correo:</strong> brucedickinson@usach.cl</p>
            <p><strong>Fecha de Nacimiento:</strong> 07/08/1958</p>
            <button @click="editProfile" class="edit-button">Editar Perfil</button>
          </div>
        </div>

        <!-- Contenedor para "Documentos" -->
        <div class="document-container">
          <h2 class="document-title">Documentos</h2>
          <div class="document-section">
            <h3>Documentos</h3>
            <p><strong>DNI:</strong> 111111111111</p>
            <p><strong>Licencia de conducir:</strong> bsljdbajf</p>
            <button @click="editDocuments" class="edit-doc-button">Agregar Documentos</button>
          </div>
        </div>
      </div>

      <!-- Contenedor para "Historial de Arriendo" -->
      <div class="history-container">
        <h2 class="history-title">Historial de Arriendo</h2>
        <div class="history-section">
          <h3>Vehículos Arrendados</h3>
          <ul>
            <li><strong>Nissan Maluma:</strong> Activo</li>
            <li><strong>Ford Mustang:</strong> Finalizado</li>
            <li><strong>Suzuki Swift:</strong> Finalizado</li>
            <li><strong>KIA Morning:</strong> Finalizado</li>
            <li><strong>Suzuki ñuñuky:</strong> Finalizado</li>
            <li><strong>Changang chang:</strong> Finalizado</li>
          </ul>
        </div>
      </div>
    </div> <!-- Cierre de content-container -->
  </main>
</template>



<script>


// Redireccionamientos
function redireccionarASubpaginaContacto() {
  window.location.href = '/contacto';
}
export default {
  methods: {
    logout() {
      localStorage.removeItem("login"); // Limpia el almacenamiento local
      window.location.href = "/"; // Redirige al login
    },
    toContact() {
      redireccionarASubpaginaContacto();
    },
    toInicio() {
      this.$router.push('/user'); // Redirecciona a la vista de inicio
    },
    toPerfilCliente() {
      this.$router.push('/perfilCliente'); // Redirecciona a perfil de cliente
    },
    toHistorialArriendo() {
      this.$router.push('/historialArriendo'); // Redirecciona a historial de arriendo
    },
    toComprobantesPago() {
      this.$router.push('/comprobantesPago'); // Redirecciona a comprobantes de pago
    },
    editProfile() {
      // Lógica para editar el perfil
    },
    editDocuments() {
      // Lógica para agregar documentos
    },
    viewDetails() {
      // Lógica para ver detalles
    }
  }
};

import axios from 'axios'; // Importar axios
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
  margin-top: 80px; /* Ajustar según la altura de la barra de navegación */
}

/* Barra de navegación */
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background-color: #ff80ab;
  color: #fff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  position: fixed; /* Hace que la barra se quede fija en la parte superior */
  top: 0; /* La coloca exactamente en la parte superior */
  left: 0; /* Alinea al inicio del contenido horizontal */
  width: 100%; /* Se asegura de que ocupe todo el ancho */
  z-index: 1000;
}

.logo {
  font-size: 1.5rem;
  font-weight: bold;
}

.nav-links a {
  color: #fff;
  margin-left: 1.5rem;
  text-decoration: none;
  font-size: 1rem;
  transition: color 0.3s ease;
}

.nav-links a:hover {
  color: #ffd1dc;
}

/* Contenedor principal */
.content-container {
  display: flex;
  flex-direction: row;
  height: calc(100vh - 80px); /* Ajustar para que ocupe todo el espacio disponible debajo de la barra de navegación */
}

/* Contenedor del perfil */
.profile-container {
  position: absolute; /* Para colocarla en una posición específica */ 
  top: 70px; /* Ajustar según la altura de la barra de navegación */ 
  left: 0; /* Para pegarla al borde izquierdo */ 
  width: 350px; /* Ajustar el ancho según sea necesario */ 
  height: calc(100vh - 70px); /* Ajustar para que ocupe todo el espacio disponible debajo de la barra de navegación */ 
  display: flex;
   flex-direction: column; 
   align-items: center; 
   justify-content: center; /* Centrar contenido verticalmente */ 
   padding: 2rem; 
   background-color: rgba(255, 255, 255, 0.8); 
   border-radius: 0; 
   box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.profile-img { width: 150px; /* Ajustar el ancho de la imagen del perfil según sea necesario */ 
margin-top: 2rem; /* Añadir margen inferior para separar la imagen de los botones */ 
} 
.nombre-cliente { 
    position: relative; /* Cambiar la posición a relativa */ 
    margin-top: 0; 
    margin-bottom: 10px; 
    text-align: center; 
    font-size: 2.5rem; 
    font-weight: bold; 
    color: #633434; 
    } 
.button-container { 
    display: flex; 
    flex-direction: column; 
    width: 123%; /* Asegurar que los botones ocupen todo el ancho del contenedor */ 
} 
.button-container button { 
    background-color: #ff80ab; 
    color: #fff; 
    font-size:18px; 
    border: none; 
    border-radius: 0px; 
    padding: 2.7rem; 
    margin: 0rem 0; 
    cursor: pointer; 
    transition: background-color 0.3s ease; 
}

.button-container button:hover {
  background-color: #ff6f61;
}

/* Contenedor central para "Datos Cliente" */
.data-container {
  height: 300px;
  padding: 2rem;
  background-color: rgba(255, 255, 255, 0.8);
  margin-left: 21rem; /* Añadir margen izquierdo */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.data-title {
  font-size: 2rem;
  color: #633434;
  
  margin-bottom: 1rem;
}

.data-section {
  margin-bottom: 2rem;
}

.data-section h3 {
  font-size: 1.5rem;
  color: #333;
  margin-bottom: 0.5rem;
}

.data-section p {
  font-size: 1.2rem;
  color: #555;
}

.edit-button {
  display: inline-block;
  padding: 0.5rem 1rem;
  background-color: #ff80ab;
  color: #fff;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 1rem;
}

.edit-button:hover {
  background-color: #ff6f61;
}
/* Containe documentos */
.document-container {
  height: 300px;
  width: 500px;
  padding: 2rem;
  background-color: rgba(255, 255, 255, 0.8);
  margin-top: 2rem;
  margin-left: 21rem; /* Añadir margen izquierdo */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.document-title {
  font-size: 2rem;
  color: #633434;
  
  margin-bottom: 1rem;
}

.document-section {
  margin-bottom: 2rem;
}

.document-section h3 {
  font-size: 1.5rem;
  color: #333;
  margin-bottom: 0.5rem;
}

.document-section p {
  font-size: 1.2rem;
  color: #555;
}

.edit-doc-button {
  display: inline-block;
  padding: 0.5rem 1rem;
  background-color: #ff80ab;
  color: #fff;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 1rem;
}

.edit-doc-button:hover {
  background-color: #ff6f61;
}
.history-container{
  position: absolute;
  height: 633px;
  width: 350px;
  padding: 1.5rem;
  background-color: rgba(255, 255, 255, 0.8);
  margin-top: 0.1rem;
  margin-right: 6rem;
  margin-left: 55rem; /* Añadir margen izquierdo */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
.history-title{
  font-size: 2rem;
  color: #633434;
  
  margin-bottom: 1rem;    
}
.history-section {
  font-size: 1.2rem;
  color: #555;    
}

</style>
