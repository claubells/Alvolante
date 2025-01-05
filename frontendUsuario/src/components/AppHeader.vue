<template>
    <header class="app-header">
      <!-- Contenedor para el título y el logo -->
      <router-link to="/user" class="logo-container">
        <!-- Título a la izquierda -->
        <h1 class="app-title">Al Volante</h1>
        <!-- Logo de la aplicación -->
        <img src="../view/media/logoRecortado.png" alt="Logo" class="logo-image" />
      </router-link>
    
      <!-- Enlaces de navegación -->
      <nav class="nav-links">
        <button @click="User" class="nav-link">
          <i class="bi bi-house-door nav-icon"></i> Inicio
        </button>
        <button @click="PerfilCliente" class="nav-link">
          <i class="bi bi-person nav-icon"></i> Ver Perfil
        </button>
        <button @click="Contacto" class="nav-link">
          <i class="bi bi-envelope nav-icon"></i> Contacto
        </button>
        <!-- Botón de cerrar sesión -->
        <button class="logout-button" @click="logout">
        <i class="bi bi-box-arrow-right nav-icon"></i> Cerrar sesión
      </button>
      </nav>
    </header>
  </template>
  
  <script>
import Contacto from '@/view/contacto.vue';
import PerfilCliente from '@/view/perfilCliente.vue';
import 'bootstrap-icons/font/bootstrap-icons.css'

  export default {
    name: "AppHeader", // Nombre del componente
    methods: {
      // Método para cerrar sesión
      logout() {
        // Eliminamos el token de autenticación y datos del localStorage
        localStorage.removeItem("jwtToken");
        localStorage.removeItem("userId");

        // Redirigimos al usuario a la página de inicio
        localStorage.removeItem("login"); // Limpia el almacenamiento local
        window.location.href = "/"; // Redirige al login
      },
      // Método para redirigir a la página de contacto
      Contacto() {
        window.location.href = "/contacto";
      },
      PerfilCliente() {
        window.location.href = "/perfilCliente";
      },
      User() {
        window.location.href = "/user";
      },
    },
  };
  </script>
  
  <style scoped>
  /* Estilos específicos del encabezado */
  .app-header {
    display: flex;
    align-items: center; /* Centra verticalmente los elementos */
    justify-content: space-between;
    padding: 0.5rem 2rem;
    background-color: #ff80ab;
    color: white;
    width: 100%; /* Ocupa todo el ancho */
    white-space: nowrap; /* Evita que el texto se divida en líneas múltiples */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }

  /* Contenedor del título y logo */
  .logo-container {
    display: flex;
    align-items: center;
    text-decoration: none; /* Evita que el título tenga subrayado */
    color: white;
    cursor: pointer;
    gap: 1rem; /* Espaciado entre el título y el logo */
  }

  /* Estilo del título */
  .app-title {
    font-size: 1.5rem;
    font-weight: bold;
    align-items: center;
    margin: 0;
    color: white;
  }

  /* Estilo del logo */
  .logo-image {
    height: 50px;
    width: auto;
  }
  
  /* Contenedor de enlaces */
  .nav-links {
    display: flex;
    gap: 2rem; /* Espaciado entre enlaces */
    align-items: center; /* Centra verticalmente los enlaces */
  }

  .nav-link {
    text-decoration: none;
    color: white;
    font-weight: bold;
    align-items: center;
    transition: color 0.3s ease;
  }
  
  .logout-button {
    background: #d81b60;
    color: white;
    border: none;
    padding: 0.5rem 1rem;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  .logout-button:hover {
    background-color: #ad1457;
  }
  

  .nav-link:hover {
    color: #d81b60; /* Cambia el color al pasar el cursor */
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
  