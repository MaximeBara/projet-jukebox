import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JukeboxPageComponent } from './Jukebox/jukebox-page/jukebox-page.component';
import {​​​​​​​​ LoginPageComponent }​​​​​​​​ from'./login-page/login-page.component';
import {​​​​​​​​ HomePageComponent }​​​​​​​​ from'./home-page/home-page.component';
import {​​​​​​​​ TitrePageComponent }​​​​​​​​ from'./titre-page/titre-page.component';

const routes: Routes = [
  /* Routes de base */
  { path: 'login', component: LoginPageComponent, pathMatch: 'full' },
  { path: 'about', component: AboutComponent, pathMatch: 'full' },
  { path: 'contact', component: ContacteComponent, pathMatch: 'full' }, 
  { path: 'home', component: HomePageComponent, pathMatch: 'full' },
  { path: 'profile', component: ProfileComponent, pathMatch: 'full' },
  { path: '', component: LoginComponent, pathMatch: 'full' },
  
  /* Routes pour les jukebox */
  // { path: 'jukebox', component: MyJukeboxPageComponent, pathMatch: 'full' },
  // { path: 'jukebox/create', component: CreateJukeboxPageComponent, pathMatch: 'full' },
  // { path: 'jukebox/join', component: JoinJukeboxPageComponent, pathMatch: 'full' },
  // { path: 'jukebox/favorites', component: CreateJukeboxPageComponent, pathMatch: 'full' },
  { path: 'jukebox/id', component: JukeboxPageComponent, pathMatch: 'full' },
  // { path: 'jukebox/:id/update', component: UpdateJukeboxPageComponent, pathMatch: 'full' },

  /* Routes pour les playlists */
  // { path: 'playlists', component: PlaylistsPageComponent, pathMatch: 'full' },
  // { path: 'playlists/create', component: CreatePlaylistPageComponent, pathMatch: 'full' },
  // { path: 'playlists/:id', component: PlaylistPageComponent, pathMatch: 'full' },
  // { path: 'playlists/:id/update', component: UpdatePlaylistPageComponent, pathMatch: 'full' },

  /* Routes pour les titres */
  { path: 'titres', component: TitrePageComponent, pathMatch: 'full' },
  // { path: 'titres/create', component: CreateTitrePageComponent, pathMatch: 'full' },

  /* Routes pour les enchères */
  // { path: 'encheres', component: EncheresPageComponent, pathMatch: 'full' },

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
