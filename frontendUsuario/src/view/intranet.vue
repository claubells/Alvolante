<template>
  <main class="main-container">
    <div class="moving-gradient">
      <div class="content">
        <img class="image" src="./media/logoalvolante.png" alt="Logo">
        <div class="header">BIENVENIDO/A A LA INTRANET</div>
        <div class="headerDescription">Seleccione su rol para continuar</div>

        <!-- Botones de rol -->
        <div class="roleButtons">
          <button class="roleButton" @click="chooseRoleC">Soy Cliente</button>
          <button class="roleButton" @click="chooseRoleT">Soy Trabajador</button>
        </div>
      </div>
    </div>
  </main>
</template>

<script setup>
// Métodos
const chooseRoleC = () => window.location.href = '/login';
const chooseRoleT = () => window.location.href = '/loginTrabajador';
</script>

<style>
/* Estilos generales */
body {
  margin: 0;
  font-family: "Arial", sans-serif;
  background: linear-gradient(135deg, #f8bbd0, #fce4ec);
}

.main-container {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.moving-gradient {
  width: 100%;
  height: 100vh;
  background: radial-gradient(circle, #faf8b4, #f8bbd0, #fce4ec, #ff80ab, #f8bbd0, #faff9a, #ff4081);
  background-size: 400% 400%;
  animation: gradient 15s ease infinite;
  display: flex;
  justify-content: center;
  align-items: center;
}

.content {
  background: #fff3e0;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 100%;
  max-width: 400px;
  box-sizing: border-box; /* Asegura que el padding y el border se incluyan en el tamaño total */
}

/* Imagen */
.image {
  display: block;
  max-width: 100%;
  height: auto;
  margin: 0 auto 1rem;
  width: 150px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

/* Encabezado */
.header {
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
  color: #ff4081;
}

.headerDescription {
  font-size: 1rem;
  color: #ff80ab;
  margin-bottom: 1.5rem;
}

/* Botones de rol */
.roleButtons {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.roleButton {
  background: #ff80ab;
  color: #ffffff;
  border: none;
  padding: 0.75rem;
  border-radius: 8px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.roleButton:hover {
  background: #ff4081;
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

@keyframes gradient {
  0% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
  100% { background-position: 0% 50%; }
}
</style>