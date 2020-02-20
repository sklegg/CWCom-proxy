package ki7lbe.protocol;

import java.util.Arrays;

public abstract class BaseDatagram {

    protected int DATA_LENGTH;
    protected boolean decoded;
    protected byte[] data;

    public BaseDatagram() {
        DATA_LENGTH = 0;
        decoded = false;
    }

    public BaseDatagram(int length) {
        DATA_LENGTH = length;
        decoded = false;
    }

    public void decode() {
        decoded = true;
    }

    public byte[] getRawData() {
        return data;
    }

    public void setRawData(byte[] data) {
        this.data = data;
    }

    public String getStringData(int start, int end) {
        return Arrays.toString(Arrays.copyOfRange(data, start, end));
    }

    public boolean isDecoded() {
        return decoded;
    }

    public String getStringData(boolean hex) {
        if (hex) {
            StringBuilder sb = new StringBuilder();
            for (byte b : data) {
                sb.append(String.format("%02x ", b));
            }
            return sb.toString();
        } else {
            return Arrays.toString(data);
        }
    }

}
