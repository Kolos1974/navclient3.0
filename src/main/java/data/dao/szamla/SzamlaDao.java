package data.dao.szamla;

import config.Config;
import data.dao.BaseDao;
import data.entity.Szamla;
import data.entity.SzamlaTetel;
import data.entity.VeSza;
import exception.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SzamlaDao extends BaseDao {

    public List<Szamla> getAll() throws DatabaseException {

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT TOP 10 * FROM " + getTableName() +
                             " INNER JOIN Ve_sza ON Ve_sza.VE_SZAKOD = " + getTableName() + ".VE_SZAKOD " +
                             "WHERE (" + getTableName() + ".STATUS IS NULL OR "
                             + getTableName() + ".STATUS = '')" +
                             "AND " + getTableName() + ".LEZART = 1 " +
                             "AND " + getTableName() + ".SZDAT >= ? " +
                         //  "AND abs(" + getTableName() + ".AFA_OSSZEG) >= ? " +
                             "AND abs(" + getTableName() + ".AFA_OSSZEG) >= ? " 
                         //  "AND Ve_sza.OrszagKod = 'HU' " +
                         //  "AND (Ve_sza.ADOSZAM >= '1' AND Ve_sza.ADOSZAM <= '9')" 
             )) {
            pstmt.setTimestamp(1, Config.navInvoiceDate);
            pstmt.setInt(2, Config.navMinTax);
            try (ResultSet szamlaResultSet = pstmt.executeQuery()) {
                List<Szamla> szamlak = new ArrayList<>();
                while (szamlaResultSet.next()) {
                    Szamla szamla = getSzamlaFromResultSet(szamlaResultSet);
                    szamla.setVeSza(getVeszaFromResultSet(szamlaResultSet));

                    PreparedStatement preparedStatementSajatBank = conn.prepareStatement(
                            "SELECT SzamlaSzam FROM Sajat_Bank WHERE Azonosito = ?");
                    preparedStatementSajatBank.setInt(1, szamlaResultSet.getInt("S_BANK_AZONOSITO"));
                    ResultSet sajatBankResultSet = preparedStatementSajatBank.executeQuery();
                    if (sajatBankResultSet.next()) szamla.setSzamlaSzam(sajatBankResultSet.getString("SzamlaSzam"));

                    PreparedStatement preparedStatementTetel = conn.prepareStatement(
                            "SELECT * FROM " + getTetelTableName()
                                    + " INNER JOIN Cikkek ON Cikkek.Cikkszam = " + getTetelTableName() + ".Cikkszam"
                                    + " WHERE IKTSZAM = ? ORDER BY TETELSORSZ");
                    if (szamla.getType() == Szamla.SzamlaType.V || szamla.getType() == Szamla.SzamlaType.DV)
                        preparedStatementTetel.setInt(1, Integer.parseInt(szamla.getIktSzam()));
                    else preparedStatementTetel.setString(1, szamla.getIktSzam());
                    ResultSet tetelResultSet = preparedStatementTetel.executeQuery();
                    while (tetelResultSet.next()) {
                        szamla.addTetel(getSzamlaTetelFromResultSet(tetelResultSet));
                    }

                    if (szamla.isModified()) {
                        String modE = szamla.getModEredeti();
                        PreparedStatement eredetiCountPst = conn.prepareStatement("SELECT COUNT(*) as count " +
                                        "FROM " + getTetelTableName() + " t " +
                                "INNER JOIN " + getTableName() + " sz ON t.IKTSZAM = sz.IKTSZAM " +
                                "WHERE sz.IKTSZAM < '" + szamla.getIktSzam() + "' AND " +
                                "( sz.IKTSZAM = '" + modE + "'" +
                                " OR sz.Mod_Eredeti = '" + modE + "'" +
                                " OR sz.St_Eredeti = '" + modE + "')");
                        ResultSet countResultSet = eredetiCountPst.executeQuery();
                        if (countResultSet.next()) szamla.setEredetiTetelCount(countResultSet.getInt("count"));
                    }

                    szamla.calculateSummeries();
                    szamlak.add(szamla);
                }
                return szamlak;
            }
        } catch (SQLException e) {
            throw new DatabaseException(getTableName() + ": " + e.getMessage());
        }
    }

    public boolean updateStatusz(String id, String status) throws DatabaseException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE " + getTableName() + " SET STATUS = ? WHERE IKTSZAM = ?")
        ) {
            preparedStatement.setString(1, status);
            if (getType() == Szamla.SzamlaType.V || getType() == Szamla.SzamlaType.DV)
                preparedStatement.setInt(2, Integer.parseInt(id));
            else preparedStatement.setString(2, id);
            return preparedStatement.execute();
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException(getTableName() + ": " + e.getMessage());
        }
    }

    private Szamla getSzamlaFromResultSet(ResultSet rs) throws SQLException {
        Szamla szamla = new Szamla();
        szamla.setType(getType());
        if (getType() == Szamla.SzamlaType.V || getType() == Szamla.SzamlaType.DV) {
            szamla.setIktSzam(Integer.toString(rs.getInt("IKTSZAM")).trim());
            int stEredeti = rs.getInt("ST_Eredeti");
            szamla.setStEredeti(rs.wasNull() ? null : Integer.toString(stEredeti).trim());
            int modEredeti = rs.getInt("Mod_Eredeti");
            szamla.setModEredeti(rs.wasNull() ? null : Integer.toString(modEredeti).trim());
            if (getType() == Szamla.SzamlaType.DV) {
                szamla.setDevizaNem(rs.getString("Devizanem"));
                szamla.setExchangeRate(rs.getDouble("Ertek"));
            } else {
                szamla.setExchangeRate(1);
            }
        } else {
            szamla.setIktSzam(rs.getString("IKTSZAM").trim());
            String stEredeti = rs.getString("ST_Eredeti");
            szamla.setStEredeti(stEredeti != null ? stEredeti.trim() : null);
            String modEredeti = rs.getString("Mod_Eredeti");
            szamla.setModEredeti(modEredeti != null ? modEredeti.trim() : null);
            szamla.setSzidoszTol(rs.getTimestamp("SZIDOSZTOL"));
            szamla.setSzidoszIg(rs.getTimestamp("SZIDOSZIG"));
            szamla.setExchangeRate(1);
        }
        szamla.setSzekod(rs.getString("SZEKOD"));
        szamla.setSzdat(rs.getTimestamp("SZDAT"));
        szamla.setFizmodkod(rs.getString("FIZMODKOD"));
        szamla.setEsdat(rs.getTimestamp("ESDAT"));
        szamla.setTeljdat(rs.getTimestamp("TELJDAT"));
        szamla.setBekdat(rs.getTimestamp("BEKDAT"));
        return szamla;
    }

    private VeSza getVeszaFromResultSet(ResultSet rs) throws SQLException {
        VeSza veSza = new VeSza();
        veSza.setMegnev(rs.getString("MEGNEV"));
        veSza.setVeSzakod(rs.getString("VE_SZAKOD"));
        if (rs.getString("ADOSZAM")==null)
        {
           veSza.setAdoszam("");
        } else
        {	
           veSza.setAdoszam(rs.getString("ADOSZAM"));
        }
        
        if (rs.getString("CSOPADOSZAM")==null)
        {
           veSza.setCsopAdoszam("");
        } else
        {	
           veSza.setCsopAdoszam(rs.getString("CSOPADOSZAM"));
        }  
        
        if (rs.getString("ADOAZON")==null)
        {
           veSza.setAdoAzon("");
        } else
        {	
           veSza.setAdoAzon(rs.getString("ADOAZON"));
        }  
        
        if (rs.getString("EUADOSZAM")==null)
        {
           veSza.setEuAdoszam("");
        } else
        {	
           veSza.setEuAdoszam(rs.getString("EUADOSZAM"));
        }  
        
        if (rs.getString("MAGANSZEMELY")==null)
        {
           veSza.setMaganszemely(false);
        } else
        {	
           veSza.setMaganszemely(rs.getBoolean("MAGANSZEMELY"));
        }  
        // veSza.setCsopAdoszam(rs.getString("CSOPADOSZAM"));
        // veSza.setAdoAzon(rs.getString("ADOAZON"));
        // veSza.setEuAdoszam(rs.getString("EUADOSZAM"));
        veSza.setIrsz(rs.getString("IRSZ"));
        veSza.setHelyseg(rs.getString("HELYSEG"));
        veSza.setUtca(rs.getString("UTCA"));
        veSza.setKozterulet(rs.getString("KOZTERULET"));
        veSza.setHazszam(rs.getString("HAZSZAM"));
        veSza.setOrszagkod(rs.getString("ORSZAGKOD"));
        return veSza;
    }

    private SzamlaTetel getSzamlaTetelFromResultSet(ResultSet rs) throws SQLException {
        SzamlaTetel tetel = new SzamlaTetel();
        tetel.setTetelsorsz(rs.getInt("TETELSORSZ"));
        tetel.setMegnev(rs.getString("MEGNEV"));
        tetel.setMennyiseg(rs.getBigDecimal("MENNYISEG"));
        tetel.setMe(rs.getString("ME"));
        tetel.setEgysegAr(rs.getBigDecimal("EGYSEGAR"));
        tetel.setAfaalap(rs.getBigDecimal("AFAALAP"));
        tetel.setAfaSzazalek(rs.getBigDecimal("AFA_SZAZALEK"));
        tetel.setAfaertek(rs.getBigDecimal("AFAERTEK"));
        tetel.setBrutto(rs.getBigDecimal("BRUTTO"));
        tetel.setIktSzam(rs.getString("IKTSZAM"));
        tetel.setKozvSzolg(rs.getString("KOZVSZOLG"));
        tetel.setAfaKulcs(rs.getString("AFAKULCS"));
        try {
            tetel.setDevizaAfaalap(rs.getBigDecimal("DevizaAFAALAP"));
            tetel.setDevizaAfaertek(rs.getBigDecimal("DevizaAfaErtek"));
            tetel.setDevizaBrutto(rs.getBigDecimal("DevizaBrutto"));
        } catch (SQLException ignored) {}
        return tetel;
    }

    private String getTetelTableName() {
        return getTableName() + "T";
    }

    protected abstract Szamla.SzamlaType getType();

}
