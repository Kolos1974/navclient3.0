package data.dao.szamla;

import data.entity.Szamla;

public class GSzamlaDao extends SzamlaDao {

    @Override
    protected Szamla.SzamlaType getType() {
        return Szamla.SzamlaType.G;
    }

    @Override
    protected String getTableName() {
        return "IKTATO_G";
    }
}
