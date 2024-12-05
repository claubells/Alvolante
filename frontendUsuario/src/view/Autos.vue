<template>
    <main>
        <div class="general">
            <div class="content">
                <h1 class="title"> Automóviles de Lujo </h1>
                <div class="autosList">
                    <div class="autoCard">
                        <div class="autoImageContainer">
                            <img class="image" src="./media/spark.jpg" alt="Rayo McQueen">
                        </div>
                        <div class="autoDetails">
                            <h2 class="autoName">Zac</h2>
                            <p class="autoDescription">¡Velocidad y carisma, listo para ganar!</p>
                            <div class="autoSpecs">
                                <div class="specItem">
                                    <span class="specTitle">Puertas:</span>
                                    <span class="specValue">4</span>
                                </div>
                                <div class="specItem">
                                    <span class="specTitle">Combustible:</span>
                                    <span class="specValue">Gasolina</span>
                                </div>
                                <div class="specItem">
                                    <span class="specTitle">Año:</span>
                                    <span class="specValue">2024</span>
                                </div>
                            </div>
                            <p class="price">
                                <span class="priceAmount">$68,688 / día</span>
                            </p>
                            <button class="selectButton" @click="selectCar">Seleccionar</button>
                        </div>
                    </div>

                    <div class="autoCard">
                        <div class="autoImageContainer">
                            <img class="image" src="./media/fiesta.jpg" alt="Mater">
                        </div>
                        <div class="autoDetails">
                            <h2 class="autoName">Ford Fiesta 2022</h2>
                            <p class="autoDescription">Un camión remolque amigable y confiable.</p>
                            <div class="autoSpecs">
                                <div class="specItem">
                                    <span class="specTitle">Puertas:</span>
                                    <span class="specValue">2</span>
                                </div>
                                <div class="specItem">
                                    <span class="specTitle">Combustible:</span>
                                    <span class="specValue">Diesel</span>
                                </div>
                                <div class="specItem">
                                    <span class="specTitle">Año:</span>
                                    <span class="specValue">2024</span>
                                </div>
                            </div>
                            <p class="price">
                                <span class="priceAmount">$45,500 / día</span>
                            </p>
                            <button class="selectButton" @click="selectCar">Seleccionar</button>
                        </div>
                    </div>

                    
                </div>
                <button class="backButton" @click="goBack">Volver</button>
            </div>
        </div>
    </main>
</template>

<script setup>
const selectCar = () => {
    alert('Has seleccionado un auto');
};

const goBack = () => {
    window.history.back();
};
</script>

<style scoped>

.general {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: linear-gradient(135deg, #f7c2d2, #fae1e1);
    font-family: 'Montserrat', sans-serif;
    padding: 3rem;
}

.content {
    background: rgba(255, 255, 255, 0.95);
    padding: 3rem;
    border-radius: 25px;
    box-shadow: 0 20px 50px rgba(0, 0, 0, 0.15);
    text-align: center;
    max-width: 1200px;
    width: 100%;
    backdrop-filter: blur(15px);
}

.title {
    font-size: 3rem;
    font-weight: 700;
    color: #2e2e2e;
    margin-bottom: 2rem;
    text-transform: uppercase;
    letter-spacing: 2px;
    font-family: 'Roboto', sans-serif;
}

.autosList {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
    gap: 2rem;
    margin-bottom: 3rem;
}

.autoCard {
    background: #fff;
    padding: 2rem;
    border-radius: 20px;
    box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    position: relative;
    overflow: hidden;
}

.autoCard:hover {
    transform: translateY(-10px);
    box-shadow: 0 20px 50px rgba(0, 0, 0, 0.2);
}

.autoImageContainer {
    position: relative;
    overflow: hidden;
    border-radius: 20px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

.image {
    width: 100%;
    height: 250px;
    object-fit: cover;
    transition: transform 0.3s ease;
}

.autoCard:hover .image {
    transform: scale(1.1);
}

.autoName {
    font-size: 1.8rem;
    font-weight: 700;
    color: #3e2723;
    margin-top: 1.5rem;
}

.autoDescription {
    font-size: 1.2rem;
    color: #616161;
    margin-top: 0.8rem;
    line-height: 1.6;
}

.autoSpecs {
    margin-top: 1.5rem;
    font-size: 1.2rem;
    color: #424242;
}

.specItem {
    margin-bottom: 1rem;
    display: flex;
    justify-content: space-between;
    font-weight: 500;
}

.specTitle {
    color: #bdbdbd;
}

.specValue {
    font-weight: 700;
    color: #212121;
}

.price {
    font-size: 1.4rem;
    font-weight: 700;
    margin-top: 1.5rem;
    color: #f06292;
}

.priceAmount {
    color: #c2185b;
}

.selectButton {
    background: linear-gradient(135deg, #f06292, #f48fb1);
    color: white;
    border: none;
    padding: 0.8rem 2rem;
    border-radius: 30px;
    font-size: 1.1rem;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.2s ease;
    margin-top: 1.5rem;
    text-transform: uppercase;
    letter-spacing: 1.5px;
}

.selectButton:hover {
    background: linear-gradient(135deg, #f06292, #f50057);
    transform: scale(1.05);
}

.backButton {
    background: #f48fb1;
    color: white;
    border: none;
    padding: 1rem 2.5rem;
    border-radius: 30px;
    font-size: 1.2rem;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.2s ease;
    margin-top: 3rem;
    text-transform: uppercase;
    letter-spacing: 1.5px;
}

.backButton:hover {
    background: #f06292;
    transform: scale(1.05);
}

@media (max-width: 768px) {
    .content {
        padding: 2rem;
    }

    .autoCard {
        padding: 1.5rem;
    }

    .selectButton, .backButton {
        font-size: 1rem;
    }
}
</style>
