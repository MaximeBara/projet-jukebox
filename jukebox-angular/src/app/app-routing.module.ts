import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  /* Routes de base */
  { path: '', component: HomePageComponent, pathMatch: 'full' },
  { path: 'login', component: LoginPageComponent, pathMatch: 'full' },
  { path: 'about', component: AboutPageComponent, pathMatch: 'full' },
  { path: 'contact', component: ContactPageComponent, pathMatch: 'full' },
  
  /* Routes pour les jukebox */
  { path: 'jukebox', component: MyJukeboxPageComponent, pathMatch: 'full' },
  { path: 'jukebox/create', component: CreateJukeboxPageComponent, pathMatch: 'full' },
  { path: 'jukebox/join', component: JoinJukeboxPageComponent, pathMatch: 'full' },
  { path: 'jukebox/favorites', component: CreateJukeboxPageComponent, pathMatch: 'full' },
  { path: 'jukebox/:id', component: JukeboxPageComponent, pathMatch: 'full' },
  { path: 'jukebox/:id/update', component: UpdateJukeboxPageComponent, pathMatch: 'full' },

  /* Routes pour les playlists */
  { path: 'playlists', component: PlaylistsPageComponent, pathMatch: 'full' },
  { path: 'playlists/create', component: CreatePlaylistPageComponent, pathMatch: 'full' },
  { path: 'playlists/:id', component: PlaylistPageComponent, pathMatch: 'full' },
  { path: 'playlists/:id/update', component: PlaylistPageComponent, pathMatch: 'full' },

  /* Routes pour les titres */
  { path: 'titres', component: TitresPageComponent, pathMatch: 'full' },
  { path: 'titres/create', component: CreateTitrePageComponent, pathMatch: 'full' },

  /* Routes pour les enchères */
  { path: 'encheres', component: EncheresPageComponent, pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
