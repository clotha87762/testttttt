package com.example.testyo;




public class ScreenScale {
		
	static  float sHpWidth=320;//��l��ù����e��
	static  float sHpHeight=240;//��l��ù�������
	static  float whHpRatio=sHpWidth/sHpHeight;//��l��ù����e����
	
	static  float sSpWidth=240;//��l�����ù����e��
	static  float sSpHeight=320;//��l�����ù�������
	static  float whSpRatio=sSpWidth/sSpHeight;//��l�����ù����e����
	
	public static void calScale
	(
		float targetWidth,	//�ؼмe��
		float targetHeight	//�ؼа���
	)
	{
	
		 sHpWidth=Constant.WIDTH;//��l��ù����e��
		 sHpHeight=Constant.HEIGHT;//��l��ù�������
		 whHpRatio=sHpWidth/sHpHeight;//��l��ù����e����
		
		 sSpWidth=Constant.WIDTH;//��l�����ù����e��
		 sSpHeight=Constant.HEIGHT;//��l�����ù�������
		 whSpRatio=sSpWidth/sSpHeight;//��l�����ù����e����
		//�����P�_�ؼЬO��ù��٬O�����ù�
		/*if(targetWidth>targetHeight)
		{
			Constant.pattern=Constant.whichPattern.Horizonal;
		}
		else
		{
			Constant.pattern=Constant.whichPattern.Vertical;
		}*/
		
		//�i���ù����G���p��
		/*if(Constant.pattern==Constant.whichPattern.Horizonal)
		{
			//�p��ؼЪ��e����
			float targetRatio=targetWidth/targetHeight;
			
			if(targetRatio>whHpRatio)
			{
				//�Y�ؼмe����j���l�e����h�H�ؼЪ����׭p�⵲�G			    
			    float ratio=targetHeight/sHpHeight;
			    float realTargetWidth=sHpWidth*ratio;
			    float lcuX=(targetWidth-realTargetWidth)/2.0f;
			    float lcuY=0;
			   Constant.LCUX = (int)lcuX;
			   Constant.LCUY = (int)lcuY;
			   Constant.RATIO=ratio;
			}
			else
			{
				//�Y�ؼмe����p���l�e����h�H�ؼЪ��e�׭p�⵲�G	
				float ratio=targetWidth/sHpWidth;
				float realTargetHeight=sHpHeight*ratio;
				float lcuX=0;
				float lcuY=(targetHeight-realTargetHeight)/2.0f;
				Constant.LCUX = (int)lcuX;
				   Constant.LCUY = (int)lcuY;
				   Constant.RATIO=ratio;
			}
		}
		*/
		//�i�檽���ù����G���p��
		//if(Constant.pattern==Constant.whichPattern.Vertical)
		
			//�p��ؼЪ��e����
			float targetRatio=targetWidth/targetHeight;
			
			if(targetRatio>whSpRatio)
			{
				//�Y�ؼмe����j���l�e����h�H�ؼЪ����׭p�⵲�G			    
			    float ratio=targetHeight/sSpHeight;
			    float realTargetWidth=sSpWidth*ratio;
			    float lcuX=(targetWidth-realTargetWidth)/2.0f;
			    float lcuY=0;
			    Constant.LCUX = (int)lcuX;
				   Constant.LCUY = (int)lcuY;
				   Constant.RATIO=ratio;
			}
			else
			{
				//�Y�ؼмe����p���l�e����h�H�ؼЪ��e�׭p�⵲�G	
				float ratio=targetWidth/sSpWidth;
				float realTargetHeight=sSpHeight*ratio;
				float lcuX=0;
				float lcuY=(targetHeight-realTargetHeight)/2.0f;
				Constant.LCUX = (int)lcuX;
				   Constant.LCUY = (int)lcuY;
				   Constant.RATIO=ratio;
			}
		
		
		Constant.RATIOX = targetWidth/sSpHeight;
		Constant.RATIOY = targetHeight/sSpWidth;
		return;
	}
}
