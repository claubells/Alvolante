<template>
  <main class="main-container">
    <div class="contacto_titulo">Contáctenos</div>
    <!-- Sección de contacto -->
    <section class="contact-section">
      <div class="contact-content">
        <img src="./media/soporte.png" alt="Soporte" class="support-img" /> <!-- Imagen de soporte -->
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
  </main>
</template>

<script setup>


</script>

<style>
/* Estilos generales */


body, html{
  margin: 0;
  padding: 0;
  min-height: 100vh;
  box-sizing: border-box;
}

body {
  margin: 0;
  font-family: "Arial", sans-serif;
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
}

.main-container {
  padding: 1rem;
}


.contacto_titulo {
  position: relative; /* Cambiar la posición a relativa */ 
  top: 10px; /* Ajustar la posición vertical hacia arriba */
  left: 14%;
  font-size: 2.5rem;
  font-weight: bold;
  color: #633434;
}


.contact-content {
  display: flex;
  align-items: center;
}

.support-img {
  width: 700px; /* Ajustar el ancho de la imagen de soporte según sea necesario */
  position: relative; /* Cambiar la posición a relativa */ 
  left: -15%; /* Ajustar la posición vertical hacia arriba */
  margin-right: 12rem; /* Ajustar el margen derecho según sea necesario */ 
  margin-left: 30rem; /* Ajustar el margen izquierdo para mover la imagen a la izquierda */
  margin-top: -15rem; /* Ajustar el margen superior para mover la imagen hacia arriba */
}

.contact-box {
  background: rgba(255, 255, 255, 0.8); /* Fondo blanco semitransparente */
  position: relative; /* Cambiar la posición a relativa */ 
  left: -25%; /* Ajustar la posición vertical hacia arriba */
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  margin-right: 1rem;
  margin-left: 1rem;
  margin-top: -15rem; /* Ajustar el margen superior para mover la imagen hacia arriba */
  width: 500px;
}

.logo-img {
  width: 150px; /* Ajustar el ancho de la imagen del logo según sea necesario */
  margin-bottom: 1rem;
  position: relative; /* Cambiar la posición a relativa */ 
  top: -3px; /* Ajustar la posición vertical hacia arriba */
}

.iconos-img {
  width: 150px; /* Ajustar el ancho de la imagen del logo según sea necesario */
  margin-bottom: 1rem;
  position: relative; /* Cambiar la posición a relativa */ 
  top: -1px; /* Ajustar la posición vertical hacia arriba */
}

.contact-content {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 12px;
  height: 700px;
}

</style>