<template>
  <main class="main-container"> 
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
        <h2>Reserva</h2>
        <p>¡Entra ya y reserva!</p>
        <button class="card-button" @click.prevent="toCalendario">Ir</button>
      </div>
    </section>

  </main>
</template>

<script setup>
// Imports
import { ref } from "vue";
import axios from 'axios';

// Variables reactivas
const userName = ref("Usuario"); // Puedes usar variables para datos dinámicos.

// Métodos
const toCalendario = () => window.location.href = "/calendarioCliente";

//creo que estos los podemos sacar nomas, ya que funciona con la barra de arriba
const toContact = () => window.location.href = "/contacto";
const toPerfilCliente = () => window.location.href = "/perfilCliente";
const logout = () => {
  localStorage.removeItem("login"); // Limpia el almacenamiento local
  window.location.href = "/"; // Redirige al login
};
</script>

<style>
body {
  margin: 0;
  font-family: "Arial", sans-serif;
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
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
  height: 220px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  margin-top: -45%;
  margin-left: -40%;
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
