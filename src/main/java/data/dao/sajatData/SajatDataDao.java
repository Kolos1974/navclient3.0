package data.dao.sajatData;

import config.Config;
import data.dao.BaseDao;
import exception.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SajatDataDao extends BaseDao {

    public void readCompanyData() throws DatabaseException {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM " + getTableName());
            ResultSet resultSet = pstmt.executeQuery()) {
            if (resultSet.next()) {
                Config.sajatCegAdoszam = resultSet.getString("SAJAT_CEG_ADOSZAM");
                Config.sajatCegNeve = resultSet.getString("SAJAT_CEG_NEVE");
                Config.sajatCegIrsz = resultSet.getString("SAJAT_CEG_IRSZ");
                Config.sajatCegVaros = resultSet.getString("SAJAT_CEG_VAROS");
                Config.sajatCegCim = resultSet.getString("SAJAT_CEG_CIM");
                Config.sajatCegKozterulet = resultSet.getString("SAJAT_CEG_KOZTERULET");
                Config.sajatCegHazszam = resultSet.getString("SAJAT_CEG_HAZSZAM");
                Config.baseUrl = resultSet.getString("NAV_API");
                Config.userName = resultSet.getString("NAV_LOGIN");
                Config.password = resultSet.getString("NAV_PASSWORD");
                Config.signKey = resultSet.getString("NAV_SIGNKEY");
                Config.exchangeKey = resultSet.getString("NAV_EXCHANGEKEY");
                Config.intervalTime = resultSet.getInt("NAV_REPEATTIME");
                Config.taxNumber = resultSet.getString("SAJAT_CEG_ADOSZAM").split("-")[0];
                Config.navImport = resultSet.getString("NAV_IMPORT");
                Config.navExport = resultSet.getString("NAV_EXPORT");
                Config.navMinTax = resultSet.getInt("NAV_MINTAX");
                Config.navInvoiceDate = resultSet.getTimestamp("NAV_INVOICEDATE");
                Config.softwareId = resultSet.getString("NAV_SW_ID");
                Config.softwareName = resultSet.getString("NAV_SW_NAME");
                Config.softwareMainVersion = resultSet.getString("NAV_SW_MAINVERSION");
                Config.softwareDevName = resultSet.getString("NAV_SW_DEVNAME");
                Config.softwareDevContact = resultSet.getString("NAV_SW_DEVCONTACT");
                Config.softwareDevCountryCode = resultSet.getString("NAV_SW_DEVCOUNTRYCODE");
                Config.softwareDevTaxNumber = resultSet.getString("NAV_SW_DEVTAXNUMBER");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    protected String getTableName() {
        return "STFOKSZ";
    }
}
