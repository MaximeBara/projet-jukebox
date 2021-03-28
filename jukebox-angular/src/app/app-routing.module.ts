import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JukeboxPageComponent } from './Jukebox/jukebox-page/jukebox-page.component';
import {​​​​​​​​ MenuPageComponent }​​​​​​​​ from'./menu-page/menu-page.component';
import {​​​​​​​​ HomePageComponent }​​​​​​​​ from'./home-page/home-page.component';
import {​​​​​​​​ TitreComponent }​​​​​​​​ from'./titre/titre.component';
import { AboutComponent } from './about/about.component';
import { ContacteComponent } from './contacte/contacte.component';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { PlaylistComponent } from './playlist/playlist.component';

const routes: Routes = [
  /* Routes de base */
  { path: '', component: MenuPageComponent, pathMatch: 'full' },
  { path: 'about', component: AboutComponent, pathMatch: 'full' },
  { path: 'contact', component: ContacteComponent, pathMatch: 'full' }, 
  { path: 'home', component: HomePageComponent, pathMatch: 'full' },
  { path: 'profile', component: ProfileComponent, pathMatch: 'full' },
  { path: 'login', component: LoginComponent, pathMatch: 'full' },
  { path: 'inscription', component: InscriptionComponent, pathMatch: 'full' },
  
  /* Routes pour les jukebox */
  // { path: 'jukebox', component: MyJukeboxPageComponent, pathMatch: 'full' },
  // { path: 'jukebox/create', component: CreateJukeboxPageComponent, pathMatch: 'full' },
  // { path: 'jukebox/join', component: JoinJukeboxPageComponent, pathMatch: 'full' },
  // { path: 'jukebox/favorites', component: CreateJukeboxPageComponent, pathMatch: 'full' },
  { path: 'jukebox/id', component: JukeboxPageComponent, pathMatch: 'full' },
  // { path: 'jukebox/:id/update', component: UpdateJukeboxPageComponent, pathMatch: 'full' },

  /* Routes pour les playlists */
   { path: 'playlists', component: PlaylistComponent, pathMatch: 'full' },
  // { path: 'playlists/create', component: CreatePlaylistPageComponent, pathMatch: 'full' },
  // { path: 'playlists/:id', component: PlaylistPageComponent, pathMatch: 'full' },
  // { path: 'playlists/:id/update', component: UpdatePlaylistPageComponent, pathMatch: 'full' },

  /* Routes pour les titres */
  { path: 'titres', component: TitreComponent, pathMatch: 'full' },
  // { path: 'titres/create', component: CreateTitrePageComponent, pathMatch: 'full' },

  /* Routes pour les enchères */
  // { path: 'encheres', component: EncheresPageComponent, pathMatch: 'full' },

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
