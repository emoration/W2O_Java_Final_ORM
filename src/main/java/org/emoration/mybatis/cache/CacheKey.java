package org.emoration.mybatis.cache;

import org.emoration.mybatis.reflection.ArrayUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/8/20
 */
public class CacheKey implements Cloneable, Serializable {
    private static final long serialVersionUID = 1146682552656046210L;
    public static final CacheKey NULL_CACHE_KEY = new CacheKey() {
        public void update(Object object) {
            throw new RuntimeException("Not allowed to update a null cache key instance.");
        }

        public void updateAll(Object[] objects) {
            throw new RuntimeException("Not allowed to update a null cache key instance.");
        }
    };
    private static final int DEFAULT_MULTIPLIER = 37;
    private static final int DEFAULT_HASHCODE = 17;
    private final int multiplier;
    private int hashcode;
    private long checksum;
    private int count;
    private List<Object> updateList;

    public CacheKey() {
        this.hashcode = DEFAULT_HASHCODE;
        this.multiplier = DEFAULT_MULTIPLIER;
        this.count = 0;
        this.updateList = new ArrayList();
    }

    public CacheKey(Object[] objects) {
        this();
        this.updateAll(objects);
    }

    public int getUpdateCount() {
        return this.updateList.size();
    }

    public void update(Object object) {
        int baseHashCode = object == null ? 1 : ArrayUtil.hashCode(object);
        ++this.count;
        this.checksum += (long)baseHashCode;
        baseHashCode *= this.count;
        this.hashcode = this.multiplier * this.hashcode + baseHashCode;
        this.updateList.add(object);
    }

    public void updateAll(Object[] objects) {
        Object[] var2 = objects;
        int var3 = objects.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Object o = var2[var4];
            this.update(o);
        }

    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof CacheKey)) {
            return false;
        } else {
            CacheKey cacheKey = (CacheKey)object;
            if (this.hashcode != cacheKey.hashcode) {
                return false;
            } else if (this.checksum != cacheKey.checksum) {
                return false;
            } else if (this.count != cacheKey.count) {
                return false;
            } else {
                for(int i = 0; i < this.updateList.size(); ++i) {
                    Object thisObject = this.updateList.get(i);
                    Object thatObject = cacheKey.updateList.get(i);
                    if (!ArrayUtil.equals(thisObject, thatObject)) {
                        return false;
                    }
                }

                return true;
            }
        }
    }

    public int hashCode() {
        return this.hashcode;
    }

    public String toString() {
        StringJoiner returnValue = new StringJoiner(":");
        returnValue.add(String.valueOf(this.hashcode));
        returnValue.add(String.valueOf(this.checksum));
        this.updateList.stream().map(ArrayUtil::toString).forEach(returnValue::add);
        return returnValue.toString();
    }

    public CacheKey clone() throws CloneNotSupportedException {
        CacheKey clonedCacheKey = (CacheKey)super.clone();
        clonedCacheKey.updateList = new ArrayList(this.updateList);
        return clonedCacheKey;
    }
}
