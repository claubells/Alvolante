<script setup>

import{RouterView} from 'vue-router'

</script>

  <template>
    <RouterView/>

    <v-app toolbar footer>

      <v-toolbar class="blue darken-3" dark>
      </v-toolbar>

      <v-navigation-drawer permanent clipped light absolute>
      </v-navigation-drawer>

  </v-app>
    
  </template>

<style>
html,
body{
  html, body {

  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;
  overflow: hidden;
}

}

#app{
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
}

html { overflow-y: auto }

</style> 