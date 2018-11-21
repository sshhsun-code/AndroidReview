// IBook.aidl
package com.review.sunqi.iamss.androidreview.aidl4_binderpool;
import com.review.sunqi.iamss.androidreview.book_test.IOnNewBookArrivedListener;
// Declare any non-default types here with import statements

interface IBook {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    String getBook(in int num);

    void registerListener(IOnNewBookArrivedListener listener);
    void unRegisterListener(IOnNewBookArrivedListener listener);
}
