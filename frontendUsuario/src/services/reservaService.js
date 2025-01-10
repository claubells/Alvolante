import axios from "axios"

// URL de la API
const API_URL = "http://localhost:8080"
// 
class ReservaService {
  async enviarReserva(reserva) {
    try {
      const token = localStorage.getItem("jwtToken"); // Obtén el token del almacenamiento local
      if (!token) {
        throw new Error("El token no está disponible en localStorage.");
      }

      // Agregamos el idVehiculo al objeto reserva
      //reserva.idVehiculo = idVehiculo.getItem("idVehiculo");
      
      reserva.fechaInicioReserva = localStorage.getItem("fechaRetiro");
      reserva.fechaFinReserva = localStorage.getItem("fechaEntrega");
      reserva.estadoReserva = 0;
      reserva.idSucursalRetiro = localStorage.getItem("idSucursalRetiro");
      reserva.idSucursalEntrega = localStorage.getItem("idSucursalEntrega");
      
      console.log("Datos de la reserva: \nidSucursalEntrega", reserva.idSucursalEntrega, "\nidSucursalRetiro", 
        reserva.idSucursalRetiro, "\nfechaInicioReserva", reserva.fechaInicioReserva, "\nfechaFinReserva", reserva.fechaFinReserva);

      console.log("Datos enviados al backend:", reserva);

      const response = await axios.post(
        "http://localhost:8080/api/reserva/crear-reserva",
        reserva, // Datos de la reserva
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
      if (error.response) {
        console.error(
          "Error al crear la reserva:",
          error.response.status,
          error.response.data
        );
      } else {
        console.error("Error de red o configuración:", error.message);
      }
      throw error;
    }
  }
}

// Exportar una instancia de la clase correctamente
const reservaServiceInstance = new ReservaService();
export default reservaServiceInstance;