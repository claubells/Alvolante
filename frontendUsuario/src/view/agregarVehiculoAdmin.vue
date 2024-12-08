<template>
  <div class="container">
    <div class="sidebar">
      <ul>
        <img class="image" src="./media/logoalvolante.png">
        <li><a href="#">Inicio</a></li>
        <li><a href="#">Profile</a></li>
        <li><a class="botonSubir" href="#">Subir Vehículo</a></li>
        <li><a href="#" @click="cierreSesion">Cerrar sesión</a></li>
      </ul>
    </div>
    <div class="main-content">
      <h1>Subir Vehículo</h1>
      <form @submit.prevent="submitForm">
        <label for="patente">Patente:</label>
        <input type="text" id="patente" v-model="vehicle.patente" required>

        <label for="numeroChasis">Número de Chasis:</label>
        <input type="text" id="numeroChasis" v-model="vehicle.numeroChasis" required>

        <label for="modelo">Modelo:</label>
        <input type="text" id="modelo" v-model="vehicle.modelo" required>

        <label for="marca">Marca:</label>
        <input type="text" id="marca" v-model="vehicle.marca" required>

        <label for="anio">Año:</label>
        <input type="number" id="anio" v-model="vehicle.anio" required>

        <label for="fotoVehiculo">Imagen del Vehículo:</label>
        <input type="file" id="fotoVehiculo" @change="onFileChange" accept="image/*">

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
        patente: "",
        numeroChasis: "",
        modelo: "",
        marca: "",
        anio: "",
        fotoVehiculo: null, // Almacenará la imagen en Base64
      },
    };
  },
  methods: {
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
    async submitForm() {
      try {
        const response = await axios.post(
          import.meta.env.VITE_BASE_URL + "api/vehiculos/crear-vehiculo",
          this.vehicle
        );
        console.log(response.data);
        alert("Vehículo ingresado con éxito");
      } catch (error) {
        console.error("Error:", error);
        alert("No se pudo generar conexión con el servidor");
      }
    },
    cierreSesion() {
      this.$router.push("/"); // redirecciona a la vista principal
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

form {
  display: flex;
  flex-direction: column;
}

label {
  font-size: 14px;
  margin-bottom: 8px;
}

input[type="text"],
input[type="number"],
input[type="file"] {
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 100%;
}

button {
  background-color: #ff80ab;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #ff4081;
}
</style>
