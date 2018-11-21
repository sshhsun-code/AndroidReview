// IMyAidl.aidl
package com.review.sunqi.iamss.androidreview.adil_Test.bean;

// Declare any non-default types here with import statements

import com.review.sunqi.iamss.androidreview.adil_Test.bean.Person;

import com.review.sunqi.iamss.androidreview.book_test.IOnNewBookArrivedListener;

interface IMyAidl {
    /**
     * 除了基本数据类型，其他类型的参数都需要标上方向类型：in(输入), out(输出), inout(输入输出)
     */
    void addPerson(in Person person);

    List<Person> getPersonList();

    Person getPerson();

    void registerListener(IOnNewBookArrivedListener listener);

    void unRegisterListener(IOnNewBookArrivedListener listener);
}