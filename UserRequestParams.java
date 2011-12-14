package com.entaz.controller;

import java.net.URLDecoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.entaz.base.BlowfishCryptoUtil;
import sun.misc.BASE64Decoder;

public class UserRequestParams
{
  private static boolean isLoginMode = true;

  public static boolean isLoginMode()
  {
    return isLoginMode;
  }

  public static void setLoginMode(boolean isLoginMode)
  {
    isLoginMode = isLoginMode;
  }

  public static String getUserNo(HttpServletRequest request)
  {
    String szUserNo = "";
    String szUserNoDecoded = null;

    if (isLoginMode)
    {
      Cookie[] cookies = request.getCookies();

      if (cookies != null)
      {
        for (int i = 0; i < cookies.length; ++i)
        {
          Cookie cookie = cookies[i];
          if (!(cookie.getName().equals("user_no"))) 
        	  continue; 
          szUserNo = cookie.getValue();
          byte[] bytDecodedUserNo = base64Decode(szUserNo);
          szUserNoDecoded = new String(bytDecodedUserNo);
        }
      }
    }
    else
    {
      szUserNo = request.getParameter("rphone");
    }

    return szUserNoDecoded;
  }
  
  public static String getNickName(HttpServletRequest request)
  {
    String szNickName = "";
    String szNickNameDecoded = null;

    if (isLoginMode)
    {
      Cookie[] cookies = request.getCookies();

      if (cookies != null)
      {
        for (int i = 0; i < cookies.length; ++i)
        {
          Cookie cookie = cookies[i];
          if (!(cookie.getName().equals("nickname"))) 
        	  continue; 
          szNickName = cookie.getValue();
          byte[] bytDecodedNickName = base64Decode(szNickName);
          szNickNameDecoded = new String(bytDecodedNickName);          
        }
      }
    }
    else
    {
    	szNickName = request.getParameter("rphone");
    }

    return szNickNameDecoded;
  }
  
  public static String getProfileNo(HttpServletRequest request)
  {
    String szPforileNo = "";
    String szPforileNoDecoded = null;

    if (isLoginMode)
    {
      Cookie[] cookies = request.getCookies();

      if (cookies != null)
      {
        for (int i = 0; i < cookies.length; ++i)
        {
          Cookie cookie = cookies[i];
          if (!(cookie.getName().equals("profile_no"))) 
        	  continue; 
          szPforileNo = cookie.getValue();
          byte[] bytDecodedNickName = base64Decode(szPforileNo);
          szPforileNoDecoded = new String(bytDecodedNickName);          
        }
      }
    }
    else
    {
    	szPforileNo = request.getParameter("rphone");
    }

    return szPforileNoDecoded;
  }
  
  public static String getCookie(HttpServletRequest request) throws Exception
  {
	  request.setCharacterEncoding("EUC-KR");
    String szCookieName = "";
    String strInputBase64Decode = null;

    if (isLoginMode)
    {
      Cookie[] cookies = request.getCookies();  
      
      if (cookies != null)
      {
        for (int i = 0; i < cookies.length; ++i)
        {
          Cookie cookie = cookies[i];
          if (!(cookie.getName().equals("_USERINFO_"))) 
        	  continue; 
          szCookieName = cookie.getValue();
          szCookieName = URLDecoder.decode(szCookieName, "UTF-8");

			String strInputDecode = BlowfishCryptoUtil.decryptBlowfish(szCookieName, "bill198210202230");
			byte[] byteInputBase64Decode = base64Decode(strInputDecode);
			strInputBase64Decode = new String(byteInputBase64Decode, "UTF-8");
		
        }
      }
    }
    else
    {
    	szCookieName = request.getParameter("rphone");
    }

    return strInputBase64Decode;
  }
  
  public static String getDeviceId(HttpServletRequest request) throws Exception
  {
	  request.setCharacterEncoding("euc-kr");
    String szDeviceId = "";

    if (isLoginMode)
    {
      Cookie[] cookies = request.getCookies();  
      
      if (cookies != null)
      {
        for (int i = 0; i < cookies.length; ++i)
        {
          Cookie cookie = cookies[i];
          if (!(cookie.getName().equals("_USERKEY_"))) 
        	  continue; 
          szDeviceId = cookie.getValue();
          szDeviceId = URLDecoder.decode(szDeviceId, "UTF-8");	
        }
      }
    }
    else
    {
    	szDeviceId = request.getParameter("rphone");
    }

    return szDeviceId;
  }
  
  public static byte[] base64Decode(String strDecode) {
		byte[] buf = null;
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();		
		
		try {
			BASE64Decoder base64Decoder = new BASE64Decoder();
			ByteArrayInputStream bin = new ByteArrayInputStream(strDecode.getBytes());
			base64Decoder.decodeBuffer(bin, bout);
		} catch (Exception e) {
		}
		buf = bout.toByteArray();
		return buf;
	}

  public static boolean isAutoLogin(HttpServletRequest request)
  {
    boolean isAutoLogin = false;

    if (isLoginMode)
    {
      Cookie[] cookies = request.getCookies();

      if (cookies != null)
      {
        for (int i = 0; i < cookies.length; ++i)
        {
          Cookie cookie = cookies[i];
          if ((!(cookie.getName().equals("VK"))) || 
            (!(cookie.getValue().equals("true")))) continue; isAutoLogin = true;
        }

      }

    }

    return isAutoLogin;
  }

  public static String getUserPhoneModel(HttpServletRequest request)
  {
    String szPhoneModel = "";
    szPhoneModel = request.getParameter("md");
    return szPhoneModel;
  }

  public static String getUserCarrier(HttpServletRequest request)
  {
    String szCarrier = "";
    szCarrier = request.getParameter("crr");
    return szCarrier;
  }

  public static String getUserHWPhoneNo(HttpServletRequest request)
  {
    String szUserHWPhoneNo = "";
    szUserHWPhoneNo = request.getParameter("rphone");
    return szUserHWPhoneNo;
  }

  public static String getUserPlatformVersion(HttpServletRequest request)
  {
    String szUserPlatformVersion = "";
    szUserPlatformVersion = request.getParameter("PVER");
    return szUserPlatformVersion;
  }

  public static String getUserHMPVersion(HttpServletRequest request)
  {
    String szUserHMPVersion = "";
    szUserHMPVersion = request.getParameter("HMPVer");
    return szUserHMPVersion;
  }
}