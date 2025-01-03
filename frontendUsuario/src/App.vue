<template>
  <div id="app">
    <router-view v-slot="{ Component , route}">
      <!-- Muestra AppHeader solo si showHeader es true -->
      <AppHeader v-if="route.meta.showHeader" />
      <component :is="Component" />
    </router-view>
  </div>
</template>

<script>
import AppHeader from './components/AppHeader.vue';

export default {
  name: "App",
  components: {
    AppHeader,
  },
};

</script>


<style>

  /* Configuración global del scroll */
  html, body{
    margin: 0;
    padding: 0;
    width: 100%; /* Ocupa todo el ancho */
    overflow-x: hidden; /* Evita desplazamiento horizontal si hay contenido sobrante */
  }

  /* Permitir scroll solo en vistas específicas */
  body.scrollable {
    overflow-y: auto; /* Habilitar scroll en las vistas necesarias */
  }
  
  

  #app{
    height: 100%;
    width: 100%;
    justify-content: center;
    align-items: center;
    overflow: hidden; 
  }
/* Evita scroll por defecto */

</style> 