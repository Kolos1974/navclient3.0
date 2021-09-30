package data.dao.szamla;

import data.entity.Szamla;

public class VSzamlaDao extends SzamlaDao {

    @Override
    protected Szamla.SzamlaType getType() {
        return Szamla.SzamlaType.V;
    }

    @Override
    protected String getTableName() {
        return "IKTATO_V";
    }
}
