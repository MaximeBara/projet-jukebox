import { Playlist } from "./playlist";
import { Titre } from "./titre";

export interface Jukebox {
    id: number,
    nom: string,
    code: string,
    typeEnchere: string,

    titreEnCours: Titre,
    playlist: Playlist,
    connectes: string[],
    fans: string[],
    administrateur: string,
    encheres: string[]
}
