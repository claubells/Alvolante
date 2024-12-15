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
        <h1>Bienvenido, Administrador</h1>
        <p>¿Qué le gustaría hacer hoy?</p>
        
        <!-- Botones de acción -->
        <div class="action-buttons">
          <button @click="AgregarVehiculoAdmin" class="action-btn">Agregar Vehículo a alguna sucursal</button>
          <button @click="arrendarVehiculo" class="action-btn">Realizar arriendo de vehículo</button>
          <button @click="VerVehiculos" class="action-btn">Ver vehículos</button>
          <button @click="Verestadisticas" class="action-btn">Ver estadisticas</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
import VerVehiculos from './VerVehiculos.vue';

  export default {
    methods: {
      cierreSesion() {
        window.location.href = "/"; 
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
      Verestadisticas() {
        window.location.href = "/estadisticasAdmin"; 
      },
      arrendarVehiculo() {
        window.location.href = "/calendarioAdmin"; 
      },
      //arrendarVehiculo() {
        //window.location.href = "/arrendar"; 
      //},
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
    margin-left: 70px;
    margin-top: 50px;
    padding: 10px;
    flex: 1;
  }
  
  h1 {
    font-size: 22px;
    margin-bottom: 10px;
  }
  
  .action-buttons {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 30px;
    margin-left: -70%;
  }
  
  .action-btn {
    background-color: #ff4081;
    color: white;
    font-size: 20px;
    padding: 15px 40px;
    border: none;
    border-radius: 50px;
    width: 80%;
    max-width: 300px;
    margin: 10px 0;
    cursor: pointer;
    transition: transform 0.3s, background-color 0.3s;
  }
  
  .action-btn:hover {
    background-color: #ff80ab;
    transform: scale(1.05);
  }
  
  @media (max-width: 768px) {
    .action-buttons {
      width: 100%;
    }
    .action-btn {
      width: 90%;
      font-size: 18px;
    }
  }
  </style>
  