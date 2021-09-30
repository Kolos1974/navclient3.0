package data.dao.szamla;

import data.entity.Szamla;

public class KSzamlaDao extends SzamlaDao {

    @Override
    protected Szamla.SzamlaType getType() {
        return Szamla.SzamlaType.K;
    }

    @Override
    protected String getTableName() {
        return "IKTATO_K";
    }
}
