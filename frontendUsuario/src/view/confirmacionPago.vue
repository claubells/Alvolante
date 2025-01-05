<template>
  <div class="container">
    <!-- Barra lateral -->
    <div class="sidebar">
      <img class="image" src="./media/logoalvolante.png" alt="Logo" />
      <ul>
        <li><a href="#" @click="toInicio">Inicio</a></li>
        <li><a href="#" @click="cierreSesion">Cerrar sesión</a></li>
      </ul>
    </div>
    <div class="main-content">
    </div>
  </div>
</template>


<script setup>
// Imports
import { reactive } from 'vue';

// Variables reactivas
const formulario = reactive({
  nombre: '',
  apellido: '',
  rut: '',
  telefono: '',
  direccion: '',
  correo: ''
});

// Métodos
const confirmacionPago = () => window.location.href = "/confirmacionPago";
const cierreSesion = () => window.location.href = "/";
const toInicio = () => window.location.href = "/admin";
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
  margin-left: 150px;
  margin-top: 50px;
  padding: 20px;
  flex: 1;
}

h1 {
  font-size: 22px;
  margin-bottom: 20px;
}

/* Formulario */
.formulario {
  display: flex;
  flex-direction: column;
  gap: 15px;
  max-width: 400px;
  margin: 0 auto;
}

.form-group {
  display: flex;
  flex-direction: column;
}

label {
  margin-bottom: 5px;
  font-weight: bold;
}

input {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 16px;
}

button {
  background-color: #ff4081;
  color: white;
  font-size: 16px;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #ff80ab;
}
</style>