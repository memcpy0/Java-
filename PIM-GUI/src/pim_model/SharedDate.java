package pim_model;

import java.time.LocalDate;

/**
 * <p>��Ŀ���ƣ�PIM GUI</p>
 * <p>�ӿ����ƣ�PIMEntity</p>
 * ����ʱ�䣺2022��5��30�� <br>
 * ��������PIMTodo��PIMAppointment��Ҫʵ�ֵĽӿ�
 * @author����ƽ
 */
public interface SharedDate {
	public LocalDate getDate();
	public void setDate(LocalDate date);
}

