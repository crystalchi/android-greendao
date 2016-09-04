package com.crystal.aidlserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class PostMsgService extends Service {

    private MsgBinder mBinder;

    private CalBinder mCalBinder;

    @Override
    public IBinder onBind(Intent intent) {
        return mCalBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*mBinder = new MsgBinder();*/
        mCalBinder = new CalBinder();
    }

    @Override
    public void onDestroy() {
//        mBinder = null;
        mCalBinder = null;
        super.onDestroy();
    }

    public class MsgBinder extends IGetMsg.Stub{

        @Override
        public String getMsg(String msg) throws RemoteException {
            return msg;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }

    public class CalBinder extends ICalculate.Stub{
        @Override
        public double calSum(double a, double b) throws RemoteException {
            return a + b;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }

}
