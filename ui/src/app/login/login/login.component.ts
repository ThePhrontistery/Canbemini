import { Kanban } from './../../kanbas/model/Kanban';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { environment } from 'src/environments/environment';
import { LoginService } from '../login.service';
import { KanbasService } from 'src/app/kanbas/kanbas.service';

declare var google:any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  hide = true;
  form: FormGroup;
  private formSubmitAttempt: boolean;
  code: string;
  sharedKanban: Kanban;

  constructor(
    private fb: FormBuilder,
    public loginService: LoginService,
    private activatedRoute: ActivatedRoute,
    private kanbanService: KanbasService
  ) {}

  ngOnInit(): void {
    if (this.activatedRoute.snapshot.params.code != null) {
      this.code = this.activatedRoute.snapshot.params.code;

      this.kanbanService.getByCode(this.code).subscribe((result) => {
        this.sharedKanban = result;
      });
    }
    this.form = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    });

    //this.googleInit();
  }

  /*
  ngAfterViewInit(): void {
    
    this.googleInit();

  }
  
  
  googleInit(){
    google.accounts.id.initialize({
      client_id: environment.google,
      callback: this.handleCredentialResponse,
    });
    google.accounts.id.renderButton(
      document.getElementById("buttonDiv"),
      { theme: "outline", size: "large" }  // customization attributes
    );
    google.accounts.id.prompt(); // also display the One Tap dialog
  }
  */
  handleCredentialResponse(response: any) {
    if (response.credential) {
      sessionStorage.setItem('token', response.credential);
      document.location.href = '/check';
    }
  }

  isFieldInvalid(field: string) {
    return (
      (!this.form.get(field).valid && this.form.get(field).touched) ||
      (this.form.get(field).untouched && this.formSubmitAttempt)
    );
  }

  private callLogin(credential) {
    this.loginService.loginGoogle(credential);
  }

  onSubmit() {
    if (this.form.valid) {
      this.loginService.login(this.form.value, this.sharedKanban);
    }
    this.formSubmitAttempt = true;
  }
}


