import java.util.Optional;
import java.util.stream.Stream;
import sun.rmi.transport.StreamRemoteCall;

public enum BankCode {

  KOREA_BANK("001", "한국은행"),
  KDB_BANK("002", "산업은행"),
  IBK_BANK("003", "기업은행"),
  KB_BANK("004", "국민은행"),
  KEB_BANK("005", "KEB 하나은행(구,외환은행)"),
  SUHYUP_BANK("007", "수협중앙회"),
  EXIM_BANK("008", "수출입은행"),
  NONGHYUP_BANK("011", "농협은행"),
  NONGHYUP_REGION_BANK("012", "지역농축협"),
  WOORI_BANK("020", "우리은행"),
  SC_BANK("023", "SC 제일은행"),
  CITI_BANK("027", "한국씨티은행"),
  DAEGU_BANK("031", "대구은행"),
  BUSAN_BANK("032", "부산은행"),
  GWANGJU_BANK("034", "광주은행"),
  JEJU_BANK("035", "제주은행"),
  JEONBUK_BANK("037", "전북은행"),
  KN_BANK("039", "경남은행"),
  KFCC("045", "새마을금고중앙회"),
  SHINHYUP("048", "신협중앙회"),
  MUTUAL_SAVING_BANK("050", "상호저축은행"),
  MORGAN_STANLEY_BANK("052", "모건스탠리은행"),
  HSBC_BANK("054", "HSBC 은행"),
  DEUTSCHE_BANK("055", "도이치은행"),
  POST_OFFICE("071", "우체국"),
  SHINHAN_BANK("088", "신한은행"),
  K_BANK("089", "케이뱅크"),
  KAKAO_BANK("090", "카카오뱅크");

  private String code;

  private String name;

  String getCode() {
    return this.code;
  }

  String getName() {
    return this.name;
  }

  BankCode(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public static BankCode getBankByCode(String code) throws Exception {

    Stream<BankCode> bankCodeStream = Stream.of(values());
    Stream<BankCode> filteredBankCodeStream = bankCodeStream.filter(
        bankCode -> bankCode.getCode().equals(code));
    Optional<BankCode> optionalBank = filteredBankCodeStream.findAny();
    return optionalBank.orElseThrow(() -> new Exception("Cannot Find BankCode"));
  }

}
