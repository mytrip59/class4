import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Thread.sleep;

public class ProductData {

    public int generateRandomAmountInt(int minLevel, int maxLevel){
        int result = 0;
        Random random = new Random(System.currentTimeMillis());
        result = minLevel + random.nextInt(maxLevel - minLevel + 1);
        try {
            sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    // min and max level contain in result
    public String generateRandomAmountString(int minLevel, int maxLevel){
        int randomInt = generateRandomAmountInt(minLevel, maxLevel);
        String randomString = Integer.toString(randomInt);
        return randomString;
    }

    public String generateRandomCost (){
        String resultString = null;
        int randomNumberInt = generateRandomAmountInt(1, 1000);
        float randomNumberFloat = (float)randomNumberInt/10;
        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator(',');
        resultString = new DecimalFormat("#0.0", unusualSymbols).format(randomNumberFloat);
        return resultString;
    }

    public String generateRandomName(int lenghOfName) {
        Random randomInt = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lenghOfName; i++) {
            sb.append(alphabet.charAt(randomInt.nextInt(alphabet.length())));
        }
        String randomName = sb.toString();
        return randomName;
        }


}
