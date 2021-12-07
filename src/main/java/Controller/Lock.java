package Controller;

import java.util.HashMap;
import java.util.Map;

public class Lock {
    public static int count= 0;
    public static final Map<String, LockType> Locks = new HashMap<>();
    public enum LockType {
        NON_EXCLUSIVE,
        EXCLUSIVE
    }
    public static void applyLock(String databaseName,String tableName){
        String tablePath = databaseName + "." + tableName;
        LockType tableLockTypeApplied = Lock.Locks.get(tablePath);
        if(tableLockTypeApplied==null){
            Lock.Locks.put(tablePath,LockType.NON_EXCLUSIVE);
            Lock.count++;
        }
        if(tableLockTypeApplied == LockType.NON_EXCLUSIVE){
            Lock.count++;
        }

    }
    public static void releaseLock(String databaseName,String tableName){
        String tablePath = databaseName + "." + tableName;
        Lock.count--;
        if(Lock.count==0){
            Lock.Locks.remove(tablePath);
        }
    }
    public static void exclusiveLock(String databaseName,String tableName){
        String tablePath = databaseName + "." + tableName;
        LockType tableLockTypeApplied = Lock.Locks.get(tablePath);
        if (tableLockTypeApplied == null) {
            Lock.Locks.put(tablePath, Lock.LockType.EXCLUSIVE);
        }
    }

    public static void removeExclusiveLock(String databaseName,String tableName){
        String tablePath = databaseName + "." + tableName;
        Lock.Locks.remove(tablePath);
    }
}
