import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { KanbaListComponent } from './kanbas/kanba-list/kanba-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/kanbas', pathMatch: 'full'},
  { path: 'kanbas', component: KanbaListComponent },

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
