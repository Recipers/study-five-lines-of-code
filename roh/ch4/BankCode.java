import java.util.Optional;
import java.util.stream.Stream;
import sun.rmi.transport.StreamRemoteCall;

public enum BankCode {

  KDB_BANK("002", "산업은행"),
  IBK_BANK("003", "기업은행"),
  KB_BANK("004", "국민은행"),
  KEB_BANK("005", "KEB 하나은행(구,외환은행)"),
  SUHYUP_BANK("007", "수협중앙회");

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
