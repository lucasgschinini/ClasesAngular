import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

//modulo de routing
import {AppRoutingModule} from './app-routing.module';

import { AppComponent } from './app.component';

//modulo de firebase
import {AngularFireModule} from '@angular/fire';

import{AngularFirestoreModule} from '@angular/fire/firestore';

//modulo de conexion con base de datos(enviroments)
import {environment} from '../environments/environment';

//servicio de base de datos
import {DatabaseService} from '../app/services/database.service';

import {ContactComponent} from './components/contact/contact.component';

//modulo de formularios reactivos
import{ReactiveFormsModule} from '@angular/forms';
import { ListaComponent } from './components/lista/lista.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    ContactComponent,
    ListaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireModule,

    ReactiveFormsModule
    
  ],
  //los servicios van dentro de providers
  providers: [DatabaseService],
  bootstrap: [AppComponent]
})
export class AppModule { }
