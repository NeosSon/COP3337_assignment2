// dif stand for difference
interface Constants {
    int wrap = 26;
    int encode_dif = 3;
    int decode_dif = 23;
}

abstract class Cipher {
    private String message;
    StringBuffer encrypted_message, decrypted_message;

    public Cipher(String text) {
        message = text;
    }

    public final void encrypt() {
        encrypted_message = new StringBuffer();
        String[] words = message.split(" ");
        for (String word : words) {
            word = encode(word) + " ";
            encrypted_message.append(word);
        }
    }

    public final void decrypt(String message) {
        decrypted_message = new StringBuffer();
        String[] words = message.split(" ");
        for (String word : words) {
            word = decode(word) + " ";
            decrypted_message.append(word);
        }
    }

    public String getEncodedMessage() {
        return encrypted_message.toString();
    }

    public String getDecodedMessage() {
        return decrypted_message.toString();
    }

    public abstract String encode(String s);

    public abstract String decode(String s);
}

class Caesar extends Cipher {
    public Caesar(String s) {
        super(s);
    }

    public String encode(String word) {
        return code(word, Constants.encode_dif);
    }

    public String decode(String word) {
        return code(word, Constants.decode_dif);
    }

    String code(String word, int DIF) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            character = determineCharacter(character, DIF);
            result.append(character);
        }
        return result.toString();
    }

    char determineCharacter(char character, final int dif) {
        if (Character.isUpperCase(character))
            character = (char) ('A' + (character - 'A' + dif) % Constants.wrap);
        else if (Character.isLowerCase(character))
            character = (char) ('a' + (character - 'a' + dif) % Constants.wrap);
        return character;
    }
}
