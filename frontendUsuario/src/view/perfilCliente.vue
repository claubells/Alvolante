<template>
  <main class="main-container">

    <!-- Contenedor principal que incluye la barra lateral y el contenido central -->
    <div class="content-container">
      <!-- Rectángulo a la izquierda -->
      <div class="profile-container">
        <img src="./media/profilePic.png" alt="Foto de Persona" class="profile-img" /> <!-- Imagen de persona -->
        <div class="button-container">
          <div class="nombre-cliente"> {{ usuario?.nameParam }}</div> 
          <button @click="toPerfilCliente"><i class="bi bi-person nav-icon"></i> Perfil</button>
          <button @click="toHistorialArriendo"><i class="bi bi-journal nav-icon"></i> Historial de Arriendo</button>
          <button @click="toComprobantesPago"><i class="bi bi-card-checklist nav-icon"></i> Comprobantes de Pago</button>
          <button @click="logout"><i class="bi bi-box-arrow-right nav-icon"></i> Cerrar Sesión</button>
        </div>
      </div>

      <!-- Contenedor central -->
      <div class="central-container">
        <!-- Contenedor para "Datos Cliente" -->
        <div class="data-container">
          <h2 class="data-title"><i class="bi bi-person-square nav-icon"></i> Datos Cliente</h2>
          <div class="data-section">
            <h3>Datos Personales</h3> <!-- Datos del usuario, deberia devolverlo el Backend --> 
            <p><strong>Correo:</strong> {{ usuario?.email }}</p>
            <button @click="editProfile" class="edit-button">Editar Perfil</button>
          </div>
        </div>

        <!-- Contenedor para "Documentos" -->
        <div class="document-container">
          <h2 class="document-title"><i class="bi bi-person-vcard nav-icon"></i>Documentos</h2>
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
        <h2 class="history-title"><i class="bi bi-journal nav-icon"></i>  Historial de Arriendo</h2>
        <div class="history-section">
          <p>Vehículos Arrendados</p>
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



<script setup >
// Imports

import axios from 'axios'; // Importar axios
import { ref, onMounted } from 'vue'; // Importar ref y onMounted para la reactividad
import { userService } from '../services/userService';
// Variables reactivas

const usuario = ref('');

// Métodos

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

// Redireccionar a la página de inicio
const logout = () => {
  localStorage.clear();
  window.location.href = "/";
};
// Redireccionar a la página perfilCliente
const toPerfilCliente = () => window.location.href = "/perfilCliente";

// Redireccionar a la página del historial de arriendo
const toHistorialArriendo = () => window.location.href = "/historialArriendo";

// Redireccionar a la página de comprobantes de pago
const toComprobantesPago = () => window.location.href = "/comprobantesPago";

const editProfile = () => {
  // Lógica para editar el perfil
};

const editDocuments = () => {
  // Lógica para agregar documentos
};

const viewDetails = () => {
  // Lógica para ver detalles
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
  margin-top: 80px; /* Ajustar según la altura de la barra de navegación */
}


/* Contenedor principal */
.content-container {
  display: flex;
  flex-wrap: nowrap;
  justify-content: space-between;
  height: calc(100vh - 80px); /* Ajustar para que ocupe todo el espacio disponible debajo de la barra de navegación */
  margin: 0 auto;
}

/* Contenedor del perfil */
.profile-container {
  position: absolute; /* Para colocarla en una posición específica */ 
  top: 70px; /* Ajustar según la altura de la barra de navegación */ 
  left: 0; /* Para pegarla al borde izquierdo */ 
  width: 450px; /* Ajustar el ancho según sea necesario */ 
  height: calc(100vh - 70px); /* Ajustar para que ocupe todo el espacio disponible debajo de la barra de navegación */ 
  display: flex;
  flex-direction: column; 
  align-items: center; 
  justify-content: center; /* Centrar contenido verticalmente */ 
  padding: 0rem; 
  background-color: rgba(255, 255, 255, 0.8); 
  border-radius: 0; 
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}




.profile-img { 
  width: 150px; /* Ajustar el ancho de la imagen del perfil según sea necesario */ 
  margin-top: 5rem; /* Añadir margen inferior para separar la imagen de los botones */ 
} 
.nombre-cliente { 
    position: relative; /* Cambiar la posición a relativa */ 
    margin-top: 2rem; 
    margin-bottom: 3rem; 
    text-align: center; 
    font-size: 2.5rem; 
    font-weight: bold; 
    color: #633434; 
    } 
.button-container { 
    display: flex; 
    flex-direction: column; 
    width: 100%; /* Asegurar que los botones ocupen todo el ancho del contenedor */ 
    height: 100%;
    flex: 1;
    
} 
.button-container button { 
  background-color: #ff80ab; 
    color: #fff; 
    font-size: 100%; 
    font-weight: bold;
    border: none; 
    border-radius: 0px; 
    padding: 2.7rem; 
    margin: 0; /* Remove margins */
    cursor: pointer; 
    transition: background-color 0.3s ease;
    flex: 1; /* Make buttons equal height */
    display: flex;
    align-items: center;
    justify-content: center;
    
}

.button-container button:hover {
  background-color: #ff6f61;
}

/* Contenedor central para "Datos Cliente" */
.data-container {
  height: 40%;
  width: 70%;
  padding: 2rem;
  background-color: rgba(255, 255, 255, 0.8);
  margin-left: 35rem; /* Añadir margen izquierdo */
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
  height: 40%;
  width: 70%;
  padding: 2rem;
  background-color: rgba(255, 255, 255, 0.8);
  margin-top: 2rem;
  margin-left: 35rem; /* Añadir margen izquierdo */
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
  height: 77%;
  width: 20%;
  padding: 1.5rem;
  background-color: rgba(255, 255, 255, 0.8);
  margin-top: 0.1rem;
  margin-right: 6rem;
  margin-left: 80rem; /* Añadir margen izquierdo */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
.history-title{
  font-size: 200%;
  color: #633434;
  
  margin-bottom: 15%;    
}
.history-section {
  font-size: 130%;
  color: #555;
      
}
.history-section p {
  font-size: 100%;
  color: #333;
  font-weight: bold;
  margin-bottom: 2rem;    
}
.history-section li {
  font-size: 100%;
  color: #333;
  
  margin-bottom: 15%;    
}
/* Iconos */
.nav-icon {
  margin-right: 5px;
  font-size: 2rem;
  vertical-align: middle;
  }
.nav-icon.large {
  font-size: 1.3rem;
}

</style>
