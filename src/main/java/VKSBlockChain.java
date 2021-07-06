import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class VKSBlockChain {

    public static int difficulty = 2;

    public static ArrayList<Block> blockchain = new ArrayList<>();

    public static void main(String[] args) {
        //add our blocks to the blockchain ArrayList:
        blockchain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine block 2... ");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine block 3... ");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }

    public static Boolean isChainValid(){
        Block current;
        Block previous;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes
        for (int i = 1; i < blockchain.size(); i++) {
            current = blockchain.get(i);
            previous = blockchain.get(i-1);

            // compare registered hash and calculated hash
            if(!current.hash.equals(current.calculateHash())){
                System.out.println("Current hashes are not equal");
                return false;
            }

            // compare previous hash and calculated previous hash
            if(!previous.hash.equals(current.previousHash)){
                System.out.println("Previous hashes are not equal");
                return false;
            }

            //check if hash is solved
            if(!current.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }

        return true;
    }

}
