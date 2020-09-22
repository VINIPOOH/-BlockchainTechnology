package ua.testing.blockchain.entity.impl;

public class TransactionVSI {
    private final String senderVSI;
    private final String recipientVSI;
    private final int amountVSI;


    public TransactionVSI(String senderVSI, String recipientVSI, int amountVSI) {
        this.senderVSI = senderVSI;
        this.recipientVSI = recipientVSI;
        this.amountVSI = amountVSI;
    }

    public String getSenderVSI() {
        return senderVSI;
    }

    public String getRecipientVSI() {
        return recipientVSI;
    }

    public int getAmountVSI() {
        return amountVSI;
    }

}
