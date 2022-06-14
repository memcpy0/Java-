import Bank.*;
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
			AdapterActivator bankAA = new BankAdapterActivator()._this(orb);
			rootPOA.the_activator(bankAA);
			//�����־�POA�Ĳ���
			org.omg.CORBA.Policy[] policies = {
				rootPOA.create_lifespan_policy(LifespanPolicyValue.PERSISTENT),
				rootPOA.create_id_uniqueness_policy(IdUniquenessPolicyValue.MULTIPLE_ID),
				rootPOA.create_request_processing_policy(
					RequestProcessingPolicyValue.USE_DEFAULT_SERVANT)
			};
			//���¶���Ĳ��Դ���myPOA
			POA grandPOA = rootPOA.create_POA("GrandPOA", rootPOA.the_POAManager(), policies);
			POA parentPOA = grandPOA.create_POA("ParentPOA", rootPOA.the_POAManager(), policies);
			POA childPOA = parentPOA.create_POA("ChildPOA", rootPOA.the_POAManager(), policies);
			org.omg.CORBA.Object ior = childPOA.create_reference_with_id(
				"BankManager".getBytes(), "IDL:Bank/AccountManager:1.0");
			String iorString = orb.object_to_string(ior);
			//��IOR���浽�ļ��й��ͻ�����ʹ��
			try{
				java.io.PrintWriter writer = new java.io.PrintWriter(
					new java.io.FileWriter("BankIOR.dat"));
				writer.println(iorString);
				writer.close();
			}catch(java.io.IOException exc){
				System.out.println("��IORд���ļ�ʱ����" + exc.getMessage());
				return;
			}
			grandPOA.destroy(true,true);
			//����POA������
			rootPOA.the_POAManager().activate();
			//�ȴ�����ͻ����������
			System.out.println("�ʻ�����ԱBankManager�Ѿ���...\n");
			orb.run();
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
}