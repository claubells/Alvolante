import axios from "axios"

// URL de la API
const API_URL = "http://localhost:8080"
// 
class ReservaService {
  async enviarReserva(reserva, idUsuario, idVehiculo) {
    try {
      const token = localStorage.getItem("jwtToken"); // Obtén el token del almacenamiento local
      const response = await axios.post(
        "http://localhost:8080/api/reserva/crear-reserva",
        reserva.value, // Datos de la reserva
        {
          headers: {
            Authorization: `Bearer ${token}`, // Incluye el token en el encabezado
            "Content-Type": "application/json", // Establece el tipo de contenido
          },
        }
      );
      console.log("Reserva creada con éxito:", response.data);
      return response.data;
    } catch (error) {
      console.error("Error al crear la reserva:", error.response || error);
      throw error;
    }
  }
}

// Exportar una instancia de la clase correctamente
const reservaServiceInstance = new ReservaService();
export default reservaServiceInstance;