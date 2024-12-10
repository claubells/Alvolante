<template>
  <nav class="navbar navbar-expand-lg bg-dark" data-bs-theme="dark">
    <div class="container-fluid">
      <img class="image" src="./media/logoalvolante.png" alt="Logo">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
              <a class="nav-link active" aria-current="page" @click.prevent="Intranet" href="#" style="margin-left: 20px;">Inicio</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" @click.prevent="contacto" href="#" style="margin-left: 0px;">Contáctanos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled custom-text" aria-disabled="true">Usted está en la vista anónima</a>
          </li>
        </ul>
        <li class="d-flex" role="search">
          <button class="btn btn-custom me-2" @click.prevent="inicioSesion">Iniciar sesión</button>
          <button class="btn btn-custom" @click.prevent="registrarse">Registrarse</button>
        </li>
      </div>
    </div>
  </nav>

  <div class="main-content" style="margin-top: 80px;"> 
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
        <button @click="verDetallesVehiculo(vehiculo.idVehiculo)">Seleccionar</button>
      </div>
    </div>
    <p v-else>No hay vehículos disponibles.</p>
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
    async fetchVehiculos() {
      try {
        const response = await axios.get(
          import.meta.env.VITE_BASE_URL + "api/vehiculos/all" // Ruta para obtener los autos
        );
        this.vehiculos = response.data;
      } catch (error) {
        console.error("Error al obtener los vehículos:", error);
        alert("No se pudo cargar la lista de vehículos");
      }
    },
    registrarse() {
      this.$router.push({ path: '/homeviews', query: { register: true } });
    },
    inicioSesion() {
      this.$router.push("/homeviews");
    },
    contacto() {
      this.$router.push('/contactoAnonimo');
    },
    Intranet() {
      this.$router.push("/");
    },
    verDetallesVehiculo(idVehiculo) {
      this.$router.push({ name: 'seleccionVehiculo', params: { idVehiculo } });
    },
  },
  mounted() {
    this.fetchVehiculos(); // Llama a la función al cargar el componente
  },
};
</script>


<style>
body {
  background: #8b8b8b;
  color: #000;
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.image {
  max-height: 60px;
  max-width: 55px;
  margin-left: -20px;
  margin-top: auto;
  

  
}

.main-content {
  margin-top: 80px; 
  padding: 40px;
  flex: 1;
}

.nav-link {
color: #ffffff !important; 
}

.navbar-brand {
color: #ffffff !important; 
}

.navbar-text {
color: #ffffff !important; 
}

.custom-text {
font-size: 1.2rem; 
color: #999999 !important; 
margin-left: 500px;
 
}

h1 {
  color: #202020;
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
  background-color: #ffffff;
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

.btn-custom {
background-color: #868686; /* Color de fondo personalizado */
color: rgb(255, 255, 255); 
border: none; 
}

.btn-custom:hover {
background-color: #707070; /* Color al pasar el mouse por encima */
}

.nav-link:hover {
background-color: #707070;
}
</style>