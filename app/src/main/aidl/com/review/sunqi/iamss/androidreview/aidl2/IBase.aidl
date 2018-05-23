// IBase.aidl
package com.review.sunqi.iamss.androidreview.aidl2;

// Declare any non-default types here with import statements

import com.review.sunqi.iamss.androidreview.aidl2.UserInfo;
interface IBase {

   int add();
   String getUserInfo(in UserInfo userInfo);
   void getList(out String[] list);
   void setList(in String[] list);
   void getSList(in String[] list);
}
