import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from './login.service';


@Component({
    selector: 'login-cmp',
    moduleId: module.id,
    templateUrl: 'login.component.html'
})

export class LoginComponent implements OnInit {

    loginForm: FormGroup;

    constructor(private fb: FormBuilder, private router: Router, private userService: UserService) {

    }

    ngOnInit() {
        this.initForm();
    }

    initForm() {
        this.loginForm = this.fb.group({
            username: ['', Validators.required],
            password: ['', Validators.required],
        });
    }

    onSubmit() {
        if (this.loginForm.valid) {
            this.userService.login(this.loginForm.get('username')?.value, this.loginForm.get('password')?.value).subscribe(
                (response: any) => {
                    if (response) {
                        console.log('Login success', response);

                        const specificValue = response.role;
                        console.log('Value of yourKey:', specificValue);
                        const userID = this.loginForm.get('username')?.value;
                        localStorage.setItem("role", specificValue);
                        localStorage.setItem("username",userID)
                        const usernameValue = this.loginForm.get('username')?.value;
                        const passwordValue = this.loginForm.get('password')?.value;
                        console.log('Username:', usernameValue);
                        console.log('Password:', passwordValue);

                        if (specificValue == 'ROLE_ADMIN') {
                            this.router.navigate(['/admin-dashboard']);
                        }
                        else if (specificValue == 'ROLE_USER') {
                            this.router.navigate(['/user-dashboard']);
                        }
                        else if (specificValue == 'ROLE_MANAGER') {
                            this.router.navigate(['/user-dashboard']);
                        }
                        else if (specificValue == 'ROLE_DADMIN') {
                            this.router.navigate(['/user-dashboard']);
                        }
                        else if (specificValue == 'ROLE_SYS_ADMIN') {   
                            this.router.navigate(['/sys-admin-dashboard']);
                        }
                    }
                },
                (error) => {
                    console.error('Login failed', error);
                    // Handle the error appropriately, e.g., show an error message to the user
                }
            );

        }
    }
}
