package pim_model;

/**
 * <p>��Ŀ���ƣ�PIM GUI</p>
 * <p>�����ƣ�PIMNote</p>
 * ����ʱ�䣺2022��5��30�� <br>
 * ��������PIMEntity�ı�������
 * @author����ƽ
 */
public class PIMNote extends PIMEntity {
	String noteText;
	
	public PIMNote() {
		type = "Note";
	}
	
	public PIMNote(String note, String prior) {
		type = "Note";
		this.noteText = note;
		this.priority = prior;
	}
	
	// ����Note���ı�
	public String getNoteText() {
		return noteText;
	}
	// ����Note���ı�
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	} 
	
	public String toString() {
		return ("NOTE " + priority + " " + noteText);
	}
	
    public boolean equals(PIMEntity p) {
    	if (p.getType().equals(type)) {
    		PIMNote pa = (PIMNote)p;
    		return pa.noteText.equals(noteText) && pa.priority.equals(priority);
    	}
    	return false;
    }
    
    public void setEntity(PIMEntity p) {
    	PIMNote pa = (PIMNote)p;
    	this.noteText = pa.noteText;
    	this.priority = pa.priority;
    }
}
