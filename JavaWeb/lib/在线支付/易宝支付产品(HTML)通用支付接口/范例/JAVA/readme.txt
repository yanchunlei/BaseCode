����Ӧ��˵��:
1. yeepay : �ױ�֧���ṩ�Ĳ���Ӧ��.�̻�ֻ��Ҫ���䲿��webӦ�÷������ķ���Ŀ¼�¼���.
 
�ļ�˵��:
1. yeepay----
              	|----index.html:�ṩ���̻����Ե�������Ҫ���� ����֧��,������ѯ,�˿�ӿ�.
              	|----pay.html:�ṩ���̻������õĵ�����ҳ��,�ύ��,�̻�����Ĳ������ύ�� reqpay.jsp.
              	|----reqpay.jsp���ύ�����ļ�,���ļ��������pay.html������Ĳ������ǩ����,Ȼ����һ��form����POST���������ױ�֧������.�̻�����Ķ����ļ�����ʹ��.
              	|----refund.html���ṩ���̻������õĵ�����ҳ��,�ύ��,�̻�����Ĳ������ύ�� reqRefund.jsp.
              	|----reqRefund.jsp���ύ�����ļ�,���ļ��������refund.html������Ĳ�����ȡ������PaymentForOnlineService.refundByTrxId����������
              	|----query.html���ṩ���̻������õĵ�����ҳ��,�ύ��,�̻�����Ĳ������ύ�� reqQuery.jsp.
              	|----reqQuery.jsp���ύ�����ļ�,���ļ��������query.html������Ĳ�����ȡ������PaymentForOnlineService.queryByOrder����������
	|----callback.jsp:���ļ���������ױ�֧�����صĽ��׽�����ݰ�,�̻�����Ķ�����ʹ��.
	|----WEB-INF----
			|----classes----
				|----merchantInfo.properties:�̻���Ϣ�����ļ�.
				|----log4j.properties:log4j�������ļ���ֻ�轫log4j.appender.R.File����������Ϊ�Լ�����log�ļ���·������.
					|----p1_MerId:�̻����
					|----keyValue:�̻���Կ
					|----onlinePaymentReqURL:����֧�������ַ
					|----queryRefundReqURL:��ѯ���˿�������ַ
			|----lib----
				|----YeePay_HTMLcommon_V3.0.jar:�ױ�֧���ṩ�����.�����õ�����������:
					1. PaymentForOnlineService.getReqMd5HmacForOnlinePayment:������������MD5-HMAC.
					2. PaymentForOnlineService.verifyCallback:У�鷵�������Ƿ���ȷ.
					3. PaymentForOnlineService.queryByOrder:������ѯ�������
					4. PaymentForOnlineService.refundByTrxId:�����˿��������
			
		
			
		