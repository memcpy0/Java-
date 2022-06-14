public class Client
{
	// ʵ��main ���������ݲ������÷ֲ�ʽ�����getCallHistory ���ͨ����¼�Ĳ�ѯ
	public static void main(String[] args)
	{
		String name = args.length > 0? args[0]:"songshengli";
        //System.out.println(name);
		Database.DatabaseTableModel result = null;
		try {
			// ��Applet ȡ������
			String host = "//localhost/";
			// Զ�̶���ı�ʶ������������ע��ʱʹ�õĶ����ʶ��ȫ��ͬ
			String objectId = "CallManagerServant001";
			// ����������������ʶ����Զ�̶���
			Telephone.CallManagerInterface callManager = (Telephone.CallManagerInterface)
			java.rmi.Naming.lookup(host + objectId);
			// ����Զ�̶��󷽷���ѯ�û���ͨ����¼
			result = callManager.getCallHistory(name);
			result.printData();
		} catch(Exception exc) {
		System.out.println(exc.getMessage());
		System.exit(1);
		}
	}
}