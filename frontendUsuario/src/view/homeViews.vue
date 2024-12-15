<script setup>
import Swal from 'sweetalert2';

</script>


<template>
    <main class="main-container">
      <div class="split-container">
        <!-- Mitad Izquierda -->
        <div class="left-container">
          <h1>Hola Viajero!</h1>
          <h2>¡Reserve ahora y prepárate para tu viaje!</h2>
        </div>
        
        <!-- Mitad Derecha -->
        <div class="right-container">
          <div class="content">
            <img class="image" src="./media/logoalvolante.png">
            <div class="header">INICIAR SESIÓN</div>
            <div class="headerDescription" v-if="!register">Complete los campos para iniciar sesión</div>
            <div class="headerDescription" v-else="!register">Complete los campos para registros</div>
            <div class="inputContainer" v-if="!register">
              <input type="email" v-model="username" placeholder="Ingrese correo">
              <input type="password" v-model="password" placeholder="Ingrese contraseña">
              <button class="sessionButton" @click="login">Iniciar sesión</button>
            </div>
            <div class="inputContainer" v-else>
              <input type="name" v-model="name" placeholder="Ingrese nombre y apellido">
              <input type="email" v-model="usernameRegister" placeholder="Ingrese correo">
              <input type="password" v-model="passwordRegister" placeholder="Ingrese contraseña">
              <input type="password" v-model="passwordRegisterConfirmation" placeholder="Repita contraseña">
              <h3 class="fechaText">Ingresa tu fecha de nacimiento:</h3>
              <input type="date" v-model="fechaDeNacimiento"/>
              <button class="sessionButton" @click="addUser">Registrar</button>
            </div>
            <div class="alsoButtons">
                <div class="headerCliente">¿No esta registrado?</div>
              <div class="alsoButton" @click="handleChange" v-if="!register">Cree una cuenta</div>
              <div class="alsoButton" @click="handleChange" v-else>Iniciar sesión</div>
              <router-link to="anonimo">
                <div class="alsoButton" @click="handleChange">Anónimo</div>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </main>
  </template>

<script>


import axios from 'axios';


function redireccionarASubpaginaUsuarioAnonimo(){
    window.location.href = '/anonimo';

} 

export default{
    data(){
        return{
            name: '',
            username: '',
            password: '',
            loggedIn: false,
            register: false,
            usernameRegister: '',
            passwordRegister: '',
            passwordRegisterConfirmation: '',
            register: this.$route.query.register === 'true'
        }

    },

    methods:{
        async login(){
            //envio de datos al backend
            const usuario = {
                "email":this.username,
                "password":this.password,
                "name":this.name,
                "fechaDeNacimiento":this.fechaDeNacimiento
                
            };
            try{
                const respuesta = await axios.post(
                  import.meta.env.VITE_BASE_URL + 'api/usuarios/login',
                   usuario)
                if(respuesta.data == 1){
                    return 0;
                }

                if(respuesta.data == 2){
                    return 0;
                }

                //tipo usuario
                if(respuesta.data == 3){
                    localStorage.setItem('login1', JSON.stringify(this.name));
                    localStorage.setItem('fechaLogin', JSON.stringify(this.fechaDeNacimiento));
                    localStorage.setItem('correoLogin', JSON.stringify(this.username));
                    window.location.href = '/user';
                }

                if(respuesta.data == 0){
                    Swal.fire({
                      title: '¡Cuidado!',
                      text: 'Credenciales invalidas!!',
                      icon: 'warning',
                      confirmButtonText: 'OK',
                      customClass: {
                        confirmButton: 'custom-confirm-button'
                      }
                    });
                }

                respuesta.data = 0;
                console.log(respuesta.data)

            }catch(error){
              Swal.fire({
                      title: '¡Cuidado!',
                      text: 'No se pudo generar conexion con el servidor',
                      icon: 'warning',
                      confirmButtonText: 'OK',
                      customClass: {
                        confirmButton: 'custom-confirm-button'
                      }
                    });

            }
        },

        //permite cambiar entre registro y no registro
        handleChange(){
            this.register = !this.register
            console.log(this.register)
        },

        //Ingresa al usuario de forma anonima
        anonimo(){
            this.username = 'anonimo'
            redireccionarASubpaginaUsuarioAnonimo();
            localStorage.setItem('login', JSON.stringify(this.username));
        },

        async addUser(){
            //envio de datos al backend
            if(this.passwordRegister == this.passwordRegisterConfirmation){
                if(this.usernameRegister != ' ' & this.passwordRegister != ' '){
                    const nuevo_usuario = {
                        'email': this.usernameRegister,
                        'password': this.passwordRegister,
                        'name': this.name,
                        'fechaDeNacimiento': this.fechaDeNacimiento,
                        'rol': 'usuario'
                    }
                    try{
                        const registro = await axios.post(import.meta.env.VITE_BASE_URL + 'api/usuarios/register', nuevo_usuario);
                        console.log(registro)
                        Swal.fire({
                      title: '¡Excelente!',
                      text: 'Usuario creado con exito',
                      icon: 'warning',
                      confirmButtonText: 'OK',
                      customClass: {
                        confirmButton: 'custom-confirm-button'
                      }
                    });

                    }catch(error){
                      Swal.fire({
                      title: '¡Cuidado!',
                      text: 'No se pudo registrar',
                      icon: 'warning',
                      confirmButtonText: 'OK',
                      customClass: {
                        confirmButton: 'custom-confirm-button'
                      }
                    });

                    }
                }
                else{
                  Swal.fire({
                      title: '¡Cuidado!',
                      text: 'No se ingresaron datos',
                      icon: 'warning',
                      confirmButtonText: 'OK',
                      customClass: {
                        confirmButton: 'custom-confirm-button'
                      }
                    });
                }
            }
            else{
              Swal.fire({
                      title: '¡Cuidado!',
                      text: 'Las contraseñas no son iguales',
                      icon: 'warning',
                      confirmButtonText: 'OK',
                      customClass: {
                        confirmButton: 'custom-confirm-button'
                      }
                    });
            }
        }



    }
}
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
  align-items: center;
  background-color: #ff80ab; /* Color de fondo para la mitad izquierda */
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

.fechaText{
  margin-left: -75%;
  font-size: 100%;
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
  justify-content: center;
  align-items: center;
  background-color: #ffffff; /* Color de fondo para la mitad derecha */
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

.sessionButton:hover {
  background: #ff4081;
}

/* Botones alternativos */
.alsoButtons {
  margin-top: 1.5rem;
}

.alsoButton {
  color: #ff4081;
  font-weight: bold;
  margin-top: 1rem;
  cursor: pointer;
  text-decoration: underline;
  font-size: 0.9rem;
}

.alsoButton:hover {
  color: #ff6f61;
  text-decoration: none;
}

.custom-confirm-button {
  background-color: #ff80ab !important; 
  color: white !important;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 1rem;
}

.custom-confirm-button:hover {
  background-color: #ff80ab !important; 
}

</style>