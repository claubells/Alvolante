<template>
    <div class="container">
      <div class="sidebar">
        <ul>
          <img class="image" src="./media/logoalvolante.png">
          <li><a href="#">Inicio</a></li>
          <li><a href="#">Profile</a></li>
          <li><a class= "botonSubir" href="#">Subir Vehículo</a></li>
          <li><a href="#"  @click="cierreSesion" >Cerrar sesion</a></li>
        </ul>
      </div>
      <div class="main-content">
        
        <h1>Subir Vehículo</h1>
        <form @submit.prevent="submitForm">
          <label for="vehicle-image">Imagen del vehículo:</label>
          <input type="file" id="vehicle-image" @change="onFileChange">
  
          <label for="vehicle-name">Nombre del vehículo + las cuatro letritas:</label>
          <input type="text" id="vehicle-name" v-model="vehicle.name" >
  
          <label for="vehicle-color">Colores:</label>
          <input type="text" id="vehicle-color" v-model="vehicle.color" >
  
          <label for="vehicle-precio">Precio:</label>
          <input type="number" id="vehicle-precio" v-model="vehicle.precio" placeholder="$">
  
          <label for="vehicle-asientos">Asientos:</label>
          <select id="vehicle-asientos" v-model="vehicle.asientos">
            <option value="2">2</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="7">7</option>
          </select>
  
          <label for="vehicle-description">Descripción:</label>
          <textarea id="vehicle-description" v-model="vehicle.description"></textarea>
  
          <label for="vehicle-type">Tipo:</label>
          <input type="text" id="vehicle-type" v-model="vehicle.type">
  
          <button type="submit">Ingresar</button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  export default {
    data() {
      return {
        vehicle: {
          name: '',
          color: '',
          precio: null,
          asientos: '',
          description: '',
          type: '',
          image: null,
        },
      };
    },
    methods: {
      onFileChange(event) {
        const file = event.target.files[0];
        this.vehicle.image = file;
      },
      async submitForm() {
        const vehiculo = {
            "name" : this.name,
            "color" : this.color,
            "precio" : this.precio,
            "asientos" : this.asientos,
            "type" : this.type,
            "image" : this.image
        }
        try {
            const respuesta = await axios.post(import.meta.env.VITE_BASE_URL + "api/agregarVehiculo" , vehiculo);
        } catch (error){
            alert("No se pudo generar conexión con el servidor");
        } 
      },
      cierreSesion(){
      this.$router.push('/'); // redirecciona a la vista principal
        }
      
    }
}
  </script>
  
  <style>

body {
background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
  color: #000 
}

  .container {
    display: flex;
  }
  .sidebar {
    width: 180px;
    flex-direction: column;
    background-color: #ff80ab;
    padding: 25px;
    border: 1px solid #000;
    height: 100vh;
    top: 0;
    left: -20px;
    color: #ffffff;
    position: fixed;
  }
  .sidebar ul {
    list-style: none;
    padding: 0;
    color: #ffffff 
  }
  .sidebar ul li {
    margin-bottom: 10px;
    color: #ffffff 
  }
  .sidebar ul li a {
    text-decoration: none;
    color: #ffffff; 
    display: block;
  }
  .main-content {
    flex: 0.6;
    padding: 20px;
    position: relative;
    top: -50px;
    margin-left: 150px; /* Esto mueve el contenido hacia la derecha */
    
  }
  h1 {
    margin-top: 0;
  }
  form {
    display: flex;
    flex-direction: column;
  }
  label {
    margin-bottom: 5px;
    font-weight: bold;
  }
  input[type="text"], input[type="file"], input[type="number"], select, textarea {
    margin-bottom: 15px;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  button {
    background-color: #ff80ab;
    color: #fff;
    border: none;
    padding: 10px 15px;
    border-radius: 4px;
    cursor: pointer;
  }
  button:hover {
    background-color: #0056b3;
  }

  .image {
    display: block;
    max-width: 100%; 
    height: auto; 
    margin: 0 auto 1rem; 
    width: 150px; 
    border-radius: 8px; 
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    margin-left: 10px;
    
}

.botonSubir{
    background-color: rgb(182, 164, 178);
    transition: background-color 0.3s ease;

}




  </style>
  