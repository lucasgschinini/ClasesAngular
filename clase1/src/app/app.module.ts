import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { DirectivasComponent } from './componentes/directivas/directivas.component';
import { EntradaComponent } from './componentes/entrada/entrada.component';

@NgModule({
  declarations: [
    AppComponent,
    DirectivasComponent,
    EntradaComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
