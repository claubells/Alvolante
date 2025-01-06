import './assets/main.css'
import AppHeader from './components/AppHeader.vue'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'

// Import Vuetify
import { createVuetify } from 'vuetify'
import 'vuetify/styles'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

// Import SweetAlert2
import VueSweetalert2 from 'vue-sweetalert2'
import 'sweetalert2/dist/sweetalert2.min.css'

// Import navbarComp
import navbarComp from './components/navbarComp.vue'

// Configure Vuetify
const vuetify = createVuetify({
    components,
    directives,
})

const app = createApp(App)
app.use(VueSweetalert2)
app.use(router)
app.use(vuetify)

// Register navbarComp as a global component
app.component('navbarComp', navbarComp)
app.component('AppHeader', AppHeader)

app.mount('#app')