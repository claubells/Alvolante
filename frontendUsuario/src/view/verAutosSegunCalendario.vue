<template>
    <div>
    <nav class="navbar">
      <div class="logo">Al Volante</div>
      <div class="nav-links">
        <a href="#" @click="toInicio">Inicio</a>
        <a href="#" @click="toPerfilCliente">Perfil</a>
        <a href="#" @click="toContact">Contacto</a>
        <a href="#" @click="logout">Cerrar Sesión</a>
      </div>
    </nav>
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
            <button @click="verDetallesVehiculo(vehiculo.idVehiculo)" class="select-button">Seleccionar</button> 
          </div>
        </div>
        <p v-else>No hay vehículos disponibles.</p>
      </div>
    </div>
</template> 


<script>
import axios from 'axios';

export default {
  data() {
    return {
      vehiculos: [], // Aquí inicializamos el estado
    };
  },
  methods: {
    async fetchVehiculos() {
      try {
        const response = await axios.get(import.meta.env.VITE_BASE_URL + 'api/vehiculos/vehiculosDisponible');
        this.vehiculos = response.data; // Guardamos los vehículos
      } catch (error) {
        console.error("Error al obtener vehículos disponibles:", error);
      }
    },
    cierreSesion() {
      console.log("Cerrar sesión clickeado");
    },
    mounted() {
    this.fetchVehiculos(); // Llamamos a la API al cargar
  },
  verDetallesVehiculo(idVehiculo) {
      this.$router.push({ name: 'seleccionVehiculo', params: { idVehiculo } });
    },
    logout() {
      localStorage.removeItem("login"); // Limpia el almacenamiento local
      window.location.href = "/"; // Redirige al login
    },
    toContact() {
      window.location.href = "/contacto";
    },
    toCalendario(){
      window.location.href = "/calendarioCliente"; 
      
    },
    toPerfilCliente(){
      window.location.href = "/perfilCliente";
    },
    toInicio(){
      window.location.href = "/user"; // donde esta la vista inicio?????????
    },
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

  .navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background-color: #ff80ab;
  color: #fff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 1000;
  
}

.logo {
  font-size: 1.5rem;
  font-weight: bold;
}

.nav-links {
  display: flex;
  gap: 1.5rem;
}

.nav-links a {
  color: #fff;
  text-decoration: none;
  font-size: 1rem;
  transition: color 0.3s ease;
}

.nav-links a:hover {
  color: #ffd1dc;
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
    margin-top: 75px;
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