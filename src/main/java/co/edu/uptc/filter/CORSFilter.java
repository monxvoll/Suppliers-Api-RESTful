package co.edu.uptc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filtro CORS (Cross-Origin Resource Sharing) para permitir el acceso
 * a los recursos del servidor desde dominios diferentes.
 *
 * @author @monx.voll
 */
public class CORSFilter implements Filter {

    /**
     * Método de inicialización del filtro. No se realiza ninguna acción en este caso.
     *
     * @param filterConfig Configuración del filtro
     * @throws ServletException si ocurre un error durante la inicialización
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No se realiza ninguna acción en la inicialización
    }

    /**
     * Método principal del filtro que configura las cabeceras CORS para las respuestas.
     * Permite solicitudes desde cualquier origen y define los métodos y cabeceras permitidos.
     *
     * @param request Solicitud del cliente
     * @param response Respuesta del servidor
     * @param chain Cadena de filtros
     * @throws IOException si ocurre un error durante la lectura o escritura
     * @throws ServletException si ocurre un error en el procesamiento del filtro
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Configuración de cabeceras CORS
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        httpResponse.setHeader("Access-Control-Max-Age", "1209600");

        // Si el método de solicitud es OPTIONS, respondemos con un estado OK y terminamos el procesamiento
        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // Continuar con el siguiente filtro en la cadena
        chain.doFilter(request, response);
    }

    /**
     * Método para limpiar recursos antes de que el filtro sea destruido. No se realiza ninguna acción en este caso.
     */
    @Override
    public void destroy() {
        // No se realiza ninguna acción en la destrucción
    }
}