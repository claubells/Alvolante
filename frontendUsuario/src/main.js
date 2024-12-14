import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import "bootstrap"
import "bootstrap/dist/css/bootstrap.min.css"
import navbarComp from './components/navbarComp.vue' // importar componente global navrbar

const app = createApp(App)
app.use(router)
app.component('navbarComp',navbarComp) // registrar el componente globalmente
app.mount('#app')