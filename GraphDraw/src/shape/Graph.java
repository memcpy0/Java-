package shape;

public abstract class Graph {
	private int gIdx;		// ͼ�����
	private int gShape;		// ͼ����״
	private String gName;	// ͼ������
	public Graph(int gIdx, int gShape, String gName) {
		this.gIdx = gIdx;
		this.gShape = gShape;
		this.gName = gName;
	}
	
	// ���Ʒ���
	public abstract void paint();
	
	public int getIdx() {
		return gIdx;
	}
	public int getShape() {
		return gShape;
	}
	public String getName() {
		return gName;
	}
	public void setIdx(int idx) {
		this.gIdx = idx;
	}
	public void setShape(int shape) {
		this.gShape = shape;
	}
	public void setName(String name) {
		this.gName = name;
	}
	
	// �����ڻ��Ʒ��������,��Ҫ����
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(gIdx) + " ");
		sb.append(String.valueOf(gShape) + " ");
		sb.append(gName);
		return sb.toString();
	}
}
