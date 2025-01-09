<template>
    <div class="container">
      <!-- Barra lateral -->
      <div class="sidebar">
        <img class="image" src="./media/logoalvolante.png" alt="Logo" />
        <ul>
          <li><a href="#" @click="toInicio"><i class="bi bi-house-door nav-icon"></i> Inicio</a></li>
          <li><a href="#" @click="cierreSesion"><i class="bi bi-box-arrow-right nav-icon"></i> Cerrar sesión</a></li>
        </ul>
      </div>
      <!-- Contenido principal -->
      <div class="main-content">
        
        <div class="form-container">
          <h2 class="titulo-form">Formulario de Arriendo</h2>
          <!-- formulario -->
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
  
          <!-- Botones de acción -->
          <button type="submit" @click= "enviarFormulario" class="action-btn">Enviar</button>
          <button type="submit" @click= "verBoleta(formulario.nombreCliente)" class="action-btn">Ver boletas</button>
        </form>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  // Imports
  import Swal from 'sweetalert2';
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  import { useRouter } from 'vue-router';
  
  // Variables reactivas
  const router = useRouter();
  const formulario = ref({
    nombreCliente: '',
    rutCliente: '',
    direccionEmisor: '',
    fechaEmision: '',
    montoTotal: '',
    correo: '',
    nombreEmisor: 'Al volante', 
    rutEmisor: '76.123.456-7',
  });
  
  // Métodos
  const cierreSesion = () => {
    window.location.href = "/";
  };
  
  const toInicio = () => {
    window.location.href = "/admin";
  };
  
  const enviarFormulario = async () => {
    try {
      const token = localStorage.getItem("jwtToken"); // Obtén el token del almacenamiento local
      const response = await axios.post('http://localhost:8080/api/boleta/generarBoleta', formulario.value, {
        headers: {
          Authorization: `Bearer ${token}`, // Añade el token al encabezado
          'Content-Type': 'application/json'
        }
      });
      Swal.fire({
          title: '¡Éxito!',
          text: '¡Boleta creada exitosamente!',
          icon: 'success',
          confirmButtonText: 'OK',
          customClass: {
          confirmButton: 'custom-confirm-button'
          }});
    } catch (error) {
      console.error("Error al generar la boleta:", error);
      await Swal.fire({
          title: '¡Error!',
          text: 'Nose pudo generar Boleta\n',
          icon: 'warning',
          confirmButtonText: 'OK',
          customClass: {
          confirmButton: 'custom-confirm-button'
        }});
    }
  };
  
  const verBoleta = () => {
    router.push({ name: 'verBoleta', params: { nombreCliente: formulario.value.nombreCliente } });
  };
  
  onMounted(() => {
    const vehiculoCosto = localStorage.getItem("vehiculoCosto");
    if (vehiculoCosto) {
      formulario.value.montoTotal = parseFloat(vehiculoCosto); // Asigna el costo del vehículo desde localStorage
    }
  });
  </script>
  
  <style>
  /* Estilo general */
  body {
    background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
    color: #000;
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
  }
  
  .titulo-form{
      margin-left: 25%;
      font-weight: bold;
    }
  
  .container {
    display: flex;
    min-height: 100vh;
  }
  
  .form-container {
    background-color: #ffffff; /* White background */
    border-radius: 10px;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin: 20px;
    width: 80%;
    max-width: 800px;
  }
  
  /* If you want to target specific form elements */
  .form-control {
    background-color: #faf0f0; /* Light gray for input fields */
    border: 1px solid #ddd;
  }
  
  /* Container for the entire content */
  .main-content {
    flex: 1;
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
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