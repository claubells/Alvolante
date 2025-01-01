<script setup>
import Swal from 'sweetalert2';

</script>
<template>
    <div class="container">
      <div class="sidebar">
        <ul>
          <img class="image" src="./media/logoalvolante.png">
          <li><a href="#" @click="toInicio">Inicio</a></li>
          <li><a class="botonSubir" href="#" @click="AgregarVehiculoAdmin">Subir Vehículo</a></li>
          <li><a href="#" @click="cierreSesion">Cerrar sesión</a></li>
        </ul>
      </div>
      <div class="main-content">
        <h1>Lista de Vehículos</h1>
        <div class="vehiculos-container" v-if="vehiculos.length">
          <div v-for="vehiculo in vehiculos" :key="vehiculo.idVehiculo" class="vehiculo-card">
            <img
              v-if="vehiculo.fotoVehiculo"
              :src="'data:image/jpeg;base64,' + vehiculo.fotoVehiculo"
              alt="Foto del Vehículo"
              class="vehiculo-imagen"
            />
            <p><strong>Patente:</strong> {{ vehiculo.patente }}</p>
            <p><strong>Modelo:</strong> {{ vehiculo.modelo }}</p>
            <p><strong>Marca:</strong> {{ vehiculo.marca }}</p>
            <p><strong>Año:</strong> {{ vehiculo.anio }}</p>
            
          </div>
        </div>
        <p v-else>No hay vehículos disponibles.</p>
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        vehiculos: [], // Lista para almacenar los vehículos
      };
    },
    methods: {
      async fetchVehiculosDispo() {
        try {
          const token = localStorage.getItem("jwtToken"); // Obtén el token del almacenamiento local
          const response = await axios.get("http://localhost:8080/api/vehiculos/all", {
            headers: {
              Authorization: `Bearer ${token}`, // Añade el token al encabezado
            },
          });
          this.vehiculosDisponibles = response.data; // Asigna los vehículos a la variable
        } catch (error) {
          console.error("Error al obtener los vehículos:", error);
          Swal.fire({
            title: "Error",
            text: "No se pudieron cargar los vehículos disponibles.",
            icon: "error",
          });
        }
      },
      cierreSesion() {
        window.location.href = "/"; // Redirecciona a la vista principal
      },
      AgregarVehiculoAdmin() {
        window.location.href = "/agregarvehiculo"; 
      },
      toInicio() {
        window.location.href = "/admin"; 
      },
      VerVehiculos() {
        window.location.href = "/vervehiculos"; 
      },
      
    },
    mounted() {
      this.fetchVehiculosDispo(); // Llama a la función al cargar el componente
    },
  };
  </script>
  
  <style>
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
  
  .sidebar {
    width: 250px;
    background-color: #ff80ab;
    padding: 20px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    position: fixed;
    height: 100%;
  }
  
  .sidebar ul {
    list-style: none;
    padding: 0;
    margin: 0;
  }
  
  .sidebar ul li {
    margin-bottom: 20px;
  }
  
  .sidebar ul li a {
    text-decoration: none;
    color: white;
    font-size: 16px;
    display: block;
    padding: 10px;
    border-radius: 4px;
    transition: background-color 0.3s;
  }
  
  .sidebar ul li a:hover {
    background-color: #ff4081;
  }
  
  .image {
    max-width: 100%;
    margin-bottom: 20px;
    border-radius: 8px;
  }
  
  .main-content {
    margin-left: 270px;
    padding: 40px;
    flex: 1;
  }
  
  h1 {
    font-size: 24px;
    margin-bottom: 20px;
  }
  
  .vehiculos-container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
  }
  
  .vehiculo-card {
    border: 1px solid #ccc;
    border-radius: 8px;
    padding: 15px;
    width: 250px;
    background-color: #fff;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    text-align: center;
  }
  
  .vehiculo-card img {
    max-width: 100%;
    border-radius: 8px;
    margin-bottom: 10px;
  }
  
  .vehiculo-card p {
    margin: 5px 0;
    font-size: 14px;
  }
  </style>
  