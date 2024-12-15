<script setup>
import { useRouter } from 'vue-router'; 
import Swal from 'sweetalert2';

const router = useRouter();
</script>


<template>
  <div class="container">
    <div class="sidebar">
      <ul>
        <img class="image" src="./media/logoalvolante.png">
        <li><a href="#"@click="toInicio">Inicio</a></li>
        <li><a class="botonSubir" href="#"@click="AgregarVehiculoAdmin">Subir Vehículo</a></li>
        <li><a href="#" @click="cierreSesion">Cerrar sesión</a></li>
      </ul>
    </div>
    <div class="main-content">
      <h1>Subir Vehículo</h1>
      <form @submit.prevent="submitForm">

        <label for="codigoACRISS">Codigo ACRISS:</label>
        <input type="text" id="codigoACRISS" v-model="vehicle.codigoACRISS" required>

        <label for="patente">Patente:</label>
        <input type="text" id="patente" v-model="vehicle.patente" ref="patente" class="{ 'error-input': patenteError }" required>
        

        <label for="numeroChasis">Número de Chasis:</label>
        <input type="text" id="numeroChasis" v-model="vehicle.numeroChasis" ref="numeroChasis" required>
        

        <label for="modelo">Modelo:</label>
        <input type="text" id="modelo" v-model="vehicle.modelo" required>

        <label for="marca">Marca:</label>
        <input type="text" id="marca" v-model="vehicle.marca" required>

        <label for="anio">Año:</label>
        <input type="number" id="anio" v-model="vehicle.anio" required>

        <label for="kilometraje">Kilometraje:</label>
        <input type="text" id="kilometraje" v-model="vehicle.kilometraje" required>

        <label for="costo">Costo:</label>
        <input type="number" id="costo" v-model="vehicle.costo" required>

        <label for="tipo">Tipo:</label>
        <input type="text" id="tipo" v-model="vehicle.tipo" required>

        <label for="color">Color:</label>
        <input type="text" id="color" v-model="vehicle.color" required>

        <label for="capacidadPasajeros">Capacidad de pasajeros:</label>
        <input type="number" id="capacidadPasajeros" v-model="vehicle.capacidadPasajeros" required>

        <label for="fechaUltimoMantenimiento">Fecha de ultimo mantenimiento:</label>
        <input type="date" id="fechaUltimoMantenimiento" v-model="vehicle.fechaUltimoMantenimiento" required>
        
        <label for="estadoVehiculo">Estado del vehiculo:</label>
        <input type="text" id="estadoVehiculo" v-model="vehicle.estadoVehiculo" required> 
        
        <label for="fotoVehiculo">Imagen del Vehículo:</label>
        <input type="file" id="fotoVehiculo" @change="onFileChange" accept="image/*">

        <label for="sucursal">Sucursal:</label>
          <select type="text" id="sucursal" v-model="vehicle.sucursal">
            <option value="">Seleccione una sucursal</option>
            <option value="Sucursal Arica">Sucursal Arica</option>
            <option value="Sucursal Iquique">Sucursal Iquique</option>
            <option value="Sucursal Antofagasta">Sucursal Antofagasta</option>
            <option value="Sucursal Copiapó">Sucursal Copiapó</option>
            <option value="Sucursal La Serena">Sucursal La Serena</option>
            <option value="Sucursal Valparaíso">Sucursal Valparaíso</option>
            <option value="Sucursal Santiago">Sucursal Santiago</option>
            <option value="Sucursal Rancagua">Sucursal Rancagua</option>
            <option value="Sucursal Talca">Sucursal Talca</option>
            <option value="Sucursal Chillán">Sucursal Chillán</option>
            <option value="Sucursal Concepción">Sucursal Concepción</option>
            <option value="Sucursal Puerto Montt">Sucursal Puerto Montt</option>
            <option value="Sucursal Coyhaique">Sucursal Coyhaique</option>
            <option value="Sucursal Punta Arenas">Sucursal Punta Arenas</option>
            <option value="Sucursal Valdivia">Sucursal Valdivia</option>
          </select>

        <button type="submit">Ingresar</button>
      </form>    
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      vehicle: {
        codigoACRISS: "",
        patente: "",
        numeroChasis: "",
        modelo: "",
        marca: "",
        kilometraje: "",
        anio: "",
        costo: "",
        tipo: "",
        color: "",
        capacidadPasajeros: "",
        fechaUltimoMantenimiento: "",
        estadoVehiculo: "",
        sucursal: "",
        fotoVehiculo: null, // Almacenará la imagen en Base64  
      },
    };
  },
  methods: {
    // para la foto
    onFileChange(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.vehicle.fotoVehiculo = e.target.result.split(",")[1]; // Solo el contenido Base64
        };
        reader.readAsDataURL(file);
      }
    },
    // enviar datos al backend
    async submitForm() {
      try {
        // agrupa los datos y los manda al backend
        const response = await axios.post(
          import.meta.env.VITE_BASE_URL + 'api/vehiculos/crear-vehiculo',
          this.vehicle)
          console.log(response.data) // respuesta del backend
        if (response.data == 4) {
          Swal.fire({
            title: '¡Cuidado!',
            text: 'Patente repetida',
            icon: 'warning',
            confirmButtonText: 'OK',
            customClass: {
              confirmButton: 'custom-confirm-button'
      }
    });
        }
        if (response.data == 2) {
          Swal.fire({
            title: '¡Cuidado!',
            text: 'Numero de chasis repetido',
            icon: 'warning',
            confirmButtonText: 'OK',
            customClass: {
              confirmButton: 'custom-confirm-button'
      }
    });
          
          
        } 
        if (response.data == 0) {  
          Swal.fire({
            title: '¡Excelente!',
            text: 'Vehículo ingresado con éxito',
            icon: 'warning',
            confirmButtonText: 'OK',
            customClass: {
              confirmButton: 'custom-confirm-button'
      }
    });
        }

        // error de la bd 
        } catch (error) {
          Swal.fire({
            title: '¡Cuidado!',
            text: 'Numero de chasis repetido o patente repetida',
            icon: 'warning',
            confirmButtonText: 'OK',
            customClass: {
              confirmButton: 'custom-confirm-button'
      }
    });
          
        }
    },
    cierreSesion() {
      window.location.href = "/"; // redirecciona a la vista principal
    },
    AgregarVehiculoAdmin() {
        window.location.href = "/agregarvehiculo"; 
      },
    toInicio() {
        window.location.href = "/admin"; 
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

.main-content {
  margin-left: 270px;
  padding: 10px; /* Menos padding en la sección principal */
  flex: 1;
}

h1 {
  font-size: 22px; /* Un poco más pequeño el título */
  margin-bottom: 10px; /* Menos espacio debajo del título */
}

form {
  display: flex;
  flex-direction: column; /* Asegura que los campos se apilen verticalmente */
}

label {
  font-size: 12px; /* Un poco más pequeño el texto de las etiquetas */
  margin-bottom: 3px; /* Menos margen debajo de las etiquetas */
}

button {
  background-color: #ff80ab;
  color: white;
  padding: 6px 12px; /* Menos padding en el botón */
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 15px; /* Un poco más pequeño el texto del botón */
  transition: background-color 0.3s;
  width: 100%;
  margin-top: 10px; /* Agregado un pequeño margen superior para separar del campo previo */
}

button:hover {
  background-color: #ff4081;
}

@media (max-width: 768px) {
  .form-column {
    width: 100%;
    padding-right: 0;
  }
  input[type="text"],
  input[type="number"],
  input[type="file"] {
    width: 100%;
    margin-right: 0;
  }
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