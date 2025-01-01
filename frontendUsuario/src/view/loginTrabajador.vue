<template>
  <main class="main-container">
    <div class="moving-gradient">
      <div class="split-container">

          <!-- Mitad Izquierda -->
          <div class="left-container">
            <h1><br>¡Hola<br>Trabajador!</h1>
          </div>
          
          <!-- Mitad Derecha -->
          <div class="right-container">
            <div class="content">
              <img class="image" src="./media/logoalvolante.png">
              <div class="header">INICIAR SESIÓN COMO TRABAJADOR</div>
              <div class="headerDescription" >Complete los campos para iniciar sesión</div>

              <!-- Contenedor de inputs -->
              <div class="inputContainer">
                <input type="email" v-model="username" placeholder="Ingrese correo">
                <input type="password" v-model="password" placeholder="Ingrese contraseña">
                <button class="sessionButton" @click="loginTrabajador">Iniciar sesión</button>
              </div>
            
              <!-- Botones adicionales -->
              <div class="alsoButtons">
                <div class="headerCliente">¿Usted no es trabajador?</div>
                <router-link to="/inicio" class="text-link">Volver al inicio</router-link>
              </div>
            </div>
          </div>
      </div>
    </div>
  </main>
</template>
  

<script setup>

// Imports
import Swal from 'sweetalert2';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { loginService } from '../services/authService.js';
import { ref } from 'vue';

//Variables reactivas
const username = ref(''); // email
const password = ref('');
const router = useRouter(); // Router

// Métodos
const handleChange = () => {
  register.value = !register.value; 
  console.log(register.value);
};

// Ingresar al usuario de forma anónima
const anonimo = () => {
  username.value = 'anonimo'; 
  redireccionarASubpaginaUsuarioAnonimo();
  localStorage.setItem('login', JSON.stringify(username.value));
};

// Redireccionar a la página de inicio
const toInicio = () => {
  window.location.href = "/inicio";
};

const loginTrabajador = async () => {

  try{
    // llamada al servicio de login
    const response = await loginService.login(username.value, password.value);

    // Verificar el rol del usuario
    const { role } = response;
    if (role === 'ADMIN' || role == 'TRABAJADOR') { // Si el rol es ADMIN

      console.log('Sesión iniciada', response);
      Swal.fire({
        title: '¡Éxito!',
        text: '¡Sesión iniciada exitosamente!',
        icon: 'success',
        confirmButtonText: 'OK',
        customClass: {
        confirmButton: 'custom-confirm-button'
        }});

      // Se redirige a la pagina del trabajador
      router.push('/trabajador');

    }else if (role === 'CLIENTE') { // Si el rol es CLIENTE
      console.log('usuario con CLIENTE, redirigiendo a /login', response);
      await Swal.fire({
        title: '¡Error!',
        text: 'Estas intentando acceder a una sección no permitida para tu rol.\nRedirigiendo...',
        icon: 'warning',
        confirmButtonText: 'OK',
        customClass: {
        confirmButton: 'custom-confirm-button'
      }});
      router.push('/login'); // Redirigir al dashboard del login
    } else {
      console.log('Rol del usuario: ', role);
      // Si el rol no es válido, muestra un SweetAlert
      await Swal.fire({
        title: '¡Acceso Denegado!',
        text: 'No tienes permisos para acceder a esta sección.',
        icon: 'error',
        confirmButtonText: 'OK',
      });
    }
  }catch(error){
    if (error.response?.status === 401) {
      await Swal.fire({
        title: "¡Error!",
        text: "Credenciales incorrectas. Verifique su correo y contraseña.",
        icon: "error",
        confirmButtonText: "OK",
      });
    } else {
      await Swal.fire({
        title: "¡Error!",
        text: "Ocurrió un error inesperado. Intente nuevamente más tarde.",
        icon: "error",
        confirmButtonText: "OK",
      });
    }

    }
};
</script>

<style >

/* Estilos generales */
body {
    margin: 0;
    font-family: "Arial", sans-serif;
    background: linear-gradient(135deg, #f8bbd0, #fce4ec);
}

.main-container {
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

/* Contenedor dividido */
.split-container {
    position:absolute;
    display: flex;
    flex:1;
    width: 100%;
    height: 100%;
}

/* Mitad izquierda */
.left-container {
  height: 100%;
  flex: 1;
  display: flex;
  justify-content: center;
  /*align-items: center;*/
  align-items: flex-start; /* Asegura que el contenido esté alineado a la izquierda */
  padding: 2rem; /* Ajusta el espacio interno si es necesario */
  background-color: transparent; /* Color de fondo para la mitad izquierda */
}

.left-container h1 {
  font-size: 900%;
  color: #FF69B4;
  text-align:left;
  position: absolute;
  margin-left: 1%;
  margin-bottom: 14%;
}

.left-container h2 {
  font-size: 300%;
  font-style: italic;
  color: #ffffff;
  text-align:bottom;
  position: absolute;
  margin-left: 1%;
  margin-top: 40%;
}

.headerCliente{
  color: #ff4081;
  margin-top: 1rem;
  font-size: 0.9rem;    
}

/* Mitad derecha */
.right-container {
  height: 100%;
  flex: 1;
  display: flex;
  justify-content: center; /* Centra verticalmente los elementos */
  align-items: center; /* Alinea horizontalmente los elementos */
  background-color: transparent; /* Color de fondo para la mitad derecha */
  padding: 2rem; /* Ajusta el espacio interno del contenedor */
}

.content {
    height: 100%;
    background: #ffffff;
    padding: 2rem;
    border-radius: 12px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
    width: 100%;
}

/* Imagen */
.image {
    max-width: 100px;
    margin: 5rem auto 1rem;
}

/* Encabezado */
.header {
    font-size: 1.5rem;
    font-weight: bold;
    margin-bottom: 0.5rem;
    color: #ff4081;
}

.headerDescription {
    font-size: 1rem;
    color: #ff80ab;
    margin-bottom: 1.5rem;
}

/* Inputs y botones */
.inputContainer {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}
input {
    padding: 0.75rem;
    border: 1px solid #ffd54f;
    border-radius: 8px;
    font-size: 1rem;
}

input:focus {
    outline: none;
    border-color: #ffc107;
    box-shadow: 0 0 0 2px rgba(255, 193, 7, 0.2);
}

.sessionButton {
    background: #ff80ab;
    color: #ffffff;
    border: none;
    padding: 0.75rem;
    border-radius: 8px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

/* Botones alternativos */
.alsoButtons {
    margin-top: 20px;
    display: flex;
    flex-direction: column; /* Organiza los elementos en columnas */
    align-items: center; /* Centra los elementos */
    gap: 10px; /* Espaciado uniforme entre los enlaces */
}

.custom-confirm-button {
    background-color: #ff80ab !important; 
    color: white !important;
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    font-size: 1rem;
}

.sessionButton:hover {
  background: #ff4081;
}

.custom-confirm-button:hover {
  background-color: #ff80ab !important; 
}

@keyframes gradient {
  0% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
  100% { background-position: 0% 50%; }
}

.moving-gradient {
  width: 100%; /* O el tamaño del elemento */
  height: 100vh; /* O el tamaño deseado */
  background: radial-gradient(circle, #faf8b4, #f8bbd0, #ecf48f,#f8bbd0, #faff9a);
  background-size: 400% 400%;
  animation: gradient 15s ease infinite;
}

</style>