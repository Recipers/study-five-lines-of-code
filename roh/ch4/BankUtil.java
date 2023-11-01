import java.util.ArrayList;
import java.util.List;

public class BankUtil {

  public static final String ACCOUNT_DELIMITER = "-";

  public static String formatAccountNumber(String accountNumber, BankCode bankCode) {

    if (bankCode == null) {
      return accountNumber;
    }
    return bankCode.formatAccountNumber(accountNumber);
  }

  public static String addHyphens(String accountNumber, int... positions) {

    if (lengthOfAccountNumber(accountNumber) < lastPosition(positions)) {
      return accountNumber;
    }

    List<String> subStringList = mapStringToListByPosition(accountNumber, positions);
    return String.join(ACCOUNT_DELIMITER, subStringList);
  }

  private static int lengthOfAccountNumber(String accountNumber) {
    return accountNumber.length();
  }

  private static int lastPosition(int... positions) {
    return positions[positions.length - 1];
  }

  private static int findSubstringBegin(int index, int... positions) {
    int begin;
    if (index == 0) {
      begin = 0;
    } else {
      begin = positions[index - 1];
    }
    return begin;
  }

  private static List<String> mapStringToListByPosition(String accountNumber, int... positions) {

    List<String> subStringList = new ArrayList<>();
    for (int i = 0; i < positions.length; i++) {
      int begin = findSubstringBegin(i, positions);
      subStringList.add(accountNumber.substring(begin, positions[i]));
      if (i == positions.length - 1) {
        subStringList.add(accountNumber.substring(positions[i]));
      }
    }
    return subStringList;
  }

}
