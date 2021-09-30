package data.dao.szamla;

import data.entity.Szamla;

public class BSzamlaDao extends SzamlaDao {

    @Override
    protected String getTableName() {
        return "IKTATO_B";
    }

    @Override
    protected Szamla.SzamlaType getType() {
        return Szamla.SzamlaType.B;
    }
}
