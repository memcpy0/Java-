package abstract_class_ver;
// ������
public class PIMNote extends PIMEntity {
	String noteText;
	
	// ����Note���ı�
	public String getNoteText() {
		return noteText;
	}
	// ����Note���ı�
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	
	public void fromString(String ex) {
		
	}
	
	public String toString() {
		return ("Item " + PIMManager.itemCount + ": NOTE " + Priority + " " + noteText);
	}
}
