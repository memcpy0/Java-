import java.util.Scanner;
import java.io.*;

public class CounterTest {
	public static void main(String[] args) {
		System.out.print("������ͳ��ָ���ı��ļ��е���Ӣ���ַ�������");
		System.out.println("������ָ���ı��ļ�������·����");
		Scanner sc = new Scanner(System.in);
		String filePath = sc.nextLine();
		
		try {
			// ��̬�����ı��ļ��ַ���������,ͳ���ַ�����
			TextFileCharacterCounter.setFile(new File(filePath)); // �����ַ�������Ŀ���ļ�
			System.out.println("Ӣ���ַ�����Ϊ(����������): " + TextFileCharacterCounter.englishNum());
			System.out.println("�����ַ�����Ϊ(����������): " + TextFileCharacterCounter.chineseNum());
		} catch (Exception e) {
			System.out.println("������ɶ�ȡ�ı��ļ�����ȷ·����");
			e.printStackTrace();
		}
	}
}

