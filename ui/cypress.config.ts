import { defineConfig } from "cypress";

export default defineConfig({
  /*retries controla cuántas veces Cypress debe
   intentar volver a ejecutar una prueba si falla, la propiedad runMode especifica el número de reintentos para las ejecuciones 
   de prueba iniciadas desde la línea de comandos o el Cypress Test Runner, y la propiedad openMode especifica el número de 
   reintentos para las ejecuciones de prueba iniciadas abriendo la aplicación Cypress.
   */
  retries: {"runMode": 2, "openMode": 0},
  //especifica que Cypress no debe grabar vídeo
  video: false,
  //Cypress tomará captura de pantalla cuando la ejecución de un test falle
  screenshotOnRunFailure: true,
  //ancho del viewport en el que se deben ejecutar las pruebas
  viewportWidth: 1200,
  //tiempo por defecto  que Cypress debe esperar a que un comando se complete antes de que expire
  defaultCommandTimeout: 5000,
  //tiempo máximo que debe esperar a que se cargue la página antes de que expire
  pageLoadTimeout: 100000,
  e2e: {
    setupNodeEvents(on, config) {
      console.log(config) // see everything in here!
      
      // modify config values
      config.defaultCommandTimeout = 10000
      config.baseUrl = 'http://localhost:4200/'
      
      // modify env var value
      config.env.ENVIRONMENT = 'staging'
      
      // IMPORTANT return the updated config object
      return config
    }

  },
});
/* // enviar el token de autenticación
module.exports = {
  requestTimeout: 60000,
  headers: {
    Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjZXNhckBlbWFpbC5jb20iLCJlbWFpbCI6ImNlc2FyQGVtYWlsLmNvbSJ9.wH0jIVJJqesxCAgzg35KD7Ez5x3U0UTfeh6z6TgNJfI',
  },
}; */
