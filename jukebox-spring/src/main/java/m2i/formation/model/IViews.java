package m2i.formation.model;

public interface IViews {
	public static interface IViewBasic {
	}

	public static interface IViewJukebox extends IViewBasic {
	}

	public static interface IViewJukeboxWithPlaylist extends IViewJukebox {
	}
	
	public static interface IViewJukeboxWithPlaylistAndTitre extends IViewJukeboxWithPlaylist, IViewPlaylistWithTitre{
	}

	public static interface IViewPlaylist extends IViewBasic {
	}
	
	public static interface IViewPlaylistWithTitre extends IViewPlaylist {
	}
	
	public static interface IViewTitre extends IViewBasic {
	}

	public static interface IViewEnchere extends IViewBasic {
	}

	public static interface IViewEnchereWithMembre extends IViewEnchere {
	}

	public static interface IViewEnchereWithJukebox extends IViewEnchere {
	}

	public static interface IViewEnchereWithTitre extends IViewEnchere {
	}
}
