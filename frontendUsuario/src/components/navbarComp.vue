<!-- src/components/GlobalComponent.vue -->
<template>

<!-- Barra de navegación -->
    <nav class="navbar">
      <div class="logo">Al Volante</div>
      <div class="nav-links">
        <a href="#" @click.prevent="toInicio">Inicio</a>
        <a href="#" @click.prevent="toPerfilCliente">Perfil</a>
        <a href="#" @click.prevent="toContact">Contacto</a>
        <a href="#" @click.prevent="logout">Cerrar Sesión</a>
      </div>
    </nav>

</template>
  
  <script>
  
  export default {
    name: "navbarComp",
    methods: {
    logout() {
      localStorage.removeItem("login"); // Limpia el almacenamiento local
      window.location.href = "/"; // Redirige al login
    },
    toContact(){
      window.location.href = "/contacto"; // Redirecciona a la vista de contacto
    },
    toInicio(){
      window.location.href = "/user"; // donde esta la vista inicio?????????
    },
    toPerfilCliente(){
      window.location.href = "/perfilCliente"; // Redireccina a perfil clinete
    }
  },
  };
</script>
  
<style scoped>
  /* Barra de navegación */
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background-color: #ff80ab;
  color: #fff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  position: fixed; /* Hace que la barra se quede fija en la parte superior */
  top: 0; /* La coloca exactamente en la parte superior */
  left: 0; /* Alinea al inicio del contenido horizontal */
  width: 100%; /* Se asegura de que ocupe todo el ancho */
  z-index: 1000;
  
}

.logo {
  font-size: 1.5rem;
  font-weight: bold;
}

.nav-links a {
  color: #fff;
  margin-left: 1.5rem;
  text-decoration: none;
  font-size: 1rem;
  transition: color 0.3s ease;
}

.nav-links a:hover {
  color: #ffd1dc;
}
</style>
  