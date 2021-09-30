package requestFactories;

import config.Config;
import exception.SHA512Exception;
import hu.gov.nav.schemas.ntca._1_0.common.*; 
import hu.gov.nav.schemas.osa._3_0.api.SoftwareType;
import utils.Algos;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Common {

    public static String getFormattedDate(Instant now) {
        return DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.ofInstant(now, ZoneOffset.UTC));
    }

    public static String getUid(Instant now) {
        return "RID" +  getFormattedDate(now) + UUID.randomUUID().toString().replace("-", "").substring(0,10);
    }

    public static BasicHeaderType getBasicHeaderType(String requestId, XMLGregorianCalendar xmlGregorianCalendar) {
        BasicHeaderType basicHeaderType = new BasicHeaderType();
        basicHeaderType.setRequestId(requestId);
        basicHeaderType.setTimestamp(xmlGregorianCalendar.normalize());
        basicHeaderType.setRequestVersion(Config.requestVersion);
        basicHeaderType.setHeaderVersion(Config.headerVersion);
        return basicHeaderType;
    }

    public static UserHeaderType getUserHeaderTypeNormal(Instant now, String requestId) throws SHA512Exception {
        UserHeaderType userHeaderType = new UserHeaderType();
        userHeaderType.setLogin(Config.userName);
//      userHeaderType.setPasswordHash(generateCryptoType(Config.getPasswordHash(),Config.SHA2_512.toString()));
        userHeaderType.setPasswordHash(generateCryptoType(Config.getPasswordHash(),Config.SHA_512.toString()));
        userHeaderType.setTaxNumber(Config.taxNumber);
        userHeaderType.setRequestSignature(generateCryptoType(Algos.generateSha3512From(requestId + Common.getFormattedDate(now) + Config.signKey).toUpperCase(),Config.SHA3_512.toString()));
        return userHeaderType;
    }

    public static SoftwareType getSoftwareType() {
        SoftwareType softwareType = new SoftwareType();
        softwareType.setSoftwareId(Config.softwareId);
        softwareType.setSoftwareName(Config.softwareName);
        softwareType.setSoftwareOperation(Config.softwareOperation);
        softwareType.setSoftwareMainVersion(Config.softwareMainVersion);
        softwareType.setSoftwareDevName(Config.softwareDevName);
        softwareType.setSoftwareDevContact(Config.softwareDevContact);
        softwareType.setSoftwareDevCountryCode(Config.softwareDevCountryCode);
        softwareType.setSoftwareDevTaxNumber(Config.softwareDevTaxNumber);
        return softwareType;
    }
    
    public static CryptoType generateCryptoType(String hashValue, String hashType) {
    	CryptoType cryptoType = new CryptoType();
    	cryptoType.setValue(hashValue);		
    	cryptoType.setCryptoType(hashType);
    	return cryptoType;
    }
    
    

}
