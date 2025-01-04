<template>
  <main class="main-container"> 
    
      <!-- Bootstrap Carousel -->
      <section class="carousel-section">
      <div id="carouselExampleCaptions" class="carousel slide">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="./media/cdealer.jpg" class="d-block w-100" alt="Al Volante">
            <div class="carousel-caption d-none d-md-block">
              <h5>¡Bienvenido!</h5>
              <p>Explora nuestras funcionalidades y descubre cómo podemos ayudarte.</p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="./media/cinicio.jpg" class="d-block w-100" alt="Al Volante">
            <div class="carousel-caption d-none d-md-block">
              <h5>Al Volante</h5>
              <p>Tu mejor opción para arrendar un vehículo.</p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="./media/cfamily.jpg" class="d-block w-100" alt="Al Volante">
            <div class="carousel-caption d-none d-md-block">
              <h5>Facíl y Rápido</h5>
              <p>Mejor servicio de arriendo de Vehículos.</p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="./media/crent.jpg" class="d-block w-100" alt="Al Volante">
            <div class="carousel-caption d-none d-md-block">
              <h5>Viaja seguro en nuestras manos</h5>
              <p>Soporte con sucursales en todas las regiones de Chile.</p>
            </div>
          </div>
          
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
        
      </div>
    </section>

    
    <!-- Secciones adicionales -->
    <section class="content-section">
      <div class="card">
        <h2>Reserva</h2>
        <p>¡Entra ya y reserva!</p>
        <button class="card-button" @click.prevent="toCalendario">Ir</button>
      </div>
    </section> 

  </main>
</template>

<script setup>
// Imports
import { ref, onMounted } from 'vue'

// Variables reactivas
const userName = ref("Usuario"); // Puedes usar variables para datos dinámicos.

// Métodos
const toCalendario = () => window.location.href = "/calendarioCliente";
onMounted(() => {
  const carousel = new bootstrap.Carousel(document.getElementById('carouselExample'))
})
</script>

<style>
body {
  margin: 0;
  padding: 0;
  font-family: "Arial", sans-serif;
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
}

.welcome-section {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 2rem;
  margin-top: 20px; /* Ajusta este valor */
}
.main-container {
  margin: 0;
  padding: 0;
}

.welcome-text {
  text-align: center;
  max-width: 800px;
}

.welcome-text h1 {
  font-size: 2rem;
  color: #ff4081;
  margin-top: 0; /* Elimina el margen negativo */
}

.welcome-text p {
  font-size: 1.2rem;
  color: #ff6f61;
}

.welcome-image {
  max-width: 40%;
}

.al-volante-img {
  width: 100%;
  height: auto;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  
}

/* Sección de tarjetas */
.content-section {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  gap: 1.5rem;
  margin-top: 67rem;
}

.card {
  background: #fff3e0;
  padding: 1.5rem;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgb(80, 0, 89);
  text-align: center;
  width: 450px;
  height: 220px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  margin-top: -45%;
}





.card h2 {
  font-size: 1.5rem;
  color: #ff4081;
  margin-bottom: 1rem;
}

.card p {
  font-size: 1rem;
  color: #757575;
  margin-bottom: 1.5rem;
}

.card-button {
  background: #ff80ab;
  color: #fff;
  border: none;
  padding: 0.75rem 1rem;
  border-radius: 8px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width:  400px;
  height: 60px;
}

.card-button:hover {
  background: #ff4081;
}

.card:hover {
  transform: translateY(-10px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}

/* Bootstrap Carousel */
.carousel-section {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 65vh; /* alto carousel*/
  width: 99vw;
  overflow: hidden;
  margin-top: 1%; /* top margin */
}

.carousel {
  width: calc(100% - 40px); /* ajustar margenes */
  height: 100%;
  
}

.carousel-item {
  height: 70vh;
  background-color: #ff80aa8c;
}

.carousel-item img {
  width: 100%;
  height: 100%;
  object-fit: contain; /* Changed from contain to cover */
  object-position: center;
}



/* texto carousel */
.carousel-caption {
  display: block !important; 
  background: #ff80aa8c;
  padding: 20px;
  border-radius: 8px;
  bottom: 70px;
}

.carousel-caption h5 {
  font-size: 200%;
  color: white;
  margin-bottom: 10px;
  font-weight: bold;
}

.carousel-caption p {
  color: white;
  font-size: 150%;
  font-weight: 500;
}

/* Estilos para los íconos de flechas prev/next */
.carousel-control-prev-icon,
.carousel-control-next-icon {
  width: 5rem;  /* Tamaño del ícono de flecha */
  height: 3rem;
  background-color: rgba(0, 0, 0, 0); /* Color de fondo del círculo */
  border-radius: 50%; /* Hace el círculo */
  padding: 1.5rem;
  filter: contrast(200%) brightness(150%); /* Hace las flechas más bold */
}

/* Estilos para los botones contenedores de prev/next */
.carousel-control-prev,
.carousel-control-next {
  width: 5%; /* Ancho del área clickeable */
  opacity: 1; /* Sin transparencia */
}

/* Efectos al pasar el mouse por los botones */
.carousel-control-prev:hover,
.carousel-control-next:hover {
  opacity: 1;
  background-color: rgba(0, 0, 0, 0.2); /* Fondo oscuro al hover */
}
</style>
