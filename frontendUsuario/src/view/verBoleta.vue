<template>
    <div class="container">
      <div class="sidebar">
        <ul>
          <img class="image" src="./media/logoalvolante.png">
          <li><a href="#" @click="toInicio">Inicio</a></li>
          <li><a href="#" @click="cierreSesion">Cerrar sesión</a></li>
        </ul>
      </div>
      <div class="main-content">
        <h1>Lista de Boletas</h1>
        <div class="boletas-container" v-if="boletas.length">
          <div v-for="boleta in boletas" :key="boleta.idBoleta" class="boleta-card">
            <p><strong>Nombre emisor:</strong> {{ boleta.nombreEmisor }}</p>
            <p><strong>Rut emisor:</strong> {{ boleta.rutEmisor }}</p>
            <p><strong>Direccion Emisión:</strong> {{ boleta.direccionEmisor }}</p>
            <p><strong>ID Boleta:</strong> {{ boleta.idBoleta }}</p>
            <p><strong>Nombre Cliente:</strong> {{ boleta.nombreCliente }}</p>
            <p><strong>Rut:</strong> {{ boleta.rutCliente }}</p>
            <p><strong>Total:</strong> ${{ boleta.total }}</p>

            <!-- Añadir otros campos si es necesario -->
          </div>
        </div>
        <p v-else>No hay boletas disponibles.</p>
      </div>
    </div>
  </template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      boletas: [], // Lista para almacenar las boletas
    };
  },
  methods: {
    async fetchBoletas() {
      try {
        const response = await axios.get(
          import.meta.env.VITE_BASE_URL + "api/boleta/all" // Ruta para obtener las boletas
        );
        this.boletas = response.data; // Guardamos los datos de las boletas
      } catch (error) {
        console.error("Error al obtener las boletas:", error);
        alert("No se pudo cargar la lista de boletas");
      }
    },
    cierreSesion() {
      window.location.href = "/"; // Redirecciona a la vista principal
    },
    toInicio() {
      window.location.href = "/admin"; // Redirecciona al inicio del administrador
    },
  },
  mounted() {
    this.fetchBoletas(); // Llama a la función al cargar el componente
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

.boletas-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.boleta-card {
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 15px;
  width: 250px;
  background-color: #fff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.boleta-card p {
  margin: 5px 0;
  font-size: 14px;
}
</style>
