<template>
    <main class="main-container">
        <div class="moving-gradient">
            <div class="split-container">

                <!-- Mitad Izquierda -->
                <div class="left-container">
                    <h1>¡Hola de</br> nuevo!</h1>
                    <h2>¡Reserva ahora y prepárate para tu viaje!</h2>
                </div>
                
                <!-- Mitad Derecha -->
                <div class="right-container">
                    <div class="content">
                        <img class="image" src="./media/logoalvolante.png">
                        <div class="header">INICIAR SESIÓN</div>
                        <div class="headerDescription">Complete los campos para iniciar sesión</div>
                        
                        <!-- Contenedor de inputs -->
                        <div class="inputContainer">
                            <input type="email" v-model="username" placeholder="Ingrese correo">
                            <input type="password" v-model="password" placeholder="Ingrese contraseña">
                            <button class="sessionButton" @click="login">Iniciar sesión <i class="bi bi-box-arrow-in-right nav-icon"></i></button>
                        </div>

                        <!-- Botones adicionales -->
                        <div class="alsoButtons">
                            <div class="headerCliente">¿No estás registrado?</div>
                            <!-- Enlaces adicionales -->
                            <button @click="goToAnon"class="text-link">Ingresar como Anónimo</button>
                            <button @click="goToRegister"class="text-link">Registrarse</button>
                            <button @click="goToIntranet"class="text-link">Volver al incio</button>
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
import { loginService } from '../services/authService.js';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

//Variables reactivas
const username = ref(''); // email
const password = ref('');
const role = ref('');
const router = useRouter(); // Router

// Métodos
const goToRegister = () => window.location.href = '/register';
const goToIntranet = () => window.location.href = '/inicio';
const goToAnon = () => window.location.href = '/anonimo';

const login = async () => {
    // Validar campos
    if (!username.value || !password.value) {
        Swal.fire({
            title: '¡Error!',
            text: 'Por favor, complete todos los campos.',
            icon: 'warning',
            confirmButtonText: 'OK',
            customClass: {
                confirmButton: 'custom-confirm-button'
            }
        });
        return;
    }

    try{
        
        // llamada al servicio de login
        const response = await loginService.login(username.value, password.value);

        // Verificar el rol del usuario
        const { role } = response;
        if (role === 'CLIENTE') { // Si el rol es CLIENTE

            console.log('Sesión iniciada', response);
            Swal.fire({
                title: '¡Éxito!',
                text: '¡Sesión iniciada exitosamente!',
                icon: 'success',
                confirmButtonText: 'OK',
                customClass: {
                confirmButton: 'custom-confirm-button'
                }});

            // Se redirige a la pagina del usuario
            router.push('/user');
        }
        else if (role === 'ADMIN' || role == 'TRABAJADOR') { // Si el rol es ADMIN

            console.log('usuario con rol ADMIN o TRABAJADOR, redirigiendo a /loginTrabajador', response);
            Swal.fire({
                title: '¡Error!',
                text: 'Estas intentando acceder a una sección no permitida para tu rol.\nRedirigiendo...',
                icon: 'warning',
                confirmButtonText: 'OK',
                customClass: {
                confirmButton: 'custom-confirm-button'
            }});
            router.push('/loginTrabajador'); // Redirigir al dashboard del trabajador
        } else {
            console.log('Rol del usuario: ', role);
            // Si el rol no es válido, muestra un SweetAlert
            Swal.fire({
                title: '¡Acceso Denegado!',
                text: 'No tienes permisos para acceder a esta sección.',
                icon: 'error',
                confirmButtonText: 'OK',
            });
        }

    } catch (error){

        Swal.fire({
        title: '¡Error!',
        text: error.message, // Usa el mensaje directamente del servicio
        icon: 'warning',
        confirmButtonText: 'OK',
        customClass: {
            confirmButton: 'custom-confirm-button'
        }});
        console.error('Error al iniciar sesión:', error);
        return;
    }
                

};

</script>

<style>
/* Estilos generales */
body {
    margin: 0;
    font-family: "Arial", sans-serif;
    background: linear-gradient(135deg, #f8bbd0, #fce4ec);
    overflow: hidden;
}

.main-container {
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 104%;
    height: 103%;
    margin-left: -2%;
    margin-top: 5.1%;
    
}

body.scrollable {
    overflow-y: hidden; /* Habilitar scroll en las vistas necesarias */
  }

/* Contenedor dividido */
.split-container {
    display: flex;
    width: 100%;
    height: 100%;
}

/* Mitad izquierda */
.left-container {
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
    /*align-items: center;*/
    align-items: flex-start; /* Asegura que el contenido esté alineado a la izquierda */
    padding: 2rem; /* Ajusta el espacio interno si es necesario */
    
}

.left-container h1 {
    font-size: 600%;
    color: #ffffff;
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
  background: radial-gradient(circle, #faf8b4, #f8bbd0, #f48fdc, #ff80ab, #f8bbd0, #faff9a, #ff4081);
  background-size: 500% 500%;
  animation: gradient 15s ease infinite;
  margin-top: -10%;
  
}

.text-link {
  color: blue;
  text-decoration: underline;
  background: none;
  border: none;
  cursor: pointer;
  font: inherit;
}

.text-link:hover {
  color: darkblue; /* Cambia el color al pasar el mouse */
  text-decoration: none; /* Opcional: elimina el subrayado */
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