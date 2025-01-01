<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router'; 

const router = useRouter(); 
const anonymousMessage = "Usted está a punto de recibir una atencion limitada, donde solo podrá visualizar los vehículos.";
const goBack = () => {
    window.location.href = '/';
};


const goToAutos = () => {
    router.push('/vehiculosAnonimo');
};
</script>

<template>
    <main>
        <div class="general">
            <div class="content">
                <img class="image" src="./media/incognito.png" alt="Usuario Anónimo">
                <div class="header">Vista Anónima</div>
                <div class="headerDescription">{{ anonymousMessage }}</div>
                <div class="actionsContainer">
                    <button class="sessionButton" @click="goBack">Volver al Inicio</button>
                    <button class="exploreButton" @click="goToAutos">Explorar Automóviles </button>
                </div>
            </div>
        </div>
    </main>
</template>

<style>

.general {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: linear-gradient(135deg, #f8bbd0, #fce4ec); /* Gradiente rosado pastel */
    font-family: 'Arial', sans-serif;
}

.content {
    background: #fce4ec; 
    padding: 2rem;
    border-radius: 12px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
    max-width: 400px;
    width: 100%;
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


.image {
    display: block;
    max-width: 100px;
    margin: 0 auto 1rem;
    height: auto;
    border-radius: 50%;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}


.actionsContainer {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

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

.sessionButton:hover{
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

.exploreButton:hover{
    background: #0de639; 
}

</style>
