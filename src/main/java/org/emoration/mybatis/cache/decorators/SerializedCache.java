package org.emoration.mybatis.cache.decorators;

//import org.emoration.mybatis.cache.Cache;
//
//import java.io.*;

/**
 * @Author czh
 * @Description TODO 可以找个库来实现
 * @Date 2023/8/19
 */
public class SerializedCache{

}
//public class SerializedCache implements Cache {
//    private final Cache delegate;
//
//    public SerializedCache(Cache delegate) {
//        this.delegate = delegate;
//    }
//
//    public String getId() {
//        return this.delegate.getId();
//    }
//
//    public int getSize() {
//        return this.delegate.getSize();
//    }
//
//    public void putObject(Object key, Object object) {
//        if (object != null && !(object instanceof Serializable)) {
//            throw new RuntimeException("SharedCache failed to make a copy of a non-serializable object: " + object);
//        } else {
//            this.delegate.putObject(key, this.serialize((Serializable)object));
//        }
//    }
//
//    public Object getObject(Object key) {
//        Object object = this.delegate.getObject(key);
//        return object == null ? null : this.deserialize((byte[])((byte[])object));
//    }
//
//    public Object removeObject(Object key) {
//        return this.delegate.removeObject(key);
//    }
//
//    public void clear() {
//        this.delegate.clear();
//    }
//
//    public int hashCode() {
//        return this.delegate.hashCode();
//    }
//
//    public boolean equals(Object obj) {
//        return this.delegate.equals(obj);
//    }
//
//    private byte[] serialize(Serializable value) {
//        try {
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            Throwable var3 = null;
//
//            Object var6;
//            try {
//                ObjectOutputStream oos = new ObjectOutputStream(bos);
//                Throwable var5 = null;
//
//                try {
//                    oos.writeObject(value);
//                    oos.flush();
//                    var6 = bos.toByteArray();
//                } catch (Throwable var31) {
//                    var6 = var31;
//                    var5 = var31;
//                    throw var31;
//                } finally {
//                    if (oos != null) {
//                        if (var5 != null) {
//                            try {
//                                oos.close();
//                            } catch (Throwable var30) {
//                                var5.addSuppressed(var30);
//                            }
//                        } else {
//                            oos.close();
//                        }
//                    }
//
//                }
//            } catch (Throwable var33) {
//                var3 = var33;
//                throw var33;
//            } finally {
//                if (bos != null) {
//                    if (var3 != null) {
//                        try {
//                            bos.close();
//                        } catch (Throwable var29) {
//                            var3.addSuppressed(var29);
//                        }
//                    } else {
//                        bos.close();
//                    }
//                }
//
//            }
//
//            return (byte[])var6;
//        } catch (Exception var35) {
//            throw new RuntimeException("Error serializing object.  Cause: " + var35, var35);
//        }
//    }
//
//    private Serializable deserialize(byte[] value) {
//        SerialFilterChecker.check();
//
//        try {
//            ByteArrayInputStream bis = new ByteArrayInputStream(value);
//            Throwable var4 = null;
//
//            Serializable result;
//            try {
//                ObjectInputStream ois = new CustomObjectInputStream(bis);
//                Throwable var6 = null;
//
//                try {
//                    result = (Serializable)ois.readObject();
//                } catch (Throwable var31) {
//                    var6 = var31;
//                    throw var31;
//                } finally {
//                    if (ois != null) {
//                        if (var6 != null) {
//                            try {
//                                ois.close();
//                            } catch (Throwable var30) {
//                                var6.addSuppressed(var30);
//                            }
//                        } else {
//                            ois.close();
//                        }
//                    }
//
//                }
//            } catch (Throwable var33) {
//                var4 = var33;
//                throw var33;
//            } finally {
//                if (bis != null) {
//                    if (var4 != null) {
//                        try {
//                            bis.close();
//                        } catch (Throwable var29) {
//                            var4.addSuppressed(var29);
//                        }
//                    } else {
//                        bis.close();
//                    }
//                }
//
//            }
//
//            return result;
//        } catch (Exception var35) {
//            throw new RuntimeException("Error deserializing object.  Cause: " + var35, var35);
//        }
//    }
//
//    public static class CustomObjectInputStream extends ObjectInputStream {
//        public CustomObjectInputStream(InputStream in) throws IOException {
//            super(in);
//        }
//
//        protected Class<?> resolveClass(ObjectStreamClass desc) throws ClassNotFoundException {
//            return Resources.classForName(desc.getName());
//        }
//    }
//}
