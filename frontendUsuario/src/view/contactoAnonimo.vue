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
                <a class="nav-link" @click.prevent="verVehiculos" href="#" style="margin-left: 0px;">Ver Vehículos</a>
            </li>
            <li class="nav-item">
            <a class="nav-link disabled custom-text" aria-disabled="true">Usted está en la vista anónima</a>
            </li>
          </ul>
            <li class="d-flex" role="search">
                <button class="btn btn-custom me-2" @click.prevent="inicioSesion">Iniciar sesión</button>
                <button class="btn btn-custom"@click.prevent="registrarse">Registrarse</button>
            </li>
        </div>
      </div>
    </nav>

    <div class="main-content" style="margin-top: 80px;"> 
        <div class="contacto_titulo">Contáctenos</div>
    <!-- Sección de contacto -->
    <section class="contact-section">
      <div class="contact-content">
        <div class="contact-box">
          <img src="./media/logoAV.png" alt="Logo AV" class="logo-img" /> <!-- Imagen del logo -->
          
          <h2>Información de contacto:</h2>
          <p>Teléfono: +5693452342</p>
          <p>Email: soporte@alvolante.com</p>
          <p>Dirección: Aeropuerto Santiago,Chile</p>
          <img src="./media/contactoIconos.png" alt="Iconos" class="iconos-img" /> <!-- Imagen de iconos -->
        </div>
      </div>
    </section>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import Intranet from "./intranet.vue";
  import Contacto from "./contacto.vue";
  
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
        this.$router.push({ path: '/homeviews', query: { register: true } }).then(() => {
          window.location.reload(); // Recarga completa la página
      }); // Redirecciona a la vista contacto
      },
      inicioSesion() {
        this.$router.push("/homeviews").then(() => {
    window.location.reload(); // Recarga completa la página
      }); // Redirecciona a la vista contacto
      },
      verVehiculos() {
        this.$router.push('/vehiculosAnonimo').then(() => {
    window.location.reload(); // Recarga completa la página
      }); // Redirecciona a la vista contacto
      },
      Intranet() {
        this.$router.push("/").then(() => {
    window.location.reload(); 
      }); 
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
  display: flex;
  flex-direction: column;
  align-items: center; /* Centra todo el contenido dentro de main-content */
}

.contacto_titulo {
  font-size: 2rem; /* Tamaño del título más grande */
  color: #202020; /* Color oscuro para el título */
  margin-bottom: 30px; /* Espaciado debajo del título */
  text-align: center; /* Centra el título */
}

.contact-section {
  display: flex;
  justify-content: center; /* Centra el contenido horizontalmente */
  align-items: center; /* Centra el contenido verticalmente */
  margin-bottom: 40px; /* Espacio debajo de la sección de contacto */
}

.contact-content {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
}

.support-img {
  max-width: 250px; /* Establece un tamaño fijo para la imagen de soporte */
  height: auto;
  object-fit: contain; /* Asegura que la imagen mantenga su proporción */
}

.contact-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center; /* Centra el texto dentro de contact-box */
  margin-right: -70px;
  margin-top: -110px;
}

.logo-img {
  max-width: 150px; /* Ajusta el tamaño del logo */
  margin-bottom: 15px; /* Espaciado debajo del logo */
}

.iconos-img {
  max-width: 150px; /* Ajusta el tamaño de los iconos */
  margin-top: 20px;
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
  background-color: #868686;
  color: rgb(255, 255, 255);
  border: none;
}

.btn-custom:hover {
  background-color: #707070;
}

.nav-link:hover {
  background-color: #707070;
}

  </style>