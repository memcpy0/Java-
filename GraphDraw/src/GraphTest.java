import java.util.*;
import shape.Graph;
import shape.GraphList;

public class GraphTest {
	public static void main(String[] args) {
		GraphList list = new GraphList();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("======================");
			System.out.println("||    1. ����ͼ��    ||");
			System.out.println("||    2. ����ͼ��    ||");
			System.out.println("||    3. ����ͼ��    ||");
			System.out.println("||    4. �˳�ϵͳ    ||");
			System.out.println("======================");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("(����:1, Բ��:2, ������:3)");
				System.out.print("������һ��ͼ�����ͱ�ţ�");
				int shape = sc.nextInt();
				sc.nextLine();
				System.out.print("�������ͼ�ε����ƣ�");
				String name = sc.nextLine();
				list.append(shape, name);	
				System.out.println("ͼ�δ����ɹ�!");
				break;
			case 2:
				System.out.print("������Ҫ������ͼ�����ƣ�");
				sc.nextLine();
				String name1 = sc.nextLine();
				Graph g = list.getGraphByName(name1);
				if (g != null) list.getGraphByName(name1).paint();
				else System.out.println("����������Ϊ" + name1 + "��ͼ��!");
				break;
			case 3:
				System.out.print("�����봴����ţ�");
				int index = sc.nextInt();
				System.out.println("����˳����Ƹ���ż�֮ǰ��ȫ��ͼ�Σ�");
				try {
					list.paintShapes(index);
				} catch (Exception e) {
					System.out.println("�����˴������ţ�");
				}
				break;
			case 4:
				sc.close();
				return;
			}	
		}
	}
}
