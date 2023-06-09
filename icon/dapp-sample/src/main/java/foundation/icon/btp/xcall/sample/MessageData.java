package foundation.icon.btp.xcall.sample;

import score.ByteArrayObjectWriter;
import score.Context;
import score.ObjectReader;
import score.ObjectWriter;

import java.io.Serializable;
import java.math.BigInteger;

public class MessageData implements Serializable {
    private final BigInteger id;
    private final String message;
    private int offset;
    private int length;

    public MessageData(BigInteger id,  String message,int offset, int length) {
        this.id = id;
        this.message = message;
        this.offset = offset;
        this.length = length;
    }

    public BigInteger getId() {
        return id;
    }
    public int getLength() {
        return length;
    }
    public int getOffset() {
        return offset;
    }

    public String getMessage() {
        return message;
    }



    public static void writeObject(ObjectWriter w, MessageData data) {
        w.beginList(4);
        w.write(data.id);
        w.write(data.message);
        w.write(data.offset);
        w.write(data.length);
        w.end();
    }

    public static MessageData readObject(ObjectReader r) {
        r.beginList();
        MessageData rbData = new MessageData(
                r.readBigInteger(),
                r.readString(),
                r.readInt(),
                r.readInt()
        );
        r.end();
        return rbData;
    }


    public byte[] toBytes() {
        ByteArrayObjectWriter writer = Context.newByteArrayObjectWriter("RLPn");
        writeObject(writer, this);
        return writer.toByteArray();
    }

    public static MessageData fromBytes(byte[] bytes) {
        ObjectReader reader = Context.newByteArrayObjectReader("RLPn", bytes);
        return readObject(reader);
    }

    
    

}
