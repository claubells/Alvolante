import './assets/main.css'
import AppHeader from './components/AppHeader.vue';

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import "bootstrap"
import "bootstrap/dist/css/bootstrap.min.css"


// Importar Vuetify
import { createVuetify } from 'vuetify'
import 'vuetify/styles'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import VueSweetalert2 from 'vue-sweetalert2'

// Importar navbarComp
import navbarComp from './components/navbarComp.vue';

// Configurar Vuetify
const vuetify = createVuetify({
    components,
    directives,
});

const app = createApp(App)
app.use(VueSweetalert2);
app.use(router);
app.use(vuetify);

// Registrar navbarComp como componente global
app.component('navbarComp',navbarComp);
app.mount('#app');
app.component('AppHeader', AppHeader);
