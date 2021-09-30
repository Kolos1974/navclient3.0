package config;

import exception.SHA512Exception;
import hu.gov.nav.schemas.osa._3_0.api.SoftwareOperationType;
import utils.Algos;

import java.sql.Timestamp;

public class Config {

    public static String baseUrl;
    public static String signKey;
    public static String requestVersion = "3.0";
    public static String headerVersion = "1.0";
    public static String userName;
    private static String passwordHash;
    public static String password;
    public static String taxNumber;
    public static String exchangeKey;
    public static int intervalTime;
    public static String navImport;
    public static String navExport;
    public static int navMinTax;
    public static Timestamp navInvoiceDate;

    // SOFTWARE ADATOK
    public static String softwareId;
    public static String softwareName;
    public static SoftwareOperationType softwareOperation = SoftwareOperationType.LOCAL_SOFTWARE;
    public static String softwareMainVersion;
    public static String softwareDevName;
    public static String softwareDevContact;
    public static String softwareDevCountryCode;
    public static String softwareDevTaxNumber;

    //DB ADATOK
    public static String dbUrl;
    public static String dbName;
    public static String dbUsername;
    public static String dbPassword;

    public static String getPasswordHash() throws SHA512Exception {
        if (passwordHash == null || passwordHash.equals("")) {
            passwordHash = Algos.generateSha512From(password);
        }
        return passwordHash;
    }

    //SAJAT CEG ADATOK
    public static String sajatCegAdoszam;
    public static String sajatCegIrsz;
    public static String sajatCegNeve;
    public static String sajatCegVaros;
    public static String sajatCegCim;
    public static String sajatCegKozterulet;
    public static String sajatCegHazszam;
    public static String sajatCountryCode = "HU";

    //ALKALMAZASMODOK

    public static AppModes appMode;

    public enum AppModes {
        XML, MSSQL, NONE;
    }

    public static final String SHA_512  = "SHA-512";
    public static final String SHA2_512 = "SHA2-512";
    public static final String SHA3_512 = "SHA3-512";
}
