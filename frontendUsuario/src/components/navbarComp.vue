<!-- src/components/GlobalComponent.vue -->
<template>

<!-- Barra de navegación -->
    <nav class="navbar">
      <!-- Logo o título de la aplicación -->
      <div class="logo">
        <router-link to="/user">
          <img src="../view/media/logoAV.png" alt="Logo" class="logo-image" />
        </router-link>
      </div>

      <!-- Opciones del navbar -->
      <div class="nav-links">
        <router-link to="/inicio" class="nav-link">Inicio</router-link>
        <router-link to="/perfilCliente" class="nav-link">Ver Perfil</router-link>
        <router-link to="/contacto" class="nav-link">Contacto</router-link>
      </div>

      <!-- Botón de cerrar sesión -->
      <div class="logout-container">
        <button class="logout-button" @click="logout">Cerrar sesión</button>
      </div>
    </nav>
</template>
  
<script>
export default {
  name: "NavbarComp",
  methods: {
    logout() {
      // Eliminar tokens de autenticación
      localStorage.removeItem("jwtToken");
      localStorage.removeItem("userId");

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
/* Estilos del navbar */
.navbar {
  display: flex;
  justify-content: space-between;
  background-color: #ff80ab;
  padding: 1rem 2rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

/* Logo */
.logo {
  display: flex;
  align-items: center;
}

.logo-image {
  height: 100px;
}

.nav-links {
  display: flex; /* Alinea los enlaces en una fila */
  justify-content: flex-end; /* Alinear los enlaces a la derecha */
  gap: 1.5rem; /* Espaciado entre los enlaces */
  color: #fff;
  margin-right: auto; /* Empuja los enlaces hacia el centro, separándolos del botón */
  
}

/* Estilo de cada enlace */
.nav-link {
  text-decoration: none;
  color: white;
  font-weight: bold;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: #ffd1dc;
}

.logout-container {
  display: flex;
  align-items: center;
}

.logout-button {
  background: #f54490;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.logout-button:hover {
  background: #db2864;
}
</style>
  