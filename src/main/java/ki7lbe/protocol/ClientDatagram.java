package ki7lbe.protocol;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;

public class ClientDatagram extends BaseDatagram {

    private String callsign;
    private int sequence;
    private short channel;

    public ClientDatagram(int length) {
        super(length);
        DATA_LENGTH = length;
        decoded = false;
        callsign = "";
        sequence = -1;
        channel = -1;
    }

    public void decode() {
        System.out.print(Instant.now().getEpochSecond() + " - ");
        //System.out.println(getStringData(false));
        System.out.println(getStringData(true));
        boolean callsignDecoded = false;

        if (data[0] == 4) {
            channel = (short) (data[3]<<8 | data[2] & 0xFF);
        }

        for (int i = 0; i < DATA_LENGTH; i++)
        {
            if (i <= 3) {
                //System.out.println(data[i]);
                continue;
            }

            // call sign
            if (i <= 54) {
                if (!callsignDecoded) {
                    callsign = new String(Arrays.copyOfRange(data, 4, 54), StandardCharsets.US_ASCII)
                            .replace("\0", "");
                    callsignDecoded = true;
                }
                continue;
            }

            // datagram sequence number
            if (i == 136) {
                sequence = data[i];
                continue;
            }



        }

        //System.out.println("short " + getStringData(137,173));
        //System.out.println(toString());
        //System.out.println();
        decoded = true;
    }

    public byte[] getRawData() {
        return data;
    }

    public void setRawData(byte[] data) {
        this.data = data;
    }

    public String getCallsign() {
        return callsign;
    }

    public String toString() {
        String result = "";

        result = result + "callsign [" + getCallsign() + "] ";
        result = result + "sequence [" + sequence + "] ";
        result = result + "channel [" + channel + "] ";
        return result;
    }
}
