import java.util.Scanner;
import java.util.ArrayList;
/*
 * ���±����Ժͽ����࣬ʵ�ּ��±������û��Ľ���
 * ���û���ʾ������Ϣ�������û�������
 * 1. ��ӣ�2. ɾ����3. ������4. չʾ
 */
public class NotePadTest {
	public static void main(String[] args) {
		NotePad pad = new NotePad();
		Scanner sc = new Scanner(System.in);
		System.out.println("��ӭʹ�ü��±�����");
		int op, idx;
		boolean flag = true;
		do {
			System.out.println("==================================");
			System.out.println("������Ҫִ�еĹ��ܱ�ţ�");
			System.out.println("1. ��Ӽ�¼");
			System.out.println("2. ɾ����¼"); // ������ȫ����¼
			System.out.println("3. ������¼"); // ������ȫ����¼
			System.out.println("4. �޸ļ�¼"); 
			System.out.println("5. ��ʾ�Ѽ�¼��");
			System.out.println("6. �˳�");
			
			op = sc.nextInt();
			switch (op) {
			case 1:
				System.out.print("�������¼���ݣ�");
				String s = sc.next();
				pad.addNote(s);
				System.out.println("��¼��ӳɹ���");
				break;
			case 2:
				System.out.print("������Ҫɾ���ڼ�����¼������0ɾ��ȫ����¼����");
				idx = sc.nextInt();
				if (idx == 0) pad.removeAllNotes();
				else if (idx > 0 && idx <= pad.size()) {
					pad.removeNote(idx - 1);
					System.out.println("��" + idx + "����¼ɾ���ɹ���");
				} else System.out.println("�޸�����¼��");
				break;
			case 3:
				System.out.print("������Ҫ��ʾ�ڼ�����¼������0��ʾȫ����¼����");
				idx = sc.nextInt();
				if(idx == 0){
					ArrayList<String> ls = pad.getAllNotes();
					int i = 0;
					for(String s1 : ls) {
						System.out.println(i + ". " + s1);
						++i;
					}
				} else if (idx > 0 && idx <= pad.size()) {
					System.out.println("��"+ idx + "����¼��" + pad.getNote(idx - 1));
				} else System.out.println("�޸�����¼��");
				break;
			case 4:
				System.out.print("������Ҫ�޸ĵڼ�����¼��");
				idx = sc.nextInt();
				if (idx > 0 && idx <= pad.size()) {
					System.out.print("�������޸ĺ�����ݣ�");
					String str = sc.next();
					pad.setNote(idx - 1, str);
					System.out.println("��¼�޸ĳɹ���");
				} else System.out.println("�޸�����¼��");
				break;
			case 5:
				System.out.println("Ŀǰһ����" + pad.size() + "����¼��");
				break;
			case 6:
				System.out.println("�����˳�����");
				break;
			default:
				System.out.println("����������������룡");
				break;
			}	
		}  while (op != 6);
		sc.close();
	}
}
