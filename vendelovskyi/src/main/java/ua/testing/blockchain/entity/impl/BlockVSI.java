package ua.testing.blockchain.entity.impl;

import java.util.List;

public class BlockVSI {
    private final int indexVSI;
    private final long timestampVSI;
    private final List<TransactionVSI> transactionsVSI;
    private final int proofVSI;
    private final String previousHashVSI;

    public BlockVSI(int indexVSI, int proofVSI, String previousHashVSI, List<TransactionVSI> transactionsVSI) {
        this.indexVSI = indexVSI;
        this.proofVSI = proofVSI;
        this.previousHashVSI = previousHashVSI;
        this.transactionsVSI = transactionsVSI;
        this.timestampVSI = System.currentTimeMillis();
    }

    public int getIndexVSI() {
        return indexVSI;
    }

    public long getTimestampVSI() {
        return timestampVSI;
    }

    public List<TransactionVSI> getTransactionsVSI() {
        return transactionsVSI;
    }

    public int getProofVSI() {
        return proofVSI;
    }

    public String getPreviousHashVSI() {
        return previousHashVSI;
    }
}
