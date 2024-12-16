<script setup>
import { useRouter } from 'vue-router'; 
import NavbarComp from '@/components/navbarComp.vue'; // Importa el componente

const router = useRouter();
</script>
<template>
    

    <div class="sidebar">
      <ul>
        <img class="image" src="./media/logoalvolante.png">
        <li><a href="#"@click="toInicio">Inicio</a></li>
        <li><a href="#" @click="cierreSesion">Cerrar sesión</a></li>
      </ul>
    </div>
  
    <!-- Contenedor principal con el formulario -->
    <div class="data-container" v-if="vehiculo">
        
      <div class="data-section">
        <button @click="Volver" class="select-button2">  Volver  </button>
        <img 
            v-if="vehiculo.fotoVehiculo"
            :src="'data:image/jpeg;base64,' + vehiculo.fotoVehiculo"
            alt="Foto del Vehículo"
            class="vehiculo-imagen"
          />
        <h1>{{ vehiculo.modelo }}</h1>
        
        <p><strong>Total Vehículo:</strong> {{ vehiculo.idVehiculo }}</p>
        <p><strong>Tipo:</strong> {{ vehiculo.tipo }}</p>
        <p><strong>Color:</strong> {{ vehiculo.color}}</p>
        <p><strong>Asientos:</strong> {{ vehiculo.capacidadPasajeros}}</p>
        <p><strong>Costo:</strong> {{ vehiculo.costo }}</p>
        <p><strong>Accesorios:</strong> {{ vehiculo.idVehiculo }}</p>
        <p><strong>Fechas:</strong> (información por completar)</p>
        
        <p><strong>Total:</strong> (vehículo + accesorios)</p>
        <button @click="irARellenarDatos"class="select-button">  Arrendar  </button>
      </div>
    </div>
    <p v-else>Cargando datos del vehículo...</p>
  </template>
  
  <script>
  
  import axios from "axios";
  
  export default {
    props: ['idVehiculo'], // Recibe el ID del vehículo como una propiedad
    data() {
      return {
        vehiculo: null, // Para almacenar los detalles del vehículo
      };
    },
    methods: {
        async fetchVehiculo() {
  try {
    const response = await axios.get(
      `${import.meta.env.VITE_BASE_URL}api/vehiculos/obtenerVehiculoPorId/${this.idVehiculo}`
    );
    if (response.data) {
      this.vehiculo = response.data;
    } else {
      console.error("No se encontraron datos del vehículo.");
    }
  } catch (error) {
    console.error("Error al cargar los detalles del vehículo:", error);
    alert("No se pudo cargar la información del vehículo.");
  }
},
  
  verDetallesVehiculoPago(idVehiculo) {
      this.$router.push({ name: 'pago', params: { idVehiculo } });
    },

  Volver() {
  // Implementar la lógica de selección de vehículo, por ejemplo, redirigir o guardar datos
  window.location.href = "/verAutosSegunCalendarioAdmin";
  },

  toInicio() {
  // Implementar la lógica de selección de vehículo, por ejemplo, redirigir o guardar datos
  window.location.href = "/Admin";
  },

  irARellenarDatos() {
  // Implementar la lógica de selección de vehículo, por ejemplo, redirigir o guardar datos
  window.location.href = "/rellenarDatosClienteAdmin";
  },

      
    },
    mounted() {
      this.fetchVehiculo(this.idVehiculo); // Carga los datos del vehículo al montar el componente
    },
  };
  </script>
  
  
  <style>
  body {
    background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
    color: #020000;
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    justify-content: center;
  }

  .sidebar {
  width: 250px;
  background-color: #ff80ab;
  padding: 15px; /* Menos padding en el sidebar */
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
  margin-bottom: 12px; /* Menor espacio entre los enlaces */
}

.sidebar ul li a {
  text-decoration: none;
  color: white;
  font-size: 15px; /* Font-size ligeramente más pequeño */
  display: block;
  padding: 7px; /* Menos padding en los enlaces */
  border-radius: 4px;
  transition: background-color 0.3s;
}

.sidebar ul li a:hover {
  background-color: #ff4081;
}

.image {
  max-width: 100%;
  margin-bottom: 10px; /* Menos margen debajo de la imagen */
  border-radius: 8px;
}
  
  .container {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
  }
  .container-fluid{
    display: flex;
  }
  
  .main-content {
    margin-top: 80px; 
    padding: 40px;
    flex: 1;
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
    text-align: center;
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
  
  /* Estilos para el Contenedor Principal y el Formulario */
  .data-container {
    margin-top: -10%;
    position: relative;
    margin-left: 5%;
    padding: 20%;
    height: 90%;
    width: 90%;
    justify-content: center;
  }
  
  .data-title {
    font-size: 150%;
    margin-bottom: 20px;
  }
  
  .data-section {
    background-color: #fff;
    padding: 10%;
    font-size: 100%;
    border-radius: 4rem;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  }
  
  .data-section h1 {
    margin-bottom: 3%;
    font-size: 150%;
    font-weight: bold;
  }
  
  .data-section form {
    display: flex;
    flex-direction: column;
  }
  
  .data-section form label {
    margin-top: 10px;
    font-weight: bold;
  }
  
  .data-section form input {
    padding: 10px;
    border: 1px solid #ced4da;
    border-radius: 4px;
    margin-bottom: 10px;
  }
  
  .data-section form button {
    padding: 10%;
    border: none;
    border-radius: 4px;
    background-color: #007bff;
    color: white;
    cursor: pointer;
    margin-top: 10px;
  }
  
  .data-section form button:hover {
    background-color: #0056b3;
  }
  
  .select-button {
    margin-top: 0%;
    width: 10%;
    padding: 10px;
    border: none;
    position: absolute;
    align-items: center;
    border-radius: 4px;
    background-color: #28a745;
    color: white;
    cursor: pointer;
    margin-left: 16%;
    justify-content: center;
  }

  .select-button2 {
    margin-top: 0%;
    width: 10%;
    padding: 10px;
    border: none;
    position: absolute;
    align-items: center;
    border-radius: 4px;
    background-color: #ffffff;
    color: rgb(0, 0, 0);
    cursor: pointer;    
    margin-left: -3%;
    margin-top: -4%;
    justify-content: center;
    border: 2px solid black;
  }


  
  .edit-button:hover {
    background-color: #218838;
  }
  
  .vehiculo-imagen {
    width: 35%; /* Ajustar el ancho de la imagen del perfil según sea necesario */ 
    display: block; /* Asegura que el margen automático funcione */ 
    margin-left: auto; 
    margin-right: auto;
    border: 2px solid black;
  }
  
  </style>
  