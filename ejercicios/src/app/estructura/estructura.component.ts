import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-estructura',
  templateUrl: './estructura.component.html',
  styleUrls: ['./estructura.component.css']
})
export class EstructuraComponent implements OnInit {

  cambiar: boolean = true;
  public urlImagen: string="../../assets/bandera1.png"

  constructor() { }

  ngOnInit() {
  }

}
