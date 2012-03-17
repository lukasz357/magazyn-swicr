package marek;
import javax.swing.JPanel;


/* pojedynczy kwadrat magazynu */
public class MagazynJPanel extends JPanel {
	private static final long serialVersionUID = -8989252675289611871L;

	private Boolean isShelf = false;		//czy dany kwadrat magazynu jest rega�em
	private Boolean isEntryPoint = false;	//czy jest punktem odbioru
	
	
	
	public Boolean getIsShelf() {
		return isShelf;
	}

	public void setIsShelf(Boolean isShelf) {
		this.isShelf = isShelf;
	}

	public Boolean getIsEntryPoint() {
		return isEntryPoint;
	}

	public void setIsEntryPoint(Boolean isEntryPoint) {
		this.isEntryPoint = isEntryPoint;
	}
}
