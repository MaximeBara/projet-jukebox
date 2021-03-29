import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import {CommonModule} from '@angular/common';

import { HttpClientModule } from "@angular/common/http";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";

import {MenubarModule} from 'primeng/menubar';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {TabViewModule} from 'primeng/tabview';
import {PaginatorModule} from 'primeng/paginator';
import { MessagesModule } from 'primeng/messages';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { JukeboxPageComponent } from './Jukebox/jukebox-page/jukebox-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { MenuPageComponent } from './menu-page/menu-page.component';
import {TableModule} from 'primeng/table';
import { LoginComponent } from './login/login.component';
import { AboutComponent } from './about/about.component';
import { ProfileComponent } from './profile/profile.component';
import { ContacteComponent } from './contacte/contacte.component';
import { YoutubeComponent } from './Jukebox/youtube/youtube.component';
import { YouTubePlayerModule } from '@angular/youtube-player';
import { InscriptionComponent } from './inscription/inscription.component';
import { TitreComponent } from './titre/titre.component';
import { PlaylistComponent } from './playlist/playlist.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    MenuPageComponent,
    JukeboxPageComponent,
    ContacteComponent,
    ProfileComponent,
    LoginComponent,
    AboutComponent,
    YoutubeComponent,
    InscriptionComponent,
    TitreComponent,
    PlaylistComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    CommonModule,
    HttpClientModule,
    MenubarModule,
    InputTextModule,
    ButtonModule,
		TabViewModule,
    TableModule,
    ReactiveFormsModule,
    FormsModule,
    YouTubePlayerModule,
    PaginatorModule,
    MessagesModule,
    ConfirmDialogModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
