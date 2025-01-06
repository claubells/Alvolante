async enviarReserva(reserva, idUsuario, idVehiculo) {
    try {
      const token = localStorage.getItem("jwtToken"); // Obtén el token del almacenamiento local
      const response = await axios.post(
        `${API_URL}/crear-reserva`,
        reserva,
        {
          params: { idUsuario, idVehiculo },
          headers: {
            Authorization: `Bearer ${token}`, // Añade el token al encabezado
            "Content-Type": "application/json", // Este encabezado es necesario
          },
        }
      );
      return response.data;
    } catch (error) {
      console.error("Error al crear la reserva:", error.response || error);
      throw error;
    }
  }