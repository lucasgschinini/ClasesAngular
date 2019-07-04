"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var EntradaComponent = /** @class */ (function () {
    function EntradaComponent() {
        this.Contar = 0;
    }
    EntradaComponent.prototype.resta = function () {
        if (this.Contar > 0) {
            this.Contar = this.getContar() - 1;
        }
        return this.getContar();
    };
    EntradaComponent.prototype.getContar = function () {
        return this.Contar;
    };
    EntradaComponent.prototype.ngOnInit = function () {
    };
    EntradaComponent = __decorate([
        core_1.Component({
            selector: 'app-entrada',
            templateUrl: './entrada.component.html',
            styleUrls: ['./entrada.component.css']
        })
    ], EntradaComponent);
    return EntradaComponent;
}());
exports.EntradaComponent = EntradaComponent;
