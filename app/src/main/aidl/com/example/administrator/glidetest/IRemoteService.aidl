// IRemoteService.aidl
package com.example.administrator.glidetest;

import com.example.administrator.glidetest.HelloMsg;

// Declare any non-default types here with import statements

interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    HelloMsg sayHello();
}
