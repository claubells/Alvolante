import axios from "axios"

// URL de la API
const API_URL = "http://localhost:8080"

// Clase para manejar el registro de usuarios
class RegisterService {

    // Método para registrar un usuario
    async register(nameParam, email, password, birthdate) {
        try{
            // Se envía la petición al backend
            const response = await axios.post(
                `${API_URL}/auth/register`, 
                {
                nameParam,  
                email,
                password,
                birthdate,
            },
            {
                headers: {
                    "Content-Type": "application/json",
                },
            }
        );
        return response.data
        } catch (error) {
            // Errores en la API
            if (error.response) {
            const status = error.response.status;
            
            if(status === 400) {
                throw new Error("Datos inválidos. Por favor, verifica los datos ingresados.");
            }
            if (status === 409) {
                throw new Error("El correo ya se encuentra registrado.");
            } if (status === 500) {
                throw new Error("Error interno en el servidor. Intente más tarde.");
            }
            } else if (error.request) {
                // Problemas de conexión con el servidor
                throw new Error("No se pudo conectar con el servidor. Por favor, revisa tu conexión.");
            } else {
                // Otros errores
                throw new Error("Ocurrió un error inesperado.");
            }
        }
    }
}

// Clase para manejar el inicio de sesión de usuarios
class LoginService {

    // Método para iniciar sesión
    async login(email, password) {
        try {
            const data = { email, password };
            //imprime el json antes de enviarlo
            console.log("Datos enviados al backend:", JSON.stringify(data, null, 2));


            // axios.post llama al metodo post de la libreria de axios para
            // enviar datos al backend
            // data: datos que se envian al backend
            // await: operacion asincronica, espera a que la peticion termine
            // response: respuesta del backend
            const response = await axios.post(`${API_URL}/auth/login`, data);
            
            // Se extrae el token, el id y el rol del usuario de la respuesta
            const { token, userId , role} = response.data;

            // Guarda el token y el id del usuario en el local storage
            localStorage.setItem("jwtToken", token);
            localStorage.setItem("userId", userId);

            console.log("Inicio de sesión exitoso: Token y UserID guardados.");
            // Retorna un obj con el token, el id del usuario y el role
            return { token, userId , role};
            
        } catch (error) {
            // Errores en la API
            if (error.response) {
                const status = error.response.status;
            
                if(status === 401) {
                    throw new Error("Credenciales incorrectas. Por favor, verifique los datos ingresados.");
                }
                if (status === 400) {
                    throw new Error("El correo ingresado no se encuentra registrado.");
                } if (status === 500) {
                    throw new Error("Error interno en el servidor. Intente más tarde.");
                }
                } else if (error.request) {
                    // Problemas de conexión con el servidor
                    throw new Error("No se pudo conectar con el servidor. Por favor, revisa tu conexión.");
                } else {
                    // Otros errores
                    throw new Error("Ocurrió un error inesperado.");
                }
        }
    }

    async checkToken() {
        try {
            const token = localStorage.getItem("jwtToken");
            const response = await axios.post(
                `${API_URL}/auth/checkToken`,
                {},
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );
            return response.data; // si el token es alido se devuelven los datos
        } catch (error) {
            console.error("Error al validar el token", error);
            throw error;
        }
    }
}

async function  validateSession() {
    const loginService = new LoginService();
    try {
        await loginService.checkToken();
        return data;
    } catch (error) {
        alert("Su sesión ha expirado. Por favor, inicie sesión nuevamente.");
        localStorage.removeItem("jwtToken");
        localStorage.removeItem("userId");
        window.location.href = "/login";
        return null;
    }
    
}

export const registerService = new RegisterService();
export const loginService = new LoginService();
export{ validateSession };



        