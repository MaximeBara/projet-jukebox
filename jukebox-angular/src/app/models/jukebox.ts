import { Playlist } from "./playlist";
import { Titre } from "./titre";
import { Profile } from "./user";

export interface Jukebox {
    id: number,
    nom: string,
    code: string,
    typeEnchere: string,

    titreEnCours: Titre,
    playlist: Playlist,
    connectes: Profile[],
    fans: Profile[],
    administrateur: Profile,
    encheres: string[]
}
