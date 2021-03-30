import { Jukebox } from "./jukebox";
import { Titre } from "./titre";
import { Profile } from "./user";

export interface Playlist {
    id: number,
    nom: string,
    dateCreation: Date,
    lien: string,
    createur: Profile,
    titres: Titre[],
    jukeboxes: Jukebox[]
}