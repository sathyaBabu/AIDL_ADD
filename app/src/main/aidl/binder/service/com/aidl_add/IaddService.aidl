// IaddService.aidl
package binder.service.com.aidl_add;

// Declare any non-default types here with import statements

interface IaddService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.

    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

            */

            int add( in int value1 , in int value2 ) ;


}
