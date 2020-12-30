// IMyAidlInterface.aidl
package com.ghy.componentproject.aidl;

import com.ghy.componentproject.aidl.MyClick;
// Declare any non-default types here with import statements
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
             String getString();
                 void postString(String s);
    MyClick getMyClick();
                          void postMyClick(in MyClick s);


}