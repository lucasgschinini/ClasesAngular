import { Component } from '@angular/core';

@Component({
  selector: 'app-root', //es la etiqueta donde tenemos que poner en el html
  templateUrl: './app.component.html', // es el valor que vamos a usar en el html. si usamos template te pide que coloques el valor para mostrar
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'clase1';
}
