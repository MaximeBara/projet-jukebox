import { Jukebox } from "./jukebox";
import { Titre } from "./titre";
import { Profile } from "./user";

export interface Enchere {
    id: number,
    dateTime: Date,
    valeur: number,
    terminee: boolean,
    user: Profile,
    jukebox: Jukebox,
    titre: Titre
}