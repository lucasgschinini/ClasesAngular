import { Component, OnInit } from '@angular/core';
import { timer } from 'rxjs';
import { TIMEOUT } from 'dns';

@Component({
  selector: 'app-entrada',
  templateUrl: './entrada.component.html',
  styleUrls: ['./entrada.component.css']
})
export class EntradaComponent implements OnInit {

  Contar: number = 0;

  resta(): number {
    if(this.Contar > 0){
      this.Contar = this.getContar() - 1;
    }
    return this.getContar();
  }

  getContar(): number {
    return this.Contar;
  }
  constructor() {
    
   }

  ngOnInit() {
  }

}
