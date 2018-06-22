package com.review.sunqi.iamss.androidreview.ashmem_test;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/**
 * Created by sunqi on 2018/6/22.
 */

public interface IMemoryService extends IInterface {

    static abstract class Stub extends Binder implements IMemoryService {
        private static final java.lang.String DESCRIPTOR = "com.review.sunqi.iamss.androidreview.ashmem_test.IMemoryService";

        static final int TRANSACTION_setValue = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_getFileDescriptor = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static com.review.sunqi.iamss.androidreview.ashmem_test.IMemoryService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }

            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if ((iin != null) && iin instanceof  IMemoryService) {
                return (IMemoryService) iin;
            }
            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_setValue: {
                    data.enforceInterface(DESCRIPTOR);
                    int value;
                    value = data.readInt();
                    this.setValue(value);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getFileDescriptor: {
                    data.enforceInterface(DESCRIPTOR);
                    ParcelFileDescriptor descriptor = this.getFileDescriptor();
                    reply.writeNoException();
                    if ((descriptor != null)) {
                        reply.writeInt(1);
                        descriptor.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements IMemoryService {

            private IBinder mRemote;

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            public java.lang.String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public ParcelFileDescriptor getFileDescriptor() throws RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                ParcelFileDescriptor descriptor;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getFileDescriptor, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        descriptor = ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                    } else {
                        descriptor = null;
                    }
                } finally {
                    _data.recycle();
                    _reply.recycle();
                }
                return descriptor;
            }

            @Override
            public void setValue(int val) throws RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(val);
                    mRemote.transact(Stub.TRANSACTION_setValue, _data, _reply, 0);
                } finally {
                    _data.recycle();
                    _reply.recycle();
                }
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }
        }
    }

    public ParcelFileDescriptor getFileDescriptor() throws RemoteException;
    public void setValue(int val) throws RemoteException;

}
