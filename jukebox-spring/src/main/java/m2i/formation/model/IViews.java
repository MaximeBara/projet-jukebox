package m2i.formation.model;

public interface IViews {
	public static interface IViewBasic {
	}

	public static interface IViewJukebox extends IViewBasic {
	}
	
	public static interface IViewJukeboxWithPlaylist extends IViewJukebox {
	}

	public static interface IViewPlaylist extends IViewBasic {
	}

	public static interface IViewTitre extends IViewBasic {
	}
}
