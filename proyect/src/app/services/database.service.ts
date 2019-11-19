//modulo que establece la clase como un servicio inyectable
import { Injectable } from '@angular/core';

//
import {AngularFirestore,AngularFirestoreCollection} from '@angular/fire/firestore';
import {Messageinterface} from '../models/messageinterface';

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {

  private contactCollection: AngularFirestoreCollection<Messageinterface>;

  constructor( private afs : AngularFirestore) {
    this.contactCollection = afs.collection<Messageinterface>('contacts');
   }

   saveMessage(newContact:any):void{
      this.contactCollection.add(newContact);
   }
}
