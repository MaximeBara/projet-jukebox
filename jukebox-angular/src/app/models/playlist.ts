import { Jukebox } from "./jukebox";
import { Titre } from "./titre";

export interface Playlist {
    id: number,
    nom: string,
    dateCreation: Date,
    lien: string,

    createur: string,
    titres: Titre[],
    jukeboxes: Jukebox[]
}
