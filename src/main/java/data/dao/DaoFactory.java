package data.dao;

import data.dao.szamla.*;
import data.entity.Szamla;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {

    private static Map<Szamla.SzamlaType, SzamlaDao> szamlaDaoMap;
    public static Map<Szamla.SzamlaType, SzamlaDao> getSzamlaDaoMap() {
        if (szamlaDaoMap == null) {
            szamlaDaoMap = new HashMap<>();
            szamlaDaoMap.put(Szamla.SzamlaType.B, DaoFactory.bSzamlaDao());
            szamlaDaoMap.put(Szamla.SzamlaType.G, DaoFactory.gSzamlaDao());
            szamlaDaoMap.put(Szamla.SzamlaType.K, DaoFactory.kSzamlaDao());
            szamlaDaoMap.put(Szamla.SzamlaType.P, DaoFactory.pSzamlaDao());
            szamlaDaoMap.put(Szamla.SzamlaType.V, DaoFactory.vSzamlaDao());
            szamlaDaoMap.put(Szamla.SzamlaType.Z, DaoFactory.zSzamlaDao());
            szamlaDaoMap.put(Szamla.SzamlaType.O, DaoFactory.oSzamlaDao());
            szamlaDaoMap.put(Szamla.SzamlaType.DV, DaoFactory.dvSzamlaDao());
        }
        return szamlaDaoMap;
    }

    private static BSzamlaDao bSzamlaDao;
    public static BSzamlaDao bSzamlaDao() {
        if (bSzamlaDao == null) {
            bSzamlaDao = new BSzamlaDao();
        }
        return bSzamlaDao;
    }

    private static GSzamlaDao gSzamlaDao;
    public static GSzamlaDao gSzamlaDao() {
        if (gSzamlaDao == null) {
            gSzamlaDao = new GSzamlaDao();
        }
        return gSzamlaDao;
    }

    private static KSzamlaDao kSzamlaDao;
    public static KSzamlaDao kSzamlaDao() {
        if (kSzamlaDao == null) {
            kSzamlaDao = new KSzamlaDao();
        }
        return kSzamlaDao;
    }

    private static PSzamlaDao pSzamlaDao;
    public static PSzamlaDao pSzamlaDao() {
        if (pSzamlaDao == null) {
            pSzamlaDao = new PSzamlaDao();
        }
        return pSzamlaDao;
    }

    private static VSzamlaDao vSzamlaDao;
    public static VSzamlaDao vSzamlaDao() {
        if (vSzamlaDao == null) {
            vSzamlaDao = new VSzamlaDao();
        }
        return vSzamlaDao;
    }

    private static ZSzamlaDao zSzamlaDao;
    public static ZSzamlaDao zSzamlaDao() {
        if (zSzamlaDao == null) {
            zSzamlaDao = new ZSzamlaDao();
        }
        return zSzamlaDao;
    }

    private static OSzamlaDao oSzamlaDao;
    public static OSzamlaDao oSzamlaDao() {
        if (oSzamlaDao == null) {
            oSzamlaDao = new OSzamlaDao();
        }
        return oSzamlaDao;
    }

    private static DVSzamlaDao dvSzamlaDao;
    public static DVSzamlaDao dvSzamlaDao() {
        if (dvSzamlaDao == null) {
            dvSzamlaDao = new DVSzamlaDao();
        }
        return dvSzamlaDao;
    }

}
