// ***********************************************************
// This example support/e2e.ts is processed and
// loaded automatically before your test files.
//
// This is a great place to put global configuration and
// behavior that modifies Cypress.
//
// You can change the location of this file or turn off
// automatically serving support files with the
// 'supportFile' configuration option.
//
// You can read more here:
// https://on.cypress.io/configuration
// ***********************************************************

// Import commands.js using ES2015 syntax:
import './commands'

// Alternatively you can use CommonJS syntax:
// require('./commands')

//añado aquí el test de login
describe('Login form', () => {
  beforeEach(() => {
    //carga la página de inicio de sesión en el navegador.
    cy.visit('/login');
  });
//define una prueba individual que verifica si el formulario de inicio de sesión se muestra correctamente en la página.
  it('should display the login form', () => {
    //busca  un elemento con la clase "login-form" y verifica si es visible
    cy.get('.login-form').should('be.visible');
    //las siguientes líneas de código realizan comprobaciones para verificar si otros elementos del formulario tienen el texto, los atributos y los placeholders correctos.
    cy.get('.title').should('have.text', 'Canbemini');
    cy.get('input[type=email]').should('have.attr', 'name', 'email');
    cy.get('input[type=email]').should('have.attr', 'required');
    cy.get('input[type=email]').should('have.attr', 'placeholder', 'Email');
    cy.get('input[type=password]').should('have.attr', 'name', 'password');
    cy.get('input[type=password]').should('have.attr', 'required');
    cy.get('input[type=password]').should('have.attr', 'placeholder', 'Password');
    cy.get('button[type=submit]').should('have.text', 'Login');
  });
//defino otra prueba individual que verifica si se muestran mensajes de error cuando el usuario envía el formulario sin completar los campos
  it('should display error messages when the form is submitted with empty fields', () => {
    //esta función es la que realiza click en el botón de envío de formulario
    cy.get('button[type=submit]').click();
    //verifico si se muestran los mensajes de error correctos
    cy.get('mat-error').should('have.length', 2);
    cy.get('mat-error').first().should('have.text', 'El correo no puede estar vacío');
    cy.get('mat-error').last().should('have.text', 'La contraseña no puede estar vacía');
  });
//defino otra prueba individual que verifica si el usuario puede iniciar sesión al enviar el formulario con datos correctos
  it('should display a success message when the form is submitted with valid data', () => {
    //las siguientes líneas escriben los campos de email , password y se hace click en el botón del formulario
    cy.get('input[name=email]').type('cesar@email.com');
    cy.get('input[name=password]').type('123');
    cy.get('button[type=submit]').click();
    //este código hace que se navegue hacía la página de kanbans
    cy.url().should('include', '/kanbans');
  });
});
