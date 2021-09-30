package data.dao.szamla;

import data.entity.Szamla;

public class DVSzamlaDao extends SzamlaDao {
    @Override
    protected String getTableName() {
        return "IKTATO_DV";
    }

    @Override
    protected Szamla.SzamlaType getType() {
        return Szamla.SzamlaType.DV;
    }
}
