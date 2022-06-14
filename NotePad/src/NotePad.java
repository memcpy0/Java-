import java.util.ArrayList;
import java.util.Scanner;
/*
 * ���±��࣬ʵ�ֲ���������¼�Ĵ洢
 * �ܹ�����Ѿ��洢�ļ�¼������
 * �ܹ�׷�Ӽ�¼
 * �ܹ�չʾ�Ѿ��洢��ȫ����¼���������κ�һ����¼
 * �ܹ�ɾ���Ѿ��洢��ȫ����¼���������κ�һ����¼
 */
public class NotePad {
	private ArrayList<String> notePad;
	public NotePad() {
		notePad = new ArrayList<>();
	}
	
	// ���ؼ�¼����
	public int size() {
		return notePad.size();
	}
	
	// ��ȡһ����¼
	public String getNote(int index) {
		return notePad.get(index);
	}
	
	// ��ȡȫ����¼
	public ArrayList<String> getAllNotes() {
		ArrayList<String> result = new ArrayList<>();
		for (String note : notePad) {
			result.add(note);
		}
		return result;
	}
	
	// ׷��һ����¼�����
	public void addNote(String note) {
		notePad.add(note);	
	}
	
	// ɾ��һ����¼
	public void removeNote(int index) {
		notePad.remove(index);
	}
	
	// ɾ��ȫ����¼
	public void removeAllNotes() {
//		while (!notePad.isEmpty()) {
//			notePad.removeLast(); // ����ɾ�����һ����¼
//		}
		notePad.clear();
	}
	
	// �޸�һ����¼
	public void setNote(int index, String note) {
		notePad.set(index, note);
	}
} 