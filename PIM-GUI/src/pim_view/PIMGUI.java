package pim_view;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * <p>��Ŀ���ƣ�PIM GUI
 * <p>�����ƣ�PIMGUI
 * ����ʱ�䣺2022��5��31�� <br>
 * �����������������ڵ�ʵ��
 * @author����ƽ
 */
public class PIMGUI {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			PIMFrame frame;
			try {
				frame = new PIMFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		});
	}
}
