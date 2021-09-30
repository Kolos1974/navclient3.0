package data.dao.szamla;

import data.entity.Szamla;

public class PSzamlaDao extends SzamlaDao {

    @Override
    protected Szamla.SzamlaType getType() {
        return Szamla.SzamlaType.P;
    }

    @Override
    protected String getTableName() {
        return "IKTATO_P";
    }
}
