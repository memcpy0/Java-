import org.omg.PortableServer.*;

public class Server
{
	public static void main(String[] args)
	{
		try{
			//��ʼ��ORB
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
			//��ȡ��POA������
			POA rootPOA = POAHelper.narrow(
				orb.resolve_initial_references("RootPOA"));
			//�����־�POA�Ĳ���
			org.omg.CORBA.Policy[] policies = {
				rootPOA.create_lifespan_policy(LifespanPolicyValue.PERSISTENT),
				//rootPOA.create_id_assignment_policy(IdAssignmentPolicyValue.USER_ID)
			};
			//���¶���Ĳ��Դ���myPOA
			POA myPOA = rootPOA.create_POA("BankPOA", rootPOA.the_POAManager(), policies);
			//�����ŷ�����
			AccountManagerImpl managerServant = new AccountManagerImpl();
			//��myPOA���ñ�ʶ"BankManager"�����ŷ�����
			myPOA.activate_object_with_id("BankManager".getBytes(), managerServant);
			//����POA������
			rootPOA.the_POAManager().activate();
			//�ȴ�����ͻ����������
			System.out.println("�˻�����ԱBankManager�Ѿ���...\n");
			orb.run();
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
}