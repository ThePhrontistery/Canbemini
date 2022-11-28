import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { LoginService } from './login.service';
import { BehaviorSubject, of } from 'rxjs';
import { User } from './login/model/User';
import { Router } from '@angular/router';

const mockUser: User = {
  email : 'ejemplo@capgemini.com',
  password : 'hola'
};

const RouterMock = {

  navigate(item:[]) {

    return {

      afterClosed: () => of(true),

    };

  },

};

fdescribe('LoginService', () => {
  let service: LoginService;

  beforeEach(() => {
    TestBed.configureTestingModule({
    imports: [
        // HttpClientTestingModule
    ],
    providers: [
      LoginService,
      Router,
      { provide: Router, useValue: RouterMock },
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA]
    });
    service = TestBed.inject(LoginService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should log in', () => {

    let spy = spyOn(service.loggedIn, 'next').and.callThrough();

    service.login(mockUser);

    expect(spy).toHaveBeenCalled();
  });

  it('should log out', () => {

    let spy = spyOn(service.loggedIn, 'next').and.callThrough();

    service.logout();

    expect(spy).toHaveBeenCalled();
  });
});
