import java.util.ArrayList;
import java.util.List;

public class BankUtil {

  public static final String ACCOUNT_DELIMITER = "-";

  public static String formatAccountNumber(String accountNumber, String bankCode) throws Exception {

    return formatAccountNumber(accountNumber, BankCode.getBankByCode(bankCode));
  }

  public static String formatAccountNumber(String accountNumber, BankCode bankCode) {

    if (bankCode == null) {
      return accountNumber;
    }

    switch (bankCode) {
      case KDB_BANK:
        if (accountNumber.length() == 11) {
          accountNumber = addHyphens(accountNumber, new int[]{3, 5, 10});
        } else {
          accountNumber = addHyphens(accountNumber, new int[]{3, 11});
        }
        break;
      case IBK_BANK:
        if (accountNumber.length() == 12) {
          accountNumber = addHyphens(accountNumber, new int[]{3, 5, 10});
        } else {
          accountNumber = addHyphens(accountNumber, new int[]{3, 9, 11});
        }
        break;
      case KB_BANK:
        if (accountNumber.length() == 12) {
          accountNumber = addHyphens(accountNumber, new int[]{3, 5, 9});
        } else {
          accountNumber = addHyphens(accountNumber, new int[]{6, 8});
        }
        break;
      case KEB_BANK:
        if (accountNumber.length() == 11) {
          accountNumber = addHyphens(accountNumber, new int[]{3, 5, 10});
        } else {
          accountNumber = addHyphens(accountNumber, new int[]{3, 9});
        }
        break;
      case SUHYUP_BANK:
        if (accountNumber.length() == 11) {
          accountNumber = addHyphens(accountNumber, new int[]{3, 5, 10});
        } else if (accountNumber.length() == 12) {
          accountNumber = addHyphens(accountNumber, new int[]{3, 11});
        } else {
          accountNumber = addHyphens(accountNumber, new int[]{3, 5, 13});
        }
        break;
      case NONGHYUP_BANK:
        accountNumber = addHyphens(accountNumber, new int[]{3, 7, 11});
        break;
      case NONGHYUP_REGION_BANK:
      case WOORI_BANK:
        accountNumber = addHyphens(accountNumber, new int[]{4, 7});
        break;
      case SC_BANK:
        accountNumber = addHyphens(accountNumber, new int[]{3, 5});
        break;
      case CITI_BANK:
        accountNumber = addHyphens(accountNumber, new int[]{3, 9});
        break;
      case DAEGU_BANK:
        accountNumber = addHyphens(accountNumber, new int[]{3, 5, 11});
        break;
      case BUSAN_BANK:
        accountNumber = addHyphens(accountNumber, new int[]{3, 7, 11});
        break;
      case GWANGJU_BANK:
        accountNumber = addHyphens(accountNumber, new int[]{3, 6});
        break;
      case JEJU_BANK:
        accountNumber = addHyphens(accountNumber, new int[]{2, 4});
        break;
      case JEONBUK_BANK:
        if (accountNumber.length() == 12) {
          accountNumber = addHyphens(accountNumber, new int[]{3, 5});
        } else {
          accountNumber = addHyphens(accountNumber, new int[]{1, 4, 6});
        }
        break;
      case KN_BANK:
        if (accountNumber.length() == 12) {
          accountNumber = addHyphens(accountNumber, new int[]{3, 5});
        } else {
          accountNumber = addHyphens(accountNumber, new int[]{3, 12});
        }
        break;
      case KFCC:
        if (accountNumber.length() == 13) {
          accountNumber = addHyphens(accountNumber, new int[]{4, 6, 12});
        } else {
          accountNumber = addHyphens(accountNumber, new int[]{4, 7, 13});
        }
        break;
      case SHINHYUP:
        if (accountNumber.length() == 10) {
          accountNumber = addHyphens(accountNumber, new int[]{3, 6});
        } else if (accountNumber.length() == 11) {
          accountNumber = addHyphens(accountNumber, new int[]{3, 7});
        } else if (accountNumber.length() == 12) {
          accountNumber = addHyphens(accountNumber, new int[]{3, 6, 11});
        } else if (accountNumber.length() == 13) {
          accountNumber = addHyphens(accountNumber, new int[]{5, 7, 12});
        } else {
          accountNumber = addHyphens(accountNumber, new int[]{5, 7, 13});
        }
        break;
      case MUTUAL_SAVING_BANK:
        accountNumber = addHyphens(accountNumber, new int[]{3, 5, 7, 13});
        break;
      case HSBC_BANK:
        accountNumber = addHyphens(accountNumber, new int[]{3, 6, 7});
        break;
      case DEUTSCHE_BANK:
        accountNumber = addHyphens(accountNumber, new int[]{2, 7});
        break;
      case POST_OFFICE:
        accountNumber = addHyphens(accountNumber, new int[]{6, 8});
        break;
      case SHINHAN_BANK:
        if (accountNumber.length() == 11) {
          accountNumber = addHyphens(accountNumber, new int[]{3, 5});
        } else {
          accountNumber = addHyphens(accountNumber, new int[]{3, 6});
        }
        break;
      case K_BANK:
        accountNumber = addHyphens(accountNumber, new int[]{3, 6});
        break;
      case KAKAO_BANK:
        accountNumber = addHyphens(accountNumber, new int[]{4, 6});
        break;
      case KOREA_BANK:
      case EXIM_BANK:
      case MORGAN_STANLEY_BANK:
        break;
      default:
        break;
    }

    return accountNumber;
  }

  private static String addHyphens(String accountNumber, int... positions) {

    List<String> subStringList = new ArrayList<>();
    if (accountNumber.length() < positions[positions.length - 1]) {
      return accountNumber;
    }
    for (int i = 0; i < positions.length; i++) {
      int begin;
      if (i == 0) {
        begin = 0;
      } else {
        begin = positions[i - 1];
      }
      subStringList.add(accountNumber.substring(begin, positions[i]));
      if (i == positions.length - 1) {
        subStringList.add(accountNumber.substring(positions[i]));
      }
    }
    return String.join(ACCOUNT_DELIMITER, subStringList);
  }

}
