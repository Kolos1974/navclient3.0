package data.dao.navStatus;

import data.dao.BaseDao;
import data.entity.NavStatus;
import data.entity.Szamla;
import exception.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NavStatusDao extends BaseDao {

    public void insert(NavStatus navStatus) throws DatabaseException {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO " + getTableName() + " (IKTSZAM, TYPE, REQUESTID, TRANSACTIONID, INVOICESTATUS, DATUM) " +
                             "VALUES (?,?,?,?,?,?)"
             )
        ) {
            pstmt.setString(1, navStatus.getIktszam());
            pstmt.setString(2, navStatus.getType());
            pstmt.setString(3, navStatus.getRequestid());
            pstmt.setString(4, navStatus.getTransactionid());
            pstmt.setString(5, navStatus.getInvoicestatus());
            pstmt.setTimestamp(6, navStatus.getDatum());
            pstmt.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<NavStatus> getAllNotDoneOrAborted() throws DatabaseException {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM " + getTableName() +
                             " WHERE NOT INVOICESTATUS = '" + Szamla.States.DONE.name() + "'"
                     + " AND NOT INVOICESTATUS = '" + Szamla.States.ABORTED.name() + "';"
        );
        ResultSet transactionStatusResultSet = pstmt.executeQuery();
        ){
            List<NavStatus> navStatusList = new ArrayList<>();
            while (transactionStatusResultSet.next()) {
                NavStatus navStatus = new NavStatus();
                navStatus.setDatum(transactionStatusResultSet.getTimestamp("DATUM"));
                navStatus.setRequestid(transactionStatusResultSet.getString("REQUESTID"));
                navStatus.setTransactionid(transactionStatusResultSet.getString("TRANSACTIONID"));
                navStatus.setInvoicestatus(transactionStatusResultSet.getString("INVOICESTATUS"));
                navStatus.setIktszam(transactionStatusResultSet.getString("IKTSZAM"));
                navStatus.setType(transactionStatusResultSet.getString("TYPE"));
                navStatus.setId(transactionStatusResultSet.getInt("ID"));
                navStatusList.add(navStatus);
            }
            return navStatusList;
        } catch(SQLException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updateStatus(NavStatus navStatus) throws DatabaseException {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE " + getTableName() + " SET INVOICESTATUS = ? WHERE ID = ?"
             )
        ){
            pstmt.setString(1, navStatus.getInvoicestatus());
            pstmt.setInt(2, navStatus.getId());
            pstmt.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    protected String getTableName() {
        return "NAV_STATUS";
    }

}
