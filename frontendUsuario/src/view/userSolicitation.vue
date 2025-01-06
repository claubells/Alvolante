<template>
  <main class="main-container">
    <!-- Bootstrap Carousel -->
    <section class="carousel-section">
      <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3" aria-label="Slide 4"></button>
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
              <h5>Confiable</h5>
              <p>Confía en nosotros para tu próximo viaje.</p>
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

    <!-- Additional Content -->
    <section class="content-section">
      <div class="card">
        <h2>Reserva</h2>
        <p>¡Entra ya y reserva!</p>
        <button class="card-button" @click.prevent="toCalendario"><i class="bi bi-calendar-fill nav-icon"></i>  Agendar </button>
      </div>
    </section>
  </main>
</template>

<script setup>
// Imports
import { ref } from 'vue'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'



// Variables reactivas
const userName = ref("Usuario")

// Métodos
const toCalendario = () => window.location.href = "/calendarioCliente"


</script>

<style>
body {
  margin: 0;
  padding: 0;
  font-family: "Arial", sans-serif;
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
}


/* Make sure styles are scoped */
.main-container {
  margin: 0;
  padding: 0;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  position: relative;
}

/* Force immediate style application */
* {
  margin: 0;
  padding: 0;
  
}


/* Sección de tarjetas */
.content-section {
  position: relative;
  z-index: 2;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 2rem;
  margin-top: 0rem; 
}

.card {
  background: white;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  width: 462px;
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
  height: 50%; /* alto carousel*/
  width: 100%;
  margin-left: calc((100% - 97%) / 2); 
  overflow: hidden;
  margin: 0; 
}

.carousel {
  width: 97%; /* ajustar margenes */
  height: 50%;
  margin: 0 auto; 
  transform: translateX(-0.0%); 
}

.carousel-item {
  height: 65vh;
  background-color: #ff80aa8c;
}

.carousel-item img {
  width: 100%;
  height: 100%;
  object-fit: contain; 
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
  width: 5rem;  
  height: 3rem;
  background-color: rgba(0, 0, 0, 0); 
  border-radius: 50%; 
  padding: 1.5rem;
  filter: contrast(200%) brightness(150%); 
}

/* Estilos para los botones contenedores de prev/next */
.carousel-control-prev,
.carousel-control-next {
  width: 5%; 
  opacity: 1; 
}

/* Efectos al pasar el mouse por los botones */
.carousel-control-prev:hover,
.carousel-control-next:hover {
  opacity: 1;
  background-color: rgba(0, 0, 0, 0.2); 
}
</style>
