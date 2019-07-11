import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ComponentesComponent } from './componentes/componentes.component';
import { EstructuraComponent } from './estructura/estructura.component';
import { FormulariosComponent } from './formularios/formularios.component';
import { DirectivasComponent } from './directivas/directivas.component';

@NgModule({
  declarations: [
    AppComponent,
    ComponentesComponent,
    EstructuraComponent,
    FormulariosComponent,
    DirectivasComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
