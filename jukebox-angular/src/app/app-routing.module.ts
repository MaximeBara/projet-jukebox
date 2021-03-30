import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MembreGuard } from './helpers/membre.guard';
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
import { CreatePlaylistComponent } from './playlist/create-playlist/create-playlist.component';

const routes: Routes = [
  /* Routes de base */
  { path: 'menu', component: MenuPageComponent, pathMatch: 'full' },
  { path: 'about', component: AboutComponent, pathMatch: 'full' },
  { path: 'contact', component: ContacteComponent, pathMatch: 'full' }, 
  { path: '', component: HomePageComponent, pathMatch: 'full' },
  { path: 'profile', component: ProfileComponent, pathMatch: 'full', canActivate: [MembreGuard] },
  { path: 'login', component: LoginComponent, pathMatch: 'full' },
  { path: 'inscription', component: InscriptionComponent, pathMatch: 'full' },
  
  /* Routes pour les jukebox */
  // { path: 'jukebox', component: MyJukeboxPageComponent, pathMatch: 'full' },
  // { path: 'jukebox/create', component: CreateJukeboxPageComponent, pathMatch: 'full' },
  // { path: 'jukebox/join', component: JoinJukeboxPageComponent, pathMatch: 'full' },
  // { path: 'jukebox/favorites', component: CreateJukeboxPageComponent, pathMatch: 'full' },
  { path: 'jukebox/:id', component: JukeboxPageComponent, pathMatch: 'full' },
  // { path: 'jukebox/:id/update', component: UpdateJukeboxPageComponent, pathMatch: 'full' },

  /* Routes pour les playlists */
   { path: 'playlists', component: PlaylistComponent, pathMatch: 'full', canActivate: [MembreGuard] },
   { path: 'playlists/create', component: CreatePlaylistComponent, pathMatch: 'full', canActivate: [MembreGuard] },
   { path: 'playlists/:id', component: TitreComponent, pathMatch: 'full', data: { kind: 'fromPlaylists', canActivate: [MembreGuard] } },
  // { path: 'playlists/:id/update', component: UpdatePlaylistPageComponent, pathMatch: 'full', canActivate: [MembreGuard] },

  /* Routes pour les titres */
  { path: 'titres', component: TitreComponent, pathMatch: 'full', canActivate: [MembreGuard] },
  // { path: 'titres/create', component: CreateTitrePageComponent, pathMatch: 'full', canActivate: [MembreGuard] },

  /* Routes pour les enchères */
  // { path: 'encheres', component: EncheresPageComponent, pathMatch: 'full', canActivate: [MembreGuard] },

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
