<template>
    <main class="main-container">
        <div class="moving-gradient">
            <div class="split-container">

                <!-- Mitad Izquierda -->
                <div class="left-container">
                    <h1>¡Hola Viajero!</h1>
                    <h2>¡Reserve ahora y prepárate para tu viaje!</h2>
                </div>
                
                <!-- Mitad Derecha -->
                <div class="right-container">
                    <div class="content">
                        <img class="image" src="./media/logoalvolante.png">
                        <div class="header">Crear una cuenta</div>
                        <div class="headerDescription" >Complete los campos para el registro</div>

                        <!-- Contenedor de inputs -->
                        <div class="inputContainer">
                            <input type="name" v-model="nameParam" placeholder="Ingrese nombre y apellido">
                            <input type="email" v-model="email" placeholder="Ingrese correo">
                            <input type="password" v-model="password" placeholder="Ingrese contraseña">
                            <input type="password" v-model="confirmPassword" placeholder="Repita contraseña">
                            <label class="fechaText">Ingrese su fecha de nacimiento:</label>
                            <input type="date" v-model="birthdate"/>
                            <button class="sessionButton" @click="addUser">Registrarse</button>
                        </div>

                        <!-- Botones adicionales -->
                        <div class="alsoButtons">
                            <div class="headerCliente">¿No estás registrado?</div>
                            <!-- Enlaces adicionales -->
                            <button @click="goToAnon"class="text-link">Ingresar como Anónimo</button>
                            <button @click="goToLogin"class="text-link">Ir al Login</button>
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
import { registerService } from '../services/authService.js';
import { ref } from 'vue';

//Variables reactivas
const nameParam = ref('');
const email = ref('');
const password = ref('');
const confirmPassword = ref('');
const birthdate = ref('');

// Métodos
const goToLogin = () => window.location.href = '/login';
const goToIntranet = () => window.location.href = '/inicio';
const goToAnon = () => window.location.href = '/anonimo';

const addUser = async () => {

    // Validar campos
    if (!nameParam.value || !email.value || !password.value || !confirmPassword.value || !birthdate.value) {
        await Swal.fire({
            title: '¡Error!',
            text: 'Por favor complete todos los campos.',
            icon: 'warning',
            confirmButtonText: 'OK',
            customClass: {
                confirmButton: 'custom-confirm-button'
            }
        });
        return;
    }

    // Validar que las contraseñas sean iguales
    if(password.value !== confirmPassword.value){
        await Swal.fire({
            title: '¡Error!',
            text: 'Las contraseñas no son iguales.\nPor favor intente nuevamente.',
            icon: 'warning',
            confirmButtonText: 'OK',
            customClass: {
                confirmButton: 'custom-confirm-button'
            }
        });
        return;
    }

    try{
        // llamada al servicio de registro
        const response = await registerService.register(
            nameParam.value,
            email.value,
            password.value,
            birthdate.value
        );

        console.log('Usuario registrado: ', response);

        await Swal.fire({
            title: '¡Excelente!',
            text: 'Usuario creado con exito',
            icon: 'success',
            confirmButtonText: 'OK',
            customClass: {
            confirmButton: 'custom-confirm-button'
            
        }});

        window.location.href = '/login'; //lo redirige al login

    }catch(error){

        Swal.fire({
        title: '¡Error!',
        text: error.message, // Usa el mensaje directamente del servicio
        icon: 'warning',
        confirmButtonText: 'OK',
        customClass: {
            confirmButton: 'custom-confirm-button'
        }});
        console.error('Error al registrar el usuario:', error);
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
}

.main-container {
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 104%;
    height: 103%;
    margin-left: -2%;
    margin-top: -0.9%;
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
  flex: 1;
  display: flex;
  height: 100%; /* Ocupa toda la altura disponible */
  justify-content: top; /* Alinea todo hacia la parte superior *
  align-items: center; /* Alinea horizontalmente los elementos */
  background-color: transparent; /* Color de fondo para la mitad derecha */
  padding: 2rem; /* Ajusta el espacio interno del contenedor */
  overflow: hidden; /* Evita desbordamientos */
}

.content {
    height: 100%;
    justify-content: top; 
    background: #ffffff;
    padding: 0rem 3rem;
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

.fechaText{
    text-align: left; /* Alinea el texto a la izquierda */
    display: block;
    margin: 0;
    font-size: 100%;
}

@keyframes gradient {
  0% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
  100% { background-position: 0% 50%; }
}

.moving-gradient {
  width: 100%; /* O el tamaño del elemento */
  height: 100vh; /* O el tamaño deseado */
  background: radial-gradient(circle, #faf8b4, #f8bbd0, #b1f48f, #ff80ab, #ebeb9a, #9bea5f, #e05986);
  background-size: 400% 400%;
  animation: gradient 15s ease infinite;
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

</style>