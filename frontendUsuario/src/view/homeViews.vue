<script setup>

</script>


<template>
    <main>
        <div class="general">
            <div class="content">
                <img class="image" src="./media/rayoMaquin.png">
                <div class="header">INICIO DE SESION</div>
                <div class="headerDescription" v-if="!register">Completar campos para iniciar sesion</div>
                <div class="headerDescription" v-else="!register">Completar campos para registros</div>
                <div class="inputContainer" v-if="!register">
                    <input type="email" v-model="usename" placeholder="Ingrese correo">
                    <input type="password" v-model="password" placeholder="Ingrese contraseña">
                    <button class="sessionButton" @click="login">Iniciar sesion</button>
                </div>
                <div class="inputContainer" v-else>
                    <input type="email" v-model="usenameRegister" placeholder="Ingrese correo">
                    <input type="password" v-model="passwordRegister" placeholder="Ingrese contraseña">
                    <input type="password" v-model="passwordRegisterConfirmation" placeholder="Repita contraseña">
                    <button class="sessionButton" @click="addUser">Registrar</button>
                </div>
                <div class="alsoButtons">
                    <div class="alsoButton" @click="handleChange" v-if="!register">Registrarse</div>
                    <div class="alsoButton" @click="handleChange" v-else>Iniciar sesion</div>
                    <router-link to="anonimo">
                        <div class="alsoButton" @click="handleChange">Anonimo</div>

                    </router-link>

                </div>

            </div>

        </div>

    </main>

</template>

<style>
/* Estructura general */
.general {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
    font-family: 'Arial', sans-serif;
}

.content {
    background: #fff3e0;
    padding: 2rem;
    border-radius: 12px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
    max-width: 400px;
    width: 100%;
}

/* Imagen */
.logo {
    max-width: 100px;
    margin: 0 auto 1rem;
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

/* Inputs y botones */
.inputContainer {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

input {
    padding: 0.75rem;
    border: 1px solid #ffd54f;
    border-radius: 8px;
    font-size: 1rem;
}

input:focus {
    outline: none;
    border-color: #ffc107;
    box-shadow: 0 0 0 2px rgba(255, 193, 7, 0.2);
}

.sessionButton {
    background: #ff80ab;
    color: #ffffff;
    border: none;
    padding: 0.75rem;
    border-radius: 8px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.sessionButton:hover {
    background: #ff4081;
}

/* Botones alternativos */
.alsoButtons {
    margin-top: 1.5rem;
}

.alsoButton {
    color: #ff4081;
    font-weight: bold;
    margin-top: 1rem;
    cursor: pointer;
    text-decoration: underline;
    font-size: 0.9rem;
}

.alsoButton:hover {
    color: #ff6f61;
    text-decoration: none;
}

.image {
    display: block;
    max-width: 100%; /* Asegura que no exceda el contenedor */
    height: auto; /* Mantiene las proporciones */
    margin: 0 auto 1rem; /* Centra la imagen y agrega espacio inferior */
    width: 150px; /* Tamaño fijo opcional */
    border-radius: 8px; /* Bordes redondeados opcionales */
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Sombras para resaltar */
}
</style>
