���Ȼ�ӭ����ѡ���ױ�֧���ṩ��֧��������񡣴�Ŀ¼�������������C#��д�ɣ�������ֱ�Ӱ������ļ�����VSӦ�õ�Ŀ¼�£����в������С�
1���ļ��б�˵��
	|-----------------------------------------------------
	|----Index.html:�ṩ���̻������õ���ҳ:�����õ�֧������ҳ��,�ύ���̻�����Ĳ������ύ���ױ�֧������Ӧ��������;������ѯ����ҳ��;�����˿���������ҳ��.)
	|----Req.aspx:(֧�������ļ���ͨ�����ļ�����֧�������̼ҿ����ڴ��ļ���д���Լ��Ķ�����Ϣ�ȣ�Ȼ��������ύ���ױ�֧����
	|----Callback.aspx:(֧����������ļ���ͨ�����ļ��̼��ж϶�Ӧ������֧���ɹ�״̬�����Ҹ��ݽ���޸��Լ����ݿ��еĶ���״̬��
	|----QueryOrderStatus.aspx:(������ѯ�ļ���ͨ�����ļ��̼Ҳ�ѯ������֧��״̬��
	|----Refund.aspx:(�����˿��ļ���ͨ�����ļ��̼��ύ�����˿�����
 	|----Web.config: �����ļ����������ñ��뷽ʽ  <globalization   requestEncoding="gb2312"   responseEncoding="gb2312"   /> 
	|----[Bin]
			|----com.yeepay.dll:�ױ�֧����װdll,��Ҫ��Ӧ������ӶԴ�dll�����á�
			API����˵�����������ռ�com.yeepay��
			
			|----ͨ�ýӿ�֧������
				����1������From����Buy.CreateForm(p1_MerId, keyValue, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse)
				����2������Url����Buy.CreateBuyUrl(p1_MerId, keyValue, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse)
				�������ͣ�string
				����˵����
					p1_MerId		�̼ұ��
					keyValue		�̼���Կ
					p2_Order		�̻�������
					p3_Amt			֧�����
					p4_Cur			���ױ���
					p5_Pid			��Ʒ����
					p6_Pcat			��Ʒ����
					p7_Pdesc		��Ʒ����
					p8_Url			�̻�����֧���ɹ����ݵĵ�ַ
					p9_SAF			�ͻ���ַ
					pa_MP				�̻���չ��Ϣ
					pd_FrpId		���б���
					pr_NeedResponse	Ӧ�����
					
			|----ͨ�ýӿ�֧��������� У�鷵�����ݰ�
				������Buy.VerifyCallback(p1_MerId, keyValue, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, hmac)
				�������ͣ�bool
				����˵����
					p1_MerId		�̼ұ��
					keyValue		�̼���Կ
					r0_Cmd			ҵ������
					r1_Code			���ش���
					r2_TrxId		�ױ�֧��������ˮ��
					r3_Amt			֧�����
					r4_Cur			���ױ���
					r5_Pid			��Ʒ����
					r6_Order		�̻�������
					r7_Uid			�ױ�֧����ԱID
					r8_MP				�̻���չ��Ϣ
					r9_BType		���׽����������
					hmac				ǩ������
					
			|----��ѯ������ϸ
				������Buy.QueryOrdDetail(p1_MerId, keyValue, p2_Order)
				�������ͣ�BuyQueryOrdDetailResult
				����˵����
					p1_MerId		�̼ұ��
					keyValue		�̼���Կ
					p2_Order		�̻�������
				���ز�����
					r0_Cmd			ҵ������
					r1_Code			��ѯ���
					r2_TrxId		�ױ�֧��������ˮ��
					r3_Amt			֧�����
					r4_Cur			���ױ���
					r5_Pid			��Ʒ����
					r6_Order		�̻�������
					r8_MP				�̻���չ��Ϣ
					rb_PayStatus		֧��״̬
					rc_RefundCount	���˿����
					rd_RefundAmt		���˿���
					hmac				ǩ������

					
			|----�����˿���ϸ
				������Buy.RefundOrd(p1_MerId, keyValue, pb_TrxId, p3_Amt, "CNY", p5_Desc)
				�������ͣ�BuyRefundOrdResult
				����˵����
					p1_MerId		�̼ұ��
					keyValue		�̼���Կ
					pb_TrxId		�ױ�֧��������ˮ��
					p3_Amt			�˿���
					p4_Cur			���ױ���
					p5_Pdesc		�˿�˵��
				���ز�����
					r0_Cmd			ҵ������
					r1_Code			��ѯ���
					r2_TrxId		�ױ�������ˮ��
					r3_Amt			�˿���
					r4_Cur			���ױ���
					hmac				ǩ������


					
2���̼Ҳ��Կ��������ױ�֧���Ĳ����̼Ҳ��Գɹ������ļ����޸ĳ��Լ����̻���ź���Կ��Ϣ�������ʺ�����:
  p1_MerId = "10000432521";
	keyValue = "8UPp0KE8sq73zVP370vko7C39403rtK1YwX40Td6irH216036H27Eb12792t";
	�̻���ź���Կ��Ҫͬʱ�޸Ĳ���Ч��

3������Req.aspx�ļ����޸������ַ,ʹ�ò��Ի������в��Ժ��ٻ�������������ʽʹ��֧���ӿڡ�
  // ���������ַ
  Buy.NodeAuthorizationURL = @"http://tech.yeepay.com:8080/robot/debug.action"; //���Ի���
  Buy.NodeAuthorizationURL = @"https://www.yeepay.com/app-merchant-proxy/node";   //��������
	
4���ױ�֧��֧�����̼�ѡ�����к����ױ�֧������ѡ�����е����ַ�ʽ��
	����ͨ���趨Req.aspx��pd_FrpId��ֵ�����е�����
	�磺pd_FrpId=""   					���ױ�֧��֧��������ѡ������
			pd_FrpId="1000000-NET"	ֱ�ӽ����ױ�֧����Ա֧��
			pd_FrpId="ICBC-NET"			ֱ�ӽ����й�������������֧��
			pd_FrpId="SZX-NET"					ֱ�ӽ��������п�����֧��
	�������б����ο��ױ�֧�����ĵ�˵����