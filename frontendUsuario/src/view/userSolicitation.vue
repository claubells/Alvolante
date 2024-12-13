<script setup>
import { ref } from "vue";

// Puedes usar variables para datos dinámicos.
const userName = ref("Usuario");
</script>

<template>
  <main class="main-container">
    <!-- Barra de navegación -->
    <nav class="navbar">
      <div class="logo">Al Volante</div>
      <div class="nav-links">
        <a href="#">Inicio</a>
        <a href="#" @click="toPerfilCliente">Perfil</a>
        <a href="#">Opciones</a>
        <a href="#" @click="toContact">Contacto</a>
        <a href="#" @click="logout">Cerrar Sesión</a>
      </div>
    </nav>

    <section class="welcome-section">
      <div class="welcome-text">
        <h1>¡Bienvenido, {{ userName }}!</h1>
        <p>Explora nuestras funcionalidades y descubre cómo podemos ayudarte.</p>
      </div>
      <div class="welcome-image">
        <img src="./media/logoAV.png" alt="Al Volante" class="al-volante-img" />
      </div>
    </section>

    <!-- Secciones adicionales -->
    <section class="content-section">
      <div class="card">
        <h2>Arriendo</h2>
        <p>¡Entra ya y arriendaaa ! meo korte</p>
        <button class="card-button" @click.prevent="toCalendario">Ir</button>
      </div>

    </section>
  </main>
</template>

<script>
import axios from 'axios';

export default {
  methods: {
    logout() {
      localStorage.removeItem("login"); // Limpia el almacenamiento local
      window.location.href = "/"; // Redirige al login
    },
    toContact() {
      window.location.href = "/contacto";
    },
    toCalendario(){
      window.location.href = "/calendarioCliente"; 
      
    },
    toPerfilCliente(){
      window.location.href = "/perfilCliente";
    },
  },
};
</script>

<style>
body {
  margin: 0;
  font-family: "Arial", sans-serif;
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
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
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 1000;
  
}

.logo {
  font-size: 1.5rem;
  font-weight: bold;
}

.nav-links {
  display: flex;
  gap: 1.5rem;
}

.nav-links a {
  color: #fff;
  text-decoration: none;
  font-size: 1rem;
  transition: color 0.3s ease;
}

.nav-links a:hover {
  color: #ffd1dc;
}


.welcome-section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 2rem;
  margin-top: -100px;
  margin-left: 0px;
  height: 1200px;
  
}

.welcome-text {
  max-width: 50%;
  
}

.welcome-text h1 {
  font-size: 2rem;
  color: #ff4081;
  margin-top: -360px;
  
}

.welcome-text p {
  font-size: 1.2rem;
  color: #ff6f61;
}

.welcome-image {
  max-width: 40%;
}

.al-volante-img {
  width: 100%;
  height: auto;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  
}

/* Sección de tarjetas */
.content-section {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  gap: 1.5rem;
  margin-top: 2rem;
}

.card {
  background: #fff3e0;
  padding: 1.5rem;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 450px;
  height: 230px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  margin-top: -800px;
  margin-left: -590px;
  border: 2px solid black;
  
}

.card h2 {
  font-size: 1.5rem;
  color: #ff4081;
  margin-bottom: 1rem;
}

.card p {
  font-size: 1rem;
  color: #757575;
  margin-bottom: 1.5rem;
}

.card-button {
  background: #ff80ab;
  color: #fff;
  border: none;
  padding: 0.75rem 1rem;
  border-radius: 8px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width:  400px;
  height: 60px;
}

.card-button:hover {
  background: #ff4081;
}

.card:hover {
  transform: translateY(-10px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}
</style>
