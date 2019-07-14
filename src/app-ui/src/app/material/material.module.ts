import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import * as Material from '@angular/material'


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    Material.MatToolbarModule,
    Material.MatGridListModule,
    Material.MatFormFieldModule,
    Material.MatInputModule,
    Material.MatRadioModule,
    Material.MatSelectModule,
    Material.MatButtonModule,
    Material.MatRadioModule,
    Material.MatTooltipModule,
    Material.MatToolbarModule,
    Material.MatSidenavModule,
    Material.MatIconModule,
    Material.MatListModule

  ],
  exports: [
    Material.MatToolbarModule,
    Material.MatGridListModule,
    Material.MatFormFieldModule,
    Material.MatInputModule,
    Material.MatRadioModule,
    Material.MatSelectModule,
    Material.MatButtonModule,
    Material.MatRadioModule,
    Material.MatTooltipModule,
    Material.MatToolbarModule,
    Material.MatSidenavModule,
    Material.MatIconModule,
    Material.MatListModule
  ]

})
export class MaterialModule { }
