package pim_model;

import java.util.*;
import java.io.*;

/**
 * <p>��Ŀ���ƣ�PIM GUI</p>
 * <p>�����ƣ�PIMManager</p>
 * ����ʱ�䣺2022��5��30�� <br>
 * ��������PIMEntity�б�Ĺ�����,�����л��洢�ͷ����л��洢����
 * @author����ƽ
 */
public class PIMManager {
	private String dataFilePath = "PIMDatabase.dat";
	private File dataFile = null;
	private ArrayList<PIMEntity> itemList;
	
	public PIMManager() throws IOException {
		dataFile = new File(dataFilePath);
		itemList = new ArrayList<>();
		if (!dataFile.exists()) { // ��������ļ�������
			dataFile.createNewFile(); // �½�һ�������ļ�
		} else loadData(); // ����ʱ��Ϊ,��������ļ�����,���������
	}
	 
	public String getDataFilePath() {
		return dataFilePath;
	}
	
	public void setDataFilePath(String other) throws IOException {
		dataFilePath = other;
		dataFile = new File(dataFilePath);
		if (!dataFile.exists()) { // ��������ļ�������
			dataFile.createNewFile(); // �½�һ�������ļ�
		}
	}
	
	public void addPIMEntity(PIMEntity p) {
		itemList.add(p);
	}
	
	public ArrayList<PIMEntity> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<PIMEntity> pl) {
		itemList = pl;
	}
	
	// ��itemList���������еĶ��󱣴浽�ļ���
	public void saveData() {
		if (dataFile.canWrite()) { // ��д�ļ�
			try (					
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));
			) {
				oos.writeObject(itemList); // ���л����϶���
				oos.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// �������ļ������л�,�������ɶ�������itemList
	public void loadData() {
		if (dataFile.canRead() && dataFile.length() > 0) { // �ɶ��ļ�,���ȴ���0ʱ��ȡ
			try (
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile));
			) {
				itemList = (ArrayList<PIMEntity>)ois.readObject(); // ���������л�
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}