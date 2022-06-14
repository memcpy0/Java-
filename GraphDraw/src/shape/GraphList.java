package shape;
import java.util.ArrayList;
import java.math.*;

public class GraphList {
	private ArrayList<Graph> gList; // ͼ������
	public GraphList() {
		gList = new ArrayList<>();
	}
	
	// ��Graph�б�ĩβ���Graph����
	public void append(int gShape, String gName) {
		switch (gShape) {
		case 1: // ��ʾ����
			gList.add(new Rectangle(gList.size(), gShape, gName));
			break;
		case 2: // ��ʾԲ��
			gList.add(new Circle(gList.size(), gShape, gName));
			break;
		case 3: // ��ʾ������
			gList.add(new Triangle(gList.size(), gShape, gName));
			break;
		}
	}
	// �����û��ṩ����ż���ͼ��
	public Graph getGraphByIndex(int index) {
		return gList.get(index);
	}
	// �����û��ṩ�����ּ���ͼ��
	public Graph getGraphByName(String name) {
		for (Graph g : gList) {
			if (name.equals(g.getName()))
				return g;
		}
		return null;
	}
	
	// �����û��ṩ�����,���Ƹ���ż�֮ǰ������ͼ��
	public void paintShapes(int index) {
		rangePaint(0, index + 1);
	}
	// ˽�з���,�ڲ�ʹ��
	private void rangePaint(int off, int len) {
		if (off < 0 || off >= gList.size()) 
			throw new IllegalArgumentException("Illegal Argument");
		len = Math.min(off + len, gList.size());
		for (int i = off; i < len; ++i) {
			gList.get(i).paint();
		}
	}
}
