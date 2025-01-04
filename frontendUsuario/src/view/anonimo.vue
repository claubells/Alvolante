<template>
    <main class="main-container">
        <div class="moving-gradient">
            <div class="content">
                <img class="image" src="./media/incognito.png" alt="Usuario Anónimo">
                <div class="header">Vista Anónima</div>
                <div class="headerDescription">{{ anonymousMessage }}</div>

                <!-- Contenedor de acciones -->
                <div class="actionsContainer">
                    <button class="sessionButton" @click="goBack">Volver al Inicio</button>
                    <button class="exploreButton" @click="goToAutos">Explorar Automóviles</button>
                </div>
            </div>
        </div>
    </main>
</template>

<script setup>
// Imports
import { ref } from 'vue';
import { useRouter } from 'vue-router'; 

// Variables reactivas
const router = useRouter(); 
const anonymousMessage = "Usted está a punto de recibir una atención limitada, donde solo podrá visualizar los vehículos.";

// Métodos
const goBack = () => window.location.href = '/';
const goToAutos = () => router.push('/vehiculosAnonimo');
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
    width: 104%;
    height: 103%;
    margin-left: -2%;
    margin-top: -0.9%;
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
    background: #fce4ec;
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
    max-width: 100px;
    height: auto;
    margin: 0 auto 1rem;
    border-radius: 50%;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

/* Encabezado */
.header {
    font-size: 1.5rem;
    font-weight: bold;
    margin-bottom: 0.5rem;
    color: #d81b60;
}

.headerDescription {
    font-size: 1rem;
    color: #ec407a;
    margin-bottom: 1.5rem;
}

/* Contenedor de acciones */
.actionsContainer {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

/* Botones */
.sessionButton {
    background: #eba3bb;
    color: #ffffff;
    border: none;
    padding: 0.75rem;
    border-radius: 8px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.sessionButton:hover {
    background: #ee6f9d;
}

.exploreButton {
    background: #60eda2;
    color: #ffffff;
    border: none;
    padding: 1.5rem;
    border-radius: 8px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.exploreButton:hover {
    background: #0de639;
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
