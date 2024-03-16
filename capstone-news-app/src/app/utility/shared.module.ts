
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';
import { GlobalInterceptor } from './global.interceptor';

@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        LoggerModule.forRoot({
            level: NgxLoggerLevel.DEBUG,
            serverLogLevel: NgxLoggerLevel.ERROR
        })
    ],
    exports: [
        CommonModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        LoggerModule
    ],
    providers: [
        // add GlobalInterceptor to providers
        {
            provide: HTTP_INTERCEPTORS,
            useClass: GlobalInterceptor,
            multi: true
        }
    ]
})
export class SharedModule { }