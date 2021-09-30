package data.dao.szamla;

import data.entity.Szamla;

public class ZSzamlaDao extends SzamlaDao {

    @Override
    protected Szamla.SzamlaType getType() {
        return Szamla.SzamlaType.Z;
    }

    @Override
    protected String getTableName() {
        return "IKTATO_Z";
    }
}
