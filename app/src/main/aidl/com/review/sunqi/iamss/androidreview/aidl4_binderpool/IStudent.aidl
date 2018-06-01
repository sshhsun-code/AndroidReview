// IStudent.aidl
package com.review.sunqi.iamss.androidreview.aidl4_binderpool;

// Declare any non-default types here with import statements

interface IStudent {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    String getStudent(in int index);

}
