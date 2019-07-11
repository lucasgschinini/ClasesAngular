import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-directivas',
  templateUrl: './directivas.component.html',
  styleUrls: ['./directivas.component.css']
})
export class DirectivasComponent implements OnInit {

  /* paises: string[] = ['Grecia','Italia','Japon']; */
  listaPaises: object = [
    {nombre:'Grecia',continente:'Europa',imagen:'../../assets/grecia.png'},
    {nombre:'Egipto',continente:'Africa',imagen:'../../assets/egipto.png'},
    {nombre:'Belgica',continente:'Europa',imagen:'../../assets/belgica.png'},
    {nombre:'???',continente:'Africa',imagen:'../../assets/bandera.png'}
]
  constructor() { }

  ngOnInit() {
  }

}
