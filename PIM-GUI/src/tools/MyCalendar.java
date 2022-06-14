/**
 * �����û�������,���������������������������������
 * java MyCalendar 2025
 * java MyCalendar 4 2025
 * java MyCalendar 4 25 2025
 */
package tools;
import java.util.*;
import java.time.*;

/**
 * @author ��ƽ 21030540006
 * @since 2022/04/09
 */
public class MyCalendar {
	private static int[][] monthDay = {
		{0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
		{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	};
	private static String[] weekDay = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	/**
	 * �ж��Ƿ�Ϊ����
	 * @param y ��
	 * @return true��ʾ������
	 */
	private static boolean isLeapYear(int y) {
		return (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0));
	}
	
	/**
	 * ����ĳ��ĳ���м���
	 * @param y ��
	 * @param m ��
	 * @return ĳ��ĳ�µ�����
	 */
	private static int getMonthDay(int y, int m) {
		return isLeapYear(y) ? monthDay[0][m] : monthDay[1][m];
	}
	
	/**
	 * ʹ��Kim Larson's formula����ָ���������������ڼ�
	 * @param y ��
	 * @param m ��
	 * @param d ��
	 * @return 0~6, ��Ӧ��һ������
	 */
	private static int kimLarson(int y, int m, int d) {
		if (m == 1 || m == 2) {
			m += 12;
			--y;
		}
		return (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
	}
	
	/**
	 * ����һ���µ���������
	 * @param y ��
	 * @param m ��
	 * @return ĳ��ĳ�µ����� 
	 */
	public static String[][] getMonthCalendar(int y, int m) {
		// ��ʼ������һ�ܵ��ַ���,�������
		String[] outWeek = new String[7];
		// ��ʼ������һ���µ�����,�������
		// ����ϵͳ����������,ÿ�����ʱ��ʾ����
		int weekIdx = 0;
		String[][] outMonth = new String[6][7];
		
		// ������м���
		int dayOfMonth = getMonthDay(y, m);
		// ����µ�һ�������ڼ�
		int target = kimLarson(y, m, 1);
		// ��null������ÿ��1�ŵ�����
		for (int i = 0; i < target; ++i) {
			outWeek[i] = null;
		}
		
		// ����һ��ʣ�µ�����뵽��һ�ܵ��ַ���
		int day = 1;
		for (int i = target; i < 7; ++i) {
			outWeek[i] = String.format("%d", day++);
		}
		// ����һ�ܼ���������
		outMonth[weekIdx++] = outWeek;
		
		// �����������μ���������
		while (day <= dayOfMonth) {
			outWeek = new String[7];
			for (int i = 0; i < 7; ++i) {
				outWeek[i] = String.format("%d", day++); // �����ÿ���µ����һ��
				// ���һ��û�н���,�����¸���,����"����"��һ��
				if (day > dayOfMonth) {
					i = i + 1;
					for (; i < 7; ++i) {
						outWeek[i] = null;
					}
					break;
				}
			}
			outMonth[weekIdx++] = outWeek;
		}
		
		// δ��������Ҫ����
		while (weekIdx <= 5) {
			outMonth[weekIdx++] = new String[7];
		}
		return outMonth;
	}
}
