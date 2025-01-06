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
        <li><a class="botonSubir" href="#"@click="AgregarVehiculoAdmin">Añadir un vehículo</a></li>
        <li><a href="#" @click="cierreSesion">Cerrar sesión</a></li>
      </ul>
    </div>
    <div class="main-content">
      <h1>Añadir un vehículo al sistema</h1>
      <form @submit.prevent="submitForm">

        <div class="mb-3">
          <label for="codigoACRISS" class="form-label">Codigo ACRISS:</label>
          <input type="text" class="form-control" v-model = "vehicle.codigoACRISS" id="codigoACRISS"  ref = "codigoACRISS" required>
        </div>

        <div class="mb-3">
          <label for="patente" class="form-label">Patente:</label>
          <input type="text" class="form-control" id="patente" v-model = "vehicle.patente" ref = "patente" required>
        </div>
        
        <div class="mb-3">
          <label for="numeroChasis" class="form-label">Numero de Chasis:</label>
          <input type="text" class="form-control" id="numeroChasis" v-model = "vehicle.numeroChasis" ref = "numeroChasis" required>
        </div>

        <div class="mb-3">
          <label for="modelo" class="form-label">Modelo:</label>
          <input type="text" class="form-control" id="modelo" v-model = "vehicle.modelo" ref = "modelo" required>
        </div>
        
        <div class="mb-3">
          <label for="marca" class="form-label">Marca:</label>
          <input type="text" class="form-control" id="marca" v-model = "vehicle.marca" ref = "marca" required>
        </div>

        <div class="mb-3">
          <label for="anio" class="form-label">Año:</label>
          <input type="number" class="form-control" id="anio" v-model = "vehicle.anio" ref = "anio" required>
        </div>

        <div class="mb-3">
          <label for="kilometraje" class="form-label">Kilometraje:</label>
          <input type="text" class="form-control" id="kilometraje" v-model = "vehicle.kilometraje" ref = "kilometraje" required>
        </div>

        <div class="mb-3">
          <label for="costo" class="form-label">Costo:</label>
          <input type="number" class="form-control" id="costo" v-model = "vehicle.costo" ref = "costo" required>
        </div>

        <div class="mb-3">
          <label for="tipo" class="form-label">Tipo:</label>
          <input type="text" class="form-control" id="tipo" v-model = "vehicle.tipo" ref = "tipo" required>
        </div>

        <div class="mb-3">
          <label for="color" class="form-label">Color:</label>
          <input type="text" class="form-control" id="color" v-model = "vehicle.color" ref = "color" required>
        </div>

        <div class="mb-3">
          <label for="capacidadPasajeros" class="form-label">Capacidad de pasajeros:</label>
          <input type="number" class="form-control" id="capacidadPasajeros" v-model = "vehicle.capacidadPasajeros" ref = "capacidadPasajeros" required>
        </div>

        <div class="mb-3">
          <label for="fechaUltimoMantenimiento" class="form-label">Fecha de ultimo mantenimiento:</label>
          <input type="date" class="form-control" id="fechaUltimoMantenimiento" v-model="vehicle.fechaUltimoMantenimiento" ref = "fechaUltimoMantenimiento" required>
        </div>

        <div class="mb-3">
          <label for="combustible" class="form-label">Combustible:</label>
          <input type="text" class="form-control" id="combustible" v-model="vehicle.combustible" ref= "combustible" required> 
        </div>

        <div class="mb-3">
          <label for="estadoVehiculo" class="form-label">Estado del vehiculo:</label>
          <input type="text" class="form-control" id="estadoVehiculo" v-model="vehicle.estadoVehiculo" ref= "estadoVehiculo" required> 
        </div>

        <div class="mb-3">
          <label for="fotoVehiculo" class="form-label">Imagen del Vehículo:</label>
          <input class="form-control" type="file" id="fotoVehiculo" @change="onFileChange" accept="image/*">
        </div>

        <div class="mb-3">
          <label for="sucursal" class="form-label">Sucursal:</label>
          <select class="form-select" id="sucursal" v-model="vehicle.sucursal">
            <option selected>Seleccione la Sucursal</option>
            <option value="1">Sucursal Arica</option>
            <option value="2">Sucursal Iquique</option>
            <option value="3">Sucursal Antofagasta</option>
            <option value="4">Sucursal Copiapó</option>
            <option value="5">Sucursal La Serena</option>
            <option value="6">Sucursal Valparaíso</option>
            <option value="7">Sucursal Santiago</option>
            <option value="8">Sucursal Rancagua</option>
            <option value="9">Sucursal Talca</option>
            <option value="10">Sucursal Chillán</option>
            <option value="11">Sucursal Concepción</option>
            <option value="12">Sucursal Puerto Montt</option>
            <option value="13">Sucursal Coyhaique</option>
            <option value="14">Sucursal Punta Arenas</option>
            <option value="15">Sucursal Valdivia</option>
          </select>
        </div>

        <button type="submit">Ingresar</button>
      </form>    
    </div>
  </div>
</template>


<script>
import vehicleService from "../services/vehicleService";

export default {
  name: "AgregarVehiculoAdmin",
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
        combustible: "",
        estadoVehiculo: "",
        sucursal: "",
        fotoVehiculo: "", // Almacenará la imagen en Base64  
      },
      error: null,
      success: false,
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
      this.error = null;
      this.success = false;
      console.log("Objeto vehiculo definido en data():", this.vehicle);

      try {
        console.log("Datos antes de enviar:", this.vehicle);
        // llama al servicio de crear un vehiculo
        const response = await vehicleService.createVehiculo(this.vehicle);

        console.log('Sesión iniciada', response);
        if (response == 4) {
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
        if (response == 2) {
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
        if (response == 0) {  
          Swal.fire({
            title: '¡Excelente!',
            text: 'Vehículo ingresado con éxito',
            icon: 'success',
            confirmButtonText: 'OK',
            customClass: {
              confirmButton: 'custom-confirm-button'
      }
    });
        }
        // error de la bd 
        } catch (error) {
          Swal.fire({
            title: '¡Error!',
            text: 'Error en la base de datos',
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

.form-label {
  font-size: 1rem; /* Tamaño de fuente */
  font-weight: 500; /* Peso de fuente */
  margin-bottom: 0.5rem; /* Espaciado inferior */
  color: #333; /* Color del texto */
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