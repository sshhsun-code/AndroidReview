// IOnNewBookArrivedListener.aidl
package com.review.sunqi.iamss.androidreview.book_test;

// Declare any non-default types here with import statements
import com.review.sunqi.iamss.androidreview.book_test.Book;

interface IOnNewBookArrivedListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void onNewBookArrived(in Book newBook);
}
