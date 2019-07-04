import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-directivas',
  templateUrl: './directivas.component.html',
  styleUrls: ['./directivas.component.css']
})

//implements onInit: se ejecuta al iniciar la app
export class DirectivasComponent implements OnInit {

  Nombre: string = 'Lucas';// especifico el tipo
  Apellido = 'S';
  Presente: boolean=true;
  Observaciones: any ='clase de Angular';

  constructor() { 
    this.Nombre = this.getNombre();
    this.Apellido = this.getApellido();
    this.Presente = this.getPresente();
    this.Observaciones = this.getObservaciones();
  }

  getObservaciones(): any {
    return this.Observaciones;
  }
  getApellido(): string {
    return this.Apellido;
  }
  getPresente(): boolean {
    return this.Presente;
  }
  getNombre(): string {
    return this.Nombre;
  }

  ngOnInit() {
  }

}
