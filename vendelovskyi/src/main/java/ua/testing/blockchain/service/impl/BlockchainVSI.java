package ua.testing.blockchain.service.impl;

import com.google.common.hash.Hashing;
import ua.testing.blockchain.entity.impl.BlockVSI;
import ua.testing.blockchain.entity.impl.TransactionVSI;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlockchainVSI {

    private static final int GENESIS_PROOF_VSI = 21091998;
    private static final String GENESIS_HASH_VSI = "vendelovskyi";
    private static final String PROF_OF_WORK_VSI = "09";
    private final List<BlockVSI> chainVSI = new ArrayList<>();
    private final List<TransactionVSI> currentTransactionsVSI = new ArrayList<>();

    public BlockchainVSI() {
        newBlockVSI(GENESIS_PROOF_VSI, GENESIS_HASH_VSI);
    }

    /**
     * @param proof        Докази проведенної роботи
     * @param previousHash Хеш попереднього блока
     * @return Новий блок
     **/
    public BlockVSI newBlockVSI(int proof, String previousHash) {

        // створюмо копію списка

        List<TransactionVSI> transactionVSIS = this.currentTransactionsVSI.stream().collect(Collectors.toList());

        // створюємо новий об'єкт блока

        BlockVSI newBlockVSI = new BlockVSI(this.chainVSI.size(), proof, previousHash, transactionVSIS);

        // очищаємо список транзакцій
        this.currentTransactionsVSI.clear();

        // додаємо новий блок у цепочку
        this.chainVSI.add(newBlockVSI);

        // повертаємо новий блок
        return newBlockVSI;
    }

    /**
     * Направляє нову транзакцію в наступний блок
     *
     * @param sender    Адреса відправника
     * @param recipient Адреса отримувача
     * @param amount    Сума
     * @return Індекса блока, що буде зберігати цю транзакцію
     */

    public int newTransactionVSI(String sender, String recipient, int amount) {
        this.currentTransactionsVSI.add(new TransactionVSI(sender, recipient, amount));
        return this.chainVSI.size();
    }

    /**
     * @param blockVSI Блок
     * @return Хеш блока
     */
    public String hashVSI(BlockVSI blockVSI) {
        StringBuilder hashingInputBuilder = new StringBuilder();

        // додаємо параметри блока у змінну в певному незмінному порядку

        hashingInputBuilder.append(blockVSI.getIndexVSI())
                .append(blockVSI.getTimestampVSI()).append(blockVSI.getProofVSI())
                .append(blockVSI.getPreviousHashVSI());

        String hashingInput = hashingInputBuilder.toString();
        // генеруємо хеш блока на основі її полів за допомогою змінної

        return Hashing.sha256().hashString(hashingInput, StandardCharsets.UTF_8).toString();

    }

    public BlockVSI lastBlockVSI() {
        return this.chainVSI.size() > 0 ? this.chainVSI.get(this.chainVSI.size() - 1) : null;
    }

    /**
     * Проста перевірка алгоритму: Пошук числа p`, так як hash(pp`)
     * містить 4
     * заголовних нуля, де p — попередній p є попереднім доказом, а p` - 5новим
     *
     * @param lastProofOfWork
     * @return int
     */
    public int proofOfWorkVSI(int lastProofOfWork) {
        int proof = 0;
        while (!isProofValidVSI(lastProofOfWork, proof)) {
            proof++;
        }
        return proof;
    }

    /**
     * Підтвердження доказу: Чи містить hash(lastProof, proof)
     * 4 заголовних нуля
     *
     * @param lastProof
     * @param proof
     * @return
     */
    private boolean isProofValidVSI(int lastProof, int proof) {

        String guessString = Integer.toString(lastProof) + proof;
        String guessHash = Hashing.sha256().hashString(guessString,
                StandardCharsets.UTF_8).toString();
        return guessHash.endsWith(PROF_OF_WORK_VSI);
    }
}