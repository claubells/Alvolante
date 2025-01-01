<template>
  <div class="container">
    <div class="sidebar">
      <img class="image" src="./media/logoalvolante.png" alt="Logo" />
      <ul>
        <li><a href="#" @click="toInicio">Inicio</a></li>
        <li><a href="#" @click="cierreSesion">Cerrar sesión</a></li>
      </ul>
    </div>
    <div class="main-content">
      <h1 class="titulo-form">Formulario de arriendo</h1>
      <form @submit.prevent="enviarFormulario" class="formulario">
        <div class="form-group">
          <label for="nombreCliente">Nombre y apellido:</label>
          <input type="text" id="nombreCliente" v-model="formulario.nombreCliente" required />
        </div>     

        <div class="form-group">
          <label for="rutCliente">RUT:</label>
          <input type="text" id="rutCliente" v-model="formulario.rutCliente" required />
        </div>

        <div class="form-group">
          <label for="direccionEmisor">Direccion de sucursal:</label>
          <input type="text" id="direccionEmisor" v-model="formulario.direccionEmisor" required />
        </div>

        <div class="form-group">
          <label for="telefono">Número de Teléfono:</label>
          <input type="tel" id="telefono" v-model="formulario.telefono" required />
        </div>

        <div class="form-group">
          <label for="direccion">Dirección:</label>
          <input type="text" id="direccion" v-model="formulario.direccion" required />
        </div>

        <div class="form-group">
          <label for="correo">Correo Electrónico:</label>
          <input type="email" id="correo" v-model="formulario.correo" required />
        </div>

        <button type="submit" @click= "confirmacionPago" class="action-btn">Enviar</button>
        <button type="submit" @click= "verBoleta(formulario.nombreCliente)" class="action-btn">Ver boleta</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      formulario: {
        nombreEmisor: 'AlVolante',
        rutEmisor: '677777',
        direccionEmisor: '',
        nombreCliente: '',
        rutCliente: '',
        telefono: '',
        direccion: '',
        total: '', 
        correo: ''
      }
    };
  },
  methods: {
    cierreSesion() {
      window.location.href = "/";
    },
    toInicio() {
      window.location.href = "/admin";
    },
    async confirmacionPago() {
        localStorage.setItem('formNombre', JSON.stringify(this.nombre));
        localStorage.setItem('formApellido', JSON.stringify(this.apellido));
        localStorage.setItem('formRut', JSON.stringify(this.rut));
        localStorage.setItem('formTelefono', JSON.stringify(this.telefono));
        localStorage.setItem('formDireccion', JSON.stringify(this.direccion));
        localStorage.setItem('formCorreo', JSON.stringify(this.correo));
        try {
        // agrupa los datos y los manda al backend
        const response = await axios.post(
          import.meta.env.VITE_BASE_URL + 'api/boleta/generarBoleta',
          this.formulario)
          alert('se creo');
        } catch (error) {      
        }
      },   

      verBoleta() {
  this.$router.push({ name: 'verBoleta', params: { nombreCliente: this.formulario.nombreCliente } });
}
    },

    mounted(){
      this.total = localStorage.getItem('total');
    },
  }

</script>

<style>
body {
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
  color: #000;
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}

.titulo-form{
    margin-left: 38%;
    font-weight: bold;
  }

.container {
  display: flex;
  min-height: 100vh;
}

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