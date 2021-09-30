package data.dao.szamla;

import data.entity.Szamla;

public class OSzamlaDao extends SzamlaDao {
    @Override
    protected Szamla.SzamlaType getType() {
        return Szamla.SzamlaType.O;
    }

    @Override
    protected String getTableName() {
        return "IKTATO_O";
    }
}
