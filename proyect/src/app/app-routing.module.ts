import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ContactComponent} from './components/contact/contact.component';
import {ListaComponent} from './components/lista/lista.component';
import { AppComponent } from './app.component';


const routes: Routes = [
    {path:'contacto',component:ContactComponent},
    {path:'lista',component:ListaComponent},
    {path:'**',component:AppComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
