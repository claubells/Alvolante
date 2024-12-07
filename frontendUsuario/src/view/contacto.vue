<script setup>
import { useRouter } from 'vue-router'; // Importar el router

const router = useRouter(); // Usar el router
import axios from 'axios';
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
    <div class="contacto_titulo">Contáctenos</div>
    <!-- Sección de contacto -->
    <section class="contact-section">
      <div class="contact-content">
        <img src="./media/soporte.png" alt="Soporte" class="support-img" /> <!-- Imagen de soporte -->
        <div class="contact-box">
          <img src="./media/logoAV.png" alt="Logo AV" class="logo-img" /> <!-- Imagen del logo -->
          
          <h2>Información de contacto:</h2>
          <p>Teléfono: +5693452342</p>
          <p>Email: soporte@alvolante.com</p>
          <p>Dirección: Aeropuerto Santiago,Chile</p>
          <img src="./media/contactoIconos.png" alt="Iconos" class="iconos-img" /> <!-- Imagen de iconos -->
        </div>
      </div>
    </section>
  </main>
</template>

<script>
export default {
  methods: {
    logout() {
      localStorage.removeItem("login"); // Limpia el almacenamiento local
      window.location.href = "/"; // Redirige al login
    },
    toContact(){
      this.$router.push('/contacto'); // Redirecciona a la vista de contacto
    },
    toInicio(){
      this.$router.push('/user'); // donde esta la vista inicio?????????
    },
    toPerfilCliente(){
      this.$router.push('/perfilCliente'); // Redireccina a perfil clinete
    }
  },
};

import axios from 'axios';

//rediccionamientos
//usuario
function redireccionarASubpaginaInicio(){
    window.location.href = '/home';

}

function redireccionarASubpaginaContacto(){
    window.location.href = '/Contacto';

} 
function redireccionarASubpaginaPerfilCliente(){
    window.location.href = '/perfilCliente';

}
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
.contacto_titulo {
  position: relative; /* Cambiar la posición a relativa */ 
  top: 100px; /* Ajustar la posición vertical hacia arriba */
  left: 100px;
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
  left: -300px; /* Ajustar la posición vertical hacia arriba */
  margin-right: 12rem; /* Ajustar el margen derecho según sea necesario */ 
  margin-left: -15rem; /* Ajustar el margen izquierdo para mover la imagen a la izquierda */
  margin-top: 3rem; /* Ajustar el margen superior para mover la imagen hacia arriba */
}

.contact-box {
  background: rgba(255, 255, 255, 0.8); /* Fondo blanco semitransparente */
  position: relative; /* Cambiar la posición a relativa */ 
  left: -250px; /* Ajustar la posición vertical hacia arriba */
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  margin-right: 1rem;
  margin-left: 1rem;
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
}

.contact-section h2 {
  font-size: 1,9rem;
  color: #ff6f61;
  margin-bottom: 1rem;
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