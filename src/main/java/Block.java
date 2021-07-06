import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private String data ; //
    private final long timeStamp; // as number of milliseconds since 1/1/1970
    private int nonce;

    // Block Constructor

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash= calculateHash();
    }


    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash + timeStamp + nonce+ data
        );
        System.out.println("calculating Hash: " + hash);
        return calculatedhash;
    }

    public void mineBlock(int difficulty){
        String target = new String ( new char[difficulty]).replace('\0' ,'0');
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);

    }

}
