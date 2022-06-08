import java.io.*;

public class TextFileCharacterCounter {
	private static File targetFile; // ��Ŀ���ļ����ַ�����ͳ��
	private static int enNum; // Ӣ���ַ�
	private static int zhNum; // �����ַ�

	public static void setFile(File f) {
		targetFile = f;
		enNum = zhNum = 0;
		try ( // ʹ��try with resource�﷨,�ڽ���ʱ�Զ��ر���
			FileReader rd = new FileReader(targetFile); // ʹ���ַ���������ͳ���ַ�
			BufferedReader bufRd = new BufferedReader(rd); // ʹ�û���,���ٴ��ļ���д�ĺ�ʱ
//			BufferedReader bufRd = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile), "utf-8"));
		) {
			String curLine;	// ÿ�δ����ж�ȡһ��
			while ((curLine = bufRd.readLine()) != null) {
				enNum += countEnglishCharacterInLine(curLine);
				zhNum += countChineseCharacterInLine(curLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int countEnglishCharacterInLine(String line) {
		int ec = 0;
		for (int i = 0; i < line.length(); ++i) {
			char c = line.charAt(i);
			if (Character.isUpperCase(c) || Character.isLowerCase(c)) { // ��д��ĸ��Сд��ĸ
				++ec; 
			}
		}
		return ec;
	}
	
	private static int countChineseCharacterInLine(String line) {
		int cc = 0;
		for (int i = 0; i < line.length(); ++i) {
			char c = line.charAt(i); // ��������ַ���Unicode�ű���ö�ٳ����Ƿ�ΪHAN(������)
			if (Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN) {
				++cc;
			}
		}
		return cc;
	}
	
	public static int englishNum() { return enNum; }
	public static int chineseNum() { return zhNum; }
}


